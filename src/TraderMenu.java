// Trader Menu will be used for bartering with merchants.
// TODO: Set up for multiple Traders. When Trader is clicked, pass in trader and player character to display.
import java.awt.Dimension;
import java.util.Stack;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TraderMenu extends JPanel implements ActionListener {
	private Game game;
	private JPanel inventoryPanel;
	private JPanel actionPanel;
	private JPanel entranceMenu;
	private Stack<String> previousPanels;								// For back button use
	private JButton tradeButton;
	private JButton talkButton;
	private JButton backButton;
	private JTextArea playerInventory;
	private JTextArea traderInventory;
	private JScrollPane playerInventoryScroller;
	private JScrollPane traderInventoryScroller;
	private int inventoryBufferSize,inventoryWidth,inventoryHeight;
	private TestTown currentTown;

	public TraderMenu(Game game) {
		this.game = game;
		this.currentTown = game.getCurrentTown();
		this.previousPanels = new Stack<String>();
		this.previousPanels.push(currentTown.getName()); 				// This needs to push an identifier to return to the town map;
		this.inventoryBufferSize = (int) (game.HEIGHT*.02);
		this.inventoryHeight = (int) (game.HEIGHT * .02);
		this.inventoryWidth = (int) (game.WIDTH*.035);
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setAlignmentX(RIGHT_ALIGNMENT);
		setPreferredSize(new Dimension(game.WIDTH, game.HEIGHT));
		/* Create and Position Right Side Menus */
		constructRight();
		/* Create and Position Action Menu */
		constructActions();
		this.add(Box.createRigidArea(new Dimension((int)(game.WIDTH*.05),inventoryBufferSize)));
		this.add(actionPanel);
		actionPanel.setAlignmentY(BOTTOM_ALIGNMENT);
		this.add(Box.createRigidArea(new Dimension((int)(game.WIDTH*.02),inventoryBufferSize)));
		this.add(Box.createHorizontalGlue());
		this.add(inventoryPanel);
		this.add(Box.createRigidArea(new Dimension((int)(game.WIDTH*.02),inventoryBufferSize)));

	}

	private void constructActions() {
		actionPanel = new JPanel();
		// Adding Buttons
		actionPanel.setLayout(new BoxLayout(actionPanel,BoxLayout.Y_AXIS));
		actionPanel.add(Box.createRigidArea(new Dimension((int)(game.WIDTH*.01),(int)(game.HEIGHT * .80))));
		actionPanel.add(Box.createVerticalGlue());
		entranceMenu = new JPanel();
		tradeButton = new JButton("Trade");
		tradeButton.addActionListener(
	      new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          System.out.println("trade");
	        }
	      }
	    );
	    entranceMenu.add(tradeButton);
		talkButton = new JButton("Talk");
		talkButton.addActionListener(
	      new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          System.out.println("Talk");
	        }
	      }
	    );
	    entranceMenu.add(talkButton);
		backButton = new JButton("Back");
		backButton.addActionListener(
	      new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          System.out.println("Back");
	          backPanel();
	        }
	      }
	    );
	    entranceMenu.add(backButton);
	    actionPanel.add(entranceMenu);
		
	}

	/* This constructs the right side panel including the player and trader inventory */
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
	
	private void backPanel(){
		changePanel(previousPanels.pop());
	}
	
	private void changePanel(String s){
		if ( s.equals(currentTown.getName())){
			// TODO: This should change the active panel to the current town name
			game.setActivePanel("Town 1");							
		} 
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
