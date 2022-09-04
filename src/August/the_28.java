package August;

import java.util.*;

public class the_28 {
    public static void main(String[] args) {
//        new Solution105().buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});

//        new Solution106().buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3});
        TreeNode t1 = new TreeNode(-10);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        t1.left=t2;t1.right=t3;t3.left=t4;t3.right=t5;
//        new Solution129().sumNumbers(t1);

        new Solution124().maxPathSum(t1);
    }
}

class Solution793 {
    public int preimageSizeFZF(int k) {
        if(k<=1)
            return 5;
        return f(k) - f(k-1);
    }

    private int f(int k) {
        long l = 0, r = (long)1e10;
        while (l<r) {
            long m = (l+r+1) >> 1;
            if(getCount(m)<=k)
                l = m;
            else
                r = m - 1;
        }
        return (int)r;
    }

    private long getCount(long m) {
        long ans = 0;
        while (m!=0) {
            ans += m/5;
            m /= 5;
        }
        return ans;
    }
}

class Solution101 {
    public boolean isSymmetric(TreeNode root) {
        if(root==null)
            return true;
        return DFS(root.left, root.right);
    }

    private boolean DFS(TreeNode left, TreeNode right) {
        if( (left==null && right!=null) || (left!=null && right==null) )
            return false;
        if(left==null && right==null)
            return true;
        if(left.val!=right.val)
            return false;
        return DFS(left.left, right.right) && DFS(left.right, right.left);
    }
}

// preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
class Solution105 {

    Map<Integer, Integer> indexMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i=0; i<inorder.length; i++)
            indexMap.put(inorder[i], i);

        return DFS(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }

    private TreeNode DFS(int[] preorder, int[] inorder, int l1, int r1, int l2, int r2) {
        if(l1>r1 && l2>r2)
            return null;
        TreeNode root = new TreeNode(preorder[l1]);
        int mid = indexMap.get(preorder[l1]);
        int leftSize = mid - l2;
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = DFS(preorder, inorder, l1+1, l1+leftSize, l2, mid-1);
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = DFS(preorder, inorder, l1+1+leftSize, r1, mid+1, r2);
        return root;
    }
}

class Solution105_1 {
    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }
}

// inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
class Solution106 {
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for(int i=0; i<inorder.length; i++)
            map.put(inorder[i], i);
        return DFS(inorder, postorder, 0, inorder.length-1, 0, postorder.length-1);
    }

    private TreeNode DFS(int[] inorder, int[] postorder, int l1, int r1, int l2, int r2) {
        if(l2>r2)
            return null;
        TreeNode root = new TreeNode(postorder[r2]);
        int mid = map.get(postorder[r2]);
        // 右子树节点个数
        int size = r1 - mid;
        // 中序左子树范围[l1,mid-1] 对应后序左子树[l2, r2-size-1]
        root.left = DFS(inorder, postorder, l1, mid-1, l2, r2-size-1);
        // 中序右子树范围[mid+1, r1] 对应后序右子树[r2-size, r2-1]
        root.right = DFS(inorder, postorder,  mid+1, r1, r2-size, r2-1);
        return root;
    }
}

class Solution109 {
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null)
            return null;
        int n = 0;
        ListNode temp = head;
        while (temp!=null) {
            temp = temp.next;
            n++;
        }
        int[] arr = new int[n];
        temp = head;
        for(int i=0; i<n; i++) {
            arr[i] = temp.val;
            temp = temp.next;
        }
        return DFS(arr, 0, n-1);
    }

    private TreeNode DFS(int[] arr, int i, int n) {
        if(i>n)
            return null;
        int mid = (i+n)>>1;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = DFS(arr, i, mid-1);
        root.right = DFS(arr, mid+1, n);
        return root;
    }
}

class Solution108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return DFS(nums, 0, nums.length-1);
    }

    private TreeNode DFS(int[] arr, int i, int n) {
        if(i>n)
            return null;
        int mid = (i+n)>>1;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = DFS(arr, i, mid-1);
        root.right = DFS(arr, mid+1, n);
        return root;
    }
}

class Solution104 {
    public int maxDepth(TreeNode root) {
        return DFS(root);
    }

    private int DFS(TreeNode root) {
        if(root==null)
            return 0;
        return Math.max(DFS(root.left), DFS(root.right))+1;
    }
}

class Solution110_1 {
    public boolean isBalanced(TreeNode root) {
        if(root==null)
            return true;
        return Math.abs(getHeight(root.left)-getHeight(root.right))<=1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int getHeight(TreeNode root) {
        if(root==null)
            return 0;
        return Math.max(getHeight(root.left), getHeight(root.right))+1;
    }
}

class Solution110_2 {
    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}

class Solution559 {
    public int maxDepth(Node root) {
        return DFS(root);
    }

    private int DFS(Node root) {
        if(root==null)
            return 0;
        List<Node> children = root.children;
        int max = 1;
        for(int i=0; i<children.size(); i++) {
            max = Math.max(DFS(children.get(i))+1, max);
        }
        return max;
    }
}

class Solution112 {

    boolean flag = false;
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null)
            return false;
        DFS(root, 0, targetSum);
        return flag;
    }

    private void DFS(TreeNode root, int i, int targetSum) {
        if(root==null) {
            return;
        }
        if(root.left==null && root.right==null && i+root.val == targetSum)
            flag = true;
        DFS(root.left, i+root.val, targetSum);
        DFS(root.right, i+root.val, targetSum);
    }
}

class Solution113 {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> path = new ArrayList<>();
        if(root==null)
            return res;
        DFS(path, root, 0, targetSum);
        return res;
    }

    private void DFS(List<Integer> path, TreeNode root, int i, int targetSum) {
        if(root==null) {
            return;
        }
        if(root.left==null && root.right==null && i+root.val == targetSum) {
            path.add(root.val);
            res.add(new ArrayList<>(path));
            path.remove(path.size()-1);
            return;
        }
        path.add(root.val);
        DFS(path, root.left, i+root.val, targetSum);
        DFS(path, root.right, i+root.val, targetSum);
        path.remove(path.size()-1);
    }
}

class Solution124 {
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        DFS(root);
        return ans;
    }

    private int DFS(TreeNode root) {
        if(root==null)
            return 0;
        int val = root.val;
        int left = DFS(root.left);
        int right = DFS(root.right);
        int temp = Math.max(val,Math.max(left+val, right+val));
        ans = Math.max(Math.max(temp, left+right+val),ans);
        return temp;
    }
}

class Solution129 {
    int ans = 0;
    public int sumNumbers(TreeNode root) {
        if(root==null)
            return 0;
        DFS(root, 0);
        return ans;
    }

    private void DFS(TreeNode root, int i) {
        if(root==null)
            return;
        if(root.left==null && root.right==null) {
            i += root.val;
            ans += i;
        }
        DFS(root.left, (i+root.val)*10);
        DFS(root.right, (i+root.val)*10);
    }
}

class Solution437 {

    int ans = 0;
    public int pathSum(TreeNode root, int targetSum) {
        getTree(root, targetSum);
        return ans;
    }

    private void getTree(TreeNode root, int targetSum) {
        if(root==null)
            return;
        DFS(root, targetSum);
        getTree(root.left, targetSum);
        getTree(root.right, targetSum);
    }


    private void DFS(TreeNode root, long targetSum) {
        if(root==null)
            return;
        if(targetSum==root.val)
            ans++;
        DFS(root.left, targetSum-root.val);
        DFS(root.right, targetSum-root.val);
    }
}

class Solution437_1 {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<Long, Integer>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = 0;
        curr += root.val;

        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }
}
