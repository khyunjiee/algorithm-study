package week35;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제: 두 용액
 * 링크: https://www.acmicpc.net/problem/2470
 * 
 * 풀이:
 * 오름차순 정렬
 * 양쪽 끝에서 시작
 * 두수의 합이 양수면 오른쪽 포인터 왼쪽으로 이동
 * 음수면 왼쪽 포인터 오른쪽으로 이동
 * 
 * 
 * 시간복잡도:
 * O(NlogN)
 * 
 * 풀이에 걸린 시간:
 * 30m
 * 
 *
 */
public class BJ_2470_두용액 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int N = Integer.parseInt(br.readLine());
		int[] solutions = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			solutions[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.parallelSort(solutions);
		
		int ans1 = -1;
		int ans2 = -1;
		int minSum = 2000000000;
		
		int left = 0;
        int right = N-1;
        int max = 2000000000;
 
        while(left < right) {
            int sum = solutions[left] + solutions[right];
 
            // 두 용액 갱신
            if(Math.abs(sum) < max) {
            	ans1 = solutions[left];
                ans2 = solutions[right];
                max = Math.abs(sum);
            }
 
            if(sum > 0)
                right--;
            else
                left++;
        }
		
		System.out.println(ans1 + " " + ans2);
	}

}
