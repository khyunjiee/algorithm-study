package com.ssafy.algo.study.week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20058 {
	/*
	 * 문제: 마법사 상어와 파이어볼
	 * 링크: https://www.acmicpc.net/problem/20058
	 * 
	 * 접근:
	 * 1. 부분격자는 이중 for문으로 나누기
	 * 2. 부분 격자의 왼쪽 상단 격자와, 오른쪽 하단 격자의 위치를 넣으면 90도 회전하는 메소드 구성
	 * 3. 나머지 작동은 메소드로 하나씩 분리
	 * 
	 * 
	 * 시간복잡도:
	 * ?
	 * 
	 * 공간복잡도:
	 * 2^N * 2^N
	 * 
	 * 풀이에 걸린 시간:
	 * 1h
	 * 
	 */
	
	static int[][] map;	// 얼음판
	static int twoPowN; // 2^N
	static boolean[][] visited; // 방문여부 표시(dfs)
	static int cnt;				// 연속된 얼음수 카운트
	static int[][] delta = {{-1,1,0,0},{0,0,-1,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		twoPowN = (int)Math.pow(2, N);
		
		map = new int[twoPowN][twoPowN];
		
		for (int i = 0; i < twoPowN; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < twoPowN; j++) 
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < Q; i++) {
			launchFireStorm(Integer.parseInt(st.nextToken())); // Q 번 파이어 스톰 시전
		}
		
		int totalIce = sumIce(); 			// 남아있는 얼음의 양 구하기
		int biggestIce = findBiggiestIce();	// 남아있는 얼음중 가장 큰 덩어리의 크기 구하기
		
		System.out.println(totalIce);
		System.out.println(biggestIce);
	}
	
	// 가장 큰 얼음의 크기 구하는 메소드
	private static int findBiggiestIce() {
		
		int biggiestIce = 0;
		visited = new boolean[twoPowN][twoPowN];
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(visited[i][j] || map[i][j]==0) continue;
				cnt = 0;
				dfs(i,j);
				biggiestIce = Math.max(biggiestIce, cnt);
			}
		}
		
		return biggiestIce;
	}
	
	// dfs 탐색
	private static void dfs(int i, int j) {
		cnt++;
		visited[i][j] = true;
		for (int d = 0; d < 4; d++) {
			int newRow = i + delta[0][d];
			int newCol = j + delta[1][d];
			if(isValid(newRow, newCol)&& !visited[newRow][newCol] && map[newRow][newCol]>0) { // 인접한 곳에 얼음이 있는 경우 재귀 호출
				dfs(newRow, newCol);
			}
		}
	}
	
	// 남은 모든 얼음의 양 더하는 메소드
	private static int sumIce() {
		int totalIce = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				totalIce += map[i][j];
			}
		}
		return totalIce;
	}

	
	// 파이어 스톰 시전
	private static void launchFireStorm(int L) {
		int twoPowL = (int)Math.pow(2, L);
		
		// 부분 격자 별로 90도 시계방향으로 돌리기
		for (int i = 0; i < twoPowN; i+= twoPowL) {
			for (int j = 0; j < twoPowN; j+= twoPowL) {
				rotate90(i, j, i+twoPowL, j+twoPowL, twoPowL);
			}
		}
		
		meltIce(); // 남은 얼음 규칙에 따라 녹이기
	}
	
	// 얼음 녹이기
	private static void meltIce() {
		
		int[][] temp = new int[twoPowN][twoPowN]; // 임시 배열
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j]==0) continue; // 얼음이 없으면 스킵
				int cnt =0;
				for (int d = 0; d < 4; d++) {
					int newRow = i + delta[0][d];
					int newCol = j + delta[1][d];
					if(isValid(newRow, newCol)) {
						if(map[newRow][newCol]>0) cnt++;
					}
				}
				
				if(cnt<3) { // 주변에 얼음의 수가 3개보다 적으면
					temp[i][j] = map[i][j]-1;
				}else { // 주변에 얼음이 3개 이상이면
					temp[i][j] = map[i][j];
				}
				
			}
		}
		
		map = temp;
	}

	private static boolean isValid(int newRow, int newCol) {
		return newRow>=0 && newCol>=0 && newRow<twoPowN && newCol<twoPowN;
	}
	
	
	// 부분 격자 시계방향으로 90도 회전
	private static void rotate90(int row1, int col1, int row2, int col2, int twoPowL) {
		
		int[][] temp  = new int[twoPowL][twoPowL];
		
		// 시계 방향 90도 회전
		for (int i = row1; i < row2; i++) {
			for (int j = col1; j < col2; j++) {
				temp[j-col1][twoPowL-1-(i-row1)] = map[i][j];
			}
		}
		
		// 맵에 쓰기
		for (int i = row1; i < row2; i++) {
			for (int j = col1; j < col2; j++) {
				map[i][j] = temp[i-row1][j-col1];
			}
		}
		
	}

//	private static void printMap(int[][] temp) {
//		for (int i = 0; i < temp.length; i++) {
//			for (int j = 0; j < temp.length; j++) {
//				System.out.print(temp[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//	}
//	
}
