package baekjoon.aug.aug25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백준 17144 미세먼지 안녕! (시뮬레이션)
 *
 * 접근 방식:
 * T가 1씩 감소될 때마다 미세먼지 위치를 리스트에 저장해서 확산시킵니다.
 * 미세먼지 위치와, 미세먼지량을 저장하고 있으면 기존 map을 복사하지 않고 덮어씌울 수 있기 때문에
 * Dust 클래스를 만들어서 List로 관리했습니다.
 * 그 후에는 공기청정기에 맞게 배열을 적절히 돌려가면서 구현했습니다.
 *
 * 시간 복잡도:
 * 최대가 이중포문이라서 R*C -> O(N^2)
 */

public class b17144 {

    static int R, C, airCleaner[][], map[][];
    // 우 하 좌 상
    static int[] dr = { 0, 1, 0, -1 };
    static int[] dc = { 1, 0, -1, 0 };

    // 미세먼지가 위치하는 좌표와 미세먼지 양
    static class Dust {
        int r, c, quantity, cnt;

        public Dust(int r, int c, int quantity) {
            this.r = r;
            this.c = c;
            this.quantity = quantity;
        }

        // 미세먼지 확산 시 확산량
        public int spread() {
            cnt++;
            return quantity/5;
        }

        // 확산 완료 후 해당 미세먼지 위치에서 감소해야할 양
        public int reduce() {
            return (quantity/5) * cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        /* input 처리부분 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        airCleaner = new int[2][2]; // 공기청정기 좌표

        int index = 0;
        // 초기 맵 세팅 & 공기청정기 위치 저장
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < C; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (num == -1) {
                    airCleaner[index++] = new int[]{i, j};
                }
            }
        }
        /* input 처리부분 */

        for (int t = T; t > 0; t--) {
            // 미세먼지 확산
            diffusing(dustsInfo()); // 먼지 정보를 갖는 List를 매개변수로 확산시킨다.

            // 공기청정기 작동
            freshAir();
        }

        System.out.println(totalDust());
    }

    // 현재 map에 위치한 미세먼지 좌표와 미세먼지량을 저장해 반환
    private static List<Dust> dustsInfo() {
        List<Dust> result = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    result.add(new Dust(i, j, map[i][j]));
                }
            }
        }

        return result;
    }

    // 미세먼지 확산 -> 따로 미세먼지 배열을 만들었기 때문에, 기존 map에 업데이트가 가능하다.
    private static void diffusing(List<Dust> dusts) {
        // 매개변수로 들어오는 미세먼지 정보들을 순회하면서 확산시킨다.
        for (Dust dust: dusts) {
            int r = dust.r;
            int c = dust.c;

            // 4방향 모두 체크 후 확산
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (isValid(nr, nc) && map[nr][nc] >= 0) {  // 확산 가능하면
                    map[nr][nc] += dust.spread();   // 해당 위치의 값에 미세먼지 확산량을 더해서 저장
                }
            }

            map[r][c] -= dust.reduce(); // 미세먼지가 있는 위치에는 확산량만큼 감소시킴
        }
    }

    // 공기청정기 가동
    private static void freshAir() {
        wind(airCleaner[0][0], airCleaner[0][1], false);    // 위쪽 공기 청정
        wind(airCleaner[1][0], airCleaner[1][1], true);     // 아래쪽 공기 청정
    }

    // 공기청정기 바람에 의해 부분 배열이 회전
    private static void wind(int r, int c, boolean isClock) {
        int d = 0;
        int num = 0;    // 공기청정기에서 나온 정화된 공기

        while (true) {
            r += dr[d];
            c += dc[d];
            if (map[r][c] == -1) break; // 만약 다음이 -1이면 break

            // 다음이 -1 (공기청정기) 이 아니면 값을 미룬 후 원래 값을 num에 넣음
            int temp = map[r][c];
            map[r][c] = num;
            num = temp;

            if (!isValid(r+dr[d], c+dc[d])) {   // 만약 다음 좌표가 map 밖에 나가면, 방향을 바꿈
                if (isClock) {  // 시계방향이면 0-1-2-3
                    d++;
                } else {        // 반시계방향이면 0-3-2-1
                    d = (d == 0)? 3: d-1;
                }
            }
        }
    }

    // 총 미세먼지량 구하기
    private static int totalDust() {
        int result = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) result += map[i][j];
            }
        }

        return result;
    }

    // 유효한 좌표 인덱스 구하기
    private static boolean isValid(int nr, int nc) {
        if (nr>=0 && nr<R && nc>=0 && nc<C) return true;
        return false;
    }
}
