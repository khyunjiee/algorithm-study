package programmers;

// [3차]n진수 게임
/*
 * n진수로 숫자를 변형하는 로직만 잘 구현하면 그리 어렵지 않은 문제.
 */
public class Programmers_17687 {
	
	public static void main(String[] args) {
		System.out.println(solution(16, 16, 2, 2));
	}
	
    public static String solution(int n, int t, int m, int p) {
        int num=0; // n진법으로 변환될 숫자
        StringBuilder totalStr = new StringBuilder();
        while(true) {
        	String strNum = numToStr(num,n);
        	totalStr.append(strNum);
        	if(totalStr.length()>=(t-1)*m+p) break;
        	num++;
        }
        
        StringBuilder answer = new StringBuilder();
        for(int i=1; i<=t; ++i) {
        	answer.append(totalStr.charAt((i-1)*m+p-1)); // 인덱스는 0부터 이므로 1 빼기
        }
        return answer.toString();
    }
    public static String numToStr(int num, int base) { // num을 base진법으로 바꾼 수를 반환
    	StringBuilder str = new StringBuilder();
    	while(true) {
    		if(num%base<10) str.append(num%base);
    		else str.append((char)('A'+(num%base)-10));
    		num /= base;
    		if(num==0) break;
    	}
    	str.reverse();
    	return str.toString();
    }
}
