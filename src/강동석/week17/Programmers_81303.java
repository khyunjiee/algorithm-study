package programmers;

import java.util.Stack;

// 표 편집
/*
 * 너무 LinkedList로 생각을 했다가 시간초과가 나서 풀이법을 구글링을 참고하였다.
 * 조금만 더 생각해보면 굳이 LinkedList를 사용할 필요없이 삭제된 정보의 인덱스만 Stack에 저장한 후
 * 마지막에 StringBuilder를 이용하여 Stack에 남은 인덱스들을 해당 위치에 X표시하면 훨씬 간단하다.
 * 시간복잡도 : O(N)
 */
public class Programmers_81303 {

	public static void main(String[] args) {
		int n = 8;
		int k = 2;
		String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
		System.out.println(solution(n, k, cmd));
	}
	public static String solution(int n, int k, String[] cmd) {
		Stack<Integer> removedStack = new Stack<Integer>(); // 삭제된 행들의 인덱스를 저장하는 스택
		int totalSize = n; // 초기 행 전체 갯수
		for(int i=0,length=cmd.length; i<length; ++i) {
			char ch = cmd[i].charAt(0);
			if(ch=='U') { // "U X"인 경우
				k -= Integer.parseInt(cmd[i].split(" ")[1]); // X만큼 인덱스 감소
			}else if(ch=='D') { // "D X"인 경우
				k += Integer.parseInt(cmd[i].split(" ")[1]); // X만큼 인덱스 증가
			}else if(ch=='C') { // "C"인 경우
				removedStack.add(k);
				totalSize--; // 전제 사이즈 1 감소
				if(k==totalSize) { // 인덱스가 마지막 행을 가리켰다면
					k--; // 인덱스 1 감소
				}
			}else { // "Z"인 경우
				if(removedStack.pop()<=k) k++; // 복구한 숫자가 현재 인덱스보다 작다면 인덱스 유지를 위해 1증가
				totalSize++;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<totalSize; ++i) {
			sb.append("O");
		}
		while(!removedStack.isEmpty()) {
			sb.insert(removedStack.pop(),"X"); // 왜인지는 모르겠지만 char형 'X'는 insert가 안되더라...?
		}
		return sb.toString();
	}
}