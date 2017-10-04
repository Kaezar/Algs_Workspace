package kdtree;


import java.util.TreeMap;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class PointST<Value> {
	private TreeMap<Point2D, Value> symbol;
	
	public PointST() {
		this.symbol = new TreeMap<Point2D, Value>();
	}
	
	public boolean isEmpty() {
		return (symbol.size() == 0);
	}
	
	public int size() {
		return symbol.size();
	}
	
	public void put(Point2D p, Value val) {
		if (p == null || val == null) { throw new java.lang.NullPointerException(); }
		symbol.put(p, val);
	}
	
	public Value get(Point2D p) {
		if (p == null) { throw new java.lang.NullPointerException(); }
		return symbol.get(p);
	}
	
	public boolean contains(Point2D p) {
		if (p == null) { throw new java.lang.NullPointerException(); }
		return symbol.containsKey(p);
	}
	
	public Iterable<Point2D> points() {
		return symbol.keySet();
	}
	
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) { throw new java.lang.NullPointerException(); }
		Iterable<Point2D> points = points();
		Stack<Point2D> range = new Stack<Point2D>();
		for (Point2D point : points) {
			if (rect.contains(point)) {
				range.push(point);
			}
		}
		return range;
	}
	
	public Point2D nearest(Point2D p) {
		if (p == null) { throw new java.lang.NullPointerException(); }
		Iterable<Point2D> points = points();
		double minDist = Double.POSITIVE_INFINITY;
		Point2D minPoint = null;
		for (Point2D point : points) {
			if (point.distanceSquaredTo(p) < minDist) {
				minPoint = point;
				minDist = point.distanceSquaredTo(p);
			}
		}
		return minPoint;
	}
	
	public static void main(String[] args) {
		String filename = args[0];
        In in = new In(filename);
        
        PointST<Integer> brute = new PointST<Integer>();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            brute.put(p, i);
        }
        int queries = 0;
		Stopwatch stop = new Stopwatch();
		while (stop.elapsedTime() < 1.0) {
			brute.nearest(new Point2D(Math.random(), Math.random()));
			queries++;
		}
		StdOut.println(queries + " queries in 1 second.");
	}
}