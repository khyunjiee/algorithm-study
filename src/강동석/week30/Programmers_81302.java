package programmers;

// 거리두기 확인하기
/*
 * dfs나 bfs로 풀 수 있지만 거리가 2밖에 안되서
 * 더 간단하게 하드코딩으로 모든 경우를 직접 확인하였다.
 */
public class Programmers_81302 {

	public static void main(String[] args) {
		String[][] places = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};
		System.out.println(solution(places));
	}
	
	static char[][] map;
	static int[][] dir = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
	
	public static int[] solution(String[][] places) {
	
		map = new char[5][5];
		int[] answer = new int[5];
	loop:for(int tc=0; tc<5; ++tc) {
			for(int r=0; r<5; ++r) {
				map[r] = places[tc][r].toCharArray();
			}
	
			for(int i=0; i<5; ++i) {
				for(int j=0; j<5; ++j) {
					if(map[i][j]=='P') {
						if(!canKeepDistance(i,j)) { // 거리두기 못하면
							answer[tc]=0;
							continue loop;
						}
					}
				}
			}
			// 모든 P가 거리두기 하면
			answer[tc]=1;
		}
        return answer;
    }
	
	public static boolean canKeepDistance(int r, int c) {
		for(int d=0; d<8; d+=2) { // 상하좌우 1칸
			int nr = r+dir[d][0];
			int nc = c+dir[d][1];
			if(nr<0 || nr>4 || nc<0 || nc>4) continue; // 경계
			if(map[nr][nc]=='P') {
				return false;
			}
		}
		for(int d=0; d<8; d+=2) { // 상하좌우 2칸
			int nr = r+dir[d][0];
			int nc = c+dir[d][1];
			if(nr<0 || nr>4 || nc<0 || nc>4) continue; // 경계
			if(map[nr][nc]=='O') { // 옆칸이 O면
				nr += dir[d][0]; // 한칸더 전진
				nc += dir[d][1];
				if(nr<0 || nr>4 || nc<0 || nc>4) continue; // 경계
				if(map[nr][nc]=='P') {
					return false;
				}
			}
		}
		for(int d=1; d<8; d+=2) { // 대각선 1칸
			int nr = r+dir[d][0];
			int nc = c+dir[d][1];
			if(nr<0 || nr>4 || nc<0 || nc>4) continue; // 경계
			if(map[nr][nc]=='P') { // 대각선이 P이면
				if(map[r][nc]=='O' || map[nr][c]=='O') { // 둘 사이에 하나라도 O가 있으면
					return false;
				}
			}
		}
		return true;
	}
}