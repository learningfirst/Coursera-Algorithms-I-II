package sort;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class FastCollinearPoints {

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

    private static Point[] aux ;
    public FastCollinearPoints(Point[] points)  {
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
        Arrays.sort(points);
        aux = new Point[points.length];

        int n = points.length;
        for (int m = 0; m < n; m++)
        {
            Point[] copy = new Point[points.length];
            for (int i = 0; i < n; i++)
            {
                copy[i] = points[i];
            }
            for (int i = 1; i < n; i = i * 2)
            {
                for (int lo = 0; lo < n - i; lo = i * 2 + lo)
                {
                    merge(copy, lo, lo+i-1, Math.min(lo+i*2-1,n-1), points[m]);
                }
            }


            int low = 0;
            int high = 0;
            int length = 0;
            for (int i = 0; i < copy.length - 1; i++)
            {

                if (copy[0].slopeTo(copy[i]) == copy[0].slopeTo(copy[i + 1]))
                {
                    if (length == 0) low = i;
                    else high = i + 1;
                    length++;
                    if (i == copy.length - 2 && length >= 2)
                    {
                        if (copy[0].compareTo(copy[low]) == -1)
                        {
                            addListNode(copy[0], copy[high]);
                            num++;
                        }

                    }
                }

                if (points[m].slopeTo(copy[i]) != points[m].slopeTo(copy[i + 1]))
                {
                    if (copy[0].compareTo(copy[low]) == -1 && length >= 2)
                    {
                        addListNode(copy[0], copy[high]);
                        num++;
                    }
                    length = 0;
                }
            }
        }
        }


       // finds all line segments containing 4 or more points

    private static void merge(Point[] a, int low, int mid, int high, Point point)
    {
        for (int k = low; k <= high ; k++)
        {
            aux[k] = a[k];
        }

        int i = low, j = mid+1;
        for(int k =low; k<= high ; k++)
        {
            if(j > high) a[k] = aux[i++];
            else if(i > mid) a[k] = aux[j++];
            else if(point.slopeOrder().compare(aux[i],aux[j]) == 1) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public int numberOfSegments()    {
        return num;
    }    // the number of line segments

    public LineSegment[] segments()     {
        LineSegment[] lineSegments = new LineSegment[num];
        int k=0;
        ListNode current = head;
        while(current != null){
            lineSegments[k] = current.lineNode;
            current = current.next;
            k++;
        }
        return lineSegments;
    }  // the line segments

    public static void main(String[] args) {

        Point[] points = new Point[]{
                new Point(0, 0), new Point( 1, 1), new Point(2, 2),new Point(3, 3),new Point(1,  0),
                new Point(2,   0),new Point(3,  0),new Point(4,   0),new Point(2, 1),new Point(3, 1),
                new Point(3,   2),new Point(4,   1),new Point(4,   2),new Point(4,   3),
        };
        FastCollinearPoints bruteCollinearPoints = new FastCollinearPoints(points);
        LineSegment[] lineSegments = bruteCollinearPoints.segments();
        for (int i = 0; i < lineSegments.length; i++)
        {
            System.out.println(lineSegments[i].toString());
        }



        // read the n points from a file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        Point[] points = new Point[n];
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//        }
//

//        // draw the points
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();
//
//        // print and draw the line segments
//        FastCollinearPoints collinear = new FastCollinearPoints(points);
//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();
    }
}
