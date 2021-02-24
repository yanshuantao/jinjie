package class01;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class Code03_MonotonousStack {

	public static int[][] getNearLessNoRepeat(int[] arr) {
		int[][] res = new int[arr.length][2];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
				int popIndex = stack.pop();
				int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
				res[popIndex][0] = leftLessIndex;
				res[popIndex][1] = i;
			}
			stack.push(i);
		}
		while (!stack.isEmpty()) {
			int popIndex = stack.pop();
			int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
			res[popIndex][0] = leftLessIndex;
			res[popIndex][1] = -1;
		}
		return res;
	}
	public static int[][] getNearLessNoRepeat2(int[] arr){
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

	// arr [3, 2, 1, 4, 5]
	//      0  1  2  3  4
	
	//  [
	//    0 :  [-1,  1  ]
	//    1 :  [-1,  2  ]
	
	//  ] 
	//
	public static int[][] getNearLess(int[] arr) {
		int[][] res = new int[arr.length][2];
		
		
		// List<Integer> -> 放的是位置，同样值的东西，位置压在一起
		// 代表值    底  ->  顶   小  -> 大
		Stack<List<Integer>> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) { // i -> arr[i] 进栈
			// 底 -> 顶， 小 -> 大
			while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
				List<Integer> popIs = stack.pop();
				// 取位于下面位置的列表中，最晚加入的那个
				int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
				for (Integer popi : popIs) {
					res[popi][0] = leftLessIndex;
					res[popi][1] = i;
				}
			}
			// 相等的、比你小的
			if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
				stack.peek().add(Integer.valueOf(i));
			} else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(i);
				stack.push(list);
			}
		}
		while (!stack.isEmpty()) {
			List<Integer> popIs = stack.pop();
			// 取位于下面位置的列表中，最晚加入的那个
			int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
			for (Integer popi : popIs) {
				res[popi][0] = leftLessIndex;
				res[popi][1] = -1;
			}
		}
		return res;
	}
	public static int[][] getNearLess2(int[] arr) {
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

	// for test
	public static int[] getRandomArrayNoRepeat(int size) {
		int[] arr = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < arr.length; i++) {
			int swapIndex = (int) (Math.random() * arr.length);
			int tmp = arr[swapIndex];
			arr[swapIndex] = arr[i];
			arr[i] = tmp;
		}
		return arr;
	}

	// for test
	public static int[] getRandomArray(int size, int max) {
		int[] arr = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
		}
		return arr;
	}

	// for test
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

	// for test
	public static boolean isEqual(int[][] res1, int[][] res2) {
		if (res1.length != res2.length) {
			return false;
		}
		for (int i = 0; i < res1.length; i++) {
			if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
				return false;
			}
		}

		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int size = 10;
		int max = 20;
		int testTimes = 2000;
		for (int i = 0; i < testTimes; i++) {
			int[] arr1 = getRandomArrayNoRepeat(size);
			int[] arr2 = getRandomArray(size, max);
//			int[][] nearLessNoRepeat = getNearLessNoRepeat2(arr1);
//			int[][] rightWay = rightWay(arr1);
//			System.out.println(isEqual(nearLessNoRepeat, rightWay));
//			if (!isEqual(nearLessNoRepeat, rightWay)) {
//				System.out.println("Oops!");
//				printArray(arr1);
//				break;
//			}
			int[][] nearLess2 = getNearLess2(arr2);
			int[][] rightWay2 = rightWay(arr2);
			if (!isEqual(nearLess2,rightWay2 )) {
				System.out.println("Oops!");
				printArray(arr2);
				break;
			}
		}
	}
}
