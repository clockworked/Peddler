import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Map {
  public Hashtable<Point, Town> townsByLocation;
  public ArrayList<Town> towns;
  public ArrayList<Road> roads;
  private final int NUM_TOWNS = 6;
  private final int DEFAULT_WIDTH = 6, DEFAULT_HEIGHT = 6;
  public int width, height;
  private Random RNG;
  public Map(Game game) {
    RNG = new Random();
    towns = new ArrayList<Town>();
    roads = new ArrayList<Road>();
    width = DEFAULT_WIDTH;
    height = DEFAULT_HEIGHT;
    townsByLocation = new Hashtable<Point, Town>();
    for (int i=1; i<=NUM_TOWNS; i++) {
      Point p=new Point();
      Town t=null;
      do {
        p.x = RNG.nextInt(width);
        p.y = RNG.nextInt(height);
        t = new Town(String.format("Town %d", i), p.x, p.y);
      } while (townsByLocation.containsKey(p));
      towns.add(t);
      townsByLocation.put(p, t);
    }

    for (Point p : townsByLocation.keySet()) {
      Town t1 = townsByLocation.get(p);
      int numConnections = RNG.nextInt(NUM_TOWNS - 1) + 1;
      int i=0;
      while (i < numConnections) {
        Town t2 = towns.get(RNG.nextInt(NUM_TOWNS));
        Road r = new Road(t1, t2);
        if (!(t1==t2) && !roads.contains(r)) {
          roads.add(r);
          for (Road r2: roads) {
            if (r != r2 && r.intersects(r2)) {
              if (r.t1 != r2.t1 && r.t1 != r2.t2 && r.t2 != r2.t1 && r.t2 != r2.t2) {
                //System.out.printf("%s->%s, %s->%s\n", r.t1, r.t2, r2.t1, r2.t2);
              }
            } else if (r != r2 && !(r.intersects(r2))) {
              //System.out.printf("%s->%s, %s->%s\n", r.t1, r.t2, r2.t1, r2.t2);
            }
          }
          i++;
        }
      }
    }
  }
}
