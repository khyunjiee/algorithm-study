package src.프로그래머스.해시.위장;

import java.util.*;

/**
 * date: 22.03.02
 * sol)
 *
 * 만약 eye 3개, 옷 4개 있다고 치면 해당 장신구를 안 착용할 경우의 수까지 해서 4*5 = 20 전체 경우의 수
 * 이 때 모두 안입는 경우 1가지를 빼야 하므로 cnt-1
 */

public class MainV2 {
    public static void main(String[] args) {

    }

    static class Solution {
        public int solution(String[][] clothes) {
            Map<String, Integer> map = new HashMap<>();
            for(String [] c : clothes){
                map.put(c[1],map.getOrDefault(c[1],0)+1);
            }
            int cnt = 1;
            Set<String> keys = map.keySet();
            for(String s : keys){
                cnt *= map.get(s)+1;
            }
            return cnt-1;
        }
    }
}
