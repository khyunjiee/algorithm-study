package week28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 트리 수정
 * 링크: https://www.acmicpc.net/problem/12912
 * 
 * 풀이:
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class BJ_12912_트리수정 {
	
	static int max = 0;
	static int[][] adjMatrix;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		adjMatrix = new int[N][N];
		int[][] logs = new int[N][];
		
		StringTokenizer st;
		for (int i = 0; i < N-1; i++) {
			 st = new StringTokenizer(in.readLine());
			 int from = Integer.parseInt(st.nextToken());
			 int to = Integer.parseInt(st.nextToken());
			 int weight = Integer.parseInt(st.nextToken());
			 
			 adjMatrix[from][to] = adjMatrix[to][from] = weight;
			 logs[i] = new int[] {from, to, weight};
		}
		
		
		// 간선을 하나씩 제거해보기
		for (int i = 0; i < N-1; i++) {
			int from = logs[i][0];
			int to = logs[i][1];
			int weight = logs[i][2];
			
			adjMatrix[from][to] = adjMatrix[to][from] = 0;
			
			// 각각의 트리의 지름 구하기
			int diameter1 = getDiameter(from);
			int diameter2 = getDiameter(to);
			// max값 갱신
			max = Math.max(max, diameter1 + diameter2 + weight);
			
			adjMatrix[from][to] = adjMatrix[to][from] = weight;
		}
		
		
		System.out.println(max);
		
	}
	
	static int farthestNode;
	static int longestPathLen;
	static boolean[] visited;
	
	private static int getDiameter(int node) {
		farthestNode = node;
		longestPathLen = 0;
		
		visited = new boolean[N];
		
		// dfs로 가장 먼거리에 있는 노드 구하기
		dfs(node, 0);
		
		longestPathLen = 0;
		visited = new boolean[N];
		// dfs로 가장 먼거리 구하기
		dfs(farthestNode, 0);
		
		return longestPathLen;
	}

	private static void dfs(int node, int len) {
		
		visited[node] = true;
		
		if(len>longestPathLen) {
			longestPathLen = len;
			farthestNode = node;
		}
		
		for (int i = 0; i < adjMatrix.length; i++) {
			if(!visited[i] && adjMatrix[node][i]>0) {
				dfs(i, len + adjMatrix[node][i]);
			}
		}
	}
}
