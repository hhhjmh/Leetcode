package August;

import java.util.*;

public class the_7 {
    public static void main(String[] args) {
        Solution301 solution301 = new Solution301();
//        solution301.removeInvalidParentheses("()())()");

        Solution1001 solution1001 = new Solution1001();
//        System.out.println(solution1001.isValid( "abccba"));

        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(5);
        ListNode l21 = new ListNode(1);
        ListNode l22 = new ListNode(3);
        ListNode l23 = new ListNode(4);
        ListNode l31 = new ListNode(2);
        ListNode l32 = new ListNode(6);
        l11.next=l12;l12.next=l13;l21.next=l22;l22.next=l23;l31.next=l32;
        ListNode[] lists = {l11, l21, l31};
        Solution21 solution21 = new Solution21();
//        solution21.mergeTwoLists(l11, l21);

        Solution23 solution23 = new Solution23();
//        solution23.mergeKLists(lists);

        Solution88 solution88 = new Solution88();
        solution88.merge(new int[]{1,2,3,0,0,0},3,new int[]{2,5,6}, 3);

    }
}

class Solution636 {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<int[]> stack = new Stack<>();
        for(String log : logs) {
            String[] temp = log.split(":");
            int id = Integer.parseInt(temp[0]);
            int time = Integer.parseInt(temp[2]);
            if(temp[1].equals("start"))
                stack.push(new int[]{id, time});
            else {
                int[] pop = stack.pop();
                int interval = time - pop[1] + 1;
                res[id] += interval;
                if(!stack.isEmpty()) res[stack.peek()[0]] -= interval;
            }
        }
        return res;
    }
}

class Solution301 {
    Set<String> set = new HashSet<>();
    int n, max, len;
    String s;
    public List<String> removeInvalidParentheses(String _s) {
        s = _s;
        n = s.length();
        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') l++;
            else if (c == ')') r++;
        }
        max = Math.min(l, r);
        dfs(0, "", 0);
        return new ArrayList<>(set);
    }
    void dfs(int u, String cur, int score) {
        if (score < 0 || score > max) return ;
        if (u == n) {
            if (score == 0 && cur.length() >= len) {
                if (cur.length() > len) set.clear();
                len = cur.length();
                set.add(cur);
            }
            return ;
        }
        char c = s.charAt(u);
        if (c == '(') {
            dfs(u + 1, cur + String.valueOf(c), score + 1);
            dfs(u + 1, cur, score);
        } else if (c == ')') {
            dfs(u + 1, cur + String.valueOf(c), score - 1);
            dfs(u + 1, cur, score);
        } else {
            dfs(u + 1, cur + String.valueOf(c), score);
        }
    }
}

class Solution301_1 {
    Set<String> set = new HashSet<>();
    int n, max, len;
    String s;
    public List<String> removeInvalidParentheses(String _s) {
        s = _s;
        n = s.length();

        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                l++;
            } else if (c == ')') {
                if (l != 0) l--;
                else r++;
            }
        }
        len = n - l - r;

        int c1 = 0, c2 = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') c1++;
            else if (c == ')') c2++;
        }
        max = Math.min(c1, c2);

        dfs(0, "", l, r, 0);
        return new ArrayList<>(set);
    }
    void dfs(int u, String cur, int l, int r, int score) {
        if (l < 0 || r < 0 || score < 0 || score > max) return ;
        if (l == 0 && r == 0) {
            if (cur.length() == len) set.add(cur);
        }
        if (u == n) return ;
        char c = s.charAt(u);
        if (c == '(') {
            dfs(u + 1, cur + String.valueOf(c), l, r, score + 1);
            dfs(u + 1, cur, l - 1, r, score);
        } else if (c == ')') {
            dfs(u + 1, cur + String.valueOf(c), l, r, score - 1);
            dfs(u + 1, cur, l, r - 1, score);
        } else {
            dfs(u + 1, cur + String.valueOf(c), l, r, score);
        }
    }
}

class Solution1001 {
    public boolean isValid(String s) {
        int n = s.length();
        if(n%3!=0)
            return false;

        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i)=='a') {
                stack.push('a');
            } if(s.charAt(i)=='b') {
                if(stack.isEmpty() || stack.peek()!='a')
                    return false;
                stack.pop();
                stack.push('b');
            } if(s.charAt(i)=='c') {
                if(stack.isEmpty() || stack.peek()!='b')
                    return false;
                stack.pop();
            }
        }
        if(stack.isEmpty())
            return true;
        return false;
    }
}

class Solution1001_1 {
    public boolean isValid(String s) {
        char[] cs = new char[s.length() + 2];
        int i = 2;
        for (char c : s.toCharArray()) {
            if (c == 'a') cs[i++] = c;
            else if (c == 'b') {
                if (cs[i - 1] != 'a') return false;
                cs[i++] = c;
            } else {
                if (cs[i - 1] != 'b' || cs[i - 2] != 'a') return false;
                i -= 2;
            }
        }
        return i == 2;
    }
}

class Solution21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (list1!=null && list2!=null) {
            ListNode temp;
            if(list1.val<list2.val) {
                temp = list1;
                list1 = list1.next;
            } else {
                temp = list2;
                list2 = list2.next;
            }
            temp.next = null;
            cur.next = temp;
            cur = cur.next;
        }
        cur.next = list1==null ? list2 : list1;
        return head.next;
    }
}

class Solution23 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(-1), cur = head;
        for(int i=0; i<lists.length; i++) {
            cur.next = mergeTwoLists(cur.next, lists[i]);
        }
        return head.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1), cur = head;
        while (l1!=null && l2!=null) {
            if(l1.val<l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return head.next;
    }
}

class Solution23_1 {
    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }

    PriorityQueue<Status> queue = new PriorityQueue<Status>();

    public ListNode mergeKLists(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }
}

class Solution23_2 {
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }
}

class Solution88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] arr = new int[m+n];
        int i=0, j=0;
        while(i<m && j<n) {
            if( nums1[i]<nums2[j]) {
                arr[i+j] = nums1[i];
                i++;
            } else{
                arr[i+j] = nums2[j];
                j++;
            }
        }
        while (i<m) {
            arr[i+j] = nums1[i];
            i++;
        } while (j<n) {
            arr[i+j] = nums2[j];
            j++;
        }
        for(int k=0; k<m+n; k++) {
            nums1[k] = arr[k];
        }
    }
}

class Solution148 {
    public ListNode sortList(ListNode head) {
        if(head==null)
            return head;
        ListNode temp = head;
        int n = 0;
        while (temp!=null) {
            n++;
            temp = temp.next;
        }
        int[] arr = new int[n];
        int index = 0;
        temp = head;
        while (temp!=null) {
            arr[index] = temp.val;
            index++;
            temp = temp.next;
        }
        Arrays.sort(arr);
        ListNode pre = new ListNode(-1), cur = pre;

        for(int i=0; i<n; i++) {
            ListNode ptr = new ListNode(arr[i]);
            cur.next = ptr;
            cur = cur.next;
        }
        return pre.next;
    }
}

class Solution148_1 {
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode dummyHead = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            ListNode prev = dummyHead, curr = dummyHead.next;
            while (curr != null) {
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode head2 = curr.next;
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    curr.next = null;
                }
                ListNode merged = merge(head1, head2);
                prev.next = merged;
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }
}

