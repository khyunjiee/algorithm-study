package programmers;

import java.util.ArrayList;

// 자물쇠와 열쇠
/*
 * lock에서 홈 부분만 포함하는 최소의 직사각형을 구한 후
 * key에서 돌기부분만 따로 좌표를 저장하여, key의 전체 좌표에 대해서 lock을 맞춰보며 완탐한다.
 * 서로 맞지 않을 경우 90도 회전해서 다시 시도하여 총 4번의 방향으로 시도한다.
 * 홈이 맞을 경우 홈에 들어간 돌기의 수와 lock에서 전체 채워져야하는 홈의 수가 맞으면 딱 맞는것이므로 true반환한다.
 */
public class Programmers_60059 {

	int N,M;
	int lockHole = 0; // lock의 홈부분의 갯수
	ArrayList<int[]> keyPos = new ArrayList<int[]>(); // key에서 돌기부분의 좌표만 저장
	
	public boolean solution(int[][] key, int[][] lock) {
		N = lock.length;
		M = key.length;
		int sr,sc,er,ec; // start row, ~, ~, end column
		sr = sc = N-1;
		er = ec = 0;
		// lock에서 홈인 부분을 전부 포함하는 최소크기의 직사각형 만드는 작업
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(lock[i][j]==0) { // lock의 모든 칸을 돌면서 홈이면
					lockHole++;
					sr = Math.min(sr, i);
					sc = Math.min(sc, j);
					er = Math.max(er, i);
					ec = Math.max(ec, j);
				}
			}
		}
		
		// lock의 홈 부분의 최소 직사각형보다 key의 사이즈가 더 작으면 무조건 홈을 다 채울 수 없으므로 false
		int width = ec-sc+1; // 자물쇠 홈 사각형의 가로길이
		int height = er-sr+1; // 자물쇠 홈 사각형의 세로길이
		if(width>M || height>M) return false;
		
		// key에서 돌기부분의 좌표만 저장
		for(int i=0; i<M; ++i) {
			for(int j=0; j<M; ++j) {
				if(key[i][j]==1) { // key에서 돌기이면
					keyPos.add(new int[] {i,j});
				}
			}
		}

		for(int t=0; t<4; ++t) { // 90도씩 회전하며 총 4번 실행
			for(int i=sr; i>er-M; --i) {
				for(int j=sc; j>ec-M; --j) {
					if(check(i,j,lock)) return true; // 맞으면 true반환 후 종료
				}
			}
			// 오른쪽 90도 회전하기
			rotate90();
		}
        return false;
    }
	public boolean check(int r, int c, int[][] lock) { // (r,c)를 key의 좌측상단으로 시작하여 lock과 비교
		int cnt = 0;
		int size = keyPos.size();
		for(int i=0; i<size; ++i) {
			int[] pos = keyPos.get(i); // key의 돌기부분을 꺼내서
			int nr = r+pos[0];
			int nc = c+pos[1];
			if(isValid(nr,nc)) { // key의 돌기부분이 lock의 범위안에 들어오면
				if(lock[nr][nc]==0) cnt++; // lock의 홈부분과 일치하면
				else return false; // lock의 돌기부분과 충돌하면
			}
		}
		// 돌기부분이 서로 충돌하지 않았을 때
		if(cnt == lockHole) return true; // lock의 홈을 충족시킨 수가 lock의 전체 홈의 갯수와 같으면
		else return false;
	}
	public boolean isValid(int r, int c) {
		if(r>=0 && c>=0 && r<N && c<N) return true;
		else return false;
	}
	public void rotate90() {
		int size = keyPos.size();
		for(int i=0; i<size; ++i) {
			keyPos.set(i, new int[] {keyPos.get(i)[1],M-1-keyPos.get(i)[0]}); // 오른쪽 90도 회전한 좌표로 update
		}
	}
}