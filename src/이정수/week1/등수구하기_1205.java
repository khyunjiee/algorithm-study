package com.ssafy.algorithm;

import java.util.Scanner;

public class 등수구하기_1205 {
	/*
	 * 접근: 랭킹 리스트에 들어갈 수 없는 경우는 리스트가 꽉 차있고 리스트의
	 * 마지막 요소보다 작거나 같은 점수인 경우, 등수는 유진이의 점수보다 더 큰 점수의 개수로
	 * 와 같습니다.
	 * 
	 * 시간복잡도: O(N)
	 */
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		
		int N = sc.nextInt();
		int score = sc.nextInt();
		int P = sc.nextInt();
		
		int[] list = new int[P];
		
		if(N>0) {// N이 0보다 클때만
			for(int i = 0;i<N;i++) {
				list[i] = sc.nextInt();
			}
			
			// 리스트에 들어갈 수 없는 경우
			if(P==N && list[list.length-1]>=score) {
				System.out.println(-1);
				return;
			}
			
			// 등수 구하기
			int cnt = 1;
			
			for(int storedScore: list) {
				if(storedScore>score) cnt++;
			}
			System.out.println(cnt);
			
		}else System.out.println(1);
		
	}

}
