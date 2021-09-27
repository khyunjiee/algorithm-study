package com.ssafy.algo.study.week4;

import java.util.Arrays;
import java.util.Scanner;

public class 쿼드트리_1992 {

	/*
	 * 접근:분할정복
	 * 재귀적으로 구현해야합니다. 
	 * 1. 주어진 모든 영역이 같은 숫자로 되어 있는지  확인
	 * 2. 같은 숫자라면 해당 숫자로 표현
	 * 3. 아니라면 네 영역으로 나누어 각 영역마다 재귀함수 호출
	 * 
	 * 시간복잡도: 
	 * 최악의 경우는 0과 1이 번갈아가면서 영상을 채우는 경우
	 * 모든 원소를 확인하는 것을 logN번 반복해야함. N^2logN => N이 64 일때 24,576 번 연산
	 * 
	 * 풀이 시간:
	 */
	static int N, map[][];
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		sb = new StringBuilder();
		N = sc.nextInt();
		map = new int[N][];
		sc.nextLine(); // 개행문자 없애기
		for(int i=0;i<N;i++) map[i] = Arrays.stream(sc.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
		
		generateQuadTree(0,0,N);
		
		System.out.println(sb.toString());
	}
	
	
	// 쿼드트리 생성
	private static void generateQuadTree(int i, int j, int n) {
		// 주어진 모든 영역이 같은 숫자로 되어 있는지  확인
		if(isUniform(i,j,n)) {
			// 같은 숫자라면 해당 숫자로 표현
			sb.append(map[i][j]);
			return;
		}
		// 아니라면 네 영역으로 나누어 각 영역마다 재귀함수 호출
		sb.append("(");
		generateQuadTree(i,j,n/2); // 왼쪽 위
		generateQuadTree(i,j+n/2,n/2); // 오른쪽 위
		generateQuadTree(i+n/2,j,n/2); // 왼쪽 아래
		generateQuadTree(i+n/2,j+n/2,n/2); // 오른쪽 아래
		sb.append(")");
	}
	
	
	// 주어진 영역의 통일성 여부 판단
	private static boolean isUniform(int i, int j, int k) {
		for(int x =i;x<i+k;x++) for(int y =j;y<j+k;y++) if(map[i][j]!=map[x][y]) return false;
		return true;
	}

}
