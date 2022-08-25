package August;

import java.util.*;

public class the_19 {
    public static void main(String[] args) {
//        new Solution35().searchInsert(new int[]{1,3,5,6}, 7);

//        new Solution38().countAndSay(4);

/*        new Solution443().compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'
                ,'b','b','b','b','b','b','b','b','b','b','b','b'
                ,'b','b','b','b','b','b','b','b','b','b','b','b'
                ,'b','b','b','b','b','b','b','b','b','b','b','b'
                ,'b','b','b','b','b','b','b','b','b','b','b','b'
                ,'b','b','b','b','b','b','b','b','b','b','b','b'
                ,'b','b','b','b','b','b','b','b','b','b','b','b'
                ,'b','b','b','b','b','b','b','b','b','b','b','b'
                ,'b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b','b'
                ,'b','b','b','b','b','b','b','b','b','b','b','b'
                ,'b','b','b','b','b','b','b','b','b','b','b','b'
                ,'b','b','b','b','b','b','b','b','b','b','b','b'
        });*/

//        new Solution40().combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);

//        new Solution41().firstMissingPositive(new int[]{1,2,0});

//        new Solution268().missingNumber(new int[]{0});

        new Solution448().findDisappearedNumbers(new int[]{1,1});

    }
}

class Solution1450 {
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int n = startTime.length, res = 0;
        for(int i=0; i<n; i++) {
            if(startTime[i]<=queryTime && endTime[i]>=queryTime)
                res++;
        }
        return res;
    }
}

class Solution35 {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left<right) {
            int mid = left + (right-left) / 2;
            if(nums[mid]>=target)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}

class Solution36 {
    public boolean isValidSudoku(char[][] board) {
        for(int i=0; i<9; i++) {
            if(!(isRow(board, i) && isLine(board, i)))
                return false;
        }
        for(int i=0; i<9; i+=3) {
            for(int j=0; j<9; j+=3){
                if(!isNine(board, i, j))
                    return false;
            }
        }
        return true;
    }

    public boolean isRow(char[][] board, int n) {
        int[] arr = new int[9];
        for(int i=0; i<9; i++) {
            if(Character.isDigit(board[n][i])) {
                int temp = board[n][i] - '1';
                if(arr[temp]==1)
                    return false;
                arr[temp]++;
            }
        }
        return true;
    }

    public boolean isLine(char[][] board, int n) {
        int[] arr = new int[9];
        for(int i=0; i<9; i++) {
            if(Character.isDigit(board[i][n])) {
                int temp = board[i][n] - '1';
                if(arr[temp]==1)
                    return false;
                arr[temp]++;
            }
        }
        return true;
    }

    public boolean isNine(char[][] board, int x, int y) {
        int[] arr = new int[9];
        for(int i=x; i<x+3; i++) {
            for(int j=y; j<y+3;j++) {
                if(Character.isDigit(board[i][j])) {
                    int temp = board[i][j] - '1';
                    if(arr[temp]==1)
                        return false;
                    arr[temp]++;
                }
            }
        }
        return true;
    }
}

class Solution37 {
    private boolean[][] line = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '0' - 1;
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }

        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }
}

class Solution38 {
    public String countAndSay(int n) {
        String s = "1";
        if(n==1)
            return s;
        for(int i=1; i<n; i++) {
            StringBuilder ss = new StringBuilder();
            int count = 1;
            for(int j=0; j<s.length(); j++) {
                if(j+1<s.length() && s.charAt(j)==s.charAt(j+1)) {
                    count++;
                } else {
                    ss.append(count).append(s.charAt(j));
                    count = 1;
                }
            }
            s = ss.toString();
        }
        return s;
    }
}

class Solution443 {
    public int compress(char[] chars) {
        if(chars.length==1)
            return 1;
        int ans = 0;

        int count = 1;
        for(int i=0,j=0; i<chars.length; i++) {
            if(i+1<chars.length && chars[i]==chars[i+1]) {
                count++;
            } else {
                chars[j] = chars[i];
                j++;
                if(count==1) {
                    ans++;
                } else if(count>1 && count<10) {
                    ans+=2;
                    chars[j] = (char) (count+'0');
                    j++;
                } else if(count>=10 && count<100){
                    ans+=3;
                    chars[j] = (char) (count/10+'0');
                    j++;
                    chars[j] = (char) (count%10+'0');
                    j++;
                } else if(count>=100 && count<1000) {
                    ans+=4;
                    chars[j] = (char) (count/100+'0');
                    count -= (count/100)*100;
                    j++;
                    chars[j] = (char) (count/10+'0');
                    j++;
                    chars[j] = (char) (count%10+'0');
                    j++;
                } else {
                    ans+=5;
                    chars[j] = (char) (count/1000+'0');
                    count -= (count/1000)*1000;
                    j++;
                    chars[j] = (char) (count/100+'0');
                    count -= (count/100)*100;
                    j++;
                    chars[j] = (char) (count/10+'0');
                    j++;
                    chars[j] = (char) (count%10+'0');
                    j++;
                }
                count = 1;
            }
        }
        return ans;
    }
}

class Solution40 {

    List<List<Integer>> res = new ArrayList<>();
    int len;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        len = candidates.length;

        track(candidates, path, target, 0, 0);
        return res;
    }

    private void track(int[] candidates, List<Integer> path, int target, int sum, int index) {
        if(target==sum) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=index; i<len; i++) {
            if(i>index && candidates[i]==candidates[i-1]) continue;
            int rs = sum + candidates[i];
            if(rs<=target) {
                path.add(candidates[i]);
                track(candidates, path, target, rs, i+1);
                path.remove(path.size()-1);
            } else {
                break;
            }
        }
    }


}

class Solution41 {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for(int i=0; i<len; i++) {
            if(nums[i]<=0)
                nums[i] = len+1;
        }
        for(int i=0; i<len; i++) {
            int k = Math.abs(nums[i]);
            if(k<=len)
                nums[k-1] = -Math.abs(nums[k-1]);
        }

        for(int i=0; i<len; i++) {
            if(nums[i]>0)
                return i+1;
        }
        return len+1;
    }
}

class Solution268 {
    public int missingNumber(int[] nums) {
        for(int i=0; i<nums.length; i++) {
            while (nums[i]<nums.length && nums[i]!=i) {
                int temp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = temp;
            }
        }
        for(int i=0; i<nums.length; i++) {
            if(nums[i]!=i)
                return i;
        }
        return nums.length;
    }
}

class Solution268_1 {
    public int missingNumber(int[] nums) {
        int sum = 0, n = nums.length;
        for(int num : nums)
            sum += num;
        return n*(n+1)/2-sum;
    }
}

class Solution287 {
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow!=fast);
        slow = 0;
        while (slow!=fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}

class Solution448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int i=0; i<nums.length; i++) {
            while (nums[i]!=i+1 && nums[nums[i]-1]!=nums[i]) {
                swap(nums, nums[i]-1, i);
            }
        }
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<nums.length; i++) {
            if(nums[i]!=i+1)
                res.add(i+1);
        }
        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
