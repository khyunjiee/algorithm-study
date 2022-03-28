package week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 점프
 * 링크: https://www.acmicpc.net/problem/1890
 * 
 * 풀이:
 * dp[i][j]는 i+1행 j+1열에 도달하는 가짓수
 * 위에서 아래로 왼쪽에서 오른쪽으로 움직이면서 dp값을 이용하여 오른쪽으로 이동하는 경우의 수와 아래방향으로
 * 이동하는 경우의 수를 dp table에 갱신
 * 
 * 
 * 시간복잡도:
 * O(N^2)
 * 
 * 풀이에 걸린 시간:
 * 1h
 *
 */
public class BJ_1890_점프 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int[][] board = new int[N][N];
		long[][] dp = new long[N][N];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 출발점 초기화
		dp[0][0] = 1;
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				
				if(board[row][col]==0) {
					continue;
				}
				
				// 오른쪽 방향으로 이동
				if(col+board[row][col]<N) {
					dp[row][col+board[row][col]] += dp[row][col];
				}
				
				// 아래로 이동하는 경우
				if(row+board[row][col]<N) {
					dp[row+board[row][col]][col] += dp[row][col];
				}
			}
		}
		System.out.println(dp[N-1][N-1]);
	}

}
