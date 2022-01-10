package com.ssafy.algo.study.week19;

import java.util.Scanner;

/**
 * 문제:⚾
 * 링크:https://www.acmicpc.net/problem/17281
 * 
 * 접근:
 * 타순이 고정이므로 가능한 타순을 모두 시도하여 최고 득점 타순 구하기
 * 1. 순열알고리즘으로 가능한 타순을 모두 시도
 * 2. 타순이 결정될때 마다 게임 시뮬레이션 수행
 * 
 * 시간복잡도:
 *? 
 * 
 * 소요 시간:
 * 2h
 * 
 */
public class BJ_17281_야구 {
	
	static int N, maxScore=0;					// 이닝수, 최고득점값
	static int[] batterSequence = new int[9];	// 타순 배열
	static int[][] results;						// 각 선수가 각 이닝에서 얻는 결과
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		results = new int[N][9];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 9; j++) {
				results[i][j] = sc.nextInt();
			}
		}
		
		permutation(0, 0);	// 순열
		
		System.out.println(maxScore);
	}

	private static void permutation(int depth, int flag) {
		
		if(depth==9) { // 9번타자까지 모두 결정되면
			int score =simulation(); // 시뮬레이션 수행하여 점수 도출
			maxScore = score>maxScore? score : maxScore;
			return ;
		}
			
		// 4번타자는 1번선수로 고정
		if(depth==3) {
			batterSequence[depth] = 0;
			permutation(depth+1, flag|1);
			return;
		}
		
		for (int i = 1; i < 9; i++) {
			if((flag&(1<<i))!=0) continue;
			batterSequence[depth] = i;
			permutation(depth +1,flag|(1<<i));
		}
	}

	private static int simulation() {
		
		int batterIdx = 0;				// batterSequence의 인덱스를 가르키는 포인터
		int out = 0;					// 아웃 개수 카운트 변수
		int[] plateStatus = {0,0,0};	// 현재 플레이트 상황(선수가 있으면 1 없으면 0)
		int score = 0;					// 점수
		
		for (int inning = 0; inning < N; inning++) {
			
			while(out<3) { // 3아웃이면 이닝 종료
				
				int batter = batterSequence[batterIdx++];
				batterIdx %= 9;
				int result = results[inning][batter]; // 타자가 공을 친 결과
				
				switch(result){
					case 0: // 아웃
						out++;
						break;
					case 1:	// 안타
						score += plateStatus[2];
						plateStatus[2] = plateStatus[1];
						plateStatus[1] = plateStatus[0];
						plateStatus[0] = 1;
						break;
					case 2:	// 2루타
						score += plateStatus[1] + plateStatus[2];
						plateStatus[2] = plateStatus[0];
						plateStatus[1] = 1;
						plateStatus[0] = 0;
						break;
					case 3:	// 3루타
						score += plateStatus[0] + plateStatus[1] + plateStatus[2];
						plateStatus[2] = 1;
						plateStatus[1] = 0;
						plateStatus[0] = 0;
						break;
					case 4:	// 홈런
						score += plateStatus[0] + plateStatus[1] + plateStatus[2];
						score++;
						plateStatus[2] = 0;
						plateStatus[1] = 0;
						plateStatus[0] = 0;
						break;
				}
			}
			
			out = 0;	// 이닝이 끝나면 아웃 카운트 초기화
			plateStatus = new int[3]; // 경기장 초기화
		}
		
		return score;
	}

}
