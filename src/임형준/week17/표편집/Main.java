package src.알고리즘스터디.날짜12월3째주.표편집;

import java.util.Stack;

/**
 * date : 21.12.22
 * link : https://programmers.co.kr/learn/courses/30/lessons/81303
 * pass : fail
 */

public class Main {
    public static void main(String[] args) {
        Solution solution= new Solution();
//        solution.solution(8,2,new String[]{"D 2","C","U 3","C","D 4","C","U 2","Z","Z"});
        String solution1 = solution.solution(5, 2, new String[]{"D 2", "U 2", "C","C","C","Z","Z"});
        System.out.println(solution1);

    }
    static class Solution {

        static int current;
        static int endIndex;

        static class Member{
            int index;
            boolean isExist;

            public Member(int index, boolean isExist) {
                this.index = index;
                this.isExist = isExist;
            }

            @Override
            public String toString() {
                return "Member{" +
                        "index=" + index +
                        ", isExist=" + isExist +
                        '}';
            }
        }

        public String solution(int n, int k, String[] cmd) {
            Member[] members = new Member[n];
            for(int i=0;i<n;i++){
                members[i] = new Member(i,true);
            }
            current = k;
            endIndex= n-1;
            Stack<Member> stack = new Stack<>();

            for(String s : cmd){
                String[] s1 = s.split(" ");
                String command = s1[0];
                int move=0;
                if(s1.length >1){
                    move = Integer.parseInt(s1[1]);
                }

                switch (command){
                    case "U":
                        while(move>0){
                            if(members[--current].isExist) move--;
                        }
                        break;
                    case "D":
                        while(move>0){
                            if(members[++current].isExist) move--;
                        }
                        break;
                    case "C":
                        members[current].isExist = false;
                        stack.push(members[current]);

                        if(current == endIndex){
                            for(int i=current-1;i>=0;i--){
                                if(members[i].isExist){
                                    current = i;
                                    endIndex = i;
                                    break;
                                }
                            }
                        }else{
                            for(int i=current+1;i<n;i++){
                                if(members[i].isExist){
                                    current = i;
                                    break;
                                }
                            }
                        }
                        break;
                    case "Z":
                        Member pop = stack.pop();
                        pop.isExist=true;
                        break;
                }
            }

            StringBuilder sb = new StringBuilder();

            for(int i=0;i<n;i++){
                if(members[i].isExist) sb.append("O");
                else sb.append("X");
            }
            return sb.toString();
        }
    }
}
