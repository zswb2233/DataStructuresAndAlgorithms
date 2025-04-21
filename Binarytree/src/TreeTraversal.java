import java.util.Stack;

public class TreeTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(null,5, null), 3, new TreeNode(6)));
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();

        preOrder2(root);
        System.out.println();
        inOrder2(root);
        System.out.println();
        postOrder2(root);
        System.out.println();
    }

    //前序遍历
    static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + "\t"); //值
        preOrder(root.left);
        preOrder(root.right);
    }
    static void preOrder2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return;
        }
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + "\t");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }
    //中序遍历
    static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val + "\t"); //值
        inOrder(root.right);
    }
    static void inOrder2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (true) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            if (stack.isEmpty()) {
                break;
            }
            current = stack.pop();
            System.out.print(current.val + "\t");
            current = current.right;
        }
    }
    //后序遍历
    static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + "\t"); //值
    }
    static void postOrder2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            if (current.right == null || current.right == prev) {
                System.out.print(current.val + "\t");
                prev = current;
                current = null;
            } else {
                stack.push(current);
                current = current.right;
            }
        }
    }
}