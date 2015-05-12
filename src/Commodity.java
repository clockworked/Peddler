

public class Commodity {
  private String name;
  private int basePrice;
  private int minPrice, maxPrice;
  private final double DEFAULT_MIN_MULTIPLIER = 0.25;
  private final double DEFAULT_MAX_MULTIPLIER = 3.00;
  // Quantity for inventory management
  private int quantity;
  // Quality for perception skilling later on? GOOD, AVERAGE, LOW
  private String quality;
  // Weight for calculating inventory space
  private int weight;
  
  public Commodity(String name, int basePrice) {
    this.name = name;
    this.basePrice = basePrice;
    this.minPrice = (int)(basePrice*DEFAULT_MIN_MULTIPLIER);
    this.maxPrice = (int)(basePrice*DEFAULT_MAX_MULTIPLIER);
    this.quantity = 0;
    this.quality = "Average";
  }
  
  public int getBase(){
	  return this.basePrice;
  }
  
  public int minPrice(){
	  return this.minPrice;
  }
  
  public int maxPrice(){
	  return this.maxPrice;
  }
  
  public int getQuantity(){
	  return this.quantity;
  }
  
  public String getQuality(){
	  return this.quality;
  }
  
  public int getWeight(){
	  return this.weight;
  }
  
}
