package baekjoon.sep.sep06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 1941 소문난 칠공주
 *
 * 접근 방식:
 * 조합으로 풀었다.
 * 다솜이파는 1, 도연이파는 -1로 저장해서 sum의 음수 양수 여부로 유효성 체크를 진행했다.
 * sum이 양수라면 같은 파 학생들이 인접해있는지 체크한 후,
 * 인접해있다면 result를 추가한다.
 *
 * 시간 복잡도:
 * O(N!) -> 가지치기로 약간 줄어듦
 *
 * 소요시간: 1시간 30분
 */

public class b1941 {

    // 상 하 좌 우
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    static int[][] students = new int[5][5];    // 학생배열
    static boolean[] picked = new boolean[25];  // 25명 중 선택된 학생 배열
    static int result;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 다솜이면(S) 1, 도연이면(Y) -1
        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                char c = input.charAt(j);
                if (c == 'S') students[i][j] = 1;
                else students[i][j] = -1;
            }
        }

        pick(0, 0, 0);
        System.out.println(result);
    }

    // cnt: 현재까지 선택한 학생 수
    // index: 25명 중에 현재 학생 인덱스
    // sum: 다솜이파가 더 많은지 확인할 변수 (양수면 OK, 음수면 NO)
    private static void pick(int cnt, int index, int sum) {
        if (cnt == 7) { // 7명을 뽑았고
            if (sum > 0) find();    // 다솜이파의 인원이 더 많다면 학생들의 인접 여부를 체크
            return;
        }
        if (index > 24) return; // 인덱스가 24라면 모든 학생을 고려한 것

        picked[index] = true;   // 해당 학생을 선택했을 경우
        pick(cnt+1, index+1, sum+students[index/5][index%5]);   // index는 0부터 24까지이므로 학생 배열에는 index/5, index%5 로 접근해야 함
        picked[index] = false;  // 해당 학생을 선택하지 않았을 경우
        pick(cnt, index+1, sum);    // sum에 추가하지 않는다.
    }


    private static void find() {
        boolean[][] choice = new boolean[5][5];
        for (int i = 0; i < 25; i++) {
            choice[i/5][i%5] = picked[i];   // 선택된 학생들을 choice 배열에 담음
        }

        int[][] teamCheck = new int[5][5];
        int num = 1;
        boolean flag = true;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (teamCheck[i][j] == 0 && choice[i][j]) {
                    if (num > 1) {
                        flag = false;  // 인접하지 않은 경우 num이 2일때 확인된다.
                        continue;
                    }
                    isAdj(teamCheck, num, i, j, choice);
                    num++;
                }
            }
        }
        if (flag) result++; // 모두 인접했다면 경우의 수 +1
    }

    // 학생들의 인접 여부를 체크
    // 인접한 학생 위치를 모두 1로 표시
    private static void isAdj(int[][] teamCheck, int num, int r, int c, boolean[][] choice) {
        teamCheck[r][c] = num;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isValid(nr, nc) && choice[nr][nc] && teamCheck[nr][nc] == 0) {
                isAdj(teamCheck, num, nr, nc, choice);
            }
        }
    }

    // 배열 유효성 검사
    private static boolean isValid(int r, int c) {
        if (r>=0 && r<5 && c>=0 && c<5) return true;
        return false;
    }
}
