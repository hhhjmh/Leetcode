package September;

import java.util.*;

public class the_16 {
}

class Solution850 {
    int MOD = (int)1e9+7;
    public int rectangleArea(int[][] rs) {
        List<Integer> list = new ArrayList<>();
        for (int[] info : rs) {
            list.add(info[0]); list.add(info[2]);
        }
        Collections.sort(list);
        long ans = 0;
        for (int i = 1; i < list.size(); i++) {
            int a = list.get(i - 1), b = list.get(i), len = b - a;
            if (len == 0) continue;
            List<int[]> lines = new ArrayList<>();
            for (int[] info : rs) {
                if (info[0] <= a && b <= info[2]) lines.add(new int[]{info[1], info[3]});
            }
            Collections.sort(lines, (l1, l2)->{
                return l1[0] != l2[0] ? l1[0] - l2[0] : l1[1] - l2[1];
            });
            long tot = 0, l = -1, r = -1;
            for (int[] cur : lines) {
                if (cur[0] > r) {
                    tot += r - l;
                    l = cur[0]; r = cur[1];
                } else if (cur[1] > r) {
                    r = cur[1];
                }
            }
            tot += r - l;
            ans += tot * len;
            ans %= MOD;
        }
        return (int) ans;
    }
}

class Solution334 {
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for(int num : nums) {
            if(num <= first)
                first = num;
            else if(num <= second)
                second = num;
            else
                return true;
        }
        return false;
    }
}

class Solution335 {
    public boolean isSelfCrossing(int[] d) {
        int n = d.length;
        if (n < 4) return false;
        for (int i = 3; i < n; i++) {
            if (d[i] >= d[i - 2] && d[i - 1] <= d[i - 3]) return true;
            if (i >= 4 && d[i - 1] == d[i - 3] && d[i] + d[i - 4] >= d[i - 2]) return true;
            if (i >= 5 && d[i - 1] <= d[i - 3] && d[i - 2] > d[i - 4] && d[i] + d[i - 4] >= d[i - 2] && d[i - 1] + d[i - 5] >= d[i - 3]) return true;
        }
        return false;
    }
}

class Solution337 {
    public int rob(TreeNode root) {
        return DFS(root);
    }

    private int DFS(TreeNode root) {
        if(root==null)
            return 0;
        if(root.left==null && root.right==null)
            return root.val;
        DFS(root.left);
        DFS(root.right);
        int first = 0, second = 0;
        if(root.left!=null) {
            first +=  root.left.left==null ? 0 : root.left.left.val;
            first +=  root.left.right==null ? 0 : root.left.right.val;
            second += root.right.val;
        }
        if(root.right!=null) {
            first +=  root.right.left==null ? 0 : root.right.left.val;
            first +=  root.right.right==null ? 0 : root.right.right.val;
            second += root.left.val;
        }
        root.val = Math.max(first + root.val, second);
        return root.val;
    }
}

class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey(), val = entry.getValue();
            if(priorityQueue.size()==k) {
                if(priorityQueue.peek()[1]<val) {
                    priorityQueue.poll();
                    priorityQueue.offer(new int[]{key, val});
                }
            } else {
                priorityQueue.offer(new int[]{key, val});
            }
        }
        int[] ret = new int[k];
        for(int i=k-1; i>=0; i--) {
            ret[i] = priorityQueue.poll()[0];
        }
        return ret;
    }
}

// 352
class SummaryRanges {
    TreeSet<int[]> ts = new TreeSet<>((a, b)->a[0]-b[0]);
    int[] head = new int[]{-10, -10}, tail = new int[]{10010, 10010};
    public SummaryRanges() {
        ts.add(head);
        ts.add(tail);
    }
    public void addNum(int val) {
        int[] cur = new int[]{val, val};
        int[] prev = ts.floor(cur);
        int[] next = ts.ceiling(cur);
        if ((prev[0] <= val && val <= prev[1]) || (next[0] <= val && val <= next[1])) {
            // pass
        } else if (prev[1] + 1 == val && next[0] - 1 == val) {
            prev[1] = next[1];
            ts.remove(next);
        } else if (prev[1] + 1 == val) {
            prev[1] = val;
        } else if (next[0] - 1 == val) {
            next[0] = val;
        } else {
            ts.add(cur);
        }
    }
    public int[][] getIntervals() {
        // using ceiling
        // int n = ts.size();
        // int[][] ans = new int[n - 2][2];
        // int[] cur = head;
        // for (int i = 0; i < n - 2; i++) {
        //     ans[i] = ts.ceiling(new int[]{cur[0] + 1, cur[1] + 1});
        //     cur = ans[i];
        // }
        // return ans;

        // using iterator
        int n = ts.size();
        int[][] ans = new int[n - 2][2];
        Iterator<int[]> iterator = ts.iterator();
        iterator.next();
        for (int i = 0; i < n - 2; i++) ans[i] = iterator.next();
        return ans;
    }
}

class Solution354 {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a,b) -> {
            return a[0]==b[0] ? b[1] - a[1] : a[0] - b[0];
        });
        int n = envelopes.length;
        int[] dp = new int[n];
        int ans = 0;
        for(int[] envelope : envelopes) {
            int i=0, j = ans;
            while (i<j) {
                int mid = (i+j) / 2;
                if(dp[mid]<envelope[1])
                    i = mid + 1;
                else
                    j = mid;
            }
            dp[j] = envelope[1];
            if(ans==j)
                ans++;
        }
        return ans;
    }
}