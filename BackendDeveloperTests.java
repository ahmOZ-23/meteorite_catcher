import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;

public class BackendDeveloperTests {

    private IterableMultiKeySortedCollectionInterface<Meteorite> collection;
    public Backend backend = new Backend(null);
    
    @BeforeEach
    public void init() {
    	collection = new TempIMKSCI<Meteorite>();// TODO
    	backend = new Backend(collection);
    }

    /**
     * tests if listMaxMass gets the max mass of the test.csv.
     */
    @Test
    public void testlistMaxMass() {
    	backend.readData("test.csv");
    	ArrayList<Meteorite> arr =  backend.listMaxMass();
    	assertEquals(4239, arr.get(0).getMass());
    	
    }

    /**
     * tests if compareTo compares correctly with the Acapulco meteor from the test.csv
     */
    @Test
    public void testcompareTo() {
        Meteor test = new Meteor("Acapulco", 16.883330, 1976, 1800);
        backend.readData("test.csv");
    	ArrayList<Meteorite> arr =  backend.listBoundedMass(1800, 2000);
    	assertEquals(0, arr.get(0).compareTo(test));
    }
    
    /**
     * Tests if the file is read
     */
    @Test
    public void testReadData() {
    	assertEquals(true, backend.readData("test.csv"));	
    }

    /**
     * tests the listBoundedMass, it should get only 1800 and 1801 from the test.csv
     */
    @Test
    public void testlistBoundedMass() {
    	backend.readData("test.csv");
    	ArrayList<Meteorite> arr =  backend.listBoundedMass(1800, 1801);
    	assertEquals(1800, arr.get(0).getMass());
    	assertEquals(1801, arr.get(1).getMass());
    }

    /**
     * test the accuracy of the read from the backend.readData, it should spit out all 3 meteor objects
     */
    @Test
    public void testReadAccuracy() {
    	backend.readData("test.csv");
    	ArrayList<Meteorite> arr =  backend.listBoundedMass(1800, 10000);
    	assertEquals(1800, arr.get(0).getMass());
    	assertEquals(1801, arr.get(1).getMass());
    	assertEquals(4239, arr.get(2).getMass());
    }
}
