package baekjoon.sep.sep04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 백준 17471 게리맨더링
 */
public class b17471 {

    static int N, min, total, population[];
    static boolean connection[];
    static ArrayList<ArrayList<Integer>> adjList;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        population = new int[N+1];  // 구역 별 인구 수
        connection = new boolean[N+1];  // 구역 연결 여부

        adjList = new ArrayList<>();    // 인접 구역 저장
        adjList.add(new ArrayList<>());

        // input
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            int p = Integer.parseInt(st.nextToken());
            population[i] = p;
            total += p; // 총 인구 수
            adjList.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                int to = Integer.parseInt(st.nextToken());
                // 양방향이므로 from - to, to - from 모두 저장
                adjList.get(to).add(i);
                adjList.get(i).add(to);
            }
        }
        min = total;
        subset(1);
        System.out.println((min == total)? -1: min);    // 만약 min이 total이라면 두 선거구로 나눌 수 없는 경우
    }

    // 부분집합 메소드
    private static void subset(int cnt) {
        if (cnt == N+1) {   // 부분집합 완성
            boolean[] checked = new boolean[cnt];
            int connCnt = 0;    // 선거구 개수
            for (int i = 1; i < cnt; i++) {
                if (!checked[i]) {
                    isConnect(i, checked);
                    connCnt++;
                }
            }

            // 선거구가 2개라면
            if (connCnt == 2) {
                int a = 0, b = 0;
                for (int i = 0; i < cnt; i++) {
                    if (connection[i]) a += population[i];
                    else b += population[i];
                }
                min = Math.min(min, Math.abs(a-b)); // 인구의 차이를 min에 저장
            }
            return;
        }

        connection[cnt] = true; // 해당 구역을 포함하고 재귀
        subset(cnt+1);
        connection[cnt] = false;    // 해당 구역을 포함하지 않고 재귀
        subset(cnt+1);
    }

    private static void isConnect(int num, boolean[] checked) {
        if (num > N) return;    // 해당 구역에서 처음 구역부터 마지막 구역까지 인접 여부를 확인했다면 return

        checked[num] = true;    // 고려했다는 표시
        for (int i = 1; i <= N; i++) {
            if (!checked[i] && connection[i] == connection[num] && adjList.get(num).contains(i)) {  // 연결되어있고 인접되어있는 도시라면
                isConnect(i, checked);  // 재귀
            }
        }
    }
}
