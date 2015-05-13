// Character Class will keep name, location, and inventory information for NPCS and the PC.

public class Character {
	private Commodity[] inventory;
	private String name;
	
	public Character(String Name){
		this.name = Name;
		inventory = new Commodity[50];
		
	}

}
