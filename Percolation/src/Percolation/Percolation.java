package Percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private WeightedQuickUnionUF quickUnion;
	private boolean[][] grid;
	private int N;
	private int nSquared;
	private int openSites = 0;
	
	public Percolation(int N){
		if(N <= 0){
			throw new java.lang.IllegalArgumentException();
		}
		 this.N = N;
		 this.nSquared = N*N;
		 this.quickUnion = new WeightedQuickUnionUF(nSquared+2);
		 this.grid = new boolean[N][N];
		 for(int i =1; i < N+1; i++){
			 quickUnion.union(0,i);
		 }
		 for(int j = nSquared; j > nSquared-N; j-- ){
			 quickUnion.union(nSquared+1, j);
		 }
	}
	private int xyto1D(int row, int col){
		int unionPointer = N*row + col+1;
		return unionPointer;
	}
	public void open(int row, int col){
		if(invalid(row, col)){
			throw new java.lang.IndexOutOfBoundsException();
		}
		// if node is not open, opens it and increments openSites
		if(grid[row][col] != true){
			grid[row][col] = true;
			openSites++;
		// check to see if there is a node to the left, and if it is open, and if so, connects to it
		if(col > 0){
			if(grid[row][col-1]){
				quickUnion.union(xyto1D(row,col), xyto1D(row,col-1));
			}
		}
		// check to see if there is a node to the right, and if it is open, and if so, connects to it
		if(col < N-2){
			if(grid[row][col+1]){
				quickUnion.union(xyto1D(row,col), xyto1D(row,col+1));
			}
		}
		// check to see if there is a node above, and if it is open, and if so, connects to it
		if(row > 0){
			if(grid[row-1][col]){
				quickUnion.union(xyto1D(row,col), xyto1D(row-1,col));
			}
		}
		// check to see if there is a node below, and if it is open, and if so, connects to it
		if(row < N-2){
			if(grid[row+1][col]){
				quickUnion.union(xyto1D(row,col), xyto1D(row+1,col));
			}
		}
		}
		
	}
	public boolean isOpen(int row, int col){
		if(invalid(row, col)){
			throw new java.lang.IndexOutOfBoundsException();
		}
		if(grid[row][col] == true)
			return true;
		else
			return false;
	}
	private boolean invalid(int row, int col){
		if(row < 0 || row > N-1 || col < 0 || col > N-1)
			return true;
		else
			return false;
	}
	public int numberOfOpenSites(){
		return openSites;
	
	}
	public boolean isFull(int row, int col){
		if(isOpen(row, col)){
			return quickUnion.connected(xyto1D(row,col),0);
		}
		else
			return false;
	}
	public boolean percolates(){
		return quickUnion.connected(0, nSquared+1);
	}
}