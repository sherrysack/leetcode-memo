## 6 ways to declare JS funcitons

- function declaration

  ```javascript
  //function declaration
  function isEven(num) {
    return num %2 == 0;
  }
  isEven(24); // => true
  ```

  It is easy to confuse the function declaration and function expression. They look very similar, but produce functions with different properties. An easy to remember rule: the function delcaration is a statement always starts with the keyword function. Otherwise it's a function expression.

  ​

- function expression

  ```javascript
  //function expression: starts with "var"
  var isTruthy = function(value) {
    return !!value;
  }
  //function expression: an argument for .filter()
  var numbers = ([1, false, 5]).filter(function(item) {
    return typeof item === 'number';
  })
  //function expression(IIFE): starts with "("
  (function messageFunction(meassage)) {
   return message+"world";
   })('hello');
  ```

  ​

- shorthand method definition

  can be used in a method declaration on object literals and ES6 classes. You can define them using a function name, followed by a list of parameters in a pair of parenthesis(para1, para2,  …, paraN) and a pair of curly braces that delimits the body statements. 

  ```javascript
  var collection = {
    items: [],
    add(...items) {
      this.items.push(...items);
    },
    get(index) {
      return this.items[index];
    }
  };
  collection.add('C', 'java', 'PHP');
  collection.get(1) //=> 'Java'
  ```

- arrow function

  An arrow function is defined using a pair of parenthesis that contains the list of parameters (param1, param2, … paramN), followed by a fat arrow => and a pair of {} that delimits the body statements

  ```java
  var absValue = (number) => {
    if(number < 0) {
      return -number;
    }
    return number;
  }
  absValue(-19); //=> 19
  absValue(5); //=> 5


  ```

  ​

- generator funciton

- Function constructor

  ​

## Functions declaration in conditionals

some JS environemnts can throw a reference error when invoking a function whose declaration appears within blocks {…} of if , for or whie statements.

Let's enable the strict mode and see what happens when a function is declared in a conditional:

```javascript
(function() {
  'use strict';
  if (true){
    function ok() {
      return 'trueok';
    }
  }else {
    function ok() {
      return 'false ok';
    }
  }
  console.log(typeof ok === 'undefined');//=>true
  console.log(ok()); //throws "referenceError" ok is not defined"
})();
```

when calling ok(), JS throws referenceError, ok is not defined, because the function declaration is inside a conditional block. Notice that this scenario works well in a non-strict mode, which make it even more confusing. 

As a general rule for these situations, when a function should be created based on some conditions - use a function expression, let's see how it's possible:

```javascript
(function() {
  'use strict';
 	var ok;
  if(true) {
    ok = function() {
      return 'turn ok';
    };
  }else {
    ok = function() {
      return 'false ok';
    };
  }
  console.log(typeof ok === 'function'); // =>true
  console.log(ok()); //=>'true ok'
})();
```

Because the function is a regular object, it is fine to assign it to a variable depending on a condition. Invoking ok() works fine and does not throw any errors.



####the function expression creates a function object that can be used in different situation.

```javascript
var count = function(array) {
  return array.length;
}
var methods = {
  numbers: [1,5,8],
  sum: function() { //create a method on an object sum
    return this.numbers.reduce(function(acc, num) { //use this function as a callback
      return acc+num; 
    })
  }
count([5，7，8]); //=>3
method.sum(); //=>14
```



