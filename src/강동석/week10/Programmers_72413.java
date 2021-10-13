package programmers;

// 합승 택시 요금
/*
 * 1. 플로이드-와샬 알고리즘을 적용하여 정점간의 최소비용을 구함.
 * 2. 정점 s에서 임의의 정점 i(1~n)까지 합승하고, i부터 흩어져서 각각 a와 b까지 도착한다고 가정
 * 3. i를 1부터 n까지 돌면서 최소비용(D[s][i] + D[i][a] + D[i][b])을 구함.
 * 플로이드-와샬 알고리즘을 알고나니 크게 어렵지는 않은 문제였다. 몰랐으면 아마 엄청 시간이 걸렸을 것 같다.
 * 시간복잡도: O(N^3), 플로이드-와샬의 시간복잡도, N이 최대 200이므로 시간초과는 걱정하지 않았다.
 */
public class Programmers_72413 {

	public int solution(int n, int s, int a, int b, int[][] fares) {
        // 플로이드 와샬
		int[][] D = new int[n][n];
		final int INF = 99999999; // 간선 비용 10만이 최대 200개 있으므로 2천만을 넘는 INF로 설정
		int length = fares.length;
		for(int i=0; i<length; ++i) {
			int from = fares[i][0]-1;
			int to = fares[i][1]-1;
			int fare = fares[i][2];
			D[from][to] = D[to][from] = fare; // 초기 인접행렬 생정
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 자기자신으로가 아니고 못가면 max로 놓고 시작
				if (i != j && D[i][j] == 0) D[i][j] = INF;
			}
		}

		// 플로이드 워샬 알고리즘 적용
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				if (i == k) continue; // 출발지 = 경유지상황 : 경유효과 없음.
				for (int j = 0; j < n; j++) {
					if (i == j) continue; // 출발지 = 도착지비용은 항상 0이라고 가정한다면
					if (D[i][k] != INF && D[k][j] != INF && D[i][j] > D[i][k] + D[k][j]) {
						D[i][j] = D[i][k] + D[k][j];
					}
				}
			}
		}

		int S = s-1;
		int A = a-1;
		int B = b-1;
		int answer = Integer.MAX_VALUE;
		for(int i=0; i<n; ++i) {
			if(answer > D[S][i] + D[i][A] + D[i][B]) answer = D[S][i] + D[i][A] + D[i][B];
		}
        return answer;
    }
}