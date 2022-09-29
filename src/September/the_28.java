package September;

import java.util.*;

public class the_28 {
    public static void main(String[] args) {
//        new Solution1709().getKthMagicNumber(10);

//        new Solution486().PredictTheWinner(new int[]{1, 5, 2});

        new Solution491().findSubsequences(new int[]{4, 6, 7, 7});
    }
}

class Solution1709 {
    public int getKthMagicNumber(int k) {
        int[] arr = new int[k];
        arr[0] = 1;
        int p3 = 0, p5 = 0, p7 = 0;
        for(int i=1; i<k; i++) {
            int min = Math.min(3*arr[p3], Math.min(5*arr[p5], 7*arr[p7]));
            arr[i] = min;
            if(min == 3*arr[p3]) {
                p3++;
            } if(min == 5*arr[p5]) {
                p5++;
            } if(min == 7*arr[p7]) {
                p7++;
            }
        }
        return arr[k-1];
    }
}

class Solution481 {
    public int magicalString(int n) {
        //相当于两个指针对一个字符串做操作
        if(n <= 3){
            return 1;
        }
        StringBuilder str =new StringBuilder("122");
        int numType = str.length() - 1; //记录下一个加入数的类型(是1还是2)
        int numCount = str.length() - 1;//记录加几个该类型数
        int count = 1;//记录1的个数

        //numType一直指向字符串的最后一个字符，而numCount是每次向右走一位
        while(str.length() < n){
            numType = str.length() - 1;
            if(str.charAt(numType) == '1'){
                if(str.charAt(numCount) == '1'){str.append("2");}
                else{str.append("22");}
            }else{
                if(str.charAt(numCount) == '1'){str.append("1");}
                else{str.append("11");}
            }
            numCount++;
        }
        for(int i = 1; i < n; i++){
            if(str.charAt(i) == '1'){
                count++;
            }
        }
        return count;
    }
}

class Solution483 {
    public String smallestGoodBase(String n) {
        long m = Long.parseLong(n);
        int max = (int)(Math.log(m) / Math.log(2) + 1);
        for (int len = max; len >= 3; len--) {
            long k = (long)Math.pow(m, 1.0 / (len - 1));
            long res = 0;
            for (int i = 0; i < len; i++) res = res * k + 1;
            if (res == m) return String.valueOf(k);
        }
        return String.valueOf(m - 1);
    }
}

class Solution486 {
    public boolean PredictTheWinner(int[] nums) {
        return total(nums, 0, nums.length - 1, 1) >= 0;
    }

    public int total(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return nums[start] * turn;
        }
        int scoreStart = nums[start] * turn + total(nums, start + 1, end, -turn);
        int scoreEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
        return Math.max(scoreStart * turn, scoreEnd * turn) * turn;
    }
}

class Solution486_1 {
    public boolean PredictTheWinner(int[] nums) {
        int length = nums.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] >= 0;
    }
}

class Solution488 {
    class State {
        String board;
        String hand;
        int step;

        public State(String board, String hand, int step) {
            this.board = board;
            this.hand = hand;
            this.step = step;
        }
    }

    public int findMinStep(String board, String hand) {
        char[] arr = hand.toCharArray();
        Arrays.sort(arr);
        hand = new String(arr);

        // 初始化用队列维护的状态队列：其中的三个元素分别为桌面球状态、手中球状态和回合数
        Queue<State> queue = new ArrayDeque<State>();
        queue.offer(new State(board, hand, 0));

        // 初始化用哈希集合维护的已访问过的状态
        Set<String> visited = new HashSet<String>();
        visited.add(board + " " + hand);

        while (!queue.isEmpty()) {
            State state = queue.poll();
            String curBoard = state.board;
            String curHand = state.hand;
            int step = state.step;
            for (int i = 0; i <= curBoard.length(); ++i) {
                for (int j = 0; j < curHand.length(); ++j) {
                    // 第 1 个剪枝条件: 当前球的颜色和上一个球的颜色相同
                    if (j > 0 && curHand.charAt(j) == curHand.charAt(j - 1)) {
                        continue;
                    }

                    // 第 2 个剪枝条件: 只在连续相同颜色的球的开头位置插入新球
                    if (i > 0 && curBoard.charAt(i - 1) == curHand.charAt(j)) {
                        continue;
                    }

                    // 第 3 个剪枝条件: 只在以下两种情况放置新球
                    boolean choose = false;
                    //  - 第 1 种情况 : 当前球颜色与后面的球的颜色相同
                    if (i < curBoard.length() && curBoard.charAt(i) == curHand.charAt(j)) {
                        choose = true;
                    }
                    //  - 第 2 种情况 : 当前后颜色相同且与当前颜色不同时候放置球
                    if (i > 0 && i < curBoard.length() && curBoard.charAt(i - 1) == curBoard.charAt(i) && curBoard.charAt(i - 1) != curHand.charAt(j)) {
                        choose = true;
                    }

                    if (choose) {
                        String newBoard = clean(curBoard.substring(0, i) + curHand.charAt(j) + curBoard.substring(i));
                        String newHand = curHand.substring(0, j) + curHand.substring(j + 1);
                        if (newBoard.length() == 0) {
                            return step + 1;
                        }
                        String str = newBoard + " " + newHand;
                        if (visited.add(str)) {
                            queue.offer(new State(newBoard, newHand, step + 1));
                        }
                    }
                }
            }
        }
        return -1;
    }

    public String clean(String s) {
        StringBuffer sb = new StringBuffer();
        Deque<Character> letterStack = new ArrayDeque<Character>();
        Deque<Integer> countStack = new ArrayDeque<Integer>();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            while (!letterStack.isEmpty() && c != letterStack.peek() && countStack.peek() >= 3) {
                letterStack.pop();
                countStack.pop();
            }
            if (letterStack.isEmpty() || c != letterStack.peek()) {
                letterStack.push(c);
                countStack.push(1);
            } else {
                countStack.push(countStack.pop() + 1);
            }
        }
        if (!countStack.isEmpty() && countStack.peek() >= 3) {
            letterStack.pop();
            countStack.pop();
        }
        while (!letterStack.isEmpty()) {
            char letter = letterStack.pop();
            int count = countStack.pop();
            for (int i = 0; i < count; ++i) {
                sb.append(letter);
            }
        }
        sb.reverse();
        return sb.toString();
    }
}

class Solution491 {
    Set<List<Integer>> ret = new HashSet<>();
    int len;
    public List<List<Integer>> findSubsequences(int[] nums) {
        len = nums.length;
        List<Integer> list = new ArrayList<>();
        DFS(nums, list, 0);
        return new ArrayList<>(ret);
    }

    private void DFS(int[] nums, List<Integer> list, int index) {
        if(list.size()>1) {
            ret.add(new ArrayList<>(list));
        }

        for(int i = index; i<len; i++) {
            if(list.isEmpty() || nums[i] >= list.get(list.size()-1)) {
                list.add(nums[i]);
                DFS(nums, list, i+1);
                list.remove(list.size()-1);
            }
        }
    }
}
