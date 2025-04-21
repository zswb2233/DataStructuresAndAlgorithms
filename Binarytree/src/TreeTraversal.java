

public class TreeTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(new TreeNode(new TreeNode(4),2,null),
                1,
                new TreeNode(new TreeNode(5),3,new TreeNode(6)));
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();

    }
    //前序遍历
    static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val+"\t");//值
        preOrder(root.left);
        preOrder(root.right);
    }
    //中序遍历
    static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.val+"\t");//值

        inOrder(root.right);
    }
    //后序遍历
    static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);


        postOrder(root.right);
        System.out.print(root.val + "\t");//值
    }
}
