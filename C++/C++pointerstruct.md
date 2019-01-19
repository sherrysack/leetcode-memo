首先介绍一下C++中的结构。对于一个结构来说：

```
struct MyStruct
{ 
      int member_a; 
};1234
```

如果有个变量**MyStruct s**，那么使用其中的成员元素时可以用

```
s.member_a = 1;1
```

如果采用指针方法访问，比如 **MyStruct \* ps**，那么同样的访问就必须使用如下形式：

```
(*ps).member_a = 1;1
```

或者

```
ps->member_a = 1;1
```

c++中当定义类对象是指针对象时候，就需要用到 **“->”** 指向类中的成员；当定义一般对象时候时就需要用到 **“.”** 指向类中的成员……. 
例如：

```
class A 
{ 
      public:
      play(); 
}12345
```

如果定义如下：

A *p则：p->play()使用; 左边是结构指针。 
A p 则：p.paly()使用; 左边是结构变量。

总结： 
**箭头（->）：左边必须为指针；** 
**点号（.）：左边必须为实体。**

------

补充：

C++语言为包含点操作符和解引用操作符的表达式提供了一个同义词：箭头操作符（->）。点操作符用于获取类类型对象的成员：

```
item1.same_isbn(item2); // run the same_isbn member of item11
```

如果有一个指向Sales_item对象的指针（或迭代器），则在使用点操作符前，需对该指针（或迭代器）进行解引用：

```
Sales_item *sp = &item1;
(*sp).same_isbn(item2); // run same_isbn on object to which sp points12
```

这里，对sp进行解引用以获得指定的Sales_item对象。然后使用点操作符调用指定对象的same_isbn成员函数。在上述用法中，注意必须用圆括号把解引用括起来，因为解引用的优先级低于点操作符。如果漏掉圆括号，则这段代码的含义就完全不同了：

```
// run the same_isbn member of sp then dereference the result!
*sp.same_isbn(item2); // error: sp has no member named same_isbn12
```

这个表达式企图获得sp对象的same_isbn成员。等价于：

```
*(sp.same_isbn(item2));  // equivalent to *sp.same_isbn(item2);1
```

然而，sp是一个没有成员的指针；这段代码无法通过编译。 
因为编程时很容易忘记圆括号，而且这类代码又经常使用，**所以C++为在点操作符后使用的解引用操作定义了一个同义词：箭头操作符（->）。**假设有一个指向类类型对象的指针（或迭代器），下面的表达式相互等价：

```
(*p).foo;  // dereference p to get an object and fetch its member named foo
p->foo;    // equivalent way to fetch the foo from the object to which p points12
```

具体地，可将same_isbn的调用重写为：

```
sp->same_isbn(item2);     // equivalent to (*sp).same_isbn(item2)
```