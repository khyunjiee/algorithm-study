package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ20055 {
    /* 20055. 컨베이어 벨트 위의 로봇
    시뮬레이션
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] A = new int[2*N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2*N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        int up = 0, down = N-1, zero = 0, step = 1, pre, loc, next;
        ArrayList<Integer> robot = new ArrayList<>();
        while (true) {
            if (--up < 0) up += (2 * N);
            if (--down < 0) down += (2 * N);
            for (int i = 0; i < robot.size(); i++) {
                if (robot.get(i) == down) robot.remove(i--);
            }
            pre = -1;
            for (int i = 0; i < robot.size(); i++) {
                loc = robot.get(i);
                next = (loc + 1) % (2 * N);
                if (pre != next && A[next] >= 1) {
                    if (--A[next] == 0) zero++;
                    if (next == down) {
                        robot.remove(i--);
                        continue;
                    }
                    robot.set(i, next);
                    pre = next;
                }else pre = loc;
            }

            if (A[up] >= 1) {
                if (--A[up] == 0) zero++;
                robot.add(up);
            }

            if (zero >= K) break;
            step++;
        }
        System.out.println(step);
    }
}
