package programmers;

// 보행자 천국
/*
 * 초등학생 때 dp인줄도 모르고 풀었던 방식대로 풀었는데 해결하여서 좀 허무했다.
 * 기본적으로 제일 윗줄과 제일 왼쪽줄만 초기화 한 후 (현재 위치 경우의수) = (왼쪽에서 오는 경우의 수) + (윗쪽에서 오는 경우의 수)
 * 즉, dp[r][c] = dp[r-1][c]+dp[r][c-1] 이런 식으로 풀었다.
 * 다만, 왼쪽과 윗쪽이 직진만 가능하거나 통행불가인 경우만 따로 처리를 하였다.
 * 시간복잡도 : O(mn) m*n의 dp배열을 한번씩만 채워넣으면 끝!
 */
public class Programmers_1832 {

	public static void main(String[] args) {
		int m=3,n=6;
		int[][] cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
		System.out.println(solution(m, n, cityMap));
	}
	
	static int MOD = 20170805;
    public static int solution(int m, int n, int[][] cityMap) {
    	int[][][] dp = new int[m][n][2]; // [][][0 또는 1] : 0은 가로방향, 1은 세로방향
    	for(int c=0; c<n; ++c) { // 0행의 가로방향 채우기
    		if(cityMap[0][c]==1) break;
    		dp[0][c][0] = 1;
    	}
    	for(int r=0; r<m; ++r) { // 0열의 세로방향 채우기
    		if(cityMap[r][0]==1) break;
    		dp[r][0][1] = 1;
    	}
    	for(int r=1; r<m; ++r) {
    		for(int c=1; c<n; ++c) {
    			// 가로방향
    			if(cityMap[r][c-1]==0) { // 왼쪽칸이 자유롭게 통행 가능하면
    				dp[r][c][0]=(dp[r][c-1][0]+dp[r][c-1][1])%MOD; // 왼쪽칸의 가로와 세로 모두 더하기
    			}else if(cityMap[r][c-1]==2) { // 왼쪽칸이 직진만 가능하면
    				dp[r][c][0]=dp[r][c-1][0]; // 위쪽칸의 세로만 더하기
    			}
    			// 세로방향
    			if(cityMap[r-1][c]==0) { // 위쪽칸이 자유롭게 통행 가능하면
    				dp[r][c][1]=(dp[r-1][c][0]+dp[r-1][c][1])%MOD; // 위쪽칸의 가로와 세로 모두 더하기
    			}else if(cityMap[r-1][c]==2) { // 위쪽칸이 직진만 가능하면
    				dp[r][c][1]=dp[r-1][c][1]; // 위쪽칸의 세로만 더하기
    			}
    		}
    	}
        int answer = (dp[m-1][n-1][0]+dp[m-1][n-1][1])%MOD;
        return answer;
    }
}