package algorithms.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImperativeTree {

    public static void doubleValues(Node root) {
        if (root == null) return;

        root.val *= 2;
        doubleValues(root.left);
        doubleValues(root.right);
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
