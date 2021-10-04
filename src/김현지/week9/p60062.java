package programmers.sep.sep27;

/**
 * 프로그래머스 외벽점검 [Lv.3]
 *
 * 접근 방식:
 * 레스토랑이 원형이기 때문에 취약 위치인 weak 배열을 2배로 늘려서 접근했습니다.
 * 그 후 친구들이 이동할 수 있는 위치를 순열로 파악해 한번씩 해보면서, min값을 찾았습니다.
 * 순열을 구할 때 next permutation을 사용했습니다.
 *
 * 시간 복잡도:
 * O(wLen * dLen * dLen)
 *
 * 소요 시간:
 * 1일..
 *
 */

public class p60062 {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.solution(12, new int[]{1, 5, 6, 10}, new int[]{1, 2, 3, 4}));
        System.out.println(solution.solution(12, new int[]{1, 3, 4, 9, 10}, new int[]{3, 5, 7}));
    }
}

class Solution {

    int answer, wLen, dLen;
    int circleWeak[];   // 취약 부분을 2배로 늘려서 원형 체크 없이 접근

    public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;
        wLen = weak.length;
        dLen = dist.length;
        circleWeak = new int[2*wLen];
        for (int i = 0; i < wLen; i++) {
            circleWeak[i] = weak[i];
            circleWeak[i+wLen] = weak[i] + n;
        }

        do {
            for (int w = 0; w < wLen; w++) {
                int start = circleWeak[w];          // 검사 시작 구간
                int end = circleWeak[w+wLen-1];     // 마지막 취약 위

                for (int d = 0; d < dLen; d++) {
                start += dist[d];                                     // 친구가 검사할 수 있는 길이를 추가
                    // start가 end 보다 크거나 같으면 마지막 취약점의 위치까지 검사한 것
                    if (start >= end) {
                        answer = Math.min(answer, d+1);                 // 현재까지 점검한 친구들의 수 -> 인덱스이므로 1 추가
                        break;
                    }
                    start = circleWeak[nextStart(start, circleWeak)];   // 다음 시작 지점을 계산
                }
            }
        } while (np(dist));

        return (answer == Integer.MAX_VALUE)? -1: answer;
    }

    // 친구들 순서를 바꿔준다
    private static boolean np(int[] arr) {
        int N = arr.length-1;

        int i = N;
        while (i > 0 && arr[i-1] >= arr[i]) --i;
        if (i == 0) return false;

        int j = N;
        while (arr[i-1] >= arr[j]) --j;
        swap(i-1, j, arr);

        int k = N;
        while (i < k) swap(i++, k--, arr);

        return true;
    }

    // 다음 검사 시작 위치 찾기
    // current: 검사가 끝난 위치
    // weak[]: 취약 지점들
    private static int nextStart(int current, int[] weak) {
        int result = Integer.MAX_VALUE;
        int index = 0;

        for (int i = 0; i < weak.length; i++) {
            // 검사하지 않은 구간 중에 가장 current에서 가까운 인덱스를 구함
            if (current < weak[i] && result >= weak[i]) {
                result = weak[i];
                index = i;
            }
        }
        return index;
    }

    private static void swap(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
