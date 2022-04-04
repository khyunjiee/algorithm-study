package programmers;

// 선입 선출 스케줄링
/*
 * 특정 시각까지 몇개의 작업을 받아들였는지를 이분탐색을 통하여 계산한 후
 * n개 직전까지의 시각과 작업갯수를 구하여 그 다음시각에 몇번째 코어에 할당되는지를 계산한다.
 */
public class Programmers_12920 {

	public static void main(String[] args) {
		int n = 7;
		int[] cores = {50,50,50,30};
		System.out.println(solution(n, cores));
	}
	
	public static int solution(int n, int[] cores) {
		int len = cores.length;
		
		int beforeStart = 0;
		int sum = 0;
		
		int low = 1;
		int high = 10000*50000/2; // 코어는최소 시간은 최대일 때
		while(low<high) {
			int mid = (low+high)/2;
			int cnt  = 0;
			for(int i=0; i<len; ++i) {
				int time = cores[i];
				cnt += mid%time==0 ? mid/time : mid/time + 1;
			}
			if(cnt>=n) {
				high = mid-1;
			}else {
				beforeStart = mid; // 시간 저장
				sum = cnt; // 처리한 작업 수 저장
				low = mid+1;
			}
		}
		
		int answer = 0;
		for(int i=0; i<len; ++i) {
			if(beforeStart%cores[i]==0) { // 바로 다음시각에 코어를 처리할 수 있으면
				sum++;
				if(sum==n) {
					answer=i+1;
					break;
				}
			}
		}
        return answer;
    }
}