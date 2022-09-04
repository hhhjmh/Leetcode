package August;

import javafx.util.Pair;

import java.util.*;


public class the_27 {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(2);
        TreeNode t4 = new TreeNode(5);
        TreeNode t5 = new TreeNode(3);
        TreeNode t6 = new TreeNode(9);
        t1.left=t2;t2.right=t3;
//        new Solution622().widthOfBinaryTree(t1);

//        new Solution95().generateTrees(3);

        new Solution99().recoverTree(t1);

    }
}

class Solution622 {
    Map<Integer, Integer> map = new HashMap<>();
    int ans;
    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, 1, 0);
        return ans;
    }
    void dfs(TreeNode root, int u, int depth) {
        if (root == null) return ;
        if (!map.containsKey(depth)) map.put(depth, u);
        ans = Math.max(ans, u - map.get(depth) + 1);
        dfs(root.left, u << 1, depth + 1);
        dfs(root.right, u << 1 | 1, depth + 1);
    }
}

class Solution95 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<TreeNode>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);

            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree);
                }
            }
        }
        return allTrees;
    }
}

class Solution96 {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i < n + 1; i++)
            for(int j = 1; j < i + 1; j++)
                dp[i] += dp[j-1] * dp[i-j];

        return dp[n];
    }
}

class Solution241 {
    char[] cs;
    public List<Integer> diffWaysToCompute(String s) {
        cs = s.toCharArray();
        return dfs(0, cs.length - 1);
    }
    List<Integer> dfs(int l, int r) {
        List<Integer> ans = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            if (cs[i] >= '0' && cs[i] <= '9') continue;
            List<Integer> l1 = dfs(l, i - 1), l2 = dfs(i + 1, r);
            for (int a : l1) {
                for (int b : l2) {
                    int cur = 0;
                    if (cs[i] == '+') cur = a + b;
                    else if (cs[i] == '-') cur = a - b;
                    else cur = a * b;
                    ans.add(cur);
                }
            }
        }
        if (ans.isEmpty()) {
            int cur = 0;
            for (int i = l; i <= r; i++) cur = cur * 10 + (cs[i] - '0');
            ans.add(cur);
        }
        return ans;
    }
}

class Solution97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[][] f = new boolean[n + 1][m + 1];

        f[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[i][j] = f[i][j] || (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[n][m];
    }
}

class Solution99 {
    List<Integer> list = new ArrayList<>();
    public void recoverTree(TreeNode root) {
        DFS(root);
        int i=0, j=list.size()-1;
        while (list.get(i)<list.get(i+1))
            i++;
        while (list.get(j)>list.get(j-1))
            j--;
        int first = list.get(i), second = list.get(j);

        DFS1(root, first, second);
    }

    private void DFS1(TreeNode root, int first, int second) {
        if(root==null)
            return;
        DFS1(root.left, first, second);
        if(root.val==first) {
            root.val = second;
        }else if(root.val==second) {
            root.val = first;
        }
        DFS1(root.right, first, second);
    }

    private void DFS(TreeNode root) {
        if(root==null)
            return;
        DFS(root.left);
        list.add(root.val);
        DFS(root.right);
    }
}

class Solution100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return DFS(p, q);
    }

    private boolean DFS(TreeNode p, TreeNode q) {
        if( (p==null&&q!=null) || (p!=null && q==null))
            return false;
        if( (p==null&&q==null))
            return true;
        if(p.val!=q.val)
            return false;
        return DFS(p.left, q.left) && DFS(p.right, q.right);
    }
}

class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if(root==null)
            return res;
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0; i<n; i++) {
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if(temp.left!=null) queue.add(temp.left);
                if(temp.right!=null) queue.add(temp.right);
            }
            res.add(list);
        }
        return res;
    }
}

class Solution103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if(root==null)
            return res;
        queue.add(root);
        int count = 1;
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0; i<n; i++) {
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if(temp.left!=null) queue.add(temp.left);
                if(temp.right!=null) queue.add(temp.right);
            }
            if(count%2==0)
                Collections.reverse(list);
            count++;
            res.add(list);
        }
        return res;
    }
}

class Solution107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if(root==null)
            return res;
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0; i<n; i++) {
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if(temp.left!=null) queue.add(temp.left);
                if(temp.right!=null) queue.add(temp.right);
            }
            res.add(list);
        }
        Collections.reverse(res);
        return res;
    }
}

class Solution111 {
    public int minDepth(TreeNode root) {
        if(root==null)
            return 0;
        int res = 1;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for(int i=0; i<n; i++) {
                TreeNode temp = queue.poll();
                if(temp.left==null && temp.right==null)
                    return res;
                if(temp.left!=null) queue.add(temp.left);
                if(temp.right!=null) queue.add(temp.right);
            }
            res++;
        }
        return res;
    }
}


class Solution637 {
    public List<Double> averageOfLevels(TreeNode root) {

        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            double ans = 0;
            int n = queue.size();
            for(int i=0; i<n; i++) {
                TreeNode temp = queue.poll();
                ans += temp.val;
                if(temp.left!=null) queue.add(temp.left);
                if(temp.right!=null) queue.add(temp.right);
            }
            ans = ans/n;
            res.add(ans);
        }
        return res;
    }
}

class Solution429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null)
            return res;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0; i<n; i++) {
                Node temp = queue.poll();
                list.add(temp.val);
                List<Node> list1 = temp.children;
                int m = list1.size();
                for(int j=0; j<m; j++) {
                    queue.add(list1.get(j));
                }
            }
            res.add(list);
        }

        return res;
    }
}

class Solution993 {
    public boolean isCousins(TreeNode root, int x, int y) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            int count = 0;
            for(int i=0; i<n; i++) {
                TreeNode temp = queue.poll();
                if(temp.val==x || temp.val==y)
                    count++;
                if(temp.left!=null) queue.add(temp.left);
                if(temp.right!=null) queue.add(temp.right);
                if(temp.left!=null && temp.right!=null) {
                    int left = temp.left.val, right = temp.right.val;
                    if( (left==x && right==y) || (left==y && right==x) )
                        return false;
                }
            }
            if(count==2)
                return true;
        }
        return false;
    }
}