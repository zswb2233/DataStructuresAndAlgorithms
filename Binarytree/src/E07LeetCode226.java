public class E07LeetCode226 {
    public static TreeNode invertTree(TreeNode root) {
        fn(root);
        return root;
    }
    private static void fn(TreeNode root) {
        if (root == null) return;
        TreeNode t=root.left;
        root.left=root.right;
        root.right=t;
        fn(root.left);
        fn(root.right);
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(null,5, null), 3, new TreeNode(6)));
        preOrder(root);
        System.out.println();
        TreeNode root2=invertTree(root);
        preOrder(root2);
    }
    static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + "\t"); //值
        preOrder(root.left);
        preOrder(root.right);
    }
}
