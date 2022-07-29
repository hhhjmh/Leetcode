package July;

public class the_27 {
    public static void main(String[] args) {
        Solution17 solution17 = new Solution17();
//        System.out.println(solution17.fractionAddition("1/15-2/17"));

        Solution18 solution18 = new Solution18();
//        System.out.println(solution18.countSubstrings("aaa"));

        Solution20 solution19 = new Solution20();
        System.out.println(solution19.longestPalindromeSubseq("bbbab"));
    }
}

// 592
class Solution17 {
    public String fractionAddition(String expression) {
        long x=0, y=1;
        int index=0, n=expression.length();

        while(index<n) {
            int sign = 0;
            if(expression.charAt(index)=='-') {
                sign=1;
                index++;
            } else if(expression.charAt(index)=='+') {
                index++;
            }

            int k=1;
            long x1=0;
            while(index<n && Character.isDigit(expression.charAt(index))) {
                x1 = (expression.charAt(index)-'0') + x1*k;
                k *= 10;
                index++;
            }
            x1 = sign == 1 ? -x1 : x1;
            index++;
            k=1;
            long y1 = 0;
            while(index<n && Character.isDigit(expression.charAt(index))) {
                y1 = (expression.charAt(index)-'0') + y1*k;
                k *= 10;
                index++;
            }

            x = x*y1 + x1*y;
            y = y*y1;
        }

        if(x==0)
            return "0/1";

        long gcd = GCD(Math.abs(x), y);
        return Long.toString(x/gcd) + "/" + Long.toString(y/gcd);
    }

    public long GCD(long x, long y) {
        long temp = y % x;
        while(temp != 0) {
            y = x;
            x = temp;
            temp = y % x;
        }
        return x;
    }
}

// 647
class Solution18 {
    public int countSubstrings(String s) {
        int ans = 0, n=s.length();
        for(int i=0; i< 2*n-1; i++) {
            int l=i/2, r=i/2+i%2;
            while(l>=0 && r<n && s.charAt(l)==s.charAt(r)) {
                ans++;
                l--;
                r++;
            }
        }
        return ans;
    }
}

// 647 官方
class Solution19 {
    public int countSubstrings(String s) {
        int n = s.length();
        StringBuffer t = new StringBuffer("$#");
        for (int i = 0; i < n; ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        n = t.length();
        t.append('!');

        int[] f = new int[n];
        int iMax = 0, rMax = 0, ans = 0;
        for (int i = 1; i < n; ++i) {
            // 初始化 f[i]
            f[i] = i <= rMax ? Math.min(rMax - i + 1, f[2 * iMax - i]) : 1;
            // 中心拓展
            while (t.charAt(i + f[i]) == t.charAt(i - f[i])) {
                ++f[i];
            }
            // 动态维护 iMax 和 rMax
            if (i + f[i] - 1 > rMax) {
                iMax = i;
                rMax = i + f[i] - 1;
            }
            // 统计答案, 当前贡献为 (f[i] - 1) / 2 上取整
            ans += f[i] / 2;
        }

        return ans;
    }
}

// 516
class Solution20 {
    public int longestPalindromeSubseq(String s) {
        int n = s.length(), res = 0;
        int[][] arr = new int[n][n];
        for(int i=n-1; i>=0; i--) {
            arr[i][i] = 1;
            for(int j=i+1; j<n; j++) {
                if(s.charAt(i)==s.charAt(j)) {
                    arr[i][j] = arr[i+1][j-1]+2;
                } else {
                    arr[i][j] = Math.max(arr[i+1][j], arr[i][j-1]);
                }
            }
        }
        return arr[0][n-1];
    }
}
