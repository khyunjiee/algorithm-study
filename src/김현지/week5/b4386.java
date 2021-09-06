package baekjoon.aug.aug26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 4386 별자리 만들기
 *
 * 접근 방식:
 * prim 알고리즘을 활용해서 풀었습니다.
 * 두 점 사이의 좌표는 Math 메소드를 적극 활용해서 계산했습니다.
 * 입력 좌표들이 실수값이기 때문에 오버플로를 고려해 double로 계산했습니다.
 *
 * 시간 복잡도:
 * O(N^2)
 *
 */

public class b4386 {

    static int N;
    static double points[][], min[], result;
    static boolean visit[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new double[N][2];
        min = new double[N];
        visit = new boolean[N];

        // points, min 배열 초기화
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            points[i][0] = Double.parseDouble(st.nextToken());
            points[i][1] = Double.parseDouble(st.nextToken());
            min[i] = Double.MAX_VALUE;
        }

        prim();
        System.out.println(Math.round(result*100)/100.0);

    }

    private static void prim() {
        min[0] = 0; // 시작 정점 min 0 처리

        for (int i = 0; i < N; i++) {
            // 현재 위치에서의 최소값과 해당 인덱스를 찾는다.
            int minIndex = -1;
            double minValue = Double.MAX_VALUE;
            for (int j = 0; j < N; j++) {
                if (!visit[j] && minValue > min[j]) {
                    minIndex = j;
                    minValue = min[j];
                }
            }

            // 방문 처리 후 최소값을 result에 더한다.
            visit[minIndex] = true;
            result += minValue;
            // 해당 최소값의 좌표를 가져온다.
            double[] point = points[minIndex];

            for (int j = 0; j < N; j++) {
                // 방문하지 않은 정점들 중에 min 값이 두 점 사이의 거리보다 큰 경우 min을 갱신해준다.
                if (!visit[j]) {
                    double d = distance(point[0], points[j][0], point[1], points[j][1]);
                    if (min[j] > d) min[j] = d;
                }
            }
        }
    }

    // 두 점 사이의 거리를 측정하는 메소드
    private static double distance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(Math.abs(x1-x2), 2) + Math.pow(Math.abs(y1-y2), 2));
    }

}
