import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

  @Test
  public void findLoopStartV1() {
    Assertions.assertNull(Main.findLoopStartV1(null));
    Assertions.assertNull(Main.findLoopStartV1(build(1)));
    Assertions.assertNull(Main.findLoopStartV1(build(1, 2, 3)));

    Node looped1 = build(1, 2, 3, 4, 5, 6, 7, 8);
    Node loopStart1 = getNode(looped1, 4);
    Node loopEnd1 = getNode(looped1, 8);
    loopEnd1.next = loopStart1;

    Assertions.assertSame(loopStart1, Main.findLoopStartV1(looped1));

    Node looped2 = build(1, 2, 3, 4, 5, 6, 7, 8);
    Node loopStart2 = getNode(looped2, 8);
    Node loopEnd2 = getNode(looped2, 8);
    loopEnd2.next = loopStart2;

    Assertions.assertSame(loopStart2, Main.findLoopStartV1(looped2));

    Node looped3 = build(1, 2, 3, 4, 5, 6, 7, 8);
    Node loopStart3 = getNode(looped3, 1);
    Node loopEnd3 = getNode(looped3, 8);
    loopEnd3.next = loopStart3;

    Assertions.assertSame(loopStart3, Main.findLoopStartV1(looped3));
  }

  @Test
  public void findLoopStartV2() {
    Assertions.assertNull(Main.findLoopStartV2(null));
    Assertions.assertNull(Main.findLoopStartV2(build(1)));
    Assertions.assertNull(Main.findLoopStartV2(build(1, 2, 3)));

    Node looped1 = build(1, 2, 3, 4, 5, 6, 7, 8);
    Node loopStart1 = getNode(looped1, 4);
    Node loopEnd1 = getNode(looped1, 8);
    loopEnd1.next = loopStart1;

    Assertions.assertSame(loopStart1, Main.findLoopStartV2(looped1));

    Node looped2 = build(1, 2, 3, 4, 5, 6, 7, 8);
    Node loopStart2 = getNode(looped2, 8);
    Node loopEnd2 = getNode(looped2, 8);
    loopEnd2.next = loopStart2;

    Assertions.assertSame(loopStart2, Main.findLoopStartV2(looped2));

    Node looped3 = build(1, 2, 3, 4, 5, 6, 7, 8);
    Node loopStart3 = getNode(looped3, 1);
    Node loopEnd3 = getNode(looped3, 8);
    loopEnd3.next = loopStart3;

    Assertions.assertSame(loopStart3, Main.findLoopStartV2(looped3));
  }

  private static Node build(Integer... values) {
    Node prev = null;
    Node head = null;

    for (Integer value : values) {
      Node n = new Node(value);

      if (prev == null) {
        head = n;
      } else {
        prev.next = n;
      }

      prev = n;
    }

    return head;
  }

  private static Node getNode(Node n, int pos) {
    if (pos <= 0) {
      return null;
    }

    while (pos-- > 1 && n != null) {
      n = n.next;
    }

    return n;
  }
}
