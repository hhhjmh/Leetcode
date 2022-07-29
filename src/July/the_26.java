package July;

import java.util.*;

public class the_26 {
    public static void main(String[] args) {
        int[] nums1 = {1,2};
        Solution14 s14 = new Solution14();
//        System.out.println(s14.subarraysWithKDistinct(nums1, 1));

        Solution15 solution15 = new Solution15();
        System.out.println(solution15.longestPalindrome("aacabdkacaa"));

    }
}

// 1206
class Skiplist {
    int level = 10;
    class Node {
        int val;
        Node[] ne = new Node[level];
        Node (int _val) {
            val = _val;
        }
    }
    Random random = new Random();
    Node he = new Node(-1);
    void find(int t, Node[] ns) {
        Node cur = he;
        for (int i = level - 1; i >= 0; i--) {
            while (cur.ne[i] != null && cur.ne[i].val < t) cur = cur.ne[i];
            ns[i] = cur;
        }
    }
    public boolean search(int t) {
        Node[] ns = new Node[level];
        find(t, ns);
        return ns[0].ne[0] != null && ns[0].ne[0].val == t;
    }
    public void add(int t) {
        Node[] ns = new Node[level];
        find(t, ns);
        Node node = new Node(t);
        for (int i = 0; i < level; i++) {
            node.ne[i] = ns[i].ne[i];
            ns[i].ne[i] = node;
            if (random.nextInt(2) == 0) break;
        }
    }
    public boolean erase(int t) {
        Node[] ns = new Node[level];
        find(t, ns);
        Node node = ns[0].ne[0];
        if (node == null || node.val != t) return false;
        for (int i = 0; i < level && ns[i].ne[i] == node; i++) ns[i].ne[i] = ns[i].ne[i].ne[i];
        return true;
    }
}

// 3
class Solution13 {
    public int lengthOfLongestSubstring(String s) {
        int[] last = new int[128];
        for(int i=0; i<128; i++)
            last[i] = -1;
        int n = s.length();

        int res = 0;
        int start = 0;
        for(int i=0; i<n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i-start+1);
            last[index] = i;
        }
        return res;
    }
}

// 992
class Solution14 {
    public int subarraysWithKDistinct(int[] nums, int k) {
            return atMostDis(nums, k) - atMostDis(nums, k-1);
    }

    public int atMostDis(int[] nums, int k) {

        int len = nums.length;
        int[] arr = new int[len+1];
        int left = 0, right = 0, res = 0, count = 0;
        while(right<len) {
            if(arr[nums[right]]==0)
                count++;
            arr[nums[right]]++;
            right++;

            while(count>k) {
                arr[nums[left]]--;
                if(arr[nums[left]]==0)
                    count--;
                left++;
            }

            res += right - left;
        }
        return res;
    }
}

// 6
class Solution15 {
    public String longestPalindrome(String s) {
        if(s==null || s.length()<2)
            return s;
        String origin = s;
        String reverse = new StringBuilder(s).reverse().toString();
        int n = s.length();
        int[][] arr = new int[n][n];
        int len=0, end=0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(origin.charAt(i)==reverse.charAt(j)) {
                    if(i==0||j==0)
                        arr[i][j]=1;
                    else
                        arr[i][j]=arr[i-1][j-1]+1;
                }

                if(arr[i][j]>len && i+j+2==arr[i][j]+n) {
                    len = arr[i][j];
                    end = i;
                }
            }
        }
        return s.substring(end-len+1, end+1);
    }
}

// 6 官方思路
class Solution16 {
    public String longestPalindrome(String s) {
        int start = 0, end = -1;
        StringBuffer t = new StringBuffer("#");
        for (int i = 0; i < s.length(); ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        t.append('#');
        s = t.toString();

        List<Integer> arm_len = new ArrayList<Integer>();
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); ++i) {
            int cur_arm_len;
            if (right >= i) {
                int i_sym = j * 2 - i;
                int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
                cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
            } else {
                cur_arm_len = expand(s, i, i);
            }
            arm_len.add(cur_arm_len);
            if (i + cur_arm_len > right) {
                j = i;
                right = i + cur_arm_len;
            }
            if (cur_arm_len * 2 + 1 > end - start) {
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }
        }

        StringBuffer ans = new StringBuffer();
        for (int i = start; i <= end; ++i) {
            if (s.charAt(i) != '#') {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return (right - left - 2) / 2;
    }

    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int strLen = s.length();
        int maxStart = 0;  //最长回文串的起点
        int maxEnd = 0;    //最长回文串的终点
        int maxLen = 1;  //最长回文串的长度

        boolean[][] dp = new boolean[strLen][strLen];

        for (int r = 1; r < strLen; r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxStart = l;
                        maxEnd = r;

                    }
                }

            }

        }
        return s.substring(maxStart, maxEnd + 1);

    }

}

