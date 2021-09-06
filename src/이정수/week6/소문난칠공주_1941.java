package com.sssafy.algo.study.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 소문난칠공주_1941 {
	/*
	 * 접근: 
	 * 7개의 자리를 선택하는 모두 경우마다 연속여부와 이다솜파 학생수를
	 * 체크해야합니다.
	 * 
	 * 시간복잡도:
	 * 25개의 자리중 7자리를 선택하는 경우의 수는
	 * 25_C_7 = 480,700 가지
	 * 각 경우마다 dfs로 인접여부를 확인하고 이다솜파 학생이 4명이상
	 * 임을 확인하려면 자리방문을 7번 반복해야합니다.
	 * 480,700 * 7 = 3,364,900 번 반복하므로 시간초과가 일어나지 않습니다.
	 */
	
	static char[][] map, newMap;
	static int[] numbers = new int[7]; // 선택된 자리 번호 담는 배열
	static int[] dr = {1,0,-1,0}; // 하 우 상 좌
	static int[] dc = {0,1,0,-1};
	static boolean[] visited; // 방문 여부 표시 배열
	static int sevenCnt=0; // 소문난 칠공주 결성 경우의 수 카운트 변수
	static int visitCnt=0; // 방문한 자리 수
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// 맵 생성
		map = new char[5][]; 
		for (int i = 0; i < 5; i++) map[i] = in.readLine().toCharArray();
	
		
		combination(0,0); // 조합 생성
		
		System.out.println(sevenCnt);
	}
	
	static void combination(int cnt, int start) {
		if(cnt==7) {
			// 체크를 위한 새로운 맵 생성
			if(createNewMap()<4) return; // 선택된  7자리중 이다솜파 4명 미만이면 X
			visited = new boolean[25]; // 방문 여부 체크 배열 초기화
			visitCnt =0; // 방문한 자리 카운트 초기화
			dfs(numbers[0]/5, numbers[0]%5); // 선택된 자리중 하나를 시작으로 dfs 완탐
			if(visitCnt==7) {
				sevenCnt++; // 연속적으로 7자리가 이어진 경우
			}
			return;
		}
		for (int i = start; i < 25; i++) {
			numbers[cnt] = i;
			combination(cnt+1, i+1);
		}
	}

	private static void dfs(int i, int j) {
		visited[(i*5)+j] = true; // 방문 여부 체크
		visitCnt++; // 방문한 자리수 카운트
		for (int k = 0; k < 4; k++) { //4방 탐색
			int nr = i + dr[k];
			int nc = j + dc[k];
			if(nr>=0 && nc>=0 && nr<5 && nc<5 && newMap[nr][nc] != 0 && !visited[(nr*5)+nc]) {
				dfs(nr,nc);
			}
		}
	}

	// 선택된 7자리들만 표시되는 맵 생성 메소드
	private static int createNewMap() {
		newMap = new char[5][5];
		int sCnt=0; // 이다솜파 학생 카운트 변수
		for(int num:numbers) {
			int i = num / 5;
			int j = num % 5;
			char student = map[i][j];
			newMap[i][j] = student;
			if(student=='S') sCnt++; // 이다솜파 학생 수 증가
		}
		return sCnt; // 선택된 7자리중 이다솜파 학생이 포함된 수 리턴
	}
}
