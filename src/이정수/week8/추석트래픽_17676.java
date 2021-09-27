package com.ssafy.algo.study.week8;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class 추석트래픽_17676 {
	/*
	 * 접근:
	 * 그리디 방식으로 해결합니다.
	 * 각 트래픽의 끝에 생성되는 윈도우 안에 포함된 로그의 수를 카운트 해 나갑니다.
	 * 이어서 다음 종료되는 트래픽의 끝으로 계속해서 옮겨가며 계산합니다.
	 * 다음 순서 로그의 시작시간이 윈도우의 끝보다 빠르거나 같으면 카운트합니다.
	 * 모든 로그를 시작과 종료시간으로 구분합니다.
	 * 
	 * 
	 * 시간복잡도:
	 * 최대 2000번 반복하며 각 반복에서최대 약 2000번 구간안에 있는 트래픽을 확인하므로 4000000 => 시간초과 X
	 * 
	 */

	public static void main(String[] args) throws ParseException {
		System.out.println(solution(new String[] {"2016-09-15 01:00:04.001 2.0s",
				"2016-09-15 01:00:07.000 2s"}));
	}
	public static int solution(String[] lines) {
		// 시작, 끝 이차원 배열로 만들기
		long[][] logs = new long[lines.length][];
		for (int i = 0; i < logs.length; i++) {
			String[] logData = lines[i].split(" ");
			long endTime  = timeToSec(logData[1]);
			long T = (int)(Double.parseDouble(logData[2].substring(0, logData[2].length()-1))*1000);
			long startTime  = (endTime - T)+1;
			
			logs[i] = new long[] {startTime, endTime};
		}
		
		
		// 각각의 끝 시간에 대해 구간별 트래픽 수 카운트
		int max = 0;
		for (int i=0;i<logs.length;i++) {
			long endPoint = logs[i][1]+1000;
			int cnt = 0;
			for (int j = i; j < logs.length; j++) {
				if(logs[j][0]<endPoint) {// 시작시점이 1초 구간의 종료시점 이하인 경우
					cnt++; // 구간안에 포함되는 트래픽 카운트
					continue;
				}
			}
			if(cnt>max) max = cnt;
		}
		
        return max;
    }
	
	
	private static long timeToSec(String time) {
		String[] timesplit = time.split(":");
		long h = Long.parseLong(timesplit[0])*1000;
		long m = Long.parseLong(timesplit[1])*1000;
		long s = (int)(Double.parseDouble(timesplit[2])*1000);
		
		s += (h*3600 + m*60);
		return s;
	}
}
