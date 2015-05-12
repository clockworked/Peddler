
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TownsGrid extends JPanel {
  private ArrayList<Road> roads;
  private Hashtable<Town, JButton> townButtons;
  public TownsGrid(ArrayList<Road> roads, Hashtable<Town, JButton> townButtons) {
    this.roads = roads;
    this.townButtons = townButtons;
  }
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D)g;
    for (Road r : roads) {
      JButton b1 = townButtons.get(r.t1);
      JButton b2 = townButtons.get(r.t2);
      int x1 = b1.getX() + b1.getWidth()/2;
      int x2 = b2.getX() + b2.getWidth()/2;
      int y1 = b1.getY() + b1.getHeight()/2;
      int y2 = b2.getY() + b2.getHeight()/2;
      g2.setStroke(new BasicStroke(5));
      g2.drawLine(x1, y1, x2, y2);
      
    }
  }
}
