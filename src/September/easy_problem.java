package September;

import java.util.*;

public class easy_problem {
    public static void main(String[] args) {
        new Solution345().reverseVowels("hello");
    }
}

class Solution257 {
    List<String> ans = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        String path = new String();
        path = path + root.val;
        DFS(root, path);
        return ans;
    }

    private void DFS(TreeNode root, String path) {

        if(root.left==null && root.right==null) {
            ans.add(new String(path));
        }
        if(root.left!=null) {
            DFS(root.left, path+"->"+root.left.val);
        }
        if(root.right!=null) {
            DFS(root.right, path+"->"+root.right.val);
        }
    }
}

class Solution263 {
    public boolean isUgly(int n) {
        if(n<=0)
            return false;
        while (n!=1) {
            if(n%2==0) {
                n = n/2;
            } else if(n%3==0) {
                n = n/3;
            } else if(n%5==0) {
                n = n/5;
            } else {
                return false;
            }
        }
        return true;
    }
}

class Solution283 {
    public void moveZeroes(int[] nums) {
        int i=0, n=nums.length;
        for(int j=0; j<n; j++) {
            if(nums[j]!=0) {
                nums[i] = nums[j];
                i++;
            }
        }
        while (i<n) {
            nums[i] = 0;
            i++;
        }
    }
}

class Solution290 {
    public boolean wordPattern(String pattern, String s) {
        String[] strings = s.split(" ");
        if(strings.length!=pattern.length())
            return false;

        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        for(int i=0; i<pattern.length(); i++) {
            char ch = pattern.charAt(i);
            String ss = strings[i];
            if(map1.containsKey(ch)) {
                if(!map1.get(ch).equals(ss))
                    return false;
            } else {
                map1.put(ch, ss);
            }
            if(map2.containsKey(ss)) {
                if(!map2.get(ss).equals(ch))
                    return false;
            } else {
                map2.put(ss, ch);
            }

        }
        return true;
    }
}

class Solution290_1 {
    public boolean wordPattern(String pattern, String str) {
        Map<String, Character> str2ch = new HashMap<String, Character>();
        Map<Character, String> ch2str = new HashMap<Character, String>();
        int m = str.length();
        int i = 0;
        for (int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p);
            if (i >= m) {
                return false;
            }
            int j = i;
            while (j < m && str.charAt(j) != ' ') {
                j++;
            }
            String tmp = str.substring(i, j);
            if (str2ch.containsKey(tmp) && str2ch.get(tmp) != ch) {
                return false;
            }
            if (ch2str.containsKey(ch) && !tmp.equals(ch2str.get(ch))) {
                return false;
            }
            str2ch.put(tmp, ch);
            ch2str.put(ch, tmp);
            i = j + 1;
        }
        return i >= m;
    }
}

class Solution326 {
    public boolean isPowerOfThree(int n) {
        if(n<=0)
            return false;
        while (n!=1) {
            if(n%3==0)
                n = n/3;
            else
                return false;
        }
        return true;
    }
}

class Solution338 {
    public int[] countBits(int n) {
        int[] ans = new int[n+1];
        for(int i=1; i<n+1; i++) {
            int count = 0;
            int k = i;
            while (k!=0) {
                k = k & (k-1);
                count++;
            }
            ans[i] = count;
        }
        return ans;
    }
}

class Solution342 {
    public boolean isPowerOfFour(int n) {
        if(n<=0)
            return false;
        while (n!=1) {
            if(n%4==0)
                n = n/4;
            else
                return false;
        }
        return true;
    }
}

class Solution345 {
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        String vowels = "aeiouAEIOU";
        for(int i=0, j=chars.length-1; i<j; ) {
            if(vowels.indexOf(chars[i])<0) {
                i++;
            } else if(vowels.indexOf(chars[j])<0) {
                j--;
            } else {
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        }
        return new String(chars);
    }
}

class Solution350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums1.length; i++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<nums2.length; i++) {
            if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
                list.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i])-1);
            }
        }
        int[] ans = new int[list.size()];
        for(int i=0; i<list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}

class Solution350_1 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}


