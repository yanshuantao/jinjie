package class04;

public class Code01_KMP {
    // O(N)
	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] str = s.toCharArray();
		char[] match = m.toCharArray();
		int x = 0; // str中当前比对到的位置
		int y = 0; // match中当前比对到的位置
		// M  M <= N   O(M)
		int[] next = getNextArray(match); // next[i]  match中i之前的字符串match[0..i-1]
		// O(N)
		while (x < str.length && y < match.length) {
			if (str[x] == match[y]) {
				x++;
				y++;
			} else if (next[y] == -1) { // y == 0
				x++;
			} else {
				y = next[y];
			}
		}
		return y == match.length ? x - y : -1;
	}
	public static int match(String str,String match) {
		int res = -1;
		if(str.length()<match.length()) {
			return res;
		}
		int len = str.length();
		char[] charArray = str.toCharArray();
		char[] matchCharArray = match.toCharArray();
		
		int[] nextsArray = getNextArray(match.toCharArray());
		int nextIndex = -1;
		for(int i = 0;i<len;i++) {
			nextIndex++;
			while(charArray[i] != matchCharArray[nextIndex]) {
				nextIndex = nextsArray[nextIndex];
				if(nextIndex == -1) {
					break;
				}
			}
			if(nextIndex == match.length()-1) {
				//返回结果
				return i-match.length()+1;
			}
		}
		
		return res;
	}

	// M   O(M)
	public static int[] getNextArray(char[] match) {
		if (match.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[match.length];
		next[0] = -1;
		next[1] = 0;
		int i = 2;
		// cn代表，cn位置的字符，是当前和i-1位置比较的字符
		int cn = 0;
		while (i < next.length) {
			if (match[i - 1] == match[cn]) { // 跳出来的时候
				next[i++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[i++] = 0;
			}
		}
		return next;
	}
	public static int[] getNextArray2(char[] str) {
		int len = str.length;
		if(len == 1) {
			return new int[]{-1};
		}
		int[] arr = new int[len];
		arr[0] = -1;
		arr[1] = 0;
		
		//之后的
		for(int i = 2;i<len;i++) {
			//arr[i]=???
			//如果str[i-1] == str[arr[i-1]]
			int index = arr[i-1];
			while(str[i-1] != str[index] && index != 0) {
				index = arr[index];
			}
			if(index == 0) {
				arr[i] = 0;
			}else {
				arr[i] = arr[index] + 1;
			}
		}
		return arr;
	}


	// for test
	public static String getRandomString(int possibilities, int size) {
		char[] ans = new char[(int) (Math.random() * size) + 1];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
		}
		return String.valueOf(ans);
	}

	public static void main(String[] args) {
		int possibilities = 5;
		int strSize = 20;
		int matchSize = 5;
		int testTimes = 5000000;
		System.out.println("test begin");
		for (int i = 0; i < testTimes; i++) {
			String str = getRandomString(possibilities, strSize);
			String match = getRandomString(possibilities, matchSize);
			int[] nextArray = getNextArray(match.toCharArray());
			int[] nextArray2 = getNextArray2(match.toCharArray());
//			String str = "bccdaabacedbc";
//			String match = "c";
			System.out.println(str);
			System.out.println(match);
			if (match(str, match) != str.indexOf(match)) {
				System.out.println("Oops!");
			}
		}
		System.out.println("test finish");
	}

}
