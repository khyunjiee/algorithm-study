package com.ssafy.algo.study.week11;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class 섬연결하기_42861 {
	/*
	 * 접근:
	 * 크루스칼
	 * 
	 * 시간복잡도:
	 * O(nlogn)
	 * 
	 * 풀이에 걸린 시간:
	 * 30분
	 * 
	 * 
	 */

	public static void main(String[] args) {
		System.out.println(solution(4,new int[][] {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}}));
	}
	
	static class Edge implements Comparable<Edge>{
        int from, to, weight;
        public Edge(int from,int to,int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
    }
    
    static private void make(int n){
        parent = new int[n];
        for(int i=0;i<n;i++) parent[i] = i;
    }
    
    static private int find(int a){
        if(a==parent[a]) return a;
        return parent[a] = find(parent[a]);
    }
    
    static private boolean union(int a, int b){
        int rootA =  find(a);
        int rootB =  find(b);
        if(rootA==rootB) return false;
        parent[rootB] = rootA;
        return true;
    }
    
    static int[] parent;
    public static int solution(int n, int[][] costs) {
        List<Edge> edgeList = new LinkedList<>();
        // Edge 생성
        for(int[] cost:costs){
            edgeList.add(new Edge(cost[0], cost[1], cost[2]));
        }
        
        // sort
        Collections.sort(edgeList);
        
        // union find
        make(n);
        
        int cnt=0, cost=0;
        for (int i = 0; i < edgeList.size(); i++) {
        	Edge edge = edgeList.get(i);
			if(union(edge.from, edge.to)) {
				cnt++;
				cost += edge.weight;
			}
			if(cnt==n-1) break;
		}
        
        return cost;
    }

}
