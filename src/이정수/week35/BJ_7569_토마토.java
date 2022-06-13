package week35;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제: 토마토
 * 링크: https://www.acmicpc.net/problem/7569
 * 
 * 풀이: 
 * 
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class BJ_7569_토마토 {

	static int[][][] map;
	static int N,M,H;
	static int greenTomatoCnt;
	static int[][] delta = {{-1,1,0,0,0,0},{0,0,-1,1,0,0},{0,0,0,0,-1,1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][N][M];
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					if(map[i][j][k]==0) greenTomatoCnt++;
				}
			}
		}
		
		int ans = bfs();
		
		System.out.println(ans);
		
	}
	
	private static int bfs() {
		
		Queue<int[]> queue = new LinkedList<>();
		int days = -1;
		
		// 존재하는 모든 익은 토마토 정보 큐에 삽입
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if(map[i][j][k]==1) {
						queue.add(new int[] {i,j,k});
					}
				}
			}
		}
		
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			days++;
			while(size-->0) {
				
				int[] position = queue.poll();
				
				for (int d = 0; d < 6; d++) {
					
					int nH = position[0] + delta[0][d];
					int nR = position[1] + delta[1][d];
					int nC = position[2] + delta[2][d];
					
					if(!isValid(nH, nR, nC)) continue;
					
					// 토마토 익히기
					map[nH][nR][nC] = 1;
					greenTomatoCnt--;
					queue.add(new int[] {nH, nR, nC});
				}
			}
		}
		
		return greenTomatoCnt==0 ? days : -1;
	}

	private static boolean isValid(int nH, int nR, int nC) {
		return nH>=0 && nH<H && nR>=0 && nR<N && nC>=0 && nC<M && map[nH][nR][nC]==0;
	}

}
