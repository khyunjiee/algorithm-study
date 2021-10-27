package baekjoon.oct.oct27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 14391 종이조각 [G3]

접근 방식:
비트마스킹으로 풀었습니다.
전체 경우의 수는 1 << N*M 입니다.
한 칸당 가로 또는 세로로 갈 수 있으므로 2^(N*M) 이 됩니다.
해당 숫자에서 비트 0은 세로로 묶인 것, 비트 1은 가로로 묶인 것을 뜻합니다.
숫자를 합치는 방법은 (이전숫자 * 10 + 현재숫자) 가 됩니다.
비트마스킹이라서 글로는 설명이 어렵네요.. 설명 더 필요하시면 아이패드 꺼내보겠습니다.

소요 시간:
1시간
 */

public class b14391 {

    static int N, M, paper[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                paper[i][j] = input.charAt(j) - '0';
            }
        }

        int max = 0;
        for (int s = 0; s < (1 << (N*M)); s++) {
            int sum = 0;
            sum += horizontal(s);
            sum += vertical(s);
            if (sum > max) max = sum;
        }
        System.out.println(max);
    }

    // 세로로 묶은 숫자들을 다 더하기
    private static int horizontal(int s) {
        int sum = 0;
        // 행을 나타내는 i가 바깥 for문
        for (int i = 0; i < N; i++) {
            int tmp = 0;
            // 열을 나타내는 j가 안쪽 for문
            for (int j = 0; j < M; j++) {
                // 해당 칸이 세로로 묶였으면
                if ((s & (1 << i*M+j)) != 0) {
                    tmp = tmp * 10 + paper[i][j];
                }
                // 해당 칸이 가로로 묶였으면
                else {
                    sum += tmp;
                    tmp = 0;
                }
            }
            sum += tmp;
        }
        return sum;
    }

    // 가로로 묶은 숫자들을 다 더하기
    private static int vertical(int s) {
        int sum = 0;
        // 열을 나타내는 i가 바깥 for문
        for (int i = 0; i < M; i++) {
            int tmp = 0;
            // 행을 나타내는 j가 안쪽 for문
            for (int j = 0; j < N; j++) {
                // 해당 칸이 가로로 묶였을 경우
                if ((s & (1 << j*M+i)) == 0) {
                    tmp = tmp * 10 + paper[j][i];
                }
                // 해당 칸이 세로로 묶였을 경우 sum에 지금까지 구한 수를 더한 후 0으로 초기화
                else {
                    sum += tmp;
                    tmp = 0;
                }
            }
            sum += tmp;
        }
        return sum;
    }

}
