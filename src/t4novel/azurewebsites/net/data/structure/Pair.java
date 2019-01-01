package t4novel.azurewebsites.net.data.structure;

public class Pair<K, V> {
	public K k;
	public V v;

	public Pair(K k, V v) {
		super();
		this.k = k;
		this.v = v;
	}
	@Override
	public String toString() {
		return "[" + this.k + ", "+ this.v + "]";
	}

}
