class CakeRecipe {
  double flour, sugar, eggs, butter, milk; // weights in ounces
  
  CakeRecipe(double flour, double sugar, double eggs, double butter, double milk) {
    if (flour != sugar) {
      throw new IllegalArgumentException("Weight of flour must be equal to weight of sugar.");
    }
    if (eggs != butter) {
      throw new IllegalArgumentException("Weight of eggs must be equal to weight of butter.");
    }
    if (eggs + milk != sugar) {
      throw new IllegalArgumentException("Weight of eggs plus the weight of milk must equal the weight of sugar.");
    }
    this.flour = flour;
    this.sugar = sugar;
    this.eggs = eggs;
    this.butter = butter;
    this.milk = milk;
  }
  
  CakeRecipe(double flour, double eggs, double milk) {
    this(flour, flour, eggs, eggs, milk);
  }
  
  CakeRecipe(double flour, double eggs, double milk, boolean areVolumes) {
    this(flour, eggs, milk);
    
    if (areVolumes) {
      this.flour = flour * 4.25;
      this.sugar = flour * 7;
      this.eggs = eggs * 1.75;
      this.butter = eggs * 8;
      this.milk = milk * 8;
    }
  }
  
  boolean sameRecipe(CakeRecipe other) {
    return this.flour - other.flour < .001 &&
        this.sugar - other.sugar < .001 &&
        this.eggs - other.eggs < .001 &&
        this.butter - other.butter < .001 &&
        this.milk - other.milk < .001;
  }
}
