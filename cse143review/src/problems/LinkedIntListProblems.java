package problems;

import datastructures.LinkedIntList;
// Checkstyle will complain that this is an unused import until you use it in your code.
import datastructures.LinkedIntList.ListNode;

/**
 * See the spec on the website for example behavior.
 *
 * REMEMBER THE FOLLOWING RESTRICTIONS:
 * - do not call any methods on the `LinkedIntList` objects.
 * - do not construct new `ListNode` objects for `reverse3` or `firstToLast`
 *      (though you may have as many `ListNode` variables as you like).
 * - do not construct any external data structures such as arrays, queues, lists, etc.
 * - do not mutate the `data` field of any node; instead, change the list only by modifying
 *      links between nodes.
 */

public class LinkedIntListProblems {

    /**
     * Reverses the 3 elements in the `LinkedIntList` (assume there are exactly 3 elements).
     */
    public static void reverse3(LinkedIntList list) {
        ListNode first = list.front;
        ListNode second = first.next;
        ListNode third = second.next;

        third.next = second;
        second.next = first;
        first.next = null;

        list.front = third;


    }

    /**
     * Moves the first element of the input list to the back of the list.
     */
    public static void firstToLast(LinkedIntList list) {
      if (list.front == null || list.front.next == null) {
          return;
        }
      ListNode temp = list.front;
      list.front = list.front.next;
      ListNode front = list.front;
      while (front.next != null){
          front = front.next;
      }
      front.next = temp;
      temp.next = null;

    }

    /**
     * Returns a list consisting of the integers of a followed by the integers
     * of n. Does not modify items of A or B.
     */
    public static LinkedIntList concatenate(LinkedIntList a, LinkedIntList b) {
        // Hint: you'll need to use the 'new' keyword to construct new objects.

        LinkedIntList result = new LinkedIntList();

        if (a.front == null && b.front == null){
            return result;
        }

        if (a.front == null){
            return b;
        }
        if (b.front ==null){
            return a;
        }
        result.front = new ListNode(a.front.data);
        ListNode curr = result.front;
        ListNode aCurr = a.front.next;

        while (aCurr != null){
            curr.next = new ListNode(aCurr.data);
            aCurr = aCurr.next;
            curr = curr.next;
        }

        ListNode bCurr = b.front;
        while (bCurr != null){
            curr.next = new ListNode(bCurr.data);
            bCurr = bCurr.next;
            curr= curr.next;
        }
        return result;






    }
}
