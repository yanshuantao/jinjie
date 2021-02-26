package my;


/**房子染色问题
 * 给出一排房子，提供一个给第i--j个房子染成某种颜色方法
 * 提供一个查询第i---j个房子一共有多少种颜色
 * 思路：提供一个sum[]数组
 * 	                 一个updateTotalArr[]数组
 * 		     一个isUpdate[]数组
 * 		    一个updateColorArr[]数组
 * 1.染色方法逻辑:判断给出的L-R是否已经包含了当前节点,如果已经包含,更新颜色数量和updateColorArr;
 * 不包含：将当前节点懒任务下发,左右节点下推
 * 查询
 * @author yanshuantao
 * @date 2021年2月25日
 */
public class House {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
