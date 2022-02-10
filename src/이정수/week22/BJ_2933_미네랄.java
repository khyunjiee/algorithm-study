package com.ssafy.algo.study.week22;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * 문제: 미네랄
 * 링크: https://www.acmicpc.net/problem/2933
 * 
 * 접근:
 * 1. 
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 소요 시간:
 * 
 */
public class BJ_2933_미네랄 {

	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static List<int[]> cluster;
	static int[][] delta = {{0,0,1,-1},{1,-1,0,0}};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String input = sc.next();
			map[i] = input.toCharArray();
		}
		
		int N = sc.nextInt();
		
		for (int i = 0; i < N; i++) {
			
			int height = sc.nextInt();
			throwBar(i, height);
			
		}
		
		printMap();
		
	}
	
	private static void dropCluster() {
		// cluster 리스트 소팅
		cluster.sort(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1]) {
					return o2[0] - o1[0];
				}
				return o1[1] - o2[1];
			}
		});
		
		// 각 열의 가장 아래 위치마다 가장 가까운 미네랄 혹은 땅의 위치 중 가장 짧은 거리 구하기
		int formerCol = -1;
		int minDist = 100;
		for (int i = 0; i < cluster.size(); i++) {
			
			if(formerCol != cluster.get(i)[1]) { // 열이 바뀔 때 마다
				formerCol  = cluster.get(i)[1];
				int dist = getDistance(cluster.get(i));
				if(dist<minDist) {
					minDist = dist;
				}
			}
		}
		
		
		// 최단 거리 만큼 떨어뜨리기
		// 맵에서 지우고
		for (int[] position : cluster) { 
			int row = position[0];
			int col = position[1];
			map[row][col] = '.';
		}
		
		// 떨어진 위치에 다시 표시
		for (int[] position : cluster) { 
			int row = position[0]+minDist;
			int col = position[1];
			map[row][col] = 'x';
		}
	}
	
	private static int getDistance(int[] point) {
		int row = point[0];
		int newRow = row+1;
		int col = point[1];
		
		while(newRow<R && map[newRow][col]=='.') {
			newRow++;
		}
		
		return newRow - row-1;
	}


	private static boolean isCluster(int row, int col) {
		
		visited[row][col] = true;
		cluster.add(new int[] {row, col});
		
		if(row==R-1) return false;
		
		for (int d = 0; d < 4; d++) {
			int newRow = row + delta[0][d];
			int newCol = col + delta[1][d];
			
			if(newRow>=0 && newRow<R && newCol>=0 && newCol<C && !visited[newRow][newCol] && map[newRow][newCol]=='x') {
				if(!isCluster(newRow, newCol)) return false;
			}
		}
		return true;
		
	}

	private static void printMap() {
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	
	private static boolean throwBar(int cnt, int height) {
		
		int increasement = 0;
		int col = -1;
		int row = R-height;
		
		if(cnt%2==0) { // 왼쪽에서 던진 경우
			increasement = 1;
			col = 0;
		}else { // 오른쪽에서 던진 경우
			increasement = -1;
			col = C-1;
		}
		
		while(col>=0 && col<C ) {
			if(map[row][col]=='x') {
				map[row][col]='.';
				
				for (int d = 0; d < 4; d++) {
					cluster = new LinkedList<>();
					visited = new boolean[R][C];
					int newRow = row + delta[0][d];
					int newCol = col + delta[1][d];
					if(newRow>=0 && newRow<R && newCol>=0 && newCol<C &&map[newRow][newCol]=='x'  && isCluster(newRow, newCol)) {
						dropCluster();
						break;
					}
				}
				
				return true;
			}
			col += increasement;
		}
		
		return false;
	}
}
