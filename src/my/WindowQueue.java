package my;

import java.util.LinkedList;

/**
 * 假设固定大小W窗口，依次划过arr,返回每一次滑出状况的最大值
 * @author Administrator
 *
 */
public class WindowQueue {

	
	public static void f(int[] datas,int winSize) {
		int len = datas.length;
		
		int[] res = new int[len - winSize + 1];
		int index = 0;
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		for(int r = 0;r<len;r++) {
			//往队列加数据
			//先弹出尾部小于等于当前的数据
			while(!list.isEmpty() && datas[list.getLast()] <= datas[r]) {
				list.pollLast();
			}
			//弹出完毕后加数据
			list.addLast(r);
			//判断当前窗口移除的数据是否是队列头部数据
//			1,2,3,4
			if(r - winSize == list.peekFirst()) {
				list.pollFirst();
			}
			if(r >= winSize - 1) {
				res[index++] = datas[list.peekFirst()];
			}
		}
		
		
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
