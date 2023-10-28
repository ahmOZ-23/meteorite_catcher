import java.io.File;
import java.util.ArrayList;

/**
 * A class that implements the BackendInterface and allows the user to interact with the program
 */
public class BackendPlaceholder implements BackendInterface{
    //variable to store a reference to the RBT
    private IterableMultiKeySortedCollectionInterface<Meteorite> RBT;

    /**
     * Constructor that takes in a reference to the RBT
     * @param RBT Red-Black Tree to store the data
     */
  public BackendPlaceholder(IterableMultiKeySortedCollectionInterface<Meteorite> RBT){
   this.RBT = RBT;
  }

    /**
     * Default constructor made for testing purposes
     */
  public BackendPlaceholder(){}

  /**
   * Reads data from a CSV formatted file and stores it as a string.
   *
   * @param fileName the name of the file the data is to be read from.
   */

  public void readData(File fileName) {
      // read the data from the file and store it in the RBT
  }

  /**
   * Reads data from a CSV formatted file and stsores it as a string.
   *
   * @param fileName the name of the file the data is to be read from.
   */
  @Override
  public boolean readData(String fileName) {
    return false;
  }

  /**
   * @return a list of meteorites with the maximum mass in the data set.
   */
  @Override
  public ArrayList<Meteorite> listMaxMass() {
    ArrayList<Meteorite> list = new ArrayList<>();
    list.add(new MeteoritePlaceholder());
    return list;
  }

  /**
   * @param lowerBound  the lower bound of the specified threshold.
   * @param higherBound the higher bound of the specified threshold.
   * @return a list of meteorites with a mass between two specified thresholds.
   */
  @Override
  public ArrayList<Meteorite> listBoundedMass(double lowerBound, double higherBound) {
    ArrayList<Meteorite> list = new ArrayList<>();
    list.add(new MeteoritePlaceholder());
    if (lowerBound == higherBound) return null; // added for testing purposes
    return list;
  }
}
