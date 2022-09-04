package August;

public class the_30 {
}

class Solution998 {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode node = new TreeNode(val);
        if(val>root.val) {
            node.left = root;
            return node;
        }
        DFS(root, root.right, node);
        return root;
    }

    private void DFS(TreeNode root, TreeNode right, TreeNode node) {
        if(right==null || right.val<node.val) {
            root.right = node;
            node.left = right;
            return;
        }
        DFS(right, right.right, node);
    }
}
