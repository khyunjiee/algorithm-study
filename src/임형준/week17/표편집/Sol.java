package src.알고리즘스터디.날짜12월3째주.표편집;

import java.util.Stack;

/**
 * link : https://velog.io/@yuiopre98/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-level-3-%ED%91%9C-%ED%8E%B8%EC%A7%91
 */

public class Sol {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String solution1 = solution.solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"});
//        String solution1 = solution.solution(5, 2, new String[]{"D 2", "U 2", "C","C","C","Z","Z"});
        System.out.println(solution1);
    }
    static class Solution {
        public String solution(int n, int k, String[] cmd) {
            Stack<Integer> remove_order = new Stack<Integer>();
            int table_size = n;
            for (String s : cmd) {
                System.out.println("k = " + k);
                System.out.println("command : " + s);
                char c = s.charAt(0);
                if (c == 'D')
                    k += Integer.parseInt(s.substring(2));
                else if (c == 'U')
                    k -= Integer.parseInt(s.substring(2));
                else if (c == 'C') {
                    remove_order.add(k);
                    table_size--;
                    if (k == table_size)
                        k--;
                } else if (c == 'Z') {
                    if (remove_order.pop() <= k)
                        k++;
                    table_size++;
                }
            }
            System.out.println(remove_order);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<table_size; i++)
                sb.append("O");
            while(!remove_order.isEmpty())
                sb.insert(remove_order.pop(), "X");
            return sb.toString();
        }
    }
}
