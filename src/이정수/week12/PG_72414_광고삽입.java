package com.ssafy.algo.study.week12;

public class PG_72414_광고삽입 {
	/*
	 * 문제: 광고삽입
	 * 링크: https://programmers.co.kr/learn/courses/30/lessons/72414
	 * 
	 * 접근: 
	 * 
	 * 
	 * 시간복잡도:
	 * 
	 * 
	 * 풀이에 걸린 시간:
	 * 
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(solution("02:03:55","00:14:15",new String[] {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"}));
	}
	
	static public String solution(String play_time, String adv_time, String[] logs) {
		
		int playTime = timeToSecond(play_time);
		int advTime = timeToSecond(adv_time);
		int[] watchers =  new int[playTime+1];
		
		for (int i=0;i<logs.length;i++) {
			String[] times = logs[i].split("-");
			int startTime = timeToSecond(times[0]);
			int endTime = timeToSecond(times[1]);
			for (int j = startTime; j < endTime; j++) {
				watchers[j]++;
			}
		}
		
		int startTime = 0;
        int endTime = advTime;
        long sum = 0;
        for (int i = startTime; i < endTime; i++) {
            sum += watchers[i];
        }

        long max = sum;
        int maxStartTime = 0;
        while (endTime <= playTime) {
            sum -= watchers[startTime];
            sum += watchers[endTime];
            if(sum > max) {
                max = sum;
                maxStartTime = startTime + 1;
            }
            startTime++;
            endTime++;
        }
		
		
		return secondToTime(maxStartTime);
        
    }

	private static String secondToTime(int time) {
		
		int hour = time / 3600;
        time %= 3600;
        int minute = time / 60;
        int second = time % 60;

        String strHour = hour > 9 ?  String.valueOf(hour) : "0" + hour;
        String strMinute = minute > 9 ?  String.valueOf(minute) : "0" + minute;
        String strSecond = second > 9 ?  String.valueOf(second) : "0" + second;

        return String.join(":", strHour, strMinute, strSecond);
	}

	private static int timeToSecond(String time) {
		
		String[] strings = time.split(":");
		
		int HH = Integer.parseInt(strings[0]);
		int MM = Integer.parseInt(strings[1]);
		int SS = Integer.parseInt(strings[2]);
		
		return HH*3600 + MM*60 + SS;
	}
}
