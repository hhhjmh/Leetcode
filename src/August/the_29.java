package August;

import java.util.*;

public class the_29 {
    public static void main(String[] args) {
//        new Solution59().generateMatrix(3);

/*        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();
        n1.val=1;n2.val=2;n3.val=3;
        n1.next=n2;n2.prev=n1;n1.child=n3;
        new Solution430().flatten(n1);*/

        new Solution115().numDistinct("rabbbit", "rabbit");
    }
}

class Solution1470 {
    public int[] shuffle(int[] nums, int n) {
        int[] arr = new int[n*2];
        for(int i=0; i<n; i++) {
            arr[i*2] = nums[i];
            arr[i*2+1] = nums[n+i];
        }
        return arr;
    }
}

class Solution61 {
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null)
            return head;
        int n = 0;
        ListNode cur = head;
        while (cur!=null) {
            cur = cur.next;
            n++;
        }
        ListNode slow = new ListNode(-1), fast = slow;
        fast.next = head;
        k = k % n;
        if(k==0)
            return head;
        while (k>0) {
            fast = fast.next;
            k--;
        }
        while (fast.next!=null) {
            fast = fast.next;
            slow = slow.next;
        }
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }
}

class Solution54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        boolean right, left = false, up = false, down = false;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] flag = new boolean[m][n];
        int count = m * n;
        right = true;
        for (int i = 0, j = 0; count > 0; count--) {
            res.add(matrix[i][j]);
            flag[i][j] = true;
            if (right) {
                if (j == n - 1 || flag[i][j + 1]) {
                    right = false;
                    down = true;
                    i++;
                } else {
                    j++;
                }
            } else if (down) {
                if (i == m - 1 || flag[i + 1][j]) {
                    down = false;
                    left = true;
                    j--;
                } else {
                    i++;
                }
            } else if (left) {
                if (j == 0 || flag[i][j - 1]) {
                    left = false;
                    up = true;
                    i--;
                } else {
                    j--;
                }
            } else if (up) {
                if (i == 0 || flag[i - 1][j]) {
                    up = false;
                    right = true;
                    j++;
                } else {
                    i--;
                }
            }
        }
        return res;
    }
}

class Solution59 {
    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];

        boolean right=true, left = false, up = false, down = false;
        boolean[][] flag = new boolean[n][n];
        int count = 0;
        for (int i = 0, j = 0; count < n*n; count++) {
            arr[i][j] = count + 1;
            flag[i][j] = true;
            if (right) {
                if (j == n - 1 || flag[i][j + 1]) {
                    right = false;
                    down = true;
                    i++;
                } else {
                    j++;
                }
            } else if (down) {
                if (i == n - 1 || flag[i + 1][j]) {
                    down = false;
                    left = true;
                    j--;
                } else {
                    i++;
                }
            } else if (left) {
                if (j == 0 || flag[i][j - 1]) {
                    left = false;
                    up = true;
                    i--;
                } else {
                    j--;
                }
            } else if (up) {
                if (i == 0 || flag[i - 1][j]) {
                    up = false;
                    right = true;
                    j++;
                } else {
                    i--;
                }
            }
        }
        return arr;
    }
}

class Solution73 {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean [] row = new boolean[m];
        boolean [] col = new boolean[n];

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(matrix[i][j]==0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(row[i]||col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

class Solution74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        for(int i=0, j=n-1; i<m && j>=0; ) {
            if(matrix[i][j]==target)
                return true;
            else if(matrix[i][j]<target)
                i++;
            else
                j--;
        }
        return false;
    }
}

class Solution82 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pre = new ListNode(-1), cur = pre;
        pre.next = head;
        int num = 1000;
        while (cur.next!=null) {
            if(cur.next.next!=null && cur.next.val==cur.next.next.val)
                num = cur.next.val;
            if(cur.next.val==num)
                cur.next = cur.next.next;
            else
                cur = cur.next;
        }
        return pre.next;
    }
}

class Solution83 {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null)
            return head;
        ListNode cur = head;
        while (cur.next!=null) {
            if(cur.val==cur.next.val)
                cur.next = cur.next.next;
            else
                cur = cur.next;
        }
        return head;
    }
}

class Solution92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode pre = new ListNode(-1), fast = pre, slow = pre;
        fast.next = head;
        while (left>1) {
            slow = slow.next;
            left--;
        }
        while (right>0) {
            fast = fast.next;
            right--;
        }
        ListNode dummy = fast.next;
        ListNode cur = slow.next;
        fast.next = null;
        slow.next = null;
        slow.next = reverse(cur);
        while (slow.next!=null) {
            slow = slow.next;
        }
        slow.next = dummy;
        return pre.next;
    }

    private ListNode reverse(ListNode head) {
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

class Solution114 {
    List<TreeNode> list = new ArrayList<>();
    public void flatten(TreeNode root) {
        if(root==null)
            return;
        DFS(root);
        for(int i=1; i<list.size(); i++) {
            root.left = null;
            root.right = list.get(i);
            root = list.get(i);
        }
    }

    private void DFS(TreeNode root) {
        if(root==null)
            return;
        list.add(root);
        DFS(root.left);
        DFS(root.right);
    }
}

class Solution115 {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] res = new int[m+1][n+1];
        for(int i=0; i<=m; i++)
            res[i][0] = 1;
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n && j<=i; j++) {
                if(s.charAt(i-1)==t.charAt(j-1))
                    res[i][j] = res[i-1][j-1]+res[i-1][j];
                else
                    res[i][j] = res[i-1][j];
            }
        }
        return res[m][n];
    }
}

// [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
class Solution118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        ans.add(new ArrayList<>(list));
        for(int i=1; i<numRows; i++) {
            list = ans.get(ans.size()-1);
            list.add(0);
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            for(int j=0; j<list.size()-1; j++) {
                temp.add(list.get(j)+list.get(j+1));
            }
            list.remove(list.size()-1);
            ans.add(new ArrayList<>(temp));
        }
        return ans;
    }
}

class Solution118_1 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; ++i) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; ++j) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            ret.add(row);
        }
        return ret;
    }
}

class Solution119 {
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> ret = new ArrayList<>();
        for(int i=0; i<=rowIndex; i++) {
            List<Integer> row = new ArrayList<>();
            for(int j=0; j<=i; j++) {
                if(j==0 || j==i)
                    row.add(1);
                else
                    row.add(ret.get(i-1).get(j-1) + ret.get(i-1).get(j));
            }
            ret.add(row);
        }
        return ret.get(rowIndex);
    }
}

class Solution119_1 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }
}