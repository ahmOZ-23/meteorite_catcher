

import java.io.File;

public class Meteor implements Meteorite{
	
	private String name;
	private double latitude;
	private double fall;
	private double mass;
	
	public Meteor(){ // default constructor
	    name = "";
	    latitude = 0.0;
	    fall = 0.0;
		mass = 0.0; 
	 };
	 
	 public Meteor(String name, double latitude, double fall, double mass) {
		 this.name = name;
		 this.latitude = latitude;
		 this.fall = fall;
		 this.mass = mass;
	 }

	 public Meteor(File csvData){ // parameterized constructor
	    // *similar to default but using a CSV reader or something like Java String.split()*
	 }
		
	@Override
	public int compareTo(Meteorite o) {
		if(!this.name.equals(o.getName())) {
			return this.name.compareTo(o.getName());
		}
		if(!(this.getMass()-o.getMass() < 0.01)) {
			return Double.valueOf(mass).compareTo(o.getMass());
		}
//		if(this.fall != o.getFall()) {
//			return (int)((this.fall-o.getFall())*1000);
//		}
//		if(this.fall != o.getFall()) 
//		{
//			return 0;
//		}
//		if(this.mass != o.getMass()) 
//		{
//			return 0;
//		}
//		if(this.latitude != o.getLatitude()) 
//		{
//			return 0;
//		}
//		if(this.name != o.getName()) 
//		{
//			return 0;
//		}
		return 0;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getLatitude() {
		// TODO Auto-generated method stub
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

	public String toString() {
		return this.name + ", " + this.latitude + ", " + this.fall + ", " + this.mass;
	}

}
