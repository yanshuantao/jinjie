package matrix;


/**
 * 矩阵特殊轨迹
 * @author Administrator
 *
 */
public class SpecialPath {

	
	/**
	 * zigzag打印
	 * @param arr
	 */
	public static void zigzag(int[][] arr) {
		//a的运动轨迹是从开始点往下走，直到不能再走往右走
		int Ar = 0;
		int Ac = 0;
		//b的运动轨迹是从开始点往右走，直到不能再走往下走
		int Br = 0;
		int Bc = 0;
		
		int Endr = arr.length - 1;
		int Endc = arr[0].length - 1;
		while(Ar != Br && Ac != Bc) {
			if(Ac < Endc) {
				Ac++;
			}else {
				Ar++;
			}
			if(Br < Endr) {
				Br++;
			}else {
				Bc++;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
