public class TreeNode {
    public TreeNode left;
    public int val;

    public TreeNode right;
    public TreeNode(int val) {
        this.val = val;
    }
    public TreeNode( TreeNode left, int val,TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {return String.valueOf(val);}
}
