
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 별자리만들기_4386 {
	/*
	 * 접근: 최소신장트리 문제입니다. 별의 개수가 n개 일때 간선의 개수는 n*(n-1)/2 이고 n은 최대 100이므로 간선의 개수는
	 * 최대 4950개입니다. 따라서 크루스칼 알고리즘을 사용하여도 시간초과가 일어나지 않습니다.
	 * 
	 * 시간복잡도: O(NlogN)
	 * 
	 * 걸린 시간: 40min
	 */
	
	static class Edge implements Comparable<Edge>{
		int start, end;
		double distance;
		public Edge(int start, int end, double distance) {
			super();
			this.start = start;
			this.end = end;
			this.distance = distance;
		}
		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", distance=" + distance + "]";
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.distance, o.distance);
		}
	}
	
	// N개의 서로소집합 생성
	static void make() {
		for (int i = 0; i < N; i++) parents[i] = -1;
	}
	
	// 주어진 vetex의 대표자 찾기
	static int find(int a) {
		if(parents[a]<0) return a;
		return parents[a] = find(parents[a]);
	}
	
	// 합집합 연산
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot)return false;
		parents[aRoot] = bRoot;
		return true;
	}
	
	static double[][] stars;
	static int N;
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		StringTokenizer st;
		
		stars = new double[N][];
		Edge[] edges = new Edge[N*(N-1)/2];
		parents = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			stars[i] = new double[] { Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())};
		}
		
		
		// 완전 그래프의 간선 생성
		int idx=0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				edges[idx++] = new Edge(i,j, calcDistance(i, j));
			}
		}
		
		// 간선 오름차순 정렬
		Arrays.sort(edges);
		make(); // 모든 정점을 각각의 집합으로 만들기
		
		// 간선을 하나씩 선택하면서 사이클 여부 확인
		double result =0 ;
		int cnt=0;
		for(Edge edge: edges) {
			if(union(edge.start,edge.end)) { // 합집합이 가능하면
				result +=edge.distance; // 가중치 합산
				if(++cnt==N-1) break; // N-1개의 간선 선택시 그만
			}
		}
		
		System.out.println(Math.floor(result*100)/100);
	}
	
	// 주어진 주 vertex(별) 사이의 거리 계산
	static double calcDistance(int i, int j) {
		double x1= stars[i][0];
		double y1= stars[i][1];
		double x2= stars[j][0];
		double y2= stars[j][1];
		return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2));
	}

}
