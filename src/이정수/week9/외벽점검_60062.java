package com.ssafy.algo.study.week9;

public class 외벽점검_60062 {
	/*
    접근:
    weak를 돌려가며 가능한 모든 dist 순열에 대해 체크
    완탐
    
    */
	public static void main(String[] args) {
		System.out.println(solution(12,new int[] {1, 3, 4, 9, 10}, new int[] {3, 5, 7}));
	}
	
	static int[] result;
	static boolean[] used;
	static int[] newWeak;
	static int min = Integer.MAX_VALUE;
	static int weakLen;
	
	
	public static int solution(int n, int[] weak, int[] dist) {
		weakLen = weak.length;
		
		//  weak 리스트 연장하기
		newWeak = new int[weakLen*2];
		for (int i = 0; i < weak.length; i++) {
			newWeak[i] = weak[i];
			newWeak[i+weak.length] = n + weak[i];
		}
		
		// dist 순열 만들기
		used = new boolean[dist.length];
		result  = new int[dist.length];
		permutation(0, dist);
		
		if(min == Integer.MAX_VALUE) return -1; // 모든 친구를 투입해도 커버안되는 경우
		return min;
	}
	
	private static void permutation(int cnt, int[] dist) {
		if(cnt==dist.length) {
			check();
			return;
		}
		
		for (int i = 0; i < dist.length; i++) {
			if(used[i]) continue;
			result[cnt] = dist[i];
			used[i] = true;
			permutation(cnt+1, dist);
			used[i] = false;
		}
	}

	private static void check() {
		// 각각의 취약점을 시작으로 하여
		for (int i = 0; i < weakLen; i++) {
			int coveredWeakPoints = 0;
			int startPoint = newWeak[i]; // 친구 j 의 점검 시작 지점
			int idx = i;
			label :for (int j = 0; j < result.length; j++) {
				int endPoint = startPoint + result[j]; //j 가 최대로 갈 수 있는 지점
				
				while(idx<newWeak.length && newWeak[idx]<=endPoint) {// 배열을 넘지 않고 j가 최대로 갈수 있는 지점보다 가까우면
					coveredWeakPoints++; // 커버하는 취약지점 개수 증가
					idx++; // 다음 취약지점 가르키기
					if(coveredWeakPoints == weakLen) {// 모든 취약지점을 커버하면
						min = Math.min(min, j+1);
						break label;
					}
				}
				startPoint = newWeak[idx]; // 다음 친구의 출발지점
			}
		}
	}
}
