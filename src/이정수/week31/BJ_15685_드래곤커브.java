package week31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 드래곤 커브
 * 링크: https://www.acmicpc.net/problem/15685
 * 
 * 풀이:
 * 움직이는 방향을 기록하고 세대가 넘어갈때마다  기록을 거꾸로 따라가며 바뀐 방향대로 이동하며
 * 표시
 * 
 * 시간복잡도:
 * O(20 * 1024 + 10000 * 4)
 * 
 * 풀이에 걸린 시간:
 * 1h
 *
 */
public class BJ_15685_드래곤커브 {
	static boolean[][] map = new boolean[101][101];
	static int[][] delta = {{0,-1,0,1},{1,0,-1,0}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			makeDragonCurve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		int cnt = 0;
		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) {
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}

	private static void makeDragonCurve(int x, int y, int d, int g) {
		int[] dragonCurveHistory = new int[1024];
		int curveIdx = 0;
		
		map[y][x] = true;
		y += delta[0][d];
		x += delta[1][d];
		
		dragonCurveHistory[curveIdx++] = d;
		map[y][x] = true;
		
		
		for (int generation = 0; generation < g; generation++) {
			// 이전 커브를 역으로 따라 그리기
			for (int i = curveIdx-1; i >= 0; i--) {
				int dir = (dragonCurveHistory[i] + 1) % 4;
				dragonCurveHistory[curveIdx++] = dir;
				y += delta[0][dir];
				x += delta[1][dir];
				map[y][x] = true;
			}
		}
	}

}
