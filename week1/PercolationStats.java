package union;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

    private double[] trials_data;
    private final double constant = 1.96;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {

        if (n <= 0||trials <= 0)
        {
            throw new IllegalArgumentException("NO");
        }
        this.trials_data = new double[trials];
        for(int i = 0;i < trials; i++)
        {
            Percolation percolation = new Percolation(n);
            while(!percolation.percolates())
            {
            int ran_row = StdRandom.uniformInt(1, n + 1);
            int ran_col = StdRandom.uniformInt(1, n + 1);
            percolation.open(ran_row, ran_col);
            }

            trials_data[i] = (double) percolation.numberOfOpenSites()/(n*n);
        }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(trials_data);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(trials_data);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        double res = StdStats.mean(trials_data) - (constant*StdStats.stddev(trials_data)/Math.sqrt(trials_data.length));
        return res;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        double res = StdStats.mean(trials_data) + (constant*StdStats.stddev(trials_data)/Math.sqrt(trials_data.length));
        return res;
    }


    public static void main(String[] args)
    {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        System.out.println("mean=" + percolationStats.mean());
        System.out.println("stddev=" + percolationStats.stddev());
        System.out.println("95% confidence interval =[" + percolationStats.confidenceLo() + "," + percolationStats.confidenceHi() + "]");
    }
//    public static void main(String[] args)
//    {
//        PercolationStats percolationStats=new PercolationStats(200,100);
//        System.out.println("mean="+percolationStats.mean());
//        System.out.println("stddev="+percolationStats.stddev());
//        System.out.println("95% confidence interval =["+percolationStats.confidenceLo()+","+percolationStats.confidenceHi()+"]");
//    }

}
