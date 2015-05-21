import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {
  public final int WIDTH=800, HEIGHT=600;
  public final int PLAYER_CASH = 1000;
  public Player player;
  public Map map;
  private MapMenu mapMenu;
  private TraderMenu traderMenu;
  private StatusMenu statusMenu;
  private InventoryMenu inventoryMenu;
  
  private JPanel activePanel, prevPanel;
  private HashMap<String, JPanel> panels;
  private TestTown currentTown;					// Currently selected Town
  
  public static void main(String[] args) {
    Game me = new Game();
  }
  
  public Game() {
    setSize(WIDTH, HEIGHT);
    setVisible(true);
    panels = new HashMap<String, JPanel>();
    map = new Map(this);
    Point p = map.findEmptyTile();
	  currentTown = new TestTown(p.x, p.y);			// For testing purposes, we're always in the same town.
	  map.addTown(currentTown);
    // TODO: make "New Game" Feature that asks for player name.
    playerCreation();
    mapMenu = new MapMenu(this, map, WIDTH, (int)(0.8*HEIGHT));
    traderMenu = new TraderMenu(this, WIDTH, (int)(0.8*HEIGHT));
    inventoryMenu = new InventoryMenu(this, WIDTH, (int)(0.8*HEIGHT));
    statusMenu = new StatusMenu(this, WIDTH, (int)(0.2*HEIGHT));
    
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(statusMenu, BorderLayout.SOUTH);
    addPanel("Trader",traderMenu);
    addPanel("Map", mapMenu);
    addPanel("Inventory", inventoryMenu);
    setActivePanel("Map");
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) { System.exit(0); }
    });
  }
  
  // Creates the Player Character. Currently using for testing inventory
  public void playerCreation() {
	  player = new Player("JackJohn", this.currentTown, PLAYER_CASH);
	  Commodity potatoes = new Commodity("Potatoes", 15);
	  player.addStack(new ItemStack(potatoes, 100, "Average"));
	  Commodity furs = new Commodity("Furs", 50);
	  player.addStack(new ItemStack(potatoes, 35, "Good"));
  }
  
  public Player getPlayer(){
	  return this.player;
  }
  
  public TestTown getCurrentTown(){
	  return this.currentTown;
  }
  

  public void setActivePanel(JPanel panel) {
    if (panel != activePanel) {
      prevPanel = activePanel;
      if (activePanel != null) {
        getContentPane().remove(activePanel);
      }
      getContentPane().add(panel, BorderLayout.CENTER);
      activePanel = panel;
      pack();
      repaint();
    }
  }

  public void setActivePanel(String key) {
    setActivePanel(panels.get(key));
  }
  
  public void prevPanel() {
    if (prevPanel==null) System.out.println("fux");
    setActivePanel(prevPanel);
    prevPanel = null;
  }
  
  public void addPanel(String key, JPanel panel) {
    panels.put(key, panel);
  }

  // Before we transition to the trader window, prepare the inventory and the trader to display  
  public void setTrader() {
    // TODO: Add trader so we can access their inventory.
    this.traderMenu.setPlayerInventory(player);		
    this.traderMenu.setTraderInventory(currentTown.getTrader());
  }
}
