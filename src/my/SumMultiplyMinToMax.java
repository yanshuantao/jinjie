package my;


/**
 * 所有子数组之和乘以最小数最大的结果
 * @author Administrator
 *
 */
public class SumMultiplyMinToMax {
	
	public int max(int[] arr) {
		int res = 0;
		for(int i = 0;i<arr.length;i++) {
			//以当前元素为子数组最小值，左右同时扩，扩到不能扩，求出当前的值
			int left = i;
			int right = i;
			int subSum = arr[i];
			while(right < arr.length) {
				right++;
				if(right == arr.length || arr[right] < arr[i]) {
					break;
				}
				subSum += arr[right];
			}
			//1,2
			while(left > 0) {
				left--;
				if(left == 0 || arr[left] < arr[i]) {
					break;
				}
				subSum += arr[left];
			}
			subSum *=arr[i];
			if(subSum > res) {
				res = subSum;
			}
		}
		return res;
	}
	public int max2(int[] arr) {
		int len = arr.length;
		int[] sumArr = new int[len];
		sumArr[0] = arr[0];
		for(int i = 1;i<len;i++) {
			sumArr[i] = sumArr[i-1] + arr[i];
		}
		for(int i = 0;i<len;i++) {
			int left = i;
			int right = i;
			while(left>=0 && arr[left] >= arr[i]) {
				left--;
			}
			
		}
		
		
		
		int res = 0;
		for(int i = 0;i<arr.length;i++) {
			//以当前元素为子数组最小值，左右同时扩，扩到不能扩，求出当前的值
			int left = i;
			int right = i;
			int subSum = arr[i];
			while(right < arr.length) {
				right++;
				if(right == arr.length || arr[right] < arr[i]) {
					break;
				}
				subSum += arr[right];
			}
			//1,2
			while(left > 0) {
				left--;
				if(left == 0 || arr[left] < arr[i]) {
					break;
				}
				subSum += arr[left];
			}
			subSum *=arr[i];
			if(subSum > res) {
				res = subSum;
			}
		}
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
