package pg;

import java.util.HashMap;

public class PG42578 {
    /* Level 2. 위장
    해쉬
    */
    public static void main(String[] args) {
        PG42578.Solution test = new PG42578.Solution();
        System.out.println(test.solution(new String[][] {
                {"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}
        }));
        System.out.println(test.solution(new String[][] {
                {"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}
        }));
    }

    static class Solution {
        public int solution(String[][] clothes) {
            int answer = 1;
            HashMap<String, Integer> map = new HashMap<>();
            for (String[] cloth:clothes) {
                map.put(cloth[1], map.getOrDefault(cloth[1], 1)+1);
            }

            for (int n: map.values())
                answer *= n;
            return answer - 1;
        }
    }
}
