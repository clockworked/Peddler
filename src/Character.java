import java.util.ArrayList;

// Character Class will keep name, location, and inventory information for NPCS and the PC.

public class Character {
	private ArrayList<ItemStack> inventory;
	public String name;
	public Town town;
	
	public Character(String name, Town town) {
		this.name = name;
		this.town = town;
		inventory = new ArrayList<ItemStack>();
	}
	
	public ArrayList<ItemStack> getInventory() {
		return inventory;
	}
	
	public void addStack(ItemStack i){
		inventory.add(i);
	}

}
