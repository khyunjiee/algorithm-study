package week1;

import java.util.Scanner;

public class Baekjoon_2606 {
	
	/*
	 * 시간복잡도 dfs()의 호출횟수
	 * n번 호출되니까 O(N)
	 */
	static int map[][];
	static int visit[];
	static int count = 0;
	static int N;
	static int pair;
	
	public static int dfs(int i) {
		visit[i] = 1;
		
		for(int j = 1; j<=N; j++) {
			if(map[i][j] == 1 && visit[j] == 0) {
				count++;
				dfs(j);
			}
		}
		return count;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		pair = sc.nextInt();
		
		map = new int[N+1][N+1];
		visit = new int[N+1];
		
		for(int i = 0; i<pair; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			map[x][y] = map[y][x] = 1;
		}
		System.out.println(dfs(1));
	}
}
