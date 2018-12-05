public class OuterClassTest {
    //How to create instance of static and nonstatic nested class?
    public static void main(String args[]) {
        //create instancd of nested Static class
        OuterClass.NestedStaticClass printer = new OuterClass.NestedStaticClass();
        
        //call non static method of nested static class
        printer.printMessage();
        
        // In order to create instance of Inner class we need an Outer class
        // instance. Let us create Outer class instance for creating non-static nested class
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        OuterClass.InnerClass inner1 = (new OuterClass()).new InnerClass();
        
        // call non static method of inner calss
        inner.display();
        
        //when we use static class - public service
        //when we use nonstatic nested class - private service
        // so we prefer static nested class than inner class
        
        //how inner class find its outer class instance
        // OuterClassName.this can be used to
        // this can only be used to refer the instance
        
        // only nested class can have static class and nonstatic class
        // inner class can only have nonstatic method
        
    }
}


// Class can also be defined in method, e.x. Anonymous class

public class AnonymousInnerClass {
    public void test() {//Runnable is an interface
        new Thread(-> {
            System.out.println("Hello from Lambda!");
            
        }).start();
        
    
    //non anonymous
    class NoneAnnoymousClass implements Runnable {
        public void run() {
            
        }
    }
    
    NoneAnnoymousClass t = new NoneAnonymousClass();
    new Thread(t).start();
    }
}

/*
Multiple top-level calss in the same java file

only one top-level class can only be declared public in a java file.
The others ave no access modifier(private, protected) nor static. Usually these are helper clsses.
The purpose of including multiple classes in one source file is to bundle related support functionality
(internal data structures, support classes, etc) together with the main public class.

Top level non-public v.s. Nested class -> public TLC in the same file

Difference:
1. A nested class has access to private members of the outer class but helper class not.
2. You may want yuour helper class to help multiple classes in the same packages
3. A static nested class can be declared public but not for helper class.
4. By design: Nesting a class (statically in JAVA) sends a clear message of intent: the nested class.
A helper is only relevant and usable to support A class. It has no meaning on its own.
*/








































