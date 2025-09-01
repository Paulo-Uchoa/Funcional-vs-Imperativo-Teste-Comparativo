package algorithms.tree;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionalTree {

    public static Node doubleValues(Node root){
        if(root == null) return null;

        return new Node (
                root.val * 2,
                doubleValues(root.left),
                doubleValues(root.right)
        );
    }

    public static List<Integer> collectEven(Node root){
        if(root == null) return List.of();

        return Stream.concat(
                Stream.concat(
                        collectEven(root.left).stream(),
                        Stream.of(root.val)
                ),
                collectEven(root.right).stream()
        )
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
    }
}
