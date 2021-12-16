package com.ssafy.algo.study.week16;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PG_42888 {
	/*
	 * 문제: 오픈채팅방
	 * 링크: https://programmers.co.kr/learn/courses/30/lessons/42888?language=java
	 * 
	 * 접근:
	 * 1. userId, nickname을 keyvalue로 하는 맵 생성
	 * 2. 입력이 들어올 때마다 맵 업데이트
	 * 3. 모든 입력을 userId + 멘트로 이루어진 문자열 배열로 변경
	 * 4. 다시 배열을 돌면서 userId를 맵에 저장된 nickname값으로 변경
	 * 
	 * 시간복잡도:
	 * 
	 * 
	 * 공간복잡도:
	 * 
	 * 
	 * 풀이에 걸린 시간:
	 * 20m
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"})));
	}
	
	static Map<String, String> map = new HashMap<>(); // userId, nickname을 키-값으로 하는 배열
	
	static public String[] solution(String[] record) {
		
		 List<String> logList = new LinkedList<>(); // 로그 저장 리스트
		 
		for (String log : record) {
			
			updateMap(log); // 로그 기록을 통해 map 업데이트
			String result = makeResult(log); // 로그 기록으로 최종적으로 나와야할 문자생성(nickname 대신 userId 출력)
			if(result!=null) { // change인 경우 없음
				logList.add(result);
			}
			
		}
		
		
		// userId를 nickname으로 변경
		String[] answer = new String[logList.size()];
		int idx = 0;
		for (String string : logList) { 
			String userId = string.substring(0, string.indexOf("님"));
			answer[idx++] = string.replace(userId, map.get(userId));
		}
       
        return answer;
    }

	private static String makeResult(String log) {
		String[] inputs = log.split(" ");
		
		if(inputs[0].equals("Enter")) {
			return inputs[1] + "님이 들어왔습니다.";
		}else if(inputs[0].equals("Leave")) {
			return inputs[1] + "님이 나갔습니다.";
		}
		
		return null;
	}

	private static void updateMap(String log) {
		String[] inputs = log.split(" ");
		
		if(inputs[0].equals("Enter") ||inputs[0].equals("Change")) {
			if(map.containsKey(inputs[1])) { // 이미 존재하는 값이면
				map.replace(inputs[1], inputs[2]);
			}else { // 처음 들어온 값이면
				map.put(inputs[1],inputs[2]);
			}
		}
	}

}
