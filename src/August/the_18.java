package August;

import java.util.*;

public class the_18 {
    public static void main(String[] args) {
        int [] arr = {1,1,1,2,2,2};
//        System.out.println(new Solution1224().maxEqualFreq(arr));

        int [] arr1 = {5,1,1};
//        new Solution31().nextPermutation(arr1);

//        new Solution47().permuteUnique(new int[]{1,1,2});

//        new Solution60().getPermutation(9, 214267);

        new Solution34().searchRange(new int[]{5,7,7,8,8,10}, 5);
    }
}

class Solution1224 {
    public int maxEqualFreq(int[] nums) {
        int [] count = new int[100010];
        int [] sum = new int[100010];
        int res = 0, max = 0;
        for(int i=0; i<nums.length; i++) {
            count[nums[i]]++;
            sum[count[nums[i]]]++;
            sum[count[nums[i]]-1]--;
            max = Math.max(max, count[nums[i]]);
            if(max==1 || max * sum[max] == i || (max - 1) * (sum[max-1] + 1) == i)
                res = i+1;
        }
        return res;
    }


}

class Solution31 {
    public void nextPermutation(int[] nums) {
        int n = nums.length, i = n - 2;
        while (i>=0 && nums[i] >= nums[i+1])
            i--;

        if(i>=0) {
            int j = n-1;
            while (j>i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i+1);
        System.out.println(1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int n = nums.length - 1;
        while (start < n) {
            swap(nums, start, n);
            start++;
            n--;
        }
    }
}

class Solution46 {

    List<List<Integer>> res = new ArrayList<>();
    int len;

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> path = new ArrayList<>();
        len = nums.length;
        boolean [] used = new boolean[len];
        track(nums, path, used, 0);
        return res;
    }

    public void track(int[] nums, List<Integer> path, boolean[] used,int depth) {
        if(depth==len) {
            res.add(new ArrayList<>(path));
        }

        for(int i=0; i<len; i++) {
            if(!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                track(nums, path, used, depth+1);
                used[i] = false;
                path.remove(path.size()-1);
            }
        }
    }
}

class Solution47 {

    Set<List<Integer>> res = new HashSet<>();
    int len;

    public List<List<Integer>> permuteUnique(int[] nums) {
        len = nums.length;
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[len];
        Arrays.sort(nums);
        track(nums, path, used, 0);
        return new ArrayList<>(res);
    }

    public void track(int[] nums, List<Integer> path, boolean[] used, int depth) {
        if(depth==len) {
            res.add(new ArrayList<>(path));
        }

        for(int i=0; i<len; i++) {
            if(used[i] || (i>0 && nums[i]==nums[i-1] && !used[i-1]))
                continue;

            path.add(nums[i]);
            used[i] = true;
            track(nums, path, used, depth+1);
            used[i] = false;
            path.remove(path.size()-1);
        }
    }
}

class Solution47_1 {
    boolean[] vis;

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, ans, 0, perm);
        return ans;
    }

    public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, idx + 1, perm);
            vis[i] = false;
            perm.remove(idx);
        }
    }
}

class Solution60 {
    public String getPermutation(int n, int k) {

        List<Integer> candidates = new ArrayList<>();
        int[] factorial = new int[n+1];
        factorial[0] = 1;
        int fact = 1;
        for(int i=1; i<=n; i++) {
            candidates.add(i);
            fact *= i;
            factorial[i] = fact;
        }
        k--;
        StringBuilder sb = new StringBuilder();
        for(int i=n-1; i>=0; i--) {
            int m = k / factorial[i];
            sb.append(candidates.remove(m));
            k -= m * factorial[i];
        }
        return sb.toString();
    }
}

class Solution33 {
    public int search(int[] nums, int target) {
        for(int i=0; i<nums.length; i++) {
            if(nums[i]==target)
                return i;
        }
        return -1;
    }
}

class Solution33_1 {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len-1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] < nums[right]){
                if(nums[mid] < target && target <= nums[right])
                    left = mid+1;
                else
                    right = mid-1;
            }
            else{
                if(nums[left] <= target && target < nums[mid])
                    right = mid-1;
                else
                    left = mid+1;
            }
        }
        return -1;
    }
}


class Solution153 {
    public int findMin(int[] nums) {
        int res = nums[0];
        for(int i=0; i<nums.length; i++) {
            res = Math.min(res, nums[i]);
        }
        return res;
    }
}

class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int left = binarySearch(nums, target, true);
        int right = binarySearch(nums, target, false) - 1;
        if(left<=right && right < nums.length && nums[left]==target && nums[right]==target)
            return new int[] {left, right};
        return new int[]{-1, -1};

    }

    public int binarySearch(int[] nums, int target, boolean flag) {
        int left = 0, right = nums.length-1, res = nums.length;
        while (left <= right) {
            int mid = left + (right-left) / 2;
            if(nums[mid] > target || (flag && nums[mid]>=target)) {
                right = mid - 1;
                res = mid;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}


/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
class Solution278 {
    public int firstBadVersion(int n) {
        int left = 1, right = n;
/*        while (left < right) {
            int mid = left + (right - left)/2;
            if(isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }*/
        return left;
    }
}
