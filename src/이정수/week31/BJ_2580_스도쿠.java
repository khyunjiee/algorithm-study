package week31;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 스도쿠
 * 링크: https://www.acmicpc.net/problem/2580
 * 
 * 풀이:
 * 각 행과 열 그리고 9개의 3*3 정사각형에 들어있는 숫자들을 boolean 배열로 표시해두고
 * dfs로 빈칸에 1~9까지의 숫자를 하나씩 넣어보면서 조건에 맞으면 다음 칸을 점점 채운다.
 * 
 * 
 * 시간복잡도:
 * ?
 * 
 * 풀이에 걸린 시간:
 * 15m
 *
 */
public class BJ_2580_스도쿠 {
	
	static int[][] sdoku = new int[9][9];
	static int[][] blankPositions = new int[81][];
	static int numberOfBlanks = 0;
	static boolean[][] rowCheck = new boolean[9][10];
	static boolean[][] colCheck = new boolean[9][10];
	static boolean[][][] squareCheck = new boolean[3][3][10];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < 9; j++) {
				int input = Integer.parseInt(st.nextToken());
				sdoku[i][j] = input;
				if(input==0) {
					blankPositions[numberOfBlanks++] = new int[] {i,j};
				}
				rowCheck[i][input] = true;
				colCheck[j][input] = true;
				squareCheck[i/3][j/3][input] = true;
			}
		}
		
		dfs(0);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				sb.append(sdoku[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}


	private static boolean dfs(int depth) {
		
		if(depth==numberOfBlanks) {
			return true;
		}
		
		int[] blankPosition  = blankPositions[depth];
		int row = blankPosition[0];
		int col = blankPosition[1];
		
		for (int i = 1; i <= 9; i++) {
			if(!rowCheck[row][i] && !colCheck[col][i] && !squareCheck[row/3][col/3][i]) {
				sdoku[row][col] = i;
				rowCheck[row][i] = colCheck[col][i] = squareCheck[row/3][col/3][i] = true;
				if(dfs(depth+1)) {
					return true;
				}
				rowCheck[row][i] = colCheck[col][i] = squareCheck[row/3][col/3][i] = false;
			}
		}
		
		return false;
	}

}
