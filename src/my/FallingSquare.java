package my;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * 简易俄罗斯方块
 * @author Administrator
 *
 */
public class FallingSquare {
	
	static class SegmentTree{
		
		int[] maxArr;
		int[] updateArr;
		boolean[] isUpdate;
		
		public SegmentTree(int size) {
			int N = size + 1;
			maxArr = new int[N << 2];
			updateArr = new int[N << 2];
			isUpdate = new boolean[N << 1];
		}
		/**
		 * 线段树更新
		 * @param L
		 * @param R
		 * @param num
		 * @param segIndex
		 */
		public void update(int L,int R,int num,int curL,int curR,int segIndex) {
			if(L <= curL && R >= curR) {
				maxArr[segIndex] = num;
				updateArr[segIndex] = num;
				isUpdate[segIndex] = true;
				return ;
			}
			//向下分发任务前需要先将之前的懒任务下发下去
			pushDown(segIndex);
			int mid = (curR + curL) >> 2;
			//判断左节点是否需要下发
			if(L < mid) {
				update(L, R, num, curL, mid, segIndex << 1);
			}
			if(R <= mid) {
				update(L, R, num, mid + 1, curR, segIndex << 1 | 1);
			}
			//更新当前节点的max值
			maxArr[segIndex] = Math.max(maxArr[segIndex << 1], maxArr[segIndex << 1 | 1]);
			
		}
		public void pushDown(int segIndex) {
			//判断是否有任务
			if(!isUpdate[segIndex]) {
				return ;
			}
			//左节点分发
			maxArr[segIndex << 1] = updateArr[segIndex];
			updateArr[segIndex <<1] = updateArr[segIndex];
			isUpdate[segIndex << 1] = true;
			//右节点分发
			maxArr[segIndex << 1 | 1] = updateArr[segIndex];
			updateArr[segIndex << 1 | 1] = updateArr[segIndex];
			isUpdate[segIndex << 1 | 1] = true;
			//当前节点清空
			isUpdate[segIndex] = false;
		}
		/**
		 * 
		 * @return
		 */
		public int search(int L,int R,int curL,int curR,int segIndex) {
			if(L >= curL && R <= curR) {
				return maxArr[segIndex];
			}
			int mid = (curL + curR) >> 1;
			//查询前先下发懒任务
			pushDown(segIndex);
			int leftMax = 0;
			int rightMax = 0;
			//是否需要向左查最大的
			if(L < mid) {
				leftMax = search(L, R, curL, mid, segIndex << 1);
			}
			if(R >= mid) {
				rightMax = search(leftMax, R, mid + 1, curR, segIndex << 1 | 1);
			}
			return Math.max(leftMax, rightMax);
		}
	}
	
	public List<Integer> fall(int[][] squares) {
		List<Integer> list = new ArrayList<Integer>();
		//先进行离散
		Map<Integer,Integer> discreteMap = discrete(squares);
		
		//构建线段树
		SegmentTree segmentTree = new SegmentTree(discreteMap.size());
		int max = 0;
		for(int[] square : squares) {
			int L = discreteMap.get(square[0]);
			int R = discreteMap.get(square[0] + square[1] - 1);
			//先查询L--R目前最大值
			int height = segmentTree.search(L, R, 1, discreteMap.size(), 1) + square[1];
			max = Math.max(max, height);
			list.add(max);
			//更新
			segmentTree.update(L, R, height, 1, discreteMap.size(), 1);
		}
		
		return list;
	}
	
	/**
	 * 离散
	 */
	public Map<Integer,Integer> discrete(int[][] squares) {
		Map<Integer,Integer> discreteMap = new HashMap<Integer,Integer>();
		TreeSet<Integer> sortedList = new TreeSet<Integer>();
		for(int[] square :squares) {
			//square[0]是x轴位置,[1]是方块的边长
			//每个方块的L位置
			sortedList.add(square[0]);
			//每个方块的R位置
			sortedList.add(square[0] + square[1] - 1);
		}
		int count = 0;
		for(Integer i:sortedList) {
			//x轴所有坐标新旧映射
			discreteMap.put(i, count++);
		}
		return discreteMap;
	}
	

	public static void main(String[] args) {
		System.out.println(2<<1);
		int a=0;
		System.out.println(a++);
		System.out.println(++a);
	}

}
