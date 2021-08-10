package com.ssafy.algo.study.week3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질_1697 {
	/*
	 * 접근: 최단시간을 구해야하므로 bfs 문제임을 알 수 있습니다. 노드하나의 차수(경우의 수)
	 * 가 3개인 트리로 볼 수 있습니다. 한번의 이동에 1초가 걸리므로 트리의 높이를 계산해주어야 합니다.
	 * 
	 * 메모리초과 문제 발생 
	 * ->  bfs로 푸는 것은 맞고 bfs에서 큐는 필수 입니다. 문제가 되는 부분은
	 * 갔던 곳을 또가는 문제이므로 이를 메모해서 걸러주면 됩니다. 메모는 맵으로 하여 메모리낭비와 접근 
	 * 오버헤드를 줄입니다.
	 * -> 그래도 안되네요 좌표가 음수가 되는 경우도 배제 해야겠습니다.
	 * 
	 * 
	 * 시간복잡도: 알 수 없음 -> 고려하지 않아도 알아서 인풋값 적당히 주는것으로 간주 가능
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		System.out.println(bfs(N, K));
		
	}
	
	public static int bfs(int N, int K) {
		Map<Integer, Boolean> map = new HashMap<>();
		Queue<Integer> queue = new LinkedList<>();
		int time=0;
		int size;
		
		// 첫번째 위치
		queue.offer(N);
		int newN;
		while(true) {
			size = queue.size();
			while(--size>=0) {
				// 방문
				newN = queue.poll();
				// 해당 좌표 방문여부 확인 후 메모
//				if(map.containsKey(newN)) continue;
//				map.put(newN, true);
				
				if(newN<0|newN>100100) continue;
				
				if(newN == K) return time;
				queue.offer(newN-1);
				queue.offer(newN+1);
				queue.offer(newN*2);
			}
			time++; // 시간 증가
		}
	}

}
