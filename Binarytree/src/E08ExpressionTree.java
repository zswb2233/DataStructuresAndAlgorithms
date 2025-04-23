import java.util.Arrays;
import java.util.LinkedList;

public class E08ExpressionTree {
    public TreeNode constructExpressionTree(String[] tokens){
        LinkedList<TreeNode> stack = new LinkedList<>();
        for(String token : tokens){
            switch(token){
                case "+","-","*","/"->{
                    //运算符
                    TreeNode right=stack.pop();
                    TreeNode left=stack.pop();
                    TreeNode root = new TreeNode((int)token.charAt(0));
                    root.left = left;
                    root.right = right;
                    stack.push(root);
                }
                default -> {
                    //数字，压栈
                    stack.push(new TreeNode((int)token.charAt(0)));
                }
            }
        }
        return stack.peek();
    }
    public TreeNode buildTree(int[] preorder,int[] inorder){
        //创建根节点
        int rootValue=preorder[0];
        TreeNode root=new TreeNode(rootValue);
        //区分左右子树
        for(int i=0;i< inorder.length;i++){
            if(inorder[i]==rootValue){
                int[] inleft= Arrays.copyOfRange(inorder,0,i);
                int[] inright=Arrays.copyOfRange(inorder,i+1,inorder.length);
                int[] preleft=Arrays.copyOfRange(preorder,1,i+1);
                int[] preright=Arrays.copyOfRange(preorder,i+1,preorder.length);
                root.left=buildTree(preleft,inleft);
                root.right=buildTree(preright,inright);
            }
        }
        return root;

    }
}
