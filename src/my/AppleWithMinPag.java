package my;

public class AppleWithMinPag {
	
	public static void test() {
		for(int i = 8;i<=100;i++) {
			int big = i/8;
			int small = 0;
			int rest = i%8;
			while(rest != 0 && rest%6 != 0 && big != 0) {
				big--;
				rest = rest + 8;
			}
			small = rest%6 == 0?rest/6:0;
			if(big != 0 || small != 0) {
				System.out.println(i + "个苹果使用大袋子:" + big +"个;小袋子" + small +"个");
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();

	}

}
