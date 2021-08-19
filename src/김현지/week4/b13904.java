package baekjoon.aug.aug18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 13904 과제
 *
 * 접근 방법:
 * 마지막 날짜에서 해결할 과제부터 그리디적으로 접근했습니다.
 * 입력을 받을 때, day를 과제 마감 날짜가 가장 긴 날로 정합니다.
 * 그리고 과제들은 해결하면 지워야하기 때문에, 리스트로 설정했습니다.
 * 그리고 while문을 돌면서 day가 0이 되기 전까지 과제 마감 날이 day보다 크거나 같은 것 중에서
 * 점수를 가장 많이 주는 과제를 골라서 리스트에서 삭제합니다.
 *
 * 시간 복잡도:
 * O(N^2)
 *
 */

public class b13904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 입력받을 과제 개수
        List<int[]> works = new LinkedList<>();     // 과제들을 저장할 리스트
        int day = 0;
        int total = 0;

        // 입력을 받으면서 리스트에 과제를 저장하고, day를 가장 큰 값으로 저장
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            day = Math.max(day, d);

            works.add(new int[]{d, w});
        }

        // 과제를 다 해결하거나 day가 0이 되면 반복문을 빠져나옴
        while (works.size() > 0 && day > 0) {
            int max = 0;
            int index = -1; // 해결할 과제가 없을 때의 분기처리가 필요하기 때문에 -1로 초기화

            for (int i = 0; i < works.size(); i++) {
                int[] work = works.get(i);
                // 만약 과제 마감 날짜가 현재 날짜보다 크거나 같고, max 값보다 과제 점수가 크다면
                if (work[0] >= day && work[1] > max) {
                    max = work[1];  // max와 index를 해당 과제로 바꾼다.
                    index = i;
                }
            }

            day--;
            total += max;   // 과제를 해결하지 못했다면 0 추가

            // index가 -1라면 과제를 해결하지 못한 것이므로 과제 리스트에서 삭제는 안해도 됨.
            // index가 -1이 아니라면 과제를 해결한 것이므로 과제 리스트에서 해당 인덱스를 삭제
            if (index != -1) works.remove(index);
        }

        System.out.println(total);

    }
}
