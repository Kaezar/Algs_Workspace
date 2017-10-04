package puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

public class Board {
	// 2d array of tiles on the board
	private int[][] tiles;
	// cached manhattan sum (initialized to -5 before first call to manhattan())
	private int manhattan = -5;
	private int hamming = -5;
	private int N;
	
	
	public Board(int[][] tiles) {
		this.N = tiles.length;
		this.tiles = new int[N][N];
		for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
        		this.tiles[i][j] = tiles[i][j];
        	}
        }
		
	}
	
	public int tileAt(int i, int j) {
		return tiles[i][j];
	}
	
	public int size() {
		return N;
	}
	
	// computes value of the goal board at goal[i][j] for example: goal[0][0] = 1
	private int xyto1D(int i, int j) {
		if (end(i,j)) {
			return 0;
		}
		return 1 + i * N + j;
	}
	
	// returns whether the given i,j coord. is at the end of the board.
	private boolean end(int i, int j) {
		return i == N-1 && j == N-1;
	}
	
	public int hamming() {
		if (hamming == -5) {
			hamming = 0;
			for(int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (tiles[i][j] != xyto1D(i,j) && !end(i,j)) {
						hamming++;
					}
				}
			}
		}
		return hamming;
	}
	
	public int manhattan() {
		// if this is the first call to manhattan
		if (manhattan == -5) {
			manhattan = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					int tile = tiles[i][j];
					if (tile != 0 && tile != xyto1D(i,j)) {
						int x = (tile-1)/N;
						int y = tile-1-x*N;
						int dist = Math.abs(i-x) + Math.abs(j-y);
						manhattan = manhattan+dist;
					}
				}
			}
		}
		// else return cached value
		return manhattan;
	}
	
	
	// constructs the goal board
	private int[][] goalBoard() {
		int count = 1;
		int[][] goalBoard = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				goalBoard[i][j] = count;
				count++;
			}
		}
		// sets last value to 0 (blank space)
		goalBoard[N-1][N-1] = 0;
		return goalBoard;
	}
	 private boolean tilesEquals(int[][] that) {
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if (tiles[i][j] != that[i][j]) {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
	 
	 public boolean isGoal() {
		 return hamming == 0;
	 }
	 
	 public boolean isSolvable() {
		int start = 0;
		int inversions = 0;
		int blankrow = 0;
		 for (int i = 0; i < N; i++) {
			 for (int j = 0; j < N; j++) {
				 // if blank space: skip
				 if (tiles[i][j] == 0) { continue; }
				 for (int k = i; k < N; k++) {
					 // if same row as tiles[i][j]
					 if (k == i) { start = j+1; }
					 // else
					 else { start = 0; }
					 for (int l = start; l < N; l++) {
						 // if blank space
						 if (tiles[k][l] == 0) {
							 blankrow = k;
							 continue;
						 }
						 else if (tiles[i][j] > tiles[k][l]) { inversions++; }
					 }
				 }
			 }
		 }
		 //StdOut.println(inversions);
		 //StdOut.println(blankrow);
		 //StdOut.println(N);
		 // if even
		 if ((N % 2) == 0) {
			 //StdOut.println("even");
			int inversionBlankSum = inversions + blankrow;
			if ((inversionBlankSum % 2) == 0) { return false; }
			else return true;
		 }
		 // if odd
		 else {
			 // if inversions are even
			 if ((inversions % 2) == 0) { return true; }
			 // if inversions are false
			 else return false;
		 }
	 }
	 
	 public boolean equals(Object y) {
		 if (this == y) { return true; }
		 if (y == null) { return false; }
		 if (this.getClass() != y.getClass()) { return false; }
		 Board that = (Board) y;
		 return this.N == that.N && tilesEquals(that.tiles);
	 }
	 
	 private boolean isNeighbor(int i, int j, int it, int jt) {
		 // check if neighbor in 2d array
	        if (it < 0 || it >= N || jt < 0 || jt >= N) {
	            return false;
	        }
	        else return true;
	    }

	 
	 public Iterable<Board> neighbors() {
		 // i,j coord. of the blank space (0)
		 int i0 = 0, j0 = 0;
	     boolean found = false;
	     // find i0,j0
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if (tiles[i][j] == 0) {
	                    i0 = i;
	                    j0 = j;
	                    found = true;
	                    break;
	                }
	            }
	            if (found) {
	                break;
	            }
	        }
	        //StdOut.println(i0);
	        //StdOut.println(j0);
	        Stack<Board> boards = new Stack<Board>();
	        
	        
	        Board board1 = new Board(tiles);
	        if (board1.isNeighbor(i0, j0, i0 - 1, j0)) {
	        	//StdOut.println("adding above");
	        	//StdOut.println(board1.toString());
	        	int temp = board1.tiles[i0][j0];
	        	//StdOut.println("should be 0 (above) " + temp);
		        board1.tiles[i0][j0] = board1.tiles[i0-1][j0];
		        board1.tiles[i0-1][j0] = temp;
		        //StdOut.println(board1.toString());
	            boards.push(board1);
	        }
	        Board board2 = new Board(tiles);
	        if (board2.isNeighbor(i0, j0, i0, j0 - 1)) {
	        	//StdOut.println("adding left");
	        	int temp = board2.tiles[i0][j0];
	        	//StdOut.println("should be 0 (left) " + temp);
		        board2.tiles[i0][j0] = board2.tiles[i0][j0-1];
		        board2.tiles[i0][j0-1] = temp;
		        //StdOut.println(board2.toString());
	            boards.push(board2);
	        }
	        Board board3 = new Board(tiles);
	        
	        if (board3.isNeighbor(i0, j0, i0 + 1, j0)) {
	        	//StdOut.println("adding below");
	        	int temp = board3.tiles[i0][j0];
	        	//StdOut.println("should be 0 (below) " + temp);
		        board3.tiles[i0][j0] = board3.tiles[i0+1][j0];
		        board3.tiles[i0+1][j0] = temp;
	            boards.push(board3);
	        }
	        Board board4 = new Board(tiles);
	       
	        if (board4.isNeighbor(i0, j0, i0, j0 + 1)) {
	        	//StdOut.println("adding right");
	        	int temp = board4.tiles[i0][j0];
	        	//StdOut.println("should be 0 (right) " + temp);
		        board4.tiles[i0][j0] = board4.tiles[i0][j0+1];
		        board4.tiles[i0][j0+1] = temp;
	            boards.push(board4);
	        }

	        return boards;
	 }
	 
	 public String toString() {
	        StringBuilder s = new StringBuilder();
	        s.append(N + "\n");
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                s.append(String.format("%2d ", tiles[i][j]));
	            }
	            s.append("\n");
	        }
	        return s.toString();
	    }
	 
	 public static void main(String[] args) {
		 In in = new In(args[0]);
		    int N = in.readInt();
		    int[][] input = new int[N][N];
		    for (int i = 0; i < N; i++)
		        for (int j = 0; j < N; j++)
		            input[i][j] = in.readInt();
		    Board initial = new Board(input);
		    StdOut.println("initial: ");
		    StdOut.println(initial.toString());
		    if (initial.isSolvable()) { StdOut.println("Solvable"); }
		    else { StdOut.println("Not solvable"); }
		    Board goal = new Board(initial.goalBoard());
		    StdOut.println("Goal: ");
		    StdOut.println(goal.toString());
		    if (initial.equals(goal)) { StdOut.println("equal"); }
		    if (initial.isGoal()) { StdOut.println("is goal"); }
		    StdOut.println("neighbors:");
		    for (Board b: initial.neighbors()) {
		    	StdOut.println(b.toString());
		    }
		    StdOut.println("Hamming sum: " + initial.hamming());
		    StdOut.println("Manhattan sum: " + initial.manhattan());
		    
	 }
 }