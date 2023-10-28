import java.util.ArrayList;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Backend implements BackendInterface {
	private IterableMultiKeySortedCollectionInterface<Meteorite> data;
	private ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();

	public Backend(IterableMultiKeySortedCollectionInterface<Meteorite> data) {
		this.data = data;
	}

	/**
	 * Reads data from a CSV formatted file and stores it as a string.
	 *
	 * @param fileName the name of the file the data is to be read from.
	 */
	public boolean readData(String fileName) {
		try {
			arr = parseCSV(fileName);
			for (int i = 1; i < arr.size(); ++i) {
				ArrayList<String> line = arr.get(i);
				Meteorite m = new Meteor(line.get(0), Double.parseDouble(line.get(7)), Double.parseDouble(line.get(6)),
						Double.parseDouble(line.get(4)));
				if (data != null)
					this.data.insert(new KeyItem<Meteorite>(m));
			}
		} catch (Exception e) {
			//System.out.println("unable to read the fileName");
			//e.printStackTrace();
			return false;
		}
		return true;
	}

	public static ArrayList<ArrayList<String>> parseCSV(String filename) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
		String token = "";
		ArrayList<ArrayList<String>> ret = new ArrayList<>();
		ArrayList<String> line_list = new ArrayList<>();
		boolean inTok = false;
		char cache = 0;
		boolean cacheValid = false;
		while (br.ready()) {
			char c = 0;
			if (cacheValid) {
				c = cache;
				cacheValid = false;
			} else {
				c = (char) br.read();
			}
			switch (c) {
				case '\r':
					// Ignore CR
					break;
				case '\n':
					if (!token.isEmpty()) {
						line_list.add(token);
						token = "";
					}
					ret.add(line_list);
					line_list = new ArrayList<>();
					break;
				case '"':
					if (!inTok) {
						inTok = true;
						break;
					} else {
						char cnext = (char) br.read();
						if (cnext == '"') {
							token += '"';
							break;
						} else {
							cache = cnext;
							cacheValid = true;// Buffer char
							inTok = false;
							break;
						}
					}
				case ',':
					if (inTok) {
						token += ',';
					} else {
						line_list.add(token);
						token = "";
					}
					break;
				default:
					token += c;
					break;
			}

		}
		// Check to see if last buffer needs to be flushed
		if (!token.isEmpty()) {
			line_list.add(token);
		}
		if (line_list.size() > 0) {
			ret.add(line_list);
		}
		return ret;
	}

	/*
	 * public static void main(String[] args) throws Exception {
	 * parseCSV("p1/meteorites.csv"); }
	 */

	/**
	 * @return a list of meteorites with the maximum mass in the data set.
	 */
	@Override
	public ArrayList<Meteorite> listMaxMass() {
		ArrayList<Meteorite> result = new ArrayList<>();
		double maxMass = Double.MIN_VALUE;
		for (Meteorite meteorite : this.data) {
			if (meteorite.getMass() > maxMass) {
				maxMass = meteorite.getMass();
				result.clear();
				result.add(meteorite);
			} else if (meteorite.getMass() == maxMass) {
				result.add(meteorite);
			}
		}
		return result;
	}

	@Override
	/**
	 * @return a list of meteorites with a mass between two specified thresholds.
	 * @param lowerBound  the lower bound of the specified threshold.
	 * @param higherBound the higher bound of the specified threshold.
	 */
	public ArrayList<Meteorite> listBoundedMass(double lowerBound, double higherBound) {
		ArrayList<Meteorite> result = new ArrayList<>();
		for (Meteorite meteorite : data) {
			double mass = meteorite.getMass();
			if (mass >= lowerBound && mass <= higherBound) {
				result.add(meteorite);
			}
		}
		return result;
	}

	//	@Override
	//	public void readData(String fileName) {
	//		try {
	//			parseCSV(fileName);
	//		} catch (Exception e) {
	//			System.out.println("unable to read the fileName");
	//		}
	//	}
}
