import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static Node<Integer> root;
    private static ArrayList<Integer> arr = new ArrayList<>();

    public Main() {
        this.root = null;
    }

    public static void add(Node node, Integer num) {
        if (root == null) {
            root = new Node<>(num);
            return;
        } else if (node.getD() < num && node.getRight() == null) {
            node.setRight(new Node(num));
            return;
        } else if (node.getD() > num && node.getLeft() == null) {
            node.setLeft(new Node(num));
        } else if (node.getD() < num)
            add(node.getRight(), num);
        else if (node.getD() > num)
            add(node.getLeft(), num);
    }

    public static ArrayList<Integer> in(Node node) {

        if (node == null)
            return arr;
        else {
            in(node.getLeft());
            arr.add(node.getD());
            in(node.getRight());
        }


        return null;
    }

    public static void delete(int[] tree, int n, int i) {
        i = tree[1];
        int last = tree[n];
        n--;
        int loc = 1, left = 2, right = 3;
        while (right < n) {
            if (last >= tree[left] && last >= tree[right]) {
                tree[loc] = last;
            }
            if (tree[right] <= tree[left]) {
                tree[loc] = tree[left];
                loc = left;
            } else {
                tree[loc] = tree[right];
                loc = right;
            }
            left = loc * 2;
            right = loc * 2 + 1;
            if (left == n && last < tree[loc]) {
                tree[loc] = tree[left];
                loc = left;
            }
            tree[loc] = i;
        }
    }

    public static void main(String[] args) {
        int[] tree = {1, 2, 3, 4, 5, 6, 7};
        delete(tree, tree.length, 1);
        System.out.println(Arrays.toString(tree));
    }
}
