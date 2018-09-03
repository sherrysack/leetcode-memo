Map<String, Integer> results = new HashMap<>();

K = String V = Integer



List<Integer> results = new ArrayList<>();

E = Integer

Set<Map.Entry<K, >> entrySet() -get the set view of all the entries in the hashmap

Set<Key> keySet() - get the set view of all the keys in the hashmap

Collections<V> values() - get the collection view of all the values in the hashmap





Vector, Hashtable from JAVA1.0

ArrayList, HashMap introduced from JAVA5

- Hashtable not allow null key, but HashMap allow one null key
- Vector, Hashtable operations are synchronized (thread safe), introduce a lot of performance penalty
- We don't use Hashtable anymore.



A table of buckets (arryy of buckets), using the array index to denoet each bucket.

What are the elements in the buckets? Singly Linked List, each node contains one<key, value> pair



```java
class Entry<K, V> {
    K key;
    V value;
    Entry<K, V> next;
}
```

HashMap can only have one null key, it is always mapped to the bucket 0.



Get into a little details about HashMap Implementations

- Array of entries
- each entry is actually a singly linked list handle collision)

```java
class Entry<K, V> {
    final K key;
    V value;
    Entry<K, V> next;
    Entry<K key, V value> {
        this.key = key;
        this.value = value;
    }
    public K getKey() {
        return key;
    }
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
}

//2.maintain an array of entries

Entry<K, V>[]array;
// to get(K key), put(K key, V value), remove(K key), we follow the steps
// hash(key) to hash#
// hash# to entry index in the array
// from the corresponding singly list, iterate all of the nodes to find if the same key exists



//3. hash(key) to get the hash#
private int hash(K key){
    //return the hash# of the key
    if(key == null) {
        return 0;
    }
    int hashNumber = key.hashCode();
}

//4, from the hash#, mapped to the entry index
int getindex(int hasNumber){
    return hashNumber % array.length;
}

//5. When iterate the corresponding entry for the given key, which is actually linked list, we need to compare each of the entry in the list, if the key is the same as the key we want
Entry<K, V> cur = array[index];
while(cur != null) {
    K curKey = cur.getKey();
    if(curKey is the same as the given key) {
        //
    }
    cur = cur.next;
}

```

==, equals*(), hashCode()

==

- determine if two primitive tyep has the same value
- determine if 2 references are pointed to the same object

equals(), hashCode()

- defined in Object class, OBject is the root class for any JAVA class
- any JAVA class implicitly extends Object class, so if the subclass these two methods are not overriden, it inherits what is defined in Object class
- the default implementation of equals is to check if the 2 references are pointed to the same object "==" 
- the default implementation of hashCode() returns a "unique hash value" for the object based on its memory address.



but sometimes we want to compare the content.

```java
public class Person{    
    private String name;    
    public Person(String name)  {
        this.name=name;    
    } 
    public boolean equals(Object o) {
        if (this==0) return true; 
        if (!o instanceof Person)
            return false; 
        final Person other=(Person)o;  
        if (this.name().equals(other.name()))     
            return true; 
        else {
            return false; 
        }
	}

```

hashCode() is on the same condition.

In JAVA, there is a contract between equals() and hashCode(), the developers need to maintain:

- if one.euqals(two), it is a must that one.hashCode() == two.hashCode()
- If one.hashCode() == two.hashCode(), it is not necessary that one.equals(two)

When you want to override equals(), please definitely override hashCode() as well. Only by doing this, you can make sure the HashMap will still work for the key type.

```java
class Coordinate {
    int x,y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    private boolean equals(Object o) {
        if(!o.instanceof(Coordinate)) {
            return false;
        }
        Coordinate e = (Coordinate)o;
        if(e.x == o.x && e.y == o.y) {
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return x*101+y;
    }
}
```

hashCode() is very important!

1. The performance of HashMap solely depends on how good the hashCode() is.
2. easy fast efficient
3. Minimize collision, as evenly distributed as possible

```java
Class Combo {
    int x, y, z;
    public Combo(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    @Override
    public int hashCode() {
        return a*31*31+b.hashCode()*31+c.hashCode();
    }
}
```

### Rehash####

Is needed when there are too many <key,  value> pairs maintained in the hash map, too many collisions

- Expand the array to a larger one and move all the <key, value> pairs to the new array (in HashMap by default, the array size is double each time)
- Rehashing is global wise - meaning all the <key, value> pairs need to participate

## HashSet

It is backed up by a HashMap instance. Only care about the Key here.

Common API

1. boolean add(E e)
2. boolean remove(Object o)
3. boolean contains(Object o)
4. void clear()
5. int size()
6. boolean isEmpty()



```java
class HashSet<K> {
    private HashMap<K, Object> map;
    //special object used for all the existing keys
    private static final Object PRESENT = new Object();
    
    public HashSet(){
        map = new HashMap<K, Object>();
        public boolean contains(K key) {
            return map.containsKey(key);
        }
        
    }
}
```

Use HashSet to dedup

Kth Smallest Sum From two sorted array(Lintcode 465)









