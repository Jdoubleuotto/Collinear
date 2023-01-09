/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    // find all line segments containing 4 or more points
    private LineSegment[] segments;


    public FastCollinearPoints(Point[] points) {
        // check for illegal arguments when the array is null and when elements are null
        if (points == null) {
            throw new IllegalArgumentException("The Input in null");
        }

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException("A point in the input is null");
            }
        }

        int numberOfPoints = points.length;
        // create a copy of the points to prevent mutation and sort in ascending order
        Point[] copyPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(copyPoints);

        // checking for illegal arguments if there are repeated elements in the array
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException("There are repeated elements");
            }
        }

        // tracks if there are 4 points in a line segment
        int count = 0;
        double[] slopes = new double[points.length];
        ArrayList<LineSegment> foundLines = new ArrayList<>();


        segments = new LineSegment[points.length];
        // iterate through the points array so every point is a "pivot" point
        int i = 0;
        while (i < numberOfPoints) {
            Point p = copyPoints[i];
            Point[] pointsBySlope = copyPoints.clone();
            Arrays.sort(pointsBySlope, p.slopeOrder());
            

            for (int j = 0; j < points.length; j++) {
                if (slopes[j] == slopes[j + 1]) {
                    count++;
                }
            }

            int index = 0;
            if (count >= 3) {
                totalSegments++;
                segments[index] = temp[i];
                index++;
            }
            i++;
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return totalSegments;
    }

    // the line segments
    public LineSegment[] segments() {
        // return lineSegments;
        return segments;
    }

    public static void main(String[] args) {
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

// tracks if there are 4 points in a line segment
// int count = 0;
// slopes = new double[points.length];
// LineSegment[] temp = new LineSegment[slopes.length];
//
// lines = new LineSegment[points.length];
// for (int i = 0; i < points.length; i++) {
//     // create a slopes array
//     for (int k = 0; k < points.length; k++) {
//         slopes[k] = points[k].slopeTo(points[i]);
//     }
//
//     for (int j = 0; i < points.length; i++) {
//         for (int l = 0; l < points.length; l++) {
//             if (l != j) {
//                 if (slopes[j] == slopes[l]) {
//                     count++;
//                     temp[i] = new LineSegment(points[j], points[l]);
//                 }
//             }
//         }
//     }
//     int index = 0;
//     if (count == 3) {
//         totalSegments++;
//         lines[index] = temp[i];
//         index++;
//     }
// }
