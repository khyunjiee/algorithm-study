package baekjoon.aug;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 백준 3190 뱀
 *
 * 접근방법 :
 * 우선 뱀은 LinkedList로 관리한다.
 * 머리부터 꼬리까지가 어느 위치에 있는지 Point 클래스를 만들어서 위치를 저장한다.
 * 그 후 LinkedList에 머리부터 차례대로 넣고, addFirst로 머리 위치를 관리한다.
 * X가 최대 10,000이므로 배열로 1초부터 10,000초까지 관리한다.
 * 방향은 반시계방향으로 설정해 왼쪽으로 꺾는다면 d-1, 오른쪽으로 꺾는다면 d+1로 처리한다.
 * 사과가 있다면, 사과를 없애주고 머리를 한칸 이동해 리스트에 추가한다.
 * 사과가 없다면 전체를 한칸 이동시킨다.
 * 그 후에 해당 초에 맞는 명령을 수행해 방향을 변경한다.
 *
 * 시간복잡도 :
 * 최대는 뱀이 10,000초에 끝나는 것이다. -> 정확하게 어떻게 책정할지 모호한 것 같다.
 *
 */

public class b3190 {

    static int N, apple_cnt, move_cnt, d = 0;
    static int[][] direction = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };    // 오른쪽 아래 왼쪽 위
    static int[][] map;
    static String[] moves;
    static LinkedList<Point> snake = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());    // 보드의 크기
        map = new int[N+1][N+1];    // 1행 1열부터 시작하므로 +1씩 padding
        apple_cnt = Integer.parseInt(br.readLine());    // 사과 개

        for (int i = 0; i < apple_cnt; ++i) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 1;  // 사과 위치에 1로 표시
        }

        move_cnt = Integer.parseInt(br.readLine()); // 방향 변환 횟수
        moves = new String[10001];  // 초를 뜻하는 X가 10,000 이하이므로 (0초 제외하고) +1 해서 배열을 만든다
        for (int i = 0; i < move_cnt; ++i) {
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            moves[index] = st.nextToken();  // 해당 초에 어떤 방향으로 전환할지 문자열을 저장한다.
        }

        snake.add(new Point(1, 1)); // 처음 시작은 1, 1 이다.
        System.out.println(go(1));
    }

    private static boolean check(int r, int c) {
        // 벽에 박았는지 확인 (유효범위 밖인지)
        if (r<=0 || c<=0 || r>N || c>N) return false;

        // 본인 몸에 박았는지 확인. Point를 순회하면서 다음 위치가 내 몸인지 확인
        for (Point p: snake) {
            if (r == p.r && c == p.c) return false;
        }

        return true;
    }

    private static int go(int time) {
        // time에 따라 다음에 어디로 가야할지 체크
        Point head = snake.get(0);
        int nr = head.r + direction[d][0];
        int nc = head.c + direction[d][1];

        if (!check(nr, nc)) return time;

        // 다음 위치에 사과가 있다면
        if (map[nr][nc] == 1) {
            // 머리 위치를 다음 위치로 변경해서 추가해준다.
            snake.addFirst(new Point(nr, nc));
            map[nr][nc] = 0;    // 사과 사라짐
        } else {
            // 다음 위치에 사과가 없다면
            // 위치를 하나씩 옮긴다
            for (int i = snake.size()-1; i > 0; --i) {
                Point next = snake.get(i-1);
                snake.get(i).r = next.r;
                snake.get(i).c = next.c;
            }
            // 머리 위치 이동
            snake.get(0).r = nr;
            snake.get(0).c = nc;
        }

        if (moves[time] != null) {
            if (moves[time].equals("L")) {  // 왼쪽이라면 방향 -1해서 왼쪽 direction을 바라보게 설정함
                d = (d-1 < 0)? direction.length-1: d-1;
            } else {
                d = (d+1 == direction.length)? 0: d+1;
            }
        }

        return go(time+1);
    }
}

// 뱀의 좌표
class Point {
    int r, c;

    public Point(int r, int c) {
        this.r = r;
        this.c = c;
    }
}
