package t4novel.azurewebsites.net.data.structure;

public class Pair<K, V>{
	public K k;
	public V v;

	public Pair(K k, V v) {
		super();
		this.k = k;
		this.v = v;
	}

	@Override
	public String toString() {
		return "[" + this.k + ", " + this.v + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		try {
			@SuppressWarnings("unchecked")
			Pair<K, V> o = (Pair<K, V>) obj;
			return k.equals(o.k) && v.equals(o.v) ;
		} catch (ClassCastException e) {
			return false;
		}

	}
}
