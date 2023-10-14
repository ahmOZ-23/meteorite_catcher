
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class that implements the Meteorite interface
 */
public class MeteoritePlaceholder implements Meteorite {
    private String name;
    private double latitude;

    private double fall;
    private double mass;

  public MeteoritePlaceholder(File file) {
    String[] data = new String[0];
    try {
      Scanner scan = new Scanner(file);
      data = scan.nextLine().split(",");
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    }
    name = data[0];
    latitude = Double.parseDouble(data[8]);
    fall = Double.parseDouble(data[5]);
    mass = Double.parseDouble(data[4]);

  }


  public MeteoritePlaceholder(){ // default constructor
    //changed the default constructor to be more useful for testing
    name = "Test";
    latitude = 1.0;
    fall = 1.0;
    mass = 1.0;
  };

  /**
   * @return the name of the meteorite.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * @return the latitude of the meteorite.
   */
  @Override
  public double getLatitude() {
    return 0;
  }



  /**
   * @return the fall of the meteorite.
   */
  @Override
  public double getFall() {
    return 0.0;
  }

  /**
   * @return the mass of the meteorite (in grams).
   */
  @Override
  public double getMass() {
    return 0;
  }

  /**
   * Compares this object with the specified object for order.  Returns a negative integer, zero, or
   * a positive integer as this object is less than, equal to, or greater than the specified
   * object.
   *
   * <p>The implementor must ensure {@link Integer#signum
   * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for all {@code x} and {@code y}.
   * (This implies that {@code x.compareTo(y)} must throw an exception if and only if
   * {@code y.compareTo(x)} throws an exception.)
   *
   * <p>The implementor must also ensure that the relation is transitive:
   * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies {@code x.compareTo(z) > 0}.
   *
   * <p>Finally, the implementor must ensure that {@code
   * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z)) == signum(y.compareTo(z))}, for
   * all {@code z}.
   *
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this object is less than, equal to,
   * or greater than the specified object.
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object's type prevents it from being compared to
   *                              this object.
   * @apiNote It is strongly recommended, but <i>not</i> strictly required that
   * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any class that implements
   * the {@code Comparable} interface and violates this condition should clearly indicate this fact.
   *  The recommended language is "Note: this class has a natural ordering that is inconsistent with
   * equals."
   */
  @Override
  public int compareTo(Meteorite o) {
    return 0;
  }


  /**
   * I have overridden the toString method for testing purposes
   * @return
   */
  @Override
  public String toString() {
    return name + ", " + latitude + ", " + fall + ", " + mass + "\n";
  }
}
