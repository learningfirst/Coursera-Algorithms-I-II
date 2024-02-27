package week4;

import java.util.ArrayList;

public class Board {

    private int[][] board;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles){
        board = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j < tiles.length; j++)
            {
                board[i][j] = tiles[i][j];
            }
        }
    }


    // string representation of this board
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(board.length+"\n");
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                str.append(board[i][j]+" ");
            }
            str.append("\n");
        }
        return str.toString();
    }


    // board dimension n
    public int dimension(){
        return board.length;
    }

    // number of tiles out of place
    public int hamming()
    {
        int res = 0;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                if(board[i][j] != 0 && board[i][j]  != (i*board.length+j+1)){
                    res++;
                }
            }
        }
        return res;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        int res = 0;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                int goal = (i*board.length+j+1);
                if(board[i][j] != 0 && board[i][j]  != goal){
                    res = res + Math.abs(i - (board[i][j]-1)/board.length) + Math.abs(j - (board[i][j]-1)%board.length);
                }
            }
        }
        return res;
    }

    // is this board the goal board?
    public boolean isGoal(){
        return hamming() == 0;
    }

    // does this board equal y?
    @Override
    public boolean equals(Object y){
       if(y == this) return true;
       if(y == null) return false;
       if(y.getClass() != this.getClass()) return false;
       Board that = (Board) y;
       if(that.manhattan() != this.manhattan()) return false;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                if(this.board[i][j] != that.board[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        ArrayList<Board> arrayList = new ArrayList<>();
        int row = 0;
        int col = 0;
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
                if(this.board[i][j] == 0){
                    row = i;
                    col = j;
                    break;
                }
        if(checkBound(board.length, row-1, col)) arrayList.add(new Board(swap(board, row, col, row-1, col)));
        if(checkBound(board.length, row+1, col)) arrayList.add(new Board(swap(board, row, col, row+1, col)));
        if(checkBound(board.length, row, col-1)) arrayList.add(new Board(swap(board, row, col, row, col-1)));
        if(checkBound(board.length, row, col+1)) arrayList.add(new Board(swap(board, row, col, row, col+1)));
        return arrayList;
    }

    private int[][] swap( int[][] board, int row1, int col1,int row2, int col2)
    {
        int[][] res = copy(board);
        int temp = res[row1][col1];
        res[row1][col1] = res[row2][col2];
        res[row2][col2] = temp;
        return res;
    }

    private int[][] copy(int[][] blocks) {
        int[][] copy = new int[blocks.length][blocks.length];
        for (int row = 0; row < blocks.length; row++)
            for (int col = 0; col < blocks.length; col++)
                copy[row][col] = blocks[row][col];

        return copy;
    }


    private boolean checkBound(int n, int row, int col){
        if(row >= 0 && row < n && col >= 0 && col < n){
            return true;
        }
        return false;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board.length-1; col++){
                if(board[row][col] != 0 && board[row][col+1] != 0)
                    return  new Board(swap(board, row, col, row, col+1));
            }
        }
        return null;
    }


    // unit testing (not graded)
    public static void main(String[] args){
        int[][] tiles = {
                {8,1,3},
                {4,0,2},
                {7,6,5}
        };
        Board board = new Board(tiles);
        System.out.println(board);
        System.out.println(board.hamming());
        System.out.println(board.manhattan());
        Board clone = board.twin();
        System.out.println(clone);
        ArrayList<Board> arrayList =(ArrayList<Board>)board.neighbors();
        for (Board bo: arrayList)
        {
            System.out.println(bo.toString());
        }
    }

}
