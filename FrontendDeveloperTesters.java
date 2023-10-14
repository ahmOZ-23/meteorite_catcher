
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *  This class tests the Frontend class and its methods
 */
public class FrontendDeveloperTesters {
    BackendInterface backend = new BackendPlaceholder(); //backend object for testing

    FrontendInterface frontend = new Frontend (backend); // frontend object for testing


    /**
     * test when no meteorite was found in the given bound?
     * Will pass in a range with no meteorites in it and check if the output is correct
     * the outputted error message is bound to change by the end of the project so this test will be updated
     */
    @Test
    public void test1(){
        TextUITester uiTester = new TextUITester("list between 0 0\nexit\n");
        frontend.startLoop();// should be a scanner in this method
        // checks if the output contains the error message
        assertTrue(uiTester.checkOutput().contains("No meteorites found in the given bound"));
    }

    /**
     * test when the given bound is invalid (lower bound > upper bound)
     * Will pass in a range with no meteorites in it and check if the output is correct
     * the outputted error message is bound to change by the end of the project so this test will be updated
     */
    @Test
    public void test2(){
        TextUITester uiTester = new TextUITester("list between 5 3\nexit\n");
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
        TextUITester uiTester = new TextUITester("list greatest\nexit\n");
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
        TextUITester uiTester = new TextUITester("list between 1 \nexit\n");
        frontend.startLoop();// should be a scanner in this method
        String  message = uiTester.checkOutput();
        assertTrue(message.contains("No upper bound entered")); // checks the outputted error message
    }














}
