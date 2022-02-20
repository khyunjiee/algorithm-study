package pg;

import java.util.Arrays;
import java.util.stream.Stream;

public class PG43238 {
    /* Level 3. 입국심사
    이분탐색 -> 심사를 받는데 걸리는 시간이 기준 -> 전체 걸리는 시간을 각 심사관이 심사를 하는데 걸리는
    시간으로 나누고 그 값들을 합하여 기다리는 사람 수보다 많은지를 확인
    */
    public static void main(String[] args) {
        PG43238.Solution test = new PG43238.Solution();
        System.out.println(test.solution(6, new int[] {7, 10}));
        System.out.println(test.solution(6, new int[] {3, 4, 5}));
    }

    static class Solution {
        public long solution(int n, int[] times) {
            long lef = 0;
            long rig = (long) Arrays.stream(times).max().getAsInt() * n;
            long mid = 0;
            long m = 0;

            while (lef + 1 != rig) {
                mid = (lef + rig) / 2;
                m = 0;
                for (int time : times) {
                    m += mid / time;
                }
                if (m < n) {
                    lef = mid;
                } else {
                    rig = mid;
                }
            }
            return rig;
        }
    }
}
