package src.알고리즘스터디.날짜12월3째주.표편집;

import java.util.Stack;

/**
 * date : 21.12.22
 * link : https://programmers.co.kr/learn/courses/30/lessons/81303
 */

public class MainV2 {
    public static void main(String[] args) {
        Solution solution= new Solution();
        String solution1 = solution.solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"});
//        String solution1 = solution.solution(5, 2, new String[]{"D 2", "U 2", "C","C","C","Z","Z"});
        System.out.println(solution1);

    }
    static class Solution {

        public String solution(int n, int k, String[] cmd) {
            Stack<Integer> stack = new Stack<>();

            int size=n;

            for(String s : cmd){
                String[] s1 = s.split(" ");
                String command = s1[0];
                int move=0;
                if(s1.length >1){
                    move = Integer.parseInt(s1[1]);
                }

                switch (command){
                    case "U":
                        k -= move;
                        break;
                    case "D":
                        k += move;
                        break;
                    case "C":
                        stack.push(k);
                        size--;
                        if(k==size){
                            k--;
                        }
                        break;
                    case "Z":
                        if(stack.pop() <= k) k++;
                        size++;
                        break;
                }
            }
            StringBuilder sb = new StringBuilder();

            for(int i=0;i<size;i++){
                sb.append("O");
            }

            while(!stack.isEmpty()) sb.insert(stack.pop(),"X");
            System.out.println(sb);

            return sb.toString();
        }
    }
}
