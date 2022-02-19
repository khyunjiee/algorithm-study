package com.ssafy.algo.study.week23;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 문제: 달이 차오른다, 가자.
 * 링크: https://www.acmicpc.net/problem/1194
 * 
 * 접근:
 * 1. 현재 소지하고 있는 열쇠를 비트마스킹으로 표현하여 가지고 있는 열쇠 조합에 따라 방문 여부 배열을 3차원으로 구성
 * map[현재가지고 있는 열쇠조합의 비트마스크][행][열]
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 소요 시간:
 * 30m
 * 
 */
public class BJ_1194_달이차오른다가자 {

	static int N,M;
	static boolean[][][] visited;
	static char[][] map;
	static int[][] delta = {{0,0,-1,1,},{-1,1,0,0}}; // 좌 우 상 하
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 행
		M = sc.nextInt(); // 열
		
		map = new char[N][M]; // 맵
		visited = new boolean[1<<6][N][M]; // 가지고 있는 열쇠조합에 따른 방문여부 표시 2차원 배열
		 
		int row = -1;
		int col = -1;
		
		for (int i = 0; i < N; i++) {
			map[i] = sc.next().toCharArray();
			
			for (int j = 0; j < M; j++) {
				if(map[i][j]=='0') {
					row  = i;
					col  = j;
					map[i][j]='.';
				}
			}
		}
		
		int result =bfs(row, col);
		
		System.out.println(result);
	}

	private static int bfs(int y, int x) {
		Queue<int[]> queue = new LinkedList<>();
		
		queue.add(new int[] {y, x, 0});
		int cnt = 0; // 이동 횟수
		
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			
			while(--size>=0) {
				
				int[] currentState = queue.poll();
				
				int row = currentState[0];
				int col = currentState[1];
				int keyState = currentState[2];
				
				// 탈출 조건
				if(map[row][col]=='1') return cnt;
				
				// 열쇠를 획득한 경우
				if(map[row][col]>='a' && map[row][col]<='f') {
					keyState = keyState | (1<< (map[row][col] - 'a'));
				}
				
				for (int d = 0; d < 4; d++) {
					
					int newRow = row + delta[0][d];
					int newCol = col + delta[1][d];
					
					if(isValid(newRow, newCol, keyState)) {
						
						// 다음 위치가 문이면서 문에 해당하는 키가 없으면 이동 불가능
						if(map[newRow][newCol]>='A' && map[newRow][newCol]<='F' 
								&& (keyState & 1<<(map[newRow][newCol]-'A'))==0) {
							continue;
						}
						
						visited[keyState][newRow][newCol] = true; // 방문 처리
						queue.add(new int[] {newRow, newCol, keyState});
					}
					
				}
			}
			cnt++;
		}
		
		return -1;
	}
	
	// 다음 위치로 이동가능한지 확인
	private static boolean isValid(int newRow, int newCol, int keyState) {
		return newRow>=0 && newRow<N && newCol>=0 && newCol<M && !visited[keyState][newRow][newCol] && map[newRow][newCol]!='#';
	}

}
