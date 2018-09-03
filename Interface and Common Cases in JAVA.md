## Interface and Common Cases in JAVA

Collection - Defines what operations should be provided for any classes that is a "Collection". Any class that is a kind of collection should implement the defined method.

- int size()
- boolean isEmpty()
- boolean add(E element)
- Boolean remove(E element)
- boolean contains(E element)
- void clear()



List - Defines a group of data structures, that can contain duplicate elements, maintain the order of insertion of the elements and provide the capability of random access by using index.

- E get(int index)
- E remove(int index)
- E set(int index, E element)



ArrayList - A concrete implementation of List by resizable array

is a list 

is a collection



Queue - Defines a group of data structures, that is designated to

1. hold and buffer elements before processing

2. provide a way of choosing which element buffered is the next one to processed

3. there are usually two ends of the Queue,  and offer always at tail, poll() always at head.

   - boolean add(E element)
   - E element()
   - E remove()
   - boolean offer(E element)
   - E peek()
   - E poll()

   ​

Deque - Defines a subtype of queue, where it is double ended. (FIFO & LIFO are both provided)

- boolean offerFirst(E element)
- boolean offerLast(E element)
- E pollFirst()
- E pollLast()
- E peekFirst()
- E peekLast()



LinkedList - A List implementation that backed a doubly linked list. It also can be used as queue/deque/stack.

1. It is a List
2. It is a Queue
3. It is a Deque
4. It is a Colleciton



PriorityQueue - Defines a special implementation of queue, where it is using priority to determine which element is the next to processing(using order)

1. It is a Queue
2. It is a Collection



Set



HashSet - A concrete implementation of Set using hahtable



SortedSet - Defines a subtype of Set, that contains no duplicate elements, and elements are sorted, a subtype of Set.

- E first()
- E last()
- SortedSet<E> headSet(E toElement); // all the elements smaller than toElement
- SortedSet<E> tailSet(E fromElement); //all the elements larger than fromElement



TreeSet - A concrete implementation of SortedSet using red-black tree.

1. It is a SortedSet
2. It is a Set
3. It is A Collection



About Arrays and Collections

They are the placeholders of a set of utility methods for manipulating arrays and collection objects.

```java
Arrays.sort(int[] array)

Arrays.sort(T[], Comparator<T> comparator)

Arrays.sort(int[] array); //use quicksort
Arrays.sort(Integer[] array); //optimized mergesort

/*
Returns a fixed-size list backed by the specified array(Changes to the returned list "write through" to the array.)
*/
Arrays.asList(T... a);// convert an arrya to a list

List<Integer> list = Arrays.asList(1,2,3);
list.set(0, 4);
//list is [4,2,3]
list.add(5);
//throws UnsupportedOperationException - fixed-sized

Arrays.copyOf(original, int newLength);

    
int[] array = new int[]{1,2,3};
int[] copy = Arrays.copyOf(array, 1);
//copy = [1]
copy = Arrays.copyOf(array, 5);
//copy = {1,2,3,0,0}; padding default values at the end

Arrays.copyOfRange(original, int from, int to);

int[] array = new int[]{1,2,3};
int[] copy = Arrays.copyOfRange(array, 1, 3);
//copy = [2]

Arrays.fill(original, value);


Arrays.toString(int[] array);
Arrays.deepToString(int[][] matrix);

int[] array = new int[]{1,2,3};
String s = Arrays.toString(array);
//s = "[1,2,3]";

int[][] matrix = new int[]{{1,2}, {1, 2}};
String t = Arrays.deepToString(matrix);
//s = "[[1,2],[1,2]]"


//convert list to array(or, Collection to array)
List<String> lst = new ArrayList<>{"fdfds"};
lst.toArray();

//but we cannot change List<Integer> to int[]
```





