package com.ssafy.algo.study.week10;

import java.util.LinkedList;
import java.util.Queue;

public class 경주로건설_67259 {
	/*
	 * 접근:
	 * 1. bfs 사용 
	 * 2. 방문 처리는 visited와누적된 비용을 기록.
	 *  이미 표시된 비용보다 낮은 비용값을 가지고 방문시 지나갈 수 있도록함
	 * 3. visited배열은 해당 칸으로 접근해온 방향으로 구분
	 * 
	 * 처음에는 visited 처리때문에 dfs만 가능하다고 생각해서 풀었는데 시간초과가 나버렸습니다.
	 * 구글링으로 visited를 처리하는 아이디어를 얻어서 다시 bfs로 해결하였습니다.
	 * 
	 * 시간복잡도:
	 * 알수 없음
	 * 
	 * 풀이에 걸린 시간:
	 * 4h
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}}));
	}
	
	
	public static int solution(int[][] board) {
		int min = Integer.MAX_VALUE;  // 최소 비용
		int[][] delt = {{-1,1,0,0},{0,0,-1,1}}; // 4방향 delta 값
		int N = board.length; // 맵의 크기
		
		int[][][] visited = new int[4][N][N]; // 방문 처리 배열
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					visited[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		Queue<int[]> queue = new LinkedList<>();
		
		
		queue.add(new int[] {0,0,-1,0}); // 0,0에서 시작
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			int r = current[0];
			int c = current[1];
			int postDirection = current[2];
			int cost = current[3];
			
			if(r==N-1 && c==N-1) {// 도착
				min = Math.min(min, cost);
				continue; // 다른 경로 비용이 더 낮을 수 있으므로 리턴하지 않음
			}
					
			for (int d = 0; d < 4; d++) {
				int nr=r+delt[0][d];
				int nc=c+delt[1][d];
				int newCost = cost;
				
				if(!isValid(nr, nc, board)) continue; // 유효하지 않은 위치면 시도 X
				
				if(postDirection==-1) { // 시작위치에서 가는 경우
					newCost += 100;
				}else if(postDirection==d) { // 이전과 방향이 같으면 직선 도로
					newCost += 100;
				}else {// 이전과 방향이 다르면 코너 + 직선 도로
					newCost += 600;
				}
				
				if(visited[d][nr][nc]>=newCost) { // 더 적은 비용으로 도착한 경우
					queue.add(new int[] {nr,nc,d,newCost});
					visited[d][nr][nc] = newCost;
				}
			}
		}
		
        return min;
    }

	private static boolean isValid(int nr, int nc, int[][] board) {
		return nr>=0 && nc>=0 && nr<board.length && nc<board.length && board[nr][nc]==0;
	}
}
