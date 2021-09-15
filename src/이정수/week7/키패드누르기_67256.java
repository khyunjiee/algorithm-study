package com.ssafy.algo.study.week7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(int[] numbers, String hand) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, int[]>map = new HashMap<>();
        // 키패드 생성
        for (int i = 0; i < 9; i++) map.put(i+1, new int[] {i/3, i%3});
		map.put(0,new int[] {3,1});
        
		int[] lThumb = {3,0}; // 왼손 위치정보
		int[] rThumb = {3,2}; // 오른손 위치정보
		
        for(int number: numbers){
            if(number== 1 || number == 4 || number ==7) {
            	sb.append("L");
            	lThumb = map.get(number);
            }
            else if(number== 3 || number == 6 || number ==9) {
            	sb.append("R");
            	rThumb = map.get(number);
            }
            else {
            	// 거리 계산
            	int lDistance = Math.abs(map.get(number)[0] - lThumb[0]) + Math.abs(map.get(number)[1] - lThumb[1]);
            	int rDistance = Math.abs(map.get(number)[0] - rThumb[0]) + Math.abs(map.get(number)[1] - rThumb[1]);
            	
            	// 거리 비교
            	if (lDistance > rDistance) { //오른손이 더 가까울 때
            		sb.append("R");
            		rThumb = map.get(number);
            	}else if(lDistance < rDistance) {//왼손이 더 가까울 때
            		sb.append("L");
            		lThumb = map.get(number);
            	}else {//같은 거리 일때
            		System.out.println(number + "//  left thumb: "+ Arrays.toString(lThumb) + "right thumb: " + Arrays.toString(rThumb));
            		if(hand.equals("right")) {
            			sb.append("R");
            			rThumb = map.get(number);
            		}else {
            			sb.append("L");
                		lThumb = map.get(number);
            		}
            	}
            }
        }
        String answer = sb.toString();
        return answer;
    }
}
