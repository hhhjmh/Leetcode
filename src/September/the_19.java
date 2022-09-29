package September;

import java.util.*;

public class the_19 {
    public static void main(String[] args) {
//        new Solution393().validUtf8(new int[]{194,155,231,184,185,246,176,131,161,222,174,227,162,134,241,154,168,185,218,178,229,187,139,246,178,187,139,204,146,225,148,179,245,139,172,134,193,156,233,131,154,240,166,188,190,216,150,230,145,144,240,167,140,163,221,190,238,168,139,241,154,159,164,199,170,224,173,140,244,182,143,134,206,181,227,172,141,241,146,159,170,202,134,230,142,163,244,172,140,191});

        new Solution397().integerReplacement(123456789);
    }
}

class Solution1636 {
    public int[] frequencySort(int[] nums) {
        PriorityQueue<int []> priorityQueue = new PriorityQueue<>((a,b)-> {
            return a[1] == b[1] ? b[0]-a[0] : a[1] - b[1];
        });
        int [] cnt = new int[210];
        for (int num : nums) {
            cnt[num+100]++;
        }
        for(int i=0; i<210; i++) {
            if(cnt[i]!=0) {
                priorityQueue.offer(new int[]{i-100, cnt[i]});
            }
        }
        int i=0;
        while (!priorityQueue.isEmpty()) {
            int[] temp = priorityQueue.poll();
            while (temp[1]>0) {
                nums[i] = temp[0];
                i++;
                temp[1]--;
            }
        }
        return nums;
    }
}

class Solution390 {
    public int lastRemaining(int n) {
        return n==1 ? 1 : 2*(n/2 + 1 - lastRemaining(n/2));
    }
}

class Solution391 {
    public boolean isRectangleCover(int[][] rectangles) {
        int n = rectangles.length;
        int[][] rs = new int[n * 2][4];
        for (int i = 0, idx = 0; i < n; i++) {
            int[] re = rectangles[i];
            rs[idx++] = new int[]{re[0], re[1], re[3], 1};
            rs[idx++] = new int[]{re[2], re[1], re[3], -1};
        }
        Arrays.sort(rs, (a,b)->{
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });
        n *= 2;
        // 分别存储相同的横坐标下「左边的线段」和「右边的线段」 (y1, y2)
        List<int[]> l1 = new ArrayList<>(), l2 = new ArrayList<>();
        for (int l = 0; l < n; ) {
            int r = l;
            l1.clear(); l2.clear();
            // 找到横坐标相同部分
            while (r < n && rs[r][0] == rs[l][0]) r++;
            for (int i = l; i < r; i++) {
                int[] cur = new int[]{rs[i][1], rs[i][2]};
                List<int[]> list = rs[i][3] == 1 ? l1 : l2;
                if (list.isEmpty()) {
                    list.add(cur);
                } else {
                    int[] prev = list.get(list.size() - 1);
                    if (cur[0] < prev[1]) return false; // 存在重叠
                    else if (cur[0] == prev[1]) prev[1] = cur[1]; // 首尾相连
                    else list.add(cur);
                }
            }
            if (l > 0 && r < n) {
                // 若不是完美矩形的边缘竖边，检查是否成对出现
                if (l1.size() != l2.size()) return false;
                for (int i = 0; i < l1.size(); i++) {
                    if (l1.get(i)[0] == l2.get(i)[0] && l1.get(i)[1] == l2.get(i)[1]) continue;
                    return false;
                }
            } else {
                // 若是完美矩形的边缘竖边，检查是否形成完整一段
                if (l1.size() + l2.size() != 1) return false;
            }
            l = r;
        }
        return true;
    }
}

class Solution392 {
    public boolean isSubsequence(String s, String t) {
        int m = s.length(), n = t.length();
        int i=0;
        for(int j=0; i<m && j<n; j++) {
            if(s.charAt(i)==t.charAt(j))
                i++;
        }
        return i==m;
    }
}

class Solution393 {
    public boolean validUtf8(int[] data) {
        for(int i=0; i<data.length; i++) {
            if(data[i]<128)
                continue;
            int cnt = 0;
            if(data[i] < 192 || data[i]>247) {
                return false;
            } else if(data[i] < 224) {
                cnt = 1;
            } else if(data[i] < 240) {
                cnt = 2;
            } else{
                cnt = 3;
            }
            while (cnt>0 && i+1<data.length) {
                i++;
                cnt--;
                if(data[i]<128 || data[i]>191)
                    return false;
            }
            if(cnt>0)
                return false;
        }
        return true;
    }
}

class Solution394 {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for(Character c : s.toCharArray()) {
            if(c == '[') {
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            }
            else if(c == ']') {
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for(int i = 0; i < cur_multi; i++) tmp.append(res);
                res = new StringBuilder(stack_res.removeLast() + tmp);
            }
            else if(c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
            else res.append(c);
        }
        return res.toString();
    }
}

class Solution395 {
    public int longestSubstring(String s, int k) {
        int ans = 0, n = s.length();
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];
        for(int m=1; m<=26; m++) {
            Arrays.fill(cnt, 0);
            for(int i=0, j=0, total=0, sum=0; i<n; i++) {
                int temp = cs[i]-'a';
                cnt[temp]++;
                if(cnt[temp]==1) total++;
                if(cnt[temp]==k) sum++;
                while (total>m) {
                    temp = cs[j]-'a';
                    cnt[temp]--;
                    if(cnt[temp]==0) total--;
                    if(cnt[temp]==k-1) sum--;
                    j++;
                }
                if(total==sum)
                    ans = Math.max(ans, i-j+1);
            }
        }
        return ans;
    }
}

class Solution396 {
    public int maxRotateFunction(int[] nums) {
        int ans = 0, n = nums.length, sum = 0;
        int[] dp = new int[n];
        for(int i=0; i<n; i++) {
            ans += nums[i]*i;
            sum += nums[i];
        }
        dp[0] = ans;
        for(int i=1; i<n; i++) {
            dp[i] = dp[i-1] - sum + nums[i-1]*n;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}

class Solution397 {
    public int integerReplacement(int n) {
        if(n==1)
            return 0;
        if(n%2==0)
            return 1 + integerReplacement(n/2);
        return 2 + Math.min(integerReplacement(n/2), integerReplacement(n/2 + 1));
    }
}

class Solution397_1 {
    public int integerReplacement(int n) {
        int ans = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                ++ans;
                n /= 2;
            } else if (n % 4 == 1) {
                ans += 2;
                n /= 2;
            } else {
                if (n == 3) {
                    ans += 2;
                    n = 1;
                } else {
                    ans += 2;
                    n = n / 2 + 1;
                }
            }
        }
        return ans;
    }
}
