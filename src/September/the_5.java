package September;

import java.util.*;

public class the_5 {
    public static void main(String[] args) {
        new Solution179().largestNumber(new int[]{3,30,34,5,9});
    }
}


class Solution652 {
    Map<String, Integer> map = new HashMap<>();
    List<TreeNode> ans = new ArrayList<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        DFS(root);
        return ans;
    }

    private String DFS(TreeNode root) {
        if(root==null)
            return " ";
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append("_");
        sb.append(DFS(root.left)).append(DFS(root.right));
        String s = sb.toString();
        map.put(s, map.getOrDefault(s, 0) + 1);
        if(map.get(s)==2)
            ans.add(root);
        return s;
    }
}

class Solution169 {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> ans = new HashMap<>();
        int n = nums.length;
        for(int i=0; i<n; i++) {
            ans.put(nums[i], ans.getOrDefault(nums[i], 0) + 1);
            if(ans.get(nums[i])> n/2)
                return nums[i];
        }
        return 0;
    }
}

class Solution169_1 {
    public int majorityElement(int[] nums) {
        int x = 0, count = 0;
        for(int i=0; i<nums.length; i++) {
            if(x==nums[i])
                count++;
            else {
                count--;
                if(count<=0) {
                    x = nums[i];
                    count = 1;
                }
            }
        }
        return x;
    }
}

class Solution229 {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> ans = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        for(int i=0; i<n; i++) {
            ans.put(nums[i], ans.getOrDefault(nums[i], 0) + 1);
            if(ans.get(nums[i])> n/3 && !res.contains(nums[i]))
                res.add(nums[i]);
        }
        return res;
    }
}

class Solution229_1 {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        int a = 0, b = 0;
        int c1 = 0, c2 = 0;
        for (int i : nums) {
            if (c1 != 0 && a == i) c1++;
            else if (c2 != 0 && b == i) c2++;
            else if (c1 == 0 && ++c1 >= 0) a = i;
            else if (c2 == 0 && ++c2 >= 0) b = i;
            else {
                c1--; c2--;
            }
        }
        c1 = 0; c2 = 0;
        for (int i : nums) {
            if (a == i) c1++;
            else if (b == i) c2++;
        }
        List<Integer> ans = new ArrayList<>();
        if (c1 > n / 3) ans.add(a);
        if (c2 > n / 3) ans.add(b);
        return ans;
    }
}

class Solution172 {
    public int trailingZeroes(int n) {
        int ans = 0;
        while (n>0) {
            ans += n /5;
            n /= 5;
        }

        return ans;
    }
}

class Solution233 {
    public int countDigitOne(int n) {
        int ans = 0;
        for(int k=1; k<=n; k*=10) {
            int temp = n % (k*10);
            ans += (n/(k*10)) * k + Math.min(Math.max(temp-k+1, 0), k);
        }
        return ans;
    }
}

class Solution179 {
    public String largestNumber(int[] nums) {
        for(int i=nums.length-1; i>0; i--) {
            for(int j=0; j<i; j++) {
                swap(nums, j, j+1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<nums.length; i++)
            sb.append(nums[i]);
        if(sb.charAt(0)=='0')
            return "0";
        return sb.toString();
    }

    private void swap(int[] nums, int i, int j) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        s1.append(nums[i]).append(nums[j]);
        s2.append(nums[j]).append(nums[i]);
        if(Long.valueOf(s1.toString())<Long.valueOf(s2.toString())) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

}