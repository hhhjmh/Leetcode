package August;

import java.util.*;

public class the_23 {
    public static void main(String[] args) {
//        new Solution66().plusOne(new int[]{9});

//        new Solution70().climbStairs(5);

//        new Solution746().minCostClimbingStairs(new int[]{10,15});

//        new Solution509().fib(2);

//        new Solution1137().tribonacci(4);

        new Solution71().simplifyPath("/a/./b/../../c/");

    }
}


class Solution782 {
    public int movesToChessboard(int[][] board) {
        int n = board.length;
        int rowMask = 0, colMask = 0;

        /* 检查棋盘的第一行与第一列 */
        for (int i = 0; i < n; i++) {
            rowMask |= (board[0][i] << i);
            colMask |= (board[i][0] << i);
        }
        int reverseRowMask = ((1 << n) - 1) ^ rowMask;
        int reverseColMask = ((1 << n) - 1) ^ colMask;
        int rowCnt = 0, colCnt = 0;
        for (int i = 0; i < n; i++) {
            int currRowMask = 0;
            int currColMask = 0;
            for (int j = 0; j < n; j++) {
                currRowMask |= (board[i][j] << j);
                currColMask |= (board[j][i] << j);
            }
            /* 检测每一行的状态是否合法 */
            if (currRowMask != rowMask && currRowMask != reverseRowMask) {
                return -1;
            } else if (currRowMask == rowMask) {
                /* 记录与第一行相同的行数 */
                rowCnt++;
            }
            /* 检测每一列的状态是否合法 */
            if (currColMask != colMask && currColMask != reverseColMask) {
                return -1;
            } else if (currColMask == colMask) {
                /* 记录与第一列相同的列数 */
                colCnt++;
            }
        }
        int rowMoves = getMoves(rowMask, rowCnt, n);
        int colMoves = getMoves(colMask, colCnt, n);
        return (rowMoves == -1 || colMoves == -1) ? -1 : (rowMoves + colMoves);
    }

    public int getMoves(int mask, int count, int n) {
        int ones = Integer.bitCount(mask);
        if ((n & 1) == 1) {
            /* 如果 n 为奇数，则每一行中 1 与 0 的数目相差为 1，且满足相邻行交替 */
            if (Math.abs(n - 2 * ones) != 1 || Math.abs(n - 2 * count) != 1 ) {
                return -1;
            }
            if (ones == (n >> 1)) {
                /* 以 0 为开头的最小交换次数 */
                return n / 2 - Integer.bitCount(mask & 0xAAAAAAAA);
            } else {
                return (n + 1) / 2 - Integer.bitCount(mask & 0x55555555);
            }
        } else {
            /* 如果 n 为偶数，则每一行中 1 与 0 的数目相等，且满足相邻行交替 */
            if (ones != (n >> 1) || count != (n >> 1)) {
                return -1;
            }
            /* 找到行的最小交换次数 */
            int count0 = n / 2 - Integer.bitCount(mask & 0xAAAAAAAA);
            int count1 = n / 2 - Integer.bitCount(mask & 0x55555555);
            return Math.min(count0, count1);
        }
    }
}

class Solution66 {
    public int[] plusOne(int[] digits) {
        int add = 1;
        for(int i=digits.length-1; i>=0; i--) {
            add = digits[i] + add;
            if(add>9) {
                digits[i] = 0;
                add = 1;
            } else {
                digits[i] = add;
                return digits;
            }
        }
        int[] arr = new int[digits.length+1];
        arr[0] = 1;
        return arr;
    }
}

class Solution68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<String>();
        int right = 0, n = words.length;
        while (true) {
            int left = right; // 当前行的第一个单词在 words 的位置
            int sumLen = 0; // 统计这一行单词长度之和
            // 循环确定当前行可以放多少单词，注意单词之间应至少有一个空格
            while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }

            // 当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格
            if (right == n) {
                StringBuffer sb = join(words, left, n, " ");
                sb.append(blank(maxWidth - sb.length()));
                ans.add(sb.toString());
                return ans;
            }

            int numWords = right - left;
            int numSpaces = maxWidth - sumLen;

            // 当前行只有一个单词：该单词左对齐，在行末填充剩余空格
            if (numWords == 1) {
                StringBuffer sb = new StringBuffer(words[left]);
                sb.append(blank(numSpaces));
                ans.add(sb.toString());
                continue;
            }

            // 当前行不只一个单词
            int avgSpaces = numSpaces / (numWords - 1);
            int extraSpaces = numSpaces % (numWords - 1);
            StringBuffer sb = new StringBuffer();
            sb.append(join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1))); // 拼接额外加一个空格的单词
            sb.append(blank(avgSpaces));
            sb.append(join(words, left + extraSpaces + 1, right, blank(avgSpaces))); // 拼接其余单词
            ans.add(sb.toString());
        }
    }

    // blank 返回长度为 n 的由空格组成的字符串
    public String blank(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    // join 返回用 sep 拼接 [left, right) 范围内的 words 组成的字符串
    public StringBuffer join(String[] words, int left, int right, String sep) {
        StringBuffer sb = new StringBuffer(words[left]);
        for (int i = left + 1; i < right; ++i) {
            sb.append(sep);
            sb.append(words[i]);
        }
        return sb;
    }
}

class Solution70 {
    public int climbStairs(int n) {
        if(n<3)
            return n;
        int x = 1, y = 2;
        for(int i=2; i<n; i++) {
            int temp = y;
            y = x + y;
            x = temp;
        }
        return y;
    }
}

class Solution746 {
    public int minCostClimbingStairs(int[] cost) {
        int min = Math.min(cost[0], cost[1]);
        for(int i=2; i<cost.length; i++) {
            cost[i] = min + cost[i];
            min = Math.min(cost[i], cost[i-1]);
        }
        return min;
    }
}

class Solution509 {
    public int fib(int n) {
        if(n<2)
            return n;
        int x = 0, y = 1;
        for(int i=1; i<n; i++) {
            int temp = y;
            y = x + y;
            x = temp;
        }
        return y;
    }
}

class Solution1137 {
    public int tribonacci(int n) {
        if(n<2)
            return n;
        if(n==2)
            return 1;
        int x = 0, y = 1, z = 1;
        for(int i=2; i<n; i++) {
            int temp = x + y + z;
            x = y;
            y = z;
            z = temp;
        }
        return z;
    }
}

class Solution71 {
    public String simplifyPath(String path) {
        String[] ss = path.split("/");

        Stack<String> stack = new Stack<>();
        for(int i=0; i<ss.length; i++) {
            if(ss[i].length()==0 || ss[i].equals("."))
                continue;
            else {
                if(ss[i].equals("..")) {
                    if(!stack.isEmpty())
                        stack.pop();
                } else {
                    stack.push(ss[i]);
                }
            }
        }
        String s = new String();
        while (!stack.isEmpty()){
            s = "/" + stack.pop() + s;
        }

        return s.length()==0 ? "/" : s;
    }
}

class Solution71_1 {
    public String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<String>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                stack.offerLast(name);
            }
        }
        StringBuffer ans = new StringBuffer();
        if (stack.isEmpty()) {
            ans.append('/');
        } else {
            while (!stack.isEmpty()) {
                ans.append('/');
                ans.append(stack.pollFirst());
            }
        }
        return ans.toString();
    }
}
