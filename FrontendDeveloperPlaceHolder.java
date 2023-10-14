import java.io.File;
import java.util.Iterator;


public class FrontendDeveloperPlaceHolder implements FrontendInterface{
	public Backend backend = new Backend(null);
	@Override
	/**
	 * Initialize the loop that repeatedly prompts users to choose a command, restarting after a command completes
	 */
	public void startLoop() {
		
	}

	@Override
	public void loadFile() {
		backend.readData("meteorites.csv");
	}

	@Override
	public void listBetweenMasses() {
		backend.listBoundedMass(1951, 1952);
	}

	@Override
	public void listGreatestMasses() {
		
	}

	@Override
	public void exit() {
		
	}

	@Override
	public void printError(String error) {
		
	}
	
	

}
