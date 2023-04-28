import java.util.HashSet;
import java.util.Set;

/**
 * Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
 * beginning of the loop.
 *
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, to make a loop in the linked list.
 *
 * EXAMPLE
 * Input: 1 - > 2 - > 3 - > 4 - > 5 - > 3 [the same 3 as earlier)
 * Output: 3
 */
public class Main {

  /**
   * The 1st approach for solving it would be to use a set in which to store all the visited nodes. If we encounter an already
   * visited node -> this is the start of the loop. This would work because a corrupted linked list will definitely point to an earlier node
   * that we already visited, if we continue traversing until a node is not null.
   *
   * Time Complexity: O(n)
   * Space Complexity: O(n)
   */
  public static Node findLoopStartV1(Node n) {
    if (n == null) {
      return null;
    }

    Set<Node> visitedNodes = new HashSet<>();

    while (n != null) {
      if (visitedNodes.contains(n)) {
        // Head of the loop
        return n;
      }

      visitedNodes.add(n);

      n = n.next;
    }

    return null;
  }

  /**
   * To get rid of the Space Complexity of O(n) we could use <link>Floyd Loop detection</link> algorithm (Hare-Tortoise). It works by using <b>slow</b> & <b>fast</b>
   * pointers. The <b>slow</b> pointer moves <b>1</b> position at a time while the <b>fast 2x</b>. So when the slow pointer reached n-th position, the fast
   * would be at 2n-th one. The initial goal is to prove there is a loop, and this could be done if the two pointers collide at some point.
   *
   * <info>To explain why this would work if a loop exists:
   * Let's denote the non-loop part of the linked list with k. When the slow pointer passed k steps, it would be located at the loop start. The fast one would've
   * passed 2k steps, which means k=(2k-k) steps into the loop. Since k could exceed the loop_length, we will denote it as mod(k, loop_length) = K,
   * to always be within limits.
   *
   * Using the information above, we conclude that the fast pointer is (loop_length - K) positions behind slow pointer and they will collide after (loop_length - K)
   * positions (slow moving 1x, fast 2x).
   *
   * The point of collision will be located at (loop_length - K) position, which means there are still K steps until the head of the loop is reached.
   * The non loop part of the linked list is also K, so if we set any pointer (let's choose the fast one) in the head of the linked list and start moving it by
   * 1 position at a time and at the same time moving the slow pointer from the collision point 1 position at a time, they will collide at the beginning of the loop.
   * </info>
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   */
  public static Node findLoopStartV2(Node n) {
    if (n == null || n.next == null) {
      return null;
    }

    Node slow = n;
    Node fast = n;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast) {
        break;
      }
    }

    if (slow != fast) {
      return null;
    }

    fast = n;

    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }

    return slow;
  }
}
