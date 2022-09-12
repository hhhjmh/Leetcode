package September;

import java.util.*;

public class the_7 {

}

class Solution1592 {
    public String reorderSpaces(String text) {
        int count = 0;
        for(int i=0; i<text.length(); i++) {
            if(text.charAt(i)==' ')
                count++;
        }
        int word = 0;
        String[] strings = text.trim().split(" ");
        for(int i=0; i<strings.length; i++) {
            if(strings[i].trim()!="")
                word++;
        }
        if(word==1) {
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<count; i++)
                sb.append(' ');
            return text.trim() + sb.toString();
        }

        int x = count / (word-1), y = count % (word-1);
        StringBuilder temp1 = new StringBuilder();
        StringBuilder temp2 = new StringBuilder();
        for(int i=0; i<x; i++) {
            temp1.append(' ');
        }
        for(int i=0; i<y; i++) {
            temp2.append(' ');
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<strings.length; i++) {
            if(strings[i].trim()!="")
                sb.append(strings[i]).append(temp1);
        }

        return sb.toString().trim() + temp2.toString();
    }
}

class Solution201 {
    public int rangeBitwiseAnd(int left, int right) {
        while (left < right) {
            right &= (right-1);
        }
        return right;
    }
}

class Solution203 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode pre = new ListNode(-1), cur = pre;
        cur.next = head;
        while (cur.next!=null) {
            if(cur.next.val == val)
                cur.next = cur.next.next;
            else
                cur = cur.next;
        }
        return pre.next;
    }
}

class Solution204 {
    public int countPrimes(int n) {
        int [] arr = new int[n];
        int ans = 0;
        for(int i = 2; i<n; i++) {
            if(arr[i]==0) {
                ans++;
                if( (long) i*i < n) {
                    for(int j=i*i; j<n; j+=i) {
                        arr[j] = 1;
                    }
                }
            }
        }
        return ans;
    }
}

class Solution205 {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map1 = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            if( (map1.containsKey(ch1) && map1.get(ch1)!=ch2) || (map2.containsKey(ch2) && map2.get(ch2)!=ch1) ) {
                    return false;
            }
            map1.put(ch1, ch2);
            map2.put(ch2, ch1);

        }
        return true;
    }
}

class Solution207 {

    List<List<Integer>> edges = new ArrayList<>();
    int[] visited;
    boolean valid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited = new int[numCourses];
        for(int i=0; i<numCourses; i++) {
            edges.add(new ArrayList<Integer>());
        }

        for(int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
        }

        for(int i=0; i<numCourses && valid; i++) {
            if(visited[i]==0) {
                DFS(i);
            }
        }
        return valid;
    }

    private void DFS(int i) {
        visited[i] = 1;
        for(int v : edges.get(i)) {
            if(visited[v]==0) {
                DFS(v);
                if(!valid)
                    return;
            } else if(visited[v]==1) {
                valid = false;
                return;
            }
        }
        visited[i] = 2;
    }
}