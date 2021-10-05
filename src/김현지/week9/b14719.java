package baekjoon.sep.sep30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 14719 빗물 [G5]
 *
 * 접근 방식:
 * 처음에는 스택으로 사용하려고 도전했다가 그게 아닌 것을 깨닫고
 * 이차원 배열을 만들어서 행별로 조사했습니다.
 * 블록이 존재하면 1, 없으면 0으로 배열을 초기화하고
 * 각 행의 0번 열부터 오른쪽으로 검사하면서 블록과 블록 사이의 빈 공간을 카운팅했습니다.
 *
 * 시간 복잡도:
 * O(H*W)
 *
 * 소요 시간:
 * 30분
 * 12352KB 92ms
 */

public class b14719 {

    static int result, H, W, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());   // 행
        W = Integer.parseInt(st.nextToken());   // 열
        map = new int[H][W];

        // 맵 만들기 -> 블록 쌓기
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < W; i++) {
            int block = Integer.parseInt(st.nextToken());
            while (block > 0) {
                map[H-block][i] = 1;
                block--;
            }
        }

        calcRain(); // 빗물을 계산한다
        System.out.println(result);
    }

    // 빗물 계산하는 메소드
    private static void calcRain() {
        for (int i = 0; i < H; i++) {   // 행별로 반복
            int j = 0;
            while (j < W) { // 열이 끝나기 전까지 while 문 순회
                if (map[i][j] == 1) {   // 블록이 존재한다면 빗물이 모아졌는지 확인
                    int cnt = 0;
                    // 다음 블록이 나올때까지 또는 좌표가 유효하지 않을 때까지 cnt++
                    while (++j < W && map[i][j] != 1) {
                        cnt++;
                    }
                    // 만약 다음 블록이 나와서 while이 끝났다면, 빗물이 모아졌으므로 result에 cnt 추가
                    if (j < W && map[i][j] == 1) result += cnt;
                } else {    // 블록이 비어있다면 열 + 1
                    j++;
                }
            }
        }
    }
}
