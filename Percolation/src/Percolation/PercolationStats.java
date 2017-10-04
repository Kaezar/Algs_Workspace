package Percolation;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


public class PercolationStats {
	int N;
	int T;
	double[] threshold;
	double ph;
	
	public PercolationStats(int N, int T){
		if(N <= 0 || T <= 0){
			throw new java.lang.IllegalArgumentException();
		}
		this.N = N;
		this.T = T;
		this.threshold = new double[T];
		
		for(int i = 0; i < T; i++){
			Percolation perc = new Percolation(N);
			
			while(perc.percolates() == false){
				int x = StdRandom.uniform(N);
				int y = StdRandom.uniform(N);
				perc.open(x, y);
			}
			int open = perc.numberOfOpenSites();
			threshold[i] = (double) open / (N*N); 
		}	
		this.ph = 1.96 * stddev() / Math.sqrt(T); 
	}
	
	public double mean(){
		return StdStats.mean(threshold);
	}
	
	public double stddev(){
		return StdStats.stddev(threshold);
	}
	
	public double confidenceLow(){
		return mean() - ph;
	}
	
	public double confidenceHigh(){
		return mean() + ph;
	}
	
	public static void main(String[] args){
		int n = 20;
		int t = 30;
		if(args.length == 2){
			n = Integer.parseInt(args[0]);
			t = Integer.parseInt(args[1]);
		}
		
		PercolationStats stats = new PercolationStats(n,t);
		StdOut.println("Values after creating PercolationStats(" + n + ", " + t + ")");
		StdOut.println("mean() = " + stats.mean());
		StdOut.println("stddev() = " + stats.stddev());
		StdOut.println("confidenceLow = " + stats.confidenceLow());
		StdOut.println("confidenceHigh = " + stats.confidenceHigh());
		
	}
	
	
}