package August;

import java.util.Stack;

public class the_31 {
}

class Solution946 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for(int i=0; i<pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && popped[j]==stack.peek()){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}

class Solution946_1 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length, idx = 0;
        for (int i = 0, j = 0; i < n; i++) {
            pushed[idx++] = pushed[i];
            while (idx > 0 && pushed[idx - 1] == popped[j] && ++j >= 0) idx--;
        }
        return idx == 0;
    }
}