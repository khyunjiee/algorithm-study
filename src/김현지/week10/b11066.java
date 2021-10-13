package baekjoon.oct.oct12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
백준 11066 파일합치기 [G3]

접근 방식:
DP로 풀었습니다.
합친 결과로 봤을 때 무조건 이전 2개가 합쳐지는 형식이므로
start, end 값을 놓고 mid를 사이값으로 바꿔가면서 최소값을 dp 배열에 저장했습니다.

소요 시간:
1시간 30분

62872KB 344ms
 */

public class b11066 {

    static final int INF = 987654321;
    static int K, dp[][], chapter[], sum[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            K = Integer.parseInt(br.readLine());
            dp = new int[501][501];     // [start][end] start부터 end까지 합쳐지는데 걸리는 비용의 최소
            chapter = new int[501];     // 입력값으로 들어오는 챕터의 크기(비용)
            sum = new int[501];         // 이전까지의 챕터들의 비용을 모두 합친 값

            // chapter 초기화
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= K; i++) {
                chapter[i] = Integer.parseInt(st.nextToken());
            }

            // sum을 0~i 까지의 합으로 할당
            for (int i = 1; i <= K; i++) {
                if (i == 1) sum[i] = chapter[i];
                else sum[i] = sum[i - 1] + chapter[i];
            }

            // dp 계산
            calc();
            System.out.println(dp[1][K]);   // 1부터 K까지 챕터들을 합쳤을 때의 비용의 최소
        }
    }

    private static void calc() {
        // space: 간격 1이면 오른쪽 1칸, 2이면 오른쪽 2칸 ...
        for (int space = 1; space <= K; space++) {
            for (int start = 1; start+space <= K; start++) {
                int end = start + space;    // end는 start에 space 간격 떨어진 곳까지
                dp[start][end] = INF;       // 최대값으로 처리해놓는다

                // 두 부분으로 나누어서 생각
                // start~end 를 합칠 때 두 부분이기 때문에 mid를 그 사이 어딘가로 할당해가며 최소값을 dp 배열에 저장
                for (int mid = start; mid < end; mid++) {
                    dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid+1][end] + sum[end] - sum[start-1]);
                }
            }
        }
    }
}
