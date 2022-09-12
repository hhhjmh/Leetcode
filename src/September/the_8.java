package September;

import java.util.*;

public class the_8 {
    public static void main(String[] args) {
//        new Solution213().rob(new int[]{2,2,4,3,2,5});

        new Solution209().minSubArrayLen(7, new int[]{2,3,1,2,4,3});
    }
}

class Solution667 {
    public int[] constructArray(int n, int k) {
        List<Integer> list = new ArrayList<>();
        boolean f = true;
        boolean[] flag = new boolean[n];
        int temp = 1;
        for(int i=0; i<n && k>0; i++,k--) {
            list.add(temp);
            flag[temp-1] = true;
            if(f) {
                temp += k;
                f = false;
            } else {
                temp -= k;
                f = true;
            }
        }

        for(int i=0; i<n; i++) {
            if(flag[i]==false)
                list.add(i+1);
        }

        return list.stream().mapToInt(Integer::valueOf).toArray();
    }
}

class Solution667_1 {
    public int[] constructArray(int n, int k) {
        int[] answer = new int[n];
        int idx = 0;
        for (int i = 1; i < n - k; ++i) {
            answer[idx] = i;
            ++idx;
        }
        for (int i = n - k, j = n; i <= j; ++i, --j) {
            answer[idx] = i;
            ++idx;
            if (i != j) {
                answer[idx] = j;
                ++idx;
            }
        }
        return answer;
    }
}

// 208
class Trie {
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}

class WordDictionary {
    private Trie root;
    class Trie {
        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
           Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public Trie[] getChildren() {
            return children;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

    public WordDictionary() {
        root = new Trie();
    }

    public void addWord(String word) {
        root.insert(word);
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, Trie node) {
        if (index == word.length()) {
            return node.isEnd();
        }
        char ch = word.charAt(index);
        if (Character.isLetter(ch)) {
            int childIndex = ch - 'a';
            Trie child = node.getChildren()[childIndex];
            if (child != null && dfs(word, index + 1, child)) {
                return true;
            }
        } else {
            for (int i = 0; i < 26; i++) {
                Trie child = node.getChildren()[i];
                if (child != null && dfs(word, index + 1, child)) {
                    return true;
                }
            }
        }
        return false;
    }
}



class Solution209 {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0, len = Integer.MAX_VALUE, n = nums.length, value = 0;
        while (right<n || value>=target) {
            if(value>=target) {
                len = Math.min(len, right-left);
                if(len==1)
                    return 1;
                value -= nums[left];
                left++;
            } else {
                value += nums[right];
                right++;
            }
        }

        return len==Integer.MAX_VALUE ? 0 : len;
    }
}

class Solution210 {

    List<List<Integer>> list = new ArrayList<>();
    int[] arr;
    boolean valid = true;
    Stack<Integer> stack = new Stack<>();

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        for(int i=0; i<numCourses; i++)
            list.add(new ArrayList<>());

        arr = new int[numCourses];
        for(int[] info : prerequisites)
            list.get(info[1]).add(info[0]);

        for(int i=0; i<numCourses && valid; i++) {
            if(arr[i]==0)
                DFS(i);
        }

        if(!valid)
            return new int[0];
        int[] ans = new int[numCourses];
        for(int i=0; i<numCourses; i++)
            ans[i] = stack.pop();
        return ans;
    }

    private void DFS(int i) {
        arr[i] = 1;
        for(int j : list.get(i)) {
            if(arr[j]==1) {
                valid = false;
                return;
            } else if(arr[j]==0) {
                arr[j] = 1;
                DFS(j);
            }
        }
        stack.push(i);
        arr[i] = 2;
    }
}

class Solution213 {
    public int rob(int[] nums) {
        int n = nums.length;
        if(nums.length==1)
            return nums[0];
        if(nums.length==2)
            return Math.max(nums[0], nums[1]);

        return Math.max(robRange(nums, 0, n-2), robRange(nums, 1, n-1));
    }

    public int robRange(int[] nums, int start, int end) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}
