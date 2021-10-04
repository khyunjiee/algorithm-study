package com.ssafy.algo.study.week9;

import java.util.*;
import java.io.*;

class 블록이동하기_60063 {
	public static void main(String[] args) {
		System.out.println(solution(new int[][] { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 },
				{ 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 } }));
	}

	/*
	 * 풀이: 
	 * 가중치 없는 최단 경로 이므로 bfs 사용 4방 탐색 + 4방 회전 
	 * visited 배열을 가로 방향일때와 세로 방향일 때로 나눠서 관리 
	 * visited 배열은 N-1*N-1 크기로 가로일 경우 차지하는 두칸 중 왼쪽 칸 기준으로 표시 
	 * 세로일 경우 차지하는 두칸 중 윗칸
	 * 기준으로 표시
	 * 
	 */
	static final int HORIZONTAL = 0;
	static final int VERTICAL = 1;

	static class Position {
		int r1, c1, r2, c2;
		int dir;

		public Position(int r1, int c1, int r2, int c2, int dir) {
			this.r1 = r1;
			this.c1 = c1;
			this.r2 = r2;
			this.c2 = c2;
			this.dir = dir;
		}

		public String toString() {
			return "출력: " + r1 + c1 + r2 + c2 + dir;
		}
	}

	static boolean[][][] visited;
	static int[][] boardN;
	static int N;
	static Queue<Position> queue;
	static final int CLOCKWISE = 0;
	static final int COUNTERCLOCKWISE = 1;

	public static int solution(int[][] board) {
        int[] dr = {-1,1,0,0}; // 상하좌우
        int[] dc = {0,0,-1,1};
        boardN = board;
        N = board.length;
        
        visited = new boolean[2][][];
        visited[0]  = new boolean[N][N-1]; // 가로 방향 방문 체크
        visited[1]  = new boolean[N-1][N]; // 세로 방향 방문 체크
        
        queue = new LinkedList<>();
        queue.add(new Position(0,0,0,1,HORIZONTAL));
        visited[HORIZONTAL][0][0] = true;
        
        int cnt=0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(--size>=0){
                Position current = queue.poll();
                // 목적지에 도달한 경우
                if(current.r2==N-1 && current.c2 ==N-1){
                    return cnt;
                }
                
                // System.out.println(current.toString());
                // 4방 이동 + 회전
                for(int d=0; d<4;d++){
                    // 4방 이동
                    int nr1 = current.r1 + dr[d];
                    int nc1 = current.c1 + dc[d];
                    int nr2 = current.r2 + dr[d];
                    int nc2 = current.c2 + dc[d];
                    
                    // 새로운 위치로 움직일수 있으면 이동
                    
                    if(isValid(nr1,nc1,nr2, nc2) && !visited[current.dir][nr1][nc1]){
                        queue.add(new Position(nr1,nc1,nr2,nc2,current.dir));
                        visited[current.dir][nr1][nc1] = true;
                    }
                }
                
                // 4방 회전
                if(current.dir==HORIZONTAL) { // 로봇이 수평 방향인 경우
                	// 로봇 위에 두칸이 모두 비어있어야 위로 회전 가능
                	if(isValid(current.r1-1, current.c1, current.r2-1, current.c2)) {
                		// 1번 기준 피벗
                		if(!visited[current.dir^1][current.r1-1][current.c1]) {
                			queue.add(new Position(current.r1-1, current.c1,current.r1, current.c1,current.dir^1)); // 1번 기준 피벗
                			visited[current.dir^1][current.r1-1][current.c1] = true;
                		}
                		// 2번 기준 피벗
                		if(!visited[current.dir^1][current.r2-1][current.c2]) {
                			queue.add(new Position(current.r2-1, current.c2,current.r2, current.c2,current.dir^1));
                			visited[current.dir^1][current.r2-1][current.c2] = true;
                		}
                	}
                	
                	// 로봇 아래 두칸이 모두 비어있어야 아래로 회전 가능
                	if(isValid(current.r1+1, current.c1, current.r2+1, current.c2)) {
                		// 1번 기준 피벗
                		if(!visited[current.dir^1][current.r1][current.c1]) {
                			queue.add(new Position(current.r1, current.c1,1+current.r1, current.c1,current.dir^1)); // 1번 기준 피벗
                			visited[current.dir^1][current.r1][current.c1] = true;
                		}
                		// 2번 기준 피벗
                		if(!visited[current.dir^1][current.r2][current.c2]) {
                			queue.add(new Position(current.r2, current.c2,current.r2+1, current.c2,current.dir^1));
                			visited[current.dir^1][current.r2][current.c2] = true;
                		}
                	}
                }else if(current.dir==VERTICAL){// 로봇이 수직 방향인 경우
                	// 로봇 좌측 두칸이 모두 비어있어야 왼쪽으로 회전 가능
                	if(isValid(current.r1, current.c1-1, current.r2, current.c2-1)) {
                		// 1번 기준 피벗
                		if(!visited[current.dir^1][current.r1][current.c1-1]) {
                			queue.add(new Position(current.r1, current.c1-1,current.r1, current.c1,current.dir^1)); // 1번 기준 피벗
                			visited[current.dir^1][current.r1][current.c1-1] = true;
                		}
                		// 2번 기준 피벗
                		if(!visited[current.dir^1][current.r2][current.c2-1]) {
                			queue.add(new Position(current.r2, current.c2-1,current.r2, current.c2,current.dir^1));
                			visited[current.dir^1][current.r2][current.c2-1] = true;
                		}
                	}
                	
                	// 로봇 우측 두칸이 모두 비어있어야 오른쪽으로 회전 가능
                	if(isValid(current.r1, current.c1+1, current.r2, current.c2+1)) {
                		// 1번 기준 피벗
                		if(!visited[current.dir^1][current.r1][current.c1]) {
                			queue.add(new Position(current.r1, current.c1,current.r1, current.c1+1,current.dir^1)); // 1번 기준 피벗
                			visited[current.dir^1][current.r1][current.c1] = true;
                		}
                		// 2번 기준 피벗
                		if(!visited[current.dir^1][current.r2][current.c2]) {
                			queue.add(new Position(current.r2, current.c2,current.r2, current.c2+1,current.dir^1));
                			visited[current.dir^1][current.r2][current.c2] = true;
                		}
                	}
                }
            }
            cnt++;
        }
        int answer = 0;
        return answer;
    }

	// 회전이 가능한지 확인
	static boolean check(int r, int c) {
		return (boardN[r][c] == 0);
	}

	// 해당 위치가 비어있고 맵 안쪽인지 확인
	static boolean isValid(int r1, int c1, int r2, int c2){
          return (r1>=0 && c1>=0 && r1<N && c1<N && boardN[r1][c1]==0
          && r2>=0 && c2>=0 && r2<N && c2<N && boardN[r2][c2]==0);
    }
}
