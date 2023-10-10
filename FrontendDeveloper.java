
import java.util.Scanner;
import java.io.File;
import BackendInterface.java;
import TextUITester.java;
import FrontendInterface.java;
import Meteorite.java;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FrontendDeveloper {
    BackendInterface backend = new BackendInterface();
    Scanner input = new Scanner(System.in);
    FrontendInterface frontend = new FrontendInterface(backend, input);


    /**
     * test when no meteorite was found in the given bound?
     * Will pass in a range with no meteorites in it and check if the output is correct
     * the outputted error message is bound to change by the end of the project so this test will be updated
     */
    @Test
    public void test1(){
        BackendInterface.readData(new File("meteorite_landings.csv"));
        System message =  FrontendInterface.listBetweenMasses(0, 0);
        assertEquals("No meteorites found in the given range", message);
    }

    /**
     * test when the given bound is invalid (lower bound > upper bound)
     * Will pass in a range with no meteorites in it and check if the output is correct
     * the outputted error message is bound to change by the end of the project so this test will be updated
     */
    @Test
    public void test2(){
        BackendInterface.readData(new File("meteorite_landings.csv"));
        System message =  FrontendInterface.listBetweenMasses(4, -2);
        assertEquals("Invalid input", message);
    }

    /**
     *  test when the user passes invalid commands to the program
     *  there should be a message printed to the user that the command is invalid
     *  the outputted error message is bound to change by the end of the project so this test will be updated
     */
    @Test
    public void test3(){
        BackendInterface.readData(new File("meteorite_landings.csv"));
        TextUITester uiTester = new TextUITester("*an invalid command*");
        frontend.startLoop();// should be a scanner in this method
        uiTester.run();
        String  message = uiTester.checkOutput();
        assertTrue(message.contains("Invalid command")); // check if the output contains the error message
    }

    /**
     * checks the output of the listGreatestMasses sub-menu when the user enters a valid lower bound
     * the output should be a list of meteorites with a mass greater than the lower bound
     * the outputted list is bound to change by the end of the project so this test will be updated
     */
    @Test
    public void test4(){
        BackendInterface.readData(new File("meteorite_landings.csv"));
        TextUITester uiTester = new TextUITester("4\nq\n");
        frontend.startLoop();// should be a scanner in this method
        uiTester.run();
        String  message = uiTester.checkOutput();
        assertTrue(message.contains("Aachen")); // checks if the outputted list contains valid meteorites
    }

    /**
     * checks the output of the listGreatestMasses sub-menu when the user enters an invalid lower bound
     * the outputted error message is bound to change by the end of the project so this test will be updated
     */
    @Test
    public void test5(){
        BackendInterface.readData(new File("meteorite_landings.csv"));
        TextUITester uiTester = new TextUITester("-2\nq\n");
        frontend.startLoop();// should be a scanner in this method
        uiTester.run();
        String  message = uiTester.checkOutput();
        assertTrue(message.contains("Invalid input")); // checks the outputted error message
    }














}
