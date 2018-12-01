package t4novel.azurewebsites.net.caching;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExpiringMap<K, V> extends AbstractMap<K, V> implements Runnable, Map<K, V> {
	private Map<K, ExpiringObject<V>> container = Collections.synchronizedMap(new HashMap<>());
	private final long maxTimeLife;
	private final long period;
	private Thread movingTasking;
	private boolean alive = true;

	public ExpiringMap(long maxTimeLife, long periodScanning) {
		this.maxTimeLife = maxTimeLife;
		this.period = periodScanning;
		movingTasking = new Thread(this);
		movingTasking.start();
	}

	public void kill() {
		this.alive = false;
		if(movingTasking.isAlive()) {
				movingTasking.interrupt();
		}
	}

	@Override
	public int size() {
		return container.size();
	}

	@Override
	public boolean isEmpty() {
		return container.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return container.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return container.containsValue(value);
	}

	@Override
	public V put(K key, V value) {
		ExpiringObject<V> last = container.put(key, new ExpiringObject<V>(value));
		if (last == null)
			return null;
		return last.data;
	}

	@Override
	public V get(Object key) {
		V rs = null;
		ExpiringObject<V> value = container.get(key);
		if (value != null)
			rs = value.data;
		return rs;
	}

	@Override
	public V remove(Object key) {
		ExpiringObject<V> out = container.remove(key);
		if(out == null) return null;
		return out.data;
	}

	@Override
	public void run() {
		while (this.alive) {
			try {
				Thread.sleep(this.period);
				if (this.container.isEmpty())
					continue;
				List<K> removingKeys = new LinkedList<>();
				for (Entry<K, ExpiringObject<V>> entry : container.entrySet()) {
					if (entry.getValue().isDead(this.maxTimeLife)) {
						removingKeys.add(entry.getKey());
					}
				}
				for (K key : removingKeys) {
					container.remove(key);
					System.out.println("Dead time is ticked : " + key + " left " + container.size() + " in map");

				}
			} catch (InterruptedException e) {
				System.out.println("server forces to ExpiringMap - verifyCodesPool die!");
			}
		}
	}

	class ExpiringObject<D> {
		D data;
		final long lastTime;

		public ExpiringObject(D data) {
			this.data = data;
			lastTime = System.currentTimeMillis();
		}

		boolean isDead(long maxTimeLife) {
			if (System.currentTimeMillis() - lastTime >= maxTimeLife)
				return true;
			return false;
		}
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

}
