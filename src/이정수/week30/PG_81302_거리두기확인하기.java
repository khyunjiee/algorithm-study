package week30;

import java.util.Arrays;

/**
 * 문제: 거리두기 확인하기
 * 링크: https://programmers.co.kr/learn/courses/30/lessons/81302#fn1
 * 
 * 풀이:
 * dfs로 두번이동하는 동안 방문하는 지점들은 모두 맨해든 거리 2 이하
 * 파티션이 있는 자리로는 이동하지 않고 그렇지 않은 자리들에 대해서만 dfs로 탐색 수행
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 40m
 *
 */
public class PG_81302_거리두기확인하기 {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[][] {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}})));
	}
	
	static char[][][] map;
	static boolean[][] visited;
	static int[][] delta = {{0,0,-1,1},{-1,1,0,0}};
	
	static public int[] solution(String[][] places) {
		
		map = new char[5][5][];
		
		for (int i = 0; i < places.length; i++) {
			for (int j = 0; j < places.length; j++) {
				map[i][j] = places[i][j].toCharArray();
			}
		}
		
		int[] answer = {1,1,1,1,1};
		
		for (int room = 0; room < places.length; room++) {
			label:for (int i = 0; i < answer.length; i++) {
				for (int j = 0; j < answer.length; j++) {
					// 사람이면 거리 두기 확인
					if(map[room][i][j]=='P') {
						visited = new boolean[5][5];
						
						// 거리두기가 안지켜지고 있으면
						if(dfsCheck(i,j,0, room)) {
							answer[room] = 0;
							break label;
						}
					}
				}
			}
		}
		
		
        return answer;
    }

	private static boolean dfsCheck(int i, int j, int depth, int room) {
		
		visited[i][j] = true;
		
		// 사람과의 거리가 2 맨해튼 거리 이하면
		if(depth!=0 && map[room][i][j]=='P') {
			return true;
		}
		
		if(depth==2) {
			return false;
		}
		
		for (int d = 0; d < 4; d++) {
			int newRow = i + delta[0][d];
			int newCol = j + delta[1][d];
			
			if(isValid(newRow, newCol, room)) {
				if(dfsCheck(newRow, newCol, depth+1, room)) {
					return true;
				}
			}
		}
		
		return false;
	}

	private static boolean isValid(int newRow, int newCol, int room) {
		return newRow>=0 && newCol>=0 && newRow<5 && newCol<5 && !visited[newRow][newCol] && (map[room][newRow][newCol]=='P' || map[room][newRow][newCol]=='O');
	}
}
