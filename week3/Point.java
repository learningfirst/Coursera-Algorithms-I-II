package sort;

import edu.princeton.cs.algs4.StdDraw;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {
    private int x;
    private int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }              // constructs the point (x, y)

    public   void draw(){
        StdDraw.point(x,y);
    }                               // draws this point

    public   void drawTo(Point that)     {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }              // draws the line segment from this point to that point

    public String toString(){
        return ("("+x+","+y+")");
    }                // string representation


    public int compareTo(Point that){
        if(y<that.y || (y ==that.y && x< that.x)) return -1;
        else if(y ==that.y && x== that.x)  return 0;
        else return 1;

    }     // compare two points by y-coordinates, breaking ties by x-coordinates
    public double slopeTo(Point that){
        if(that == null)  throw new NullPointerException();
        if(x == that.x && y == that.y) return Double.NEGATIVE_INFINITY;
        else if(x == that.x) return Double.POSITIVE_INFINITY;
        else if(y == that.y) return (1.0-1.0)/1.0;
        double slope = ((double)(y-that.y)/(x-that.x));
        return slope;

    }       // the slope between this point and that point
    public Comparator<Point> slopeOrder()  {
        return new  slope();
    }            // compare two points by slopes they make with this point

    private  class slope implements Comparator<Point>{

        @Override
        public int compare(Point o1, Point o2)
        {
            if(slopeTo(o1) < slopeTo(o2)) return -1;
            else  if(slopeTo(o1) > slopeTo(o2)) return 1;
            else return 0;
        }
    }

    public static void main(String[] args)
    {
        Point[] point = new Point[]{new Point(0,4),new Point(3,3),new Point(1,3),new Point(4,0)};
        for (int i = 0; i < 4; i++)
        {
            System.out.println(point[i].toString());
        }
        Arrays.sort(point);
        for (int i = 0; i < 4; i++)
        {
            System.out.println(point[i].toString());
        }
    }
}