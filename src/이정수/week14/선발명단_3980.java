package com.ssafy.algo.study.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 선발명단_3980 {
	/*
	 * 접근:
	 * 1. 첫번째 포지션부터 차례대로 능력치가 0이 아닌 선수를 선택해나가면서 dfs로 해결
	 * 2. 특정 포지션에 가용가능한 선수가 없는 경우 가지치기
	 * 
	 * 시간복잡도:
	 * 11^11 보다 작습니다.
	 * 
	 * 풀이에 걸린 시간:
	 * 20min
	 * 
	 */
	
	static int[][] S; 			// 선수별 포지션 적합도
	static boolean[] used;		// 이미 포지션이 정해진 선수 여부 표시 배열
	static int max;	// 능력치 합 최대값
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			// 변수 초기화
			max=0;
			S = new int[11][11];
			used = new boolean[11];
			
			// 선수별 포지션 능력치 값 입력
			for (int i = 0; i < 11; i++) {
				st = new StringTokenizer(in.readLine());
				for (int j = 0; j < 11; j++)
					S[i][j] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0,0); // dfs 수행
			
			System.out.println(max);
		}
	}
	private static void dfs(int position, int total) {
		if(position==11) { // 11명 모두 포지션이 정해진 경우
			max = Math.max(max, total);
			return;
		}
		
		for (int i = 0; i < S.length; i++) { // 모든 선수들을 확인
			if(used[i]) continue; // 이미 포지션이 정해진 선수는 스킵
			if(S[i][position]>0) { // 해당 선수가 포지션에서 능력치가 0 이상이면
				used[i] = true;		// 포지션 할당 여부 표시
				dfs(position, total+S[i][position]);
			}
			used[i] = false;
		}
	}
	
}
