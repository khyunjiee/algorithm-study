package baekjoon.oct.oct09;

/*
백준 12100 2048(Easy) [G2]

접근 방식:
중복 순열과 스택을 사용해서 풀었습니다.
중복 순열로 미는 방향을 5가지를 찾고
게임을 시작했을 때 블록을 스택에 저장합니다.
스택에 저장할 때 pop() 으로 이전값을 확인하고 만약 현재 값과 같다면 이전 값의 2배를 스택에 푸시합니다.
블록을 더할 수 없는 경우라면 현재 값을 그대로 스택에 푸시합니다.
스택 제네릭은 int[2] 인데 0번 인덱스는 해당 블록의 넘버이고, 1번 인덱스는 블록이 더해졌는지 여부입니다. (0-변화없었음 1-더해진 결과임)

시간 복잡도:
O(n!) 인데 n이 5이기 때문에 시간은 충분하다고 생각했습니다.
이 상황에서 탐색하는 것은 5 * N^2 인데 N이 최대 20이므로 이것도 시간이 충분합니다.

소요 시간:
2시간
53732KB 464ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b12100 {

    static int N, map[][], max;
    // 상 하 좌 우 -> 비교하는 방향
    // 하 상 우 좌 로 밀었을때 위에 맞는 인덱스로 확인해 스택에 저장
    static int[][] delta = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        max = Integer.MIN_VALUE;

        // 맵 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (max < map[i][j]) max = map[i][j];
            }
        }

        perm(0, new int[5]);    // 순열을 구해서 max값 갱신
        System.out.println(max);     // 결과값 리턴
    }

    // 게임 진행 방향 중복 순열
    private static void perm(int cnt, int[] arr) {
        // 다섯 방향을 모두 선택했다면
        if (cnt == 5) {
            move(arr, copy());  // 움직여보기
            return;
        }

        for (int i = 0; i < 4; i++) {
            arr[cnt] = i;
            perm(cnt+1, arr);
        }
    }

    // 구한 순열에 맞게 방향 움직이기
    private static void move(int[] arr, int[][] temp) {
        for (int index: arr) {
            // 상 하 -> 컬럼 기준
            // index 0: 위쪽으로 비교, 1: 아래쪽으로 비교
            if (index < 2) upOrDown(temp, index, (index == 0)? N-1: 0);
            // 좌 우 -> 행 기준
            // index 2: 왼쪽으로 비교, 3: 오른쪽으로 비교
            else leftOrRight(temp, index, (index == 2)? N-1: 0);
        }
    }

    // 위로 올리기 또는 아래로 내리기
    private static void upOrDown(int[][] arr, int d, int start) {
        // int[] 인덱스 2 -> 0번: 블록 숫자, 1번: 이번 턴에 합침 여부(0: 변화 없음 1: 합친 결과)
        Stack<int[]> stack = new Stack<>();
        // 컬럼별로 행을 밀어가면서 찾음
        for (int j = 0; j < N; j++) {
            int nr = start;

            while (isValid(nr, j)) {            // 좌표가 유효하다면
                sumOrNo(stack, arr[nr][j]);     // 합칠 수 있는지 확인해서 스택에 푸시
                nr += delta[d][0];              // 좌표 옮기기
            }

            // 체크하면서 stack 값을 빼서 arr에 넣어주기
            int cnt = N;
            nr -= delta[d][0];
            while (isValid(nr, j) && cnt > 0) {
                if (cnt-- == stack.size()) arr[nr][j] = stack.pop()[0];
                else arr[nr][j] = 0;
                nr -= delta[d][0];
            }
        }
    }

    // 왼쪽으로 밀기 또는 오른쪽으로 밀
    private static void leftOrRight(int[][] arr, int d, int start) {
        // int[] 인덱스 2 -> 0번: 블록 숫자, 1번: 이번 턴에 합침 여부(0: 변화 없음 1: 합친 결과)
        Stack<int[]> stack = new Stack<>();
        // 행별로 컬럼을 밀어가면서 찾음
        for (int i = 0; i < N; i++) {
            int nc = start;
            while (isValid(i, nc)) {              // 좌표가 유효하다면
                sumOrNo(stack, arr[i][nc]);       // 합칠 수 있는지 확인해서 스택에 푸시
                nc += delta[d][1];                // 좌표 이동
            }

            // 체크하면서 stack 값을 빼서 arr에 넣어주기
            int cnt = N;
            nc -= delta[d][1];
            while (isValid(i, nc) && cnt > 0) {
                if (cnt-- == stack.size()) arr[i][nc] = stack.pop()[0];
                else arr[i][nc] = 0;
                nc -= delta[d][1];
            }
        }
    }

    // 합칠 수 있는지 확인해서 스택에 저장
    private static void sumOrNo(Stack<int[]> stack, int current) {
        // 값이 0이면 무시
        if (current != 0) {
            // 스택이 비었다면 0이 아닌 값을 푸시
            if (stack.isEmpty()) {
                stack.push(new int[]{current, 0});
            }
            // 스택이 차있다면 합칠 수 있는지 확인 필요
            else {
                int[] before = stack.pop();
                if (before[0] == current && before[1] == 0) {       // 값이 같고, 한번도 합친 적이 없어 합칠 수 있다면
                    stack.push(new int[]{current * 2, 1});          // 스택에 푸시. 합쳤다는 뜻으로 1번 인덱스에 1 할당
                    if (max < current * 2) max = current * 2;       // max값 갱신
                } else {
                    stack.push(before);                             // 값이 다르다면 이전 값과 현재 값 순서대로 스택에 푸시
                    stack.push(new int[]{current, 0});
                }
            }
        }
    }

    // 배열 복사
    private static int[][] copy() {
        int[][] temp = new int[N][N];
        for (int i = 0; i < N; i++) {
            temp[i] = Arrays.copyOf(map[i], N);
        }
        return temp;
    }

    // 좌표 유효성 검사
    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
