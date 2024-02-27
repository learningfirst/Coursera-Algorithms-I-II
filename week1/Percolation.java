package union;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean[] block;
    private int n;
    private int top;
    private int bottom;
    private int count = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if (n <= 0)
        {
            throw new IllegalArgumentException();
        }
        weightedQuickUnionUF = new WeightedQuickUnionUF(n*n+2);
        block=new boolean[n*n+2];
        this.n = n;
        for (int i = 0; i < n*n+2; i++)
        {
            block[i] = true;
        }
        top = n*n;
        bottom = n*n+1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        int num = (row-1)* n + col-1;
        int up = num- n, down = num+n, left = num-1, right = num+1;
        if (!isOpen(row, col))
        {
            block[num] = false;
            count++;
            if (up >= 0 && block[up] == false)
            {
                weightedQuickUnionUF.union(num, up);
            }
            else if (up < 0)
            {
                weightedQuickUnionUF.union(num,top);
            }
            if(down < n * n && block[down] == false){weightedQuickUnionUF.union(num,down);}
            else if (down >= n*n)
            {
                weightedQuickUnionUF.union(num,bottom);
            }
            if (num%n != 0 && left >= 0 && block[left] == false){weightedQuickUnionUF.union(num,left);}
            if(right%n != 0 && right < n * n && block[right] == false){weightedQuickUnionUF.union(num,right);}
        }
    }

    private void validate(int row,int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("index is not between 0 and " + (n-1));
        }
    }
    private int index(int row,int col)
    {
        validate(row, col);
        return (row-1)* n+col-1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        int num = index(row, col);
        return block[num] ? false : true;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        int num = index(row, col);
        boolean res = (weightedQuickUnionUF.find(num) == weightedQuickUnionUF.find(top));
        return res;
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        int num = count;
        return num;
    }

    // does the system percolate?
    public boolean percolates()
    {
        boolean res = (weightedQuickUnionUF.find(bottom)==weightedQuickUnionUF.find(top));
        return res;
    }

    // test client (optional)
    public static void main(String[] args){
//        Percolation percolation = new Percolation(5);
//        percolation.open(1,3);
//        System.out.println(percolation.isFull(1,1));
//        System.out.println(percolation.isFull(1,3));
//        System.out.println(percolation.isOpen(1,3));
//        percolation.open(2,3);
//        percolation.open(3,3);
//        percolation.open(4,3);
//        System.out.println(percolation.percolates());
//        System.out.println(percolation.isFull(4,4));
//        percolation.open(4,4);
//        System.out.println(percolation.isFull(4,4));
//        percolation.open(5,5);
//        System.out.println(percolation.numberOfOpenSites());
//        System.out.println(percolation.percolates());
//        percolation.open(4,5);
//        System.out.println(percolation.percolates());
        System.out.println(-1%7);
    }
}