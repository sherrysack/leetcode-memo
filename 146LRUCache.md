## 146 LRU Cache

Design and implement a data structure for [Least Recently Used (LRU) cache](https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU). It should support the following operations: `get` and `put`.

`get(key)` - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
`put(key, value)` - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

**Follow up:**
Could you do both operations in **O(1)** time complexity?

**Example:**

```
LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
```

```java
class LRUCache {
    private class DLinkedNode {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode post;
        public DLinkedNode() {
        }
        public DLinkedNode(int a, int b) {
            this.key = a;
            this.value = b;
        }
    }
    private DLinkedNode head, tail;
    private int cap;
    
    private void addNode(DLinkedNode node) {
        node.pre = head;
        node.post = head.post;
        head.post.pre = node;
        head.post = node;
        
    }
    private void removeNode(DLinkedNode node) {
        //this method would only cost O(1) time
        node.pre.post = node.post;
        node.post.pre = node.pre;
    }
    private void moveTohead(DLinkedNode node) {
        removeNode(node);
        addNode(node);
    }
    private HashMap<Integer, DLinkedNode> map;
    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<Integer, DLinkedNode>();
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.post = tail;
        tail.pre = head;
        
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            moveTohead(map.get(key));
            //System.out.println("the searched one is"+key);
            //System.out.println(head.post.key);
            return map.get(key).value;
        }else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)) {
            map.get(key).value = value;
            moveTohead(map.get(key));
        }else {
            if(map.size() == cap) {
                map.remove(tail.pre.key);
                removeNode(tail.pre); 
            }
            DLinkedNode node = new DLinkedNode(key, value);
            addNode(node);
            map.put(key, node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```

