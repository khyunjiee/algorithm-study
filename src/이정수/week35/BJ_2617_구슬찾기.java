package week35;

import java.util.Scanner;

/**
 * 문제: 구슬 찾기
 * 링크: https://www.acmicpc.net/status?user_id=jslee7420&problem_id=2617&from_mine=1
 * 
 * 풀이:플로이드 워샬
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class BJ_2617_구슬찾기 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] arr = new int[N + 1][N + 1];
		
		for (int i = 0; i < M; i++) {
			int heavierMarble = sc.nextInt();
			int lighterMarble = sc.nextInt();

			arr[heavierMarble][lighterMarble] = 1;
			arr[lighterMarble][heavierMarble] = -1;
		}

		int ans = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					if (arr[k][i] != 0 && arr[j][i] == arr[i][k])
						arr[j][k] = arr[j][i];
				}
			}
		}

		int[] heavier = new int[N + 1];
		int[] lighter = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (arr[i][j] == 1)
					heavier[i]++;
				if (arr[i][j] == -1)
					lighter[i]++;
			}
		}

		for (int i = 1; i <= N; i++) {
			if (heavier[i] > N/2 || lighter[i] > N/2)
				ans++;
		}
		System.out.println(ans);
	}

}
