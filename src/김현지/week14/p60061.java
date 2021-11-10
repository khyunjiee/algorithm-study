package programmers.nov.nov10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p60061 {
    public static void main(String[] args) {
        Solution_p60061 solutionP60061 = new Solution_p60061();
//        [[1,0,0],[1,1,1],[2,1,0],[2,2,1],[3,2,1],[4,2,1],[5,0,0],[5,1,0]]
//        solutionP60061.solutionP60061(5, new int[][]{{1,0,0,1},{1,1,1,1},{2,1,0,1},{2,2,1,1},{5,0,0,1},{5,1,0,1},{4,2,1,1},{3,2,1,1}});
//        [[0,0,0],[0,1,1],[1,1,1],[2,1,1],[3,1,1],[4,0,0]]
        int[][] result = solutionP60061.solution(5, new int[][]{{0,0,0,1},{2,0,0,1},{4,0,0,1},{0,1,1,1},{1,1,1,1},{2,1,1,1},{3,1,1,1},{2,0,0,0},{1,1,1,0},{2,2,0,1}});
        System.out.println(Arrays.deepToString(result));
    }
}

/*
프로그래머스 Lv3 기둥과 보

접근 방식:
기둥 또는 보를 설치할 경우 설치할 수 있는지,
삭제할 경우 삭제했다고 가정한 후의 벽이 모두 유효한지 체크합니다.
특별한 알고리즘 없이 시뮬레이션으로 풀었습니다.

소요 시간:
1시간 30분
 */
class Solution_p60061 {
    private int n;
    private boolean[][][] build;

    public int[][] solution(int n, int[][] build_frame) {
        // [x][y][0] = 기둥 , [x][y][1] = 보
        this.build = new boolean[n+1][n+1][2];
        this.n = n;

        for (int[] frame: build_frame) {
            int fx = frame[0];
            int fy = frame[1];
            boolean isPillar = (frame[2] == 0);     // true: 기둥, false: 보
            boolean isBuild = (frame[3] == 1);      // true: 짓기, false: 삭제

            // 기둥 또는 보를 건설할 때
            if (isBuild) {
                if (isPillar && validPillar(fx, fy)) {
                    // 기둥을 세울 수 있음
                    build[fx][fy][0] = true;
                } else if (!isPillar && validBo(fx, fy)) {
                    // 보를 구축할 수 있음
                    build[fx][fy][1] = true;
                }
            }
            // 기둥 또는 보를 삭제할 때
            else {
                build[fx][fy][frame[2]] = false;
                // 해당 기둥이나 보를 주웠을 때 모든 기둥과 보가 유효하면 삭제 가능
                if (!checkValidation())
                    build[fx][fy][frame[2]] = true;
            }
        }

        return result();
    }

    // 해당 위치에 기둥을 세울 수 있는지
    private boolean validPillar(int x, int y) {
        // 바닥 설치 || 아래가 기둥일 때 || 보의 한쪽 끝 부분 위일 때
        return (y == 0) ||
                (isValid(x, y-1) && build[x][y-1][0]) ||
                (isValid(x-1, y) && build[x-1][y][1] || build[x][y][1]);
    }

    // 해당 위치에 보를 세울 수 있는지
    private boolean validBo(int x, int y) {
        // 한쪽 끝에 기둥이 있을 때 || 양쪽 끝이 보와 동시에 연결되어 있을 때
        return (isValid(x, y-1) && isValid(x+1, y-1) && build[x][y-1][0] || build[x+1][y-1][0]) ||
                (isValid(x-1, y) && isValid(x+1, y) && build[x-1][y][1] && build[x+1][y][1]);
    }

    // 좌표 유효성 검사
    private boolean isValid(int x, int y) {
        return x >= 0 && x <= n && y >= 0 && y <= n;
    }

    // 모든 N X N 장소의 기둥 또는 보가 유효한지 확인
    private boolean checkValidation() {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (build[i][j][0] && !validPillar(i, j)) return false;
                if (build[i][j][1] && !validBo(i, j)) return false;
            }
        }
        return true;
    }

    // 결과 배열 return
    private int[][] result() {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (build[i][j][0]) list.add(new int[]{i, j, 0});
                if (build[i][j][1]) list.add(new int[]{i, j, 1});
            }
        }
        return list.toArray(new int[list.size()][3]);
    }
}
