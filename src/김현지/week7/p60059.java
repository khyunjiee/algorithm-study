package programmers.sep.sep13;

/**
 * 프로그래머스 Lv.3 자물쇠와 열쇠
 *
 * 접근 방식:
 * 우선, key를 90도씩 돌려가면서 해당 키에 대한 완탐을 진행해야 한다고 생각했습니다.
 * 그런데 밖으로 나가도 자물쇠의 홈만 채워지면 되므로 완전히 확장해서 맵의 정 중앙에 기존 자물쇠의 홈이 위치하도록 하고,
 * 처음부터 key를 완전탐색하면서 정 중앙 부분이 1로 모두 채워지는 순간을 찾았습니다.
 *
 * 시간복잡도:
 * O(N^4)..? N^2 * M^2 정도 되는 것 같습니다.
 * N과 M이 20 이하이므로 완탐으로도 시간이 터질 것 같진 않았습니다.
 *
 * 소요 시간: 1시간 50분
 */

public class p60059 {
    public static void main(String[] args) {
        Solution_p60059 s = new Solution_p60059();
        System.out.println(s.solution(new int[][]{ {0, 0, 0}, {1, 0, 0}, {0, 1, 1} },
                                    new int[][]{ {1, 1, 1}, {1, 1, 0}, {1, 0, 1} }));
    }
}

class Solution_p60059 {
    public boolean solution(int[][] key, int[][] lock) {
        int n = key.length;
        int m = lock.length;
        int[][] secLock = new int[m * 3][m * 3];    // lock을 확장합니다.

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                secLock[i+m][j+m] = lock[i][j];     // 정 중앙에 입력으로 들어오는 lock을 위치시킵니다.
            }
        }

        for (int t = 0; t < 4; t++) {
            // key를 90도씩 회전해가면서 확인합니다.
            key = turn(key);

            for (int a = 0; a < m * 2; a++) {
                for (int b = 0; b < m * 2; b++) {
                    // 회전한 key를 하나씩 확인합니다. (n만큼 우측, 아래로 밀어가면서 확인)
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            secLock[a + i][b + j] += key[i][j];
                        }
                    }

                    // 만약 정답이라면 그대로 return
                    if (check(secLock)) return true;

                    // 확인했던 위치를 되돌립니다. (초기화 작업)
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            secLock[a + i][b + j] -= key[i][j];
                        }
                    }
                }
            }
        }
        return false;
    }

    // key를 90도씩 회전합니다.
    int[][] turn(int[][] key) {
        int[][] ret = new int[key.length][key[0].length];
        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret.length; j++) {
                ret[i][j] = key[key.length - 1 - j][i];
            }
        }
        return ret;
    }

    // 자물쇠의 홈에 키가 들어갔는지 확인
    boolean check(int[][] lock) {
        int count = 0;
        int len = lock.length / 3;
        // 매개변수로 들어오는 확장된 맵인 lock의 정 중앙만 확인
        // 만약 count가 len의 제곱이면 모두 채워진 것이므로 열쇠로 열 수 있다.
        for (int i = len; i < len * 2; i++) {
            for (int j = len; j < len * 2; j++) {
                if (lock[i][j] == 1) count++;
            }
        }
        return count == len * len;
    }
}
