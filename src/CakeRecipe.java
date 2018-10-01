import tester.*;

// represents all CakeRecipes
class CakeRecipe {
  double flour, sugar, eggs, butter, milk; // weights in ounces
  
  // constructor that represents as the amount of flour, sugar, eggs, butter and milk in the recipe
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
  
  // constructor that only requires one to enter the amount of flour, eggs and milk
  CakeRecipe(double flour, double eggs, double milk) {
    this(flour, flour, eggs, eggs, milk);
  }
  
  // constructor that takes in the flour, eggs and milk 
  // as volumes rather than weights and a boolean flag areVolumes 
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
  
  // detects if the two CakeRecipes are the same
  boolean sameRecipe(CakeRecipe other) {
    return this.flour - other.flour < .001 &&
        this.sugar - other.sugar < .001 &&
        this.eggs - other.eggs < .001 &&
        this.butter - other.butter < .001 &&
        this.milk - other.milk < .001;
  }
}

// represents all types of CakeRecipes
class ExamplesCakeRecipe {
  CakeRecipe redVelvet = new CakeRecipe(5, 5, 2.5, 2.5, 2.5);
  CakeRecipe redVelvetChocoChip = new CakeRecipe(5, 5, 2.5, 2.5, 2.5);
  CakeRecipe chocolate = new CakeRecipe(22, 22, 11, 11, 11);
  
  // testing sameRecipe
  boolean testSameRecipe(Tester t) {
    return t.checkExpect(this.redVelvet.sameRecipe(this.chocolate), false)
        && t.checkExpect(this.redVelvet.sameRecipe(this.redVelvetChocoChip), true);
  }
}
