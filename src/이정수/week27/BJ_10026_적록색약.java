package week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제: 적록색약
 * 링크: https://www.acmicpc.net/problem/10026
 * 
 * 풀이:
 * dfs를 두번 사용하여 연속한 구역 찾기
 * 적록색약의 경우 초록색과 빨간색을 같은 색으로 간주
 * 
 * 
 * 시간복잡도:
 * O(N^2)
 * 
 * 풀이에 걸린 시간:
 * 30m
 *
 */
public class BJ_10026_적록색약 {
	
	static int[][] delta = {{0,0,-1,1},{-1,1,0,0}};
	static boolean[][] visited;
	static int N, normalCnt, colorBlindCnt;
	static char[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		map = new char[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		// 정상 시력 카운트
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					dfs(new char[] {map[i][j]}, i,j);
					normalCnt++;
				}
			}
		}
		
		
		// 적록색약 카운트
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					if(map[i][j]=='B') {
						dfs(new char[] {map[i][j]}, i,j);
					}else {
						dfs(new char[] {'R','G'}, i,j);
					}
					colorBlindCnt++;
				}
			}
		}
		
		System.out.println(normalCnt + " " + colorBlindCnt);
		
		
		
	}

	private static void dfs(char[] candidates, int row, int col) {
		
		visited[row][col] = true;
		
		for (int d = 0; d < 4; d++) {
			int newRow = row + delta[0][d];
			int newCol = col + delta[1][d];
			
			// 유효한 자리가 아니면 스킵
			if(!(newRow>=0 && newCol>=0 && newRow<N && newCol<N && !visited[newRow][newCol])) {
				continue;
			}
			
			// 찾는 색이 아니면 스킵
			if(candidates.length==2 && map[newRow][newCol]=='B') {
				continue;
			}else if(candidates.length==1 && candidates[0]!=map[newRow][newCol]){
				continue;
			}
			
			dfs(candidates, newRow, newCol);
		}
		
	}

}
