package August;

import java.util.*;

public class the_26 {
    public static void main(String[] args) {
//        new Solution1464().maxProduct(new int[]{10,2,5,2});

//        new Solution91().numDecodings("1123");

        new Solution93().restoreIpAddresses("25525511135");
    }
}


class Solution1464 {
    public int maxProduct(int[] nums) {
        int index1 = 0, index2 = -1, n = nums.length, max = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > max) {
                index1 = i;
                max = nums[i];
            }

        }
        max = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] > max && i != index1) {
                index2 = i;
                max = nums[i];
            }
        }
        return (nums[index1] - 1) * (nums[index2] - 1);
    }
}

class Solution91 {
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i=1; i<=s.length(); i++) {
            if(s.charAt(i-1)!='0')
                dp[i] += dp[i-1];
            if(i>1 && s.charAt(i-2)!='0' && ( ((s.charAt(i-2)-'0')*10 + s.charAt(i-1)-'0') <=26))
                dp[i] += dp[i-2];
        }
        return dp[n];
    }
}


class Solution639 {
    static final int MOD = 1000000007;

    public int numDecodings(String s) {
        int n = s.length();
        // a = f[i-2], b = f[i-1], c = f[i]
        long a = 0, b = 1, c = 0;
        for (int i = 1; i <= n; ++i) {
            c = b * check1digit(s.charAt(i - 1)) % MOD;
            if (i > 1) {
                c = (c + a * check2digits(s.charAt(i - 2), s.charAt(i - 1))) % MOD;
            }
            a = b;
            b = c;
        }
        return (int) c;
    }

    public int check1digit(char ch) {
        if (ch == '0') {
            return 0;
        }
        return ch == '*' ? 9 : 1;
    }

    public int check2digits(char c0, char c1) {
        if (c0 == '*' && c1 == '*') {
            return 15;
        }
        if (c0 == '*') {
            return c1 <= '6' ? 2 : 1;
        }
        if (c1 == '*') {
            if (c0 == '1') {
                return 9;
            }
            if (c0 == '2') {
                return 6;
            }
            return 0;
        }
        return (c0 != '0' && (c0 - '0') * 10 + (c1 - '0') <= 26) ? 1 : 0;
    }
}

// 101023
class Solution93 {

    List<String> res = new ArrayList<>();
    int len;
    public List<String> restoreIpAddresses(String s) {
        len = s.length();
        List<Integer> path = new ArrayList<>();

        DFS(s, path, 0);

        return res;
    }

    private void DFS(String s, List<Integer> path, int index) {
        if(path.size()==4 && index==len) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<path.size(); i++) {
                sb.append(path.get(i)).append(".");
            }
            sb.deleteCharAt(sb.length()-1);
            res.add(sb.toString());
        }
        if(index >= len || path.size()>4)
            return;
        if(s.charAt(index)!='0') {
            int x = s.charAt(index)-'0';
            path.add(x);
            DFS(s, path, index+1);
            path.remove(path.size()-1);
            if(index<len-1) {
                int y = s.charAt(index+1)-'0';
                x = x*10+y;
                path.add(x);
                DFS(s, path, index+2);
                path.remove(path.size()-1);
            }
            if(index<len-2) {
                int z = s.charAt(index+2)-'0';
                x = x*10+z;
                if(x<256) {
                    path.add(x);
                    DFS(s, path, index+3);
                    path.remove(path.size()-1);
                }
            }
        } else {
            path.add(0);
            DFS(s, path, index+1);
            path.remove(path.size()-1);
        }
    }
}

class Solution94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        DFS(root, res);
        return res;
    }

    private void DFS(TreeNode root, List<Integer> res) {
        if(root==null)
            return;
        DFS(root.left, res);
        res.add(root.val);
        DFS(root.right, res);
    }
}

class Solution98 {

    List<Integer> list = new ArrayList<>();

    public boolean isValidBST(TreeNode root) {
        DFS(root);
        for(int i=0; i<list.size()-1; i++) {
            if(list.get(i)>=list.get(i+1))
                return false;
        }
        return true;
    }

    private void DFS(TreeNode root) {
        if(root==null)
            return;
        DFS(root.left);
        list.add(root.val);
        DFS(root.right);
    }
}

class Solution98_1 {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }
}

class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        DFS(root, res);
        return res;
    }

    private void DFS(TreeNode root, List<Integer> res) {
        if(root==null)
            return;
        res.add(root.val);
        DFS(root.left, res);
        DFS(root.right, res);
    }
}

class Solution145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        DFS(root, res);
        return res;
    }

    private void DFS(TreeNode root, List<Integer> res) {
        if(root==null)
            return;
        DFS(root.left, res);
        DFS(root.right, res);
        res.add(root.val);
    }
}

class BSTIterator {

    int index = 0;
    List<TreeNode> res = new ArrayList<>();

    public BSTIterator(TreeNode root) {
        DFS(root);
    }

    private void DFS(TreeNode root) {
        if(root==null)
            return;
        DFS(root.left);
        res.add(root);
        DFS(root.right);
    }

    public int next() {
        index++;
        return res.get(index-1).val;
    }

    public boolean hasNext() {
        return index<res.size();
    }
}

class Solution230 {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> res = new ArrayList<>();
        DFS(root, res);
        return res.get(k-1);
    }

    private void DFS(TreeNode root, List<Integer> res) {
        if(root==null)
            return;
        DFS(root.left, res);
        res.add(root.val);
        DFS(root.right, res);
    }
}

class Solution783 {
    public int minDiffInBST(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        DFS(root, res);
        int ans = Integer.MAX_VALUE;
        for(int i=0; i<res.size()-1; i++) {
            ans = Math.min(res.get(i+1)-res.get(i), ans);
        }
        return ans;
    }

    private void DFS(TreeNode root, List<Integer> res) {
        if(root==null)
            return;
        DFS(root.left, res);
        res.add(root.val);
        DFS(root.right, res);
    }
}

class Solution783_1 {
    int pre;
    int ans;

    public int minDiffInBST(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        }
        dfs(root.right);
    }
}

