## 42 Trapping Rain Water









#### Approach 2: Dynamic Programming

**Intuition**

In brute force, we iterate over the left and right parts again and again just to find the highest bar size upto that index. But, this could be stored. Voila, dynamic programming.

The concept is illustrated as shown:

![Dynamic programming](https://leetcode.com/articles/Figures/42/trapping_rain_water.png)

**Algorithm**

- Find maximum height of bar from the left end upto an index i in the array left_maxleft_max.

- Find maximum height of bar from the right end upto an index i in the array right_maxright_max.

- Iterate over the height array and update ans:

  - Add min(max_left[i],max_right[i])−height[i]) to ans
