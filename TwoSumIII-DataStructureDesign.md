**  Two Sum III - Data structure design**

Design and implement a TwoSum class. It should support the following operations: `add` and `find`.

`add` - Add the number to an internal data structure.
`find` - Find if there exists any pair of numbers which sum is equal to the value.

**Example 1:**

```
add(1); add(3); add(5);
find(4) -> true
find(7) -> false

```

**Example 2:**

```
add(3); add(1); add(2);
find(3) -> true
find(6) -> false
```



这道题看起来很简单，但是因为题目条件交代的不是很清楚，其实暗含很多假设：

数字可以重复，但是4=2+2，则2必须被add两次；否则这一对数的组合是不成立的；

当判断2没有2个时，需要continue到下一个可能，而不是直接return false。


```java
class TwoSum {
    /** duplicate elements are possible. */
    /** Initialize your data structure here. */
    HashMap<Integer, Integer> map = new HashMap<>();
    public TwoSum() {
    
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0)+1);
        
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
            for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
                Integer i = entry.getKey();
                if(value - i == i) {
                    if(map.get(i) >= 2)
                        return true;
                    else{
                        continue;
                    }
                }
                if(map.containsKey(value-i)) {
                    return true;
                }
            }
            return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
```