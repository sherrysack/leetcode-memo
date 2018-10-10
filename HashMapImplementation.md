## HashMap Implementation##

```java
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
/*
A hashtable implementation of map, demonstration purpose,
generic type is provided.
supported operations:
size()
clear()
put(String key, Integer value)
get(String key)
remove(String key)



*/
public class HashMap1<K, V> extends AbstractMap<K, V> implements Map<K,V>, Cloneable, Serializable {{
    /*
    ListNode is a static class of HashMap, since it is:
    very close bonded to HashMap class,
    we probably need to access this class outside from MyHashMap class.
     */
    public class ListNode<K, V> implements Map.Entry<K,V>{
        final K key;
        V val;
        ListNode<K, V> next;
        private ListNode(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return key;
        }
        public V getValue() {
            return val;
        }
        public final V setValue(V value) {
            this.val = value;
            return this.val;
        }
    }
    private int size;
    private ListNode<K, V>[] map;
    private float loadFactor;
    public static final int DEFAULT_CAPACITY = 16;
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_POSITIVE_MASK = 0x7FFFFFFF;


    public HashMap1(int cap, float loadFactor) {
        if(cap <= 0) {
            throw new IllegalArgumentException("cap can not be <= 0");
        }
        @SuppressWarnings({"rawtypes","unchecked"})
        ListNode<K, V>[] newmap = (ListNode<K, V>[])(new ListNode[cap]);
        map = newmap;
        this.loadFactor = loadFactor;
    }

    public HashMap1(int cap) {
        this(cap, DEFAULT_LOAD_FACTOR);
    }

    public HashMap1() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public int size() {
        return this.size;
    }

    public void clear() {
       size = 0;
       Arrays.fill(this.map, null);

    }

    private int gethash(K key) {
        if(key == null) {
            return 0;
        }
        int hash = key.hashCode();
        if(hash < 0) hash = (hash & DEFAULT_POSITIVE_MASK);//guarantee non-negative
        return hash;
    }

    private boolean equalsKey(K a, K b) {
        return a==b || ((a!=null)&& a.equals(b));
    }

    public void put(K key, V value) {
        int pos = gethash(key)%this.size; // % can not ensure positive
        ListNode<K, V> node = map[pos];
        size++;
        if(node == null) {
            node = new ListNode<K, V>(key, value);
            map[pos] = node;
        }else {
            ListNode prev = node;
            while(node != null) {
                //once the key already exits, it needs to update
                if(equalsKey(node.key, key)) {
                    node.val = value;
                    return;
                }
                prev = node;
                node = node.next;
            }
            prev.next = new ListNode<K, V>(key, value);
        }

    }

    public V get(K key) {
        int pos = gethash(key)%this.size;
        ListNode<K,V> node = map[pos];
        while(node != null) {
            if(equalsKey(node.key, key)) {
                node = node.next;
            }
        }
        return null;
    }

    public void remove(String key) {

    }

    public boolean containsKey(K key) {
        //get the index of the key
        int pos = gethash(key)%map.length;
        ListNode<K, V> node = map[pos];
        if(node == null) return false;
        while(node != null) {
            //check if the key equals
            if(equalsKey(node.key, key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    private boolean needRehash(){
        float ratio = (size+0.0f)/map.length;
        return ratio > this.loadFactor;
    }

    private boolean rehash() {
        //new double size
        //for each node in the old array
        //do the put() operation to the new larger array
        return false;

    }
}

```

