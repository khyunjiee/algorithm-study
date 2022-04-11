package week30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 문제: 벽 부수고 이동하기 4
 * 링크: https://www.acmicpc.net/problem/16946
 * 
 * 풀이:
 * 매번 벽을 부수고 탐색할 경우 같은 공간 탐색을 중복하여 비효율적
 * 미리 빈공간들을 탐색해서 묶음으로 분리하고 벽마다 인접한 빈공간 집합의 정보로 상수시간안에 이동가능한
 * 칸의 개수를 세기
 * 
 * 시간복잡도:
 * O(N^2)
 * 
 * 풀이에 걸린 시간:
 * 30m
 *
 */
public class BJ_16946_벽부수고이동하기4 {

	static int[][] map, emptySpaceRecord;
	static int emptyIdCnt,N,M;
	static int[] emptySpaceSize;
	static int[][] delta = {{0,0,1,-1},{1,-1,0,0}};
	static Set<Integer>checkSet;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		emptySpaceRecord = new int[N][M];
		emptySpaceSize = new int[1000000];
		
		for (int i = 0; i < N; i++) {
			String line = in.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		}
		
		// 빈 공간 미리 기록 및 그룹화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 빈공간이고 탐색한적이 없으면 dfs 탐색 시작
				if(map[i][j]==0 && emptySpaceRecord[i][j]==0) {
					dfs(i,j,++emptyIdCnt);
				}
			}
		}
		
		// 벽마다 벽에서 이동가능한 위치의 수 구하기
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j]==0) {
					sb.append(0);
				}else {
					checkSet = new HashSet<>(); // 인접 빈공간 집합 관리 세트
					int sum = 1;
					for (int d = 0; d < 4; d++) {
						int newRow = i + delta[0][d];
						int newCol = j + delta[1][d];
						
						if(isValid2(newRow, newCol)) {
							checkSet.add(emptySpaceRecord[newRow][newCol]); // 인접한 빈공간 집합의 id 저장
							sum += emptySpaceSize[emptySpaceRecord[newRow][newCol]]; // 빈공간의 크기 더하기
						}
					}
					sb.append(sum%10);
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static boolean isValid2(int newRow, int newCol) {
		// 맵 안에 있고 이미 확인 한 공간이 아닌 빈공간 집합이면 true 리턴
		return newRow>=0 && newCol>=0 && newRow<N && newCol<M 
				&& !checkSet.contains(emptySpaceRecord[newRow][newCol]) && map[newRow][newCol]==0; 
	}

	private static void dfs(int i, int j, int id) {
		
		emptySpaceRecord[i][j] = id;
		emptySpaceSize[id]++;
		
		for (int d = 0; d < 4; d++) {
			int newRow = i + delta[0][d];
			int newCol = j + delta[1][d];
			
			if(isValid(newRow, newCol)) {
				
				dfs(newRow, newCol, id);
			}
		}
	}

	private static boolean isValid(int newRow, int newCol) {
		return newRow>=0 && newCol>=0 && newRow<N && newCol<M 
				&& emptySpaceRecord[newRow][newCol]==0 && map[newRow][newCol]==0;
	}

}
