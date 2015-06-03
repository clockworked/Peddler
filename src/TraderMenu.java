// Trader Menu will be used for bartering with merchants.
// TODO: Set up for multiple Traders. When Trader is clicked, pass in trader and player character to display.
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TraderMenu extends JPanel {
	private Game game;
	private JPanel rightPanel, leftPanel;
	private JPanel actionPanel;
	private JPanel entranceMenu;
	private JButton tradeButton, talkButton, backButton;
	private JTable playerInvTable, traderInvTable, playerOfferTable, traderOfferTable; 
	private JScrollPane playerInventoryScroller, traderInventoryScroller;
	private JButton playerAddButton, traderAddButton;
	private int inventoryBufferSize, inventoryWidth, inventoryHeight;
	private ArrayList<ItemStack> playerOffer, traderOffer;

	/* I don't know what you're doing with horizontal glue, rigid areas, etc.
	 * I won't cut them for now but they seem like a headache. */
	public TraderMenu(Game game, int width, int height) {
		this.game = game;
		JPanel currentTownPanel = game.getPanel(game.currentTown.name);
		game.prevPanels.push(currentTownPanel);
		inventoryBufferSize = (int) (game.HEIGHT*.02);
		inventoryHeight = (int) (game.HEIGHT * .02);
		inventoryWidth = (int) (game.WIDTH*.035);
		setLayout(new BorderLayout());
		setAlignmentX(CENTER_ALIGNMENT);
		setPreferredSize(new Dimension(width, height));
		
		playerOffer = new ArrayList<ItemStack>();
		traderOffer = new ArrayList<ItemStack>();
		
		/* Create and Position Right Side Menus */
		leftPanel = constructLeft();
		rightPanel = constructRight();
		/* Create and Position Action Menu */
		actionPanel = constructActions();
		//add(Box.createRigidArea(new Dimension((int)(game.WIDTH*.05),inventoryBufferSize)));
		add(actionPanel, BorderLayout.SOUTH);
		//add(Box.createRigidArea(new Dimension((int)(game.WIDTH*.02),inventoryBufferSize)));
		//add(Box.createHorizontalGlue());
		add(leftPanel, BorderLayout.WEST);
		add(rightPanel, BorderLayout.EAST);
		//add(Box.createRigidArea(new Dimension((int)(game.WIDTH*.02),inventoryBufferSize)));
	}

	private JPanel constructActions() {
		actionPanel = new JPanel();
    actionPanel.setAlignmentY(BOTTOM_ALIGNMENT);
		// Adding Buttons
		actionPanel.setLayout(new BoxLayout(actionPanel,BoxLayout.Y_AXIS));
		//actionPanel.add(Box.createRigidArea(new Dimension((int)(game.WIDTH*.01),(int)(game.HEIGHT * .80))));
		//actionPanel.add(Box.createVerticalGlue());
		entranceMenu = new JPanel();
		entranceMenu.setOpaque(false);
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
          //backPanel();
        }
      }
    );
	  entranceMenu.add(backButton);
	  actionPanel.add(entranceMenu);
		return actionPanel;
	}

	/* This constructs the right side panel including the player and trader inventory */
	private JPanel constructRight() {
		rightPanel = new JPanel();
		//inventoryPanel.add(Box.createRigidArea(new Dimension(0,inventoryBufferSize*3)));
		rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
		playerInvTable = game.player.createInventoryTable();
		playerInventoryScroller = new JScrollPane(playerInvTable);
		rightPanel.add(new JLabel("Your Inventory"));
		rightPanel.add(playerInventoryScroller);
		playerAddButton = new JButton("Add");
		playerAddButton.setEnabled(false);
		playerAddButton.addActionListener(new ActionListener() {
		  @Override
		  public void actionPerformed(ActionEvent e) {
		    ItemStack i = game.player.inventory.get(playerInvTable.getSelectedRow());
		    if (!playerOfferContains(i)) {
		      playerOffer.add(i);
		      updatePlayerOffer();
		    }
		  }
		});
    playerInvTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        playerAddButton.setEnabled(playerInvTable.getSelectedRows().length > 0);
      }
    });
		rightPanel.add(playerAddButton);
		
		//inventoryPanel.add(Box.createRigidArea(new Dimension(0,inventoryBufferSize)));
		rightPanel.add(new JLabel("Trader Inventory"));
		traderInvTable = game.currentTown.trader.createInventoryTable();
		traderInventoryScroller = new JScrollPane(traderInvTable);
		rightPanel.add(traderInventoryScroller);
		traderAddButton = new JButton("Add");
		traderAddButton.setEnabled(false);
    traderAddButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ItemStack i = game.currentTown.trader.inventory.get(traderInvTable.getSelectedRow());
        if (!traderOfferContains(i)) {
          traderOffer.add(i);
          updateTraderOffer();
        }
      }
    });
    rightPanel.add(traderAddButton);
    traderInvTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        traderAddButton.setEnabled(traderInvTable.getSelectedRows().length > 0);
      }
    });
		//inventoryPanel.add(Box.createRigidArea(new Dimension(0,inventoryBufferSize*3)));
		// sizing does not work.....
		//right.setPreferredSize(new Dimension(inventoryWidth,inventoryHeight));
		rightPanel.setMinimumSize(new Dimension(inventoryWidth,inventoryHeight));
		return rightPanel;
	}
	
	private void updatePlayerOffer() {
    playerOfferTable.setModel(InvTable.modelFromInventory(playerOffer));
  }

  private void updateTraderOffer() {
    traderOfferTable.setModel(InvTable.modelFromInventory(traderOffer));
  }

  private JPanel constructLeft() {
    leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    playerOfferTable = new InvTable(playerOffer);
    traderOfferTable = new InvTable(traderOffer);
    leftPanel.add(new JScrollPane(playerOfferTable));
    leftPanel.add(new JScrollPane(traderOfferTable));
    leftPanel.setPreferredSize(new Dimension(300, 200));
    return leftPanel;
	}

	/*public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}*/

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
	
	/* Check name, quality, not quantity */
	public boolean playerOfferContains(ItemStack item) {
	  for (ItemStack i : playerOffer) {
	    if ((i.getName().equals(item.getName())) && (i.getQuality().equals(item.getQuality()))) {
	      return true;
	    }
	  }
	  return false;
	}
	
	/* Check name, quality, not quantity */
  public boolean traderOfferContains(ItemStack item) {
    for (ItemStack i : traderOffer) {
      if ((i.getName().equals(item.getName())) && (i.getQuality().equals(item.getQuality()))) {
        return true;
      }
    }
    return false;
  }

}
