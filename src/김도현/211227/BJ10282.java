package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 10282. 해킹
 * ["컴퓨터 a가 다른 컴퓨터 b에 의존","총 감염된 컴퓨터","걸리는 시간"] => 그래프 알고리즘(방문 가능 여부,cost)
 * MST? Dijkstra? => cost의 합계 X => Dijkstra
*/

public class BJ10282 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int N, D, C, A, B, S;
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); 
			D = Integer.parseInt(st.nextToken()); 
			C = Integer.parseInt(st.nextToken());
			
			ArrayList<int[]>[] nodeList = new ArrayList[N+1];
			for (int i = 0; i < N + 1; i++) nodeList[i] = new ArrayList<int[]>();
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				A = Integer.parseInt(st.nextToken()); 
				B = Integer.parseInt(st.nextToken()); 
				S = Integer.parseInt(st.nextToken());
				nodeList[B].add(new int[] {A, S});
			}
			
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> o1[1] - o2[1]);
			pq.add(new int[] {C, 0});
			int[] cost = new int[N+1];
			Arrays.fill(cost, Integer.MAX_VALUE);
			cost[C] = 0;
			int[] cur;
			int temp;
			
			while (!pq.isEmpty()) {
				cur = pq.poll();
				if (cost[cur[0]] < cur[1]) continue;
				for (int[] node : nodeList[cur[0]]) {
					temp = cur[1] + node[1];
					if (temp < cost[node[0]]) {
						cost[node[0]] = temp;
						pq.add(new int[] {node[0], temp});
					}
				}
			}
			
			int resNum = 0, resCost = 0;
			for (int c : cost) {
				if (c != Integer.MAX_VALUE) {
					resNum++;
					if (c > resCost) resCost = c;
				}
			}
			
			System.out.println(resNum + " " + resCost);
			
		}
	}

}
