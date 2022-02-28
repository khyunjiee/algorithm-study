package src.프로그래머스.그리디.구명보트;

import java.util.Arrays;

/**
 *
 */

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int solution1 = solution.solution(new int[]{70, 50, 80, 50}, 100);
        System.out.println(solution1);
    }
    static class Solution {
        public int solution(int[] people, int limit) {
            Arrays.sort(people);

            int min=0;
            int result=0;

            for(int i=people.length-1;i>=min;i--){
                if(people[i]+people[min]<=limit) {
                    min++;
                }
                result++;
            }
            return result;
        }
    }
}
