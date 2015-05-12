package peddler;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

public class TownMenu extends JPanel {
  private Game game;
  private Town town;
  private JMenuBar menuBar;
  private JButton backButton;
  private JButton tavernButton;
  private JButton traderButton;
  public TownMenu(Game game, Town town) {
    this.game = game;
    this.town = town;
    setAlignmentX(CENTER_ALIGNMENT);
    setPreferredSize(new Dimension(game.WIDTH, game.HEIGHT));
    add(new JLabel(town.name));
    backButton = new JButton("Back");
    backButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          showMap();
        }
      }
    );
    add(backButton);
    tavernButton = new JButton("Tavern");
    tavernButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.out.println("tavern...");//showMap();
        }
      }
    );
    add(tavernButton);
    traderButton = new JButton("Trader");
    traderButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.out.println("trader...");//showMap();
        }
      }
    );
    add(traderButton);
  }
  public void showMap() {
    game.setActivePanel("Map");
  }
  
}
