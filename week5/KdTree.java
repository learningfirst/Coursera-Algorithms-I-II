//package week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree
{
    private Node root;
    private int size;
    public KdTree()                               // construct an empty set of points
    {
        root = null;
        size = 0;
    }

    private class Node
    {
        private RectHV rect;
        private Point2D point2D;
        private Node left,right;

        public Node(Point2D point2D){
            this.point2D = point2D;
            this.left = null;
            this.right = null;
        }
    }

    public boolean isEmpty()                      // is the set empty?
    {
        return size == 0;
    }
    public int size()                         // number of points in the set
    {
        return size;
    }

    public void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if(p == null){
            throw new IllegalArgumentException();
        }

        root = insert(root, p, null ,1);

    }

    private Node insert(Node node, Point2D newPoint, Node parentNode, int depth)
    {
        if(node == null) {
            Node node1 = new Node(newPoint);
            size++;
            if(parentNode == null) node1.rect = new RectHV(0,0,1,1);
            else {
                if(depth%2 == 1) {
                    if(CompareTo(parentNode.point2D.y(), newPoint.y()) == 1){
                        node1.rect = new RectHV(parentNode.rect.xmin(), parentNode.rect.ymin(), parentNode.rect.xmax(), parentNode.point2D.y());
                    }
                    else {
                        node1.rect = new RectHV(parentNode.rect.xmin(), parentNode.point2D.y(), parentNode.rect.xmax(), parentNode.rect.ymax());
                    }
                }
                else {
                    if(CompareTo(parentNode.point2D.x(), newPoint.x()) == 1){
                        node1.rect = new RectHV(parentNode.rect.xmin(), parentNode.rect.ymin(), parentNode.point2D.x(), parentNode.rect.ymax());
                    }
                    else {
                        node1.rect = new RectHV(parentNode.point2D.x(), parentNode.rect.ymin(), parentNode.rect.xmax(), parentNode.rect.ymax());
                    }
                }
            }
            return node1;
        }

        if(node.point2D.equals(newPoint)){
            return node;
        }

        if(depth%2 == 1)
        {
            if(CompareTo(node.point2D.x(), newPoint.x()) == 1){
                node.left = insert(node.left, newPoint, node, depth+1);
            }
            else {
                node.right = insert(node.right, newPoint, node, depth+1);
            }
        }
        else if(depth%2 == 0)
        {

            if(CompareTo(node.point2D.y(), newPoint.y()) == 1){
                node.left = insert(node.left, newPoint, node, depth+1);
            }
            else {
                node.right = insert(node.right, newPoint, node, depth+1);
            }
        }
        return node;
    }

    private int CompareTo(double x,double y)
    {
        if(x <= y) return -1;
        else  return 1;
    }

    public boolean contains(Point2D p)            // does the set contain point p?
    {
        if(p == null){
            throw new IllegalArgumentException();
        }

        Node x = root;
        int depth = 1;
        while (x != null)
        {
            if(x.point2D.equals(p)) return true;
            if(depth%2 == 1){
                if(CompareTo(x.point2D.x(), p.x()) == 1)  x = x.left;
                else x = x.right;
            }
            else {
                if(CompareTo(x.point2D.y(), p.y()) == 1)  x = x.left;
                else x = x.right;
            }
            depth++;
        }
        return false;
    }

    public void draw()                         // draw all points to standard draw
    {
        draw(root, null, 1);
    }

    private void draw(Node node, Node parentNode, int depth){
        if(!(node.left ==null)){
            draw(node.left, node, depth+1);
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(node.point2D.x(), node.point2D.y());
        if (parentNode == null) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            StdDraw.line(node.point2D.x(), 0, node.point2D.x(), 1);
        }else if(depth%2 == 1){
            if (node.equals(parentNode.left)) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius();
                StdDraw.line(node.point2D.x(), parentNode.rect.ymin(), node.point2D.x(), parentNode.point2D.y());
            } else {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius();
                StdDraw.line(node.point2D.x(), parentNode.point2D.y(), node.point2D.x(), parentNode.rect.ymax());
            }
        }else {
            if (node.equals(parentNode.left)) {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius();
                StdDraw.line(parentNode.rect.xmin(), node.point2D.y(), parentNode.point2D.x(), node.point2D.y());
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius();
                StdDraw.line(parentNode.point2D.x(), node.point2D.y(), parentNode.rect.xmax(), node.point2D.y());
            }
        }

        // draw left path of BST
        if (!(node.right == null)) {
            draw(node.right, node, depth+1);
        }
    }

    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary)
    {
        if(rect == null){
            throw new IllegalArgumentException();
        }
        ArrayList<Point2D> arrayList = new ArrayList<>();
        range(root, rect, 1, arrayList);
        return arrayList;
    }

    private void range(Node node, RectHV rectHV, int depth, ArrayList<Point2D> arrayList)
    {
        if(node == null)  return;
        if(rectHV.contains(node.point2D)) arrayList.add(node.point2D);
        if(depth%2 == 1){
            if(CompareTo(node.point2D.x(), rectHV.xmax()) == 1)
                range(node.left, rectHV, depth+1, arrayList);
            else if(CompareTo(node.point2D.x(), rectHV.xmin()) == -1)
                range(node.right, rectHV, depth+1, arrayList);
            else
            {
                range(node.left, rectHV, depth+1, arrayList);
                range(node.right, rectHV, depth+1, arrayList);
            }

        }
        else {
            if(CompareTo(node.point2D.y(), rectHV.ymax()) == 1)
                range(node.left, rectHV, depth+1, arrayList);
            else if(CompareTo(node.point2D.y(), rectHV.ymin()) == -1)
                range(node.right, rectHV, depth+1, arrayList);
            else
            {
                range(node.left, rectHV, depth+1, arrayList);
                range(node.right, rectHV, depth+1, arrayList);
            }
        }
    }

    public Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    {
        if(p == null){
        throw new IllegalArgumentException();
    }
        if(this.isEmpty()){
            return null;
        }
        //double initial = root.point2D.distanceTo(p);
        return nearest(root, p, root.point2D, 1);
    }

    private Point2D nearest(Node node, Point2D point2D,Point2D nearPoint, int depth)
    {
        if(node == null || nearPoint.distanceTo(point2D) <= node.rect.distanceTo(point2D)){
            return nearPoint;
        }
        if(point2D.distanceTo(node.point2D) < point2D.distanceTo(nearPoint)){
            nearPoint = node.point2D;
        }

        if(depth%2 == 1)
        {
            if(CompareTo(node.point2D.x(), point2D.x()) == 1){
                nearPoint = nearest(node.left, point2D, nearPoint, depth+1);
                nearPoint = nearest(node.right, point2D, nearPoint, depth+1);
            }
            else {
                nearPoint = nearest(node.right, point2D, nearPoint, depth+1);
                nearPoint = nearest(node.left, point2D, nearPoint, depth+1);
            }
        }
        else {
            if(CompareTo(node.point2D.y(), point2D.y()) == 1){
                nearPoint = nearest(node.left, point2D, nearPoint, depth+1);
                nearPoint = nearest(node.right, point2D, nearPoint, depth+1);
            }
            else {
                nearPoint = nearest(node.right, point2D, nearPoint, depth+1);
                nearPoint = nearest(node.left, point2D, nearPoint, depth+1);
            }
        }
        return nearPoint;
    }

    public static void main(String[] args)
    {
        KdTree tree = new KdTree();
        tree.insert(new Point2D(0.7,0.2));
        tree.insert(new Point2D(0.5,0.4));
        tree.insert(new Point2D(0.2,0.3));
        tree.insert(new Point2D(0.4,0.7));
        tree.insert(new Point2D(0.9,0.6));
        //tree.draw();
        System.out.println(tree.contains(new Point2D(0.4, 0.7)));
//        RectHV rectHV = new RectHV(0,0,0.5,0.5);
//        for (Point2D p: tree.range(rectHV))
//        {
//            System.out.println(p.toString());
//        }
//        System.out.println(tree.nearest(new Point2D(0.2,1)));
    }
}
