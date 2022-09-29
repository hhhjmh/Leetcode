package September;

import java.util.*;

public class the_26 {
    public static void main(String[] args) {
//        new Solution1719().missingTwo(new int[]{1});

        new Solution468().validIPAddress("256.256.256.256");
    }
}

class Solution1719 {
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2;
        long sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
        for(int num : nums) {
            sum1 += num;
            sum3 += num*num;
        }
        for(int i=1; i <= n; i++) {
            sum2 += i;
            sum4 += i*i;
        }
        sum2 -= sum1;
        sum4 -= sum3;
        int y = (int) Math.sqrt((double) (2*sum4-sum2*sum2));
        int ans1 = (int) (sum2 - y) / 2;
        int ans2 = (int) (sum2 + y) / 2;

        return new int[]{ans1, ans2};
    }
}

class Solution468 {
    public String validIPAddress(String ip) {
        if (ip.indexOf(".") >= 0 && check4(ip)) return "IPv4";
        if (ip.indexOf(":") >= 0 && check6(ip)) return "IPv6";
        return "Neither";
    }
    boolean check4(String ip) {
        int n = ip.length(), cnt = 0;
        char[] cs = ip.toCharArray();
        for (int i = 0; i < n && cnt <= 3; ) {
            // 找到连续数字段，以 x 存储
            int j = i, x = 0;
            while (j < n && cs[j] >= '0' && cs[j] <= '9' && x <= 255) x = x * 10 + (cs[j++] - '0');
            // 非 item 字符之间没有 item
            if (i == j) return false;
            // 含前导零 或 数值大于 255
            if ((j - i > 1 && cs[i] == '0') || (x > 255)) return false;
            i = j + 1;
            if (j == n) continue;
            // 存在除 . 以外的其他非数字字符
            if (cs[j] != '.') return false;
            cnt++;
        }
        // 恰好存在 3 个不位于两端的 .
        return cnt == 3 && cs[0] != '.' && cs[n - 1] != '.';
    }
    boolean check6(String ip) {
        int n = ip.length(), cnt = 0;
        char[] cs = ip.toCharArray();
        for (int i = 0; i < n && cnt <= 7; ) {
            int j = i;
            while (j < n && ((cs[j] >= 'a' && cs[j] <= 'f') || (cs[j] >= 'A' && cs[j] <= 'F') || (cs[j] >= '0' && cs[j] <= '9'))) j++;
            // 非 item 字符之间没有 item 或 长度超过 4
            if (i == j || j - i > 4) return false;
            i = j + 1;
            if (j == n) continue;
            // 存在除 : 以外的其他非数字字符
            if (cs[j] != ':') return false;
            cnt++;
        }
        // 恰好存在 7 个不位于两端的 :
        return cnt == 7 && cs[0] != ':' && cs[n - 1] != ':';
    }
}

/*class Solution470 extends SolBase {
    public int rand10() {


        return (rand7() + r);
    }
}*/

/*class Solution472 {
    Trie trie = new Trie();
    class Trie {
        September.Trie[] children;
        boolean isEnd;

        public Trie() {
            children = new September.Trie[26];
            isEnd = false;
        }
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<String>();
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() == 0) {
                continue;
            }
            boolean[] visited = new boolean[word.length()];
            if (dfs(word, 0, visited)) {
                ans.add(word);
            } else {
                insert(word);
            }
        }
        return ans;
    }

    public boolean dfs(String word, int start, boolean[] visited) {
        if (word.length() == start) {
            return true;
        }
        if (visited[start]) {
            return false;
        }
        visited[start] = true;
        Trie node = trie;
        for (int i = start; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            node = node.children[index];
            if (node == null) {
                return false;
            }
            if (node.isEnd) {
                if (dfs(word, i + 1, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void insert(String word) {
        Trie node = trie;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

}*/

class Solution473 {
    public boolean makesquare(int[] matchsticks) {
        int totalLen = Arrays.stream(matchsticks).sum();
        if (totalLen % 4 != 0) {
            return false;
        }
        Arrays.sort(matchsticks);
        for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
        }

        int[] edges = new int[4];
        return dfs(0, matchsticks, edges, totalLen / 4);
    }

    public boolean dfs(int index, int[] matchsticks, int[] edges, int len) {
        if (index == matchsticks.length) {
            return true;
        }
        for (int i = 0; i < edges.length; i++) {
            edges[i] += matchsticks[index];
            if (edges[i] <= len && dfs(index + 1, matchsticks, edges, len)) {
                return true;
            }
            edges[i] -= matchsticks[index];
        }
        return false;
    }
}

class Solution473_1 {
    public boolean makesquare(int[] matchsticks) {
        int totalLen = Arrays.stream(matchsticks).sum();
        if (totalLen % 4 != 0) {
            return false;
        }
        int len = totalLen / 4, n = matchsticks.length;
        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int s = 1; s < (1 << n); s++) {
            for (int k = 0; k < n; k++) {
                if ((s & (1 << k)) == 0) {
                    continue;
                }
                int s1 = s & ~(1 << k);
                if (dp[s1] >= 0 && dp[s1] + matchsticks[k] <= len) {
                    dp[s] = (dp[s1] + matchsticks[k]) % len;
                    break;
                }
            }
        }
        return dp[(1 << n) - 1] == 0;
    }
}

class Solution474 {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] cnt = new int[len][2];
        for (int i = 0; i < len; i++) {
            String str = strs[i];
            int zero = 0, one = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') zero++;
                else one++;

            }
            cnt[i] = new int[]{zero, one};
        }
        int[][][] f = new int[len + 1][m + 1][n + 1];
        for (int k = 1; k <= len; k++) {
            int zero = cnt[k - 1][0], one = cnt[k - 1][1];
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    int a = f[k - 1][i][j];
                    int b = (i >= zero && j >= one) ? f[k - 1][i - zero][j - one] + 1 : 0;
                    f[k][i][j] = Math.max(a, b);
                }
            }
        }
        return f[len][m][n];
    }
}


