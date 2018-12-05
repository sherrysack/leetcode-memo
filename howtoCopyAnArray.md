# how to copy an array

```java
int[] a = new int[]{1,2,3,4,5};
int[] b = a.clone();
```

If you want to make a copy of:

```java
int[] a = {1,2,3,4,5};
```

This is the way to go:

```java
int[] b = Arrays.copyOf(a, a.length);
```

`Arrays.copyOf` may be faster than `a.clone()` on small arrays. Both copy elements equally fast but clone() returns `Object` so the compiler has to insert an implicit cast to `int[]`. You can see it in the bytecode, something like this:

```java
ALOAD 1
INVOKEVIRTUAL [I.clone ()Ljava/lang/Object;
CHECKCAST [I
ASTORE 2
```