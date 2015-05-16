import java.util.Hashtable;

public class TestTown {
	public String name;
	public int x, y;
	private Hashtable<String,Character> characters;
	private Character trader;
	private Character bartender;
	

	public TestTown() {
		createTrader();
	}

	public String getName(){
		return this.name;
	}
	
	private void createTrader() {
		this.trader = new Character("Test Trader");
		this.characters = new Hashtable<String,Character>();
		Commodity ironOre = new Commodity("Iron Ore", 15);
		this.trader.addStack(new ItemStack(ironOre, 100, "Poor"));
		Commodity wheat = new Commodity("Stalks of Wheat", 50);
		this.trader.addStack(new ItemStack(wheat, 300, "Good"));
		characters.put("Hilda Sellingbottoms", this.trader);
	}
	
	public Character getCharacter(String n){
		return characters.get(n);
	}
	
	public Character getTrader(){
		return this.trader;
	}
	

}
