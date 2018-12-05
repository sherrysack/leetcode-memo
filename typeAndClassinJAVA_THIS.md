# type and class in JAVA

A class is a type. An interface is a type. A primitive is a type. An array is a type.

Therefore, every type is also either a class (including an enum constant), an interface, a primitive, or an array.

There are two distinct categories of types: primitive types and reference types:

·        A variable of primitive type always holds a primitive value of that same type. Such a value can only be changed by assignment operations on that variable.

·        A variable of reference type always holds the value of a reference to an object. All objects, including arrays, support the methods of class `Object`. The reference types are class types (including enum types), interface types, and array types.

Every piece of data has a type which defines its structure, namely how much memory it takes up, how it is laid out, and more importantly, how you can interact with it.

Examples of primitive types:

\1.       `int`

\2.       `float`

\3.       `char`

\4.       `boolean`

Examples of class types:

\1.       [`String`](http://docs.oracle.com/javase/7/docs/api/java/lang/String.html)

\2.       [`Integer`](http://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html)

\3.       [`Boolean`](http://docs.oracle.com/javase/7/docs/api/java/lang/Boolean.html)

\4.       [`ArrayList`](http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html)

\5.       [`StringBuilder`](http://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html)

Examples of interface types:

\1.       [`Collection`](http://docs.oracle.com/javase/7/docs/api/java/util/Collection.html)

\2.       [`List`](http://docs.oracle.com/javase/7/docs/api/java/util/List.html)

\3.       [`Map`](http://docs.oracle.com/javase/7/docs/api/java/util/Map.html)

\4.       [`Serializable`](http://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html)

Examples of array types:

\1.       `int[]`

\2.       `String[]`

\3.       `Integer[][][]`

Basically, anything that you can refer to as a variable has a type, and classes are a kind of a type.



# When to use this in JAVA

The `this` keyword is primarily used in three situations. The first and most common is in setter methods to disambiguate variable references. The second is when there is a need to pass the current class instance as an argument to a method of another object. The third is as a way to call alternate constructors from within a constructor.

**Case 1:** Using `this` to disambiguate variable references. In Java setter methods, we commonly pass in an argument with the same name as the private member variable we are attempting to set. We then assign the argument `x` to `this.x`. This makes it clear that you are assigning the value of the parameter "name" to the instance variable "name".

```java
public class Foo
{
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}
```

**Case 2:** Using `this` as an argument passed to another object.

```java
public class Foo
{
    public String useBarMethod() {
        Bar theBar = new Bar();
        return theBar.barMethod(this);
    }

    public String getName() {
        return "Foo";
    }
}

public class Bar
{
    public void barMethod(Foo obj) {
        obj.getName();
    }
}
```

**Case 3:** Using `this` to call alternate constructors. In the comments, [**trinithis**](https://stackoverflow.com/users/239916/trinithis) correctly pointed out another common use of `this`. When you have multiple constructors for a single class, you can use `this(arg0, arg1, ...)` to call another constructor of your choosing, provided you do so in the first line of your constructor.

```java
class Foo
{
    public Foo() {
        this("Some default value for bar");

        //optional other lines
    }

    public Foo(String bar) {
        // Do something with bar
    }
}
```

I have also seen `this` used to emphasize the fact that an instance variable is being referenced (sans the need for disambiguation), but that is a rare case in my opinion.

[share](https://stackoverflow.com/a/2411283)[improve this answer](https://stackoverflow.com/posts/2411283/edit)

More info here: <http://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html>