import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;

public class BackendDeveloperTests {

    private IterableMultiKeySortedCollectionInterface<Integer> collection;
    public Backend backend = new Backend(null);
    
    @BeforeEach
    public void init() {
    	collection = new TempIMKSCI<Integer>();// TODO
    	backend = new Backend(null);
    }

    @Test
    public void testInsertSingleKey() {
        assertTrue(collection.insertSingleKey(1));
        assertTrue(collection.insertSingleKey(2));
        assertFalse(collection.insertSingleKey(1)); // Duplicate key, should return false
    }

    @Test
    public void testNumKeys() {
        collection.insertSingleKey(1);
        collection.insertSingleKey(2);
        collection.insertSingleKey(3);

        assertEquals(3, collection.numKeys());
    }
    
    /**
     * Tests if the file is read
     */
    @Test
    public void testReadData() {
    	assertEquals(true, backend.readData("meteorites.csv"));	
    }

    @Test
    public void testIterator() {
        
    }

    @Test
    public void testSetIterationStartPoint() {
        collection.insertSingleKey(3);
        collection.insertSingleKey(1);
        collection.insertSingleKey(2);

        collection.setIterationStartPoint(2);

        Iterator<Integer> iterator = collection.iterator();
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
    }
}
