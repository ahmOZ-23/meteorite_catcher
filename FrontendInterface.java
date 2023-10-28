

public interface FrontendInterface {
 // Scanner input;
 // Backend backend;

 // public IndividualFrontendInterface(BackendInterface backend, Scanner input);

    /**
     * initialize the loop that repeatedly prompts users to choose a command, restarting after a command completes
     */
    void startLoop();

    /**
     * prompt the user for a .csv file with the scanner
     * don't take method parameters, ask for the file location with the scanner
     */
    void loadFile(); // prompt user for file with scanner (don't take method parameters)

    /**
     * prompt the user for the upper/lower bounds with the scanner
     */
    void listBetweenMasses(); // prompt user for upper/lower bounds with scanner

    /**
     * prompt the user for a lower bound with the scanner or use a default
     */
    void listGreatestMasses(); // prompt user for a lower bound or use default

    /**
     * exit the program using System.exit()
     */
    void exit();

    /**
     * print an error for the user, formatted nicely
     * @param error the error to display to the user
     */
    void printError(String error);


}

