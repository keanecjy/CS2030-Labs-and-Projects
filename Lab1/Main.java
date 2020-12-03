import java.util.Scanner;
import java.util.Arrays;
class Main {
    public static Circle createCircle(Point p1, Point p2, 
            double radius) {
        Point mid = p1.midPoint(p2);
        double dist = p2.distBetween(mid);
        if (radius * radius < dist * dist || p1.equals(p2)) {
            return null;
        } else {
            double distToCentre = Math.sqrt(radius * radius
                    - dist * dist);
            double angleBetween = p1.angleTo(p2);
            double ang = angleBetween + Math.PI / 2; 
            Point centre = mid.moveTo(ang, distToCentre);
            return Circle.getCircle(centre, radius);
        }
    }
    public static int coverage(Circle c, Point[] arr) {
        int val = 0;
        for (int i = 0; i < arr.length; i ++) {
            if (c.contains(arr[i])) {
                val ++;
            } 
        }
        return val;
    }
    public static int DiscCoverage(Point[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length - 1; i ++) {
            int overlap = 0;
            Point p1 = arr[i];
            for (int j = i + 1; j < arr.length; j ++) {
                Point p2 = arr[j];
                Circle c = createCircle(p1, p2, 1);
                Circle c2 = createCircle(p2, p1, 1);
                if (p1.distBetween(p2) <= 2 && c != null) {
                    int num1 = coverage(c, arr);
                    int num2 = coverage(c2, arr);
                    overlap = Math.max(num1, num2);
                }
                max = Math.max(max, overlap);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numofpoints = sc.nextInt();
        Point[] arrPoint = new Point[numofpoints];
        for (int i = 0; i < numofpoints; i ++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            arrPoint[i] = new Point(x, y);
        }
        System.out.println("Maximum Disc Coverage: " +
                DiscCoverage(arrPoint));
    }
}
