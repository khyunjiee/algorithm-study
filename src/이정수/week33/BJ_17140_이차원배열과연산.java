package week33;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * 문제: 이차원 배열과 연산 링크: https://www.acmicpc.net/problem/17140
 * 
 * 풀이:
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class BJ_17140_이차원배열과연산 {
	static class Number implements Comparable<Number> {
		int num, cnt;

		public Number(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Number o) {
			return this.cnt == o.cnt ? this.num - o.num : this.cnt - o.cnt;
		}

	}

	static int[][] A = new int[100][100];
	static int maxRow = 3, maxCol = 3;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int r = sc.nextInt();
		int c = sc.nextInt();
		int k = sc.nextInt();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				A[i][j] = sc.nextInt();
			}
		}

		int time = 0;
		while (time <= 100) {

			// A[r][c] 체크
			if (A[r - 1][c - 1] == k) {
				System.out.println(time);
				return;
			}

			// 연산
			if (maxRow >= maxCol) { // R연산
				operation(maxRow, maxCol, true);
			} else { // C연산
				operation(maxCol, maxRow, false);
			}

			time++;
		}

		System.out.println(-1);
	}

	private static void operation(int a, int b, boolean isRowOperation) {

		// 최대 행/열 수 초기화
		if (isRowOperation) {
			maxCol = 0;
		}else {
			maxRow = 0;
		}
		
		int[][] temp = new int[100][100]; // 결과 기록
		for (int i = 0; i < a; i++) {
			
			Map<Integer, Number>cntMap = new HashMap<>(); // 숫자 카운트 맵
			for (int j = 0; j < b; j++) {
				
				// 카운트맵 갱신
				if (isRowOperation) {
					if(A[i][j]==0) continue;
					Number number = cntMap.getOrDefault(A[i][j], new Number(A[i][j], 0));
					number.cnt++;
					cntMap.put(A[i][j], number);
				} else {
					if(A[j][i]==0) continue;
					Number number = cntMap.getOrDefault(A[j][i], new Number(A[j][i], 0));
					number.cnt++;
					cntMap.put(A[j][i], number);
				}
			}
			
			// 규칙에 따라 정렬
			List<Number> numberList = new ArrayList<>(100);

			for (Object val : cntMap.values()) {
				numberList.add((Number) val);
			}

			Collections.sort(numberList);
			int maxIdx = Math.min(50, numberList.size());
			
			
			// 최대 행열값 갱신
			if (isRowOperation) {
				maxCol = Math.max(maxCol, maxIdx*2);
			}else {
				maxRow = Math.max(maxRow, maxIdx*2);
			}
			
			
			for (int j = 0; j < maxIdx; j++) {
				if (isRowOperation) {
					temp[i][j * 2] = numberList.get(j).num;
					temp[i][j * 2 + 1] = numberList.get(j).cnt;
				} else {
					temp[j * 2][i] = numberList.get(j).num;
					temp[j * 2 + 1][i] = numberList.get(j).cnt;
				}
			}
		}
		A = temp;
	}

}
