//package week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

public class PointSET {
    private SET<Point2D> point2DS;
    public  PointSET()  // construct an empty set of points
    {
        point2DS = new SET<>();

    }

    public boolean isEmpty()                      // is the set empty?
    {
        return point2DS.isEmpty();
    }

    public int size()                         // number of points in the set
    {
        return point2DS.size();
    }

    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if(p == null){
            throw new IllegalArgumentException();
        }
        point2DS.add(p);
    }

    public  boolean contains(Point2D p)            // does the set contain point p?
    {
        if(p == null){
            throw new IllegalArgumentException();
        }
        return point2DS.contains(p);
    }

    public void draw()                         // draw all points to standard draw
    {
        point2DS.toString();
    }

    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {
        if(rect == null){
            throw new IllegalArgumentException();
        }
        SET<Point2D>  set = new SET<>();
        for (Point2D point2D : point2DS)
        {
            if(rect.contains(point2D)) set.add(point2D);
        }
        return set;
    }

    public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    {
        if(p == null){
            throw new IllegalArgumentException();
        }
        if(isEmpty()){
            return null;
        }

        Point2D nearPoint = point2DS.min();
        for (Point2D point2D : point2DS)
        {
            if(p.distanceTo(point2D) < p.distanceTo(nearPoint))
                 nearPoint = point2D;
        }
        return nearPoint;
    }

    public static void main(String[] args)                  // unit testing of the methods (optional)
    {
        Point2D point2D1 = new Point2D(0,0);
        Point2D point2D2 = new Point2D(0,0.2);
        Point2D point2D3 = new Point2D(0.2,0.2);
        Point2D point2D4 = new Point2D(0.3,0.4);
        RectHV rectHV = new RectHV(0,0, 0.3,0.3);
        PointSET pointSET = new PointSET();
        pointSET.insert(point2D1);
        pointSET.insert(point2D2);
        pointSET.insert(point2D3);
        pointSET.insert(point2D4);
        for (Point2D p: pointSET.range(rectHV))
        {
            System.out.println(p.toString());
        }
        System.out.println(pointSET.nearest(point2D4).toString());
    }
}