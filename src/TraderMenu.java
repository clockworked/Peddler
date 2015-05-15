// Trader Menu will be used for bartering with merchants.
// TODO: Set up for multiple Traders. When Trader is clicked, pass in trader and player character to display.
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TraderMenu extends JPanel implements ActionListener {
	private Game game;
	private JPanel inventoryPanel;
	private JTextArea playerInventory;
	private JTextArea traderInventory;
	private JScrollPane playerInventoryScroller;
	private JScrollPane traderInventoryScroller;
	private int inventoryBufferSize,inventoryWidth,inventoryHeight;
	private TestTown currentTown;


	public TraderMenu(Game game) {
		this.game = game;
		this.inventoryBufferSize = (int) (game.HEIGHT*.02);
		this.inventoryHeight = (int) (game.HEIGHT * .02);
		this.inventoryWidth = (int) (game.WIDTH*.035);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setAlignmentX(RIGHT_ALIGNMENT);
		setPreferredSize(new Dimension(game.WIDTH, game.HEIGHT));
		// Create and Position Right Side Menus
		constructRight();
		this.add(Box.createRigidArea(new Dimension((int)(game.WIDTH*.65),inventoryBufferSize)));
		this.add(Box.createHorizontalGlue());
		this.add(inventoryPanel);
		this.add(Box.createRigidArea(new Dimension((int)(game.WIDTH*.02),inventoryBufferSize)));

	}

	// This constructs the right side panel including the player and trader inventory
	private void constructRight() {
		inventoryPanel = new JPanel();
		inventoryPanel.add(Box.createRigidArea(new Dimension(0,inventoryBufferSize*3)));
		inventoryPanel.setLayout(new BoxLayout(inventoryPanel,BoxLayout.Y_AXIS));
		playerInventory = new JTextArea();
		playerInventory.setEditable(false);
		playerInventory.setAlignmentX(Component.RIGHT_ALIGNMENT);
		playerInventoryScroller = new JScrollPane (playerInventory);
		inventoryPanel.add(playerInventoryScroller);
		inventoryPanel.add(Box.createRigidArea(new Dimension(0,inventoryBufferSize)));
		traderInventory = new JTextArea();
		traderInventory.setEditable(false);
		traderInventory.setAlignmentX(Component.RIGHT_ALIGNMENT);
		traderInventoryScroller = new JScrollPane (traderInventory);
		inventoryPanel.add(traderInventoryScroller);
		inventoryPanel.add(Box.createRigidArea(new Dimension(0,inventoryBufferSize*3)));
		// sizing does not work.....
		//right.setPreferredSize(new Dimension(inventoryWidth,inventoryHeight));
		inventoryPanel.setMinimumSize(new Dimension(inventoryWidth,inventoryHeight));

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
			playerInventory.append(e.getQuantity() + " " + e.getQuality() + " Quality " + e.getName()+ " \t " + e.getPrice()+ " Rupees\n");
		}
	}
	
	public void setTraderInventory(Character t) {
		traderInventory.setText("Trader Inventory\n\n");
		for (ItemStack e : t.getInventory()) {
			traderInventory.append(e.getQuantity() + " " + e.getQuality() + " Quality " + e.getName()+ " \t " + e.getPrice()+ " Rupees\n");
		}
	}
}
