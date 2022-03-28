package week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제: 최솟값 찾기
 * 링크: https://www.acmicpc.net/problem/11003
 * 
 * 풀이:
 * deque 사용
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */

public class BJ_11003_최솟값찾기_deque {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] A = new int[N];
		st = new StringTokenizer(in.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		Deque<int[]> deque = new ArrayDeque<int[]>();
		
		for (int i = 0; i < N; i++) {
			
			A[i] = Integer.parseInt(st.nextToken());
			
			// deque안에 있는 값들이 새로 넣는 값보다 작을때까지 빼내기
			while(!deque.isEmpty() && deque.peekLast()[0]>=A[i]) {
				deque.pollLast();
			}
			
			deque.offer(new int[] {A[i], i});
			
			while(deque.peek()[1]<=i-L) { // 슬라이딩 윈도우 범위를 넘는 값들을 빼기
				deque.poll();
			}
			
			int D = deque.peek()[0];
			
			sb.append(D + " ");
			
		}
		
		System.out.println(sb.toString());
	}

}
