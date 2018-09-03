## what to use when you are doing permutation

The difference is that in JAVA, String is a wrapper class type

- it is not a plain char array, the immutability doesn't allow you to modify it.

We need to:

- Make the requirement clear, is it allowed to use some of the very efficient JAVA uitliies
  - Can use StringBuilder?
  - Can use the existing method in String? e.g. trim(), replace()
- Usually two strategies:
  - char[] array = input.toCharArray(). to do whatever we need to do on the char array, then new String(array), String(array, offset, length)
  - use StringBuilder builder, utilize the append(), deleteCharAt(); use builder.toString, new String(builder) to convert it back.



**All Subsets (distinct SubSequence) of a sorted String u without duplicate characters **

Use StringBuilder because

- We don't want to create too many String obejcts
- we need a lot of append(), deleteCharAt(), 





