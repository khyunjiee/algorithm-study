package programmers;

import java.util.HashMap;
import java.util.Map;

// 위장
public class Programmers_42578 {
	
	public static void main(String[] args) {
		String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
		System.out.println(solution(clothes));
	}
	
	public static int solution(String[][] clothes) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(String[] arr : clothes) {
			map.put(arr[1], map.getOrDefault(arr[1], 0)+1);
		}
        int answer = 1;
        for(String key : map.keySet()) {
        	answer *= map.get(key)+1;
        }
        return answer-1;
    }
}