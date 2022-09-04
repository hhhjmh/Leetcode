package September;

import java.util.*;

public class the_2 {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(4);
        TreeNode t3 = new TreeNode(5);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(5);
        t1.left=t2;t1.right=t3;t2.left=t4;t2.right=t5;t3.right=t6;
//        new Solution687().longestUnivaluePath(t1);

//        new Solution123().maxProfit(new int[]{1,2,4,2,5,7,2,4,9,0});

//        new Solution188().maxProfit(2, new int[]{2, 4, 1});

//        System.out.println(new Solution125().isPalindrome("0P"));

//        new Solution680().validPalindrome("ebcbbececabbacecbbcbe");
        // "hot","dot","dog","lot","log","cog"

        List<String> list = new ArrayList<String>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        new Solution126().findLadders( "hit", "cog", list);
    }
}

class Solution687 {
    int maxValue = 0;
    public int longestUnivaluePath(TreeNode root) {
        DFS(root);
        return maxValue;
    }

    private int DFS(TreeNode root) {
        if(root==null || (root.left==null&&root.right==null))
            return 0;
        int v, left=0, right=0;
        if(root.left!=null) {
            int temp = DFS(root.left);
            if(root.val==root.left.val)
                left = temp + 1;
        }

        if(root.right!=null) {
            int temp = DFS(root.right);
            if(root.val==root.right.val)
                right = temp + 1;
        }

        if(root.left!=null && root.right!=null && root.val==root.left.val && root.val==root.right.val) {
            v = left + right;
            maxValue = Math.max(maxValue, v);
        }
        v = Math.max(left, right);

        maxValue = Math.max(maxValue, v);
        return v;
    }
}

class Solution687_1 {
    int res;

    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left), right = dfs(root.right);
        int left1 = 0, right1 = 0;
        if (root.left != null && root.left.val == root.val) {
            left1 = left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            right1 = right + 1;
        }
        res = Math.max(res, left1 + right1);
        return Math.max(left1, right1);
    }
}

class Solution120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int [][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for(int i=1; i<n; i++) {
            dp[i][0] =dp[i-1][0] + triangle.get(i).get(0);
        }
        int ans = dp[n-1][0];
        for(int i=1; i<n; i++) {
            for(int j=1; j<=i; j++) {
                if(j==i)
                    dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
                else
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + triangle.get(i).get(j);
                if(i==n-1)
                    ans = Math.min(dp[i][j], ans);
            }
        }
        return ans;
    }
}

class Solution120_1 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] += triangle.get(i).get(0);
        }
        int minTotal = f[0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[i]);
        }
        return minTotal;
    }
}

class Solution122 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] dp = new int[n];
        int temp = prices[0];
        for(int i=1; i<n; i++) {
            if(prices[i]>temp)
                dp[i] = dp[i-1] + prices[i] - temp;
            else
                dp[i] = dp[i-1];
            temp = prices[i];
        }
        return dp[n-1];
    }
}

class Solution123 {
    public int maxProfit(int[] prices) {
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for(int i=1; i<prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1+prices[i]);
            buy2 = Math.max(buy2, sell1-prices[i]);
            sell2 = Math.max(sell2, buy2+prices[i]);
        }
        return sell2;
    }
}


class Solution188 {
    public int maxProfit(int k, int[] prices) {
        int [] buys = new int[k+1];
        int [] sells = new int[k+1];
        if(prices.length==0)
            return 0;
        for(int i=1; i<=k; i++) {
            buys[i] = -prices[0];
        }
        for(int i=1; i<prices.length; i++) {
            for(int j=1; j<=k; j++) {
                buys[j] = Math.max(buys[j], sells[j-1]-prices[i]);
                sells[j] = Math.max(sells[j], buys[j]+prices[i]);
            }
        }
        return sells[k];
    }
}

class Solution309 {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        int f0 = -prices[0];
        int f1 = 0;
        int f2 = 0;
        for (int i = 1; i < n; ++i) {
            int newf0 = Math.max(f0, f2 - prices[i]);
            int newf1 = f0 + prices[i];
            int newf2 = Math.max(f1, f2);
            f0 = newf0;
            f1 = newf1;
            f2 = newf2;
        }

        return Math.max(f1, f2);
    }
}

class Solution714 {
    public int maxProfit(int[] prices, int fee) {
        int buy = prices[0] + fee;
        int profit = 0;
        for(int i=1; i<prices.length; i++) {
            if(prices[i] + fee < buy) {
                buy = prices[i] + fee;
            } else if(prices[i]>buy) {
                profit += prices[i] - buy;
                buy = prices[i];
            }
        }
        return profit;
    }
}

class Solution125 {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++)
            if(Character.isLetter(s.charAt(i)) || Character.isDigit(s.charAt(i)))
                sb.append(s.charAt(i));

        return sb.toString().equals(sb.reverse().toString());
    }
}

class Solution680 {
    public boolean validPalindrome(String s) {
        int low = 0, high = s.length() - 1;
        while (low < high) {
            char c1 = s.charAt(low), c2 = s.charAt(high);
            if (c1 == c2) {
                ++low;
                --high;
            } else {
                return validPalindrome(s, low, high - 1) || validPalindrome(s, low + 1, high);
            }
        }
        return true;
    }

    public boolean validPalindrome(String s, int low, int high) {
        for (int i = low, j = high; i < j; ++i, --j) {
            char c1 = s.charAt(i), c2 = s.charAt(j);
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }
}


