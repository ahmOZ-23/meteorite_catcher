import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *  A class that implements the FrontendInterface and allows the user to interact with the program
 */
public class Frontend implements FrontendInterface {

    BackendInterface backend; // backend object to get data from
    Scanner input; // scanner to get user input

  // a list of commands that the user can use
    String listOfCommands = "====================================\n"+
        "- load <filename> (loads in the file to be read from)\n" +
        "- listGreatest (Lists the greatest meteorites in the data set)\n" +
        "- listBetween <lower bound> <upper bound>  (lists all the meteorites in the specified bounds)\n" +
        "- exit (exits the program)\n"+
        "====================================";

  /**
   * Constructor that takes in a reference to the backend
   * @param backend backend object to get data from
   */
   public Frontend(BackendInterface backend){
     this.backend = backend;
     // startLoop(); // start the loop
   }

  /**
   * The main method that runs the program and the window that allows the user to interact with the program
   * Starts the loop that allows the user to interact with the program
   */
  @Override
    public void startLoop() {
      System.out.println("Welcome to the meteorite database!\n"+ listOfCommands + "\n");
      input = new Scanner(System.in);
      while (true){
          System.out.println("Please enter a command and parameters (if applicable)\n");
          String command = input.next().trim();
          if (command.equals("load")){
              loadFile();
              }
          else if (command.contains("list")){
            command = input.next().trim();
            if (command.contains("between")){
                  listBetweenMasses();
              }
            else if (command.contains("greatest")){
                  listGreatestMasses();
              }
          }
          else if (command.equals("exit")){
            exit();
            break;
          }
          else {
              printError("Invalid command");
          }
      }
    }

  /**
   * Loads the file that the user enters
   * If the file is not found or the file name is invalid, an error message is printed
   * If the file is found, the data is read from the file and stored in the RBT
   */
    @Override
    public void loadFile() {
      if (!input.hasNext()) printError("No file name entered");
      try {
          backend.readData(new File(input.next()));
        }catch(Exception e){
          printError("Invalid file name or file not found");
        }
      System.out.println("File loaded");
    }

  /**
   * Lists all the meteorites in the specified bounds
   * If the user does not enter a lower bound or an upper bound, an error message is printed
   * If the user enters a lower bound that is greater than the upper bound, an error message is printed
   * If no meteorites are found in the given bound, an error message is printed
   * If meteorites are found in the given bound, they are printed
   */

  @Override
    public void listBetweenMasses() {
      double upBound;
      double lowBound;
     if (!input.hasNextDouble()) {
       printError("No lower bound entered");
       return;
     }
     else lowBound = input.nextDouble();
     if (!input.hasNextDouble()) {
       printError("No upper bound entered");
       return;
     }

     else upBound = input.nextDouble();

     if (lowBound > upBound)
       printError("your lower bound is greater than your upper bound");
      ArrayList<Meteorite> list = backend.listBoundedMass(lowBound, upBound);

      if (list != null) System.out.println(list);
     printError("No meteorites found in the given bound");
   }

  /**
   * Lists the greatest meteorites in the data set
   *  If no meteorites are found in the given bound, an error message is printed
   */
  @Override
    public void listGreatestMasses() {
    ArrayList<Meteorite> list = backend.listMaxMass();
    if (list != null) System.out.println(list);
    printError("No meteorites found");
    }

  /**
   * Exits the program
   * Closes the scanner
   */
    @Override
    public void exit() {
     System.out.println("Exiting...");
     input.close();
   }

  /**
   * Prints an error message to the user
   * and a list of valid commands
   * @param errorMsg the error to display to the user
   */
    @Override
    public void printError(String errorMsg) {

     System.out.println(errorMsg +"\n" +
              "Here's a list of valid commands:\n" +
              listOfCommands);
    }
}

