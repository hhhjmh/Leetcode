package September;

import java.util.*;

public class the_15 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);l1.next=l2;l2.next=l3;l3.next=l4;l4.next=l5;
        new Solution328().oddEvenList(l1);
    }
}

class Solution672 {
    public int flipLights(int n, int presses) {
        //不按开关
        if (presses == 0) {
            return 1;
        }
        //特殊情况处理
        if (n == 1) {
            return 2;
        } else if (n == 2) {
            //特殊情况
            return presses == 1 ? 3 : 4;
        } else {
            //n >= 3
            return presses == 1 ? 4 : presses == 2 ? 7 : 8;
        }
    }
}


class Solution318 {
    public int maxProduct(String[] words) {
        int max = 0;
        for(int i=0; i<words.length; i++) {
            for(int j=i+1; j<words.length; j++) {
                boolean flag = true;
                int x = words[i].length(), y = words[j].length();
                if(max<x*y) {
                    for(int k=0; k<words[j].length(); k++) {
                        if(words[i].indexOf(words[j].charAt(k))!=-1) {
                            flag = false;
                            break;
                        }
                    }
                    if(flag) {
                        max = Math.max(max, x*y);
                    }
                }
            }
        }
        return max;
    }
}

class Solution318_1 {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] hash = new int[n];
        for(int i=0; i<n; i++) {
            int temp = 0;
            for(char ch : words[i].toCharArray()){
                temp |= 1 << (ch-'a');
            }
            hash[i] = temp;
        }
        int max = 0;
        for(int i=0; i<n; i++) {
            for(int j=1; j<n; j++) {
                if((hash[i]&hash[j])==0) {
                    max = Math.max(words[i].length()*words[j].length(), max);
                }
            }
        }
        return max;
    }
}

class Solution319 {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}

class Solution321 {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];
        int start = Math.max(0, k - n), end = Math.min(k, m);
        for (int i = start; i <= end; i++) {
            int[] subsequence1 = maxSubsequence(nums1, i);
            int[] subsequence2 = maxSubsequence(nums2, k - i);
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }

    public int[] maxSubsequence(int[] nums, int k) {
        int length = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = length - k;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    public int[] merge(int[] subsequence1, int[] subsequence2) {
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }

    public int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }
}

class Solution322 {
    public int coinChange(int[] coins, int amount) {
        int [] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for(int i=1; i<=amount; i++) {
            for(int j=0; j<coins.length; j++) {
                if(coins[j]<=i) {
                    dp[i] = Math.min(dp[i-coins[j]] + 1, dp[i]);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}

class Solution327 {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long sum = 0;
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            preSum[i + 1] = sum;
        }

        Set<Long> allNumbers = new TreeSet<Long>();
        for (long x : preSum) {
            allNumbers.add(x);
            allNumbers.add(x - lower);
            allNumbers.add(x - upper);
        }
        // 利用哈希表进行离散化
        Map<Long, Integer> values = new HashMap<Long, Integer>();
        int idx = 0;
        for (long x: allNumbers) {
            values.put(x, idx);
            idx++;
        }

        int ret = 0;
        BIT bit = new BIT(values.size());
        for (int i = 0; i < preSum.length; i++) {
            int left = values.get(preSum[i] - upper), right = values.get(preSum[i] - lower);
            ret += bit.query(right + 1) - bit.query(left);
            bit.update(values.get(preSum[i]) + 1, 1);
        }
        return ret;
    }
    class BIT {
        int[] tree;
        int n;

        public BIT(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        public int lowbit(int x) {
            return x & (-x);
        }

        public void update(int x, int d) {
            while (x <= n) {
                tree[x] += d;
                x += lowbit(x);
            }
        }

        public int query(int x) {
            int ans = 0;
            while (x != 0) {
                ans += tree[x];
                x -= lowbit(x);
            }
            return ans;
        }
    }
}

class Solution328 {
    public ListNode oddEvenList(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }
        ListNode pre = new ListNode(-1), curA = pre, dummy = new ListNode(-2), curB = dummy;
        int count = 1;
        while (head!=null) {
            ListNode temp = head;
            head = head.next;
            if(count%2==1) {
                curA.next = temp;
                curA = curA.next;
            } else {
                curB.next = temp;
                curB = curB.next;
            }
            count++;
        }
        curB.next = null;
        curA.next = dummy.next;
        return pre.next;
    }
}

class Solution329 {
    public int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int rows, columns;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        rows = matrix.length;
        columns = matrix[0].length;
        int[][] memo = new int[rows][columns];
        int ans = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                ans = Math.max(ans, dfs(matrix, i, j, memo));
            }
        }
        return ans;
    }

    public int dfs(int[][] matrix, int row, int column, int[][] memo) {
        if (memo[row][column] != 0) {
            return memo[row][column];
        }
        ++memo[row][column];
        for (int[] dir : dirs) {
            int newRow = row + dir[0], newColumn = column + dir[1];
            if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] > matrix[row][column]) {
                memo[row][column] = Math.max(memo[row][column], dfs(matrix, newRow, newColumn, memo) + 1);
            }
        }
        return memo[row][column];
    }
}

class Solution330 {
    public int minPatches(int[] nums, int n) {
        int patches = 0, len = nums.length, index = 0;
        long x = 1;
        while (x<=n) {
            if(index<len && nums[index]<=x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                patches++;
            }
        }
        return patches;
    }
}

class Solution332 {
    Map<String, PriorityQueue<String>> map = new HashMap<>();
    List<String> res = new ArrayList<>();
    public List<String> findItinerary(List<List<String>> tickets) {
        for(List<String> ticket : tickets) {
            String s1 = ticket.get(0), s2 = ticket.get(1);
            if(!map.containsKey(s1)) {
                map.put(s1, new PriorityQueue<>());
            }
            map.get(s1).offer(s2);
        }
        DFS("JFK");
        Collections.reverse(res);
        return res;
    }

    private void DFS(String jfk) {
        while (map.containsKey(jfk) && map.get(jfk).size()>0) {
            String temp = map.get(jfk).poll();
            DFS(temp);
        }
        res.add(jfk);
    }
}



