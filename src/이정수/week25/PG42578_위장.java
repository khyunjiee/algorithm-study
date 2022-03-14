package week25;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PG42578_¿ß¿Â {

	public static void main(String[] args) {
		System.out.println(solution(new String[][] {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}}));
	}
	
	static public int solution(String[][] clothes) {
		
		Map<String, Integer> map = new HashMap<>();
		
		for (String[] clothe : clothes) {
			String name =  clothe[0];
			String type =  clothe[1];
			if(map.containsKey(type)) {
				map.put(type, map.get(type)+1);
			}else {
				map.put(type, 1);
			}
		}
		
		int answer = 1;
		
		for (Entry<String, Integer> entry : map.entrySet()) {
			answer *= (entry.getValue() + 1);
		}
		
        return --answer;
    }

}
