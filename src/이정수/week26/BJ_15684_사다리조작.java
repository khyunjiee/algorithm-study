package week26;

import java.util.Scanner;


public class BJ_15684_사다리조작 {
	
	static int N, M, H;
	static boolean[][] ladder;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		
		int[] crossCnt = new int[N+1];
		ladder = new boolean[H+1][N+1];
		
		for (int i = 0; i < M; i++) {
			
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			crossCnt[b]++;
			
			ladder[a][b]=true;
			
		}
		
		
		// 홀수인 라인이 3개 초과면 불가능
		int cnt=0;
		for (int cross : crossCnt) {
			if(cross%2==1) {
				cnt++;
			}
		}
		
		if(cnt>3) {
			System.out.println(-1);
			return;
		}
		
		int lines = 0;
		for (; lines < 4; lines++) {
			if(dfs(1, 1, 0, lines)) break;
		}
		
		lines = lines>3?-1:lines;	// 3개이상이면 불가능
		System.out.println(lines);
	}

	private static boolean dfs(int startRow, int startCol, int depth, int lines) {
		
		if(depth==lines) { // 가로선을 모두 사용하면 
			return isCorrect(ladder);
		}
		
		int row = startRow-1;
		for (int col = startCol; col < N; col++) {
			
			while(++row<=H) {
				// 이미 가로선이 있으면 불가능
				if(ladder[row][col]) {
					continue;
				}
				
				// 왼쪽에 가로선이 있으면 불가능
				if(ladder[row][col-1]) {
					continue;
				}
					
				// 오른쪽에 가로선이 있으면 불가능
				if(ladder[row][col+1]) {
					continue;
				}
				
				// 가로선 놓기
				ladder[row][col] = true;
				
				if(row<H) {
					if(dfs(row+1, col, depth+1, lines)) {
						return true;
					}
				}else{
					if(dfs(1, col+1,  depth+1, lines)) {
						return true;
					}
				}
				
				ladder[row][col] = false;
				
			}
			
			row=0;
		}
		
		return false;
		
	}
	
	
	// i번 세로선의 결과가 i번이 나오는지 확인
	private static boolean isCorrect(boolean[][] ladder) {
		
		for (int vLine = 1; vLine <= N; vLine++) {
			
			int row = 0;
			int col = vLine;
			
			while(row<=H) {
				
				if(col<N && ladder[row][col]) { // 우측으로 가는 길 있으면
					col++;
				}else if(col>0 && ladder[row][col-1]){ // 좌측으로 가는 길 있으면
					col--;
				}
				
				row++;
				
			}
			
			if(vLine != col) {
				return false;
			}
			
		}
		
		return true;
	}

}
