package task2;

import com.annyarusova.task2.AvlTree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AvlTreeTests {
    @Test
    @DisplayName("Check insertion with left rotation")
    void checkInsertionWithLeftRotation() {
        AvlTree<Integer> avlTree = new AvlTree<>();

        for (int i = 0; i < 3; i++) {
            avlTree.insert(i);
        }

        assertEquals(1, avlTree.root.element);
        assertEquals(0, avlTree.root.left.element);
        assertEquals(2, avlTree.root.right.element);
        assertEquals("0 1 2", avlTree.print());
    }

    @Test
    @DisplayName("Check insertion with double left rotation")
    void checkInsertionWithDoubleLeftRotation() {
        AvlTree<Integer> avlTree = new AvlTree<>();

        avlTree.insert(0);
        avlTree.insert(2);
        avlTree.insert(1);


        assertEquals(1, avlTree.root.element);
        assertEquals(0, avlTree.root.left.element);
        assertEquals(2, avlTree.root.right.element);
        assertEquals("0 1 2", avlTree.print());
    }

    @Test
    @DisplayName("Check insertion right rotation")
    void checkInsertionWithRightRotation() {
        AvlTree<Integer> avlTree = new AvlTree<>();

        for (int i = 2; i >= 0; i--) {
            avlTree.insert(i);
        }

        assertEquals(1, avlTree.root.element);
        assertEquals(0, avlTree.root.left.element);
        assertEquals(2, avlTree.root.right.element);
        assertEquals("0 1 2", avlTree.print());
    }

    @Test
    @DisplayName("Check insertion with double right rotation")
    void checkInsertionWithDoubleRightRotation() {
        AvlTree<Integer> avlTree = new AvlTree<>();

        avlTree.insert(2);
        avlTree.insert(0);
        avlTree.insert(1);

        assertEquals(1, avlTree.root.element);
        assertEquals(0, avlTree.root.left.element);
        assertEquals(2, avlTree.root.right.element);
        assertEquals("0 1 2", avlTree.print());
    }

    @Test
    @DisplayName("Check find existing element")
    void checkFindExistingElement() {
        AvlTree<Integer> avlTree = new AvlTree<>();

        for (int i = 0; i < 3; i++) {
            avlTree.insert(i);
        }

        for (int i = 0; i < 3; i++) {
            assertTrue(avlTree.find(i));
        }
    }

    @Test
    @DisplayName("Check find non-existing element")
    void checkFindNonExistingElement() {
        AvlTree<Integer> avlTree = new AvlTree<>();

        for (int i = 0; i < 3; i++) {
            avlTree.insert(i);
        }

        assertFalse(avlTree.find(3));
    }

    @Test
    @DisplayName("Check printing elements in order")
    void checkPrintingElementsInOrder() {
        AvlTree<Integer> avlTree = new AvlTree<>();

        for (int i = 0; i < 3; i++) {
            avlTree.insert(i);
        }

        assertEquals("0 1 2", avlTree.print());
    }

    @Test
    @DisplayName("Check deleting leaf elements")
    void checkDeletingLeafElements() {
        AvlTree<Integer> avlTree = new AvlTree<>();

        for (int i = 0; i < 3; i++) {
            avlTree.insert(i);
        }

        avlTree.delete(2);
        assertFalse(avlTree.find(2));
        assertEquals(1, avlTree.root.element);
        assertEquals(0, avlTree.root.left.element);
        assertEquals("0 1", avlTree.print());

        avlTree.delete(0);
        assertFalse(avlTree.find(0));
        assertEquals(1, avlTree.root.element);
        assertEquals("1", avlTree.print());
    }

    @Test
    @DisplayName("Check deleting root element")
    void checkDeletingRootElement() {
        AvlTree<Integer> avlTree = new AvlTree<>();

        for (int i = 0; i < 3; i++) {
            avlTree.insert(i);
        }

        avlTree.delete(1);
        assertFalse(avlTree.find(1));
        assertEquals("0 2", avlTree.print());
    }

    @Test
    @DisplayName("Check rebalancing after deleting element")
    void checkRebalancingAfterDeletingElement() {
        AvlTree<Integer> avlTree = new AvlTree<>();

        for (int i = 0; i < 4; i++) {
            avlTree.insert(i);
        }

        avlTree.delete(0);
        assertFalse(avlTree.find(0));
        assertEquals(2, avlTree.root.element);
        assertEquals(1, avlTree.root.left.element);
        assertEquals(3, avlTree.root.right.element);
    }
}
