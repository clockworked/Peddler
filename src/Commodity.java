

public class Commodity {
  private String name;
  private int basePrice;
  private int minPrice, maxPrice;
  private final double DEFAULT_MIN_MULTIPLIER = 0.25;
  private final double DEFAULT_MAX_MULTIPLIER = 3.00;
  // Weight for calculating inventory space
  private int weight;
  
  public Commodity(String name, int basePrice) {
    this.name = name;
    this.basePrice = basePrice;
    this.minPrice = (int)(basePrice*DEFAULT_MIN_MULTIPLIER);
    this.maxPrice = (int)(basePrice*DEFAULT_MAX_MULTIPLIER);

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
  
  public int getWeight(){
	  return this.weight;
  }
  
  public String getName(){
	  return this.name;
  }
  
}
