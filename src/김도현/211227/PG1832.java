package PG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Level3. 보행자 천국
 * ["오른쪽 또는 아래 방향으로 한 칸씩 이동","20170805로 나눈 나머지",1 <= m,n <= 500] ==> DP
*/

public class PG1832 {
	final static int MOD = 20170805;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int[][] cityMap = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				cityMap[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int[][][] DP = new int[2][m][n];
		for (int i = 0; i < m; i++) {
			if (cityMap[i][0] == 1) break;
			DP[0][i][0] = 1;
		}
		for (int j = 0; j < n; j++) {
			if (cityMap[0][j] == 1) break;
			DP[1][0][j] = 1;
		}
		
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (cityMap[i][j] == 1) continue;
				if (cityMap[i-1][j] == 0) DP[0][i][j] = (DP[0][i-1][j] + DP[1][i-1][j]) % MOD;
				else DP[0][i][j] = DP[0][i-1][j];
				if (cityMap[i][j-1] == 0) DP[1][i][j] = (DP[1][i][j-1] + DP[0][i][j-1]) % MOD;
				else DP[1][i][j] = DP[1][i][j-1];
			}
		}
		
//		for (int i = 0; i < m; i++) {
//			for (int j = 0; j < n; j++)
//				System.out.print(DP[0][i][j] + " ");
//			System.out.println();
//		}
//		System.out.println();
//		for (int i = 0; i < m; i++) {
//			for (int j = 0; j < n; j++)
//				System.out.print(DP[1][i][j] + " ");
//			System.out.println();
//		}
		
		System.out.println((DP[0][m-1][n-1] + DP[1][m-1][n-1]) % MOD);
		
	}

}
