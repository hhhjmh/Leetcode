package September;

import java.util.*;

public class the_18 {
}

class Solution827 {
    static int N = 510;
    static int[] p = new int[N * N], sz = new int[N * N];
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
    void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return ;
        if (sz[ra] > sz[rb]) {
            union(b, a);
        } else {
            sz[rb] += sz[ra]; p[ra] = p[rb];
        }
    }
    public int largestIsland(int[][] g) {
        int n = g.length;
        for (int i = 1; i <= n * n; i++) {
            p[i] = i; sz[i] = 1;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 0) continue;
                for (int[] di : dirs) {
                    int x = i + di[0], y = j + di[1];
                    if (x < 0 || x >= n || y < 0 || y >= n || g[x][y] == 0) continue;
                    union(i * n + j + 1, x * n + y + 1);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    ans = Math.max(ans, sz[find(i * n + j + 1)]);
                } else {
                    int tot = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int[] di : dirs) {
                        int x = i + di[0],y = j + di[1];
                        if (x < 0 || x >= n || y < 0 || y >= n || g[x][y] == 0) continue;
                        int root = find(x * n + y + 1);
                        if (set.contains(root)) continue;
                        tot += sz[root];
                        set.add(root);
                    }
                    ans = Math.max(ans, tot);
                }
            }
        }
        return ans;
    }
}

class Solution378 {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int []> priorityQueue = new PriorityQueue<>((a, b)-> {
            return a[2]-b[2];
        });
        int n = matrix.length;
        for(int i=0; i<n; i++)
            priorityQueue.offer(new int[]{0,i, matrix[0][i]});
        int ans = 0;
        while (k>0) {
            k--;
            int[] temp = priorityQueue.poll();
            ans = temp[2];
            if(temp[0]+1<n) {
                priorityQueue.offer(new int[]{temp[0]+1, temp[1],matrix[temp[0]+1][temp[1]]});
            }
        }
        return ans;
    }
}

class Solution378_1 {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }
}

// 380
class RandomizedSet {

    int[] loc = new int[200000];
    Map<Integer, Integer> map = new HashMap<>();
    int index = -1;

    public RandomizedSet() {

    }

    public boolean insert(int val) {
        if(map.containsKey(val))
            return false;
        index++;
        loc[index] = val;
        map.put(val, index);
        return true;
    }

    public boolean remove(int val) {
        if(!map.containsKey(val))
            return false;
        int temp = map.get(val);
        if(temp!=index) map.put(loc[index], temp);
        loc[temp] = loc[index];
        index--;
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return loc[(int) ((index+1)*Math.random())];
    }
}

// 381
class RandomizedCollection {
    Map<Integer, Set<Integer>> idx;
    List<Integer> nums;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        idx = new HashMap<Integer, Set<Integer>>();
        nums = new ArrayList<Integer>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        nums.add(val);
        Set<Integer> set = idx.getOrDefault(val, new HashSet<Integer>());
        set.add(nums.size() - 1);
        idx.put(val, set);
        return set.size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!idx.containsKey(val)) {
            return false;
        }
        Iterator<Integer> it = idx.get(val).iterator();
        int i = it.next();
        int lastNum = nums.get(nums.size() - 1);
        nums.set(i, lastNum);
        idx.get(val).remove(i);
        idx.get(lastNum).remove(nums.size() - 1);
        if (i < nums.size() - 1) {
            idx.get(lastNum).add(i);
        }
        if (idx.get(val).size() == 0) {
            idx.remove(val);
        }
        nums.remove(nums.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get((int) (Math.random() * nums.size()));
    }
}

// 382
class Solution382 {

    int[] arr;
    int len;
    public Solution382(ListNode head) {
        ListNode l1 = head;
        int n = 0;
        while (l1!=null) {
            n++;
            l1 = l1.next;
        }
        arr = new int[n];
        len = n;
        l1 = head;
        for(int i=0; i<n; i++) {
            arr[i] = l1.val;
            l1 = l1.next;
        }
    }

    public int getRandom() {
        return arr[ (int) (Math.random() * (len+1))];
    }
}

class Solution383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] arr1 = new int[26];
        for(char ch : ransomNote.toCharArray())
            arr1[ch-'a']++;
        for(char ch : magazine.toCharArray())
            arr1[ch-'a']--;
        for(int num : arr1)
            if(num>0)
                return false;

        return true;
    }
}

// 384
class Solution384 {
    int[] nums;
    int n;
    Random random = new Random();
    public Solution384(int[] _nums) {
        nums = _nums;
        n = nums.length;
    }
    public int[] reset() {
        return nums;
    }
    public int[] shuffle() {
        int[] ans = nums.clone();
        for (int i = 0; i < n; i++) {
            swap(ans, i, i + random.nextInt(n - i));
        }
        return ans;
    }
    void swap(int[] arr, int i, int j) {
        int c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
    }
}

class Solution386 {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, j = 1; i < n; i++) {
            ans.add(j);
            if (j * 10 <= n) {
                j *= 10;
            } else {
                while (j % 10 == 9 || j + 1 > n) j /= 10;
                j++;
            }
        }
        return ans;
    }
}

class Solution387 {
    public int firstUniqChar(String s) {
        int[] cnt = new int[26];
        for(char ch : s.toCharArray())
            cnt[ch-'a']++;
        for(int i=0; i<s.length(); i++) {
            if(cnt[s.charAt(i)-'a']==1)
                return i;
        }
        return -1;
    }
}

class Solution388 {
    public int lengthLongestPath(String s) {
        Map<Integer, String> map = new HashMap<>();
        int n = s.length();
        String ans = null;
        for (int i = 0; i < n; ) {
            int level = 0;
            while (i < n && s.charAt(i) == '\t' && ++level >= 0) i++;
            int j = i;
            boolean isDir = true;
            while (j < n && s.charAt(j) != '\n') {
                if (s.charAt(j++) == '.') isDir = false;
            }
            String cur = s.substring(i, j);
            String prev = map.getOrDefault(level - 1, null);
            String path = prev == null ? cur : prev + "/" + cur;
            if (isDir) map.put(level, path);
            else if (ans == null || path.length() > ans.length()) ans = path;
            i = j + 1;
        }
        return ans == null ? 0 : ans.length();
    }
}

class Solution388_1 {
    static int[] hash = new int[10010];
    public int lengthLongestPath(String s) {
        Arrays.fill(hash, -1);
        int n = s.length(), ans = 0;
        for (int i = 0; i < n; ) {
            int level = 0;
            while (i < n && s.charAt(i) == '\t' && ++level >= 0) i++;
            int j = i;
            boolean isDir = true;
            while (j < n && s.charAt(j) != '\n') {
                if (s.charAt(j++) == '.') isDir = false;
            }
            int cur = j - i;
            int prev = level - 1 >= 0 ? hash[level - 1] : -1;
            int path = prev + 1 + cur;
            if (isDir) hash[level] = path;
            else if (path > ans) ans = path;
            i = j + 1;
        }
        return ans;
    }
}
