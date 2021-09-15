package com.ssafy.algo.study.week7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 순위검색_72412 {
	/*
	 * 접근:
	 * 만들어질 수 있는 모든 경우의 수를 해쉬맵으로 만듭니다.
	 * 
	 * 
	 * 
	 * 
	 */

	public static void main(String[] args) {
		
//		System.out.println(Arrays.toString(solution(new String[] {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"},
//				new String[] {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"})));
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(5);
		list.add(7);
		list.add(8);
		
		System.out.println(list.toString());
		
		System.out.println(countPeople(list, 3));
//		
	}
	
	
	public static int[] solution(String[] info, String[] query) {
		// 모든 경우의 수를 고려한 해쉬맵 생성
		Map<String, List<Integer>> map = new HashMap<>();
		createMap(map);
		
		// info값을 해쉬맵에 넣기
		for (String inf : info) addToMap(inf, map);
		
		// 오름차순 정렬
		for(String key:map.keySet()) map.get(key).sort((a, b) -> a-b);
		
		// query 수행
		int[] result = new int[query.length];
		int idx=0;
		for(String key: query) {
			key = key.replace("and", "");
			String[] vals = key.split(" ");
			key = "";
			for (int i = 0; i < vals.length-1; i++) key += vals[i];
			int score = Integer.parseInt(vals[vals.length-1]);
			
			List<Integer> scores = map.get(key);
			// 이분탐색
			result[idx++] = countPeople(scores, score);
		}
		return result;
	}


	private static int countPeople(List<Integer> scores, int score) {
		int left = 0;
		int right = scores.size()-1;
		
		while(left<=right) {
			int mid = (left + right) / 2;
			if(score>scores.get(mid)) left = mid+1;
			else right = mid-1;
		}
		return scores.size() - left;
	}


	private static void addToMap(String inf, Map<String, List<Integer>> map) {
		String[] vals = inf.split(" ");
		String[] lang = {vals[0], "-"};
		String[] job = {vals[1], "-"};
		String[] career = {vals[2], "-"};
		String[] soulfood = {vals[3], "-"};
		int score = Integer.parseInt(vals[4]);
		
		for (int i = 0; i < lang.length; i++) {
			for (int j = 0; j < job.length; j++) {
				for (int x = 0; x < career.length; x++) {
					for (int y = 0; y < soulfood.length; y++) {
						String key = lang[i] + job[j] + career[x] + soulfood[y];
						map.get(key).add(score);
					}
				}
			}
		}
	}


	private static void createMap(Map<String, List<Integer>> map) {
		String[] lang = {"cpp", "java", "python", "-"};
		String[] job = {"backend", "frontend", "-"};
		String[] career = {"junior", "senior", "-"};
		String[] soulfood = {"chicken", "pizza", "-"};
		
		for (int i = 0; i < lang.length; i++) {
			for (int j = 0; j < job.length; j++) {
				for (int x = 0; x < career.length; x++) {
					for (int y = 0; y < soulfood.length; y++) {
						String key = lang[i] + job[j] + career[x] + soulfood[y];
						map.put(key, new ArrayList<Integer>());
					}
				}
			}
		}
	}
}
