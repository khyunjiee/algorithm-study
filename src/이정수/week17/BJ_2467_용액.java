package com.ssafy.algo.study.week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제:용액
 * 링크:https://www.acmicpc.net/problem/2467
 * 
 * 접근:
 * 1. 주어진 숫자들중 0이 있을때와 없을때로 구분
 * 2. 0 이 있는 경우 0+가장 큰 음수 or 0+가장 작은 양수 or 음수+양수 중 가장 작은 숫자를 생성해내는 것이 답
 * 3. 0 이 없는 경우 가장 큰 두 음수의 합 or 가장 작은 두양수의 합 or 음수+양수 중 가장 작은 숫자를 생성해내는 것이 답
 * 
 * 시간복잡도:
 * O(N)
 * 
 * 
 * 소요 시간:
 * 1h
 * 
 */
public class BJ_2467_용액 {
	static int N, arr[], bestResult, answer[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
	
		int zeroIdx = Arrays.binarySearch(arr, 0);
		
		bestResult = Integer.MAX_VALUE;
		answer = new int[2];
 		
		if(zeroIdx>=0) { // 0이 존재하면
			
			// 0, 가장 작은 양수 조합
			if(zeroIdx<N-1) {
				bestResult = arr[zeroIdx+1];
				answer[0] = 0;
				answer[1] = arr[zeroIdx+1];
			}
			
			// 가장 큰 음수, 0 조합
			if(zeroIdx>0 && bestResult>-arr[zeroIdx-1]) {
				bestResult = -arr[zeroIdx-1];
				answer[0] = arr[zeroIdx-1];
				answer[1] = 0;
			}
			
			// 음수, 양수 조합 중 최적의 조합 찾기
			if(zeroIdx<N-1 && zeroIdx>0)
				findBestCase(zeroIdx-1, zeroIdx+1);
			
		}else { // 0이 없으면
			int startOfPositive = -zeroIdx -1;
			int startOfNegative = -zeroIdx -2;

			// 가장 큰 두 음수 조합
			if(startOfNegative>0) {
				bestResult = Math.abs(arr[startOfNegative] + arr[startOfNegative-1]);
				answer[0] = arr[startOfNegative-1];
				answer[1] = arr[startOfNegative];
			}
			// 가장 작은 두 양수 조합
			if(startOfPositive<N-1 && bestResult > arr[startOfPositive] + arr[startOfPositive+1]) {
				bestResult = arr[startOfPositive] + arr[startOfPositive+1];
				answer[0] = arr[startOfPositive];
				answer[1] = arr[startOfPositive+1];
			}
			
			// 음수, 양수 조합 중 최적의 조합 찾기
			if(startOfNegative>=0 && startOfPositive<N)
				findBestCase(startOfNegative, startOfPositive);
		}
		
		System.out.println(answer[0] + " " + answer[1]);
	}
	
	
	
	private static void findBestCase(int i, int j) {
		while(i>=0 && j<N) {
			int result = arr[i]+arr[j];
			if(bestResult>Math.abs(result)) {
				bestResult = Math.abs(result);
				answer[0] = arr[i];
				answer[1] = arr[j];
			}
			if(i>0 && result>0) {
				i--;
			}else if(j<N-1 && result<0) {
				j++;
			}else {
				break;
			}
		}
		
	}

}
