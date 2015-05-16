import java.awt.Point;

public class Road {
  public Town t1, t2;
  public Road(Town t1, Town t2) {
    this.t1 = t1;
    this.t2 = t2;
  }
  
  public boolean equals(Road r) {
    if ((t1 == r.t1) && (t2 == r.t2)) {
      return true;
    } else if ((t1 == r.t2) && (t2 == r.t1)) {
      return true;
    } else {
      return false;
    }
  }
  
  public boolean sharesEndpoint(Road r) {
    return (t1 == r.t1 || t1 == r.t2 || t2 == r.t1 || t2 == r.t2);
  }
  
  public boolean intersects(Road r) {
    /* v1 and v2 indicate whether the respective line is vertical.
     * We'll need to do other calculations if so. */
    boolean v1=false, v2=false;
    
    Point p1 = new Point(t1.x, t1.y);
    Point q1 = new Point(t2.x, t2.y);
    v1 = (p1.x==q1.x);
    double s1 = (double)(q1.y-p1.y)/(q1.x-p1.x);
    
    Point p2 = new Point(r.t1.x, r.t1.y);
    Point q2 = new Point(r.t2.x, r.t2.y);
    double s2 = (double)(q2.y-p2.y)/(q2.x-p2.x);
    v2 = (p2.x==q2.x);
    
    //System.out.printf("vertical? %s, %s\n", v1, v2);
    
    /* Solve p1 + n*mySlope = p2 + m*rSlope. They intersect if
     * p1.x + n = p2.x + m, and p1.y + n*mySlope = p2.y + m*rSlope). */
    
    if ((v1 && v2) || (s1 == s2) || (s2 != 0 && s1 == -1/s2)) {
      // Roads are parallel!
      // Need to account for roads going directly into each other. Later.
      System.out.printf("(%s -> %s) & (%s -> %s) => no_p\n", t1, t2, r.t1, r.t2);
      return false;
    } else if (v1) {
      double n = p2.y - p1.y + (p1.x - p2.x)*s2;
      double ratio = n/(q1.y-p1.y);
      if ((ratio<0) || (ratio>1)) {
        System.out.printf("(%s -> %s) & (%s -> %s) => no_v\n", t1, t2, r.t1, r.t2);
        return false;
      } else {
        System.out.printf("(%s -> %s) & (%s -> %s) => yes_v\n", t1, t2, r.t1, r.t2);  
        return true;
      }
    } else if (v2) {
      return r.intersects(this);
    } else if (s1 == 0) {
      //double n = (p1.y - p2.y)/s2;
      double n = (p1.y - p2.y)/s2 + p2.x;
      double ratio = n/(q2.x - p2.x);
      if (ratio<0 || ratio>1) {
        System.out.printf("(%s -> %s) & (%s -> %s) => no_h\n", t1, t2, r.t1, r.t2);
        return false;
      } else {
        System.out.printf("(%s -> %s) & (%s -> %s) => yes_h\n", t1, t2, r.t1, r.t2);
        return true;
      }
    } else if (s2 == 0) {
      return r.intersects(this);
    } else {
      double m = ((p2.y/s1) - (p1.y/s1) + p1.x - p2.x)/(1 - s2/s1);
      double n = p2.x - p1.x + m;
      double r1 = m/(q2.x - p2.x);
      double r2 = n/(q1.x-p1.x);
      if (r1<0 || r1>1 || r2<0 || r2>1) {
        System.out.printf("(%s -> %s) & (%s -> %s) => no\n", t1, t2, r.t1, r.t2);
        return false;
      } else {
        System.out.printf("(%s -> %s) & (%s -> %s) => yes", t1, t2, r.t1, r.t2);
        System.out.printf("((s1=%f, s2=%f, m=%f, n=%f, ratio=%f))\n", s1, s2, m, n, r2);
        return true;
      }
    }
  }
  
  public String toString() {
    return String.format("Road<%s, %s>", t1, t2);
  }
}