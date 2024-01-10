import java.io.*;
import java.util.StringTokenizer;

public class H38 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            int[] preO = new int[n];
            int[] inO = new int[n];
            for (int j = 0; j < n; j++) {
                preO[j] = in.nextInt();
            }
            for (int j = 0; j < n; j++) {
                inO[j] = in.nextInt();
            }
            TreeNode x = new TreeNode(0, null, null);
            TreeNode.postOrder(x.build(preO, 0, preO.length, inO, 0, inO.length), out);
            out.println(" ");
        }
        out.close();
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x, TreeNode left, TreeNode right) {
        val = x;
        this.left = left;
        this.right = right;
    }
    public TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart >= preEnd || inStart >= inEnd) {
            return null;
        }
        int inIndex = 0;
        for (int i = inStart; i < inEnd; i++) {
            if (inorder[i] == preorder[preStart]) {
                inIndex = i;
                break;
            }
        }
        return new TreeNode(preorder[preStart], build(preorder, preStart + 1, preStart + inIndex - inStart + 1, inorder, inStart, inIndex), build(preorder, preStart + inIndex - inStart + 1, preEnd, inorder, inIndex + 1, inEnd));
    }
    public static void postOrder(TreeNode node, QWriter out) {
        if (node == null) {
            return;
        }
        postOrder(node.left, out);
        postOrder(node.right, out);
        out.print(node.val + " ");
    }
}



