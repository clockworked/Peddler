
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    JLabel caption = new JLabel("Select a town:");
    add(caption);
    townsGrid = new TownsGrid(map.roads, townButtons);
    townsGrid.setPreferredSize(new Dimension(game.WIDTH, game.HEIGHT-caption.getHeight()));
    townsGrid.setLayout(new GridBagLayout());
    townsGrid.setOpaque(false);
    add(townsGrid);
    for (Point p : map.towns.keySet()) {
      Town t = map.towns.get(p);
      JButton b = new JButton();
      b.setActionCommand(t.name);
      Icon nodeIcon = new ImageIcon("node.png");
      b.setIcon(nodeIcon);
      b.setPreferredSize(new Dimension(nodeIcon.getIconWidth(), nodeIcon.getIconHeight()));
      b.setToolTipText(t.name);
      b.addActionListener(this);
      game.addPanel(t.name, new TownMenu(game, t));
      townsGrid.add(b, new GridBagConstraints(p.x, p.y, 1, 1, 0, 0, GridBagConstraints.NORTHWEST,
                    GridBagConstraints.NONE, new Insets(20,20,20,20), 0, 0));
      townButtons.put(t, b);
    }
  }
  public void actionPerformed(ActionEvent e) {
    String townName = e.getActionCommand();
    game.setActivePanel(townName);
  }

}