package com.ssafy.algo.study.week11;

import java.util.Arrays;
import java.util.Scanner;

public class 성냥개비_3687 {
	/*
	 * 접근:
	 * DP
	 * 1. n개의 성냥개비로 만들수 있는 가장 작은 숫자 DP table 하나
	 * 	dpMin[n] = min (dpMin[2] + dpMin[n-2], dpMin[3] + dpMin[n-3], ..., dpMin[n-2] + dpMin[2])
	 * 2. n개의 성냥개비로 만들수 있는 가장 큰 숫자 DP table 하나
	 * 	dpMax[n] = max (dpMax[2] + dpMax[n-2], dpMax[3] + dpMax[n-3], ..., dpMax[n-2] + dpMax[2])
	 * 
	 * DP[2]~DP[7]은 직접 초기값 세팅
	 * 
	 * 
	 * dpMax에서 NumberFormat Exception이 발생했습니다.
	 * n이 100이면 11111111111...11 50자리 숫자가 나옵니다. long , double모두 불가능하므로 다른 방법을 사용합니다.
	 * n이 짝수이면 1111111...
	 * n이 홀수 이면 711111111... 이 가장 큰 수입니다.
	 * 
	 * 
	 * 시간 복잡도:
	 * O(n^2)
	 * 
	 * 
	 * 풀이에 걸린 시간:
	 * 2h
	 * 
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		String[] smallnums  = {null, null,"1","7","4","2","6","8"}; // index개의 성냥으로 만들 수 있는 가장 작은 숫자 
		String[] largenums  = {null, null,"1","7"}; // index개의 성냥으로 만들 수 있는 가장 큰 숫자
		
		for (int tc = 1; tc <= T; tc++) {
			int n = sc.nextInt();
			
			// dpMin Table
			
			String[] dpMin = new String[n+1];
			Arrays.fill(dpMin, Long.toString(Long.MAX_VALUE));
			for (int i = 2; i <= Math.min(n, smallnums.length-1); i++) {
				dpMin[i] = smallnums[i];
			}
			
			// dpMin table 채우기
			for (int i = 2; i < dpMin.length; i++) {
				for (int j = 2; j <= i-2; j++) {
					long currentVal = Long.parseLong(dpMin[i]);
					// 뒤에있는 6은 모두 0으로 변경
					String convert6to0 = dpMin[i-j].replace('6', '0');
					long newVal = Long.parseLong(dpMin[j] + convert6to0);
					dpMin[i] = Long.toString(Math.min(currentVal, newVal));
				}
			}
			
			// dpMax Table
			String[] dpMax = new String[n+1];
			Arrays.fill(dpMax, "0");
			for (int i = 2; i <= Math.min(n, largenums.length-1); i++) {
				dpMax[i] = largenums[i];
			}
			
			// dpMax table 채우기
			for (int i = 4; i < dpMax.length; i++) {
				if(i%2==0) { // 짝수일 때
					dpMax[i] = dpMax[i-2] + "1"; 
				}else { // 홀수일 때
					dpMax[i] = "7" + dpMax[i-3]; 
				}
			}
			
			System.out.println(dpMin[n] + " " + dpMax[n]);
		}
	}
}
