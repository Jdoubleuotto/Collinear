/* *****************************************************************************
 *  Name: John Otto
 *  Date: 1/4/2023
 *  Description: A program BruteCollinearPoints.java that examines 4 points
 *  at a time and checks whether they all lie on the same line segment,
 *  returning all such line segments. To check whether the 4 points p, q, r,
 *  and s are collinear, check whether the three slopes between p and q,
 *  between p and r, and between p and s are all equal.
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    // total line segments
    // private int totalSegments;

    // arr of all line segments
    private LineSegment[] lineSegments;
    // array list for dynamic sizing
    private ArrayList<LineSegment> segments;


    // find all line segments containing 4 fours
    public BruteCollinearPoints(Point[] points) {
        // check for illegal arguments when the array is null and when elements are null
        if (points == null) {
            throw new IllegalArgumentException("The Input in null");
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("A point in the input is null");
            }
        }
        // checking for illegal arguments if there are repeated elements in the array
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("There are repeated elements");
                }
            }
        }

        // create a copy of the points to prevent mutation and sort in ascending order
        Point[] copyPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(copyPoints);

        // use an array list and create track of an index for insertion
        segments = new ArrayList<>();

        // use 4 for loops to check every unique pair of 4 points in the array
        for (int p = 0; p < copyPoints.length - 3; p++) {
            for (int q = p + 1; q < copyPoints.length - 2; q++) {
                for (int r = q + 1; r < copyPoints.length - 1; r++) {
                    for (int s = r + 1; s < copyPoints.length; s++) {
                        // if the slopes from p->q->r->s are equal, points p & q are a line segment
                        double slopePQ = copyPoints[p].slopeTo(copyPoints[q]);
                        double slopePR = copyPoints[p].slopeTo(copyPoints[r]);
                        double slopePS = copyPoints[p].slopeTo(copyPoints[s]);
                        if (slopePQ == slopePR && slopePQ == slopePS) {
                            segments.add(new LineSegment(copyPoints[p], copyPoints[s]));
                        }
                    }
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the array of all line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[numberOfSegments()]);
    }

    // testing and drawing
    public static void main(String[] args) {
        // Point p1 = new Point(0, 4);
        // Point p2 = new Point(0, 1);
        // Point p3 = new Point(1, 0);
        // Point p4 = new Point(1, 1);
        // Point p5 = new Point(2, 2);
        // Point p6 = new Point(3, 3);
        // Point p7 = new Point(4, 4);
        // Point p8 = new Point(5, 5);
        //
        // Point[] points = { p1, p2, p3, p4, p5, p6, p7, p8 };
        // BruteCollinearPoints point = new BruteCollinearPoints(points);
        // System.out.println(point.segments()[0]);
        // System.out.println(point.segments()[1]);

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
