package programmers;

// 파괴되지 않은 건물
/*
 * 당시 코테에서는 효율성을 해결하지 못했다.
 * 하지만 이번에 생각보다 어렵지 않게 누적합 문제라는것을 알 수 있었다.
 * 백준에서 비슷한 유형의 문제를 풀어본 것이 도움이 된 것 같다.
 * 역시 알고리즘 문제를 꾸준히 풀다보면 실력이 조금이나마 향상된 것 같아 뿌듯하다.
 * 풀이법
 * 1. 누적합의 경계설정
 * 2. 가로방향 누적합 계산 and 세로방향 누적합 계산(서로 순서는 바뀌어도 무방)
 * 3. 누적합과 원래의 내구도와 비교
 * 시간복잡도: O(K+NM) => skill의 수만큼 경계 설정 4*K + 누적합2번 2*N*M + board와 누적합 전체 비교 N*M = 4K+3NM
 */
public class Programmers_92344 {

	public static void main(String[] args) {
		int[][] board = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
		int[][] skill = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
		System.out.println(solution(board, skill));
	}
	
	public static int solution(int[][] board, int[][] skill) {
		int R = board.length; // 행의 길이
		int C = board[0].length; // 열의 길이
		int[][] sum = new int[R][C]; // 누적합의 배열
		for(int i=0,len=skill.length; i<len; ++i) {
			int type = skill[i][0];
			int r1 = skill[i][1];
			int c1 = skill[i][2];
			int r2 = skill[i][3];
			int c2 = skill[i][4];
			int degree = skill[i][5];
			// 누적합의 경계 설정
			if(type==1) { // 공격
				sum[r1][c1] -= degree;
				if(c2+1<C) sum[r1][c2+1] += degree;
				if(r2+1<R) sum[r2+1][c1] += degree;
				if(r2+1<R && c2+1<C) sum[r2+1][c2+1] -= degree;
			}else { // 회복
				sum[r1][c1] += degree;
				if(c2+1<C) sum[r1][c2+1] -= degree;
				if(r2+1<R) sum[r2+1][c1] -= degree;
				if(r2+1<R && c2+1<C) sum[r2+1][c2+1] += degree;
			}
		}
		int answer = 0; // 생존한 건물 수
		// 왼 -> 오 방향으로 누적합 계산
		for(int r=0; r<R; ++r) {
			for(int c=1; c<C; ++c) {
				sum[r][c] += sum[r][c-1]; // 왼쪽칸의 누적합을 더함
			}
		}
		// 위 -> 아래 방향으로 누적합 계산
		for(int r=1; r<R; ++r) {
			for(int c=0; c<C; ++c) {
				sum[r][c] += sum[r-1][c]; // 위쪽칸의 누적합을 더함
			}
		}
		// 누적합과 건물 내구도를 비교하여 갯수 체크
		for(int r=0; r<R; ++r) {
			for(int c=0; c<C; ++c) {
				if(board[r][c]+sum[r][c]>0) answer++;
			}
		}
		return answer;
    }
}