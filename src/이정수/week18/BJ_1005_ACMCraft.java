package com.ssafy.algo.study.week18;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 문제:ACM Craft 링크:https://www.acmicpc.net/problem/1005
 * 
 * 접근: 위상정렬 문제입니다.
 * 1. 각 건물을 짓기 위해 필요한 건물의 수를 나타내는 차수 배열 생성
 * 2. 차수가 0인 건물들 부터 우선순위 큐에 삽입
 * 3. 큐에서 건물들을 순서대로 빼면서 빼낸 건물 다음에 지어져야할 건물의 차수 하나씩 감소시키기
 * 4. 아직 지어지지 않은 건물 중에서 차수가 0인 건물을 큐에 삽입
 * 
 * 시간복잡도:
 * O(N^2)
 * 
 * 소요 시간:
 * 1h
 * 
 */
public class BJ_1005_ACMCraft {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();	// 테스트 케이스 수

		for (int tc = 1; tc <= T; tc++) {
			
			int N = sc.nextInt();	// 건물의 개수
			int K = sc.nextInt();	// 건설 순서 규칙 총 개수
			int[] D = new int[N+1];	// 건물당 건설에 걸리는 시간
			boolean[] done = new boolean[N+1]; // 건설 완료 여부
			
			for (int i = 1; i <= N; i++) {
				D[i] = sc.nextInt();
			}
			
			boolean[][] adjMatrix = new boolean[N+1][N+1];	// 인접행렬로 나타낸 건물들의 선행 관계
			int[] degrees = new int[N+1];					// 건물의 차수(건물을 짓기 위해 먼저 지어져야하는 건물의 수)
			
			for (int j = 0; j < K; j++) {
				int X = sc.nextInt();
				int Y = sc.nextInt();
				
				adjMatrix[X][Y] = true;
				
				degrees[Y]++;
			}
			
			int W = sc.nextInt();
			
			// 우선 순위 큐(건설 완료 시간으로 오름차순 정렬)
			PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>(){

				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[1] - o2[1];
				}
				
			});
			
			// 차수가 0인 건물들부터 큐에 삽입
			for (int i = 1; i < degrees.length; i++) {
				if(degrees[i]==0) {
					q.add(new int[] {i, D[i]});
					done[i] = true;
				}
				
			}
			
			while(!q.isEmpty()) {
				int[] data = q.poll();
				int building = data[0]; // 지어진 건물 번호
				int time = data[1];		// 건물이 건설 완료된 시간
				
				// W면 종료
				if(building==W) {
					System.out.println(time);
					break;
				}
				
				// 차수 줄이기
				for (int i = 1; i < adjMatrix.length; i++) {
					if(adjMatrix[building][i]) 
						degrees[i]--;
				}
				
				// 차수가 0이된 건물 큐에 삽입
				for (int i = 1; i < degrees.length; i++) {
					if(degrees[i]==0 && !done[i]) {
						q.add(new int[] {i, time + D[i]});
						done[i] = true;
					}
				}
			}
			
		}

	}

}
