package August;


import java.util.*;

public class the_5 {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(4);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(6);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(1);
        TreeNode t6 = new TreeNode(5);
        t1.left = t2;t1.right = t3; t2.left = t4;t2.right=t5;t3.left=t6;
        Solution623 solution623 = new Solution623();
//        solution623.addOneRow(t1, 1, 2);

        Solution22 solution22 = new Solution22();
//        solution22.generateParenthesis(3);

        int[] arr = {2,3,6,7};

        Solution39 solution39 = new Solution39();
//        solution39.combinationSum(arr, 7);

        Solution401 solution401 = new Solution401();
        solution401.readBinaryWatch(5);

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

class Solution623 {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        TreeNode res = new TreeNode(val);
        if(depth==1) {
            res.left = root;
            return res;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        int cur = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            cur++;
            for(int i=0; i<size; i++) {
                TreeNode temp = queue.poll();
                if(temp.left!=null) queue.offer(temp.left);
                if(temp.right!=null) queue.offer(temp.right);
                if(cur==depth) {

                    TreeNode lr = new TreeNode(val);
                    lr.left = temp.left;
                    temp.left = lr;

                    TreeNode rr = new TreeNode(val);
                    rr.right = temp.right;
                    temp.right = rr;
                }
            }
            if(cur==depth)
                break;
        }
        return root;
    }
}

class Solution623_1 {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (root == null) {
            return null;
        }
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }
        if (depth == 2) {
            root.left = new TreeNode(val, root.left, null);
            root.right = new TreeNode(val, null, root.right);
        } else {
            root.left = addOneRow(root.left, val, depth - 1);
            root.right = addOneRow(root.right, val, depth - 1);
        }
        return root;
    }
}

class Solution22 {

    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        StringBuilder sb = new StringBuilder();
        track(sb, 0, 0, n);
        return res;
    }

    public void track(StringBuilder sb, int left, int right, int max) {
        if(sb.length()==max*2) {
            res.add(sb.toString());
        }

        if(left<max) {
            sb.append('(');
            track(sb, left+1, right, max);
            sb.deleteCharAt(sb.length()-1);
        }
        if(right<left) {
            sb.append(')');
            track(sb, left, right+1, max);
            sb.deleteCharAt(sb.length()-1);
        }

    }
}

class Solution39 {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);
        track(list, candidates, 0, target);
        return res;
    }

    public void track(List<Integer> list, int[] candidates,int index, int target) {
        if(target==0) {
            res.add(new ArrayList<>(list));
            return;
        }

        for(int i=index; i<candidates.length; i++) {
            if(candidates[i]<=target) {
                list.add(candidates[i]);
                track(list, candidates, i, target-candidates[i]);
                list.remove(list.size()-1);
            } else {
                break;
            }
        }
    }
}

class Solution401 {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        for(int i=0; i<1024; i++) {
            int hour = i>>6, minute = i & 63;
            if(hour<12 && minute<60 && Integer.bitCount(i)==turnedOn) {
                res.add(new String(hour + ":" + (minute < 10 ? "0"+minute:minute)));
            }
        }
        return res;
    }
}
