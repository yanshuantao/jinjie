package my;

/**
 * 字符串字串是否包含某个字符串(子串概念：必须是连续的)
 * @author Administrator
 *
 */
public class SubStrMatch {
	
	public static int match(String str,String match) {
		int res = -1;
		int len = str.length();
		char[] charArray = str.toCharArray();
		char[] matchCharArray = match.toCharArray();
		
		int[] nextsArray = generateNextsArray(match);
		int nextIndex = -1;
		for(int i = 0;i<len;i++) {
			nextIndex++;
			while(charArray[i] != matchCharArray[nextIndex]) {
				nextIndex = nextsArray[nextIndex];
				if(nextIndex == -1) {
					break;
				}
			}
			if(nextIndex == match.length() - 1) {
				//返回结果
				return i-match.length()-1;
			}
		}
		
		return res;
	}
	public static int[] generateNextsArray(char[] str) {
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
		}
		return arr;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
