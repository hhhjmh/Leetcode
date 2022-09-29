package September;

import java.util.*;

public class the_20 {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7); t1.left = t2; t1.right = t3; t3.left = t4; t3.right = t5;
//        new Solution404().sumOfLeftLeaves(t1);

//        new Solution405().toHex(26);

//        new Solution400().findNthDigit(1000000000);

//        System.out.println(new Solution402().removeKdigits("1432219", 3));

        new Solution698().canPartitionKSubsets(new int[]{1,1,1,1}, 4);
    }
}

class Solution698 {
    int[] nums;
    int n, t, k;
    public boolean canPartitionKSubsets(int[] _nums, int _k) {
        nums = _nums; k = _k;
        int tot = 0;
        for (int x : nums) tot += x;
        if (tot % k != 0) return false; // 可行性剪枝
        Arrays.sort(nums);
        n = nums.length; t = tot / k;
        return dfs(n - 1, 0, 0, new boolean[n]);
    }
    boolean dfs(int idx, int cur, int cnt, boolean[] vis) {
        if (cnt == k) return true;
        if (cur == t) return dfs(n - 1, 0, cnt + 1, vis);
        for (int i = idx; i >= 0; i--) {  // 顺序性剪枝
            if (vis[i] || cur + nums[i] > t) continue;  // 可行性剪枝
            vis[i] = true;
            if (dfs(i - 1, cur + nums[i], cnt, vis)) return true;
            vis[i] = false;
            if (cur == 0) return false; // 可行性剪枝
        }
        return false;
    }
}

class Solution398 {

    Map<Integer, List<Integer>> map = new HashMap<>();
    public Solution398(int[] nums) {
        for(int i=0; i<nums.length; i++) {
            List<Integer> temp = map.getOrDefault(nums[i], new ArrayList<>());
            temp.add(i);
            map.put(nums[i], temp);
        }
    }

    public int pick(int target) {
        List<Integer> temp = map.get(target);
        return temp.get((int) (Math.random()*(temp.size())));
    }
}

class Solution399 {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();

        UnionFind unionFind = new UnionFind(2 * equationsSize);
        // 第 1 步：预处理，将变量的值与 id 进行映射，使得并查集的底层使用数组实现，方便编码
        Map<String, Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        for (int i = 0; i < equationsSize; i++) {
            List<String> equation = equations.get(i);
            String var1 = equation.get(0);
            String var2 = equation.get(1);

            if (!hashMap.containsKey(var1)) {
                hashMap.put(var1, id);
                id++;
            }
            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2, id);
                id++;
            }
            unionFind.union(hashMap.get(var1), hashMap.get(var2), values[i]);
        }

        // 第 2 步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);

            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    private class UnionFind {

        private int[] parent;

        /**
         * 指向的父结点的权值
         */
        private double[] weight;


        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }

        public void union(int x, int y, double value) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }

            parent[rootX] = rootY;
            // 关系式的推导请见「参考代码」下方的示意图
            weight[rootX] = weight[y] * value / weight[x];
        }

        /**
         * 路径压缩
         *
         * @param x
         * @return 根结点的 id
         */
        public int find(int x) {
            if (x != parent[x]) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        public double isConnected(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }
    }
}

class Solution400 {
    public int findNthDigit(int n) {
        int len = 1;
        while (len * 9 * Math.pow(10, len - 1) < n) {
            n -= len * 9 * Math.pow(10, len - 1);
            len++;
        }
        long s = (long) Math.pow(10, len - 1);
        long x = n / len - 1 + s;
        n -= (x - s + 1) * len;
        return n == 0 ? (int) (x % 10) : (int) ((x + 1) / Math.pow(10, len - n) % 10);
    }
}

class Solution402 {
    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new LinkedList<Character>();
        int length = num.length();
        for (int i = 0; i < length; ++i) {
            char digit = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }

        for (int i = 0; i < k; ++i) {
            deque.pollLast();
        }

        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            ret.append(digit);
        }
        return ret.length() == 0 ? "0" : ret.toString();
    }
}

class Solution403 {
    public boolean canCross(int[] stones) {
        int n = stones.length;
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;
        for (int i = 1; i < n; ++i) {
            if (stones[i] - stones[i - 1] > i) {
                return false;
            }
        }
        for (int i = 1; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                int k = stones[i] - stones[j];
                if (k > j + 1) {
                    break;
                }
                dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                if (i == n - 1 && dp[i][k]) {
                    return true;
                }
            }
        }
        return false;
    }
}


class Solution404 {
    public int sumOfLeftLeaves(TreeNode root) {
        int ans = 0;
        if(root==null)
            return ans;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for(int i=0; i<n; i++) {
                TreeNode temp = queue.poll();
                if(temp.left!=null) {
                    if(temp.left.left==null && temp.left.right==null)
                        ans += temp.left.val;
                    queue.offer(temp.left);
                }
                if(temp.right!=null)
                    queue.offer(temp.right);
            }
        }
        return ans;
    }
}

class Solution404_1 {
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        int res = 0;
        //判断节点是否是左叶子节点，如果是则将它的和累计起来
        if(root.left != null && root.left.left == null && root.left.right == null){
            res += root.left.val;
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right) + res;
    }
}

class Solution405 {
    public String toHex(int num) {
        StringBuilder sb = new StringBuilder();
        if(num==0)
            return "0";
        char[] cs = new char[] {'0', '1', '2', '3','4', '5','6', '7','8', '9','a', 'b','c', 'd','e', 'f',};
        while (num!=0) {
            int k = 0;
            for(int i=0; i<4; i++) {
                k += ((num & 1) << i);
                num >>>= 1;
            }
            sb.append(cs[k]);
        }
        return sb.reverse().toString();
    }
}

class Solution406 {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }
}

class Solution407 {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visit = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{i * n + j, heightMap[i][j]});
                    visit[i][j] = true;
                }
            }
        }
        int res = 0;
        int[] dirs = {-1, 0, 1, 0, -1};
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            for (int k = 0; k < 4; ++k) {
                int nx = curr[0] / n + dirs[k];
                int ny = curr[0] % n + dirs[k + 1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visit[nx][ny]) {
                    if (curr[1] > heightMap[nx][ny]) {
                        res += curr[1] - heightMap[nx][ny];
                    }
                    pq.offer(new int[]{nx * n + ny, Math.max(heightMap[nx][ny], curr[1])});
                    visit[nx][ny] = true;
                }
            }
        }
        return res;
    }
}


class Solution409 {
    public int longestPalindrome(String s) {
        int[] arr = new int[52];
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if(ch>='A' && ch<='Z')
                arr[ch-'A']++;
            else if(ch>='a' && ch<='z')
                arr[ch-'a'+26]++;
        }
        int ret = 0;
        boolean flag = false;
        for(int i=0; i<52; i++) {
            if(arr[i]%2==1)
                flag = true;
            ret += (arr[i]/2)*2;
        }
        return flag ? ret+1 : ret;
    }
}

class Solution410 {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }
        int[] sub = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sub[i + 1] = sub[i] + nums[i];
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, m); j++) {
                for (int k = 0; k < i; k++) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return f[n][m];
    }
}

class Solution412 {
    public List<String> fizzBuzz(int n) {
        List<String> ret = new ArrayList<>();
        for(int i=1; i<=n; i++) {
            if(i%15==0)
                ret.add("FizzBuzz");
            else if(i%3==0)
                ret.add("Fizz");
            else if(i%5==0)
                ret.add("Buzz");
            else
                ret.add(String.valueOf(i));
        }
        return ret;
    }
}