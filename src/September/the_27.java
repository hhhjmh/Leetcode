package September;

import java.util.*;

public class the_27 {
    public static void main(String[] args) {
//        new Solution482().licenseKeyFormatting("2-5g-3-J", 2);

        new Solution500().findWords(new String[]{"omk"});
    }
}

class Solution0102 {
    public boolean CheckPermutation(String s1, String s2) {
        if(s1.length()!=s2.length())
            return false;
        int[] cs = new int[128];
        for(char ch : s1.toCharArray()) {
            cs[ch]++;
        }
        for(char ch : s2.toCharArray()) {
            cs[ch]--;
            if(cs[ch]<0)
                return false;
        }
        return true;
    }
}

class Solution475 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int m = houses.length, n = heaters.length;
        int[] arr = new int[m];
        Arrays.fill(arr, Integer.MAX_VALUE);
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                arr[j] = Math.min(arr[j], Math.abs(houses[j]-heaters[i]));
            }
        }
        int ret = Integer.MIN_VALUE;
        for(int num : arr) {
            ret = Math.max(ret, num);
        }
        return ret;
    }
}

class Solution475_1 {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int ans = 0;
        for (int i = 0, j = 0; i < houses.length; i++) {
            int curDistance = Math.abs(houses[i] - heaters[j]);
            while (j < heaters.length - 1 && Math.abs(houses[i] - heaters[j]) >= Math.abs(houses[i] - heaters[j + 1])) {
                j++;
                curDistance = Math.min(curDistance, Math.abs(houses[i] - heaters[j]));
            }
            ans = Math.max(ans, curDistance);
        }
        return ans;
    }
}

class Solution477 {
    public int totalHammingDistance(int[] nums) {
        int ans = 0, n = nums.length;
        for(int i=0; i<30; i++) {
            int c = 0;
            for(int num : nums) {
                c += (num >> i) & 1;
            }
            ans += c * (n-c);
        }
        return ans;
    }


}

/*class Solution {

    double r, x, y;
    public Solution(double radius, double x_center, double y_center) {
        r = radius;
        x = x_center;
        y = y_center;
    }

    public double[] randPoint() {
        double radius = Math.sqrt(Math.random() * r * r);
        double angel = Math.random() * 360;
        double x_add = x + radius * Math.sin(angel);
        double y_add = y + radius * Math.cos(angel);
        return new double[] {x_add, y_add};
    }
}*/

class Solution479 {
    public int largestPalindrome(int n) {
        int[] ans = {0, 9, 987, 123, 597, 677, 1218, 877, 475};
        return ans[n];
    }
}

class Solution480 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        /*
        滑窗+对顶堆:
        我们创建两个堆left和right,其中left是大顶堆存储小的一半元素,right为小顶堆存储大的一半元素
        假定right存储的元素数目总是>=left存储的元素数目
        1.当窗口元素总数为奇数时:中位数为排序k/2的数字,此时直接right堆顶就是答案
        2.当窗口元素总数为偶数时:中位数为排序k/2与(k-1)/2的均值,此时将left堆顶与right堆顶取均值即可\
        还要注意的是:窗口滑动过程中我们加入与删除元素后记得调整堆使得堆平衡
         */
        int len = nums.length;
        int cnt = len - k + 1;  // 滑窗个数
        double[] res = new double[cnt];
        // Integer.compare(b, a)逻辑为:(x < y) ? -1 : ((x == y) ? 0 : 1) 只比较不会加减
        PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); // 大顶堆(注意不要b-a防止溢出)
        PriorityQueue<Integer> right = new PriorityQueue<>((a, b) -> Integer.compare(a, b)); // 小顶堆
        // 初始化堆:[0,k-1] 使得right>=left
        for (int i = 0; i < k; i++) {
            right.add(nums[i]);
        }
        for (int i = 0; i < k / 2; i++) {
            left.add(right.poll()); // 弹出最小的数字给left
        }
        // 首个中位数加入res
        res[0] = getMid(left, right);
        // 这里的i代表即将加入窗口的右端元素
        for (int i = k; i < len; i++) {
            int a = nums[i], b = nums[i - k];   // a为即将加入窗口的元素,b为即将退出窗口的元素
            if (a >= right.peek()) {
                right.add(a);
            } else {
                left.add(a);
            }
            if (b >= right.peek()) {
                right.remove(b);
            } else {
                left.remove(b);
            }
            // 调整堆
            adjust(left, right);
            // 该窗口中位数加入结果
            res[i - k + 1] = getMid(left, right);
        }
        return res;
    }

    // 调整堆使得堆平衡
    private void adjust(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        while (left.size() > right.size()) right.add(left.poll());  // 左边比右边多,左边必定不符合条件,往右边搬
        while (right.size() > left.size() + 1) left.add(right.poll());  // 右边比左边多1以上,右边必定多了,往左边搬
    }

    // 根据left与right两个堆返回中位数
    private double getMid(PriorityQueue<Integer> left, PriorityQueue<Integer> right) {
        if (left.size() == right.size()) return left.peek() / 2.0 + right.peek() / 2.0; // 范围不知道防止溢出
        else return (double) right.peek();
    }
}

class Solution482 {
    public String licenseKeyFormatting(String s, int k) {
        s = s.toUpperCase();
        StringBuilder sb = new StringBuilder();
        int m = 0;
        for(int i=s.length()-1 ; i>=0; i--) {
            char ch = s.charAt(i);
            if(ch=='-')
                continue;
            if(m==k) {
                sb.append('-');
                m = 0;
            }
            if((ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) {
                m++;
                sb.append(ch);
            }
        }
        return sb.reverse().toString();
    }
}

class Solution485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0, cur = nums[0];
        for(int i=1; i<nums.length; i++) {
            if(nums[i]==1) {
                cur++;
                ans = Math.max(ans, cur);
            } else {
                cur = 0;
            }
        }
        return ans;
    }
}

class Solution492 {
    public int[] constructRectangle(int area) {
        int L = (int) Math.sqrt(area);
        while (area%L!=0) {
            L++;
        }
        int W = area / L;
        if(W>L) {
            return new int[] {W, L};
        }
        return new int[] {L, W};
    }
}

class Solution496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums2.length; i++) {
            map.put(nums2[i], i);
        }
        for(int i=0; i<n; i++) {
            for(int j=map.get(nums1[i]); j<nums2.length; j++) {
                if(nums2[j]>nums1[i]) {
                    ans[i] = nums2[j];
                    break;
                }
            }
        }
        return ans;
    }
}

class Solution500 {
    public String[] findWords(String[] words) {
        String s1 = "QWERTYUIOP";
        String s2 = "ASDFGHJKL";
        String s3 = "ZXCVBNM";
        List<String> ss = new ArrayList<>();
        for(int i=0; i<words.length; i++) {
            String s = words[i].toUpperCase();
            int len = s.length();
            int b1 = 0, b2 = 0, b3 = 0;
            for(int j=0; j<len; j++) {
                char ch = s.charAt(j);
                if(s1.indexOf(ch)>-1) {
                    b1 = 1;
                } else if(s2.indexOf(ch)>-1) {
                    b2 = 1;
                } else {
                    b3 = 1;
                }
            }
            if( (b1+b2+b3) == 1 )
                ss.add(words[i]);
        }
        String[] strings = new String[ss.size()];
        for(int i=0; i<ss.size(); i++) {
            strings[i] = ss.get(i);
        }
        return strings;
    }
}
