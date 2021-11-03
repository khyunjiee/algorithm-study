package com.ssafy.algo.study.week13;

import java.util.Arrays;

public class 셔틀버스_17678 {
	/*
	 * 접근:
	 * 셔틀은 셔틀이 도착한 시간보다 먼저 서있던 크루들 중 아직 셔틀을 타지 못한 크루들을 m명 태워갑니다.
	 * 각각의 셔틀이 태워가는 크루들이 서있던 시간을 하나씩 셔틀 배열에 넣어서 해결
	 * 
	 * 시간복잡도:
	 * O(NM)
	 * 
	 * 풀이에 걸린 시간:
	 * 40 min
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(solution(2,10,2,new String[] {"09:10", "09:09", "08:00"}));
	}
	public static String solution(int n, int t, int m, String[] timetable) {
		
		Arrays.parallelSort(timetable); // 대기열 도착 시간 오름차순 정렬
		String[] shuttle=null;			// 셔틀이 태우는 크루 배열
		int idx=0;						// timetable의 element를 차례대로 가르키기 위한 인덱스
		
		for (int i = 0; i < n; i++) {
			int shuttleArrivalTime = convertTimeToMin("09:00") + t*i; 	// 셔틀 도착시간
			int passengerCnt = 0;										// 셔틀 탑승객 수 카운트 변수
			shuttle = new String[m];									// 셔틀 배열 생성
			
			while(idx < timetable.length 									// 대기열의 모든 크루를 태우지 않았고
					&& shuttleArrivalTime>=convertTimeToMin(timetable[idx]) // 아직 셔틀에 타지 않은 크루중 가장 먼저 도착한 크루가 셔틀 도착시간보다 빨리 도착했고
					&& passengerCnt<m) {									// 셔틀 수용인원수를 초과하지 않았을 때
				
				shuttle[passengerCnt] = timetable[idx];	// 셔틀에 크루 탑승
				idx++;
				passengerCnt++;
				
			}
		}
		
		
		if(shuttle[m-1]==null) { // 마지막 셔틀에 자리가 남는 경우
			
			int lastShuttleTime = convertTimeToMin("09:00") + t*(n-1);
			return minTimeToTime(lastShuttleTime);
			
		}else {// 마지막 셔틀에 자리가 남지 않는 경우
			
			int lastCrewTime = convertTimeToMin(shuttle[m-1]);
			return minTimeToTime(lastCrewTime-1);
			
		}
    }
	
	// 문자열 시간 -> 분단위 시간 변경 메소드
	public static int convertTimeToMin(String time) {
		String[] times = time.split(":");
		int hour = Integer.parseInt(times[0]);
		int min = Integer.parseInt(times[1]);
		return hour*60+min;
	}
	
	// 분단위 시간 -> 문자열 시간 변경 메소드
	public static String minTimeToTime(int min) {
		int hour = min/60; 		// 시
		int minute = min%60;	// 분
		// hh:mm 으로 변경
		String hourString = hour<10?"0"+Integer.toString(hour):Integer.toString(hour);
		String minuteString = minute<10?"0"+Integer.toString(minute):Integer.toString(minute);
		String time =  hourString + ":" + minuteString;
		return time;
	}
}
