package com.ssafy.algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 카드2_2164 {
	/*
	 * 접근: FIFO구조이므로 queue를 사용해야합니다. 한바퀴 반복할때마다 절반의 카드가 사라지므로
	 * N*(N/2)*(N//4)*...1 = O(N^logN) 시간이 걸립니다(?). 틀린것 같습니다. 모르겠습니다.
	 * 
	 * 시간복잡도: O(N^logN)?
	 * 
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// get N
		int N = sc.nextInt();
		
		// generate queue
		Queue<Integer> queue = new LinkedList<>();
		for(int i=1;i<=N;i++)queue.add(i);
		
		// follow logic
		while(queue.size()!=1) {
			queue.remove();
			queue.add(queue.remove());
		}
		
		System.out.println(queue.remove());
		
	}

}
