import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {
  public final int WIDTH=800, HEIGHT=600;
  public final int PLAYER_CASH = 1000;
  public Player player;
  public Map map;
  public Stack<JPanel> prevPanels;
  public Town currentTown;
  
  private MapMenu mapMenu;
  private TraderMenu traderMenu;
  private StatusMenu statusMenu;
  private InventoryMenu inventoryMenu;
  
  private JPanel activePanel;
  private HashMap<String, JPanel> panels;
  
  public static void main(String[] args) {
    Game me = new Game();
  }
  
  public Game() {
    setSize(WIDTH, HEIGHT);
    setVisible(true);
    panels = new HashMap<String, JPanel>();
    prevPanels = new Stack<JPanel>();
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

  public void setActivePanel(JPanel panel) {
    if (panel != activePanel) {
      prevPanels.push(activePanel);
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
    if (prevPanels.size() > 0) {
      setActivePanel(prevPanels.pop());
    } else {
      System.out.println("(Error: no prev panel)");
    }
  }
  
  public void addPanel(String key, JPanel panel) {
    panels.put(key, panel);
  }
  
  public JPanel getPanel(String key) {
    return panels.get(key);
  }

  public void refreshTraderMenu() {
    traderMenu = new TraderMenu(this, WIDTH, (int)(0.8*HEIGHT));
  }
}
