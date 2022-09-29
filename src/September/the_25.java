package September;

import java.util.*;

public class the_25 {
    public static void main(String[] args) {
//        new Solution788().rotatedDigits(1000);

/*        new Solution463().islandPerimeter(new int[][] {
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0},
        });*/

        new Solution467().findSubstringInWraproundString("abaab");

    }
}

class Solution788 {
    public int rotatedDigits(int n) {
        int ans = 0;
        for(int i=1; i<=n; i++) {
            if(check(i))
                ans++;
        }
        return ans;
    }

    private boolean check(int i) {
        int[] arr1 = {0, 1, 8};
        int[] arr2 = {2, 5, 6, 9};
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(0);list1.add(1);list1.add(8);
        list2.add(2);list2.add(5);list2.add(6);list2.add(9);
        boolean flag = false;
        while (i>0) {
            int temp = i % 10;
            i = i/10;
            if(list1.contains(temp)) {
                continue;
            } else if(list2.contains(temp)) {
                flag = true;
            } else {
                return false;
            }
        }
        return flag;
    }
}

class Solution462 {
    public int minMoves2(int[] nums) {
        int res = 0, i = 0, j = nums.length - 1;
        Arrays.sort(nums);
        while (i<j) {
            res += nums[j--] - nums[i++];
        }
        return res;
    }
}

class Solution463 {
    public int islandPerimeter(int[][] grid) {
        int ans = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                ans += getBoard(grid, i, j);
            }
        }
        return ans;
    }

    private int getBoard(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if(grid[i][j]==0)
            return 0;
        int ret = 4;
        if(i-1>=0 && grid[i-1][j]==1) {
            ret--;
        }
        if(i+1<m && grid[i+1][j]==1) {
            ret--;
        }
        if(j-1>=0 && grid[i][j-1]==1) {
            ret--;
        }
        if(j+1<n && grid[i][j+1]==1) {
            ret--;
        }
        return ret;
    }
}

class Solution464 {
    int n, t;
    int[] f = new int[1 << 20];
    // 1 true / -1 false
    int dfs(int state, int tot) {
        if (f[state] != 0) return f[state];
        for (int i = 0; i < n; i++) {
            if (((state >> i) & 1) == 1) continue;
            if (tot + i + 1 >= t) return f[state] = 1;
            if (dfs(state | (1 << i), tot + i + 1) == -1) return f[state] = 1;
        }
        return f[state] = -1;
    }
    public boolean canIWin(int _n, int _t) {
        n = _n; t = _t;
        if (n * (n + 1) / 2 < t) return false;
        if (t == 0) return true;
        return dfs(0, 0) == 1;
    }
}

class Solution466 {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (n1 == 0) {
            return 0;
        }
        int s1cnt = 0, index = 0, s2cnt = 0;
        // recall 是我们用来找循环节的变量，它是一个哈希映射
        // 我们如何找循环节？假设我们遍历了 s1cnt 个 s1，此时匹配到了第 s2cnt 个 s2 中的第 index 个字符
        // 如果我们之前遍历了 s1cnt' 个 s1 时，匹配到的是第 s2cnt' 个 s2 中同样的第 index 个字符，那么就有循环节了
        // 我们用 (s1cnt', s2cnt', index) 和 (s1cnt, s2cnt, index) 表示两次包含相同 index 的匹配结果
        // 那么哈希映射中的键就是 index，值就是 (s1cnt', s2cnt') 这个二元组
        // 循环节就是；
        //    - 前 s1cnt' 个 s1 包含了 s2cnt' 个 s2
        //    - 以后的每 (s1cnt - s1cnt') 个 s1 包含了 (s2cnt - s2cnt') 个 s2
        // 那么还会剩下 (n1 - s1cnt') % (s1cnt - s1cnt') 个 s1, 我们对这些与 s2 进行暴力匹配
        // 注意 s2 要从第 index 个字符开始匹配
        Map<Integer, int[]> recall = new HashMap<Integer, int[]>();
        int[] preLoop = new int[2];
        int[] inLoop = new int[2];
        while (true) {
            // 我们多遍历一个 s1，看看能不能找到循环节
            ++s1cnt;
            for (int i = 0; i < s1.length(); ++i) {
                char ch = s1.charAt(i);
                if (ch == s2.charAt(index)) {
                    index += 1;
                    if (index == s2.length()) {
                        ++s2cnt;
                        index = 0;
                    }
                }
            }
            // 还没有找到循环节，所有的 s1 就用完了
            if (s1cnt == n1) {
                return s2cnt / n2;
            }
            // 出现了之前的 index，表示找到了循环节
            if (recall.containsKey(index)) {
                int[] value = recall.get(index);
                int s1cntPrime = value[0];
                int s2cntPrime = value[1];
                // 前 s1cnt' 个 s1 包含了 s2cnt' 个 s2
                preLoop = new int[]{s1cntPrime, s2cntPrime};
                // 以后的每 (s1cnt - s1cnt') 个 s1 包含了 (s2cnt - s2cnt') 个 s2
                inLoop = new int[]{s1cnt - s1cntPrime, s2cnt - s2cntPrime};
                break;
            } else {
                recall.put(index, new int[]{s1cnt, s2cnt});
            }
        }
        // ans 存储的是 S1 包含的 s2 的数量，考虑的之前的 preLoop 和 inLoop
        int ans = preLoop[1] + (n1 - preLoop[0]) / inLoop[0] * inLoop[1];
        // S1 的末尾还剩下一些 s1，我们暴力进行匹配
        int rest = (n1 - preLoop[0]) % inLoop[0];
        for (int i = 0; i < rest; ++i) {
            for (int j = 0; j < s1.length(); ++j) {
                char ch = s1.charAt(j);
                if (ch == s2.charAt(index)) {
                    ++index;
                    if (index == s2.length()) {
                        ++ans;
                        index = 0;
                    }
                }
            }
        }
        // S1 包含 ans 个 s2，那么就包含 ans / n2 个 S2
        return ans / n2;
    }
}

class Solution467 {
    public int findSubstringInWraproundString(String p) {
        int n = p.length(), ans = 0;
        int [] dp = new int[26];
        char[] cs = p.toCharArray();
        dp[cs[0]-'a']++;
        for(int i=1, j=1; i<n; i++) {
            int cur = cs[i]-'a', pre = cs[i-1]-'a';
            if( (cur==0 && pre==25) || pre+1 == cur) j++;
            else j = 1;
            dp[cur] = Math.max(dp[cur], j);
        }

        for(int num : dp)
            ans += num;
        return ans;
    }
}
