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
	
	/* JTable or InvTable? */
	public JTable createInventoryTable() {
	  return new InvTable(inventory);
	}

}
