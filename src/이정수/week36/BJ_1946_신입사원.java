package week36;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 문제:
 * 링크:
 * 
 * 풀이:
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class BJ_1946_신입사원 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		Comparator<int[]> scoreComparator = new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		};
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 0; testCase < T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			int[][] scores = new int[N][];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				scores[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}
			Arrays.sort(scores, scoreComparator);
			
			int cutLine = scores[0][1];
			int cnt = 1;
			for (int i = 1; i < scores.length; i++) {
				if(cutLine>scores[i][1]) {
					cutLine = scores[i][1];
					cnt++;
				}
			}
			System.out.println(cnt);
		}
		
	}

}
