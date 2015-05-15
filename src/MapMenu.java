import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MapMenu extends JPanel implements ActionListener {
  private Game game;
  private Map map;
  private TownsGrid townsGrid;
  private Hashtable<Town, JButton> townButtons;
  public MapMenu(Game game, Map map) {
    this.game = game;
    this.map = map;
    townButtons = new Hashtable<Town, JButton>();
    setPreferredSize(new Dimension(game.WIDTH, game.HEIGHT));
    setAlignmentX(CENTER_ALIGNMENT);
    setLayout(new BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));
    JLabel caption = new JLabel("Select a town:");
    add(caption);
    townsGrid = new TownsGrid(map.roads, townButtons);
    //townsGrid.setPreferredSize(new Dimension(game.WIDTH*3/4, game.HEIGHT*9/10));
    townsGrid.setLayout(new GridLayout(map.height, map.width, 0, 0));
    townsGrid.tiles = new JPanel[map.width][map.height];
    for (int y=0; y<map.height; y++) {
      for (int x=0; x<map.width; x++) {
        JPanel tile = new JPanel();
        townsGrid.tiles[x][y] = tile;
        tile.setOpaque(false);
        //tile.setBackground(new Color(192,192,192,255));
        //tile.setBorder(BorderFactory.createLineBorder(new Color(128,128,128,255)));
        tile.setAlignmentX(CENTER_ALIGNMENT);
        tile.setAlignmentY(CENTER_ALIGNMENT);
        tile.setVisible(true);
        townsGrid.add(tile);
        //tile.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0,0,0,64)));
      }
    }
    townsGrid.setOpaque(false);
    add(townsGrid);
    for (Point p : map.townsByLocation.keySet()) {
      Town t = map.townsByLocation.get(p);
      JButton b = new JButton(t.name, new ImageIcon("node.png"));
      b.setActionCommand(t.name);
      b.setHorizontalTextPosition(SwingConstants.CENTER);
      b.setVerticalTextPosition(SwingConstants.BOTTOM);
      b.setOpaque(false);
      b.setBackground(new Color(0,0,0,0));
      b.setBorder(null);
      b.addActionListener(this);
      townsGrid.tiles[p.x][p.y].add(b);
      //System.out.printf("%s (%d %d)\n", t.name, p.x, p.y);
      townButtons.put(t, b);
    }
  }
  public void actionPerformed(ActionEvent e) {
    String townName = e.getActionCommand();
    game.setActivePanel(townName);
  }

}