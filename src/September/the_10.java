package September;

import java.util.*;

public class the_10 {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(0);
        TreeNode t3 = new TreeNode(2);
        t1.left = t2; t1.right = t3;
//        new Solution669().trimBST(t1, 1, 2);

//        new Solution228().summaryRanges(new int[]{0,2,3,4,6,8,9});

//        new Solution223().computeArea(-3,0,3,4, 0,-1,9,2);

//        new Solution227().calculate("3+2*2");

        new Solution224().calculate("1+2+(3-(4+5))");
    }
}

class Solution669 {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root==null)
            return root;
        if(root.val<low) {
            root = root.right;
            root = trimBST(root, low, high);
        } else if(root.val>high) {
            root = root.left;
            root = trimBST(root, low, high);
        }
        if(root!=null) {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
        }
        return root;
    }
}

class Solution221_1 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> ts = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            Long u = nums[i] * 1L;
            // 从 ts 中找到小于等于 u 的最大值（小于等于 u 的最接近 u 的数）
            Long l = ts.floor(u);
            // 从 ts 中找到大于等于 u 的最小值（大于等于 u 的最接近 u 的数）
            Long r = ts.ceiling(u);
            if(l != null && u - l <= t) return true;
            if(r != null && r - u <= t) return true;
            // 将当前数加到 ts 中，并移除下标范围不在 [max(0, i - k), i) 的数（维持滑动窗口大小为 k）
            ts.add(u);
            if (i >= k) ts.remove(nums[i - k] * 1L);
        }
        return false;
    }
}

class Solution221_2 {
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        int maxSquare = maxSide * maxSide;
        return maxSquare;
    }
}


class Solution222 {
    public int countNodes(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        if(root==null)
            return 0;
        queue.add(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int n = queue.size();
            ans += n;
            for(int i=0; i<n; i++) {
                TreeNode temp = queue.poll();
                if(temp.left!=null) queue.offer(temp.left);
                if(temp.right!=null) queue.offer(temp.right);
            }
        }
        return ans;
    }
}

class Solution223 {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        int x = (ax2-ax1) * (ay2-ay1);
        int y = (bx2-bx1) * (by2-by1);
        if( ax1>=bx2 || bx1>=ax2|| ay1>=by2 || by1>=ay2 )
            return x+y;
        int x1 = Math.max(ax1, bx1);
        int x2 = Math.min(ax2, bx2);
        int y1 = Math.max(ay1, by1);
        int y2 = Math.min(ay2, by2);
        return x+y-(x2-x1)*(y2-y1);
    }
}

class Solution224 {
    public int calculate(String s) {
        Deque<Integer> ops = new LinkedList<Integer>();
        ops.push(1);
        int sign = 1;

        int ret = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                ret += sign * num;
            }
        }
        return ret;
    }
}

// 225
class MyStack {
    Queue<Integer> queue = new ArrayDeque<>();
    int size = 0;
    public MyStack() {

    }

    public void push(int x) {
        queue.add(x);
        size++;
    }

    public int pop() {
        for(int i=1; i<size; i++) {
            queue.add(queue.poll());
        }
        size--;
        return queue.poll();
    }

    public int top() {
        for(int i=1; i<size; i++) {
            queue.add(queue.poll());
        }
        int ans = queue.peek();
        queue.add(queue.poll());
        return ans;
    }

    public boolean empty() {
        return size == 0;
    }
}

class Solution227 {
    public int calculate(String s) {
        s = s.trim();
        Stack<Integer> nums = new Stack<>();
        Stack<Character> chars = new Stack<>();
        for(int i=0; i<s.length();) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch)) {
                int n = 0;
                while (i<s.length() && Character.isDigit(s.charAt(i))) {
                    n = n*10 + s.charAt(i)-'0';
                    i++;
                }
                nums.push(n);
                if( !chars.isEmpty() && nums.size()==chars.size()+1 && (chars.peek()=='*' || chars.peek()=='/')) {
                    int x = nums.pop();
                    int y = nums.pop();
                    if(chars.pop()=='*')
                        nums.push(y*x);
                    else
                        nums.push(y/x);
                }
            } else if(ch=='+' || ch=='-' || ch=='*' || ch=='/') {
                if(ch=='+' || ch=='-') {
                    if(!chars.isEmpty() && nums.size()==chars.size()+1) {
                        int x = nums.pop();
                        int y = nums.pop();
                        if(chars.pop()=='+')
                            nums.push(y+x);
                        else
                            nums.push(y-x);
                    }
                }
                chars.push(ch);
                i++;
            } else {
                i++;
            }
        }
        if(!chars.isEmpty()) {
            int x = nums.pop();
            int y = nums.pop();
            if(chars.pop()=='+')
                nums.push(y+x);
            else
                nums.push(y-x);
        }
        return nums.pop();
    }
}

class Solution227_1 {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}


class Solution228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        int n = nums.length;
        int left=0, right=0;
        while (right<n){
            if(nums[right]==nums[left] || nums[right]==nums[right-1]+1) {
                right++;
            } else {
                if(right-left==1)
                    ans.add(nums[left]+"");
                else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(nums[left]).append("->").append(nums[right-1]);
                    ans.add(sb.toString());
                }
                left = right;
            }
        }
        if(right-left==1)
            ans.add(nums[left]+"");
        else {
            StringBuilder sb = new StringBuilder();
            sb.append(nums[left]).append("->").append(nums[right-1]);
            ans.add(sb.toString());
        }
        return ans;
    }
}

class Solution231 {
    public boolean isPowerOfTwo(int n) {
        if(n<=0)
            return false;
        int ans = 0;
        while (n!=0) {
            ans += (n&1);
            n >>= 1;
        }
        return ans == 1;
    }
}

class Solution236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null)
            return root;
        if(root==p || root==q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left!=null && right!=null) {
            return root;
        } else if(left!=null) {
            return left;
        } else if(right!=null) {
            return right;
        }
        return null;
    }
}
