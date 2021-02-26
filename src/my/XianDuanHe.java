package my;

import java.util.Comparator;
import java.util.PriorityQueue;

import javafx.scene.layout.Priority;

/**
 * 线段和问题
 * @author yanshuantao
 * @date 2021年2月25日
 */
public class XianDuanHe {

	static class Line implements Comparator<Line>{
		int left;
		int right;
		
		public Line(int left,int right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public int compare(Line o1, Line o2) {
			return o1.left - o2.left;
		}
	}
	
	public int maxCover(int[][] lines) {
		int max = 0;
		PriorityQueue<Line> list = new PriorityQueue<Line>();
		PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
		
		
		for(int[] line:lines) {
			Line l = new Line(line[0], line[1]);
			list.add(l);
		}
		while(!list.isEmpty()) {
			Line curLine = list.poll();
			while(!heap.isEmpty() && heap.peek() < curLine.left) {
				heap.poll();
			}
			heap.add(curLine.right);
			max = Math.max(max, heap.size());
		}
		return max;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
