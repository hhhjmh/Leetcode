package August;

public class the_2 {
    public static void main(String[] args) {
//        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
//        circularQueue.enQueue(1); // 返回 true
//        circularQueue.enQueue(2); // 返回 true
//        circularQueue.enQueue(3); // 返回 true
//        circularQueue.enQueue(4); // 返回 false，队列已满
//        circularQueue.Rear(); // 返回 3
//        circularQueue.isFull(); // 返回 true
//        circularQueue.deQueue(); // 返回 true
//        circularQueue.enQueue(4); // 返回 true
//        circularQueue.Rear(); // 返回 4

        Solution12 solution12 = new Solution12();
//        solution12.intToRoman(58);

        Solution13 solution13 = new Solution13();
        solution13.romanToInt("LVIII");

    }
}

class MyCircularQueue {

    class ListNode {
        ListNode next;
        int val;
        ListNode(int val) {
            this.val = val;
        }
    }
    ListNode head = new ListNode(-1), rear=head;
    int size = 0, curSize = 0;

    public MyCircularQueue(int k) {
        size = k;
    }

    public boolean enQueue(int value) {
        if(curSize == size) {
            return false;
        }
        ListNode temp = new ListNode(value);

        rear.next = temp;
        rear = rear.next;
        if(curSize == 0) {
            head = rear;
        }
        curSize++;
        return true;
    }

    public boolean deQueue() {
        if(curSize==0)
            return false;
        head = head.next;
        curSize--;
        return true;
    }

    public int Front() {
        if(curSize==0)
            return -1;
        return head.val;
    }

    public int Rear() {
        if(curSize==0)
            return -1;
        return rear.val;
    }

    public boolean isEmpty() {
        return curSize == 0;
    }

    public boolean isFull() {
        return curSize == size;
    }
}

class Solution12 {
    public String intToRoman(int num) {
        int x = num / 1000, y = (num -x*1000)/ 100, z = (num-x*1000-y*100) / 10, k = num % 10;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<x; i++) {
            sb.append('M');
        }

        String [] strings = {"CM", "CD", "C", "D", "XC", "XL", "X", "L", "IX", "IV", "I", "V"};

        sb = Romen(sb, strings, 0, y);
        sb = Romen(sb, strings, 4, z);
        sb = Romen(sb, strings, 8, k);

        /*if(y==9) {
            sb.append("CM");
        } else if(y==4) {
            sb.append("CD");
        } else if(y<4) {
            for(int i=0; i<y; i++)
                sb.append("C");
        } else {
            sb.append("D");
            for(int i=5; i<y; i++)
                sb.append("C");
        }

        if(z==9) {
            sb.append("XC");
        } else if(z==4) {
            sb.append("XL");
        } else if(z<4) {
            for(int i=0; i<z; i++)
                sb.append("X");
        } else {
            sb.append("L");
            for(int i=5; i<z; i++)
                sb.append("X");
        }

        if(k==9) {
            sb.append("IX");
        } else if(k==4) {
            sb.append("IV");
        } else if(k<4) {
            for(int i=0; i<k; i++)
                sb.append("I");
        } else {
            sb.append("V");
            for(int i=5; i<k; i++)
                sb.append("I");
        }*/

        return sb.toString();
    }

    private StringBuilder Romen(StringBuilder sb, String[] strings, int k, int y) {
        if(y==9) {
            sb.append(strings[k]);
        } else if(y==4) {
            sb.append(strings[k+1]);
        } else if(y<4) {
            for(int i=0; i<y; i++)
                sb.append(strings[k+2]);
        } else {
            sb.append(strings[k+3]);
            for(int i=5; i<y; i++)
                sb.append(strings[k+2]);
        }
        return sb;
    }
}

class Solution13 {
    public int romanToInt(String s) {
        int n = s.length(), res = 0;
        for(int i=0; i<n;) {
            if(s.charAt(i)=='C') {
                if(i+1<n && s.charAt(i+1)=='M') {
                    res += 900;
                    i += 2;
                } else if(i+1<n && s.charAt(i+1)=='D') {
                    res += 400;
                    i += 2;
                } else {
                    res += 100;
                    i++;
                }
            } else if(s.charAt(i)=='X') {
                if(i+1<n && s.charAt(i+1)=='C') {
                    res += 90;
                    i += 2;
                } else if(i+1<n && s.charAt(i+1)=='L') {
                    res += 40;
                    i += 2;
                } else {
                    res += 10;
                    i++;
                }
            } else if(s.charAt(i)=='I') {
                if(i+1<n && s.charAt(i+1)=='X') {
                    res += 9;
                    i += 2;
                } else if(i+1<n && s.charAt(i+1)=='V') {
                    res += 4;
                    i += 2;
                } else {
                    res += 1;
                    i++;
                }
            } else if(s.charAt(i)=='M') {
                res += 1000;
                i++;
            } else if(s.charAt(i)=='D') {
                res += 500;
                i++;
            }else if(s.charAt(i)=='L') {
                res += 50;
                i++;
            }else if(s.charAt(i)=='V') {
                res += 5;
                i++;
            }
        }
        return res;
    }
}
