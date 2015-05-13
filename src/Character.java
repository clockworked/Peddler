import java.util.ArrayList;

// Character Class will keep name, location, and inventory information for NPCS and the PC.

public class Character {
	private ArrayList<ItemStack> inventory;
	public String name;
	
	public Character(String Name){
		this.name = Name;
		inventory = new ArrayList<ItemStack>();
	}
	
	public ArrayList<ItemStack> getInventory() {
		return inventory;
	}
	
	public void addStack(ItemStack i){
		inventory.add(i);
	}

}
