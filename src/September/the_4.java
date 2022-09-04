package September;

import java.util.*;

public class the_4 {
    public static void main(String[] args) {
/*        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.get(1);
        lruCache.put(3,3);
        lruCache.get(2);
        lruCache.put(4,4);
        lruCache.get(1);
        lruCache.get(3);
        lruCache.get(4);*/

        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(1);
        ListNode l4 = new ListNode(3);l1.next=l2;l2.next=l3;l3.next=l4;
//        new Solution147().insertionSortList(l1);

//        new Solution151().reverseWords("a good   example");

        new Solution165().compareVersion("1.01.1", "1.001");
    }
}


class Solution1582 {
    public int numSpecial(int[][] mat) {
        int ans = 0;
        for(int i=0; i<mat.length; i++) {
            for(int j=0; j<mat[0].length; j++) {
                if(mat[i][j]==1 && check(mat, i, j))
                    ans++;
            }
        }
        return ans;
    }

    private boolean check(int[][] mat, int i, int j) {
        for(int k = 0; k<mat.length; k++)
            if(k!=i && mat[k][j]==1)
                return false;

        for(int k = 0; k<mat[0].length; k++)
            if(k!=j && mat[i][k]==1)
                return false;

        return true;
    }
}

class LRUCache {
    class DLinkedNode{
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) { key = _key; value = _value;}
    }
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size = 0;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if(size==0 || !cache.containsKey(key))
            return -1;
        DLinkedNode node = cache.get(key);
        removeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            DLinkedNode node = cache.get(key);
            node.value = value;
            removeToHead(node);
        } else {
            DLinkedNode node = new DLinkedNode(key, value);
            addToHead(node);
            cache.put(key, node);
            size++;
            if(size>capacity) {
                DLinkedNode node1 = deleteTail();
                cache.remove(node1.key);
                size--;
            }
        }
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;

    }

    private void deleteNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void removeToHead(DLinkedNode node) {
        deleteNode(node);
        addToHead(node);
    }

    private DLinkedNode deleteTail() {
        DLinkedNode node = tail.prev;
        deleteNode(node);
        return node;
    }
}

class LFUCache {
    int minfreq, capacity;
    Map<Integer, Node> keyTable;
    Map<Integer, DoublyLinkedList> freqTable;

    public LFUCache(int capacity) {
        this.minfreq = 0;
        this.capacity = capacity;
        keyTable = new HashMap<Integer, Node>();
        freqTable = new HashMap<Integer, DoublyLinkedList>();
    }

    public int get(int key) {
        if (capacity == 0) {
            return -1;
        }
        if (!keyTable.containsKey(key)) {
            return -1;
        }
        Node node = keyTable.get(key);
        int val = node.val, freq = node.freq;
        freqTable.get(freq).remove(node);
        // 如果当前链表为空，我们需要在哈希表中删除，且更新minFreq
        if (freqTable.get(freq).size == 0) {
            freqTable.remove(freq);
            if (minfreq == freq) {
                minfreq += 1;
            }
        }
        // 插入到 freq + 1 中
        DoublyLinkedList list = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
        list.addFirst(new Node(key, val, freq + 1));
        freqTable.put(freq + 1, list);
        keyTable.put(key, freqTable.get(freq + 1).getHead());
        return val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        if (!keyTable.containsKey(key)) {
            // 缓存已满，需要进行删除操作
            if (keyTable.size() == capacity) {
                // 通过 minFreq 拿到 freqTable[minFreq] 链表的末尾节点
                Node node = freqTable.get(minfreq).getTail();
                keyTable.remove(node.key);
                freqTable.get(minfreq).remove(node);
                if (freqTable.get(minfreq).size == 0) {
                    freqTable.remove(minfreq);
                }
            }
            DoublyLinkedList list = freqTable.getOrDefault(1, new DoublyLinkedList());
            list.addFirst(new Node(key, value, 1));
            freqTable.put(1, list);
            keyTable.put(key, freqTable.get(1).getHead());
            minfreq = 1;
        } else {
            // 与 get 操作基本一致，除了需要更新缓存的值
            Node node = keyTable.get(key);
            int freq = node.freq;
            freqTable.get(freq).remove(node);
            if (freqTable.get(freq).size == 0) {
                freqTable.remove(freq);
                if (minfreq == freq) {
                    minfreq += 1;
                }
            }
            DoublyLinkedList list = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
            list.addFirst(new Node(key, value, freq + 1));
            freqTable.put(freq + 1, list);
            keyTable.put(key, freqTable.get(freq + 1).getHead());
        }
    }
    class Node {
        int key, val, freq;
        Node prev, next;

        Node() {
            this(-1, -1, 0);
        }

        Node(int key, int val, int freq) {
            this.key = key;
            this.val = val;
            this.freq = freq;
        }
    }

    class DoublyLinkedList {
        Node dummyHead, dummyTail;
        int size;

        DoublyLinkedList() {
            dummyHead = new Node();
            dummyTail = new Node();
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
            size = 0;
        }

        public void addFirst(Node node) {
            Node prevHead = dummyHead.next;
            node.prev = dummyHead;
            dummyHead.next = node;
            node.next = prevHead;
            prevHead.prev = node;
            size++;
        }

        public void remove(Node node) {
            Node prev = node.prev, next = node.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }

        public Node getHead() {
            return dummyHead.next;
        }

        public Node getTail() {
            return dummyTail.prev;
        }
    }
}

class Solution147 {
    public ListNode insertionSortList(ListNode head) {
        ListNode pre = new ListNode(-1), cur = pre;
        while (head!=null) {
            ListNode temp = head;
            head = head.next;
            while (cur.next!=null && cur.next.val<temp.val)
                cur = cur.next;
            temp.next = cur.next;
            cur.next = temp;
            cur = pre;
        }
        return pre.next;
    }
}

class Solution151 {
    public String reverseWords(String s) {
        String[] ss = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i= ss.length-1; i>=0; i--) {
            if(ss[i].trim().length()>0) {
                sb.append(ss[i]).append(" ");
            }
        }
        return sb.toString().trim();
    }
}

class Solution154 {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else if (nums[pivot] > nums[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return nums[low];
    }
}

class MinStack {

    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();
    int min = Integer.MAX_VALUE;

    public MinStack() {

    }

    public void push(int val) {
        s1.push(val);
        min = Math.min(val, min);
        s2.push(min);
    }

    public void pop() {
        s1.pop();
        s2.pop();
        if(s2.isEmpty())
            min = Integer.MAX_VALUE;
        else
            min = s2.peek();
    }

    public int top() {
        return s1.peek();
    }

    public int getMin() {
        return s2.peek();
    }
}

class Solution239_1 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
}

class Solution239_2 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}

class Solution162 {
    public int findPeakElement(int[] nums) {
        int ans = 0, max = nums[0];
        for(int i=1; i<nums.length; i++) {
            if(nums[i]>max) {
                max = nums[i];
                ans = i;
            }
        }
        return ans;
    }
}

class Solution162_1 {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] < nums[mid+1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}

class Solution852 {
    public int peakIndexInMountainArray(int[] arr) {
        int ans = 0, max = arr[0];
        for(int i=1; i<arr.length; i++) {
            if(arr[i]>max) {
                max = arr[i];
                ans = i;
            }
        }
        return ans;
    }
}

class Solution852_1 {
    public int peakIndexInMountainArray(int[] arr) {
        int l=0, r = arr.length-1;
        while (l<r) {
            int mid = l + ((r-l)>>1);
            if(arr[mid] < arr[mid+1])
                l = mid +1;
            else
                r = mid;
        }
        return l;
    }
}

class Solution164 {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        long exp = 1;
        int[] buf = new int[n];
        int maxVal = Arrays.stream(nums).max().getAsInt();

        while (maxVal >= exp) {
            int[] cnt = new int[10];
            for (int i = 0; i < n; i++) {
                int digit = (nums[i] / (int) exp) % 10;
                cnt[digit]++;
            }
            for (int i = 1; i < 10; i++) {
                cnt[i] += cnt[i - 1];
            }
            for (int i = n - 1; i >= 0; i--) {
                int digit = (nums[i] / (int) exp) % 10;
                buf[cnt[digit] - 1] = nums[i];
                cnt[digit]--;
            }
            System.arraycopy(buf, 0, nums, 0, n);
            exp *= 10;
        }

        int ret = 0;
        for (int i = 1; i < n; i++) {
            ret = Math.max(ret, nums[i] - nums[i - 1]);
        }
        return ret;
    }
}

class Solution165 {
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");

        for(int i=0; i<s1.length || i<s2.length; i++) {
            int n1 = i<s1.length ? Integer.parseInt(s1[i]) : 0;
            int n2 = i<s2.length ? Integer.parseInt(s2[i]) : 0;
            if(n1<n2)
                return -1;
            else if(n1>n2)
                return 1;
        }
        return 0;
    }


}

class Solution166 {
    public String fractionToDecimal(int numerator, int denominator) {
        long a = numerator, b = denominator;
        if(a%b==0) return String.valueOf(a/b);
        StringBuilder sb = new StringBuilder();
        if(a*b<0)
            sb.append('-');
        a = Math.abs(a);
        b = Math.abs(b);
        sb.append(a/b).append(".");
        a = a % b;
        Map<Long, Integer> map = new HashMap<>();
        while (a!=0) {
            map.put(a, sb.length());
            a *= 10;
            sb.append(a/b);
            a %= b;
            if(map.containsKey(a)) {
                int u = map.get(a);
                return String.format("%s(%s)", sb.substring(0, u), sb.substring(u));
            }
        }
        return sb.toString();
    }
}
