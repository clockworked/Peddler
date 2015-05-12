

public class Commodity {
  private String name;
  private int basePrice;
  private int minPrice, maxPrice;
  private final double DEFAULT_MIN_MULTIPLIER = 0.25;
  private final double DEFAULT_MAX_MULTIPLIER = 3.00;
  public Commodity(String name, int basePrice) {
    this.name = name;
    this.basePrice = basePrice;
    this.minPrice = (int)(basePrice*DEFAULT_MIN_MULTIPLIER);
    this.maxPrice = (int)(basePrice*DEFAULT_MAX_MULTIPLIER);
  }
}
