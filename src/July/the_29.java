package July;

public class the_29 {
    public static void main(String[] args) {
        int[] p1 = {0,0};
        int[] p2 = {5,4};
        int[] p3 = {5,0};
        int[] p4 = {0,4};
        Solution593 solution593 = new Solution593();
//        System.out.println(solution593.validSquare(p1, p2, p3, p4));

        Solution7_1 solution7_1 = new Solution7_1();
//        System.out.println(solution7_1.reverse(2123456789));

        Solution8_1 solution8_1 = new Solution8_1();
//        System.out.println(solution8_1.myAtoi("   +0 123"));

        Solution190 solution190 = new Solution190();
        System.out.println(solution190.reverseBits(43261596));

    }
}

// 593
class Solution593 {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if((p1[0]==p2[0]&&p1[1]==p2[1]) || (p1[0]==p3[0]&&p1[1]==p3[1]) || (p1[0]==p4[0]&&p1[1]==p4[1]))
            return false;
        return (isEqual(p1, p2, p3, p4) + isEqual(p1, p3, p2, p4) +isEqual(p1, p4, p2, p3)) > 1;
    }

    public int isEqual(int[] p1, int[] p2, int[] p3, int[] p4) {
        int x12 = p1[0]-p2[0], y12 = p1[1]-p2[1], x34 = p3[0]-p4[0], y34=p3[1]-p4[1];
        int x23 = p2[0]-p3[0], y23= p2[1]-p3[1], x13 = p1[0]-p3[0], y13= p1[1]-p3[1];
        int x24 = p2[0]-p4[0], y24= p2[1]-p4[1], x14 = p1[0]-p4[0], y14= p1[1]-p4[1];
        if(x12*y34!=x34*y12 || x12*x12+y12*y12!=x34*x34+y34*y34 ||
                (x12*x12+y12*y12+x23*x23+y23*y23!=x13*x13+y13*y13 && x12*x12+y12*y12+x24*x24+y24*y24!=x14*x14+y14*y14)
                ||(x12*x12+y12*y12!=x23*x23+y23*y23 && x12*x12+y12*y12!=x13*x13+y13*y13 ))
            return 0;
        return 1;
    }

    // 任选3点作为等腰直角三角形
    long len = -1;
    public boolean validSquare1(int[] a, int[] b, int[] c, int[] d) {
        return calc(a, b, c) && calc(a, b, d) && calc(a, c, d) && calc(b, c, d);
    }
    boolean calc(int[] a, int[] b, int[] c) {
        long l1 = (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
        long l2 = (a[0] - c[0]) * (a[0] - c[0]) + (a[1] - c[1]) * (a[1] - c[1]);
        long l3 = (b[0] - c[0]) * (b[0] - c[0]) + (b[1] - c[1]) * (b[1] - c[1]);
        boolean ok = (l1 == l2 && l1 + l2 == l3) || (l1 == l3 && l1 + l3 == l2) || (l2 == l3 && l2 + l3 == l1);
        if (!ok) return false;
        if (len == -1) len = Math.min(l1, l2);
        else if (len == 0 || len != Math.min(l1, l2)) return false;
        return true;
    }
}

// 7
class Solution7_1 {
    public int reverse(int x) {
        long res = 0;
        int flag = x>0 ? 1 : 0;
        x = Math.abs(x);
        while(x>0) {
            res = res*10+x%10;
            x /= 10;
        }
        if(res>Integer.MAX_VALUE) {
            res = 0;
        }
        return flag==1? (int)res : (int)-res;
    }
}

// 7 官方 100%
class Solution7_2 {
    public int reverse(int x) {
        int res = 0;
        while(x!=0) {
            //每次取末尾数字
            int tmp = x%10;
            //判断是否 大于 最大32位整数
            if (res>214748364 || (res==214748364 && tmp>7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res<-214748364 || (res==-214748364 && tmp<-8)) {
                return 0;
            }
            res = res*10 + tmp;
            x /= 10;
        }
        return res;
    }
}

// 8
class Solution8_1 {
    public int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) return 0;
        if (!Character.isDigit(s.charAt(0)) && s.charAt(0) != '-' && s.charAt(0) != '+')
            return 0;
        int res = 0;
        boolean flag = s.charAt(0) == '-';
        int i = !Character.isDigit(s.charAt(0)) ? 1 : 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            int temp = ((flag ? Integer.MIN_VALUE : Integer.MIN_VALUE + 1) + (s.charAt(i) - '0')) / 10;
            if (temp > res)
                return flag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            res = res * 10 - (s.charAt(i) - '0');
            i++;
        }
        return flag ? res : -res;
    }
}

// 190 官方
class Solution190 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for(int i=0; i<32 && n!=0; i++) {
            res |= (n&1) << (31-i);
            n >>>= 1;
        }
        return res;
    }
}
