package week36;

/**
 * 문제: 멀쩡한 사각형
 * 링크: https://programmers.co.kr/learn/courses/30/lessons/62048?language=java
 * 
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class PG_62048_멀쩡한사각형 {

	public static void main(String[] args) {
		System.out.println(solution(8,12));
	}
	
	static public long solution(int w, int h) {
        long answer = (long)w*h - (w + h - gcd(w,h));
        return answer;
    }

	private static int gcd(int w, int h) {
		
		int biggerNum = Math.max(w, h);
		int smallerNum = Math.min(w, h);
		
		while(biggerNum%smallerNum!=0) {
			int quotient = biggerNum / smallerNum;
			int remainder = biggerNum % smallerNum;
			
			biggerNum = smallerNum;
			smallerNum = remainder;
		}
		
		return smallerNum;
	}

}
