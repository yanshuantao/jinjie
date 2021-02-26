package my;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;


/**
 * 最大矩形和问题
 * @author yanshuantao
 * @date 2021年2月26日
 */
public class RectangleMax {
	
	public int max(Rectangle[] rectangles) {
		int max = 0;
		
		//先按底部排序
		Arrays.sort(rectangles);
		TreeSet<Rectangle> leftList = new TreeSet<Rectangle>();
		//先从下往上挤
		for(int i = 0;i < rectangles.length;) {
			//先找到相同底部的列表start--------
			do {
				leftList.add(rectangles[i++]);
			}while(i < rectangles.length && rectangles[i].bottom == rectangles[i - 1].bottom);
			//先找到相同底部的列表end--------
			//淘汰掉所有顶部小于等于当前底部的列表
			removeLowerOnCurBottom(leftList, rectangles[i].bottom);
			TreeSet<Rectangle> rightList = new TreeSet<RectangleMax.Rectangle>();
			//从左往右挤，将右边界小于当前左边界的移除
			for(Rectangle rectangle:leftList) {
				removeLowerOnCurLeft(rightList, rectangle.left); 
				rightList.add(rectangle);
				max = Math.max(max, rightList.size());
			}
		}
		
		return max;
	}
	void removeLowerOnCurBottom(TreeSet<Rectangle> leftList,int curBottom) {
		Iterator<Rectangle> iterator = leftList.iterator();
		while(iterator.hasNext()) {
			Rectangle next = iterator.next();
			if(next.top <= curBottom) {
				iterator.remove();
			}
		}
	}
	void removeLowerOnCurLeft(TreeSet<Rectangle> leftList,int curLeft) {
		
	}
	
	static class Rectangle{
		int top;
		int bottom;
		int left;
		int right;
		public Rectangle(int top, int bottom, int left, int right) {
			this.top = top;
			this.bottom = bottom;
			this.left = left;
			this.right = right;
		}
	}
	static class BottomComparator implements Comparator<Rectangle>{
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.bottom - o2.bottom;
		}
	}
	static class LeftComparator implements Comparator<Rectangle>{
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.left - o2.left;
		}
	}
	static class RightComparator implements Comparator<Rectangle>{
		@Override
		public int compare(Rectangle o1, Rectangle o2) {
			return o1.right - o2.right;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
