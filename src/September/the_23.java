package September;

import java.util.*;

public class the_23 {
    public static void main(String[] args) {
/*        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(9);
        System.out.println(myLinkedList.get(1));
//        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 1);
        myLinkedList.addAtIndex(1, 7);
        myLinkedList.deleteAtIndex(1);
        myLinkedList.addAtHead(7);
        myLinkedList.addAtHead(4);
        myLinkedList.deleteAtIndex(1);
        myLinkedList.addAtIndex(1, 4);
        myLinkedList.addAtHead(2);
        myLinkedList.deleteAtIndex(5);

        System.out.println(myLinkedList.get(1));
        myLinkedList.deleteAtIndex(1);
        System.out.println(myLinkedList.get(1));*/

        new Solution436().findRightInterval(new int[][] {
                {3,4}, {2,3}, {1,2}
        });
    }
}

class MyLinkedList {

    private DNode head;
    private DNode tail;
    private int len = 0;

    public MyLinkedList() {
        head = new DNode(-1);
        tail = new DNode(-1);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int index) {
        DNode temp = head.next;
        while (index>0 && temp!=tail) {
            temp = temp.next;
            index--;
        }
        return temp.val;
    }

    public void addAtHead(int val) {
        DNode temp = new DNode(val);
        temp.next = head.next;
        head.next.pre = temp;
        head.next = temp;
        temp.pre = head;
        len++;
    }

    public void addAtTail(int val) {
        DNode temp = new DNode(val);
        temp.pre = tail.pre;
        tail.pre.next = temp;
        tail.pre =temp;
        temp.next = tail;
        len++;
    }

    public void addAtIndex(int index, int val) {
        if(index<=0)
            addAtHead(val);
        else if(index==len)
            addAtTail(val);
        else if(index>len)
            return;
        else {
            DNode temp = head;
            DNode cur = new DNode(val);
            while (index>0) {
                temp = temp.next;
                index--;
            }
            cur.next = temp.next;
            temp.next.pre = cur;
            cur.pre = temp;
            temp.next = cur;
            len++;
        }

    }

    public void deleteAtIndex(int index) {
        if(index<0 || index>=len) {
            return;
        } else {
            DNode cur = head.next;
            while (index>0) {
                cur = cur.next;
                index--;
            }
            cur.pre.next = cur.next;
            cur.next.pre = cur.pre;
            len--;
        }
    }

    class DNode {
        int val;
        DNode pre;
        DNode next;
        public DNode() {};
        public DNode(int val) {this.val = val;};
    }


}

// 432
class AllOne {
    class Node {
        int cnt;
        Set<String> set = new HashSet<>();
        Node left, right;
        Node(int _cnt) {
            cnt = _cnt;
        }
    }

    Node hh, tt;
    Map<String, Node> map = new HashMap<>();
    public AllOne() {
        hh = new Node(-1000); tt = new Node(-1000);
        hh.right = tt; tt.left = hh;
    }

    void clear(Node node) {
        if (node.set.size() == 0) {
            node.left.right = node.right;
            node.right.left = node.left;
        }
    }

    public void inc(String key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.set.remove(key);
            int cnt = node.cnt;
            Node next = null;
            if (node.right.cnt == cnt + 1) {
                next = node.right;
            } else {
                next = new Node(cnt + 1);
                next.right = node.right;
                next.left = node;
                node.right.left = next;
                node.right = next;
            }
            next.set.add(key);
            map.put(key, next);
            clear(node);
        } else {
            Node node = null;
            if (hh.right.cnt == 1) {
                node = hh.right;
            } else {
                node = new Node(1);
                node.right = hh.right;
                node.left = hh;
                hh.right.left = node;
                hh.right = node;
            }
            node.set.add(key);
            map.put(key, node);
        }
    }

    public void dec(String key) {
        Node node = map.get(key);
        node.set.remove(key);
        int cnt = node.cnt;
        if (cnt == 1) {
            map.remove(key);
        } else {
            Node prev = null;
            if (node.left.cnt == cnt - 1) {
                prev = node.left;
            } else {
                prev = new Node(cnt - 1);
                prev.right = node;
                prev.left = node.left;
                node.left.right = prev;
                node.left = prev;
            }
            prev.set.add(key);
            map.put(key, prev);
        }
        clear(node);
    }

    public String getMaxKey() {
        Node node = tt.left;
        for (String str : node.set) return str;
        return "";
    }

    public String getMinKey() {
        Node node = hh.right;
        for (String str : node.set) return str;
        return "";
    }
}

class Solution435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b)-> {
            return a[1]-b[1];
        });

        int ans = 1, right = intervals[0][1];

        for(int i=1; i<intervals.length; i++) {
            if(intervals[i][0]>=right) {
                right = intervals[i][1];
                ans++;
            }
        }
        return intervals.length - ans;
    }
}

class Solution436 {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int [][] clone = new int[n][2];
        for(int i=0; i<n; i++)
            clone[i] = new int[]{intervals[i][0], i};

        int[] ans = new int[n];
        Arrays.sort(clone, (a, b) -> {return a[0]-b[0];});
        for(int i=0; i<n; i++) {
            int num = intervals[i][1];
            int left = 0, right = n-1;
            while (left<right) {
                int mid = (left+right)/2;
                if(clone[mid][0]>=num)
                    right = mid;
                else
                    left = mid + 1;
            }
            ans[i] = clone[right][0]>=num ? clone[right][1] : -1;
        }
        return ans;
    }
}

class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {
        int m = s.length(), n = p.length();
        List<Integer> list = new ArrayList<>();
        int[] arr1 = new int[26];
        int[] arr2 = new int[26];
        char[] cs = s.toCharArray();
        for(char ch : p.toCharArray())
            arr1[ch-'a']++;
        if(m<n)
            return list;
        for(int i=0; i<n; i++) {
            arr2[cs[i]-'a']++;
        }
        for(int i=0; i<=m-n; i++) {
            if(i>0) {
                arr2[cs[i-1]-'a']--;
                arr2[cs[i+n-1]-'a']++;
            }
            if(same(arr1, arr2)) {
                list.add(i);
            }
        }
        return list;
    }

    private boolean same(int[] arr1, int[] arr2) {
        for(int i=0; i<arr1.length; i++) {
            if(arr1[i]!=arr2[i])
                return false;
        }
        return true;
    }
}

class Solution440 {
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0) {
            int steps = getSteps(curr, n);
            if (steps <= k) {
                k -= steps;
                curr++;
            } else {
                curr = curr * 10;
                k--;
            }
        }
        return curr;
    }

    public int getSteps(int curr, long n) {
        int steps = 0;
        long first = curr;
        long last = curr;
        while (first <= n) {
            steps += Math.min(last, n) - first + 1;
            first = first * 10;
            last = last * 10 + 9;
        }
        return steps;
    }
}


class Solution442 {
    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        List<Integer> ans = new ArrayList<>();
        for(int num : nums) {
            if(set.contains(num))
                ans.add(num);
            else
                set.add(num);
        }
        return ans;
    }
}

class Solution442_1 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for(int num : nums) {
            if(nums[Math.abs(num)-1]<0) {
                ans.add(Math.abs(num));
            } else {
                nums[Math.abs(num)-1] *= -1;
            }
        }
        return ans;
    }
}

class Solution446 {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        // 每个 f[i] 均为哈希表，哈希表键值对为 {d : cnt}
        // d : 子序列差值
        // cnt : 以 nums[i] 为结尾，且差值为 d 的子序列数量
        List<Map<Long, Integer>> f = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Map<Long, Integer> cur = new HashMap<>();
            for (int j = 0; j < i; j++) {
                Long d = nums[i] * 1L - nums[j];
                Map<Long, Integer> prev = f.get(j);
                int cnt = cur.getOrDefault(d, 0);
                cnt += prev.getOrDefault(d, 0);
                cnt ++;
                cur.put(d, cnt);
            }
            f.add(cur);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Map<Long, Integer> cur = f.get(i);
            for (Long key : cur.keySet()) ans += cur.get(key);
        }
        int a1 = 0, an = n - 1;
        int cnt = (a1 + an) * n / 2;
        return ans - cnt;
    }
}

