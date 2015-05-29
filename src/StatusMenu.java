import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusMenu extends JPanel {
  private final Game game;
  private JLabel nameLabel, cashLabel;
  private JButton invButton;
  public StatusMenu(final Game game, int width, int height) {
    super();
    this.game = game;
    setPreferredSize(new Dimension(width, height));
    
    setVisible(true);
    /* TODO: make this pretty */
    setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createMatteBorder(6,0,0,0,new ImageIcon("border.png")),
      BorderFactory.createEmptyBorder(6,6,6,6)
    ));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setAlignmentY(LEFT_ALIGNMENT);
    nameLabel = new JLabel(game.player.name);
    add(nameLabel);
    cashLabel = new JLabel(String.format("%d gold", game.player.gold));
    add(cashLabel);
    invButton = new JButton("Inventory");
    invButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        game.setActivePanel("Inventory");
      }
    });
    
    add(invButton);
  }
  /* is this the right paint method? */
  public void paintComponent(Graphics g) {
    cashLabel.setText(String.format("%d gold", game.player.gold));
    super.paintComponent(g);
  }
}
