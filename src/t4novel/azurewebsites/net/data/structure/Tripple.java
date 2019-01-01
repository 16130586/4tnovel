package t4novel.azurewebsites.net.data.structure;

public class Tripple<K, V1, V2> {
	public K k;
	public V1 v1;
	public V2 v2;

	public Tripple(K key, V1 v1, V2 v2) {
		super();
		this.k = key;
		this.v1 = v1;
		this.v2 = v2;
	}

	@Override
	public String toString() {
		return "[" + this.k + " , " + this.v1 + " ," + this.v2 + "]";
	}

}
