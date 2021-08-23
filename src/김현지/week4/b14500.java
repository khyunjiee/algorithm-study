package baekjoon.aug.aug21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 14500 테트로미노
 *
 * 접근 방식:
 * 입력으로 들어오는 배열의 크기를 [N+1][M+1] 로 설정해 좌측과 상단에 여유 공간을 두었습니다.
 * 입력으로 들어오는 값을 그대로 저장할 map 배열과
 * 각 행의 누적 값을 저장할 row,, 각 열의 누적 값을 저장할 col 배열을 같이 저장합니다.
 * 그 후에 테트로미노의 타입별로 가능한 경우의 수를 put 메소드에 case문으로 모든 경우를 체크합니다.
 *
 * 시간 복잡도:
 * O(N^2)
 * 완전탐색이라서 N*M의 시간복잡도가 발생하는데, 최댓값이 500*500=250000 이라서 충분하다고 생각했습니다.
 *
 */

public class b14500 {

    static int N, M, max, map[][], row[][], col[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());   // 행
        M = Integer.parseInt(st.nextToken());   // 열
        map = new int[N+1][M+1];                // 인풋으로 들어오는 기본 배열
        row = new int[N+1][M+1];                // 각 행의 누적합을 저장할 배열
        col = new int[N+1][M+1];                // 각 열의 누적합을 저장할 배열
        max = Integer.MIN_VALUE;                // 결과 리턴할 max 값

        // map, row, col 배열 초기화
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= M; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                row[i][j] = row[i][j-1] + num;
                col[i][j] = col[i-1][j] + num;
            }
        }

        tetromino();
        System.out.println(max);
    }

    // map 크기만큼 순회하면서 테트로미노 타입을 하나씩 놓아본다
    private static void tetromino() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int t = 0; t < 5; t++) {
                    max = Math.max(max, put(i, j, t+1));
                }
            }
        }
    }

    // 매개변수로 들어오는 테트로미노 타입을 맵에 배치했을 때 최댓값을 리턴
    // r,c: 현재 위치 type: 테트로미노 타입 (1~5 문제에 적힌 순서대로)
    private static int put(int r, int c, int type) {
        // 테트로미노 type 으로 switch 문
        switch (type) {
            case 1:
                int result = 0;
                if (isValid(r, c+3)) result = Math.max(result, rowSum(r, c, 3));    // 가로로 죽
                if (isValid(r+3, c)) result = Math.max(result, colSum(r, c, 3));    // 세로로 쭉
                return result;
            case 2:
                result = 0;
                // 사각형
                if (isValid(r, c+1) && isValid(r+1, c+1)) result = rowSum(r, c, 1) + rowSum(r+1, c, 1);
                return result;
            case 3:
                result = 0;
                if (isValid(r+2, c)) {      // 아래쪽으로 배치된 테트로미노 가능
                    int tempColSum = colSum(r, c, 2);
                    if (isValid(r, c+1)){   // 오른쪽으로 꺾인 테트로미노 가능
                        int numMax = Math.max(map[r][c+1], map[r+2][c+1]);
                        result = Math.max(result, tempColSum+numMax);
                    }
                    if (isValid(r, c-1)) {  // 왼쪽으로 꺾인 테트로미노 가능
                        int numMax = Math.max(map[r][c-1], map[r+2][c-1]);
                        result = Math.max(result, tempColSum+numMax);
                    }
                }
                if (isValid(r, c+2)) {      // 오른쪽으로 배치된 테트로미노 가능
                    int tempRowSum = rowSum(r, c, 2);
                    if (isValid(r+1, c)) {  // 아래로 꺾인 테트로미노 가능
                        int numMax = Math.max(map[r+1][c], map[r+1][c+2]);
                        result = Math.max(result, tempRowSum+numMax);
                    }
                    if (isValid(r-1, c)) {  // 위로 꺾인 테트로미노 가능
                        int numMax = Math.max(map[r-1][c], map[r-1][c+2]);
                        result = Math.max(result, tempRowSum+numMax);
                    }
                }
                return result;
            case 4:
                result = 0;
                if (isValid(r+2, c)) {      // 세로 테트로미노 가능
                    if (isValid(r, c+1)) result = Math.max(result, colSum(r, c, 1)+colSum(r+1, c+1, 1));    // 오른쪽으로 꺾인 테트로미노 가능
                    if (isValid(r, c-1)) result = Math.max(result, colSum(r, c, 1)+colSum(r+1, c-1, 1));    // 왼쪽으로 꺾인 테트로미노 가능
                }
                if (isValid(r+1, c)) {      // 가로 테트로미노 가능
                    if (isValid(r, c+2)) result = Math.max(result, rowSum(r, c, 1)+rowSum(r+1, c+1, 1));    // 오른쪽으로 향하는 테트로미노 가능
                    if (isValid(r, c-2)) result = Math.max(result, rowSum(r, c-1, 1)+rowSum(r+1, c-2, 1));  // 왼쪽으로 향하는 테트로미노 가능
                }
                return result;
            case 5:
                result = 0;
                if (isValid(r, c-1) && isValid(r, c+1)) {       // 가로 테트로미노 가능
                    if (isValid(r+1, c)) result = Math.max(result, rowSum(r, c-1, 2)+map[r+1][c]);  // ㅜ 모양
                    if (isValid(r-1, c)) result = Math.max(result, rowSum(r, c-1, 2)+map[r-1][c]);  // ㅗ 모양
                }
                if (isValid(r-1, c) && isValid(r+1, c)) {       // 세로 테트로미노 가능
                    if (isValid(r, c-1)) result = Math.max(result, colSum(r-1, c, 2)+map[r][c-1]);  // ㅓ 모양
                    if (isValid(r, c+1)) result = Math.max(result, colSum(r-1, c, 2)+map[r][c+1]);  // ㅏ 모양
                }

                return result;
        }
        return 0;
    }

    // 같은 행에서 누적합을 계산하는 메소드
    private static int rowSum(int r, int c, int cnt) {
        return row[r][c+cnt]-row[r][c-1];
    }

    // 같은 열에서 누적합을 계산하는 메소드
    private static int colSum(int r, int c, int cnt) {
        return col[r+cnt][c]-col[r-1][c];
    }

    // 맵에 왼쪽과 상단에만 패딩을 줬지만 테트로미노는 해당 범위 안에 속해야하기 때문에 유효성 체크
    private static boolean isValid(int r, int c) {
        if (r<1 || r>N || c<1 || c>M) return false;
        return true;
    }

}


