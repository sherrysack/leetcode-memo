## JAVA Stack and Heap and GC ##

All static & instance variables are stored in *PermGen* space of heap memory. If variables are *primitive* type then its value as well stored with them as name-value pair. But if variable is *user-defined*(objects) then its reference is stored in *PermGen* as name-reference pair but actually it is stored in other heap spaces like `Young/Old Generation`.

But Local Variables are stored in *Stack* of its corresponding thread. Similarly method calls are also stored in stack. So each thread has separate copy of local variables.[Know more about Memory Allocation](https://hackthejava.wordpress.com/2015/01/09/memory-architecture-by-jvmruntime-data-areas/).