package com.ssafy.algo.study.week4;

import java.util.Scanner;

public class 물병_1052 {
	/*
	 * 접근:
	 * 그리디한 방식으로 해결합니다.
	 * 항상 최소한으로 만들수 있는 물병수를 구하고 가장 낮은 양의 물이 들어있는 물병에 든
	 * 물양 만큼의 새로운 물병을 추가하면 물병을 하나씩 줄일 수 있습니다.
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[] bottles = makeList(N);
		
		
		int result = 0;
		while(sum(bottles)>K) {
			for (int j = 0; j < bottles.length; j++) {
				if(bottles[j]!=0) {
					result += Math.pow(2, j);
					bottles = makeList(N+result);
					break;
				}
			}
		}
		System.out.println(result);
	}

	private static int[] makeList(int N) {
		int[] bottles = new int[30];
		int i=0;
		
		while(N>0) {
			i  = (int)base2Log(N);
			bottles[i] = 1;
			N -= (int)Math.pow(2, i);
		}
		return bottles;
	}

	private static int sum(int[] bottles) {
		int total = 0;
		for (int bottle : bottles) total += bottle;
		return total;
	}

	private static double base2Log(double n) {
		return Math.log(n) / Math.log(2);
	}

}
