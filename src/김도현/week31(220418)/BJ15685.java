package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ15685 {
    /* 15685. 드래곤 커브
    시뮬레이션
    */
    private static final int MAX_SIZE = 101;
    private static final int[] dr = new int[] {0, -1, 0, 1};
    private static final int[] dc = new int[] {1, 0, -1, 0};

    public static class Curve {
        int x, y, d, g;
        public Curve(int y, int x, int d, int g) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.g = g;
        }
    }

    public static void generateCurve(boolean[][] map, Curve curve) {
        ArrayList<int[]> arrayList = new ArrayList<>();
        int nx = curve.x, ny = curve.y;
        int[] orig, cur;
        map[nx][ny] = true;
        arrayList.add(new int[] {nx, ny});
        nx += dr[curve.d];
        ny += dc[curve.d];
        map[nx][ny] = true;
        arrayList.add(new int[] {nx, ny});

        int len, dx, dy;
        for (int i = 0; i < curve.g; i++) {
            len = arrayList.size();
            orig = arrayList.get(len - 1);
            for (int j = len - 2; j >= 0; j--) {
                cur = arrayList.get(j);
                nx = orig[0] + (cur[1] - orig[1]);
                ny = orig[1] + (orig[0] - cur[0]);
                map[nx][ny] = true;
                arrayList.add(new int[] {nx, ny});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[MAX_SIZE][MAX_SIZE];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            generateCurve(map, new Curve(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        int result = 0;
        for (int i = 0; i < MAX_SIZE-1; i++)
            for (int j = 0; j < MAX_SIZE-1; j++)
                if (map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) result++;
        System.out.println(result);
    }
}
