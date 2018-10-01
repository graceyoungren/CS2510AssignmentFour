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

  // returns true if this magazine is the same as m
  boolean sameMagazine(Magazine m);

  // returns true if this TVSerive is the same as tv
  boolean sameTVSeries(TVSeries tv);

  // returns true if this podcast is the same as p
  boolean samePodcast(Podcast p);
}

// represents the abstract class of entertainment
abstract class AEntertainment implements IEntertainment {
  String name;
  double price; // represents price per issue
  int installments; // number of issues per year

  // the constructor
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

  // return the total duration of this entertainment
  public int duration() {
    return 50 * this.installments;
  };

  // is this IEntertainment the same as that one?
  public abstract boolean sameEntertainment(IEntertainment that);

  // is this abstract IEntertainment the same as that one?
  public boolean sameAEntertainment(AEntertainment that) {
    return this.name == that.name && this.price == that.price && this.installments == that.installments;
  }

  // is this Magazine the same as that one?
  public boolean sameMagazine(Magazine m) {
    return false;
  }

  // is this TV Series the same as that one?
  public boolean sameTVSeries(TVSeries tv) {
    return false;
  }

  // is this Podcast the same as that one?
  public boolean samePodcast(Podcast p) {
    return false;
  }
}

// represents Magazine as a type of entertainment
class Magazine extends AEntertainment {
  String genre;
  int pages;

  // the constructor
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
    return that.sameMagazine(this);
  }

  // is this Magazine the same as that one?
  @Override
  public boolean sameMagazine(Magazine m) {
    return this.sameAEntertainment(m) && this.genre == m.genre && this.pages == m.pages;
  }
}

//represents TV Series as a type of entertainment
class TVSeries extends AEntertainment {
  String corporation;

  // the constructor
  TVSeries(String name, double price, int installments, String corporation) {
    super(name, price, installments);
    this.corporation = corporation;
  }

  // is this TVSeries the same as that IEntertainment?
  public boolean sameEntertainment(IEntertainment that) {
    return that.sameTVSeries(this);
  }

  // is this TVSeries the same as that one?
  @Override
  public boolean sameTVSeries(TVSeries tv) {
    return this.sameAEntertainment(tv) && this.corporation == tv.corporation;
  }
}

//represents Podcast as a type of entertainment
class Podcast extends AEntertainment {

  // the constructor
  Podcast(String name, double price, int installments) {
    super(name, price, installments);
  }

  // is this Podcast the same as that IEntertainment?
  public boolean sameEntertainment(IEntertainment that) {
    return that.samePodcast(this);
  }

  // is this Podcast the same as that one?
  @Override
  public boolean samePodcast(Podcast p) {
    return this.sameAEntertainment(p);
  }
}

// represents all types of entertainment 
class ExamplesEntertainment {
  IEntertainment rollingStone = new Magazine("Rolling Stone", 2.55, "Music", 60, 12);
  IEntertainment people = new Magazine("People", 2.99, "Gossip", 100, 30);
  IEntertainment peopleDup = new Magazine("People", 2.99, "Gossip", 100, 30);
  IEntertainment houseOfCards = new TVSeries("House of Cards", 5.25, 13, "Netflix");
  IEntertainment supergirl = new TVSeries("Supergirl", 6.0, 23, "Netflix");
  IEntertainment serial = new Podcast("Serial", 0.0, 8);
  IEntertainment slowBurn = new Podcast("Slow Burn", 1.99, 12);
  IEntertainment serialDup = new Podcast("Serial", 0.0, 8);

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
  
  // testing duration
  boolean testDuration(Tester t) {
    return t.checkExpect(this.rollingStone.duration(), 3600)
        && t.checkExpect(this.people.duration(), 15000)
        && t.checkExpect(this.houseOfCards.duration(), 650)
        && t.checkExpect(this.supergirl.duration(), 1150)
        && t.checkExpect(this.serial.duration(), 400)
       && t.checkExpect(this.serial2.duration(), 250);
  }
  // testing format
  boolean testFormat(Tester t) {
    return t.checkExpect(this.rollingStone.format(), "Rolling Stone, $2.55.")
        && t.checkExpect(this.people.format(), "People, $2.99.")
        && t.checkExpect(this.houseOfCards.format(), "House of Cards, $5.25.")
        && t.checkExpect(this.supergirl.format(), "Supergirl, $6.0.")
        && t.checkExpect(this.serial.format(), "Serial, $0.0.")
        && t.checkExpect(this.slowBurn.format(), "Slow Burn, $1.99.");
  }

  // testing sameEntertainment
  boolean testSameEntertainment(Tester t) {
    return t.checkExpect(serial.sameEntertainment(serialDup), true)
        && t.checkExpect(serial.sameEntertainment(serial2), false)
        && t.checkExpect(people.sameEntertainment(peopleDup), true);
  }

}
