
import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Map {
  public int width, height;
  public Hashtable<Point, Town> towns;
  public ArrayList<Road> roads;
  public Map(Game game) {
    width = height = 10;
    Random RNG = new Random();
    towns = new Hashtable<Point, Town>();
    roads = new ArrayList<Road>();
    for (int i=1; i<=6; i++) {
      Point p=new Point();
      Town t=null;
      do {
        p.x = RNG.nextInt(width);
        p.y = RNG.nextInt(height);
        t = new Town(String.format("Town %d", i), p.x, p.y);
      } while (towns.containsKey(p));
      towns.put(p, t);
    }
    double roadChance = 0.5;
    for (Point p : towns.keySet()) {
      Town t1 = towns.get(p);
      for (Point q : towns.keySet()) {
        Town t2 = towns.get(q);
        if (t1 != t2) {
          Road r = new Road(t1,t2);
          if (RNG.nextDouble() < roadChance) {
            if (!roads.contains(r)) {
              roads.add(r);
            }
          }
        }
      }
    }

  }
}
