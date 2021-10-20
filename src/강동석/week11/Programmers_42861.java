package programmers;

import java.util.Arrays;

// 섬 연결하기
/*
 * MST(최소신장트리) 문제이다. prim과 kruskal이 대표적이지만
 * 이 문제에서는 kruskal을 사용해보았다.
 */
public class Programmers_42861 {
	
	public static void main(String[] args) {
		int n = 4;
		int[][] costs = {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};
		System.out.println(solution(n, costs));
	}
	
	static class Edge implements Comparable<Edge> {
		int start, end, weight;
		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static int[] parents; // 부모원소를 관리(트리처럼 사용)
	private static void make() {
		parents = new int[V];
		// 모든 원소를 자신을 대표자로 만듦
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}
	private static int find(int a) {
		if(a == parents[a]) return a;
		return parents[a] = find(parents[a]); // 자신이 속한 집합의 대표자를 자신의 부모로 : path compression
	}
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	static int V,E;
	static Edge[] edgeList;
	
	public static int solution(int n, int[][] costs) {
		V = n; // 정점의 갯수
        E = costs.length; // 간선의 갯수
        edgeList = new Edge[E];
        for (int i = 0; i < E; i++) {
        	edgeList[i] = new Edge(costs[i][0], costs[i][1], costs[i][2]);
        }
        Arrays.sort(edgeList); // weight 기준 오름차순 정렬

		make(); // 모든 정점을 각각으로 집합으로 만듦

		int cnt=0, result=0;
		for (Edge edge : edgeList) { // 비용이 작은 간선부터 차례대로
			if(union(edge.start, edge.end)) { // 두 정점 사이에 경로가 없으면 연결
				result += edge.weight;
				if(++cnt == n-1) break; // n-1개의 간선을 선택했으면 신장트리 완성
			}
		}
        return result;
    }
}