package algorithms.tree;

import java.util.ArrayList;
import java.util.List;

public class ImperativeTree {

    public static void tripleValues(Node root) {
        if (root == null) return;

        root.val *= 3;
        tripleValues(root.left);
        tripleValues(root.right);
    }

    public static List<Integer> collectEven(Node root) {
        List<Integer> result = new ArrayList<>();
        collectEvenHelper(root, result);
        return result;
    }

    private static void collectEvenHelper(Node root, List<Integer> result) {
        if (root == null) return;

        collectEvenHelper(root.left, result);
        if (root.val % 2 == 0) {
            result.add(root.val);
        }
        collectEvenHelper(root.right, result);
    }
}
