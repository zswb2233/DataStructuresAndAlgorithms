import java.util.LinkedList;
import java.util.Queue;

public class E06LeetCode111_1 {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if(left==0) return right + 1;
        if(right==0) return left + 1;
        return Math.min(left, right) + 1;
    }
    public int minDepth1(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int c1=1;//每层几个元素
        int dep=0;
        while(!queue.isEmpty()){
            dep++;
            int c2=0;
            for(int i=0;i<c1;i++){

                TreeNode node = queue.poll();
                System.out.println(node.val+"\t");
                if(node.left==null && node.right==null){
                    return dep;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                    c2++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    c2++;
                }
            }

            c1=c2;
            System.out.println();
        }
        return dep;
    }

}
