package week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1012_¿Ø±‚≥ÛπË√ﬂ {
	
	static boolean[][] map, visited;
	static int M,N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for (int testCase = 0; testCase < T; testCase++) {
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			map = new boolean[N][M];
			visited = new boolean[N][M];
			
			for (int i = 0; i < K; i++) {
				
				st = new StringTokenizer(in.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				
				map[Y][X] = true;
			}
			
			int cnt = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(!visited[i][j] && map[i][j]) {
						dfs(i,j);
						cnt++;
					}
				}
			}
			
			System.out.println(cnt);
		}
		
	}
	static int[][] delta  = {{0,0,-1,1},{-1,1,0,0}};
	private static void dfs(int row, int col) {
		
		visited[row][col] = true;
		
		for (int d = 0; d < 4; d++) {
			int newRow = row + delta[0][d];
			int newCol = col + delta[1][d];
			
			if(newRow>=0 && newCol>=0 && newRow<N && newCol<M &&
					!visited[newRow][newCol] && map[newRow][newCol]) {
				dfs(newRow, newCol);
			}
		}
	}

}
