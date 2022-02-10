package com.ssafy.algo.study.week22;

import java.util.HashMap;
import java.util.Map;

/**
 * 문제: 뉴스 클러스터링
 * 링크: https://programmers.co.kr/learn/courses/30/lessons/17677
 * 
 * 접근:
 * 1. 모든 문자 대문자로 변경
 * 2. 다중 집합 생성(맵 자료구조로 관리)
 * 3. 교집합과 합집합의 원소 개수 카운트
 * 4. 답 도출
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 소요 시간:
 * 
 */
public class PG_17677_뉴스클러스터링 {
	
	public static void main(String[] args) {
		System.out.println(solution("handshake", "shake hands"));
	}
	
	static public int solution(String str1, String str2) {
		
		Map<String, Integer> str1Map = new HashMap<>();
		Map<String, Integer> str2Map = new HashMap<>();
		
		// 대문자로 변경
		str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();
		
		// 다중집합 만들기
		for (int i = 0; i < str1.length()-1; i++) {
			// 두 글자 모두 알파벳이면
			if(str1.charAt(i)>='A' && str1.charAt(i)<='Z' && str1.charAt(i+1)>='A' && str1.charAt(i+1)<='Z') {
				String subString = str1.substring(i,i+2);
				if(str1Map.containsKey(subString)) {
					str1Map.put(subString, str1Map.get(subString)+1);
				}else {
					str1Map.put(subString, 1);
				}
			}
		}
		
		for (int i = 0; i < str2.length()-1; i++) {
			// 두 글자 모두 알파벳이면
			if(str2.charAt(i)>='A' && str2.charAt(i)<='Z' && str2.charAt(i+1)>='A' && str2.charAt(i+1)<='Z') {
				String subString = str2.substring(i,i+2);
				if(str2Map.containsKey(subString)) {
					str2Map.put(subString, str2Map.get(subString)+1);
				}else {
					str2Map.put(subString, 1);
				}
			}
		}
		
		// 둘다 공집합인 경우
		if(str1Map.size()==0 && str2Map.size()==0) {
			return 65536;
		}
		
		// 교집합
		int intersectionCnt = 0;
		for (String key: str1Map.keySet()) {
			if(str2Map.containsKey(key)) {
				intersectionCnt += Math.min(str1Map.get(key), str2Map.get(key));
			}
		}
		
		// 합집합
		int unionCnt = 0;
		for (String key: str1Map.keySet()) {
			if(str2Map.containsKey(key)) {
				unionCnt += Math.max(str1Map.get(key), str2Map.get(key));
			}else {
				unionCnt += str1Map.get(key);
			}
		}
		
		for (String key: str2Map.keySet()) {
			if(!str1Map.containsKey(key)) {
				unionCnt += str2Map.get(key);
			}
		}
		
		double similarity = ((double)intersectionCnt)/unionCnt; // 유사도 계산
        int answer = (int) Math.floor(similarity*65536);
        return answer;
    }

}
