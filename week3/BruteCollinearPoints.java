package sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {

    private ListNode head;
    private int num;

    private class ListNode{
        private LineSegment lineNode;
        private ListNode next;

        public ListNode(LineSegment lineSegment){
            lineNode = lineSegment;
            next = null;
        }
    }
    private void addListNode(Point q ,Point p){
        if(head == null){
            head = new ListNode(new LineSegment(p,q));
        }
        else {
            ListNode temp = head;
            head = new ListNode(new LineSegment(p,q));
            head.next = temp;
        }
    }

    public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
    {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < points.length; i++) {
            if (i == 0){
                if (points[i] == null) {
                    throw new IllegalArgumentException();
                }
            }
            for (int j = i + 1; j < points.length; j++) {
                if (i == 0) {
                    if (points[j] == null)
                        throw new IllegalArgumentException();
                }

                if (points[i].compareTo(points[j]) == 0){
                    throw new IllegalArgumentException();
                }
            }
        }
        if (points.length < 4) {
            return;
        }
        head =null;
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++)
        {
            for (int j = i+1; j < points.length; j++)
            {
                for (int k = j+1; k < points.length; k++)
                {
                    for (int l = k+1; l < points.length; l++)
                    {
                        if((points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])) && (points[j].slopeTo(points[k]) == points[k].slopeTo(points[l])))
                        {
                            num++;
                            addListNode(points[i],points[l]);
                        }
                    }
                }
            }
        }
    }
    public int numberOfSegments()        // the number of line segments
    {
        return num;
    }
    public LineSegment[] segments()                // the line segments
    {
        LineSegment[] lineSegments = new LineSegment[num];
        int k=0;
        ListNode current = head;
        while(current != null){
            lineSegments[k] = current.lineNode;
            current = current.next;
            k++;
        }
        return lineSegments;
    }

    public static void main(String[] args)
    {
//        Point[] points = new Point[]{
//                new Point(1,1),new Point(2,2),new Point(3,3),new Point(4,4),
//                 new Point(2,1),new Point(4,2),new Point(6,3),new Point(8,4),
//                new Point(10,6),new Point(1,4)
//        };
//        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
//        LineSegment[] lineSegments = bruteCollinearPoints.segments();
//        for (int i = 0; i < lineSegments.length; i++)
//        {
//            System.out.println(lineSegments[i].toString());
//        }
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}