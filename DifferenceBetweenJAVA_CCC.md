## Difference between JAVA & C++

- **Platform compatible, write once, compile onece, run everywhere on JVM**

  1. C++: write once, compile everywhere
  2. JRE vs JDK
     - JRE (JAVA Runtime Environment) is the JVM program, JAVA application need to run on JRE
     - JDK contains the tools for developing JAVA program running on JRE, for example, it provides the compiler "javac".

- Compiles to Java byte code can be recognized by JVM, independent to underline OS.

- Interpreter to routines on systems with different machine codes.

- All types(references types, primitive types) are always pass by value

- JAVA doesn't support unsigned

- JVM - is an abstract computing machine. The JVM is a program that looks like a machine to the program written to excute it .

  Compile/Interpret language

  1. JAVA source code -> JVM readbale byte code(.java->.class)
  2. JVM interpret the JAVA byte code to precompiled routine on machine code.
  3. JustInTime(JIT) compilation to compile the bytecode to native intstructions.

  *The current version of Sun HotSpot JVM uses a technique called JIT compilation to compile bytecode

  to compile the bytecode to the native instructions understood by the CPU on the fly at run time.

- Difference between pointers in C++ and reference in JAVA:

  1. references in JAVA are strong typed

  2. there is no pointer arithmetic on references

     pointers in C++ are much more powerful but also more dangerous

  Stack: local variable, tracking method call flow, return address etc. - one per thread

  Heap: dynamic memory allocation - only one per JVM

  The heap is where your object data is sorted. This area is then managed by the garbage collector selected at startup.

- **Where is Automatic Garbage Collection?

- Automatic gc is the process of looking at heap memory, identifying which objects are in useand which are not, and delteing the unused objects. 

  In a prgorammimg language like C, gc is a manual process. 

  1. Step one - marking Memory Allocator holds a list of references to free spaces and searches for free space whenever an allocation is required.

     JVM Generation

     The information learned from the object

- ​

  ​