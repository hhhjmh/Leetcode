package demo1;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class demo {
}


class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

class Solution116 {
    public Node connect(Node root) {
        if(root==null)
            return root;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            int n = queue.size();
            if(temp.left!=null) queue.add(temp.left);
            if(temp.right!=null) queue.add(temp.right);
            for(int i=0; i<n; i++) {
                Node node = queue.poll();
                temp.next = node;
                temp = node;
                if(temp.left!=null) queue.add(temp.left);
                if(temp.right!=null) queue.add(temp.right);
            }
        }
        return root;
    }
}