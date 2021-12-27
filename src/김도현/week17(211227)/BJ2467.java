package BJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2467. 용액
*/

public class BJ2467 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int lef = 0, rig = n - 1;
		int lefVal = arr[lef], rigVal = arr[rig];
		int optVal = Integer.MAX_VALUE, temp;
		
		while (true) {
			temp = arr[lef] + arr[rig];
			if (Math.abs(temp) <= optVal) {
				optVal = Math.abs(temp);
				lefVal = arr[lef];
				rigVal = arr[rig];
			}
			if (lef + 1 >= rig) break;
			if (temp < 0) {
				if (arr[rig] > 0) lef++;
				else lef = rig - 1;
			}else if (temp > 0) {
				if (arr[lef] < 0) rig--;
				else rig = lef + 1;
			}else break;
		}
		
		System.out.println(lefVal + " " + rigVal);
		
	}

}
