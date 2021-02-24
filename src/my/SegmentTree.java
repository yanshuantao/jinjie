package my;


/**
 * 线段树
 * @author Administrator
 *
 */
public class SegmentTree {
	
	int[] arr;
	int[] sum;
	int[] lazyAdd;
	
	
	public SegmentTree(int[] original) {
		int len = original.length;
		arr = new int[len + 1];
		for(int i = 0;i<arr.length - 1;i++) {
			arr[i + 1] = original[i];
		}
		sum = new int[2*len];
		lazyAdd = new int[2*len];
	}
	
	/**
	 * 区间加法
	 * @param arr
	 * @param L
	 * @param R
	 * @param num
	 */
	public void add(int L,int R,int num){
		addLoop(L, R, num, 1, sum.length,1);
	}
	public void addLoop(int L,int R,int num,int curL,int curR,int lazyIndex) {
		if(L <= curL && R >= curR) {
			//当前区间已经被包,不再向下分发任务
			lazyAdd[lazyIndex] += num;
			//更新sum值
			sum[lazyIndex] += (R-L+1)*num;
			return ;
		}
		int mid = L + R/2;
		//向下分发之前堆积的任务
		pushDown(lazyIndex, mid - L + 1, R - mid - 1 + 1);
		
		//R小于curL 或者L>curR,都不进行分发
		//上句等于curL大于R 
		if(L <= mid) {
			//向左节点分发任务
			addLoop(L, R, num, curL, mid, 2*lazyIndex);
		}
		if(R > mid) {
			//向右节点分发任务
			addLoop(L, R, num, mid + 1, curR, 2*lazyIndex + 1);
		}
				
	}
	/**
	 * 将任务下发
	 * @param lazyIndex
	 * @param cl左节点接到任务的元素数量
	 * @param cr右子树接到任务的元素数量
	 */
	public void pushDown(int lazyIndex,int cl,int cr) {
		if(lazyAdd[lazyIndex] != 0) {
			//下发add任务
			//左节点的懒任务更新和sum更新
			lazyAdd[2*lazyIndex] += lazyAdd[lazyIndex];
			sum[2*lazyIndex] += cl*lazyAdd[lazyIndex];
			
			//左节点的懒任务更新和sum更新
			lazyAdd[2*lazyIndex + 1] += lazyAdd[lazyIndex];
			sum[2*lazyIndex + 1] += cr*lazyAdd[lazyIndex];
			
			//将当前的add任务重置为0
			sum[lazyIndex] = 0;
		}
	}
	public void update(int L,int R,int num) {
		
	}
	public void search(int L,int R) {
		
	}
	/**
	 * 构建arr数组L-R区间的线段树
	 * @param arr
	 * @param L
	 * @param R
	 * @return
	 */
	public void build(int L,int R,int sumIndex) {
		if(L == R) {
			sum[sumIndex] = arr[L];
			return ;
		}
		int mid = (L+R)/2;
		//左节点
		build(L, mid, 2*sumIndex);
		//右节点
		build(mid + 1,R,2*sumIndex + 1);
		//当前节点
		sum[sumIndex] = sum[2*sumIndex] + sum[2*sumIndex + 1];
	}
	

	public static void main(String[] args) {
		int[] arrys = {1,2,3,4};
		SegmentTree segmentTree = new SegmentTree(arrys);
		segmentTree.build(1, 4, 1);
		System.out.println(segmentTree.sum);
	}

}
