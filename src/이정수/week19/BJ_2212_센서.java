package com.ssafy.algo.study.week19;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 문제:센서
 * 링크:https://www.acmicpc.net/problem/2212
 * 
 * 접근:
 * 하나의 집중국으로 모든 센서를 커버 하려면 수신가능 영역의 길이는 양끝에 위치한 센서사이의 거리입니다.
 * 집중국이 하나 늘어나면 가장 거리가 먼 센서 사이의 공간을 비우고 각각의 집중국이 나머지 센서들이 위치한 길이를 커버해야합니다.
 * 이렇게 K개의 집중국이 있으면 센서 사이의 공간을 K-1개만큼 커버하지 않을 수 있습니다.
 * 센서 사이의 거리를 구하고 이를 오름차순 정렬하여 K-1개만큼 전체 길이에서 제외하면 답을 구할 수 있습니다.
 * 
 * 
 * 시간복잡도:
 * O(NlogN)
 * 
 * 소요 시간:
 * 20m
 * 
 */
public class BJ_2212_센서 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] sensors = new int[N];
		
		for (int i = 0; i < N; i++) {
			sensors[i] = sc.nextInt();
		}
		
		Arrays.sort(sensors);
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		
		for (int i = 1; i < sensors.length; i++) {
			pq.add(sensors[i] - sensors[i-1]);
		}
		
		for (int i = 0; i < K-1; i++) {
			pq.poll();
		}
		
		int ans = 0;
		while(!pq.isEmpty()) {
			ans += pq.poll();
		}
		
		System.out.println(ans);
	}

}
