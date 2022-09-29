package September;

import java.util.*;

public class the_14 {
    public static void main(String[] args) {
        /*new Solution310().findMinHeightTrees(6, new int[][] {
                {3,0},{3,1},{3,2},{3,4},{5,4}
        });*/

//        new Solution312().maxCoins(new int[] {1,3,1,5,8,1});

//        new Solution313().nthSuperUglyNumber(12, new int[] {2,7,13,19});

        new Solution315().countSmaller(new int[]{3,9,5,2,6,1,3});
    }
}

class Solution1619 {
    public double trimMean(int[] arr) {
        int n = arr.length, m = n/20;
        Arrays.sort(arr);
        int ans = 0;
        for(int i=m; i<n-m; i++) {
            ans += arr[i];
        }
        return (ans*1.0) / (n-2*m);
    }
}


class Solution310 {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if(n==1) {
            res.add(0);
            return res;
        }
        int [] degree = new int[n];
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<n; i++) {
            list.add(new ArrayList<>());
        }

        for(int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<n; i++) {
            if(degree[i]==1)
                queue.offer(i);
        }

        while (!queue.isEmpty()) {
            res = new ArrayList<>();
            int size = queue.size();
            for(int i=0; i<size; i++) {
                int cur = queue.poll();
                res.add(cur);
                List<Integer> neighbors = list.get(cur);
                for(int neighbor : neighbors) {
                    degree[neighbor]--;
                    if(degree[neighbor]==1)
                        queue.offer(neighbor);
                }
            }
        }

        return res;
    }
}

class Solution312 {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] rec = new int[n + 2][n + 2];
        int[] val = new int[n + 2];
        val[0] = val[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            val[i] = nums[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j <= n + 1; j++) {
                for (int k = i + 1; k < j; k++) {
                    int sum = val[i] * val[k] * val[j];
                    sum += rec[i][k] + rec[k][j];
                    rec[i][j] = Math.max(rec[i][j], sum);
                }
            }
        }
        return rec[0][n + 1];
    }
}

class Solution313 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int m = primes.length;
        int[] arr = new int[m];
        long[] dp = new long[n];
        dp[0] = 1;
        int ans = 0;
        for(int i=1; i<n; i++) {
            dp[i] = primes[0]*dp[arr[0]];
            for(int j=1; j<m; j++) {
                dp[i] = Math.min(dp[i], primes[j]*dp[arr[j]]);
            }
            for(int j=0; j<m; j++) {
                if(dp[i]==primes[j]*dp[arr[j]]) {
                    arr[j]++;
                }
            }
        }
        return (int)dp[n-1];
    }
}

class Solution315 {
    int[] a;
    int[] c;
    public List<Integer> countSmaller(int[] nums) {
        discretization(nums);
        init(nums.length);
        List<Integer> res = new ArrayList<>();
        for(int i=nums.length-1; i>=0; i--) {
            int id = getId(nums[i]);
            res.add(query(id-1));
            update(id);
        }
        Collections.reverse(res);
        return res;
    }

    private void init(int len) {
        c = new int[len];
    }

    private int lowBit(int x) {
        return x & (-x);
    }

    private void update(int pos) {
        while (pos<c.length) {
            c[pos]++;
            pos += lowBit(pos);
        }
    }

    private int query(int pos) {
        int ans = 0;
        while (pos>0) {
            ans += c[pos];
            pos -= lowBit(pos);
        }
        return ans;
    }

    private int getId(int x) {
        return Arrays.binarySearch(a, x) + 1;
    }

    private void discretization(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }
        int size = set.size();
        a = new int[size];
        int index = 0;
        for(int num : set) {
            a[index++] = num;
        }
        Arrays.sort(a);
    }
}

class Solution316 {
    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            Character ch = s.charAt(i);
            if(stack.contains(ch))
                continue;
            while (!stack.isEmpty() && stack.peek()>ch && s.indexOf(stack.peek(), i)!=-1)
                stack.pop();
            stack.push(ch);
        }
        char[] chars = new char[stack.size()];
        for(int i=chars.length-1; i>=0; i--) {
            chars[i] = stack.pop();
        }
        return new String(chars);
    }
}