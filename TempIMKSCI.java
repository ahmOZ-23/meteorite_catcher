



import java.util.Iterator;
import java.util.TreeSet;

public class TempIMKSCI<T extends Comparable<T>> implements
		IterableMultiKeySortedCollectionInterface<T> {
	TreeSet<KeyListInterface<T>> ts = new TreeSet<>();

	@Override
	public boolean insert(KeyListInterface<T> data) throws NullPointerException, IllegalArgumentException {
		if(ts.contains(data))
			return false;
		ts.add(data);
		return true;
		
	}

	@Override
	public boolean contains(Comparable<KeyListInterface<T>> data) {
		return ts.contains(data);
	}

	@Override
	public int size() {
		return ts.size();
	}

	@Override
	public boolean isEmpty() {
		return ts.isEmpty();
	}

	@Override
	public void clear() {
		ts.clear();
	}

	@Override
	public boolean insertSingleKey(T key) {
		if(ts.contains(new KeyItem<T>(key)))
			return false;
		ts.add(new KeyItem<T>(key));
		return true;
	}

	@Override
	public int numKeys() {
		return ts.size();
	}

	@Override
	public Iterator<T> iterator() {
		TreeSet<T> ts2= new TreeSet<>();
		for(var v : ts) {
			ts2.add(((KeyItem<T>)v).keys.get(0));
		}
		return ts2.iterator();
	}

	@Override
	public void setIterationStartPoint(Comparable<T> startPoint) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
}
