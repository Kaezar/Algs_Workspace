package puzzle;

import java.util.Stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	private Stack<Board> boards;
	private int moves;
	
	
	private class SearchNode implements Comparable<SearchNode> {
		private Board board;
		private int moves;
		private SearchNode previous;
		private int priority = -1;
		
		SearchNode(Board board, int moves, SearchNode previous) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
        }

        private int priority() {
            if (priority == -1) {
                priority = moves + board.manhattan();
            }
            return priority;
        }

        public int compareTo(SearchNode that) {
            if (this.priority() < that.priority()) {
                return -1;
            }
            if (this.priority() > that.priority()) {
                return +1;
            }
            return 0;
        }
	}
	
	public Solver(Board initial) {
		if (initial.isSolvable() == false) { throw new java.lang.IllegalArgumentException(); }
		boards = new Stack<Board>();
		this.boards.push(initial);
		
		MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
		moves = 0;
        Board board = initial;
        SearchNode node = new SearchNode(board, 0, null);
        minPQ.insert(node);
        
        while (moves < 100) {
        	node = minPQ.delMin();
        	board = node.board;
        	if (board.isGoal()) {
        		this.boards.push(board);
                while (node.previous != null) {
                    node = node.previous;
                    this.boards.push(node.board);
                }
                return;
        	}
        	node.moves++;
        	Iterable<Board> neighbors = board.neighbors();
            for (Board neighbor : neighbors) {
                if (node.previous != null
                        && neighbor.equals(node.previous.board)) {
                    continue;
                }
                SearchNode newNode = new SearchNode(neighbor, node.moves, node);
                minPQ.insert(newNode);
            }
        }
	}
	
	public int moves() {
		return boards.size() - 1;
	}
	
	public Iterable<Board> solution() {
		return boards;
	}
	
	public static void main(String[] args) {
		//long startTime = System.nanoTime(); 
	    // create initial board from file
	    In in = new In(args[0]);
	    int N = in.readInt();
	    int[][] tiles = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            tiles[i][j] = in.readInt();
	    Board initial = new Board(tiles);

	    // check if puzzle is solvable; if so, solve it and output solution
	    if (initial.isSolvable()) {
	        Solver solver = new Solver(initial);
	        
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        //long endTime = System.nanoTime();
	        
	        for (Board board : solver.solution())
	            StdOut.println(board);
	        //StdOut.println("Took "+(endTime - startTime) + " ns");
	    }

	    // if not, report unsolvable
	    else {
	        StdOut.println("Unsolvable puzzle");
	    }
	    
	}
}