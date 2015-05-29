import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/* Character Class will keep name, location, and inventory information
 * for NPCS and the PC. */

public class Character {
	public ArrayList<ItemStack> inventory;
	public int gold; // Generic currency!
	public String name;
	public Town town;
	
	public Character(String name, Town town, int gold) {
		this.name = name;
		this.town = town;
    this.gold = gold;
		inventory = new ArrayList<ItemStack>();
	}
	
	public Character(String name, Town town) {
	  this(name, town, 0);
	}
	
	public ArrayList<ItemStack> getInventory() {
		return inventory;
	}
	
	public void addStack(ItemStack i){
		inventory.add(i);
	}
	
	public JTable createInventoryTable() {
	  String[][] rows = new String[inventory.size()][4];

    for (int y=0; y<inventory.size(); y++) {
      ItemStack i = inventory.get(y);
      rows[y][0] = i.getName();
      rows[y][1] = ""+i.getQuantity();
      rows[y][2] = i.getQuality();
      rows[y][3] = ""+i.getPrice();
    }
    Object[] colNames = {"Name", "Quantity", "Quality", "Price"};
    JTable invTable = new JTable(rows, colNames);
    invTable.setShowGrid(true);
    invTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    return invTable;
	}

}
