package programmers;

import java.util.LinkedList;
import java.util.Queue;

// [카카오 인턴] 경주로 건설
/*
 * bfs를 이용하여 비용의 최솟값을 구하는 문제이다.
 * 방향을 설정하며 방향이 바뀔 떄 마다 더하는 비용이 달라진다.
 * 풀이방식은 무난하지만 반례를 조심해야 한다.
 * 반례 : 기존의 최적 경로가 X만큼의 비용을 가지고 (r,c)에 왔을 때,
 * 현재 경로가 X+400만큼의 비용을 가지고 기존경로와 수직으로 (r,c)에 와도, 다음 진행방향이 쭉 직진이면 X+500의 비용이 되고,
 * 기존의 경로는 꺾어야 하므로 X+600이 되는 경우이다.
 * 이 경우 기존의 경로보다 비용이 많다고 해서 무조건 무시해버리면 오답을 찾게 되므로 신경써야 한다.
 */
public class Programmers_67259 {

	final int UP=0,RIGHT=1,DOWN=2,LEFT=3;
	
	int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}}; // 상,우,하,좌
	public int solution(int[][] board) {
		int result=Integer.MAX_VALUE;
        int N = board.length;
        int[][] cost = new int[N][N]; // 최적의 비용을 저장하는 배열
        
        for(int i=0; i<N; ++i) {
        	for(int j=0; j<N; ++j) {
        		cost[i][j] = Integer.MAX_VALUE;
        	}
        }
        
        Queue<int[]> q = new LinkedList<int[]>();
        if(board[0][1]==0) {
        	cost[0][1] = 100;
        	q.offer(new int[] {0,1,RIGHT,100});
        }
        if(board[1][0]==0) {
        	 cost[1][0] = 100;
        	 q.offer(new int[] {1,0,DOWN,100});
        }
        while(!q.isEmpty()) {
        	int[] current = q.poll();
        	int r = current[0]; // 행
        	int c = current[1]; // 열
        	int d = current[2]; // 방향 : 상(0),우(1),하(2),좌(3)
        	int sum = current[3]; // 비용합
        	if(r==N-1 && c==N-1) {
        		if(result>sum) result = sum;
        	}
        	for(int i=0; i<4; i++) {
        		int nr = r+dir[i][0];
        		int nc = c+dir[i][1];
        		
        		if(nr<0 || nr>=N || nc<0 || nc>=N || board[nr][nc]!=0) continue; // 경계 및 벽 체크
        		int nextSum = sum; // 다음칸까지의 비용의 합
        		if(d==i) nextSum += 100; // 같은 방향이면 +100
        		else nextSum += 600; // 다른 방향이면 +600(코너+직선경로)
        		
        		// 기존의 최적값이 경로를 꺾어서 갈 경우 현재 경로값이 경로를 꺾지 않으면 최대 400만큼의 비용이 더 들어와도 현재 경로가 유리하다.
        		if(cost[nr][nc]>=nextSum-400) {
        			cost[nr][nc]=nextSum;
        			q.offer(new int[] {nr,nc,i,nextSum});
        		}
        	}
        }
        return result;
    }
}