import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Map {
  public Hashtable<Point, Town> townsByLocation;
  public ArrayList<Town> towns;
  public ArrayList<Road> roads;
  private final int NUM_TOWNS = 4;
  private final int DEFAULT_WIDTH = 10, DEFAULT_HEIGHT = 7;
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
      Point p = findEmptyTile();
      Town t = new Town(String.format("Town %d", i), p.x, p.y);
      towns.add(t);
      townsByLocation.put(p, t);
      game.addPanel(t.name, new TownMenu(game, t));
    }

    addRoads();
  }
  public void addRoads() {
    double roadChance = 1;
    ArrayList<Town> connectedNodes = new ArrayList<Town>();
    connectedNodes.add(towns.get(RNG.nextInt(towns.size())));
    for (Town t1 : towns) {
      for (Town t2: towns) {
        if (t1 != t2) { 
          Road r = new Road(t1, t2);
          boolean roadExists = false;
          for (Road r2 : roads) {
            if (r.equals(r2)) {
              roadExists = true;
              break;
            }
          }
          if (!roadExists) {
            boolean crossesRoad = false;
            for (Road r2: roads) {
              if (!r.sharesEndpoint(r2)) {// && r.intersects(r2)) {
                crossesRoad = true;
              }
            }
            if (RNG.nextDouble() < roadChance) {
              roads.add(r);
              if (connectedNodes.contains(t2) && !connectedNodes.contains(t1)) {
                connectedNodes.add(t1);
              }
              if (connectedNodes.contains(t1) && !connectedNodes.contains(t2)) { 
                connectedNodes.add(t2);
              }
            }
          }
        }
      }
      if (!connectedNodes.contains(t1)) System.out.println(t1);
    }
  }
  
  // TODO: check if all tiles used up
  public Point findEmptyTile() {
    Point p=new Point();
    do {
      p.x = RNG.nextInt(width);
      p.y = RNG.nextInt(height);
    } while (townsByLocation.containsKey(p));
    return p;
  }
}
