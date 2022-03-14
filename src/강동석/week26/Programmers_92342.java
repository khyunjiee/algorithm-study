package programmers;

// 양궁대회
/*
 * 1. dfs를 이용하여 최소한의 화살 갯수로 모든 경우의 수 체크
 * 2. 점수 최댓값 갱신 시 기존의 최댓값의 배열과 비교하여 낮은 점수의 화살이 많은 배열을 선택한다.
 * 2번에서 배열을 비교하는 로직을 빼먹어서 한동안 디버깅하느라 시간이 오래 걸렸다.
 */
public class Programmers_92342 {

	public static void main(String[] args) {
		int n = 10;
		int[] info = {0,0,0,0,0,0,0,0,3,4,3};
		System.out.println(solution(n, info));
	}
	
	static int max; // 최대 점수차
	static int[] lionArr; // 라이언의 점수판
	static int[] answerArr; // 리턴 할 배열
	static int[] apeachArr; // info배열을 static으로 변경
	
	public static int[] solution(int n, int[] info) {
		lionArr = new int[11];
		answerArr = new int[11];
		apeachArr = info;
		dfs(0,n);
		System.out.println(max);
		for(int a : answerArr) {
			System.out.print(a+" ");
		}
		System.out.println();
		if(max==0) return new int[]{-1};
        return answerArr;
    }
	
	public static void dfs(int idx, int arrowLeft) { // idx번째 점수판을 체크, 남은 화살수: arrowLeft
		if(arrowLeft==0) { // 화살을 다 소비했으면
			calcResult(); // 점수 계산
			return;
		}
		if(idx>10) { // 모든 점수판을 다 확인했는데 화살이 남았으면
			lionArr[10] += arrowLeft; // 남은 화살을 모두 0점에 올인 후
			calcResult(); // 점수 계산
			lionArr[10] = 0; // 점수 계산이 끝난 후에는 마지막 칸의 화살을 초기화
			return;
		}
		
		// 가능하면 현재 점수판을 이기고 다음 점수판으로 이동
		if(apeachArr[idx]<arrowLeft) { // 남은 화살이 현재 점수판의 상대 화살보다 많으면
			lionArr[idx] = apeachArr[idx]+1; // 상대보다 1개 더 많은 화살 저장
			dfs(idx+1,arrowLeft-lionArr[idx]); // 남은 화살 수 감소 후 다음 점수판으로 이동
		}
		lionArr[idx] = 0; // 현재 점수판을 포기하고
		dfs(idx+1,arrowLeft); // 다음 점수판으로 이동
	}
	
	public static void calcResult() {
		int gap = 0; // 라이언의 점수 - 어피치의 점수
		for(int i=0; i<11; ++i) {
			if(lionArr[i]>apeachArr[i]) { // 라이언이 점수 획득
				gap += 10-i; // 점수만큼 증가
			}else if(apeachArr[i]!=0){ // 어피치의 화살이 존재하면 어피치 점수 획득
				gap -= 10-i; // 점수만큼 감소
			}
		}
		if(max<gap) { // 최댓값 갱신
			max = gap;
			for(int i=0; i<11; ++i) { // 라이언의 배열을 정답배열에 저장
				answerArr[i] = lionArr[i];
			}
		}else if(max==gap) { // 값이 같으면 낮은 화살이 많은 것을 선택
			for(int i=10; i>=0; --i) {
				if(answerArr[i]<lionArr[i]) { // 새로운 배열이 낮은 점수의 화살이 더 많으면
					for(int j=0; j<11; ++j) { // 라이언의 배열을 정답배열에 저장
						answerArr[j] = lionArr[j];
					}
				}else if(answerArr[i]>lionArr[i]) { // 기존의 배열이 낮은 점수의 화살이 더 많으면
					break; // 그만 비교
				}
			}
		}
	}
}