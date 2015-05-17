import java.util.Hashtable;

public class Town {
  public String name;
  public Hashtable<String,Character> characters;
  
  public int x, y;

  
  public Town(String name, int x, int y) {
    this.name = name;
    this.x = x;
    this.y = y;
  }
  public String toString() {
    return name;
  }
  
}
