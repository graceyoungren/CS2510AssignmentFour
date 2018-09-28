import tester.*;

interface IEntertainment {
  // compute the total price of this Entertainment
  double totalPrice();

  // computes the minutes of entertainment of this IEntertainment
  int duration();

  // produce a String that shows the name and price of this IEntertainment
  String format();

  // is this IEntertainment the same as that one?
  boolean sameEntertainment(IEntertainment that);
}

abstract class AEntertainment implements IEntertainment {
  String name;
  double price; // represents price per issue
  int installments; // number of issues per year

  AEntertainment(String name, double price, int installments) {
    this.name = name;
    this.price = price;
    this.installments = installments;
  }

  // computes the price of a yearly subscription to this Magazine
  public double totalPrice() {
    return this.price * this.installments;
  }

  // produce a String that shows the name and price of this Magazine
  public String format() {
    return this.name + ", $" + this.price + ".";
  }
  
  public int duration() {
    return 50 * this.installments;
  };
  
  public abstract boolean sameEntertainment(IEntertainment that);
}

class Magazine extends AEntertainment {
  String genre;
  int pages;

  Magazine(String name, double price, String genre, int pages, int installments) {
    super(name, price, installments);
    this.genre = genre;
    this.pages = pages;
  }

  // computes the minutes of entertainment of this Magazine, (includes all
  // installments)
  @Override
  public int duration() {
    return 5 * this.pages * this.installments;
  }

  // is this Magazine the same as that IEntertainment?
  public boolean sameEntertainment(IEntertainment that) {
    return true;
  }

}

class TVSeries extends AEntertainment {
  String corporation;

  TVSeries(String name, double price, int installments, String corporation) {
    super(name, price, installments);
    this.corporation = corporation;
  }

  // is this TVSeries the same as that IEntertainment?
  public boolean sameEntertainment(IEntertainment that) {
    return true;
  }
}

class Podcast extends AEntertainment {

  Podcast(String name, double price, int installments) {
    super(name, price, installments);
  }

  // is this Podcast the same as that IEntertainment?
  public boolean sameEntertainment(IEntertainment that) {
    return true;
  }
}

class ExamplesEntertainment {
  IEntertainment rollingStone = new Magazine("Rolling Stone", 2.55, "Music", 60, 12);
  IEntertainment houseOfCards = new TVSeries("House of Cards", 5.25, 13, "Netflix");
  IEntertainment serial = new Podcast("Serial", 0.0, 8);

  IEntertainment rollingStone2 = new Magazine("Rolling Stone2", 3, "Music", 72, 15);
  IEntertainment houseOfCards2 = new TVSeries("House of Cards2", 7.25, 10, "Netflix");
  IEntertainment serial2 = new Podcast("Serial2", 2.5, 5);

  // testing total price method
  boolean testTotalPrice(Tester t) {
    return t.checkInexact(this.rollingStone.totalPrice(), 2.55 * 12, .0001)
        && t.checkInexact(this.houseOfCards.totalPrice(), 5.25 * 13, .0001)
        && t.checkInexact(this.serial.totalPrice(), 0.0, .0001)
        && t.checkInexact(this.rollingStone2.totalPrice(), 45.0, .0001)
        && t.checkInexact(this.houseOfCards2.totalPrice(), 72.5, .0001)
        && t.checkInexact(this.serial2.totalPrice(), 12.5, .0001);
  }

}