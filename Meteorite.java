/**
 * An interface for a class that defines a single meteorite and exposes properties required by the
 * frontend: name, latitude, longitude, fall, and mass (in grams).
 *
 * This interface extends the KeyListInterface to help store multiple objects in each node of the tree
 */
interface Meteorite extends Comparable<Meteorite> {
  // public Meteorite(){ // default constructor
  // name = "";
  // latitude = 0.0;
  // fall = 0.0;
  // mass = 0.0;
  // };

  // public Meteorite(T csvData){ // parameterized constructor
  // *similar to default but using a CSV reader or something like Java String.split()*
  // }

  /**
   * Defines a single meteorite object from a CSV formatted string.
   * CSV formatted as: name,id,nametype,recclass,mass,fall,year,reclat,reclong,GeoLocation
   * @param csvData an object that contains the data from a CVS data set
   */
  // public T meteoriteMaker();

  /**
   * @return the name of the meteorite.
   */
  public String getName();

  /**
   * @return the latitude of the meteorite.
   */
  public double getLatitude();


  /**
   * @return the fall of the meteorite.
   */
  public double getFall();

  /**
   * @return the mass of the meteorite (in grams).
   */
  public double getMass();

}
