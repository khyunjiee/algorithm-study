package pg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class PG64065 {
    /* Level 2. 튜플
    튜플 원소의 index = 전체 원소 개수 - 각 집합에서 존재하는 해당 원소 개수 => 해쉬
     */
    public static void main(String[] args) {
        Solution test = new Solution();
        System.out.println(Arrays.toString(test.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")));
        System.out.println(Arrays.toString(test.solution("{{1,2,3},{2,1},{1,2,4,3},{2}}")));
        System.out.println(Arrays.toString(test.solution("{{20,111},{111}}")));
        System.out.println(Arrays.toString(test.solution("{{123}}")));
        System.out.println(Arrays.toString(test.solution("{{4,2,3},{3},{2,3,4,1},{2,3}}")));
    }

    static class Solution {
        public int[] solution(String s) {
            HashMap<Integer, Integer> map = new HashMap<>();
            s = s.replaceAll("\\{","");
            s = s.replaceAll("\\}","");
            s = s.replaceAll("\\,"," ");
            StringTokenizer st = new StringTokenizer(s);
            int temp;
            while (st.hasMoreElements()) {
                temp = Integer.parseInt(st.nextToken());
                map.put(temp, map.getOrDefault(temp, 0) + 1);
            }
            int[] answer = new int[map.size()];
            for (Map.Entry<Integer, Integer> entry:map.entrySet()) {
                answer[map.size()-entry.getValue()] = entry.getKey();
            }
            return answer;
        }
    }
}
