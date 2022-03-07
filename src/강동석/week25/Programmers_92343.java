package programmers;

// 양과 늑대
/*
 * greedy하게 접근
 * 1. 안 잡은 양 중에서 가장 가까운 양을 찾음.
 * 2. 그 양을 잡을 수 있는지 판단 후 잡을 수 있으면 중간 과정에서 늑대와 그 양을 모두 잡음.
 * 3. 잡은 양 카운트하고, 남은 목숨 계산함.
 * 4. 1~3 계속 반복하며 양을 다 잡거나 가장 가까운 양을 잡을 수 없는 상태이면 종료 
 * 위와 같은 로직으로 해결하려 하였으나, 로직이 잘못된건지 풀지 못했다ㅠㅠ
 */
public class Programmers_92343 {

	public static void main(String[] args) {
		int[] info = {0,0,1,1,1,0,1,0,1,0,1,1};
		int[][] edges = {{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}};
		System.out.println(solution(info, edges));
	}
	
	static int[] parents; 
	static boolean[] catched;
	
	public static int solution(int[] info, int[][] edges) {
		int length = info.length;
		catched = new boolean[length]; // 현재까지 모은 동물들을 체크
		catched[0] = true; // 0번 노드는 항상 true
		parents = new int[length]; // 각 노드의 부모 노드 인덱스를 저장
		for(int i=0,len=length-1; i<len; ++i) {
			parents[edges[i][1]] = edges[i][0]; // 부모 노드의 인덱스를 저장
		}
		catchNode(3);
		System.out.println(countToNode(5));
		int life = 1; // 목숨 = 모은 양의 수 - 모은 늑대 수
		int answer = 1; // 모은 양의 수
		while(true) {
			int minDist = 99; // 최소거리
			int idx = 0; // 해당 양의 인덱스
			for(int i=1; i<length; ++i) { // 모든 노드를 체크하면서(루트노드 제외)
				if(info[i]==0 && !catched[i]) { // 아직 잡히지 않은 양이면
					int count = countToNode(i);
					if(minDist > count) {
						minDist = count; // 최솟값 갱신
						idx = i; // 해당 노드의 인덱스 저장
					}
				}
			}
			if(life>minDist-1) { // min-1만큼의 늑대를 모아도 목숨이 살아있으면
				life -= minDist-2; // min-1만큼의 목숨을 빼고, 양을 1마리 얻으면 1증가 이므로
				catchNode(idx); // idx번째 노드까지 내려가면서 모두 catched에 체크하기
				answer++; // 모은 양의 수 1증가
			}else { // min만큼의 늑대를 모으면 목숨이 없으면
				break; // 종료
			}
		}
        return answer;
    }
	
	public static int countToNode(int nodeNum) { // nodeNum번 노드에서 catched한 노드까지의 거리를 반환
		int cnt = 1; // 도달하는 횟수
		while(true) {
			if(!catched[parents[nodeNum]]) { // 부모가 아직 안잡혔으면
				nodeNum = parents[nodeNum]; // 부모 노드로 거슬러 올라가기
				cnt++; // 횟수 1 증가
			}else {
				break;
			}
		}
		return cnt;
	}
	
	public static void catchNode(int nodeNum) {
		while(!catched[nodeNum]) { // catched되어있지 않으면
			catched[nodeNum]=true; // 모으기
			nodeNum = parents[nodeNum]; // 부모로 거슬러 올라가기
		}
	}
}