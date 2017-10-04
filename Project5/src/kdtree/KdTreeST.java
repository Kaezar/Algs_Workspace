package kdtree;


import java.util.TreeMap;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class KdTreeST<Value> {
	private Node root;
	private int size;
	
	
	private class Node {
		private Point2D p;
		private Value value;
		private RectHV rect;
		private Node lb;
		private Node rt;
		private boolean xory;
		// true if x, false if y
		//private boolean xory;
		
		
		public Node(Point2D p, Value value, RectHV rect, boolean xory) {
			this.p = p;
			this.value = value;
			this.rect = rect;
			this.xory = xory;
		}
	}
	
	public KdTreeST() {
		this.root = null;
		this.size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int size() {
		return size;
	}
	
	public void put(Point2D p, Value val) {
		if (p == null || val == null) { throw new java.lang.NullPointerException(); }
		RectHV initial = new RectHV(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		root = put(root, p, val, true, initial);
	}
	
	// compares the two points x or y values
	private int compareToXY(Point2D x, Point2D p, boolean xory) {
		// compare x
		if (xory) {
			if (x.x() < p.x()) { return - 1; }
			if (x.x() == p.x()) { return 0; }
			if (x.x() > p.x()) { return 1; }
		}
		// compare y
		else {
			if (x.y() < p.y()) { return - 1; }
			if (x.y() == p.y()) { return 0; }
			if (x.y() > p.y()) { return 1; }
		}
		return 0;
	}
	
	private Node put(Node x, Point2D p, Value val, boolean xory, RectHV rect) {
		if (x == null) {
			size++;
			//StdOut.println("creating new node: " + p + val);
			return new Node(p, val, rect, xory); 
			}
		int cmp = compareToXY(x.p, p, xory);
		 // if x.p < p
		 if      (cmp < 0) { 
			 if (xory) {
				 RectHV newRect = new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
				 x.rt = put(x.rt,  p, val, !xory, newRect);
			 }
			 else {
				 RectHV newRect = new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax());
				 x.rt = put(x.rt,  p, val, !xory, newRect);
			 }
		 }
		 // if x.p > p
	     else if (cmp > 0) {
	    	 if (xory) {
	    		 RectHV newRect = new RectHV(rect.xmin(), rect.ymin(), x.p.x(), rect.ymax());
	    		 x.lb = put(x.lb, p, val, !xory, newRect);
	    	 }
	    	 else {
	    		 RectHV newRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.p.y());
	    		 x.lb = put(x.lb, p, val, !xory, newRect);
	    	 }
	     }
		 // if just one coordinate matches
	     else if (compareToXY(x.p, p, !xory) != 0) { 
	    	 if (xory) {
				 RectHV newRect = new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
				 x.rt = put(x.rt,  p, val, !xory, newRect);
			 }
			 else {
				 RectHV newRect = new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax());
				 x.rt = put(x.rt,  p, val, !xory, newRect);
			 }
	     }
		 // if both coordinates match
	     else x.value = val;
		 return x;
		 
	}
	
	public Value get(Point2D p) {
		if (p == null) { throw new java.lang.NullPointerException(); }
		return get(root, p, true);
	}
	
	private Value get(Node x, Point2D p, boolean xory) {
		if (x == null) {
			//StdOut.println("point not found");
			return null;
		}
		int cmp = compareToXY(x.p, p, xory);
		// if x.p < p
		if (cmp < 0) return get(x.rt, p, !xory);
		// if x.p > p
		if (cmp > 0) return get(x.lb, p, !xory);
		// if one coordinate matches
		else if (compareToXY(x.p, p, !xory) != 0) return get(x.rt, p, !xory);
		// if both coordinates match (search hit)
		else return x.value;
	}
	
	public boolean contains(Point2D p) {
		if (p == null) { throw new java.lang.NullPointerException(); }
		return (get(p) != null);
	}
	
	
	// uses BFS algorithm to create a queue with all points in level-order traversal
	public Iterable<Point2D> points() {
		Queue<Node> queue = new Queue<Node>();
		Queue<Point2D> points = new Queue<Point2D>();
		queue.enqueue(root);
		while (!queue.isEmpty()) {
			Node tempNode = queue.dequeue();
			points.enqueue(tempNode.p);
			if (tempNode.lb != null) queue.enqueue(tempNode.lb);
			if (tempNode.rt != null) queue.enqueue(tempNode.rt);
		}
		return points;
	}
	
	
	
	public Iterable<Point2D> range(RectHV rect) {
		Stack<Point2D> stack = new Stack<Point2D>();
		range(root, stack, rect);
		return stack;
	}
	
	private void range(Node x, Stack<Point2D> stack, RectHV rect) {
		if (x != null && x.rect.intersects(rect)) {
			if (rect.contains(x.p))  stack.push(x.p);
			range(x.lb, stack, rect);
			range(x.rt, stack, rect);
		}
	}
	
	public Point2D nearest(Point2D p) {
		if (p == null) { throw new java.lang.NullPointerException(); }
		Queue<Node> queue = new Queue<Node>();
		Point2D nearest = root.p;
		queue.enqueue(root);
		while (!queue.isEmpty()) {
			Node tempNode = queue.dequeue();
			if (tempNode.p.distanceSquaredTo(p) <= nearest.distanceSquaredTo(p)) {
				nearest = tempNode.p;
			}
			int cmp = compareToXY(tempNode.p, p, tempNode.xory);
			if (cmp <= 0) {
				if (tempNode.rt != null && tempNode.rt.rect.distanceSquaredTo(p) <= nearest.distanceSquaredTo(p) ){
					queue.enqueue(tempNode.rt);
				}
				if(tempNode.lb != null &&tempNode.lb.rect.distanceSquaredTo(p) <= nearest.distanceSquaredTo(p)) {
					queue.enqueue(tempNode.lb);
				}
			}
			if (cmp > 0) {
				if(tempNode.lb != null && tempNode.lb.rect.distanceSquaredTo(p) <= nearest.distanceSquaredTo(p)) {
					queue.enqueue(tempNode.lb);
				}
				if (tempNode.rt != null && tempNode.rt.rect.distanceSquaredTo(p) <= nearest.distanceSquaredTo(p) ){
					queue.enqueue(tempNode.rt);
				}
			}
		}
		return nearest;
	}
	
	
	public static void main(String[] args) {
		String filename = args[0];
        In in = new In(filename);
		
		KdTreeST<Integer> kdtree = new KdTreeST<Integer>();
		//Queue<Point2D> points = new Queue<Point2D>();
		for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            //points.enqueue(p);
            kdtree.put(p, i);
        }
		//Iterable<Point2D> points = kdtree.points();
		//for (Point2D point: points) {
		//	StdOut.println(point);
		//	StdOut.println("Value: " + kdtree.get(point));
		//}
		//StdOut.println("Size: " + kdtree.size());
		
		//Point2D test1 = new Point2D(0.5, 1.0);
		//Point2D test2 = new Point2D(1.0, 0.5);
		//StdOut.println(kdtree.compareToXY(test1, test2, true));
		//StdOut.println(kdtree.compareToXY(test1, test2, false));
        
		int queries = 0;
		Stopwatch stop = new Stopwatch();
		while (stop.elapsedTime() < 1.0) {
			kdtree.nearest(new Point2D(Math.random(), Math.random()));
			queries++;
		}
		StdOut.println(queries + " queries in 1 second.");
        
	}
}