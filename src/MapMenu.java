import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MapMenu extends JPanel {
  private Game game;
  private Map map;
  private TownsGrid townsGrid;
  private Hashtable<Town, JLabel> townButtons;
  public MapMenu(final Game game, final Map map, int width, int height) {
    this.game = game;
    this.map = map;
    townButtons = new Hashtable<Town, JLabel>();
    setPreferredSize(new Dimension(width, height));
    setAlignmentX(CENTER_ALIGNMENT);
    setLayout(new BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
    JLabel caption = new JLabel("Select a town:");
    add(caption);
    townsGrid = new TownsGrid(game, map.roads, townButtons);
    townsGrid.setLayout(new GridLayout(map.height, map.width, 0, 0));
    townsGrid.tiles = new JPanel[map.width][map.height];
    for (int y=0; y<map.height; y++) {
      for (int x=0; x<map.width; x++) {
        JPanel tile = new JPanel();
        townsGrid.tiles[x][y] = tile;
        tile.setOpaque(false);
        //tile.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,255)));
        tile.setAlignmentX(CENTER_ALIGNMENT);
        tile.setAlignmentY(CENTER_ALIGNMENT);
        //tile.setVisible(true);
        townsGrid.add(tile);
      }
    }
    townsGrid.setOpaque(false);
    add(townsGrid);
    for (Point p : map.townsByLocation.keySet()) {
      /* these are final for MouseAdapter weirdness */
      final MapMenu mm = this;
      final Town t = map.townsByLocation.get(p);
      final JLabel b = new JLabel(new ImageIcon("node.png"));
      b.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.DARK_GRAY, 3),
        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3)
      ));
      b.setHorizontalTextPosition(SwingConstants.CENTER);
      b.setVerticalTextPosition(SwingConstants.BOTTOM);
      b.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          if (game.player.town == t) {
            mm.showTownMenu(t);
          } else {
            if (map.roadExists(game.player.town, t)) {
              game.player.town = t;
              townsGrid.repaint();
            }
          }
        }
      });
      
      townsGrid.tiles[p.x][p.y].add(b);
      JLabel tn = new JLabel(t.name);
      tn.setAlignmentX(CENTER_ALIGNMENT);
      tn.setAlignmentY(CENTER_ALIGNMENT);
      tn.setHorizontalAlignment(SwingConstants.CENTER);
      townsGrid.tiles[p.x][p.y].add(tn);
      townsGrid.tiles[p.x][p.y].revalidate();
      townsGrid.tiles[p.x][p.y].repaint();
      //System.out.printf("%s (%d %d)\n", t.name, p.x, p.y);
      townButtons.put(t, b);
    }
  }
  public void showTownMenu(Town t) {
    String townName = t.name;
    game.setActivePanel(townName);
  }
  
  @Override
  public void paint(Graphics g) {
    for (Town t : map.towns) {
      /* these are final for MouseAdapter weirdness */
      JLabel b = townButtons.get(t);
      if (game.player.town == t) {
        b.setBorder(BorderFactory.createCompoundBorder(
          BorderFactory.createLineBorder(new Color(128,0,128), 3),
          BorderFactory.createLineBorder(new Color(255,0,255), 3)
        ));
      } else {
        b.setBorder(BorderFactory.createCompoundBorder(
          BorderFactory.createLineBorder(Color.DARK_GRAY, 3),
          BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3)
        ));
      }
    }
    super.paint(g);
  }
}