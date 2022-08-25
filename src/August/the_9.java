package August;

import java.util.*;

public class the_9 {
    public static void main(String[] args) {
        Solution29 solution29 = new Solution29();
//        solution29.divide(-2147483648, -3);

        Solution29_1 solution29_1 = new Solution29_1();
//        solution29_1.divide(-2147483648, -3);

        Solution30_1 solution30_1 = new Solution30_1();
        solution30_1.findSubstring("barfoofoobarthefoobarman", new String[]{"bar","foo","the"});
    }
}

class Solution1413 {
    public int minStartValue(int[] nums) {
        int sum = 0, res = 1;
        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            res = sum+res > 0 ? res : -(sum-1);
        }
        return res;
    }
}

class Solution29 {
    public int divide(int dividend, int divisor) {
        if(dividend==Integer.MIN_VALUE) {
            if(divisor==1)
                return Integer.MIN_VALUE;
            if(divisor==-1)
                return Integer.MAX_VALUE;
        }

        if(divisor==Integer.MIN_VALUE)
            return dividend==Integer.MIN_VALUE ? 1 : 0;

        if(dividend==0)
            return 0;

        boolean flag = (dividend ^ divisor) >= 0;
        dividend = dividend>0 ? dividend : -dividend;
        divisor = Math.abs(divisor);

        List<Integer> candidates = new ArrayList<>();
        candidates.add(divisor);
        int index = 0;
        while (candidates.get(index) <= dividend - candidates.get(index)) {
            candidates.add(candidates.get(index)+candidates.get(index));
            index++;
        }

        int res = 0;

        for(int i=candidates.size()-1; i>=0; i--) {
            if(dividend>=candidates.get(i)) {
                res += 1 << i;
                dividend -= candidates.get(i);
            }
        }
        return flag ? res : -res;
    }
}

class Solution29_1 {
    public int divide(int dividend, int divisor) {
        // 考虑被除数为最小值的情况
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        // 考虑除数为最小值的情况
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        // 考虑被除数为 0 的情况
        if (dividend == 0) {
            return 0;
        }

        // 一般情况，使用类二分查找
        // 将所有的正数取相反数，这样就只需要考虑一种情况
        boolean rev = false;
        if (dividend > 0) {
            dividend = -dividend;
            rev = !rev;
        }
        if (divisor > 0) {
            divisor = -divisor;
            rev = !rev;
        }

        List<Integer> candidates = new ArrayList<Integer>();
        candidates.add(divisor);
        int index = 0;
        // 注意溢出
        while (candidates.get(index) >= dividend - candidates.get(index)) {
            candidates.add(candidates.get(index) + candidates.get(index));
            ++index;
        }
        int ans = 0;
        for (int i = candidates.size() - 1; i >= 0; --i) {
            if (candidates.get(i) >= dividend) {
                ans += 1 << i;
                dividend -= candidates.get(i);
            }
        }

        return rev ? -ans : ans;
    }
}

class Solution30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int n = s.length(), m = words.length, num = words[0].length();
        Map<String, Integer> map = new HashMap<>();
        for(String str : words) {
            map.put(str,map.getOrDefault(str, 0) + 1);
        }

        for(int i=0; i<n-m*num+1; i++) {
            Map<String, Integer> temp = new HashMap<>();
            String str = s.substring(i, i+m*num);
            for(int j=0; j<m*num; j+= num) {
                String s1 = str.substring(j, j+num);
                temp.put(s1, temp.getOrDefault(s1, 0) + 1);
            }
            if(temp.equals(map))
                res.add(i);
        }
        return res;
    }
}

class Solution30_1 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        int one_word = words[0].length();
        int word_num = words.length;
        int all_len = one_word * word_num;
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < one_word; i++) {
            int left = i, right = i, count = 0;
            HashMap<String, Integer> tmp_map = new HashMap<>();
            while (right + one_word <= s.length()) {
                String w = s.substring(right, right + one_word);
                right += one_word;
                if (!map.containsKey(w)) {
                    count = 0;
                    left = right;
                    tmp_map.clear();
                } else {
                    tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                    count++;
                    while (tmp_map.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
                        String t_w = s.substring(left, left + one_word);
                        count--;
                        tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                        left += one_word;
                    }
                    if (count == word_num) res.add(left);
                }
            }
        }
        return res;
    }
}

