package week29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제: 벽 부수고 이동하기 링크: https://www.acmicpc.net/problem/2206
 * 
 * 풀이:
 * bfs 탐색
 * visited배열을 벽을 하나 부순 상태와 아직 부수지 않은 상태 두개 사용
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 30m
 *
 */
public class BJ_2206_벽부수고이동하기 {

	static int[][] map;
	static final int TRUE = 0;
	static final int FALSE = 1;
	static int N, M;
	static int[][] delta = { { 0, 0, -1, 1 }, { -1, 1, 0, 0 } };
	static boolean[][][] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		visited = new boolean[2][N][M];

		int ans = bfs();

		System.out.println(ans);
	}

	private static int bfs() {

		Queue<int[]> queue = new LinkedList<>();

		queue.add(new int[] { 0, 0, 0 });
		visited[0][0][0] = true;
		
		int cnt = 1;

		while (!queue.isEmpty()) {

			int size = queue.size();

			while (size-- > 0) {
				int[] position = queue.poll();

				int row = position[0];
				int col = position[1];
				int canBreak = position[2];

				if (row == N - 1 && col == M - 1) {
					return cnt;
				}

				for (int d = 0; d < 4; d++) {
					int newRow = row + delta[0][d];
					int newCol = col + delta[1][d];

					if (isValid(newRow, newCol, canBreak)) {

						if (canBreak == TRUE) { // 벽을 아직 안부순 경우
							if (map[newRow][newCol] == 1) {
								visited[1][newRow][newCol] = true;
								queue.add(new int[] { newRow, newCol, FALSE });
							} else {
								visited[0][newRow][newCol] = true;
								queue.add(new int[] { newRow, newCol, TRUE });
							}
						} else if (canBreak == FALSE && map[newRow][newCol] == 0) { // 벽을 부쉈고 빈공간인 경우
							visited[1][newRow][newCol] = true;
							queue.add(new int[] { newRow, newCol, FALSE });
						}

					}
				}
			}
			cnt++;

		}

		return -1;
	}

	private static boolean isValid(int newRow, int newCol, int canBreak) {
		return newRow >= 0 && newCol >= 0 && newRow < N && newCol < M && !visited[canBreak][newRow][newCol];
	}

}
