

import java.util.Arrays;
import java.util.Scanner;

public class 미세먼지안녕_17144 {
	/*
	 * 접근: 시뮬문제입니다. 미세먼지 동작 메소드, 공기청정기 작동 메소드로 나누어 구현합니다.
	 */
	static int[][] map, tempMap;
	static int R, C, upRow=-1, downRow=-1; // 입력값, 공기청정기 상부 위치, 하부 위치
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		int T = sc.nextInt();
		
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j]==-1) {
					if(upRow<0) upRow = i; // 공청기 상부위치 저장
					else downRow = i; // 공청기 하부위치 저장
				}
			}
		}
		
		for (int sec = 0; sec < T; sec++) {
			difusion(); // 확산
			airPurication(); // 공기청정
		}

		System.out.println(calcDust());
	}
	
	//남은 먼지양 계산
	private static int calcDust() {
		int total = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) if(map[i][j]>0)  total += map[i][j];

		}
		return total;
	}

	private static void difusion() {
		tempMap = new int[R][C];
		int[] dr = new int[] {0,0,1,-1};
		int[] dc = new int[] {1,-1,0,0};
		
		for (int row = 0; row < R; row++) for (int col = 0; col < C; col++) {
			if(map[row][col]>0) {
				for (int i = 0; i < 4; i++) {
					int nr = row + dr[i];
					int nc = col + dc[i];
					if(nr>=0 && nc>=0 && nr<R && nc<C && map[nr][nc]>=0) { // 맵안쪽 공기청정기가 없는 방향인 경우에만
						tempMap[nr][nc] += map[row][col]/5;
						tempMap[row][col] -= map[row][col]/5;
					}
				}
			}
		}
		updateMap(); // 맵 갱신
	}
	
	private static void updateMap() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) map[i][j]+=tempMap[i][j];
		}
	}



	private static void airPurication() {
		// 위쪽 바람
		for (int i = upRow-1; i > 0; i--) map[i][0] = map[i-1][0]; //왼쪽 수직
		for (int i = 0; i < C-1; i++) map[0][i] = map[0][i+1]; //위쪽 수평
		for (int i = 0; i <upRow; i++) map[i][C-1] = map[i+1][C-1]; //오른쪽 수직
		for (int i = C-1; i >1; i--) map[upRow][i] = map[upRow][i-1]; //아래쪽 수평
		map[upRow][1] = 0;
		
		// 아래쪽 바람
		for (int i = downRow+1; i < R-1; i++) map[i][0] = map[i+1][0]; //왼쪽 수직
		for (int i = 0; i <C-1; i++) map[R-1][i] = map[R-1][i+1]; //아래쪽 수평
		for (int i = R-1; i >downRow; i--) map[i][C-1] = map[i-1][C-1]; //오른쪽 수직
		for (int i = C-1; i >1; i--) map[downRow][i] = map[downRow][i-1]; //위쪽 수평
		map[downRow][1] = 0;
	}
	
	// 디버깅을 위한 맵 출력메소드
	static void printMap() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) System.out.print(map[i][j]+" ");
			System.out.println();
		}
	}
}
