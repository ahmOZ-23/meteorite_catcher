import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  This class tests the Frontend class and its methods
 */
public class FrontendDeveloperTesters {
    BackendInterface backend; //backend object for testing

    FrontendInterface frontend;  // frontend object for testing

    /**
     * creates a new backend object for each test.
     */
    @BeforeEach
    public void init(){
        backend = new Backend(new IterableMultiKeyRBT<>()); // will be changed by the finial backend implementation
        frontend = new Frontend (backend);
    }


    /**
     * test when no meteorite was found in the given bound?
     * Will pass in a range with no meteorites in it and check if the output is correct
     * the outputted error message is bound to change by the end of the project so this test will be updated
     */
    @Test
    public void test1(){
        frontend = new Frontend (new BackendPlaceholder());
        TextUITester uiTester = new TextUITester("listBetween 0 0\nexit\n");
        frontend.startLoop();// should be a scanner in this method
        String message = uiTester.checkOutput();
        System.out.println(message);
        // checks if the output contains the error message
        assertTrue(message.contains("No meteorites found in the given bound"));
    }

    /**
     * test when the given bound is invalid (lower bound > upper bound)
     * Will pass in a range with no meteorites in it and check if the output is correct
     * the outputted error message is bound to change by the end of the project so this test will be updated
     */
    @Test
    public void test2(){
        frontend = new Frontend (new BackendPlaceholder());
        TextUITester uiTester = new TextUITester("listBetween 5 3\nexit\n");
        frontend.startLoop();// should be a scanner in this method
        String message = uiTester.checkOutput();
        // checks if the output contains the error message
        assertTrue(message.contains("your lower bound is greater than your upper bound"));
    }

    /**
     *  test when the user passes invalid commands to the program
     *  there should be a message printed to the user that the command is invalid
     *  the outputted error message is bound to change by the end of the project so this test will be updated
     */
    @Test
    public void test3(){
        frontend = new Frontend (new BackendPlaceholder());
        TextUITester uiTester = new TextUITester("*an_invalid_command*\nexit\n");
        frontend.startLoop();// should be a scanner in this method
        String  message = uiTester.checkOutput();
        assertTrue(message.contains("Invalid command")); // check if the output contains the error message
    }

    /**
     * checks the output of the listGreatestMasses the output should be a list of meteorites with
     * the maximum mass in the data set.
     * the outputted list is bound to change by the end of the project so this test will be updated
     */
    @Test
    public void test4(){
        frontend = new Frontend (new BackendPlaceholder());
        TextUITester uiTester = new TextUITester("listGreatest\nexit\n");
        frontend.startLoop();// should be a scanner in this method
        String  message = uiTester.checkOutput();
        assertTrue(message.contains("Test, 1.0, 1.0, 1.0")); // checks if the outputted list contains valid meteorites
    }

    /**
     * checks the output of the listBetweenMasses sub-menu when the user does not enter an upper bound
     * the outputted error message is bound to change by the end of the project so this test will be updated
     */
    @Test
    public void test5(){
        frontend = new Frontend (new BackendPlaceholder());
        TextUITester uiTester = new TextUITester("listBetween 1 \nexit\n");
        frontend.startLoop();// should be a scanner in this method
        String  message = uiTester.checkOutput();
        assertTrue(message.contains("No upper bound entered")); // checks the outputted error message
    }

    /**
     * checks the validity of the loadfile command when the user enters a valid file name
     * should return a list of meteorites with the maximum mass in the data set.
     */
    @Test
    public void IntegrationTest1(){
        TextUITester uiTester = new TextUITester("load test.csv\nlistGreatest\nexit\n");
        frontend.startLoop();// should be a scanner in this method
        String  message = uiTester.checkOutput();
        assertFalse(message.contains("File not found"));
        //checks if the outputted list contains the expected masses
        System.out.println(message);
        assertTrue(message.contains("107000")); // checks if the outputted list contains valid meteorites
    }

    /**
     * checks the output of the listBetweenMasses sub-menu when the user enters a valid range
     * should return a list of all the meteorites in the given range
     */
    @Test
    public void IntegrationTest2(){
        TextUITester uiTester = new TextUITester("load test.csv\nlistBetween 10 400\nexit\n");
        frontend.startLoop(); // should be a scanner in this method
        String  message = uiTester.checkOutput();
        //checks if the outputted list contains the expected masses
        assertFalse(message.contains("File not found"));
        System.out.println(message);
        // checks if the outputted list contains valid meteorites
        assertTrue(message.contains("21")); // checks if the outputted list contains valid meteorites
    }

    /**
     * Checks the validity of the listBetweenMasses method for the backend class
     * tests two cases: (1)  when the user enters an invalid range (2) when the user enters a valid range
     */
    @Test
    public void backendTest1(){
        BackendInterface backend = new Backend(new IterableMultiKeyRBT<>());
        // checks if the file is found; true should be returned
        assertTrue(backend.readData("test.csv"));
        // checks if the outputted list contains valid meteorites
        assertTrue(backend.listBoundedMass(10, 10).isEmpty()
            && backend.listBoundedMass(10, 400).size() == 1
            &&backend.listBoundedMass(10, 400).get(0).getMass() == 21);
    }

    /**
     * Checks the validity of the listMaxMass method for the backend class
     * There should be one meteorite with the maximum mass in this data set.
     *
     */
    @Test
    public void backendTest2(){
        BackendInterface backend = new Backend(new IterableMultiKeyRBT<>());
        // checks if the file is found; true should be returned
        assertTrue(backend.readData("test.csv"));
        // checks if the outputted list contains valid meteorites
        assertTrue(backend.listMaxMass().size() == 1 && backend.listMaxMass().get(0).getMass() == 107000);
    }














}
