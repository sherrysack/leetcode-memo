## Iterator

Iterate through a Collection object (List, etc.)

- next(): Returns the 
- hasNext()
- remove(): Removes form the underlying collection the last element returned by its iterator



```java
for(Iterator<Apple> iter = list.iterator)(); iter.hasNext();) {
    Apple apple = iter.next();
    System.out.println(apple.printOut());
}
```





##ListIterator##

A ListIterator has no current element; its cursor position always lies between the element that would be returned by a call to previous() and the element that would be returned by a call to next().

- previous(): Returns the previous element in the list an moves the pursor position backwards.
- hasPrevious():
- nextIndex(): Returns the  index



## Generics

how to define static methods using generics:

```java
// whenever using generics, if the generics appears in the 

//wrong
public static T getFirst(List<T> list) {
    ...
}

//correct
public static <K, T> T getFirst(List<T> list, Key key) {
    ...
}
```

### Subclass and Superclass in Generics

```java
Apple apple = new Apple();
Fruit fruit = apple; // ok apple is a fruit

//what if
Fruit fruit = new Fruit();
Apple apple = fruit; // wrong, need cast
Apple apple = (Apple) fruit; // OK at compile time, but may have java.lang.ClassCastExcep at run time

//For example
Orange b = new Orange();
Fruit f = b;
Apple a = (Apple) f;// ok at compile time, have ClassCastException at run time
```

**A subclass can always be cast to a super class but not the other way. Generics, it's different**

```java
//Not allowed
List<Apple> apples = new ArrayList<>();
List<Fruit> fruits = apples; //not allowed
//if you allow fruits = apples, thwn we may do fruits.add(new Orange())

//Not allowed either
List<Fruit> fruits = new ArrayList<>();
List<Apple> apples = fruits;
//if you allow apples = fruits, then we may do apples.new(new Orange()).


```

**Generics check the type during the compile time. In contrast, Array does not.**

```java
Apple[] apples = new Apple[3];
Fruit[] fruits = apples;//ok at compile time
furits[0] = new Apple();//ok at compile time
fruits[1] = new Fruit();//ArrayStoreException, A fruit object is not necessarily an Apple object
fruits[2] = new Orange();//ArrayStoreException, An orange object is not an apple object for sure.
```

