import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class E05LeetCode104 {

    //获得二叉树深度

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;//叶子结点
        int i=maxDepth(root.left);
        int j=maxDepth(root.right);
        return Math.max(i,j)+1;
    }
    public int maxDepth2(TreeNode root) {


        TreeNode cur = root;
        TreeNode pop = null;
        LinkedList<TreeNode> stack = new LinkedList<>();
        int max=0;//栈的最大高度
        while (cur != null || !stack.isEmpty()) {}
        if (cur != null) {
            stack.push(cur);
            int size = stack.size();
            if (size > max) {
                max = size;
            }
            cur = cur.left;
        }else{
            TreeNode peek=stack.peek();
            //顶的右子树为空或者是上一次被pop的值，那么该节点是探查完的，直接pop掉
            if (peek.right == null || peek.right == pop){
                pop=stack.pop();
            }else{
                //没有谈查完
                cur=peek.right;
            }
        }
        return max;
    }
    public int maxDepth3(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int c1=1;//每层几个元素
        int dep=0;
        while(!queue.isEmpty()){
            int c2=0;
            for(int i=0;i<c1;i++){

                TreeNode node = queue.poll();
                System.out.println(node.val+"\t");
                if (node.left != null) {
                    queue.offer(node.left);
                    c2++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    c2++;
                }
            }
            dep++;
            c1=c2;
            System.out.println();
        }
        return dep;
    }
}
