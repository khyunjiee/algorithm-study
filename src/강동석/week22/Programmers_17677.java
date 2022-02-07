package programmers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// [1차] 뉴스 클러스터링
/*
 * 문자를 나눈 후 갯수를 저장하기 위해 HashMap을 이용한다.
 * 문자로만 이루어져 있는지 체크한 후 교집합과 합집합의 수를 key값을 통해 구하고,
 * 원하는 결과값을 계산한다.
 */
public class Programmers_17677 {

	public static void main(String[] args) {
		String str1 = "AB";
		String str2 = "CD";
		System.out.println(solution(str1, str2));
	}

	public static int solution(String str1, String str2) {
		str1 = str1.toLowerCase(); // 소문자 변환
		str2 = str2.toLowerCase();
		
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		Map<String, Integer> map2 = new HashMap<String, Integer>();
		
		for(int i=0,len=str1.length(); i<len-1; ++i) {
			String substr1 = str1.substring(i, i+2);
			if(!isAlphabet(substr1)) continue; // 영문자로만 되어있는지 체크
			map1.put(substr1, map1.getOrDefault(substr1, 0)+1);
		}
		
		for(int i=0,len=str2.length(); i<len-1; ++i) {
			String substr2 = str2.substring(i, i+2);
			if(!isAlphabet(substr2)) continue; // 영문자로만 되어있는지 체크
			map2.put(substr2, map2.getOrDefault(substr2, 0)+1);
		}
		
		Set<String> keySet1 = map1.keySet();
		Set<String> keySet2 = map2.keySet();
		
		int commonCnt = 0; // 교집합의 크기
		for(String key : keySet1) {
			commonCnt += Math.min(map1.get(key), map2.getOrDefault(key, 0));
		}
		
		int totalCnt = 0; // 합집합의 크기
		for(String key : keySet1) {
			totalCnt += map1.get(key); // 집합1의 원소 수 다 더하기
		}
		for(String key : keySet2) {
			totalCnt += map2.get(key); // 집합2의 원소 수 다 더하기
		}
		if(totalCnt==0) return 65536; // 둘 다 공집합이면 1로 처리
		totalCnt -= commonCnt; // 교집합의 수만큼 뺴주기
		
        int answer = (int)((double)commonCnt/totalCnt*65536);
        return answer;
    }
	
	public static boolean isAlphabet(String str) {
		return str.charAt(0) >= 'a' && str.charAt(0) <= 'z' && str.charAt(1) >= 'a' && str.charAt(1) <= 'z';
	}
}