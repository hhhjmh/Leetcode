package August;

import java.util.*;

public class the_8 {
    public static void main(String[] args) {
        Solution761 solution761 = new Solution761();
//        solution761.makeLargestSpecial("11011000");

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(7);
        l1.next=l2;l2.next=l3;l3.next=l4;l4.next=l5;l5.next=l6;l6.next=l7;

        Solution25 solution25 = new Solution25();
//        solution25.reverseKGroup(l1, 3);

        Solution80 solution80 = new Solution80();
//        solution80.removeDuplicates(new int[]{1,1,1,2,2,3});

        Solution28_KMP solution28_kmp = new Solution28_KMP();
        solution28_kmp.strStr("hello", "ll");

    }
}

class Solution761 {
    public String makeLargestSpecial(String s) {
        if (s.length() == 0) return s;
        List<String> list = new ArrayList<>();
        char[] cs = s.toCharArray();
        for (int i = 0, j = 0, k = 0; i < cs.length; i++) {
            k += cs[i] == '1' ? 1 : -1;
            if (k == 0) {
                list.add("1" + makeLargestSpecial(s.substring(j + 1, i)) + "0");
                j = i + 1;
            }
        }
        Collections.sort(list, (a, b)->(b + a).compareTo(a + b));
        StringBuilder sb = new StringBuilder();
        for (String str : list) sb.append(str);
        return sb.toString();
    }
}

class Solution24 {
    public ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(-1), cur = pre;
        if(head==null || head.next==null)
            return head;
        ListNode fast = head, slow = head.next;
        while (fast!=null && slow!=null) {

            cur.next = slow;
            fast.next = slow.next;
            slow.next = fast;
            cur = fast;
            fast = fast.next;
            if(fast==null)
                break;
            slow = fast.next;

        }
        return pre.next;
    }
}

class Solution24_1 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
}

class Solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        int count = 0, tempk = k;
        ListNode temp = head;
        while (temp!=null) {
            temp = temp.next;
            count++;
        }
        if(count<k)
            return head;
        ListNode pre = new ListNode(-1), h1 = pre;
        pre.next = head;
        while (tempk>0) {
            h1 = h1.next;
            tempk--;
        }
        ListNode h2 = h1.next;
        h1.next = null;

        pre.next = reverse(head);
        head.next = reverseKGroup(h2, k);

        return pre.next;

    }


    public ListNode reverse(ListNode head) {
        ListNode dummy = new ListNode(-1);

        while (head!=null) {
            ListNode temp = head;
            head = head.next;
            temp.next = dummy.next;
            dummy.next = temp;
        }

        return dummy.next;
    }
}

class Solution26 {
    public int removeDuplicates(int[] nums) {
        int res = 0;
        for(int i=0; i<nums.length; i++) {
            if(i==0 || nums[i]!=nums[i-1]) {
                nums[res] = nums[i];
                res++;
            }
        }
        return res;
    }
}

class Solution27 {
    public int removeElement(int[] nums, int val) {
        int res = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i]!=val) {
                nums[res] = nums[i];
                res++;
            }
        }
        return res;
    }
}

class Solution80 {
    public int removeDuplicates(int[] nums) {
        int res = 0, temp = 0;
        for(int i=0; i<nums.length; i++) {
            if(i<2 || nums[i]>nums[res-2]) {
                nums[res] = nums[i];
                res++;
            }
        }
        return res;
    }
}

class Solution28 {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}

class Solution28_KMP {
    public int strStr(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();
        int[] fail = new int[n];
        fail[0] = -1;
        int i=0,j=-1;
        while (i<needle.length()-1) {
            if(j==-1 || needle.charAt(i)==needle.charAt(j)) {
                i++;j++;
                fail[i] = j;
            } else {
                j = fail[j];
            }
        }

        i=0;j=0;
        while (i<m && j<n) {
            if(j==-1 || haystack.charAt(i)==needle.charAt(j)) {
                i++;j++;
            } else
                j = fail[j];
        }

        return j>=n? i-n : -1;
    }
}

class Solution459 {
    public boolean repeatedSubstringPattern(String s) {
        String ss = s + s;
        return ss.substring(1, ss.length()-1).contains(s);
    }
}