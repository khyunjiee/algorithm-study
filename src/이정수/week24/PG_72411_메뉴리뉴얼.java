package week24;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * 문제: 메뉴 리뉴얼
 * 링크: https://programmers.co.kr/learn/courses/30/lessons/72411
 * 
 * 풀이:
 * 1. 가능한 모든 조합 생성
 * 2. 카운트 하여 가능한 코스 뽑아내기
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class PG_72411_메뉴리뉴얼 {

	public static void main(String[] args) {
		System.out.println(Arrays.deepToString(solution(new String[] {"XYZ", "XWY", "WXA"}, new int[] {2,3,4})));
	}
	
	
	static Map<String, Integer> map = new HashMap<>(); // 가능한 모든 코스요리 저장
	static List<String> temp, list = new LinkedList<>();
	
	static public String[] solution(String[] orders, int[] course) {
		
		// 가능한 모든 조합으로 코스요리 후보군 만들기
		for (String order : orders) {
			for (int numberOfMenu : course) {
				comb(order, 0, 0,  numberOfMenu, "");
			}
		}
		
		
		for (int numberOfMenu : course) {// 생성할 코스요리에 포함된 각 단품의 갯수마다
			
			temp = new LinkedList<>(); // 임시변수
			int max = 0; 
			
			for (Map.Entry<String, Integer> entry : map.entrySet()) { // 모든 코스요리 후보군으로 부터
				
				if(entry.getKey().length()==numberOfMenu) { // 현재 확인하려고하는 단품 개수에 해당하는 후보이면
					
					if(max<entry.getValue() && entry.getValue()>1) { // 지금까지 확인했던 후보코스들보다 빈도가 높고 1번이상 나왔으면
						temp = new LinkedList<>(); // temp 초기화 후 추가
						temp.add(entry.getKey());
						max = entry.getValue();
						
					}else if(max==entry.getValue()) { // 만약 가장 많이 함께 주문된 메뉴 구성이 여러 개라면, 모두 배열에 담기
						temp.add(entry.getKey());
					}
					
				}
				
			}
			
			// 뽑아낸 코스요리 결과리스트에 담기
			for (String i : temp) {
				list.add(i);
			}
		}
		
		// 배열로 변경
        String[] answer = new String[list.size()];
        for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}
        Arrays.sort(answer);
        return answer;
    }



	private static void comb(String order, int depth, int start, int numberOfMenu, String result) {
		
		if(depth==numberOfMenu) {
			result = sortAsc(result);
			if(!map.containsKey(result)) {
				map.put(result, 1);
			}else {
				map.replace(result, map.get(result)+1);
			}
			return;
		}
		
		for (int i = start; i < order.length(); i++) {
			comb(order, depth+1, i+1, numberOfMenu, result + order.charAt(i));
		}
		
	}
	
	// 문자열을 오름차순으로 변경
	static String sortAsc(String str) {
		char[] arr = str.toCharArray();
		Arrays.sort(arr);
		return new String(arr);
	}

}
