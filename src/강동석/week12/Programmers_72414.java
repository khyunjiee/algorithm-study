package programmers;

// 광고 삽입
/*
 * 각 시청자들의 시간마다 시작시각과 끝시각을 표시하고,
 * 처음부터 배열을 1번 순회하면 각 시각마다 시청자의 수가 되고,
 * 1번 더 순회하면, 처음부터 해당시각까지의 누적합이 된다.
 * 처음에 그리디 문제인 줄 알고 한참 고민하다가, 결국 떠오르지 않아서 다른 풀이를 참고하였다.
 * 시간복잡도 : O(N), 시간복잡도가 개에바쎄바인 문제이다. 풀이법만 알면 너무나도 간단하다.
 */
public class Programmers_72414 {

	public static void main(String[] args) {
		String play_time = "02:03:55";
		String adv_time = "00:14:15";
		String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
		System.out.println(solution(play_time, adv_time, logs));
	}
	public static String solution(String play_time, String adv_time, String[] logs) {
		final int END = strToSec(play_time); // 종료 시간을 초로 환산
		int advTime = strToSec(adv_time); // 광고 시간을 초로 환산
		long[] counts = new long[END+1]; // 각 인덱스는 처음부터 해당 인덱스초까지의 누적 합, 누적합은 int 범위를 초과할 수 있으므로 long
		String[] str;
		int N = logs.length;
		for(int i=0; i<N; ++i) {
			str = logs[i].split("-");
			counts[strToSec(str[0])]++; // 시작 지점을 1 증가
			counts[strToSec(str[1])]--; // 종료 지점을 1 감소
			
		}
		for(int i=1; i<=END; ++i) { // 처음 실행하면 각 인덱스는 해당지점에서 시청중인 인원 수
			counts[i] += counts[i-1];
		}
		for(int i=1; i<=END; ++i) { // 두 번째 실행하면 각 인덱스는 처음부터 해당지점까지의 누적 합
			counts[i] += counts[i-1];
		}
		long sum = counts[advTime-1]; // 합은 int 범위 초과할 수 있으므로 long
		int result = 0;
		for(int i=0; i+advTime<=END; ++i) {
			if(sum<counts[i+advTime]-counts[i]) {
				sum=counts[i+advTime]-counts[i];
				result=i+1;
			}
		}
        String answer = secToStr(result);
        return answer;
    }
	
	public static int strToSec(String str) { // "HH:MM:SS" 형식의 시간을 초 단위로 환산
		int sec = 0;
		String[] parse = str.split(":");
		sec += Integer.parseInt(parse[0])*3600; // 시
		sec += Integer.parseInt(parse[1])*60; // 분
		sec += Integer.parseInt(parse[2]); // 초
		return sec;
	}
	public static String secToStr(int sec) { // 초 단위를 "HH:MM:SS" 형식의 시간으로 변경
		String str = "";
		int HH = sec/3600;
		int MM = (sec%3600)/60;
		int SS = sec%60;
		if(HH<10) {
			str += "0"+HH+":";
		}else {
			str += HH+":";
		}
		if(MM<10) {
			str += "0"+MM+":";
		}else {
			str += MM+":";
		}
		if(SS<10) {
			str += "0"+SS;
		}else {
			str += SS;
		}
		return str;
	}
}