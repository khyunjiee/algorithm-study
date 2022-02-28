package src.프로그래머스.완전탐색.카펫;

import java.util.Arrays;

/**
 * 완전탐색이라는 키워드를 보고 어렵게 생각했다.
 * 식 유도를 통해 답을 구하는 간단한 문제였다.

 totalCnt = yellow + brown
 for i <- 0 to totalCnt
     row = i
     col = totalCnt / row
     if (row-2) * (col-2) == yellow

 * yellow + brown  = 총 블록숫자
 * brown이 마지막 칸에만 채워지므로, row-2 * col-2 를 하면 yellow 값이 나온다는 것을 이용한다.
 */

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(24,24);
    }
    static class Solution {
        public int[] solution(int brown, int yellow) {

            int totalCnt = brown + yellow;
            int[] res = new int[2];

            for(int i=1;i<totalCnt;i++){
                int row = i;
                int col = totalCnt/row;

                if((row-2) * (col-2) == yellow && row >= col){
                    res[0] = row;
                    res[1] = col;
                }
            }
            System.out.println(Arrays.toString(res));
            return res;
        }
    }
}
