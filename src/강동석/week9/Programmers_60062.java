package programmers;

// 외벽 점검
/*
 * 경우의 수가 적기 때문에 모든 시작점을 기준으로
 * 모든 사람이 순서를 다르게 투입하는 모든 경우의 수를 따지는 완전탐색 문제이다.
 * 처음에 가장 긴 간선을 제거한 하나의 케이스로만 모든 순열을 따져보면 되는 줄 착각하여 시간이 많이 걸렸다.
 * 시간복잡도 : 경우의수가 작아서 따지지 않았다.
 */
public class Programmers_60062 {

	int weak_length,dist_length,result,N;
	boolean[] used;
	public int solution(int n, int[] weak, int[] dist) {
		N = n;
        weak_length = weak.length;
        dist_length = dist.length;
        int[][] weak_cases = new int[weak_length][weak_length]; //  각 지점마다 시작하는 케이스를 전부 생성
        weak_cases[0] = weak; // 쳣 케이스는 주어진 케이스
        for(int i=1; i<weak_length; ++i) {
        	weak_cases[i] = makeNextCase(weak_cases[i-1]); // 모든 케이스를 생성
        }
        used = new boolean[dist.length]; // dfs를 이용하기 위한 배열
        result = Integer.MAX_VALUE;
        for(int i=0; i<weak_length; ++i) { // 각 케이스마다
        	for(int j=0; j<dist_length; ++j) { // 각 인원을 시작으로하는 순열 dfs
        		dfs(j,0,1,weak_cases[i],dist);
        	}
        }
        if(result==Integer.MAX_VALUE) result = -1; // 정답이 없으면 -1
        return result;
    }
	public int[] makeNextCase(int[] weak) {
		int[] newCase = new int[weak_length];
		for(int i=0; i<weak_length-1; ++i) {
			newCase[i] = weak[i+1];
		}
		newCase[weak_length-1] = weak[0]+N;
		return newCase;
	}
	public void dfs(int num, int idx, int depth, int[] weak, int[] dist) { // 현재 num번째 인원이 투입, weak[idx]부터 채우기 시작, 총 depth명 투입
		
		int nextIdx = idx;
		while(weak[nextIdx]<=weak[idx]+dist[num]) { // num번쨰 인원이 닿지않는 인데스를 찾을 때 까지
			nextIdx++; // 다음 취약지점의 인덱스
			if(nextIdx==weak_length) { // 마지막 취약지점까지 거리가 닿으면 
				if(result>depth) result = depth; // 최솟값 갱신 후 종료
				return;
			}
		}
		
		if(depth==dist_length) return; // 모든 인원을 투입했는데 마지막 취약지점까지 닿지않으면 종료
		
		used[num] = true;
		for(int i=0; i<dist_length; ++i) {
			if(!used[i]) {
				dfs(i,nextIdx,depth+1,weak,dist); // i번쨰 인원이 weak[nextIdx]부터 점검 시작
			}
		}
		used[num] = false;
	}
}