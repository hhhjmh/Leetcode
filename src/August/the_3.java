package August;

import java.util.*;

public class the_3 {
    public static void main(String[] args) {
        Solution899 solution899 = new Solution899();
//        solution899.orderlyQueue("kuh", 1);

        Solution273 solution273 = new Solution273();
//        System.out.println(solution273.numberToWords(10));

        String[] strs = {"dog","racecar","car"};
        Solution14 solution14 = new Solution14();
        System.out.println(solution14.longestCommonPrefix(strs));

    }
}

class Solution899 {
    public String orderlyQueue(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        if(k==1) {
            for(int i=0; i<s.length(); i++) {
                char ch = sb.charAt(0);
                sb.deleteCharAt(0);
                sb.append(ch);
                s = sb.toString().compareTo(s) > 0 ? s : sb.toString();
            }
            return s;
        } else {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            return new String(arr);
        }
    }
}

class Solution273 {

    String[] ones = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] tens = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] hundreds = {"Hundred", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if(num==0)
            return "Zero";
        StringBuilder sb = new StringBuilder();
        int billions = num / 1000000000;
        num = num - billions * 1000000000;
        int millions = num / 1000000;
        num = num - millions * 1000000;
        int thousands = num / 1000;
        num = num - thousands * 1000;

        if(billions > 0) {
            sb.append(number(billions)).append(hundreds[3]).append(" ");
        } if(millions > 0) {
            sb.append(number(millions)).append(hundreds[2]).append(" ");
        } if(thousands > 0) {
            sb.append(number(thousands)).append(hundreds[1]).append(" ");
        } if (num > 0) {
            sb.append(number(num)).append(" ");
        }

        return sb.toString().trim();
    }

    public String number(int n) {
        StringBuilder res = new StringBuilder();
        int h = n / 100;
        if(h>0) {
            res.append(ones[h-1]).append(" ").append(hundreds[0]).append(" ");
        }
        n = n - h * 100;
        if( n >= 10 && n < 20) {
            res.append(teens[n-10]).append(" ");
            return res.toString();
        }
        int ten = n / 10;
        int one = n % 10;
        if(ten > 0) {
            res.append(tens[ten-2]).append(" ");
        }
        if(one > 0) {
            res.append(ones[one-1]).append(" ");
        }
        return res.toString();
    }
}

class Solution14 {
    public String longestCommonPrefix(String[] strs) {
        if(strs[0]=="")
            return "";
        String sb = new String(strs[0]);

        for(int i=1; i<strs.length; i++) {
            String temp = new String(strs[i]);
            if(temp.toString()=="") {
                return "";
            }
            if(temp.length() < sb.length()) {
                sb = sb.substring(0, temp.length());
            } else {
                temp = temp.substring(0, sb.length());
            }
            for(int j=0; j<temp.length(); j++) {
                if(temp.charAt(j)!=sb.charAt(j)) {
                    sb = sb.substring(0, j);
                    if(sb=="")
                        return sb;
                    break;
                }
            }
        }
        return sb;
    }
}
