25. Reverse Nodes in k-Group

Given a linked list, reverse the nodes of a linked list *k* at a time and return its modified list.

*k* is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of *k* then left-out nodes in the end should remain as it is.


**Example:**

Given this linked list: `1->2->3->4->5`

For *k* = 2, you should return: `2->1->4->3->5`

For *k* = 3, you should return: `3->2->1->4->5`

**Note:**

- Only constant extra memory is allowed.

- You may not alter the values in the list's nodes, only nodes itself may be changed.

  My clear but not optimized solution

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode[] reverse(ListNode head, int k, ListNode[] res) {
        //ListNode[] res = new ListNode[2];
        res[1] = head;
        ListNode prev = null;
        int cnt = 0;
        while(cnt < k) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
            cnt++;
        }
        res[0] = prev;
        System.out.println(res[0].val);
        return res;
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k == 0) return head;
        ListNode slow = head, fast = head;
        boolean first = true;
        ListNode dummy = new ListNode(9);
        dummy.next = head;
        ListNode prev = new ListNode(0);
        ListNode[] temp = new ListNode[2];
        while(fast != null) {
            int i = 0;
            for(; (i < k-1) && (fast != null); i++) {
                fast = fast.next;
            }
            //the remaining linked list is less than k group
            if(i != (k-1) || (fast == null)) {
                if(!first) {
                    prev.next = slow;
                }
                return dummy.next;
            }
            fast = fast.next;
            //reverse the linked list from slow to fast
            reverse(slow, k, temp);
            if(first) {
                dummy.next = temp[0];
                //here is the first initialization for prev
                prev = temp[1];
                first = false;
            }else {
                //link the previous k group's end to the new k group's head
                prev.next = temp[0];
                prev = temp[1];
            }
            //the linked list is finished
            if(fast == null) return dummy.next;
            slow = fast;
        }
        
        return dummy.next;
    }
}
```

