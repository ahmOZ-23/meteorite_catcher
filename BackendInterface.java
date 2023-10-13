///////////////////////////////////////   
// Title: Backend Interface
// Course: CS 400 Fall 2023
///////////////////////////////////////
import java.util.Iterator;
import java.io.File;

/**
 * An interface for the backend that exposes the required functionality to the frontend: read
 * data from a file, get a list of meteorites with the maximum mass in the data set, get a list
 * of meteorites with a mass between two specified thresholds.
 *
 * This interface extends the IterableMultiKeySortedCollectionInterface to iterate over the 
 * keys and values stored in the data structure.
 */
public interface BackendInterface<T extends Comparable<T>>{
	/*
	 * public BackendInterface(IterableMultiKeySortedCollectionInterface imksci);
	 */
    /**
     * Reads data from a CSV formatted file and stores it as a string.
     * @param fileName the name of the file the data is to be read from. 
     */
    public void readData(File fileName);

    /**
     * @return a list of meteorites with the maximum mass in the data set.
     */
    public T[] listMaxMass();

    /**
     * @return a list of meteorites with a mass between two specified thresholds.
     * @param lowerBound the lower bound of the specified threshold.
     * @param higherBound the higher bound of the specified threshold.
     */
    public T[] listBoundedMass(double lowerBound, double higherBound);

}
