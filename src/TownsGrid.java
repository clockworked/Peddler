import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TownsGrid extends JPanel {
  private ArrayList<Road> roads;
  private Hashtable<Town, JLabel> townButtons;
  public JPanel[][] tiles;
  public TownsGrid(ArrayList<Road> roads, Hashtable<Town, JLabel> townButtons) {
    this.roads = roads;
    this.townButtons = townButtons;
  }
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    Graphics2D g2 = (Graphics2D)g;
    for (Road r : roads) {
      JPanel t1 = tiles[r.t1.x][r.t1.y];
      JPanel t2 = tiles[r.t2.x][r.t2.y];
      int x1 = t1.getX() + t1.getWidth()/2;
      int y1 = t1.getY() + townButtons.get(r.t1).getY() + townButtons.get(r.t1).getHeight()/2;
      //int y1 = t1.getY() + t1.getHeight()/2;
      int x2 = t2.getX() + t2.getWidth()/2;
      int y2 = t2.getY() + townButtons.get(r.t2).getY() + townButtons.get(r.t2).getHeight()/2;
      //int y2 = t2.getY() + t2.getHeight()/2;
      g2.setColor(Color.BLACK);
      g2.setStroke(new BasicStroke(4));
      g2.drawLine(x1, y1, x2, y2);
    }
  }
}
