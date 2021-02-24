package my;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MonotonousStack {
	
	
	/**
	 * 没有重复值的算法
	 * @param arr
	 * @return
	 */
	public static int[][] getNearLessNoRepeat(int[] arr){
//		[2, 1, 5, 4, 0, 3]
		//思路：
		//1.遍历原数组，
		//2.做一个从小到大顺序栈，将数组中数据索引依次扔进栈中
		//3.如果顶部元素大于当前元素,那么顶部元素比它小的右边最近的数就是当前元素
		//4.将此元素弹出，将当前元素压入栈中，并更新右边最近小于的数
		//5.如果顶部元素小于当前元素,那么当前元素小于它的左边最近的数就是顶部元素,记录并将当前元素压入栈中
		//6.所有元素遍历完成之后,开始依次弹出栈中元素
		int[][] res = new int[arr.length][2];
		int len = arr.length;
		
		Stack<Integer> stack = new Stack<Integer>();
		for(int i = 0;i<len;i++) {
			while(!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
				//弹出顶部元素,并更新顶部元素右边小于它的最近的数是当前元素
				Integer popIndex = stack.pop();
				res[popIndex][1] = i;
			}
			//将当前元素压入栈中,并且更新它的左边小于它的最近的数为栈中当前顶部元素
			if(stack.isEmpty()) {
				res[i][0] = -1;
			}else {
				res[i][0] = stack.peek();
			}
			stack.push(i);
		}
		while(!stack.isEmpty()) {
			Integer pop = stack.pop();
			res[pop][1] = -1;
		}
		return res;
	}
	public static int[][] getNearLess(int[] arr) {
		int[][] res = new int[arr.length][2];
		int len = arr.length;
//		[2, 1,1,5, 4, 0, 3]

		Stack<List<Integer>> stack = new Stack<>();
		for(int index = 0;index<len;index++) {
			while(!stack.isEmpty() && arr[stack.peek().get(0)] > arr[index]) {
				//弹出整个集合
				List<Integer> popList = stack.pop();
				for(Integer pop:popList) {
					res[pop][1] = index;
				}
			}
//			List<Integer> curList = stack.peek();
			if(!stack.isEmpty()) {
				List<Integer> peek = stack.peek();
				if(arr[peek.get(0)] == arr[index]) {
					List<Integer> pop = stack.pop();
					//拷贝结果
					res[index][0] = res[pop.get(0)][0];
					pop.add(index);
					stack.push(pop);
				}else {
					//不相等,赋值
					List<Integer> newList = new ArrayList<Integer>();
					newList.add(index);
					stack.push(newList);
					res[index][0] = peek.get(peek.size()-1);
				}
				
			}else {
				//当前栈为空,那么它左边小于它的最近的值没有
				res[index][0] = -1;
				List<Integer> newList = new ArrayList<Integer>();
				newList.add(index);
				stack.push(newList);
			}
		}
		while(!stack.isEmpty()) {
			List<Integer> popList = stack.pop();
			for(Integer pop:popList) {
				//右边没有比它小的
				res[pop][1] = -1;
			}
		}
		
		return res;
	}
	public static int[][] rightWay(int[] arr) {
		int[][] res = new int[arr.length][2];
		for (int i = 0; i < arr.length; i++) {
			int leftLessIndex = -1;
			int rightLessIndex = -1;
			int cur = i - 1;
			while (cur >= 0) {
				if (arr[cur] < arr[i]) {
					leftLessIndex = cur;
					break;
				}
				cur--;
			}
			cur = i + 1;
			while (cur < arr.length) {
				if (arr[cur] < arr[i]) {
					rightLessIndex = cur;
					break;
				}
				cur++;
			}
			res[i][0] = leftLessIndex;
			res[i][1] = rightLessIndex;
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = {0, 0, 5, 12, 0, 5, -9};
		int[][] nearLess = getNearLess(arr);
		int[][] rightWay = rightWay(arr);
		System.out.println();
	}

}
