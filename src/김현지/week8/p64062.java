package programmers.sep.sep27;

/**
 * 프로그래머스 징검다리 건너기 [Lv3]
 *
 * 접근 방식:
 * for문을 순회하는 방법이 제일 naive한 방법이지만, 효율성에서 통과하지 않습니다.
 * 그래서 니니즈 친구들을 고정할 수 있는 방법을 찾게되었습니다.
 * 그냥 탐색이 안되므로 분할탐색..? 으로 찾았습니다.
 * stones 배열의 최대가 200,000,000 이므로 최대로 통과할 수 있는 니니즈 친구들의 수를 200,000,000으로 잡았습니다.
 *
 * 시간 복잡도:
 * O(NlogN)
 *
 * 소요 시간:
 * 1시간 30분
 */

public class p64062 {
    public static void main(String[] args) {
        Solution_p64062 solution = new Solution_p64062();
        System.out.println(solution.solution(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
    }
}

class Solution_p64062 {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = 200000000;  // stones 배열의 최대 원소
        int len = stones.length;

        // left가 right보다 작거나 같을 때까지 반복
        while(left <= right) {
            int mid = (right+left)/2;   // 중간값
            int zeroStone = 0;          // 건널 수 없는 연속된 돌들의 합

            // for 문의 조건에 zeroStone 이 k보다 작은 경우를 추가해 같이 체크
            for (int i = 0; i < len && zeroStone < k; i++) {
                if (stones[i] - mid <= 0) zeroStone++;  // 0보다 작거나 같다면 건널 수 없는 돌
                else zeroStone = 0; // 건널 수 있는 돌이 나온다면 0으로 초기화
            }

            if (zeroStone < k) left = mid + 1;  // mid 명은 건널 수 있다는 뜻. left를 mid+1 로 할당
            else right = mid - 1;               // mid 명이 건널 수 없다는 뜻. right를 mid-1 로 할당
        }

        return left;    // left 또는 right 를 return
    }
}

