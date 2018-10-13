## How to write comparator



1. JAVA 8 lambda 

```java
listDevs.sort((Developer o1, Developer o2)->o1.getAge()-o2.getAge());
```

2. write anonymous function

```java

PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new comparator<Integer> {
    public int comparator(int c1, int c2) {
        if(c1==c2) {
            return 0;
        }
        return c1>c2?1:-1;
    }
});
```

3 write other comparator

```java
// the class can be public or private as you like
public class InterComp implements Comparator<int[]> {
    //this function has to be public, or it cannot override
    public int compare(int[] o1, int[] o2) {
        if(o1[0] == o2[0]) {
            return o1[1] > o2[1]?-1:1;//descending order
        }
        return o1[0] < o2[0] ? -1:1; //ascending order
    }
}
```