package programmers;

// [1차]추석 트래픽
/*
 * 시작 및 종료 시각을 초(second)단위로 환산하여 계산.
 * 종료시간 오름차순 정렬 후, 종료시간을 기준으로 1초를 탐색하며 최대 갯수를 구한다.
 */
public class Programmers_17676 {

	class Time {
		double start,end;

		public Time(double start, double end) {
			this.start = start;
			this.end = end;
		}
	}
	public int solution(String[] lines) {
		int N = lines.length;
		if(N==1) return 1; // 시간이 1개이면 무조건 1
		Time[] times = new Time[N]; // 처음부터 종료시간 기준으로 오름차순 정렬되어 있음.
		for(int i=0; i<N; ++i) {
			String[] info = lines[i].split(" ");
			double hour = Double.parseDouble(info[1].split(":")[0]) * 3600;
			double min = Double.parseDouble(info[1].split(":")[1]) * 60;
			double sec = Double.parseDouble(info[1].split(":")[2]);
			double end = hour+min+sec; // 종료시각을 초로 환산
			double T = Double.parseDouble(info[2].replace("s", "")); // 처리시간
			double start = end-T+0.001; // 시작시각
			times[i] = new Time(start, end);
		}
		
		double from=0,to=0;
		int answer = 0;
		for(int i=0; i<N; ++i) {
			int cnt = 1;
			from=times[i].end;
			to = from+1.0;
			for(int j=i+1; j<N; j++) {
				if(times[j].start<to) {
					cnt++;
				}
			}
			if(answer<cnt) answer=cnt;
		}
        return answer;
    }
}
