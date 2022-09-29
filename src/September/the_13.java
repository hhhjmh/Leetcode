package September;

import java.util.Arrays;

public class the_13 {
    public static void main(String[] args) {
        new Solution670().maximumSwap(2736);
    }
}

class Solution670 {
    public int maximumSwap(int num) {
        String s = String.valueOf(num);
        char[] chars = s.toCharArray();
        int n = s.length();
        for(int i=0; i<n-1; i++) {
            int p = chars[i] - '0';
            int max = p;
            int k = 0;
            for(int j=n-1; j>i; j--) {
               if(chars[j]-'0'>max) {
                   max = chars[j] - '0';
                   k = j;
               }
            }
            if(max>p) {
                char temp = chars[i];
                chars[i] = chars[k];
                chars[k] = temp;
                break;
            }
        }
        String s1 = new String(chars);
        return Integer.valueOf(s1);
    }
}

class Solution300 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 1;
        for(int i=1; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(nums[i]>nums[j])
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }
}

class Solution300_1 {
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
}

// 304
class NumMatrix {

    int[][] arr;
    int m, n;

    public NumMatrix(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        arr = new int[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(i==0 && j==0) {
                    arr[i][j] = matrix[i][j];
                } else if(i==0) {
                    arr[i][j] = arr[i][j-1] + matrix[i][j];
                } else if(j==0) {
                    arr[i][j] = arr[i-1][j] + matrix[i][j];
                } else {
                    arr[i][j] = arr[i-1][j] + arr[i][j-1] - arr[i-1][j-1] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int left = col1 == 0 ? 0 : arr[row2][col1-1];
        int up = row1 == 0 ? 0 : arr[row1-1][col2];
        int left_up = 0;
        if(col1!=0 && row1!=0) {
            left_up = arr[row1-1][col1-1];
        }
        return arr[row2][col2] - up-left + left_up;
    }
}

class Solution306 {
    

    public boolean isAdditiveNumber(String num) {
        return DFS(num, 0, 0, 0, 0);
    }

    private boolean DFS(String num, int index, int count, long prevprev, long prev) {
        if(index>=num.length() ) {
            return count > 2;
        }

        long current = 0;
        for(int i=index; i<num.length(); i++) {
            char ch = num.charAt(i);
            if(num.charAt(index)=='0' && i>index) {
                return false;
            }

            current = current * 10 + ch-'0';

            if(count>=2) {
                long sum = prev + prevprev;
                if(current>sum) {
                    return false;
                }
                if(current<sum) {
                    continue;
                }
            }

            if(DFS(num, i+1, count+1, prev, current))
                return true;
        }
        return false;
    }

}

class NumArray307 {
    int[] tree;
    int lowbit(int x) {
        return x & -x;
    }
    int query(int x) {
        int ans = 0;
        for (int i = x; i > 0; i -= lowbit(i)) ans += tree[i];
        return ans;
    }
    void add(int x, int u) {
        for (int i = x; i <= n; i += lowbit(i)) tree[i] += u;
    }

    int[] nums;
    int n;
    public NumArray307(int[] _nums) {
        nums = _nums;
        n = nums.length;
        tree = new int[n + 1];
        for (int i = 0; i < n; i++) add(i + 1, nums[i]);
    }

    public void update(int i, int val) {
        add(i + 1, val - nums[i]);
        nums[i] = val;
    }

    public int sumRange(int l, int r) {
        return query(r + 1) - query(l);
    }
}
