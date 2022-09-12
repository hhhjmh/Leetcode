package September;

import java.util.*;

public class the_12 {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);t1.left=t2;
        // t1.right=t3;t3.left=t4;t3.right=t5;
        Codec codec = new Codec();
//        codec.serialize(t1);
        codec.deserialize("1,2");
    }
}


class Solution1608 {
    public int specialArray(int[] nums) {
        int [] arr = new int[1001];
        for(int num : nums)
            arr[num]++;
        for(int i=999; i>0; i--) {
            arr[i] += arr[i+1];
            if(i==arr[i])
                return i;
        }
        return -1;
    }
}

// 284
class PeekingIterator implements Iterator<Integer> {
    Iterator<Integer> iterator;
    Integer next;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        if(iterator.hasNext())
            next = iterator.next();
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer ans = next;
        next = iterator.hasNext() ? iterator.next() : null;
        return ans;
    }

    @Override
    public boolean hasNext() {
        return next!=null;
    }
}

class Solution289 {
    public void gameOfLife(int[][] board) {
        int m = board.length, n = board[0].length;
        boolean[][] change = new boolean[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                change[i][j] = isChange(board, i, j, board[i][j]);
            }
        }

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(change[i][j])
                    board[i][j] = board[i][j] == 1 ? 0 : 1;
            }
        }
    }

    private boolean isChange(int[][] board, int i, int j, int k) {
        int m = board.length, n = board[0].length;
        int count = 0;
        if(valid(m, n, i-1, j-1) && board[i-1][j-1]==1) count++;
        if(valid(m, n, i-1, j) && board[i-1][j]==1) count++;
        if(valid(m, n, i-1, j+1) && board[i-1][j+1]==1) count++;
        if(valid(m, n, i, j-1) && board[i][j-1]==1) count++;
        if(valid(m, n, i, j+1) && board[i][j+1]==1) count++;
        if(valid(m, n, i+1, j-1) && board[i+1][j-1]==1) count++;
        if(valid(m, n, i+1, j) && board[i+1][j]==1) count++;
        if(valid(m, n, i+1, j+1) && board[i+1][j+1]==1) count++;

        if(k==1 && (count<2 || count>3))
            return true;
        if(k==0 && count==3)
            return true;
        return false;
    }

    private boolean valid(int m, int n, int i, int j) {
        if(i>=0 && i<m && j>=0 && j<n)
            return true;
        return false;
    }
}

// 295_1
/*class MedianFinder {
    PriorityQueue<Integer> max;
    PriorityQueue<Integer> min;
    public MedianFinder() {
        max = new PriorityQueue<>((a,b) ->{return b-a;});
        min = new PriorityQueue<>();
    }

    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        if(min.size()>max.size()) {
            max.offer(min.poll());
        }
    }

    public double findMedian() {
        if(max.size()==min.size())
            return (max.peek() + min.peek()) / 2.0;
        return max.peek();
    }
}*/

// 295_2
class MedianFinder {
    TreeMap<Integer, Integer> head = new TreeMap<>(), tail = new TreeMap<>();
    int[] arr = new int[101];
    int a, b, c;
    public void addNum(int num) {
        if (num >= 0 && num <= 100) {
            arr[num]++;
            b++;
        } else if (num < 0) {
            head.put(num, head.getOrDefault(num, 0) + 1);
            a++;
        } else if (num > 100) {
            tail.put(num, tail.getOrDefault(num, 0) + 1);
            c++;
        }
    }
    public double findMedian() {
        int size = a + b + c;
        if (size % 2 == 0) return (find(size / 2) + find(size / 2 + 1)) / 2.0;
        return find(size / 2 + 1);
    }
    int find(int n) {
        if (n <= a) {
            for (int num : head.keySet()) {
                n -= head.get(num);
                if (n <= 0) return num;
            }
        } else if (n <= a + b) {
            n -= a;
            for (int i = 0; i <= 100; i++) {
                n -= arr[i];
                if (n <= 0) return i;
            }
        } else {
            n -= a + b;
            for (int num : tail.keySet()) {
                n -= tail.get(num);
                if (n <= 0) return num;
            }
        }
        return -1; // never
    }
}

// 297
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root==null)
            return "";
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for(int i=0; i<n; i++) {
                TreeNode temp = queue.poll();
                if(temp.left!=null)  {
                    queue.offer(temp.left);
                    sb.append(",").append(temp.left.val);
                } else {
                    sb.append(",null");
                }
                if(temp.right!=null)  {
                    queue.offer(temp.right);
                    sb.append(",").append(temp.right.val);
                } else {
                    sb.append(",null");
                }
            }
        }
        while (sb.charAt(sb.length()-1)=='l') {
            sb.delete(sb.length()-5, sb.length());
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strings = data.split(",");
        if(data=="" || data.length()==0)
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(strings[0]));
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        for(int i=1; i<strings.length;i++) {
            TreeNode temp = queue.poll();
            if(!strings[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.parseInt(strings[i]));
                temp.left = left;
                queue.offer(left);
            } else {
                temp.left = null;
            }
            i++;
            if(i<strings.length && !strings[i].equals("null")) {
                TreeNode right = new TreeNode(Integer.parseInt(strings[i]));
                temp.right = right;
                queue.offer(right);
            } else {
                temp.right = null;
            }
        }
        return root;
    }
}