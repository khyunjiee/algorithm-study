package com.ssafy.algo.study.week22;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 문제:숨바꼭질 2
 * 링크:https://www.acmicpc.net/problem/12851
 * 
 * 접근:
 * 1. bfs + 가지치기
 * 2. 움직인 횟수별로 나누어서 탐색
 * 3. 동생을 찾은 경우 같은 움직인 횟수까지의 탐색은 모두 수행
 * 4. 이미 방문한 지점은 후에 다시 방문하지 않도록 visited 처리하여 시간초과 문제 해결
 * 
 * 시간복잡도:
 * ?
 * 
 * 
 * 소요 시간:
 * 1h
 * 
 */
public class BJ_12851_숨바꼭질2 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Queue<Integer> queue = new LinkedList<>();
		queue.add(N);
		
		boolean found = false;
		int time = -1;
		int cnt = 0;
		boolean[] visited = new boolean[100001];
		
		while(!found) {
			int size = queue.size();
			time++;
			
			while(--size>=0) {
				int current = queue.poll();
				visited[current] = true;
				
				if(current==K) {
					found = true;
					cnt++;
				}
				
				// 걷기
				int next = current - 1;
				if(next>=0 && !visited[next]) {
					queue.add(next);
				}
				
				next = current + 1;
				if(next<=100000 && !visited[next]) {
					queue.add(next);
				}
				
				// 순간이동
				next = current*2;
				if(next==current) continue;
				if(next>=0 && next<=100000 && !visited[next]) {
					queue.add(next);
				}
			}
		}
		
		System.out.println(time);
		System.out.println(cnt);
	}

}
