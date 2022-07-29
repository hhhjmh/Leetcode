package July;

import java.util.*;

public class the_25 {
    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
//        System.out.println(s1.multiply("103", "456"));

        Solution6 s2 = new Solution6();
//        System.out.println(s2.multiply("123", "456"));

        Solution7 s3 = new Solution7();
//        System.out.println(s3.addBinary("11", "1"));

        Solution8 s4 = new Solution8();
//        System.out.println(s4.getSum(99, 3));

        Solution9 s5 = new Solution9();
//        System.out.println(s5.addStrings("456", "77"));

/*        Solution10 s6 = new Solution10();
        ListNode l1 = new ListNode(7);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(3); l1.next = l2; l2.next = l3;l3.next = l4;
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        ListNode l7 = new ListNode(4);l5.next = l6; l6.next = l7;
        System.out.println(s6.addTwoNumbers(l1, l5).val);*/

        Solution12 s6 = new Solution12();
        int [] arr = {1,2,0,0};
        System.out.println(s6.addToArrayForm(arr, 34));
    }
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

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// 919
class CBTInserter {

    Queue<TreeNode> queue = new ArrayDeque<>();
    TreeNode root;

    public CBTInserter(TreeNode root) {
        this.root = root;
        TreeNode temp = root;
        queue.offer(temp);
        while(!queue.isEmpty()) {
            temp = queue.peek();
            if(temp.left!=null) queue.offer(temp.left);
            if(temp.right!=null) queue.offer(temp.right);
            if(temp.left!=null && temp.right!=null) queue.poll();
            if(temp.left==null || temp.right==null) break;
        }
    }

    public int insert(int val) {
        TreeNode res = queue.peek();
        TreeNode temp = new TreeNode(val);
        if(res.left==null) {
            res.left = temp;
            queue.offer(res.left);
        } else {
            res.right = temp;
            queue.poll();
            queue.offer(res.right);
        }
        return res.val;
    }

    public TreeNode get_root() {
        return root;
    }
}

// 919 官方题解
class CBTInserter1 {
    int cnt;
    TreeNode root;

    public CBTInserter1(TreeNode root) {
        this.cnt = 0;
        this.root = root;

        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            ++cnt;
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public int insert(int val) {
        ++cnt;
        TreeNode child = new TreeNode(val);
        TreeNode node = root;
        int highbit = 31 - Integer.numberOfLeadingZeros(cnt);
        for (int i = highbit - 1; i >= 1; --i) {
            if ((cnt & (1 << i)) != 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        if ((cnt & 1) != 0) {
            node.right = child;
        } else {
            node.left = child;
        }
        return node.val;
    }

    public TreeNode get_root() {
        return root;
    }
}

// 43
class Solution1 {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0"))
            return "0";
        String res = "0";
        int m = num1.length(), n = num2.length();
        for(int i=m-1; i>=0; i--) {
            StringBuilder temp = new StringBuilder();
            int a = 0;
            for(int k=i; k<m-1; k++)
                temp.append("0");
            for(int j=n-1; j>=0; j--) {
                if(num1.charAt(i)-'0'==0)
                    break;
                int k = (num1.charAt(i)-'0') * (num2.charAt(j)-'0') + a;
                a = k / 10;
                temp.append(k%10);
            }
            if(a!=0)
                temp.append(a);
            res = add(res, temp.reverse().toString());
        }
        return res;
    }

    public String add(String num1, String num2) {
        if(num1==null || num2==null)
            return num1 == null ? num2 : num1;
        int i=num1.length()-1, j=num2.length()-1, add=0;
        StringBuilder sb = new StringBuilder();
        while(i>=0 || j>=0 || add!=0) {
            int x = i>=0 ? num1.charAt(i)-'0' : 0;
            int y = j>=0 ? num2.charAt(j)-'0' : 0;
            int result = x + y + add;
            add = result / 10;
            sb.append(result%10);
            i--;j--;
        }
        return sb.reverse().toString();
    }
}

// 43 官方解法
class Solution6 {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0"))
            return "0";
        int len1 = num1.length(), len2 = num2.length();
        int[] res = new int[len1+len2];
        for(int i=len1-1; i>=0; i--) {
            int x = num1.charAt(i)-'0';
            for(int j=len2-1; j>=0; j--) {
                int y = num2.charAt(j)-'0';
                int sum = x*y + res[i+j+1];
                res[i+j+1] = sum %10;
                res[i+j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<len1+len2; i++) {
            if(i==0 && res[i]==0) continue;
            sb.append(res[i]);
        }

        return sb.toString();
    }
}

// 67
class Solution7 {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int len1 = a.length()-1, len2 = b.length()-1, add = 0;
        while(len1>=0 || len2>=0 || add!=0) {
            int x = len1 >= 0 ? a.charAt(len1)-'0' : 0;
            int y = len2 >= 0 ? b.charAt(len2)-'0' : 0;
            int sum = x + y + add;
            sb.append(sum%2);
            add = sum / 2;
            len1--;
            len2--;
        }
        return sb.reverse().toString();
    }
}

// 371
class Solution8 {
    public int getSum(int a, int b) {
        int sum = a ^ b;
        int carry = (a & b) << 1;
        if(carry!=0) {
            return getSum(sum, carry);
        }
        return sum;
    }
}

// 415
class Solution9 {
    public String addStrings(String num1, String num2) {
        int i = num1.length()-1, j = num2.length()-1, add = 0;
        StringBuilder sb = new StringBuilder();
        while(i>=0 || j>=0 || add!=0) {
            int x = i>=0 ? num1.charAt(i)-'0' : 0;
            int y = j>=0 ? num2.charAt(j)-'0' : 0;
            int sum = x + y + add;
            add = sum / 10;
            sb.append(sum%10);
            i--;j--;
        }
        return sb.reverse().toString();
    }
}

// 445
class Solution10 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        int add = 0;
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while(l1!=null || l2!=null || add!=0) {
            int x = l1==null ? 0 : l1.val;
            int y = l2==null ? 0 : l2.val;
            int sum = x + y + add;
            add = sum / 10;
            ListNode temp = new ListNode(sum % 10);
            cur.next = temp;
            cur = cur.next;
            if(l1!=null) l1 = l1.next;
            if(l2!=null) l2 = l2.next;
        }
        return reverse(head.next);
    }

    public ListNode reverse(ListNode node) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        Stack<ListNode> stack = new Stack<>();
        while(node!= null) {
            ListNode temp = new ListNode(node.val);
            stack.add(temp);
            node = node.next;
        }

        while (!stack.isEmpty()) {
            cur.next = stack.pop();
            cur = cur.next;
        }
        return head.next;
    }
}

// 445 官方题解
class Solution11 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<Integer>();
        Deque<Integer> stack2 = new ArrayDeque<Integer>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode ans = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int a = stack1.isEmpty() ? 0 : stack1.pop();
            int b = stack2.isEmpty() ? 0 : stack2.pop();
            int cur = a + b + carry;
            carry = cur / 10;
            cur %= 10;
            ListNode curnode = new ListNode(cur);
            curnode.next = ans;
            ans = curnode;
        }
        return ans;
    }
}

// 989
class Solution12 {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> res = new LinkedList<>();
        int m = num.length-1, carry = 0;
        while(m>=0 || k!=0 || carry!=0) {
            int x = m>=0 ? num[m] : 0;
            int y = k!=0 ? k%10 : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            res.add(0, sum%10);
            m--;
            k /= 10;
        }
        return res;
    }
}
