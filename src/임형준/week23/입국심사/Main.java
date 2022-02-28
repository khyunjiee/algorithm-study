package src.프로그래머스.이분탐색.입국심사;

import java.util.Arrays;

/**
 * date : 22.02.21
 * 이분 탐색이라는 것을 몰랐으면, 이렇게 생각도 못했을 것 같다.
 * 풀이는 여러 인터넷의 풀이를 보고 이해는 했지만, 막상 시험장에 가서 이렇게 풀 수 있을까? 라는 생각이 드는 문제
 * 배열을 정렬해주고,
 * peopleCount += mid / t 로 사람 수를 세준다.
 * ( t = for i <- 0 to times.length : times[i] )
 * 사람수가 부족하다면 start 값을 mid +1로 늘려주고, 사람수가 많다면 end 값을 mid-1로 줄여서 최소값을 찾는다.
 */

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        long solution1 = solution.solution(6, new int[]{7, 10});
        System.out.println(solution1);
    }

    static class Solution {
        public long solution(int n, int[] times) {
            Arrays.sort(times);
            long start = 0;
            long end = Long.MAX_VALUE;
            long result = Long.MAX_VALUE;

            while(start<=end){
                long mid = (start+end)/2;
                long peopleCount =0;

                for(int t : times){
                    peopleCount += mid/t;
                    if(peopleCount>=n) break;
                }

                if(peopleCount < n){
                    start = mid+1;
                }else{
                    result = Math.min(result,mid);
                    end = mid-1;
                }
            }
            return result;
        }
    }
}
