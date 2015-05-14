import java.awt.Point;

public class Road {
  public Town t1, t2;
  public Road(Town t1, Town t2) {
    this.t1 = t1;
    this.t2 = t2;
  }
  
  public boolean intersects(Road r) {
    try {
      Point p1 = new Point(t1.x, t1.y);
      Point q1 = new Point(t2.x, t2.y);
      double s1 = (double)(q1.y-p1.y)/(q1.x-p1.x);
      
      Point p2 = new Point(r.t1.x, r.t1.y);
      Point q2 = new Point(r.t2.x, r.t2.y);
      double s2 = (double)(q2.y-p2.y)/(q2.x-p2.x);
      
      /* Solve p1 + n*mySlope = p2 + m*rSlope. They intersect if
       * p1.x + n = p2.x + m, and p1.y + n*mySlope = p2.y + m*rSlope). */
      double m = ((p2.y/s1) - (p1.y/s1) + p1.x - p2.x)/(1 - s2/s1);
      double n = p2.x - p1.x + m;
      if (Math.abs(n) > Math.abs(q1.x - p1.x) || (n < 0)) {
        //System.out.println("no");
        return false;
      } else if (Math.abs(m) > Math.abs(q2.x - p2.x) || (m < 0)) {
        //System.out.println("no2");
        return false;
      }
      return true;
    } catch (ArithmeticException e) {
      System.out.println("help");
      return false;
    }
  }
}
