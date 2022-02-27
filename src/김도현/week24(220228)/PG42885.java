package pg;

import java.util.Arrays;

public class PG42885 {
    /* Level 2. 구명보트
    투포인터
    */
    public static void main(String[] args) {
        PG42885.Solution test = new PG42885.Solution();
        System.out.println(test.solution(new int[] {70, 50, 80, 50}, 100));
        System.out.println(test.solution(new int[] {70, 80, 50}, 100));
    }

    static class Solution {
        public int solution(int[] people, int limit) {
            int answer = 0;
            int lef = 0, rig = people.length - 1;
            Arrays.sort(people);
            while (lef <= rig) {
                if (people[lef] > limit / 2) {
                    answer += (rig - lef + 1);
                    break;
                }
                if (people[lef] + people[rig] <= limit) lef += 1;
                rig -= 1;
                answer += 1;
            }
            return answer;
        }
    }
}
