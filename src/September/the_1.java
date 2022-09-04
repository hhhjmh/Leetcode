package September;

import java.util.*;

public class the_1 {
    public static void main(String[] args) {
//        new Solution150().evalRPN(new String[]{"4","3","-"});

//        new Solution191().hammingWeight(-3);

//        new Solution258().addDigits(1);

//        new Solution299().getHint("1807", "7810");

//        new Solution476().findComplement(5);

        new Solution456().find132pattern(new int[]{2,4,3,1});
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution1475 {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int [] ans = new int[n];
        for(int i=0; i<n; i++) {
            ans[i] = prices[i];
            for(int j=i+1; j<n; j++) {
                if(prices[j]<=ans[i]) {
                    ans[i]-=prices[j];
                    break;
                }
            }
        }
        return ans;
    }
}

class Solution136 {
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if(entry.getValue()==1)
                return entry.getKey();
        }
        return 0;
    }
}

class Solution136_1 {
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for(int i=1; i<nums.length; i++)
            ans ^= nums[i];
        return ans;
    }
}

class Solution150 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<tokens.length; i++) {
            String temp = tokens[i];
             if(temp.equals("+") || temp.equals("-") || temp.equals("*") || temp.equals("/"))  {
                int y = stack.pop();
                int x = stack.pop();
                if(temp.equals("+")) {
                    stack.push(x+y);
                } else if(temp.equals("-")) {
                    stack.push(x-y);
                } else if(temp.equals("*")) {
                    stack.push(x*y);
                } else if(temp.equals("/")) {
                    stack.push(x/y);
                }
            } else {
                int j=0;
                boolean flag = false;
                if(temp.charAt(0)=='-') {
                    flag = true;
                    j++;
                }
                int num = 0;
                for(; j<temp.length(); j++) {
                    num = num * 10 + temp.charAt(j)-'0';
                }
                num = flag ? -num : num;
                stack.push(num);
            }
        }
        return stack.peek();
    }
}

class Solution150_1 {
    public int evalRPN(String[] tokens) {
        int n = tokens.length;
        int[] stack = new int[(n + 1) / 2];
        int index = -1;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            switch (token) {
                case "+":
                    index--;
                    stack[index] += stack[index + 1];
                    break;
                case "-":
                    index--;
                    stack[index] -= stack[index + 1];
                    break;
                case "*":
                    index--;
                    stack[index] *= stack[index + 1];
                    break;
                case "/":
                    index--;
                    stack[index] /= stack[index + 1];
                    break;
                default:
                    index++;
                    stack[index] = Integer.parseInt(token);
            }
        }
        return stack[index];
    }
}

class Solution160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode hA = headA, hB = headB;
        while (hA!=null && hB!=null) {
            hA = hA.next;
            hB = hB.next;
        }
        int n1 = 0, n2 = 0;
        while (hA!=null) {
            hA = hA.next;
            n1++;
        }
        while (hB!=null) {
            hB = hB.next;
            n2++;
        }
        hA = headA;
        hB = headB;
        while (n1>0) {
            hA = hA.next;
            n1--;
        }
        while (n2>0) {
            hB = hB.next;
            n2--;
        }
        while (hA!=null) {
            if(hA==hB)
                return hA;
            hA = hA.next;
            hB = hB.next;
        }
        return null;
    }
}

class Solution160_1 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}


class Solution171 {
    public int titleToNumber(String columnTitle) {
        int ans = 0;
        for(int i=0; i<columnTitle.length(); i++) {
            ans = ans * 26 + columnTitle.charAt(i)-'A' + 1;
        }
        return ans;
    }
}

class Solution191 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int ans = 0;
        while (n!=0) {
            ans += n&1;
            n >>>= 1;
        }
        return ans;
    }
}

class Solution206 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = new ListNode(-1);
        while (head!=null) {
            ListNode cur = head;
            head = head.next;
            cur.next = pre.next;
            pre.next = cur;
        }
        return pre.next;
    }
}

class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        if(root==null)
            return root;

        DFS(root);

        return root;
    }

    private TreeNode DFS(TreeNode root) {
        if(root==null)
            return null;
        TreeNode left = DFS(root.left);
        TreeNode right = DFS(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}

// 232
class MyQueue {

    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    public MyQueue() {

    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        if(stack2.isEmpty()) {
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        }
        return stack2.pop();
    }

    public int peek() {
        if(stack2.isEmpty()) {
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}

class Solution235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> list1 = new ArrayList<>();
        List<TreeNode> list2 = new ArrayList<>();
        DFS(root, p, list1);
        DFS(root, q, list2);
        TreeNode ans = root;
        for(int i=0, j=0; i<list1.size() && j<list2.size(); i++,j++) {
            if(list1.get(i)==list2.get(j)) {
                ans = list1.get(i);
            } else
                break;
        }
        return ans;
    }

    private void DFS(TreeNode root, TreeNode p, List<TreeNode> list1) {
        list1.add(root);
        if(root.val==p.val)
            return;
        else if(root.val<p.val)
            DFS(root.right, p, list1);
        else
            DFS(root.left, p, list1);
    }
}

class Solution235_1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                ancestor = ancestor.right;
            } else {
                break;
            }
        }
        return ancestor;
    }
}

class Solution237 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

class Solution258 {
    public int addDigits(int num) {
        int ans = 0;
        while (num>=10) {
            ans = 0;
            while (num>0) {
                ans += num % 10;
                num /= 10;
            }
            num = ans;
        }
        return num;
    }
}

class Solution258_1 {
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}

class Solution292 {
    public boolean canWinNim(int n) {
        return n%4 != 0;
    }
}

class Solution299 {
    public String getHint(String secret, String guess) {
        int[] num1 = new int[10];
        int[] num2 = new int[10];
        int cows = 0, bulls = 0;
        for(int i=0; i<secret.length(); i++) {
            int n1 = secret.charAt(i)-'0';
            int n2 = guess.charAt(i)-'0';
            if(n1==n2)
                bulls++;
            else {
                num1[n1]++;
                num2[n2]++;
            }
        }
        for(int i=0; i<10; i++)
            cows += Math.min(num1[i], num2[i]);
        StringBuilder sb = new StringBuilder();
        sb.append(bulls).append("A").append(cows).append("B");
        return sb.toString();
    }
}

// 303
class NumArray {

    int[] numsNew;
    public NumArray(int[] nums) {
        numsNew = nums;
        for(int i=1; i<numsNew.length; i++)
            numsNew[i] += numsNew[i-1];
    }

    public int sumRange(int left, int right) {
        return left==0 ? numsNew[right] : numsNew[right]-numsNew[left-1];
    }
}

class Solution331 {
    public boolean isValidSerialization(String preorder) {
        int n = 1;
        String[] str = preorder.split(",");
        for(int i=0; i<str.length; i++) {
            if(str[i].equals("#"))
                n--;
            else
                n++;
            if(n==0 && i!=str.length-1)
                return false;
        }
        return n==0;
    }
}

/* public interface NestedInteger {

             // @return true if this NestedInteger holds a single integer, rather than a nested list.
             public boolean isInteger();

             // @return the single integer that this NestedInteger holds, if it holds a single integer
             // Return null if this NestedInteger holds a nested list
             public Integer getInteger();

             // @return the nested list that this NestedInteger holds, if it holds a nested list
             // Return empty list if this NestedInteger holds a single integer
             public List<NestedInteger> getList();
 }*/

// 341
/*class NestedIterator implements Iterator<Integer> {

    List<Integer> ans = new ArrayList<>();
    int len, n=0;
    public NestedIterator(List<NestedInteger> nestedList) {
        for(int i=0; i<nestedList.size(); i++) {
            DFS(nestedList.get(i));
        }
        len = ans.size();
    }

    private void DFS(NestedInteger nestedInteger) {
        if(nestedInteger.isInteger()) {
            ans.add(nestedInteger.getInteger());
        } else {
            for(int i=0; i<nestedInteger.getList().size(); i++)
                DFS(nestedInteger.getList().get(i));
        }
    }

    @Override
    public Integer next() {
        n++;
        return ans.get(n-1);
    }

    @Override
    public boolean hasNext() {
        return n<len-1;
    }
}*/

class Solution344 {
    public void reverseString(char[] s) {
        int mid = s.length/2, n = s.length-1;
        for(int i=0; i<mid; i++) {
            swap(s, i, n-i);
        }
    }

    private void swap(char[] s, int i, int n) {
        char temp = s[i];
        s[i] = s[n];
        s[n] = temp;
    }
}

class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> ans = new ArrayList<>();
        boolean[] l1 = new boolean[1001];
        boolean[] l2 = new boolean[1001];
        for(int i=0; i<nums1.length; i++)
            l1[nums1[i]] = true;
        for(int i=0; i<nums2.length; i++)
            l2[nums2[i]] = true;
        for(int i=0; i<l1.length; i++)
            if(l1[i] && l2[i])
                ans.add(i);

        int [] arrs = new int[ans.size()];
        for(int i=0; i<ans.size(); i++)
            arrs[i] = ans.get(i);
        return arrs;
    }
}

class Solution363 {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        return 0;
    }
}

class Solution389 {
    public char findTheDifference(String s, String t) {
        int[] s1 = new int[26];
        int[] s2 = new int[26];
        for(int i=0; i<s.length(); i++)
            s1[s.charAt(i)-'a']++;
        for(int i=0; i<t.length(); i++)
            s2[t.charAt(i)-'a']++;
        for(int i=0; i<26; i++)
            if(s2[i]>s1[i])
                return (char) (i+'a');
        return 'a';
    }
}

class Solution389_1 {
    public char findTheDifference(String s, String t) {
        int ret = 0;
        for (int i = 0; i < s.length(); ++i) {
            ret ^= s.charAt(i);
        }
        for (int i = 0; i < t.length(); ++i) {
            ret ^= t.charAt(i);
        }
        return (char) ret;
    }
}

class Solution456 {
    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int k = Integer.MIN_VALUE;
        for(int i=n-1; i>=0; i--) {
            if(nums[i]<k)
                return true;
            while (!stack.isEmpty() && stack.peek()<nums[i]) {
                k = Math.max(k, stack.pop());
            }
            stack.push(nums[i]);
        }
        return false;
    }
}

class Solution461 {
    public int hammingDistance(int x, int y) {
        x ^= y;
        int ans = 0;
        while (x>0) {
            ans += x&1;
            x >>= 1;
        }
        return ans;
    }
}

class Solution476 {
    public int findComplement(int num) {
        boolean flag = false;
        int ans = 0;
        for(int i=31; i>=0; i--) {
            if(((1<<i)&num) == (1<<i))
                flag = true;
            if(flag==true) {
                if(((1<<i)&num) == 0 )
                    ans += 1<<i;
            }
        }
        return ans;
    }
}