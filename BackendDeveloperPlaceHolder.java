package p1;

import java.io.File;
import java.util.Iterator;


public class BackendDeveloperPlaceHolder implements Meteorite{

	private String Name; // name of the meteorite
	private double mass; // mass of the meteorite in grams, doubles as an id
	private double latitude; // the latitude of the meteorite
	private double fall; // where the meteor was seen falling
	
	public BackendDeveloperPlaceHolder(){ // default constructor
	    Name = "";
	    latitude = 0.0;
	    fall = 0.0;
		mass = 0.0; 
	}
	
	public BackendDeveloperPlaceHolder(File csvData){ // parameterized constructor
	    // *similar to default but using a CSV reader or something like Java String.split()*
	}

	@Override
	public String getName() {
		return this.Name;
	}

	@Override
	public double getLatitude() {
		return this.latitude;
	}


	@Override
	public double getFall() {
		// TODO Auto-generated method stub
		return this.fall;
	}

	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return this.mass;
	}

	@Override
	public void addKey(Comparable newKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean containsKey(Comparable key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
