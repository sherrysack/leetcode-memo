## How to implement deque with 3 stacks

 



Define three stack, use two stacks as front and back of the deque and the third stack as middle which will be used while reversing sequence of elements from the first 2 stacks.

• In size method, return the sum of size of three stacks.

• In isEmpty method, return if the deque is empty.

• In pushLeft method;

o Push the given item at the top of left stack, which denotes left of deque.

• In pushRight method;

o Push the given item at the top of right stack, which denotes right of deque.

• In popLeft method;

o Since it is pop from left, first check if left stack has any element. If yes, pop and return it.

o If not, check the middle stack.

• If it has any element, pop out all its elements and push them in left stack.

• Now pop out the topmost element from left stack and return it.

o If middle stack is also empty, lastly look in the right stack.

• Pop out all its elements and push them in middle stack.

• Now pop out the topmost element from middle stack and return it.

• In popRight method;

o Since it is pop from right, first check if right stack has any element. If yes, pop and return it.

o If not, check the middle stack.

• If it has any element, pop out all its elements and push them in right stack.

• Now pop out the topmost element from right stack and return it.

o If middle stack is also empty, lastly look in the left stack.

• Pop out all its elements and push them in middle stack.

• Now pop out the topmost element from middle stack and return it.

• In printDeq method;

o First print the left stack, then middle and right stack.

• In main method;

o Get elements from standard input and push them in deque using pushLeft and pushRight methods. Press ctrl+z to stop.

o Print the elements in three stacks.

o Pop out the elements from deque and print them using popLeft and popRight methods

• Note that all the deque operation takes constant amortized number of stack operations.