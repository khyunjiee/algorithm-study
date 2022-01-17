package com.ssafy.algo.study.week19;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 문제:공유기 설치 링크:https://www.acmicpc.net/problem/2110
 * 
 * 접근:
 * 구글의 도움을 받았습니다.
 * 이분탐색으로 가장 인접한 두 공유기 사이의 최대 거리를 시도합니다.
 * 매번 시도할때마다 가장 인접한 두 공유기 사이의 최대 거리 이상을 유지하면서 설치가능한 최대 공유기수를 카운트 합니다.
 * C개이상의 공유기를 설치할 수 있으면 거리를 늘리고 아니면 거리를 줄이면서 시도합니다.
 * 
 * 
 * 시간복잡도:
 * O(NlogN)
 * 
 * 소요 시간:
 * 2h
 * 
 */
public class BJ_2110_공유기설치 {

	static int C, answer = 0;
	static int[] housesCoordinates;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		C = sc.nextInt();
		housesCoordinates = new int[N];

		for (int i = 0; i < N; i++) {
			housesCoordinates[i] = sc.nextInt();
		}
		
		
		// 좌표 오름차순 정렬
		Arrays.sort(housesCoordinates);

		int left = 1;
		int right = housesCoordinates[N - 1] - housesCoordinates[0]; // 양끝 좌표의 차가 최대 거리
		
		
		//이분탐색
		while (left <= right) {
			int mid = (left + right) / 2;

			if (canInstall(mid)) { // 설치가능하면 거리 늘리기
				left = mid + 1;
			} else {				// 설치 불가능하면 거리 줄이기
				right = mid - 1;
			}
		}

		System.out.println(answer);
	}
	
	
	// distance이상의 거리를 두면서 C개 이상의 공유기를 설치할수 있는지 확인하는 메소드
	private static boolean canInstall(int distance) {
		
		int cnt = 1;
		int accDistance = 0;
		
		for (int i = 1; i < housesCoordinates.length; i++) {
			accDistance += housesCoordinates[i] - housesCoordinates[i - 1];
			if (accDistance >= distance) { // distance 이상의 거리가 확보되면 공유기 설치
				cnt++;
				accDistance = 0;
			}
		}

		if (cnt >= C) {
			answer = distance;
			return true;
		} else {
			return false;
		}

	}
}
