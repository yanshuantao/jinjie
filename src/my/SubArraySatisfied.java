package my;

import java.util.List;

public class SubArraySatisfied {
	
	public static int f_main(int[] datas,int num) {
		
	}
	public int f(int[] datas,int num,int index,List<Integer> sub) {
		if(index == datas.length) {
			if(sub == null || sub.size() <= 1) {
				return 0;
			}
			//排序
			return sub.get(sub.size())-sub.get(0) <= num?1:0;
		}
		//当前位置加入sub
		sub.add(datas[index]);
		int yes = f(datas, num, index + 1, sub);
		sub.remove(datas[index]);
		int no = f(datas, num, index + 1, sub);
		return Math.max(yes, no);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
