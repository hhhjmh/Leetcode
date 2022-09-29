package September;

import java.util.*;

public class the_22 {
    public static void main(String[] args) {
//        new Solution423().originalDigits("owoztneoer");

        new Solution441().arrangeCoins(Integer.MAX_VALUE);
    }
}

class Solution1640 {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<pieces.length; i++)
            map.put(pieces[i][0], i);

        for(int i=0; i<arr.length;) {
            if(!map.containsKey(arr[i]))
                return false;
            int j = map.get(arr[i]);
            for(int k=0; k<pieces[j].length; k++, i++) {
                if(arr[i]!=pieces[j][k])
                    return false;
            }
        }
        return true;
    }
}

class Solution420 {
    public int strongPasswordChecker(String password) {
        char[] cs = password.toCharArray();
        int n = cs.length;
        int A = 0, B = 0, C = 0;
        for (char c : cs) {
            if (c >= 'a' && c <= 'z') A = 1;
            else if (c >= '0' && c <= '9') B = 1;
            else if (c >= 'A' && c <= 'Z') C = 1;
        }
        int m = A + B + C;
        if (n < 6) {
            return Math.max(6 - n, 3 - m);
        } else if (n <= 20) {
            int tot = 0;
            for (int i = 0; i < n; ) {
                int j = i;
                while (j < n && cs[j] == cs[i]) j++;
                int cnt = j - i;
                if (cnt >= 3) tot += cnt / 3;
                i = j;
            }
            return Math.max(tot, 3 - m);
        } else {
            int tot = 0;
            int[] cnts = new int[3];
            for (int i = 0; i < n; ) {
                int j = i;
                while (j < n && cs[j] == cs[i]) j++;
                int cnt = j - i;
                if (cnt >= 3) {
                    tot += cnt / 3; cnts[cnt % 3]++;
                }
                i = j;
            }
            int base = n - 20, cur = base;
            for (int i = 0; i < 3; i++) {
                if (i == 2) cnts[i] = tot;
                if (cnts[i] != 0 && cur != 0) {
                    int t = Math.min(cnts[i] * (i + 1), cur);
                    cur -= t; tot -= t / (i + 1);
                }
            }
            return base + Math.max(tot, 3 - m);
        }
    }
}

class Solution421 {
    // static 成员整个类独一份，只有在类首次加载时才会创建，因此只会被 new 一次
    static int N = (int)1e6;
    static int[][] trie = new int[N][2];
    static int idx = 0;
    // 每跑一个数据，会被实例化一次，每次实例化的时候被调用，做清理工作
    public Solution421() {
        for (int i = 0; i <= idx; i++) {
            Arrays.fill(trie[i], 0);
        }
        idx = 0;
    }
    void add(int x) {
        int p = 0;
        for (int i = 31; i >= 0; i--) {
            int u = (x >> i) & 1;
            if (trie[p][u] == 0) trie[p][u] = ++idx;
            p = trie[p][u];
        }
    }
    int getVal(int x) {
        int ans = 0;
        int p = 0;
        for (int i = 31; i >= 0; i--) {
            int a = (x >> i) & 1, b = 1 - a;
            if (trie[p][b] != 0) {
                ans |= (b << i);
                p = trie[p][b];
            } else {
                ans |= (a << i);
                p = trie[p][a];
            }
        }
        return ans;
    }
    public int findMaximumXOR(int[] nums) {
        int ans = 0;
        for (int i : nums) {
            add(i);
            int j = getVal(i);
            ans = Math.max(ans, i ^ j);
        }
        return ans;
    }
}

class Solution423 {
    public String originalDigits(String s) {
        Map<Character, Integer> c = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            c.put(ch, c.getOrDefault(ch, 0) + 1);
        }

        int[] cnt = new int[10];
        cnt[0] = c.getOrDefault('z', 0);
        cnt[2] = c.getOrDefault('w', 0);
        cnt[4] = c.getOrDefault('u', 0);
        cnt[6] = c.getOrDefault('x', 0);
        cnt[8] = c.getOrDefault('g', 0);

        cnt[3] = c.getOrDefault('h', 0) - cnt[8];
        cnt[5] = c.getOrDefault('f', 0) - cnt[4];
        cnt[7] = c.getOrDefault('s', 0) - cnt[6];

        cnt[1] = c.getOrDefault('o', 0) - cnt[0] - cnt[2] - cnt[4];

        cnt[9] = c.getOrDefault('i', 0) - cnt[5] - cnt[6] - cnt[8];

        StringBuffer ans = new StringBuffer();
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < cnt[i]; ++j) {
                ans.append((char) (i + '0'));
            }
        }
        return ans.toString();
    }
}

class Solution424 {
    public int characterReplacement(String s, int k) {
        int left = 0, right = 0, n = s.length(), max = 0;
        int[] cnt = new int[26];
        while (right<n) {
            cnt[s.charAt(right)-'A']++;
            max = Math.max(max, cnt[s.charAt(right)-'A']);
            while (right-left+1 - max > k) {
                cnt[s.charAt(left)-'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }
}

class Solution433 {
    static char[] items = new char[]{'A', 'C', 'G', 'T'};
    public int minMutation(String S, String T, String[] bank) {
        Set<String> set = new HashSet<>();
        for (String s : bank) set.add(s);
        Deque<String> d = new ArrayDeque<>();
        Map<String, Integer> map = new HashMap<>();
        d.addLast(S);
        map.put(S, 0);
        while (!d.isEmpty()) {
            int size = d.size();
            while (size-- > 0) {
                String s = d.pollFirst();
                char[] cs = s.toCharArray();
                int step = map.get(s);
                for (int i = 0; i < 8; i++) {
                    for (char c : items) {
                        if (cs[i] == c) continue;
                        char[] clone = cs.clone();
                        clone[i] = c;
                        String sub = String.valueOf(clone);
                        if (!set.contains(sub)) continue;
                        if (map.containsKey(sub)) continue;
                        if (sub.equals(T)) return step + 1;
                        map.put(sub, step + 1);
                        d.addLast(sub);
                    }
                }
            }
        }
        return -1;
    }
}


class Solution434 {
    public int countSegments(String s) {
        String[] ss = s.split(" ");
        int ans = 0;
        for(String s1 : ss) {
            if(s1.trim().length()!=0)
                ans++;
        }
        return ans;
    }
}


class Solution441 {
    public int arrangeCoins(int n) {
        long x = 1;
        while ( n> ((x*x+x)/2) ) {
            x++;
        }
        return (int) (x-1);
    }
}

class Solution441_1 {
    public int arrangeCoins(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if ((long) mid * (mid + 1) <= (long) 2 * n) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}