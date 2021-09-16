package programmers;

// [카카오 인턴] 키패드 누르기
public class Programmers_67256 {

	public static void main(String[] args) {

	}

	public static String solution(int[] numbers, String hand) {
		StringBuilder sb = new StringBuilder();
		int[] lHand = new int[] {3,0}; // 왼손 좌표
		int[] rHand = new int[] {3,2}; // 오른손 좌표
		int N = numbers.length;
		// 0~9까지 숫자패드의 좌표 저장
		int[][] position = new int[][] {{3,1},{0,0},{0,1},{0,2},{1,0},{1,1},{1,2},{2,0},{2,1},{2,2}};
		for(int i=0; i<N; i++) {
			int num = numbers[i];
			if(num==1||num==4||num==7) {
				lHand = position[num]; // 왼손 이동
				sb.append("L");
			} else if(num==3||num==6||num==9) {
				rHand = position[num]; // 오른손 이동
				sb.append("R");
			} else { // 2,5,8,0 이면
				int toLeft = Math.abs(position[num][0]-lHand[0]) + Math.abs(position[num][1]-lHand[1]); // 왼손까지의 거리
				int toRight = Math.abs(position[num][0]-rHand[0]) + Math.abs(position[num][1]-rHand[1]); // 오른손까지의 거리
				if(toLeft<toRight) { // 왼손이 더 가까우면
					lHand = position[num]; // 왼손 이동
					sb.append("L");
				} else if(toLeft>toRight) { // 오른손이 더 가까우면
					rHand = position[num]; // 오른손 이동
					sb.append("R");
				} else { // 같은 거리만큼 떨어져 있으면
					if(hand.equals("right")) { // 오른손잡이면
						rHand = position[num]; // 오른손 이동
						sb.append("R");
					} else { // 왼손잡이면
						lHand = position[num]; // 왼손 이동
						sb.append("L");
					}
				}
			}
		}
		String answer = sb.toString();
        return answer;
    }
}