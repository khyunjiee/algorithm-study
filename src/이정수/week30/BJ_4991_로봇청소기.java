package week30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_4991_로봇청소기 {
	static char[][] map;
	static boolean[][] visited;
	static int[][] dirtyPlaces;
	static int h,w;
	static int[] result;
	static boolean[] used;
	static int dirtyPlacesCnt, min;
	static int[][] distances;
	static int distanceFromStart[];
	static int[] dirtyPlacesMap;
	static int[][] delta = {{0,0,-1,1},{-1,1,0,0}};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		while(!(w==0 && h==0)) {
			map = new char[h][w];
			dirtyPlaces = new int[w*h][];
			visited = new boolean[h][w];
			dirtyPlacesCnt = 0;
			dirtyPlacesMap = new int[400];
			Arrays.fill(dirtyPlacesMap, -1);
			int[] startPosition = {-1,-1};
			
			for (int i = 0; i < h; i++) {
				String line = in.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j]=='*') {
						dirtyPlaces[dirtyPlacesCnt] = new int[] {i,j};
						dirtyPlacesMap[i*w+j] = dirtyPlacesCnt++;
					}else if(map[i][j]=='o') {
						startPosition = new int[] {i,j};
					}
				}
			}
			
			distances = new int[dirtyPlacesCnt][dirtyPlacesCnt];
			distanceFromStart = new int[dirtyPlacesCnt];

			// bfs로 모든 정점간에 최단 거리 구하기
			// bfs로 시작점에서부터 모든 더러운 칸까지의 최단 거리 구하기
			if(!calcAllDistances() || !calcAllDistancesFromStart(startPosition)) {
				System.out.println(-1);
				st = new StringTokenizer(in.readLine());
				
				w = Integer.parseInt(st.nextToken());
				h = Integer.parseInt(st.nextToken());
				continue;
			}
			
			
			// 순열로 정점 방문 순서 정하고 brute force
			used = new boolean[dirtyPlacesCnt];
			result = new int[dirtyPlacesCnt];
			min = Integer.MAX_VALUE;
			permutation(0, startPosition, 0);
			System.out.println(min);
			st = new StringTokenizer(in.readLine());
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
		}
		
		
	}
	
	// 시작점에서 더러운 점까지의 거리 모두 구하기
	private static boolean calcAllDistancesFromStart(int[] startPosition) {
		
		int[] currentPosition = startPosition;
		
		Queue<int[]> queue = new LinkedList<>();
		visited = new boolean[h][w];
		
		queue.add(currentPosition);
		visited[currentPosition[0]][currentPosition[1]] = true;
		
		int cnt = 0;
		int dirtCnt = 0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			while(size-->0) {
				int[] position = queue.poll();
				int row = position[0];
				int col = position[1];
				
				if(map[row][col]=='*') {
					distanceFromStart[dirtyPlacesMap[row*w+col]] = cnt;
					dirtCnt++;
				}
				
				for (int d = 0; d < 4; d++) {
					int newRow = position[0] + delta[0][d];
					int newCol = position[1] + delta[1][d];
					
					if(isValid(newRow, newCol)) {
						visited[newRow][newCol] = true;
						queue.add(new int[] {newRow, newCol});
					}
				}
			}
			cnt++;
		}
		
		if(dirtCnt!=dirtyPlacesCnt) {
			return false;
		}
		
		return true;
	}
	
	// 더러운 칸 끼리의 거리 모두 구하기
	private static boolean calcAllDistances() {
		
		for (int i = 0; i < dirtyPlacesCnt; i++) {
			
			int[] currentPosition = dirtyPlaces[i];
			
			Queue<int[]> queue = new LinkedList<>();
			visited = new boolean[h][w];
			
			queue.add(currentPosition);
			visited[currentPosition[0]][currentPosition[1]] = true;
			
			int cnt = 0;
			int dirtCnt = 0;
			
			while(!queue.isEmpty()) {
				int size = queue.size();
				
				while(size-->0) {
					int[] position = queue.poll();
					int row = position[0];
					int col = position[1];
					
					if(map[row][col]=='*') {
						distances[i][dirtyPlacesMap[row*w+col]] = distances[dirtyPlacesMap[row*w+col]][i] = cnt;
						dirtCnt++;
					}
					
					for (int d = 0; d < 4; d++) {
						int newRow = position[0] + delta[0][d];
						int newCol = position[1] + delta[1][d];
						
						if(isValid(newRow, newCol)) {
							visited[newRow][newCol] = true;
							queue.add(new int[] {newRow, newCol});
						}
					}
				}
				cnt++;
			}
			
			if(dirtCnt!=dirtyPlacesCnt) {
				return false;
			}
		}
		
		return true;
	}
	
	private static void permutation(int depth, int[] currentPosition, int distanceAcc) {
		
		if(depth==dirtyPlacesCnt) {
			min = Math.min(min, distanceAcc);
			return;
		}
		
		for (int i = 0; i < dirtyPlacesCnt; i++) {
			if(used[i]) {
				continue;
			}
			used[i] = true;
			
			// 목표 지점까지 가는데 필요한 이동거리 구하기
			int requiredMoves;
			if (depth==0) {
				requiredMoves = distanceFromStart[i];
			}else {
				int currentPositionID = dirtyPlacesMap[currentPosition[0]*w+currentPosition[1]];
				requiredMoves = distances[currentPositionID][i];
			}
			
			permutation(depth+1, dirtyPlaces[i], distanceAcc + requiredMoves);
			used[i] = false;
		}
	}
	
	private static boolean isValid(int newRow, int newCol) {
		return newRow>=0 && newCol>=0 && newRow<h && newCol<w && !visited[newRow][newCol] && map[newRow][newCol]!='x';
	}

}
