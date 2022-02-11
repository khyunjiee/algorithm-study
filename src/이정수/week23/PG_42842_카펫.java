package com.ssafy.algo.study.week23;

import java.util.Arrays;

/**
 * 문제: 카펫
 * 링크: https://programmers.co.kr/learn/courses/30/lessons/42842/solution_groups?language=java
 * 
 * 접근:
 * 브루트 포스
 * 세로 길이를 1부터 차례대로 시도
 * brown = (가로 * 세로) *2 -4 
 * yellow = (가로 - 2) * (세로 -2)
 * 두가지 조건을 만족하는 가로세로 길이가 답
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 소요 시간:
 * 20m
 * 
 */
public class PG_42842_카펫 {
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(24,24)));
	}

	public static int[] solution(int brown, int yellow) {
		int[] answer = null;
		for (int row = 1; row <= (brown + yellow)/row; row++) {
			
			if((brown + yellow)%row==0) { // 나누어 떨어지면
				
				int col = (brown + yellow)/row; // 가로 길이
				
				if(brown == ((row + col) *2 -4) && yellow == (row-2) * (col-2)) {
					answer = new int[] {col, row};
				}
				
			}
			
		}
        
        return answer;
    }
	
}
