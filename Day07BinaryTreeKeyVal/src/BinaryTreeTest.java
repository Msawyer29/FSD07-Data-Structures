import org.junit.jupiter.api.*;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {
    BinaryTree<String, Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinaryTree<>();
        tree.put("D", 4);
        tree.put("B", 2);
        tree.put("A", 1);
        tree.put("C", 3);
        tree.put("E", 5);
    }

    @Test
    void getValByKey() {
        assertEquals(Integer.valueOf(1), tree.getValByKey("A"));
        assertEquals(Integer.valueOf(2), tree.getValByKey("B"));
        assertEquals(Integer.valueOf(3), tree.getValByKey("C"));
        assertEquals(Integer.valueOf(4), tree.getValByKey("D"));
        assertEquals(Integer.valueOf(5), tree.getValByKey("E"));
    }

    @Test
    void getValByKey_KeyNotFound() {
        assertThrows(RuntimeException.class, () -> tree.getValByKey("Z"));
    }

    @Test
    void iteratorTest() {
        Iterator<BinaryTree.Pair<String, Integer>> iterator = tree.iterator();
        assertTrue(iterator.hasNext());
        BinaryTree.Pair<String, Integer> pair = iterator.next();
        assertEquals("A", pair.getKey());
        assertEquals(Integer.valueOf(1), pair.getValue());
    }
}
