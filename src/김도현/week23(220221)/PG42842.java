package pg;

import java.util.Arrays;

public class PG42842 {
    /* Level 2. 카펫
    brown => m + n, yellow => mn, 2차 방정식
    */
    public static void main(String[] args) {
        PG42842.Solution test = new PG42842.Solution();
        System.out.println(Arrays.toString(test.solution(10, 2)));
        System.out.println(Arrays.toString(test.solution(8, 1)));
        System.out.println(Arrays.toString(test.solution(24, 24)));
    }

    static class Solution {
        public int[] solution(int brown, int yellow) {
            int s = (brown + 4) / 2;
            int p = yellow + brown;
            return new int[]{(int) (s + Math.sqrt(s * s - 4 * p)) / 2, (int) (s - Math.sqrt(s * s - 4 * p)) / 2};
        }
    }
}
