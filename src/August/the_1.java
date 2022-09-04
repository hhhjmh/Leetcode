package August;

import java.util.List;

public class the_1 {
    public static void main(String[] args) {
        int [] arr = {0,1,0,2,1,0,1,3,2,1,2,1};
        Solution42 solution42 = new Solution42();
        System.out.println(solution42.trap(arr));
    }
}

class Solution1374 {
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n-1; i++) {
            sb.append('a');
        }
        return n%2==0 ? sb.append('b').toString() : sb.append('a').toString();
    }
}

class Solution44 {
    public boolean isMatch(String s, String p) {
        return s.matches(p);
    }
}

// 官方
class Solution44_2 {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}

class Solution11 {
    public int maxArea(int[] height) {
        int res = 0, n=height.length-1;
        for(int i=0, j=n; i<j;) {
            if(height[i]<height[j]) {
                int temp = height[i]*(j-i);
                res = temp > res ? temp : res;
                i++;
            } else {
                int temp = height[j]*(j-i);
                res = temp > res ? temp : res;
                j--;
            }
        }
        return res;
    }
}

// 官方
class Solution42 {
    public int trap(int[] height) {
        int i=0, j=height.length-1,res=0;
        int left=0, right=0;
        while(i<j) {
            left = Math.max(left, height[i]);
            right = Math.max(right, height[j]);
            if(left<right) {
                res += left - height[i++];
            } else {
                res += right - height[j--];
            }
        }
        return res;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}