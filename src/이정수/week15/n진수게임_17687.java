package com.ssafy.algo.study.week15;

public class n진수게임_17687 {
	/*
	 * 접근:
	 * 모든 숫자를 합쳐서 하나의 문자열로 만든 뒤 튜브가 말해야하는 숫자들만 뽑아서 하나의 문자열로 리턴
	 * 
	 * 시간복잡도:
	 * ?
	 * 
	 * 풀이에 걸린 시간:
	 * 38m
	 * 
	 */
	
	
	
	public static void main(String[] args) {
		System.out.println(solution(16,16,2,2));
	}
	
	static char[] num = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; // 9보다 큰 숫자를 알파벳으로 변환하기 위한 배열
	
	static public String solution(int n, int t, int m, int p) {
		
		int lastIdx = (p-1) + m*(t-1);
		
		// 전체 숫자를 합친 String 생성
		String wholeString = "";
		int decimal = 0;
		while(wholeString.length()<=lastIdx+1) {
			wholeString += decimalToNnotation(decimal++, n);
		}
		
		// answer 생성
		String answer = "";
		
		for (int i = p-1; i <= lastIdx; i+=m)
			answer += wholeString.charAt(i);
		
        return answer;
    }
	
	
	// 십진수 => N진수
	private static String decimalToNnotation(int decimal, int n) {
		
		String convertedNumber = "";
		
		while(decimal/n>0) {
			convertedNumber = num[decimal%n] + convertedNumber;
			decimal /= n;
		}
		convertedNumber = num[decimal] + convertedNumber;
		
		return convertedNumber;
	}

}
