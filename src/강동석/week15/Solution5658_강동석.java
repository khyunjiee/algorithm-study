package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// [모의 SW 역량테스트] 보물상자 비밀번호
/*
 * 내림차순 정렬 수 앞에서 K번쨰 큰수를 찾아도 되지만,
 * 그냥 편하게 오름차순 정렬 후 뒤에서부터 찾았다.
 */
public class Solution5658_강동석 {
	
	static int N,K,result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			String[] str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			K = Integer.parseInt(str[1]);
			char[] strArr = br.readLine().toCharArray();
			int[] numArr = new int[N]; // 최대 N개의 다른 숫자가 만들어 질 수 있다.
			int idx = 0; // numArr에 숫자를 저장할 위치의 인덱스
			for(int r=0; r<N/4; ++r) { // rotation을 총 N/4번 한다.
				for(int i=0; i<4; ++i) {
					String strNum = "";
					for(int j=0; j<N/4; ++j) {
						strNum += strArr[(r+i*N/4+j)%N];
					}
					numArr[idx++] = Integer.parseInt(strNum,16); // 16진수를 10진수로 변환하여 저장
				}
			}
			Arrays.sort(numArr); // 오름차순 정렬 후 뒤에서부터 접근
			if(K==1) result=numArr[N-1]; // k=1이면 첫번쨰 원소가 정답
			idx=1; // K번째로 큰 수를 찾기위한 인덱스
			for(int i=N-2; i>=0; --i) {
				if(numArr[i]!=numArr[i+1]) { // 작은 수가 나오면
					if(++idx==K) { // K번쨰로 큰 수이면
						result = numArr[i]; // 결과값 저장
						break;
					}
				}
			}
			System.out.println("#"+tc+" "+result);
		}
	}
}