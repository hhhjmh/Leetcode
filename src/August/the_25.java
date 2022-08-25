package August;

import java.util.*;

public class the_25 {
    public static void main(String[] args) {
//        new Solution658().findClosestElements(new int[]{1,2,3,4,5}, 4, 4);

        /*new Solution79().exist(new char[][]{
                {'A'},
        }, "A");*/

//        new Solution84().largestRectangleArea(new int[]{6,7,5,2,4,5,9,3});

/*        new Solution85().maximalRectangle(new char[][] {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','0','1','1','1'},
                {'1','0','0','1','0'},
        });*/

/*
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(2);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(2);
        l1.next = l2; l2.next = l3; l3.next = l4; l4.next = l5; l5.next = l6;

        new Solution86().partition(l1, 3);
*/
//        new Solution89().grayCode(3);

        new Solution89_1().grayCode(3);

    }
}

class Solution658 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        int n = arr.length;
        if(x<=arr[0]) {
            for(int i=0; i<k; i++)
                ans.add(arr[i]);
        } else if(x>= arr[n-1]) {
            for(int i=n-1; n-1-i<k; i--)
                ans.add(arr[i]);
        } else {
            int j = 0;
            while (arr[j]<x) {
                j++;
            }
            int i = j-1;

            while (k>0) {
                if(i==-1) {
                    ans.add(arr[j]);
                    j++;
                } else if(j==n) {
                    ans.add(arr[i]);
                    i--;
                } else {
                    if(Math.abs(x-arr[i])<= Math.abs(arr[j]-x)) {
                        ans.add(arr[i]);
                        i--;
                    } else {
                        ans.add(arr[j]);
                        j++;
                    }
                }
                k--;
            }
        }
        Collections.sort(ans);
        return ans;
    }
}

class Solution658_1 {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length, l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (arr[mid] <= x) l = mid;
            else r = mid - 1;
        }
        r = r + 1 < n && Math.abs(arr[r + 1] - x) < Math.abs(arr[r] - x) ? r + 1 : r;
        int i = r - 1, j = r + 1;
        while (j - i - 1 < k) {
            if (i >= 0 && j < n) {
                if (Math.abs(arr[j] - x) < Math.abs(arr[i] - x)) j++;
                else i--;
            } else if (i >= 0) {
                i--;
            } else {
                j++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int p = i + 1; p <= j - 1; p++) ans.add(arr[p]);
        return ans;
    }
}

class Solution79 {

    boolean flag = false;

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                backTrack(board, word, visited, i, j, 0);
                if(flag)
                    return true;
            }
        }

        return false;
    }

    private boolean backTrack(char[][] board, String word,boolean[][] visited, int i, int j, int index) {
        if(index==word.length()) {
            flag = true;
            return true;
        }
        if(i<0 || j<0 || i>=board.length || j>=board[0].length)
            return false;


        if(board[i][j]==word.charAt(index) && !visited[i][j]) {
            visited[i][j] = true;
            backTrack(board, word, visited, i+1, j, index+1);
            backTrack(board, word, visited, i-1, j, index+1);
            backTrack(board, word, visited, i, j+1, index+1);
            backTrack(board, word, visited, i, j-1, index+1);
            visited[i][j] = false;
        }
        return false;
    }
}


class Solution212 {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> ans = new HashSet<String>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                dfs(board, trie, i, j, ans);
            }
        }

        return new ArrayList<String>(ans);
    }

    public void dfs(char[][] board, Trie now, int i1, int j1, Set<String> ans) {
        if (!now.children.containsKey(board[i1][j1])) {
            return;
        }
        char ch = board[i1][j1];
        now = now.children.get(ch);
        if (!"".equals(now.word)) {
            ans.add(now.word);
        }

        board[i1][j1] = '#';
        for (int[] dir : dirs) {
            int i2 = i1 + dir[0], j2 = j1 + dir[1];
            if (i2 >= 0 && i2 < board.length && j2 >= 0 && j2 < board[0].length) {
                dfs(board, now, i2, j2, ans);
            }
        }
        board[i1][j1] = ch;
    }
}

class Trie {
    String word;
    Map<Character, Trie> children;
    boolean isWord;

    public Trie() {
        this.word = "";
        this.children = new HashMap<Character, Trie>();
    }

    public void insert(String word) {
        Trie cur = this;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new Trie());
            }
            cur = cur.children.get(c);
        }
        cur.word = word;
    }
}

class Solution84 {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int [] left = new int[n];
        int [] right = new int[n];

        Deque<Integer> mono_stack = new ArrayDeque<>();
        for(int i=0; i<n; i++) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()]>=heights[i])
                mono_stack.poll();
            left[i] = mono_stack.isEmpty() ? -1 : mono_stack.peek();
            mono_stack.push(i);
        }

        mono_stack.clear();
        for(int i=n-1; i>=0; i--) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()]>=heights[i])
                mono_stack.poll();
            right[i] = mono_stack.isEmpty() ? n : mono_stack.peek();
            mono_stack.push(i);
        }

        int ans = 0;
        for(int i=0; i<n; i++)
            ans = Math.max(ans, (right[i]-left[i]-1) * heights[i] );

        return ans;
    }
}

class Solution84_1 {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);

        Deque<Integer> mono_stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}

class Solution85 {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int ans = 0;
        int [] height = new int[n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(matrix[i][j]=='1')
                    height[j] += 1;
                else
                    height[j] = 0;
            }
            ans = Math.max(ans,largestRectangleArea(height));
        }
        return ans;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int [] left = new int[n];
        int [] right = new int[n];
        Arrays.fill(right, n);
        Deque<Integer> mono_stack = new ArrayDeque<>();

        for(int i=0; i<n; i++) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i;
                mono_stack.poll();
            }

            left[i] = mono_stack.isEmpty() ? -1 : mono_stack.peek();
            mono_stack.push(i);
        }

        int ans = 0;
        for(int i=0; i<n; i++)
            ans = Math.max(ans, (right[i]-left[i]-1)*heights[i]);
        return ans;
    }
}

class Solution86 {
    public ListNode partition(ListNode head, int x) {
        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(-1);
        ListNode small = l1;
        ListNode large = l2;
        ListNode cur = head;
        while (head!=null) {
            head = head.next;
            cur.next = null;
            if(cur.val<x) {
               small.next = cur;
               small = small.next;
            } else {
                large.next = cur;
                large = large.next;
            }
            cur = head;
        }
        small.next = l2.next;
        return l1.next;
    }
}

class Solution87 {
    // 记忆化搜索存储状态的数组
    // -1 表示 false，1 表示 true，0 表示未计算
    int[][][] memo;
    String s1, s2;

    public boolean isScramble(String s1, String s2) {
        int length = s1.length();
        this.memo = new int[length][length][length + 1];
        this.s1 = s1;
        this.s2 = s2;
        return dfs(0, 0, length);
    }

    // 第一个字符串从 i1 开始，第二个字符串从 i2 开始，子串的长度为 length，是否和谐
    public boolean dfs(int i1, int i2, int length) {
        if (memo[i1][i2][length] != 0) {
            return memo[i1][i2][length] == 1;
        }

        // 判断两个子串是否相等
        if (s1.substring(i1, i1 + length).equals(s2.substring(i2, i2 + length))) {
            memo[i1][i2][length] = 1;
            return true;
        }

        // 判断是否存在字符 c 在两个子串中出现的次数不同
        if (!checkIfSimilar(i1, i2, length)) {
            memo[i1][i2][length] = -1;
            return false;
        }

        // 枚举分割位置
        for (int i = 1; i < length; ++i) {
            // 不交换的情况
            if (dfs(i1, i2, i) && dfs(i1 + i, i2 + i, length - i)) {
                memo[i1][i2][length] = 1;
                return true;
            }
            // 交换的情况
            if (dfs(i1, i2 + length - i, i) && dfs(i1 + i, i2, length - i)) {
                memo[i1][i2][length] = 1;
                return true;
            }
        }

        memo[i1][i2][length] = -1;
        return false;
    }

    public boolean checkIfSimilar(int i1, int i2, int length) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        for (int i = i1; i < i1 + length; ++i) {
            char c = s1.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        for (int i = i2; i < i2 + length; ++i) {
            char c = s2.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) - 1);
        }
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            int value = entry.getValue();
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
}

// [0,1,3,2,6,7,5,4]
class Solution89 {

    List<Integer> res = new ArrayList<>();
    boolean flag = false;

    public List<Integer> grayCode(int n) {
        int len = 1 << n;
        boolean[] visited = new boolean[len];
        visited[0] = true;
        res.add(0);
        backTrack(visited, 1, n,  len);
        return res;
    }

    private void backTrack(boolean[] visited, int index, int n, int len) {
        if(index==len) {
            int num = res.get(res.size()-1);
            int count = 0;
            while (num>0) {
                count += num & 1;
                num >>= 1;
            }
            if(count==1)
                flag = true;
        }
        if(!flag) {
            for(int i=0; i<n; i++) {
                int m = 1;
                m <<= i;
                int num = res.get(res.size()-1);
                num ^= m;
                if(!visited[num]) {
                    visited[num] = true;
                    res.add(num);
                    backTrack(visited, index+1, n, len);
                    if(flag)
                        break;
                    res.remove(num);
                    visited[num] = false;
                }
            }
        }
    }
}

class Solution89_1 {
    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        ret.add(0);
        for (int i = 1; i <= n; i++) {
            int m = ret.size();
            for (int j = m - 1; j >= 0; j--) {
                ret.add(ret.get(j) | (1 << (i - 1)));
            }
        }
        return ret;
    }
}

class Solution717 {
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        for(int i=0; i<n; i++) {
            if(bits[i]==0) {
                if(i==n-1)
                    return true;
            } else {
                i++;
            }
        }
        return false;
    }
}
