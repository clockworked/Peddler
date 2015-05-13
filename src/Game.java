
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {
  public final int WIDTH=800, HEIGHT=600;
  private Player player;
  private Map map;
  private MapMenu mapMenu;
  private TraderMenu traderMenu;
  private JPanel activePanel;
  private HashMap<String, JPanel> panels;
  
  public static void main(String[] args) {
    Game me = new Game();
  }
  
  public Game() {
    setSize(WIDTH, HEIGHT);
    setVisible(true);
    panels = new HashMap<String, JPanel>();
    map = new Map(this);
    mapMenu = new MapMenu(this, map);
    traderMenu = new TraderMenu(this);
    // TODO: make "New Game" Feature that asks for player name.
    playerCreation();
    
    addPanel("Trader",traderMenu);
    panels.put("Map", mapMenu);
    setActivePanel("Map");
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) { System.exit(0); }
    });
  }
  
  // Creates the Player Character. Currently using for testing inventory
  private void playerCreation() {
	  player = new Player("JackJohn");
	  Commodity potatoes = new Commodity("Potatoes",15);
	  player.addStack(new ItemStack(potatoes,100,"Average"));
	
}
  
  public Player getPlayer(){
	  return this.player;
  }

public void setActivePanel(String key) {
    if (activePanel != null) {
      getContentPane().remove(activePanel);
    }
    getContentPane().add(panels.get(key));
    activePanel = panels.get(key);
    pack();
    repaint();
  }
  
  public void addPanel(String key, JPanel panel) {
    panels.put(key, panel);
  }

  // Before we transition to the trader window, prepare the inventory and the trader to display
  public void setTrader(){		// TODO: Add trader so we can access their inventory.
  this.traderMenu.setPlayerInventory(player);
		
		
  }
}
