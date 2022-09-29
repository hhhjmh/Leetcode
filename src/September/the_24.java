package September;

import java.util.*;

public class the_24 {
    public static void main(String[] args) {
//        new Solution1652().decrypt(new int[]{2,4,9,3}, -2);

        /*new Solution447().numberOfBoomerangs(new int[][] {
                {0,0},{0,1},{0,2},
        });*/

//        new Solution457().circularArrayLoop(new int[]{-1,-2,-3,-4,-5});

        new Solution458().poorPigs(1000, 15, 60);

    }
}


class Solution1652 {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] ans = new int[n];
        if(k==0)
            return ans;
        for(int i=0; i<n; i++) {
            int count = 0;
            if(k<0) {
                for(int j=i+k+n; (j%n)!=i; j++) {
                    count += code[(j%n)];
                }
            } else {
                for(int j=i+k; (j%n)!=i; j--) {
                    count += code[(j%n)];
                }
            }
            ans[i] = count;
        }
        return ans;
    }
}

class Solution447 {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0, n = points.length;
        if(n<2)
            return ans;
        for(int i=0; i<n; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for(int j=0; j<n; j++) {
                if(i==j)
                    continue;
                int x = points[i][0]-points[j][0], y = points[i][1] - points[j][1];
                int dict = x*x + y*y;
                map.put(dict, map.getOrDefault(dict, 0) + 1);
            }

            for(int dict : map.keySet()) {
                int cnt = map.get(dict);
                ans += cnt * (cnt - 1);
            }
        }
        return ans;
    }


}

class Solution450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null)
            return root;
        if(root.val == key) {
            if(root.left==null) return root.right;
            if(root.right==null) return root.left;
            TreeNode t = root.left;
            while (t.right!=null) t = t.right;
            t.right = root.right;
            return root.left;
        } else if(root.val<key) root.right = deleteNode(root.right, key);
        else root.left = deleteNode(root.left, key);
        return root;
    }

}

class Solution451 {
    public String frequencySort(String s) {
        int[][] arr = new int[128][2];
        for(int i=0; i<128; i++)
            arr[i][0] = i;
        for(char ch : s.toCharArray())
            arr[ch][1]++;

        Arrays.sort(arr, (a, b) -> {
            return b[1] - a[1];
        });
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<128; i++) {
            if(arr[i][1]==0)
                break;
            while (arr[i][1]>0) {
                sb.append((char) (arr[i][0]));
                arr[i][1]--;
            }
        }
        return sb.toString();
    }
}

class Solution452 {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, (a, b)-> {
            return a[0] - b[0];
        });

        int ans = 1, n = points.length;
        int left = points[0][0], right = points[0][1];
        for(int i=1; i<n; i++) {
            if( (points[i][0] >= left && points[i][0]<=right) || (points[i][1] >= left && points[i][1]<=right) ) {
                left = Math.max(left, points[i][0]);
                right = Math.min(right, points[i][1]);
            } else {
                left = points[i][0];
                right = points[i][1];
                ans++;
            }
        }
        return ans;
    }
}

class Solution453 {
    public int minMoves(int[] nums) {
        int min = Arrays.stream(nums).min().getAsInt();
        int ans = 0;
        for(int num : nums)
            ans += (num-min);
        return ans;
    }
}

class Solution454 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums1.length, ans = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int num = nums1[i] + nums2[j];
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int num = nums3[i] + nums4[j];
                if(map.containsKey(-num))
                    ans += map.get(-num);
            }
        }
        return ans;

    }
}

class Solution455 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length, n = s.length, ans = 0;
        for(int i=0,j=0; i<m && j<n; j++) {
            if(g[i]<=s[j]) {
                i++;
                ans++;
            }
        }
        return ans;
    }
}

class Solution457 {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        // 使用 vis 数组对每个下标进行标记
        // 如果下标为 i 的位置在第 idx 轮被标记，则有 vis[i] = idx
        int[] vis = new int[n];
        for (int start = 0, idx = 1; start < n; start++, idx++) {
            if (vis[start] != 0) continue;
            int cur = start;
            boolean flag = nums[cur] > 0;
            while (true) {
                int next = ((cur + nums[cur]) % n + n) % n;
                if (next == cur) break;
                if (vis[next] != 0) {
                    // 如果 next 点已经被标记过，并且不是在本轮被标记，那么往后的通路必然都被标记，且无环，跳出
                    if (vis[next] != idx) break;
                        // 如果 next 点已被标记，并且是本来被标记，说明找到了环
                    else return true;
                }
                if (flag && nums[next] < 0) break;
                if (!flag && nums[next] > 0) break;
                vis[next] = idx;
                cur = next;
            }
        }
        return false;
    }
}

class Solution458 {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int x = minutesToTest / minutesToDie + 1, count = 1;
        int ans = x;
        while (ans < buckets) {
            ans *= x;
            count++;
        }
        return count;
    }
}