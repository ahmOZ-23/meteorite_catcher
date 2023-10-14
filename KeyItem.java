import java.util.ArrayList;
import java.util.Iterator;

public class KeyItem<T extends Comparable<T>> implements KeyListInterface<T>{
	ArrayList<T> keys = new ArrayList<>();

	public KeyItem(T m) {
		keys.add(m);
	}

	@Override
	public int compareTo(KeyListInterface<T> o) {
		return keys.get(0).compareTo(((KeyItem<T>)o).keys.get(0));
	}

	@Override
	public Iterator<T> iterator() {
		return keys.iterator();
	}

	@Override
	public void addKey(T newKey) {
		keys.add(newKey);
	}

	@Override
	public boolean containsKey(T key) {
		return keys.contains(key);
	}
	
}
