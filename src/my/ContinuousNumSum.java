package my;


/**
 * 连续整数和
 * @author yanshuantao
 * @date 2021年2月26日
 */
public class ContinuousNumSum {
	
	
	/**
	 * 暴力尝试法
	 * @param num
	 * @return
	 * boolean
	 */
	public static boolean isContinuousNumSum(int num) {
		int original = num;
		boolean res = false;
		if(num <= 2) {
			return res;
		}
		//第一个数字从1开始尝试,如果尝试失败开始递增,当这个数字>num/2时结束,返回false
		int first = 1;
		while(first < num/2) {
			int cur = first;
			while(cur < num) {
				num = num - cur;
				cur++;
				if(cur == num) {
					System.out.println("num==========" + original+"start =="+first);
					return true;
				}
			}
			first++;
		}
		return res;
	}
	/**
	 * 打表规律法
	 * @param num
	 * @return
	 * boolean
	 */
	public static boolean isContinuousNumSum2(int num) {
		int original = num;
		boolean res = false;
		if(num <= 5) {
			return res;
		}
		int begin = 6;
		int step = 4;
		for(;begin <= num;) {
			if(num == begin) {
				System.out.println("num==========" + original);
				return true;
			}
			System.out.println("begin-----------"+begin + "step======="+step);
			begin += step;
			step ++;
		}
		return false;
		
	}
	public static void main(String[] args) {
//		System.out.println(1);
		for(int i = 3;i<100;i++) {
			isContinuousNumSum(i);
		}
		System.out.println();
//		for(int i = 3;i<100;i++) {
//			isContinuousNumSum2(55);
//		}
//		for(int i = 3;i<100;i++) {
//			isContinuousNumSum(54);
//		}
	}

}
