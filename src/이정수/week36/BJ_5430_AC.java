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
public class BJ_5430_AC {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		label: for (int testCase = 0; testCase < T; testCase++) {
			String p = br.readLine();
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			String input = br.readLine();
			input = input.substring(1, input.length()-1);
			st = new StringTokenizer(input, ",");
			
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int frontIdx = 0;
			int rearIdx = N-1;
			boolean isReversed = false;
			
			for (int i = 0; i < p.length(); i++) {
				char operation = p.charAt(i);
				
				switch(operation) {
					case 'R':
						int tmp = frontIdx;
						frontIdx = rearIdx;
						rearIdx = tmp;
						isReversed = isReversed?false:true;
						break;
					case 'D':
						if(isReversed) {
							if(frontIdx<rearIdx) {
								System.out.println("error");
								continue label;
							}
							frontIdx--;
						}else {
							if(frontIdx>rearIdx) {
								System.out.println("error");
								continue label;
							}
							frontIdx++;
						}
						break;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append('[');
			if(isReversed) {
				for (int i = frontIdx; i >= rearIdx; i--) {
					sb.append(arr[i]+",");
				}
			}else {
				for (int i = frontIdx; i <= rearIdx; i++) {
					sb.append(arr[i]+",");
				}
			}
			if(sb.charAt(sb.length()-1)==',') {
				sb.deleteCharAt(sb.length()-1);
			}
			sb.append(']');
			System.out.println(sb.toString());
		}
		
	}

}
