package programmers.oct.oct27;

import java.util.*;
import java.util.stream.Collectors;

/*
프로그래머스 Lv3 보석 쇼핑

접근 방식:
투 포인터로 접근했습니다.
보석의 종류를 얻기 위해 set을 사용했고, 각 보석의 개수를 저장하기 위헤 map을 사용했습니다.
보석 개수가 1 초과라면 index를 +1 하여 시작 진열대를 계산합니다.
그 후 map과 set의 크기가 같으면 모든 보석을 볼 수 있는 것이므로 length 를 비교해 업데이트합니다

소요 시간:
2시간
 */

public class p67258 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[]{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"});
    }
}
class Solution {
    public int[] solution(String[] gems) {
        Set<String> set = Arrays.stream(gems).collect(Collectors.toSet());
        HashMap<String, Integer> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        int start = 0;                      // 쇼핑 시작 구간
        int index = 0;                      // 중
        int length = Integer.MAX_VALUE;     // 몇 개까지 봐야하는지

        for (int i = 0; i < gems.length; i++) {
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            queue.offer(gems[i]);

            while (true) {
                String temp = queue.peek();
                // 현재까지 temp 보석의 개수가 1개 초과라면 -1 후 index + 1
                if (map.get(temp) > 1) {
                    map.put(temp, map.get(temp) - 1);
                    queue.poll();
                    index++;
                } else {
                    break;
                }
            }

            // map에 모든 보석이 모두 들어갔고, length가 큐 크기보다 작으면 start, length 업데이트
            if (map.size() == set.size() && length > queue.size()) {
                length = queue.size();
                start = index;
            }
        }
        // start는 index이므로 start + 1 ~ start + length
        return new int[]{start + 1, start + length};
    }
}