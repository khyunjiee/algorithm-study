package programmers;

// 입국심사
/*
 * 모든 심사관이 가장 오래 걸리는 심사관의 시간만큼 걸린다고 가정하여
 * 가장 오래 걸리는 시간을 구한 후 시간을 이분탐색하며
 * 해당 시간경과 시 각각의 심사관에 대하여 몇명까지 입국심사를 하였는지 다 더한 후,
 * n보다 같거나 크면 시간의 범위를 낮게 잡고, n보다 모자라면 범위를 높게 잡으면서 진행한다.
 */
public class Programmers_43238 {

	public static void main(String[] args) {
		int n = 6;
		int[] times = {7,10};
		System.out.println(solution(n, times));
	}
	
	public static long solution(int n, int[] times) {
		int len = times.length;
		long maxTime = 0;
		for(int i=0; i<len; ++i) {
			maxTime = Math.max(maxTime, times[i]); // 가장 오래 심사하는 심사관의 걸리는 시간 구하기
		}
		
		long high = n/times.length*maxTime;
		long low = 1;
		
		long answer = 0;
		while(low<=high) {
			long mid = (low+high)/2;
			long totalCnt = 0;
			for(int i=0; i<len; ++i) {
				totalCnt += mid/times[i];
			}
			if(totalCnt>=n) { // 검사를 다 하고 남으면
				answer=mid;
				high = mid-1;
			}else { // 검사를 다 못하는 경우
				low = mid+1;
			}
		}
        return answer;
    }
	
}