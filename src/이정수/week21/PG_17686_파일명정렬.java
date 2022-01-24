package com.ssafy.algo.study.week21;

import java.util.Arrays;

/**
 * 문제:[3차]파일명 정렬
 * 링크:https://programmers.co.kr/learn/courses/30/lessons/17686
 * 
 * 접근:
 * 1. 파일 클래스를 만들어 생성자로 head와 number 추출
 * 2. 조건에 맞춰 comparable을 implement하여 Arrays.sort() 사용
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 소요 시간:
 * 30m
 * 
 */
public class PG_17686_파일명정렬 {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(new String[] {"img12"})));
	}
	
	static class File implements Comparable<File>{
		String head;
		int number;
		String fileName;
		
		public File(String fileName) {
			super();
			
			int idx = 0; // 인덱스
			
			// 숫자가 아닌 문자인 구간 확인
			while(!Character.isDigit(fileName.charAt(idx))) {
				idx++;
			}
			this.head = fileName.substring(0, idx).toLowerCase();
			
			int cnt = 0; // 숫자 개수 카운트
			int beginIdx = idx; // number 시작 인덱스
			
			// 숫자 구간 확인
			while(idx<fileName.length() && Character.isDigit(fileName.charAt(idx)) && cnt<5 ) {
				cnt++;
				idx++;
			}
			this.number = Integer.parseInt(fileName.substring(beginIdx, idx));
			this.fileName = fileName;
		}
		
		@Override
		public int compareTo(File o) {
			if(this.head.equals(o.head)) { // head가 같으면 number로 대소 비교
				return this.number - o.number;
			}else {
				return this.head.compareTo(o.head); // head로 대소 비교
			}
		}
		
	}
	
	static public String[] solution(String[] files) {
		
		File[] fileArr = new File[files.length];
		
		for (int i = 0; i < fileArr.length; i++) {
			fileArr[i] = new File(files[i]);
		}
		
		Arrays.sort(fileArr);
		
        String[] answer = new String[files.length];
        
        for (int i = 0; i < answer.length; i++) {
			answer[i] = fileArr[i].fileName;
		}
        
        return answer;
    }

}
