package my;

public class Morris {
	
	public static class Node {
		public int value;
		Node left;
		Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	/**
	 * Morris遍历
	 * @param head
	 */
	public void f(Node head) {
		if(head == null) {
			return;
		}
		//当前节点
		Node cur = head;
		//当前节点左树的最右节点
		Node mostRight = null;
		while(cur != null) {
			//循环一进来就更新当前mostRight为当前节点的左节点
			mostRight = cur.left;
			if(mostRight != null) {
				while(mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if(mostRight.right == null) {
					//更新最右节点
					mostRight.right = cur;
					cur = cur.left;
				}else {
					//第二次返回此节点
					mostRight.right = null;
					cur = cur.right;
				}
				
			}else {
				//无左树,指针直接指向右节点
				cur = cur.right;
			}
		}
	}
	/**
	 * 先序打印
	 * @param head
	 */
	public void fXian(Node head) {
		if(head == null) {
			return;
		}
		//当前节点
		Node cur = head;
		//当前节点左树的最右节点
		Node mostRight = null;
		while(cur != null) {
			//循环一进来就更新当前mostRight为当前节点的左节点
			mostRight = cur.left;
			if(mostRight != null) {
				while(mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if(mostRight.right == null) {
					//打印
					System.out.println(cur.value);
					//更新最右节点的right为当前节点，并且切换到当前节点的左节点
					mostRight.right = cur;
					cur = cur.left;
				}else {
					//第二次返回此节点
					mostRight.right = null;
					cur = cur.right;
				}
				
			}else {
				//打印
				System.out.println(cur.value);
				//无左树,指针直接指向右节点
				cur = cur.right;
			}
		}
	}
	/**
	 * 中序遍历
	 * @param head
	 */
	public void fZhong(Node head) {
		if(head == null) {
			return;
		}
		//当前节点
		Node cur = head;
		//当前节点左树的最右节点
		Node mostRight = null;
		while(cur != null) {
			//循环一进来就更新当前mostRight为当前节点的左节点
			mostRight = cur.left;
			if(mostRight != null) {
				while(mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if(mostRight.right == null) {
					//更新最右节点的right为当前节点，并且切换到当前节点的左节点
					mostRight.right = cur;
					cur = cur.left;
				}else {
					//打印
					System.out.println(cur.value);
					//第二次返回此节点
					mostRight.right = null;
					cur = cur.right;
				}
				
			}else {
				//打印
				System.out.println(cur.value);
				//无左树,指针直接指向右节点
				cur = cur.right;
			}
		}
	}
	/**
	 * 使用Morris判断是否是一颗搜索二叉树
	 * @param head
	 * @return
	 */
	public boolean isBST(Node head) {
		//核心思路就是：中序遍历过程中,一旦发现某个左节点比父节点大，直接返回false
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
