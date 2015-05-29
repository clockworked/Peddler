import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class InventoryMenu extends JPanel {
  private final Game game;
  private JTable invTable;
  public InventoryMenu (final Game game, int width, int height) {
    super();
    this.game = game;
    setAlignmentX(CENTER_ALIGNMENT);
    setPreferredSize(new Dimension(width, height));
    setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    JTable invTable = game.player.createInventoryTable();
    add(new JScrollPane(invTable));
    //add(new JLabel("inventory lol"));
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        game.prevPanel();
      }
    });
    add(closeButton);
  }

}
