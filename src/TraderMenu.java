// Trader Menu will be used for bartering with merchants.
// TODO: Set up for multiple Traders. When Trader is clicked, pass in trader and player character to display.
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TraderMenu extends JPanel implements ActionListener {
	private Game game;
	private JTextArea playerInventory;
	private JTextArea traderInventory;
	private JScrollPane playerInventoryScroller;
	private JScrollPane traderInventoryScroller;

	public TraderMenu(Game game) {
		this.game = game;
		setPreferredSize(new Dimension(game.WIDTH, game.HEIGHT));
		setAlignmentX(CENTER_ALIGNMENT);
		// Set up Player & Trader Inventory Display
		playerInventory = new JTextArea(10,40);
		playerInventory.setEditable(false);
		playerInventoryScroller = new JScrollPane (playerInventory);
		add(playerInventoryScroller);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void printInventory() {
		System.out.println("Current Inventory: ");
		for (ItemStack e : game.getPlayer().getInventory()) {
			System.out.println(e.getQuantity() + " " + e.getName());
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

	}

	public void setPlayerInventory(Player player) {
		playerInventory.setText("Current Inventory\n\n");
		for (ItemStack e : game.getPlayer().getInventory()) {
			playerInventory.append(e.getQuantity() + " " + e.getQuality() + " Quality " + e.getName()+ "\t\t\t" + e.getPrice()+ " Rupees \n");
		}
		
	}

}
