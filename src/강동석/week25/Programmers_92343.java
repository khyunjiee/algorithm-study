package programmers;

import java.util.ArrayList;

// 양과 늑대
/*
 * info의 길이가 2~17로 이진트리 노드의 갯수가 최대 17개이므로 거의 완전탐색을 하여도 시간걱정을 할 필요가 없는 문제이다.
 * dfs를 이용하여 완탐을 하면서, 기존에 갈 수 있는 자식 노드들의 정보를 다음 dfs탐색이 같이 넘겨주면,
 * 매 dfs마다 갈 수 있는 자식 노드들의 정보를 가지고 있으면서 완탐을 하게 된다.
 */
public class Programmers_92343 {

	public static void main(String[] args) {
		int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
		int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
		System.out.println(solution(info, edges));
	}
	
	static int max;
	static int[] infos;
	static ArrayList<Integer>[] adjList;
	
	public static int solution(int[] info, int[][] edges) {
		infos = info;
		int length = info.length;
		adjList = new ArrayList[length];
		for(int i=0; i<length; ++i) {
			adjList[i] = new ArrayList<Integer>();
		}
		for(int[] edge : edges) {
			adjList[edge[0]].add(edge[1]);
		}
		ArrayList<Integer> start = new ArrayList<Integer>();
		start.add(0); // 0번 노드만 포함하여 시작
		max=0;
		dfs(0,0,0,start);
        return max;
    }
	
	public static void dfs(int curNode, int sheep, int wolves, ArrayList<Integer> childList) {
		if(infos[curNode]==0) sheep++; // 해당 노드가 양이면 1마리 증가
		else wolves++; // 아니면 늑대 1마리 증가
		
		if(wolves>=sheep) { // 늑대가 양보다 같거나 많으면 종료
			return;
		}
		if(max<sheep) { // 최댓값 갱신
			max = sheep;
		}
		ArrayList<Integer> nextList = new ArrayList<Integer>();
		for(int nextNode : adjList[curNode]) {
			nextList.add(nextNode); // 현재 노드의 새로운 자식노드들을 추가
		}
		for(int nextNode : childList) { // 이전의 갈 수 있는 자식노드들에 대하여
			if(nextNode!=curNode) nextList.add(nextNode); // 현재 노드가 아니면 자식 노드를 전부 합치기 
		}
		for(int nextNode : nextList) {
			dfs(nextNode, sheep, wolves, nextList); // 자식 노드들을 전부 방문
		}
	}
}