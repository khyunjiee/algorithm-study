package programmers;

// 카펫
public class Programmers_42842 {
	
	public static void main(String[] args) {
		int brown = 24;
		int yellow = 24;
		System.out.println(solution(brown, yellow)[0]);
	}
	
	public static int[] solution(int brown, int yellow) {
		int n = brown/2-2;
		int garo = n%2==0?n/2:n/2+1;
		int sero = 0;
		for(; garo<n; garo++) {
			sero=n-garo;
			if(garo*sero==yellow) break;
		}
        int[] answer = {garo+2, sero+2};
        return answer;
    }
}