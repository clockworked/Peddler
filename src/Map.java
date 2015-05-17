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
  private Game game;
  public Map(Game game) {
    this.game = game;
    RNG = new Random();
    towns = new ArrayList<Town>();
    roads = new ArrayList<Road>();
    width = DEFAULT_WIDTH;
    height = DEFAULT_HEIGHT;
    townsByLocation = new Hashtable<Point, Town>();
    for (int i=1; i<=NUM_TOWNS; i++) {
      Point p = findEmptyTile();
      Town t = new Town(String.format("Town %d", i), p.x, p.y);
      addTown(t);
    }
    addRoads();
  }
  public void addRoads() {
    ArrayList<Town> connectedNodes = new ArrayList<Town>();
    connectedNodes.add(towns.get(RNG.nextInt(towns.size())));
    for (Town t : towns) {
      connectNode(t);
    }
  }
  
  private void connectNode(Town t) {
    if (towns.size() == 1) {
      return;
    }
    while (true) {
      Town t2 = towns.get(RNG.nextInt(towns.size()));
      if (t != t2) {
        if (roadExists(t, t2)) {
          return;
        } else {
          roads.add(new Road(t, t2));
          return;
        }
      }
    }
  }
  
  public boolean roadExists(Town t1, Town t2) {
    return roadExists(new Road(t1, t2));
  }
  
  public boolean roadExists(Road r) {
    for (Road r2 : roads) {
      if (r.equals(r2)) {
        return true;
      }
    }
    return false;
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
  public void addTown(Town t) {
    towns.add(t);
    Point p = new Point(t.x, t.y);
    townsByLocation.put(p, t);
    game.addPanel(t.name, new TownMenu(game, t));
    if (towns.size() > 1) {
      connectNode(t);
    }
  }
}
