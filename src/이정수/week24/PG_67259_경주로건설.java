package week24;

import java.util.*;
class PG_67259_경주로건설 {
	
	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{0,0,0},{0,0,0},{0,0,0}}));
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
				
				if(visited[d][nr][nc]>=newCost) { // 처음 왔거나 이미 지나간 것보다 더 적은 비용으로 도착한 경우
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