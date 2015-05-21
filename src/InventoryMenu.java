import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class InventoryMenu extends JPanel {
  private final Game game;
  public InventoryMenu (final Game game, int width, int height) {
    super();
    this.game = game;
    setAlignmentX(CENTER_ALIGNMENT);
    setPreferredSize(new Dimension(width, height));
    
    add(new JLabel("inventory lol"));
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        game.prevPanel();
      }
    });
    add(closeButton);
  }

}
