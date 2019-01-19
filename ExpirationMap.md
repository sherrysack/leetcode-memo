## Expiration Map

```java
Code  （用后台线程主动删除）
class MyMap<K, V> {
	Map<K, V> map;
	Map<K, Long> time;
	private static final int DEFAULT_CAPACITY = 16;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private Thread clearThread = new Thread(new Runnable() {
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(5000);
				}catch(Exception e) {
					e.printStackTrace();
				}
				for(K key : map.keySet()) get(key);
			}
		}
		
	});
	public MyMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	public MyMap(int capacity) {
		this(capacity, DEFAULT_LOAD_FACTOR);
	}
	public MyMap(int capacity, float loadFactor) {
		map = new ConcurrentHashMap<>(capacity, loadFactor);
		time = new ConcurrentHashMap<>(capacity, loadFactor);
		clearThread.start();
	}
	public V get(K key) {
		long now = System.currentTimeMillis();
		Long expired = time.get(key);
		if(expired == null) return null;
		if(Double.compare(now, expired) > 0) {
			map.remove(key);
			time.remove(key);
			return null;
		} else {
			return map.get(key);
		}
	}
	public V put(K key, V value, long duration) {
		long now = System.currentTimeMillis();
		long expired = now + duration;
		time.put(key, expired);
		return map.put(key, value);
	}
}

```

