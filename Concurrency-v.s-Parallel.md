##Concurrency v.s. Parallel

*Concurrency* multiple tasks run simultaneously. Semantic concept, the "time" here may be an abstract concept. You can't tell who is first, who is second.

*Parallel* multiple taks physically run simultaneously. Implementation level concept.  In real time, there are at least executors.

Example: multicore machine, Hadoop clusters.



The ways to perform parallel programming (to physically launch multiple executors):

**Multiprocess vs. multi-thread**

Thread: an indepent execution of instructions with independent memory space, stack, heap, and os resource.

Each process sees a complete memory space.

Different processes communicates through interprocess communication(explicit IPC).



Within a JAVA application you work with several threads to achieve parallel processing or asynchronous behaviour.