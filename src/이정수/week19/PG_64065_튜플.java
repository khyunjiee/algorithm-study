package com.ssafy.algo.study.week19;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * 문제:튜플
 * 링크:https://programmers.co.kr/learn/courses/30/lessons/64065
 * 
 * 접근:
 * 1. 파싱하여 정수형 이차원 배열로 변경
 * 2. 1차원 배열을 크기순서로 오름차순 배열
 * 3. 첫번째 인덱스부터 차례대로 확인하여 집합에 새로 추가된 숫자를 답안 배열에 추가
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 소요 시간:
 * 1h
 * 
 */
public class PG_64065_튜플 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")));
	}
	
	static public int[] solution(String s) {
		
		String trimedS = s.substring(2, s.length()-2); // 양끝의 {{ 와 }} 제거
		String[] numbers = trimedS.split("},\\{");		// },{ 를 기준으로 split
		
		int[][] sets = new int[numbers.length][];
		
		// 정수형 2차원 배열로 변경
		for (int i = 0; i < sets.length; i++) {
			sets[i] =  Arrays.stream(numbers[i].split(",")).mapToInt(Integer::parseInt).toArray(); 
		}
		
		// 1차원 배열의 크기순으로 정렬
		Arrays.sort(sets, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1.length - o2.length;
			}
		});
		
		// 이미 확인한 숫자를 관리하기 위한 집합
		Set<Integer> numberSet = new HashSet<>();
		
		int[] answer = new int[sets.length]; // 답안 배열
		int idx =0;
		
		// 크기가 작은 배열부터 순서대로 확인하며 새로 나타나는 숫자를 답안 배열에 추가
		for (int[] set : sets) {
			for (int number : set) {
				if(!numberSet.contains(number)) {
					answer[idx++] = number;
					numberSet.add(number);
				}
			}
		}
		
        
        return answer;
    }

}
