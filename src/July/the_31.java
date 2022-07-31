package July;

import java.util.ArrayDeque;
import java.util.Queue;

public class the_31 {
    public static void main(String[] args) {
        TreeNode q1 = new TreeNode(1);
        TreeNode q2 = new TreeNode(7);
        TreeNode q3 = new TreeNode(0);
        TreeNode q4 = new TreeNode(7);
        TreeNode q5 = new TreeNode(-8);
        q1.left = q2;q1.right = q3;q2.left=q4; q2.right = q5;

        Solution1161 solution1161 = new Solution1161();
//        System.out.println(solution1161.maxLevelSum(q1));

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(1);
        l1.next = l2;
//        l2.next=l3;l3.next=l4;
        Solution234 solution234 = new Solution234();
//        System.out.println(solution234.isPalindrome(l1));

        Solution234_2 solution234_2 = new Solution234_2();
//        System.out.println(solution234_2.isPalindrome(l1));

        Solution10_1 solution10_1 = new Solution10_1();
        System.out.println(solution10_1.isMatch("aa", "a*"));
    }
}

class Solution1161 {
    public int maxLevelSum(TreeNode root) {
        int res = 0, maxValue = Integer.MIN_VALUE, count=0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            int level = 0;
            count++;
            for(int i=0; i<n; i++) {
                TreeNode temp = queue.poll();
                level += temp.val;
                if(temp.left!=null) queue.offer(temp.left);
                if(temp.right!=null) queue.offer(temp.right);
            }
            if(maxValue<level) {
                maxValue = level;
                res = count;
            }
        }
        return res;
    }
}

class Solution234 {
    public boolean isPalindrome(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head!=null) {
            sb.append(head.val);
            head = head.next;
        }
        return sb.toString().equals(sb.reverse().toString());
    }
}

// 官方题解
class Solution234_2 {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head, cur = null;
        // 快慢指针找到mid
        while (fast!=null) {
            fast = fast.next==null? fast.next:fast.next.next;
            slow = slow.next;
        }
        // 反转后半链表
        while (slow!=null) {
            ListNode temp = slow.next;
            slow.next = cur;
            cur = slow;
            slow = temp;
        }
        // 快速匹配
        while (cur!=null) {
            if(head.val!=cur.val)
                return false;
            cur = cur.next;
            head = head.next;
        }
        return true;
    }
}

// 官方题解
class Solution10_1 {
    public boolean isMatch(String s, String p) {

        int m = s.length(), n = p.length();
        boolean [][] arr = new boolean[m+1][n+1];
        arr[0][0] = true;

        for(int i=0; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(p.charAt(j-1)!='*') {
                    if(matches(s,p,i,j))
                        arr[i][j] = arr[i-1][j-1];
                } else {
                    arr[i][j] = arr[i][j-2];
                    if(matches(s,p,i,j-1))
                        arr[i][j] = arr[i-1][j] || arr[i][j];
                }
            }
        }
        return arr[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if(i==0)
            return false;
        if(p.charAt(j-1)=='.')
            return true;
        return s.charAt(i-1)==p.charAt(j-1);
    }
}