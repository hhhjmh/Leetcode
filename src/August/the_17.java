package August;

import java.util.*;

public class the_17 {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        t1.left = t2;t1.right=t3;t2.left=t4;t2.right=t5;t3.right=t6;t4.left=t7;t6.right=t8;
        Solution1302 solution1302 = new Solution1302();
        solution1302.deepestLeavesSum(t1);

        Solution76 solution76 = new Solution76();
        System.out.println(solution76.minWindow("a", "aa"));
    }
}

class Solution1302 {
    public int deepestLeavesSum(TreeNode root) {
        int res = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            res = 0;
            int n = queue.size();
            for(int i=0; i<n; i++) {
                TreeNode temp = queue.poll();
                res += temp.val;
                if(temp.left!=null) queue.add(temp.left);
                if(temp.right!=null) queue.add(temp.right);
            }
        }
        return res;
    }
}

class Solution76 {

    Map<Character, Integer> old = new HashMap<>();
    Map<Character, Integer> count = new HashMap<>();

    public String minWindow(String s, String t) {
        if(s.length()<t.length())
            return "";

        int left = 0, right = 0, resLeft = -1, resRight = -1, len = Integer.MAX_VALUE;
        int sLen = s.length(), tLen = t.length();
        for(char ch : t.toCharArray()) {
            old.put(ch, old.getOrDefault(ch, 0) + 1);
        }

        while (right < sLen) {

            char ch = s.charAt(right);
            count.put(ch, count.getOrDefault(ch, 0) + 1);

            while (check() && left <= right) {
                if(right-left < len) {
                    resLeft = left;
                    resRight = right;
                    len = right - left;
                }
                char chL = s.charAt(left);
                count.put(chL, count.getOrDefault(chL, 0) -1);
                left++;
            }

            right++;

        }

        return resLeft == -1 ? "" : s.substring(resLeft, resRight + 1);

    }

    public boolean check() {
        for(Map.Entry<Character, Integer> entry : old.entrySet()) {
            char ch = entry.getKey();
            if(entry.getValue() > count.getOrDefault(ch, 0))
                return false;
        }
        return true;
    }
}

class Solution76_1 {
    public String minWindow(String s, String t) {
        if (s == null || s == "" || t == null || t == "" || s.length() < t.length()) {
            return "";
        }
        //维护两个数组，记录已有字符串指定字符的出现次数，和目标字符串指定字符的出现次数
        //ASCII表总长128
        int[] need = new int[128];
        int[] have = new int[128];

        //将目标字符串指定字符的出现次数记录
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }

        //分别为左指针，右指针，最小长度(初始值为一定不可达到的长度)
        //已有字符串中目标字符串指定字符的出现总频次以及最小覆盖子串在原字符串中的起始位置
        int left = 0, right = 0, min = s.length() + 1, count = 0, start = 0;
        while (right < s.length()) {
            char r = s.charAt(right);
            //说明该字符不被目标字符串需要，此时有两种情况
            // 1.循环刚开始，那么直接移动右指针即可，不需要做多余判断
            // 2.循环已经开始一段时间，此处又有两种情况
            //  2.1 上一次条件不满足，已有字符串指定字符出现次数不满足目标字符串指定字符出现次数，那么此时
            //      如果该字符还不被目标字符串需要，就不需要进行多余判断，右指针移动即可
            //  2.2 左指针已经移动完毕，那么此时就相当于循环刚开始，同理直接移动右指针
            if (need[r] == 0) {
                right++;
                continue;
            }
            //当且仅当已有字符串目标字符出现的次数小于目标字符串字符的出现次数时，count才会+1
            //是为了后续能直接判断已有字符串是否已经包含了目标字符串的所有字符，不需要挨个比对字符出现的次数
            if (have[r] < need[r]) {
                count++;
            }
            //已有字符串中目标字符出现的次数+1
            have[r]++;
            //移动右指针
            right++;
            //当且仅当已有字符串已经包含了所有目标字符串的字符，且出现频次一定大于或等于指定频次
            while (count == t.length()) {
                //挡窗口的长度比已有的最短值小时，更改最小值，并记录起始位置
                if (right - left < min) {
                    min = right - left;
                    start = left;
                }
                char l = s.charAt(left);
                //如果左边即将要去掉的字符不被目标字符串需要，那么不需要多余判断，直接可以移动左指针
                if (need[l] == 0) {
                    left++;
                    continue;
                }
                //如果左边即将要去掉的字符被目标字符串需要，且出现的频次正好等于指定频次，那么如果去掉了这个字符，
                //就不满足覆盖子串的条件，此时要破坏循环条件跳出循环，即控制目标字符串指定字符的出现总频次(count）-1
                if (have[l] == need[l]) {
                    count--;
                }
                //已有字符串中目标字符出现的次数-1
                have[l]--;
                //移动左指针
                left++;
            }
        }
        //如果最小长度还为初始值，说明没有符合条件的子串
        if (min == s.length() + 1) {
            return "";
        }
        //返回的为以记录的起始位置为起点，记录的最短长度为距离的指定字符串中截取的子串
        return s.substring(start, start + min);
    }
}