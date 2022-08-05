package August;

import java.util.*;

public class the_4 {
    public static void main(String[] args) {
        int[] nums = {-1,2,1,-4};
        Solution16 solution16 = new Solution16();
//        solution16.threeSumClosest(nums, 1);

        Solution17 solution17 = new Solution17();
        solution17.letterCombinations("233");

    }
}

class Solution1430 {
    public List<Integer> minSubsequence(int[] nums) {
        int n = nums.length;
        List<Integer> res = new ArrayList<>();
        int sum = 0, temp = 0;
        Arrays.sort(nums);
        for(int num: nums) {
            sum += num;
        }
        for(int i=n-1; i>=0; i--) {
            temp += nums[i];
            res.add(nums[i]);
            if(temp > sum/2) {
                break;
            }
        }
        return res;
    }
}

class Solution16 {
    public int threeSumClosest(int[] nums, int target) {
        int res = 0, near = Integer.MAX_VALUE, n = nums.length;

        Arrays.sort(nums);
        for(int i=0; i<n-2; i++) {
            if(i!=0 && nums[i]==nums[i-1])
                continue;
            for(int j=i+1, k=n-1; j<k;) {
                int temp = nums[i]+nums[j]+nums[k];
                if(Math.abs(target-temp)<near) {
                    res = temp;
                    near = Math.abs(target-temp);
                } if(target-temp>0) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return res;
    }
}

class Solution17 {

    String[] nums = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    Set<String> res = new HashSet<>();
    public List<String> letterCombinations(String digits) {
        if(digits.length()==0)
            return new ArrayList<>();
        track(digits);
        List<String> ans = new ArrayList<>(res);
        return ans;
    }

    public void track(String digits) {

        String s = nums[digits.charAt(0)-'2'];
        Set<String> temp = new HashSet<>();
        for(int i=0; i<s.length(); i++) {
            if(res.isEmpty()) {
                temp.add(s.substring(i, i+1));
            } else {
                for(String item : res) {
                    temp.add(item + s.substring(i, i+1));
                }
            }
        }
        res = temp;
        if(digits.length()==1)
            return;
        track(digits.substring(1, digits.length()));
    }
}
