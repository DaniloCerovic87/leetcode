package problem18_BinaryTreeInorderTraversal;

import java.util.*;

public class BinaryTreeInorderTraversal {

    public static void main(String[] args) {

        Integer[] input = {1, 2, 3, 4, 5, null, 8, null, null, 6, 7, 9};

        TreeNode root = buildTree(input);

        List<Integer> result = inorderTraversal(root);

        System.out.println(result);
    }

    private static List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {

            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            result.add(current.val);

            current = current.right;
        }

        return result;
    }

    private static void inorder(TreeNode node, List<Integer> result) {
        if(node == null) {
            return;
        }

        inorder(node.left, result);
        result.add(node.val);
        inorder(node.right, result);
    }

    public static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        int i = 1;
        int n = arr.length;

        while (!queue.isEmpty() && i < n) {
            TreeNode current = queue.remove();

            Integer leftVal = arr[i];
            i++;
            if (leftVal != null) {
                TreeNode left = new TreeNode(leftVal);
                current.left = left;
                queue.add(left);
            }

            if (i < n) {
                Integer rightVal = arr[i];
                i++;
                if (rightVal != null) {
                    TreeNode right = new TreeNode(rightVal);
                    current.right = right;
                    queue.add(right);
                }
            }
        }

        return root;
    }

}
