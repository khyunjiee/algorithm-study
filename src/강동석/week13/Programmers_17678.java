package programmers;

import java.util.PriorityQueue;

// [1차] 셔틀버스
/*
 * 사람들을 일찍 온 순서대로 정렬을 한 후, 버스를 앞에서 부터 순서대로 최대한 태울 수 있는 만큼 태운 후
 * 마지막 버스의 마지막 자리까지 도달하거나 중간에 대기자가 전부 다 탄 경우를 잘 계산하면
 * 그렇게 어렵지는 않은 문제였다.
 * 다만 중간중간 경우의 수들을 논리적으로 잘 생각하는것이 중요했던거 같은 문제!
 * 시간복잡도 O(N): N*M(2중for문) + NlogN(PriorityQueue정렬)
 */
public class Programmers_17678 {

	public static void main(String[] args) {
		int n = 10;
		int t = 60;
		int m = 45;
		String[] timetable = {"23:59","23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"};
		System.out.println(solution(n, t, m, timetable));
	}

	public static String solution(int n, int t, int m, String[] timetable) {
		int length = timetable.length;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=0; i<length; i++) {
			pq.offer(strToNum(timetable[i])); // "HH:MM" 형식을 분 단위로 환산 후 pq에 추가
		}
		int answerNum = 0;
	loop:for(int i=0; i<n; ++i) {
			int busTime = 540+i*t; // 09:00을 분으로 환산 후 t초 간격의 i번째 버스
			for(int j=0; j<m; ++j) { // 한 버스당 최대 m명까지 탑승가능
				if(pq.isEmpty()) { // 버스가 다 가득 차기전에 대기자가 이미 없으면
					answerNum = 540+(n-1)*t; // 제일 마지막 버스로 탑승
					break loop; // 끝
				}
				if(i==n-1) { // 제일 마지막 버스이면
					if(j==m-1) { // 제일 마지막 탑승자이면
						if(busTime<pq.peek()) { // 다음 대기자가 버스를 못타면
							answerNum = busTime; // 버스시간에 맞춰서 도착
						}else { // 다음 대기자가 버스를 탈 수 있으면
							answerNum = pq.peek()-1; // 다음 대기자보다 1분전 도착
						}
					}else { // 제일 마지막 탑승자가 아니면
							if(busTime<pq.peek()) { // 다음 대기자가 버스를 못타면
								answerNum = busTime;
							}else { // 다음 대기자가 버스를 탈 수 있으면
								pq.poll();
							}
					}
				} else { // 마지막 버스가 아니면
					if(busTime>=pq.peek()) { // 제일 앞의 대기자가 버스보다 일찍 도착하면
						pq.poll();
					}else {
						break; // 다음 버스로 넘어감
					}
				}
			}
		}
		
        String answer = numToStr(answerNum);
        return answer;
    }
	public static int strToNum(String time) {
		int minute = 0;
		String[] str = time.split(":");
		minute += Integer.parseInt(str[0])*60;
		minute += Integer.parseInt(str[1]);
		return minute;
	}
	public static String numToStr(int num) {
		String HH = num/60 > 9 ? num/60+"" : "0"+num/60;
		String MM = num%60 > 9 ? num%60+"" : "0"+num%60;
		return HH+":"+MM;
	}
}