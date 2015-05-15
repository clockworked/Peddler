public class ItemStack {
	private Commodity type;
	private int quantity;
	private String quality;		// GOOD, AVERAGE, POOR. Currently unsure of how this will work when adding new items... take lowest quality?

	public ItemStack(Commodity t, int c, String q) {
		this.type = t;
		this.quantity = c;
		this.quality = q;
	}
	
	public void setQuantity(int i) {
		this.quantity = i;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public String getName() {
		return this.type.getName();
	}
	
	public String getQuality() {
		return this.quality;
	}
	
	public int getPrice() {
		return this.type.getBase();
	}
}
