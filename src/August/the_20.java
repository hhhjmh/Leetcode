package August;

import java.util.*;

public class the_20 {
    public static void main(String[] args) {
//        new Solution654().constructMaximumBinaryTree(new int[]{3});

//        new Solution765().minSwapsCouples(new int[]{3,0,2,5,4,1});

/*        new Solution48().rotate(new int[][] {
                {5,1,9,11},
                {2,4,8,10},
                {13,3,6,7},
                {15,14,12,16},
        });*/

//        System.out.println(new Solution242().isAnagram("anagram", "nagaram"));

//        new Solution49().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});

//        new Solution50_1().myPow(2, -13);

//        new Solution69_1().mySqrt(4);

        System.out.println(new Solution372().superPow(2147483647, new int[]{2,0,0}));

    }
}

class Solution654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode root = DFS(nums, 0, nums.length-1);
        return root;
    }

    public TreeNode DFS(int[] nums, int start, int end) {
        if(start>end)
            return null;

        int index = start, maxValue = nums[start];
        for(int i=start; i<=end; i++) {
            if(maxValue<nums[i]) {
                index = i;
                maxValue = nums[i];
            }
        }
        TreeNode tree = new TreeNode(maxValue);
        tree.left = DFS(nums, start, index-1);
        tree.right = DFS(nums, index+1, end);
        return tree;
    }
}

class Solution765 {
    int[] p = new int[70];
    void union(int a, int b) {
        p[find(a)] = p[find(b)];
    }
    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
    public int minSwapsCouples(int[] row) {
        int n = row.length, m = n / 2;
        for (int i = 0; i < m; i++) p[i] = i;
        for (int i = 0; i < n; i += 2) union(row[i] / 2, row[i + 1] / 2);
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if (i == find(i)) cnt++;
        }
        return m - cnt;
    }
}

class Solution55 {
    public boolean canJump(int[] nums) {
        int rightMost = 0;
        int n = nums.length;
        for(int i=0; i<n; i++) {
            if(i<=rightMost) {
                rightMost = Math.max(rightMost, i+nums[i]);
                if(rightMost>=n-1)
                    return true;
            }
        }
        return false;
    }
}

class Solution45 {
    public int jump(int[] nums) {
        int end = 0, steps = 0;
        int rightMost = 0;
        int n = nums.length;
        for(int i=0; i<n-1; i++) {
            if(i<=rightMost) {
                rightMost = Math.max(rightMost, i+nums[i]);
                if(end==i) {
                    end = rightMost;
                    steps++;
                }

            }
        }
        return steps;
    }
}


class Solution48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length-1;
        for(int i=0; i<(n+1)/2; i++) {
            for(int j=0; j<(n+2)/2; j++) {
                swap(matrix, i, j, n);
            }
        }
        return;
    }

    private void swap(int[][] matrix, int i, int j, int n) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[n-j][i];
        matrix[n-j][i] = matrix[n-i][n-j];
        matrix[n-i][n-j] = matrix[j][n-i];
        matrix[j][n-i] = temp;
    }
}

class Solution242 {
    public boolean isAnagram(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);
        return String.valueOf(ch1).equals(String.valueOf(ch2));
    }
}

class Solution49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str: strs) {
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            String s = String.valueOf(temp);
            if(map.containsKey(s)) {
                for(Map.Entry<String, List<String>> entry: map.entrySet()) {
                    String key = entry.getKey();
                    if(isAnagram(key, s)) {
                        List<String> list = entry.getValue();
                        list.add(str);
                        map.put(key, list);
                        break;
                    }
                }
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(s, list);
            }
        }
        List<List<String>> res = new ArrayList<>();
        for(Map.Entry<String , List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    public boolean isAnagram(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);
        return String.valueOf(ch1).equals(String.valueOf(ch2));
    }
}

class Solution49_1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}

class Solution50 {
    public double myPow(double x, int n) {
        return Math.pow(x, n);
    }
}

class Solution50_1 {
    public double myPow(double x, int n) {
        double res = 1;
        for(int i=n; i!=0; i/=2) {
            if(i%2!=0) {
                res *= x;
            }
            x *= x;
        }
        return n>0 ? res: 1/res;
    }
}


class Solution69 {
    public int mySqrt(int x) {
        long i=0;
        while (i*i<=x) {
            i++;
        }
        return (int) i-1;
    }
}

class Solution69_1 {
    public int mySqrt(int x) {
        if(x==1)
            return 1;
        int min = 0, max = x;
        while (max-min>1) {
            int mid = min + (max-min)/2;
            if(x/mid<mid)
                max = mid;
            else
                min = mid;
        }
        return min;
    }
}

class Solution372 {
    int MOD = 1337;
    public int superPow(int a, int[] b) {
        return dfs(a, b, b.length - 1);
    }
    int dfs(int a, int[] b, int u) {
        if (u == -1) return 1;
        return qpow(dfs(a, b, u - 1), 10) * qpow(a, b[u]) % MOD;
    }
    int qpow(int a, int b) {
        int ans = 1;
        a %= MOD;
        while (b != 0) {
            if ((b & 1) != 0) ans = ans * a % MOD;
            a = a * a % MOD;
            b >>= 1;
        }
        return ans;
    }
}
