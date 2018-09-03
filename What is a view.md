What is a "view"?

entrySet()



public Set<Map.Enrty<K, V >> entrySet()

Returns a Set view of the mappings contained in this map. The set is backed by the map, so changes to the map after reflected in the set.



Which is correct?

```java
for(Map.Entry<String, Integer> entry: map.entrySet()) {
    if(entry.getValue()) {
        map.remove(entry.getKey());
    }
}
//throw ConcurrentModificationException; cannot remove while iterating the whole map

Iterator<Map.Entry<String, Integer>> iter = map.entrySet.iterator();
while(iter.hasNext()) {
    Map.Entry<String, Integer> cur = iter.next();
    if(cur.getValue() == 0) {
        iter.remove();
    }
}
```

