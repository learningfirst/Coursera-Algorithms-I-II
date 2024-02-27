package week4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class Solver {

    private class MinPQ
    {
        private SearchNode[] array;
        private int n;
        private int size;
        MinPQ(){
            array = new SearchNode[2];
            n = 1;
            size = 2;
        }

        private boolean isEmpty(){
            return n == 1;
        }

        private void swim(int n){
            while(n/2 >= 1 && less(n, n/2)){
                exch(n, n/2);
                n = n/2;
            }
        }

        private void exch(int a, int b){
            SearchNode temp  = array[a];
            array[a] = array[b];
            array[b] = temp;
        }

        private boolean less(int a, int b){
            return array[a].propertity < array[b].propertity;
        }

        private void insert(SearchNode searchNode){
            if(n == size) {
                SearchNode[] temp = new SearchNode[size*2];
                for (int i = 1; i < n; i++)
                {
                    temp[i] = array[i];
                }
                array = temp;
                size = size*2;
            }
            array[n] = searchNode;
            swim(n);
            n++;
        }

        private SearchNode delMin(){
            if(isEmpty()) throw  new NoSuchElementException();
            if(n == size/4){
                SearchNode[] temp = new SearchNode[size/2];
                for (int i = 1; i < n; i++)
                {
                    temp[i] = array[i];
                }
                array = temp;
                size = size/2;
            }
            SearchNode min =  array[1];
            array[1] = array[n-1];
            array[n-1] = null;
            n--;
            sink(1);
            return min;
        }

        private void sink(int i)
        {
            while(i*2 < n){
                int j = i*2;
                if(i*2+1 < n && less(i*2+1, i*2)) j++;
                if(less(i, j)) break;
                exch(i, j);
                i = j;
            }
        }
    }

    private class SearchNode{
        private Board board;
        private int move;
        private SearchNode previous;
        private int propertity;
        SearchNode(Board board, int move, SearchNode previous){
            this.board = board;
            this.move = move;
            this.previous = previous;
            this. propertity = move + board.manhattan();
        }

    }

    private boolean isSolvable;
    private SearchNode lastNode;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){
        if(initial == null) throw new IllegalArgumentException();
        MinPQ minPq1 = new MinPQ();
        MinPQ minPq2 = new MinPQ();
        SearchNode searchNode1 = new SearchNode(initial, 0 ,null);
        SearchNode searchNode2 = new SearchNode(initial.twin(), 0, null);
        minPq1.insert(searchNode1);
        minPq2.insert(searchNode2);
        while(true)
        {
            lastNode = ASearch(minPq1);
            if(lastNode.board.isGoal()) {
                isSolvable = true;
                return;
            }
            if( ASearch(minPq2).board.isGoal()){
                isSolvable =false;
                return;
            }
        }
    }

    private SearchNode ASearch(MinPQ minPq){
        //Board initial = queue.array[1].board;
        //while(!initial.isGoal()){
            SearchNode bestNode = minPq.delMin();
            for (Board neighbor : bestNode.board.neighbors())
            {
                if(bestNode.previous == null || !neighbor.equals(bestNode.previous.board)){
                    minPq.insert(new SearchNode(neighbor, bestNode.move+1 , bestNode));
                }
            }
            return bestNode;
        //}
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable(){
        return isSolvable;
    }


    // min number of moves to solve initial board; -1 if unsolvable
    public int moves(){
        if(!isSolvable()) return -1;
        return lastNode.move;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution(){
        if(!isSolvable()) return null;
        Stack<Board> process = new Stack<>();
        SearchNode current = lastNode;
        while(current!= null){
            process.push(current.board);
            current = current.previous;
        }
        return process;
    }


    // test client (see below)
    public static void main(String[] args){
        int[][] tiles = {
                {1,7,4},
                {6,0,5},
                {8,2,3}
        };
        Board initial = new Board(tiles);
        Solver solver = new Solver(initial);
        System.out.println(solver.isSolvable());

         Stack<Board> solution=(Stack<Board>) solver.solution();
        for (Board bo: solution)
        {
            System.out.println(bo.toString());
        }


//         //create initial board from file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        int[][] tiles = new int[n][n];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                tiles[i][j] = in.readInt();
//        Board initial = new Board(tiles);
//
//        // solve the puzzle
//        Solver solver = new Solver(initial);
//
//        // print solution to standard output
//        if (!solver.isSolvable())
//            StdOut.println("No solution possible");
//        else {
//            StdOut.println("Minimum number of moves = " + solver.moves());
//            for (Board board : solver.solution())
//                StdOut.println(board);
//        }
    }

}