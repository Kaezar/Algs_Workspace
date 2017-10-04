package seamcarver;

import java.awt.Color;
import java.util.PriorityQueue;
import java.util.stream.DoubleStream;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class SeamCarver {
	private Picture pic;
	
	private class SearchNode implements Comparable<SearchNode> {
        private int x;
        private int y;
        private SearchNode prev;
        private double dist;

        public SearchNode(int x, int y, SearchNode prev, double dist) {
            this.x = x;
            this.y = y;
            this.prev = prev;
            this.dist = dist;
        }

        public int compareTo(SearchNode that) {
            if (this.dist < that.dist) {
                return 1;
            } else if (that.dist < this.dist) {
                return -1;
            } else {
                return 0;
            }
        }
    }
	
	public SeamCarver(Picture picture) {
		if (picture == null) { throw new java.lang.NullPointerException(); }
		pic = new Picture(picture);
	}
	
	public Picture picture() {
		return pic;
	}
	
	public int width() {
		return pic.width();
	}
	
	public int height() {
		return pic.height();
	}
	
	 public double energy(int x, int y) {
	        if (!checkBounds(x, y)) { throw new java.lang.IndexOutOfBoundsException(); }

	        if (x == 0 || x == width() - 1 || y == 0 || y == height() - 1) {
	            return 255 * 255 * 3.0;
	        }

	        Color leftColor     = pic.get(x - 1, y);
	        Color rightColor    = pic.get(x + 1, y);
	        Color topColor      = pic.get(x, y - 1);
	        Color bottomColor   = pic.get(x, y + 1);

	        int leftRed     = leftColor.getRed();
	        int leftGreen   = leftColor.getGreen();
	        int leftBlue    = leftColor.getBlue();

	        int rightRed    = rightColor.getRed();
	        int rightGreen  = rightColor.getGreen();
	        int rightBlue   = rightColor.getBlue();

	        int topRed    = topColor.getRed();
	        int topGreen  = topColor.getGreen();
	        int topBlue   = topColor.getBlue();

	        int bottomRed    = bottomColor.getRed();
	        int bottomGreen  = bottomColor.getGreen();
	        int bottomBlue   = bottomColor.getBlue();

	        return (leftRed - rightRed) * (leftRed - rightRed)
	            + (leftGreen - rightGreen) * (leftGreen - rightGreen)
	            + (leftBlue - rightBlue) * (leftBlue - rightBlue)
	            + (topRed - bottomRed) * (topRed - bottomRed)
	            + (topGreen - bottomGreen) * (topGreen - bottomGreen)
	            + (topBlue - bottomBlue) * (topBlue - bottomBlue);
	    }
	 
	 private boolean checkBounds(int x, int y) {
	        return 0 <= x && x < width() && 0 <= y && y < height();
	    }
	 
	 public int[] findHorizontalSeam() {
	        PriorityQueue<SearchNode> pq = new PriorityQueue<SearchNode>();
	        double[][] dist = new double[width()][height()];
	        for (int i = 0; i < width(); ++i) {
	            for (int j = 0; j < height(); ++j) {
	                dist[i][j] = Double.POSITIVE_INFINITY;
	            }
	        }
	        for (int j = 0; j < height(); ++j) {
	            dist[0][j] = energy(0, j);
	            pq.add(new SearchNode(0, j, null, dist[0][j]));
	        }
	        while (pq.size() != 0) {
	            SearchNode current = pq.peek();
	            if (current.x == width() - 1) {
	                break;
	            }
	            pq.poll();
	            for (int s = -1; s <= 1; ++s) {
	                int cx = current.x;
	                int cy = current.y;
	                int px = cx + 1;
	                int py = cy + s;
	                if (!checkBounds(px, py)) {
	                    continue;
	                }
	                if (dist[cx][cy] + energy(px, py) < dist[px][py]) {
	                    dist[px][py] = dist[cx][cy] + energy(px, py);
	                    SearchNode post = new SearchNode(px, py, current, dist[px][py]);
	                    pq.add(post);
	                }
	            }
	        }
	        int[] seams = new int[width()];
	        SearchNode current = pq.poll();
	        while (current != null) {
	            seams[current.x] = current.y;
	            current = current.prev;
	        }
	        return seams;
	    }
	 
	 public int[] findVerticalSeam() {
	        PriorityQueue<SearchNode> pq = new PriorityQueue<SearchNode>();
	        double[][] dist = new double[width()][height()];
	        for (int i = 0; i < width(); ++i) {
	            for (int j = 0; j < height(); ++j) {
	                dist[i][j] = Double.POSITIVE_INFINITY;
	            }
	        }
	        for (int i = 0; i < width(); ++i) {
	            dist[i][0] = energy(i, 0);
	            pq.add(new SearchNode(i, 0, null, dist[i][0]));
	        }
	        while (pq.size() != 0) {
	            SearchNode current = pq.peek();
	            if (current.y == height() - 1) {
	                break;
	            }
	            pq.poll();
	            for (int s = -1; s <= 1; ++s) {
	                int cx = current.x;
	                int cy = current.y;
	                int px = cx + s;
	                int py = cy + 1;
	                if (!checkBounds(px, py)) {
	                    continue;
	                }
	                if (dist[cx][cy] + energy(px, py) < dist[px][py]) {
	                    dist[px][py] = dist[cx][cy] + energy(px, py);
	                    SearchNode post = new SearchNode(px, py, current, dist[px][py]);
	                    pq.add(post);
	                }
	            }
	        }
	        int[] seams = new int[height()];
	        SearchNode current = pq.poll();
	        while (current != null) {
	            seams[current.y] = current.x;
	            current = current.prev;
	        }
	        return seams;
	    }
	 
	 public void removeHorizontalSeam(int[] a) {
	        int n = a.length;
	        if (n <= 1 || n != width()) {
	            throw new java.lang.IllegalArgumentException();
	        }
	        for (int i = 0; i < n; ++i) {
	            if (a[i] < 0 || a[i] >= height()) {
	                throw new java.lang.IllegalArgumentException();
	            }
	        }
	        for (int i = 1; i < n; ++i) {
	            if (Math.abs(a[i] - a[i - 1]) > 1) {
	                throw new java.lang.IllegalArgumentException();
	            }
	        }
	        Picture tmp = new Picture(width(), height() - 1);
	        for (int x = 0; x < width(); ++x) {
	            for (int y = 0; y < a[x]; ++y) {
	                tmp.set(x, y, pic.get(x, y));
	            }
	            for (int y = a[x] + 1; y < height(); ++y) {
	                tmp.set(x, y - 1, pic.get(x, y));
	            }
	        }
	        pic = tmp;
	    }

	    public void removeVerticalSeam(int[] a) {
	        int m = a.length;
	        if (m <= 1 || m != height()) {
	            throw new java.lang.IllegalArgumentException();
	        }
	        for (int i = 0; i < m; ++i) {
	            if (a[i] < 0 || a[i] >= width()) {
	                throw new java.lang.IllegalArgumentException();
	            }
	        }
	        for (int i = 1; i < m; ++i) {
	            if (Math.abs(a[i] - a[i - 1]) > 1) {
	                throw new java.lang.IllegalArgumentException();
	            }
	        }
	        Picture tmp = new Picture(width() - 1, height());
	        for (int y = 0; y < height(); ++y) {
	            for (int x = 0; x < a[y]; ++x) {
	                tmp.set(x, y, pic.get(x, y));
	            }
	            for (int x = a[y] + 1; x < width(); ++x) {
	                tmp.set(x - 1, y, pic.get(x, y));
	            }
	        }
	        pic = tmp;
	    }
	    public static void main(String[] args) {
	    	Picture rand = SCUtility.randomPicture(2000, 250);
	    	int N = 50;
	    	double[] hTimes = new double[N];
	    	for (int i = 0; i < N; i++) {
	    		Picture copy = new Picture(rand);
	    		SeamCarver seam = new SeamCarver(copy);
	    		Stopwatch stop = new Stopwatch();
	    		seam.removeHorizontalSeam(seam.findHorizontalSeam());
	    		hTimes[i] = stop.elapsedTime();
	    	}
	    	double hSum = DoubleStream.of(hTimes).sum();
	    	double hAverage = hSum/N;
	    	double[] vTimes = new double[N];
	    	for (int i = 0; i < N; i++) {
	    		Picture copy = new Picture(rand);
	    		SeamCarver seam = new SeamCarver(copy);
	    		Stopwatch stop = new Stopwatch();
	    		seam.removeVerticalSeam(seam.findVerticalSeam());
	    		vTimes[i] = stop.elapsedTime();
	    	}
	    	double vSum = DoubleStream.of(vTimes).sum();
	    	double vAverage = vSum/N;
	    	StdOut.println("The average runtime of horizontal seam removal is: " + hAverage);
	    	StdOut.println("The average runtime of vertical seam removal is: " + vAverage);
	    	
	    }
}









