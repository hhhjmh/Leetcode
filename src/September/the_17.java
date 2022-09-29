package September;

import java.util.*;

public class the_17 {
    public static void main(String[] args) {
//        System.out.println(new Solution367().isPerfectSquare(11));

//        new Solution357().countNumbersWithUniqueDigits(8);

//        new Solution368().largestDivisibleSubset(new int[]{1,2,4,8});

//        new Solution375().getMoneyAmount(5);

        new Solution376_1().wiggleMaxLength(new int[]{1,17,5,10,13,15,10,5,16,8});

    }
}

class Solution1624 {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] count = new int[26];
        for(char ch : s.toCharArray()) {
            count[ch-'a']++;
        }
        int ans = -1;
        for(int i=0; i<26; i++) {
            if(count[i]>1) {
                int x=0, y=s.length()-1;
                while (s.charAt(x)!=(i+'a')) x++;
                while (s.charAt(y)!=(i+'a')) y--;
                ans = Math.max(ans, y-x-1);
            }
        }
        return ans;
    }
}

//  355
class Twitter {
    private class Node {
        // 哈希表存储关注人的 Id
        Set<Integer> followee;
        // 用链表存储 tweetId
        LinkedList<Integer> tweet;

        Node() {
            followee = new HashSet<Integer>();
            tweet = new LinkedList<Integer>();
        }
    }

    // getNewsFeed 检索的推文的上限以及 tweetId 的时间戳
    private int recentMax, time;
    // tweetId 对应发送的时间
    private Map<Integer, Integer> tweetTime;
    // 每个用户存储的信息
    private Map<Integer, Node> user;

    public Twitter() {
        time = 0;
        recentMax = 10;
        tweetTime = new HashMap<Integer, Integer>();
        user = new HashMap<Integer, Node>();
    }

    // 初始化
    public void init(int userId) {
        user.put(userId, new Node());
    }

    public void postTweet(int userId, int tweetId) {
        if (!user.containsKey(userId)) {
            init(userId);
        }
        // 达到限制，剔除链表末尾元素
        if (user.get(userId).tweet.size() == recentMax) {
            user.get(userId).tweet.remove(recentMax - 1);
        }
        user.get(userId).tweet.addFirst(tweetId);
        tweetTime.put(tweetId, ++time);
    }

    public List<Integer> getNewsFeed(int userId) {
        LinkedList<Integer> ans = new LinkedList<Integer>();
        for (int it : user.getOrDefault(userId, new Node()).tweet) {
            ans.addLast(it);
        }
        for (int followeeId : user.getOrDefault(userId, new Node()).followee) {
            if (followeeId == userId) { // 可能出现自己关注自己的情况
                continue;
            }
            LinkedList<Integer> res = new LinkedList<Integer>();
            int tweetSize = user.get(followeeId).tweet.size();
            Iterator<Integer> it = user.get(followeeId).tweet.iterator();
            int i = 0;
            int j = 0;
            int curr = -1;
            // 线性归并
            if (j < tweetSize) {
                curr = it.next();
                while (i < ans.size() && j < tweetSize) {
                    if (tweetTime.get(curr) > tweetTime.get(ans.get(i))) {
                        res.addLast(curr);
                        ++j;
                        if (it.hasNext()) {
                            curr = it.next();
                        }
                    } else {
                        res.addLast(ans.get(i));
                        ++i;
                    }
                    // 已经找到这两个链表合起来后最近的 recentMax 条推文
                    if (res.size() == recentMax) {
                        break;
                    }
                }
            }
            for (; i < ans.size() && res.size() < recentMax; ++i) {
                res.addLast(ans.get(i));
            }
            if (j < tweetSize && res.size() < recentMax) {
                res.addLast(curr);
                for (; it.hasNext() && res.size() < recentMax;) {
                    res.addLast(it.next());
                }
            }
            ans = new LinkedList<Integer>(res);
        }
        return ans;
    }

    public void follow(int followerId, int followeeId) {
        if (!user.containsKey(followerId)) {
            init(followerId);
        }
        if (!user.containsKey(followeeId)) {
            init(followeeId);
        }
        user.get(followerId).followee.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        user.getOrDefault(followerId, new Node()).followee.remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */

class Solution357 {
    public int countNumbersWithUniqueDigits(int n) {
        if(n==0)
            return 1;
        int [] dp = new int[n];
        dp[0] = 10;
        for(int i=2; i<=n; i++) {
            int x1 = 9;
            for(int j=i-1; j>0; j--) {
                x1 *= (10-j);
            }
            dp[i-1] = x1 + dp[i-2];
        }
        return dp[n-1];
    }
}

class Solution365 {
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        if(jug1Capacity+jug2Capacity<targetCapacity)
            return false;
        int x = gcd(jug1Capacity, jug2Capacity);
        return targetCapacity%x == 0;
    }

    private int gcd(int a, int b) {
        return b > 0 ? gcd(b, a%b) : a;
    }
}

class Solution367 {
    public boolean isPerfectSquare(int num) {
        if(num==1)
            return true;
        int i=1, j=num;
        while (i<j) {
            int mid = i + (j-i)/2;
            if((long)mid*mid == num) {
                return true;
            } else if((long)mid*mid < num) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return false;
    }
}

class Solution368 {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length, index = 0, len = 1;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        Arrays.sort(nums);
        for(int i=1; i<n; i++) {
            for(int j=i-1; j>=0; j--) {
                if(nums[i]%nums[j]==0) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    if(len < dp[i]) {
                        len = dp[i];
                        index = i;
                    }
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        res.add(nums[index]);
        index--;
        len--;
        while (index>=0 && len>0) {
            if(res.get(res.size()-1)%nums[index]==0 && dp[index]==len) {
                res.add(nums[index]);
                len--;
            }
            index--;
        }
        Collections.reverse(res);
        return res;
    }
}

class Solution373 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<int []> priorityQueue = new PriorityQueue<>(k, (a,b)-> {
            return nums1[a[0]]+nums2[a[1]]-nums1[b[0]]-nums2[b[1]];
        });
        for(int i=0; i<m && i<k; i++) {
            priorityQueue.offer(new int[]{i, 0});
        }
        while (k-->0 && !priorityQueue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int[] temp = priorityQueue.poll();
            list.add(nums1[temp[0]]);
            list.add(nums2[temp[1]]);
            res.add(list);
            if(temp[1] + 1 < n) {
                priorityQueue.offer(new int[] {temp[0], temp[1]+1});
            }
        }
        return res;
    }
}

class Solution375 {
    public int getMoneyAmount(int n) {
        int[][] f = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                f[i][j] = j + f[i][j - 1];
                for (int k = i; k < j; k++) {
                    f[i][j] = Math.min(f[i][j], k + Math.max(f[i][k - 1], f[k + 1][j]));
                }
            }
        }
        return f[1][n];
    }
}

class Solution376 {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        boolean[] up = new boolean[n];
        int [] dp = new int[n];
        dp[0] = 1;
        int j=1;
        for (; j<n; j++) {
            if(nums[j]>nums[j-1]) {
                dp[j] = 2;
                up[j] = true;
                break;
            } else if(nums[j]<nums[j-1]) {
                dp[j] = 2;
                up[j] = false;
                break;
            } else {
                dp[j] = dp[j-1];
            }
        }
        for(int i=j+1; i<n; i++) {
            if( (nums[i]>nums[i-1] && !up[i-1]) || (nums[i]<nums[i-1] && up[i-1]) ) {
                up[i] = !up[i-1];
                dp[i] = dp[i-1] + 1;
            } else {
                up[i] = up[i-1];
                dp[i] = dp[i-1];
            }
        }
        return dp[n-1];
    }
}

class Solution376_1 {
    public int wiggleMaxLength(int[] nums) {
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                up = down + 1;
            else if (nums[i] < nums[i - 1])
                down = up + 1;
        }
        return nums.length == 0 ? 0 : Math.max(down, up);
    }
}

class Solution377 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i=0; i<=target; i++) {
            for(int num : nums) {
                if(i>=num) {
                    dp[i] += dp[i-num];
                }
            }
        }
        return dp[target];
    }
}