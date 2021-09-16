package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 순위 검색
/*
 * map을 이용하고 이분탐색을 이용하여 해결.
 * 한참을 고민하다가 pq배열을 만들어서 해결해보려했으나 실패.
 * 너무 답이 안 떠올라 솔직히 구선생의 도움을 좀 많이 받았다 ㅠㅠ
 * 
 * 후기: 일단 어렵다.... level2가 이정도라니... 아직 나는 갈 길이 멀구나 라는것을 절실히 느꼈다.
 */
public class Programmers_72412 {

	Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
	boolean[] isUsed = new boolean[4];
	
	public int[] solution(String[] info, String[] query) {
		int n = info.length;
		for(int i=0; i<n; ++i) {
			String[] strArr = info[i].split(" ");
			makeKeys(0,strArr);
		}
		
		for(String key : map.keySet()) {
			Collections.sort(map.get(key)); // 모든 리스트들을 정렬 -> 나중에 점수 이분탐색을 위해서
		}
		
		int m = query.length;
        int[] answer = new int[m];
        for(int i=0; i<m; ++i) {
        	String q = query[i];
        	q = q.replace(" and ", ""); // 공백과 and를 없애고 "키값 점수" 형식으로 남기기
        	String key = q.split(" ")[0]; // 키값
        	int score = Integer.parseInt(q.split(" ")[1]); // 점수
        	if(map.get(key)==null) { // 점수가 한 번도 추가되지 않았으면 그냥 0 추가하고 pass
        		answer[i] = 0; 
        		continue;
        	}
        	List<Integer> scores = map.get(key);
			int start = 0;
			int end = scores.size();
			while (start < end) {
				int mid = (start + end) / 2;
				if(scores.get(mid) < score) start = mid + 1;
				else end = mid;
			}
			answer[i] = scores.size() - start;
        }
        return answer;
	}
	
    public void makeKeys(int n, String[] strArr) {
    	if(n==4) { // 4개 다 선택여부 확인하면
    		String str = "";
    		for(int i=0; i<4; ++i) {
    			str += isUsed[i] ? strArr[i] : "-"; // 사용하면 선택지 붙이고 사용 안하면 "-"붙이기
    		}
    		if(map.get(str)==null) map.put(str, new ArrayList<Integer>()); // 아직 한번도 점수를 추가안했으면 새로 ArrayList 생성 후 추가
			map.get(str).add(Integer.parseInt(strArr[4])); // 점수를 int형으로 변환해서 list에 담기
			return;
    	}
    	
    	isUsed[n]=true;
    	makeKeys(n+1, strArr);
    	isUsed[n]=false;
    	makeKeys(n+1, strArr);
    }
}