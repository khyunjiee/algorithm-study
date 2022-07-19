package week38;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 문제: 합이 0인 네 정수 링크: https://www.acmicpc.net/problem/7453
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
public class BJ_7453_합이0인네정수 {

	static long[] CD;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		long[] A = new long[n];
		long[] B = new long[n];
		long[] C = new long[n];
		long[] D = new long[n];
		long[] AB = new long[n * n];
		CD = new long[n * n];

		for (int i = 0; i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			A[i] = Long.parseLong(st.nextToken());
			B[i] = Long.parseLong(st.nextToken());
			C[i] = Long.parseLong(st.nextToken());
			D[i] = Long.parseLong(st.nextToken());
		}

		int idx = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				AB[idx] = A[i] + B[j];
				CD[idx++] = C[i] + D[j];
			}
		}

		Arrays.sort(AB);
		Arrays.sort(CD);
		
		long cnt = 0;
		for (int i = 0; i < AB.length; ++i) {
			cnt += getUpperBound(0, CD.length, -AB[i]) - getLowerBound(0, CD.length, -AB[i]);
		}

		System.out.println(cnt);
	}

	private static int getUpperBound(int left, int right, long target) {
		while (left < right) {
			int mid = (left + right) / 2;
			if (CD[mid] <= target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

	private static int getLowerBound(int left, int right, long target) {
		while (left < right) {
			int mid = (left + right) / 2;
			if (CD[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

}