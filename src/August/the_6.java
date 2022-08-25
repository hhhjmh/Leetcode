package August;

import java.util.*;

public class the_6 {
    public static void main(String[] args) {
        String s = "abcabcmn", s2 = "abcabeabcabcmn";
        Solution1408 solution1408 = new Solution1408();
//        solution1408.KMP("leetcoder", "od");

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next=l2;l2.next=l3;l3.next=l4;l4.next=l5;
        Solution19 solution19 = new Solution19();
//        solution19.removeNthFromEnd(l1, 2);

        Solution20 solution20 = new Solution20();
//        System.out.println(solution20.isValid("[ [ [ ] ] ]"));

        Solution32 solution32 = new Solution32();
        System.out.println(solution32.longestValidParentheses("()(())"));

    }
}



class Solution1408 {
    public List<String> stringMatching(String[] words) {
        Set<String> res = new HashSet<>();
        for(int i=0; i<words.length; i++) {
            for(int j=0; j<words.length; j++) {
                if(i!=j && KMP(words[j], words[i]))
                    res.add(words[i]);
            }
        }
        List<String> ans = new ArrayList<>(res);

        return  ans;
    }

    public boolean KMP(String s1, String s2) {
        int[] next = getNext(s2);
        int i=0, j=0, n1=s1.length(), n2=s2.length();
        while (i<n1 && j<n2) {
            if(j==-1 || s1.charAt(i)==s2.charAt(j)) {
                i++;j++;
            } else {
                j = next[j];
            }
        }
        if(j>=n2)
            return true;
        else
            return false;
    }

    public int[] getNext(String s) {
        int n = s.length();
        int[] next = new int[n];
        int j=0,k=-1;
        next[0]=-1;
        while (j<n-1) {
            if(k==-1 || s.charAt(k)==s.charAt(j)) {
                k++;j++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}

class Solution1408_1 {
    public List<String> stringMatching(String[] ss) {
        List<String> ans = new ArrayList<>();
        int n = ss.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (ss[j].indexOf(ss[i]) >= 0) {
                    ans.add(ss[i]);
                    break;
                }
            }
        }
        return ans;
    }

}


class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next==null)
            return null;
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode fast = node, slow = node;
        while (n-->0) {
            fast = fast.next;
        }
        while (fast.next!=null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return node.next;
    }
}

class Solution20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i)=='('||s.charAt(i)=='[' || s.charAt(i)=='{')
                stack.push(s.charAt(i));
            if(s.charAt(i)==')') {
                if(stack.isEmpty())
                    return false;
                if(stack.peek().equals('('))
                    stack.pop();
                else
                    return false;
            }
            if(s.charAt(i)==']') {
                if(stack.isEmpty())
                    return false;
                if(stack.peek().equals('['))
                    stack.pop();
                else
                    return false;
            }if(s.charAt(i)=='}') {
                if(stack.isEmpty())
                    return false;
                if(stack.peek().equals('{'))
                    stack.pop();
                else
                    return false;
            }
        }
        if(stack.isEmpty())
            return true;
        else
            return false;
    }
}

class Solution32 {
    public int longestValidParentheses(String s) {
        int res = 0;
        int[] dp = new int[s.length()];
        for(int i=1; i<s.length(); i++) {
            if(s.charAt(i)==')') {
                if(s.charAt(i-1)=='(') {
                    dp[i] = i<2 ? 2 :dp[i-2] + 2;
                } else if(i-dp[i-1]>0 && s.charAt(i-dp[i-1]-1)=='(') {
                    dp[i] = dp[i-1] + 2 + ((i-dp[i-1]) >= 2 ? dp[i-dp[i-1]-2] : 0);
                }
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
}

class Solution32_1 {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}

