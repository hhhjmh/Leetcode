package August;

import java.util.*;

public class the_24 {
    public static void main(String[] args) {
//        new Solution72().minDistance("horse", "ros");

//        new Solution583().minDistance("leetcode", "etco");

//        new Solution712().minimumDeleteSum("delete", "leet");

//        new Solution1035().maxUncrossedLines(new int[]{1,3,7,1,7,5}, new int[]{1,9,2,5,1});

//        new Solution75().sortColors(new int[]{2});

//        new Solution324().wiggleSort(new int[]{1,5,1,1,6,4});

//        new Solution324_1().wiggleSort(new int[]{1,5,1,1,6,4});

//        new Solution77().combine(20, 20);

//        new Solution78().subsets(new int[]{1,2,3,4});

//        new Solution90().subsetsWithDup(new int[]{4,4,4,1,4});

        new Solution784().letterCasePermutation("A1B2c3");

    }
}

class Solution1460 {
    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target, arr);
    }
}

class Solution72 {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m+1][n+1];
        for(int i=0; i<m+1; i++)
            dp[i][0] = i;
        for(int i=0; i<n+1; i++)
            dp[0][i] = i;

        for(int i=1; i<m+1; i++) {
            for(int j=1; j<n+1; j++) {
                int left = dp[i][j-1];
                int down = dp[i-1][j];
                int left_down = dp[i-1][j-1];
                if(word1.charAt(i-1)==word2.charAt(j-1))
                    left_down--;
                dp[i][j] = 1+Math.min(Math.min(left, down), left_down);
            }
        }

        return dp[m][n];
    }
}

class Solution583 {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int [][] dp = new int[m+1][n+1];
        for(int i=0; i<m+1; i++)
            dp[i][0] = i;
        for(int i=0; i<n+1; i++)
            dp[0][i] = i;

        for(int i = 1; i<m+1; i++) {
            for(int j = 1; j<n+1; j++) {
                int left = dp[i][j-1]+1;
                int up = dp[i-1][j]+1;
                int left_up = dp[i-1][j-1];
                if(word1.charAt(i-1)!=word2.charAt(j-1))
                    left_up = dp[i-1][j-1]+2;
                dp[i][j] = Math.min(left_up, Math.min(left, up));
            }
        }
        return dp[m][n];
    }
}

class Solution583_1 {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int lcs = dp[m][n];
        return m - lcs + n - lcs;
    }
}

class Solution712 {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int [][] dp = new int[m+1][n+1];
        for(int i=1; i<m+1; i++)
            dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
        for(int i=1; i<n+1; i++)
            dp[0][i] = dp[0][i-1] + s2.charAt(i-1);

        for(int i = 1; i<m+1; i++) {
            for(int j = 1; j<n+1; j++) {
                if(s1.charAt(i-1)==s2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = Math.min(dp[i-1][j]+s1.charAt(i-1), dp[i][j-1]+s2.charAt(j-1));
            }
        }

        return dp[m][n];
    }
}

class Solution1035 {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int [][] dp = new int[m+1][n+1];
        for(int i=1; i<m+1; i++) {
            for(int j=1; j<n+1; j++) {
                if(nums1[i-1]==nums2[j-1])
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m][n];
    }
}


class Solution75 {
    public void sortColors(int[] nums) {
        int i=0, j = 0, k = nums.length-1;
        while (j<=k) {
            if(nums[j]==0) {
                nums[i] = 0;
                i++;
                j++;
            } else if(nums[j]==1) {
                j++;
            } else {
                swap(nums, j, k);
                k--;
            }
        }
        while (i<j) {
            nums[i] = 1;
            i++;
        }

        return;
    }

    private void swap(int[] nums, int j, int k) {
        int temp = nums[j];
        nums[j] = nums[k];
        nums[k] = temp;
    }
}

class Solution324 {
    public void wiggleSort(int[] nums) {
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int n = nums.length;
        int x = (n + 1) / 2;
        for (int i = 0, j = x - 1, k = n - 1; i < n; i += 2, j--, k--) {
            nums[i] = arr[j];
            if (i + 1 < n) {
                nums[i + 1] = arr[k];
            }
        }
    }
}

class Solution324_1 {
    int[] nums;
    int n;
    int qselect(int l, int r, int k) {
        if (l == r) return nums[l];
        int x = nums[l + r >> 1], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) swap(i, j);
        }
        int cnt = j - l + 1;
        if (k <= cnt) return qselect(l, j, k);
        else return qselect(j + 1, r, k - cnt);
    }
    void swap(int a, int b) {
        int c = nums[a];
        nums[a] = nums[b];
        nums[b] = c;
    }
    int getIdx(int x) {
        return (2 * x + 1) % (n | 1);
    }
    public void wiggleSort(int[] _nums) {
        nums = _nums;
        n = nums.length;
        int x = qselect(0, n - 1, n + 1 >> 1);
        int l = 0, r = n - 1, loc = 0;
        while (loc <= r) {
            if (nums[getIdx(loc)] > x) swap(getIdx(loc++), getIdx(l++));
            else if (nums[getIdx(loc)] < x) swap(getIdx(loc), getIdx(r--));
            else loc++;
        }
    }
}

class Solution324_2 {
    public void wiggleSort(int[] nums) {
        //5001个桶
        int[] bucket = new int[5001];
        for (int num : nums) {
            bucket[num]++;
        }
        int j = 5000;
        //插空放 较大元素
        for (int i = 1; i < nums.length; i += 2) {
            while (bucket[j] == 0) {
                j--;
            }
            nums[i] = j;
            bucket[j]--;
        }
        //插空放 较小元素
        for (int i = 0; i < nums.length; i += 2) {
            while (bucket[j] == 0) {
                j--;
            }
            nums[i] = j;
            bucket[j]--;
        }
    }
}

class Solution77 {

    List<List<Integer>> ans = new ArrayList<>();
    int len;

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> path = new ArrayList<>();
        len = k;
        boolean[] visited = new boolean[n];
        backTrack(path, visited, 0, n);
        return ans;
    }

    private void backTrack(List<Integer> path, boolean[] visited, int index, int n) {
        if(path.size() == len) {
            ans.add(new ArrayList<>(path));
        }

        for(int i=index; i<n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                path.add(i+1);
                backTrack(path, visited, i+1, n);
                path.remove(path.size()-1);
                visited[i] = false;
            }
        }
    }
}

class Solution78 {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> path = new ArrayList<>();
        backTrack(nums, path, 0, nums.length);
        return ans;
    }

    private void backTrack(int[] nums, List<Integer> path, int index, int length) {
        if(index==length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        backTrack(nums, path, index+1, length);
        path.add(nums[index]);
        backTrack(nums, path, index+1, length);
        path.remove(path.size()-1);
    }
}

class Solution90 {

    Set<List<Integer>> ans = new HashSet<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> path = new ArrayList<>();
        backTrack(nums, path, 0, nums.length);
        return new ArrayList<>(ans);
    }

    private void backTrack(int[] nums, List<Integer> path, int index, int length) {
        if(index==length) {
            List<Integer> temp = new ArrayList<>(path);
            Collections.sort(temp);
            ans.add(new ArrayList<>(temp));
            return;
        }

        backTrack(nums, path, index+1, length);
        path.add(nums[index]);
        backTrack(nums, path, index+1, length);
        path.remove(path.size()-1);
    }
}


class Solution90_1 {
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfs(false, 0, nums);
        return ans;
    }

    public void dfs(boolean choosePre, int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<Integer>(t));
            return;
        }
        dfs(false, cur + 1, nums);
        if (!choosePre && cur > 0 && nums[cur - 1] == nums[cur]) {
            return;
        }
        t.add(nums[cur]);
        dfs(true, cur + 1, nums);
        t.remove(t.size() - 1);
    }
}

class Solution784 {

    List<String> ans = new ArrayList<>();

    public List<String> letterCasePermutation(String s) {
        StringBuilder sb = new StringBuilder(s.toLowerCase());
        int len = s.length();

        backTrack(sb, 0, len);

        return ans;
    }

    private void backTrack(StringBuilder sb, int i, int len) {
        if(i==len) {
            ans.add(sb.toString());
            return;
        }
        if(!Character.isDigit(sb.charAt(i))) {
            char ch = sb.charAt(i);
            char ch1 = (char) (ch-32);
            backTrack(sb, i+1, len);
            sb.replace(i, i+1, Character.toString(ch1));
            backTrack(sb, i+1, len);
            sb.replace(i, i+1, Character.toString(ch));
        } else
            backTrack(sb, i+1, len);
    }
}