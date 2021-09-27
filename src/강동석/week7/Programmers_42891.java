package programmers;

import java.util.Arrays;

// 무지의 먹방 라이브
public class Programmers_42891 {
	
	public static void main(String[] args) {
		System.out.println(solution(new int[] {3,1,2},5));
	}
	
	public static int solution(int[] food_times, long k) {
		int N = food_times.length;
		if(N>k) return (int)k+1; // 음식 길이보다 k가 작으면
		int[] sortedArr = food_times.clone();
		Arrays.sort(sortedArr);
		int rotationCnt = 0; // 몇바퀴 돌았는지 횟수
		int idx=0; // 정렬된 배열에서 가리킬 인덱스, 몇개의 음식을 이미 다 먹었는지 체크하기 위함

		long leftNum = k; // 네트워크 장애까지 남은 횟수
		while(leftNum>=N-idx) {
			leftNum -= (N-idx); // 한바퀴 돌면서 다 먹은 음식은 빼줘야 하므로 idx개 만큼 뺌
			rotationCnt++;
			for(int i=idx; i<N+1; ++i) {
				if(i==N) return -1; // 이미 N개의 음식을 다 먹었으면 -1 리턴
				if(sortedArr[i]>rotationCnt) {
					idx = i;
					break;
				}
			}
		}
		int answer = 0;
		for(int i=0; i<N; ++i) {
			if(food_times[i]>rotationCnt) { // 아직 음식이 남아 있으면
				if(leftNum==0) { // 더 이상 남은 먹을 횟수가 없으면
					answer = i+1; // 음식이 1번부터 시작하므로 +1
					break;
				}
				--leftNum; // 아직 먹을 횟수가 남아 있으면 남은 횟수 1회 차감
			}
		}
        return answer;
    }
}
