package week29;

import java.util.Scanner;

/**
 * 문제: 히스토그램
 * 링크: https://www.acmicpc.net/problem/1725
 * 
 * 풀이:
 * 분할 정복
 * 절반씩 분할하여 왼쪽 세그먼트에서 만들 수 있는 가장 큰 직사각형의 넓이
 * 오른쪽에서 가장 큰 넓이 접하는 구간을 포한하는 가장큰 직사각형의 넓이를 비교하여
 * 가장 큰 값을 선택
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */

public class BJ_1725_히스토그램 {
	
	static int[] histogram;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		histogram = new int[N];
		
		for (int i = 0; i < N; i++) {
			histogram[i] = sc.nextInt();
		}
		
		int ans = getMaxRectangleArea(0,N-1);
		
		System.out.println(ans);
	}

	private static int getMaxRectangleArea(int from, int to) {
		
		if(from==to) {
			return histogram[from];
		}
		
		int left = (from+to)/2;
		int right = left;
		
		// 왼쪽 부분 최대 직사각형 넓이
		int leftMaxRectangleArea = getMaxRectangleArea(from, left);
		
		// 오른쪽 부분 최대 직사각형 넓이
		int rightMaxRectangleArea = getMaxRectangleArea(right+1, to);
		
		// 겹치는 부분을 포함하는 최대 직사각형 넓이
		int maxArea = histogram[right];
		int height = histogram[right];
		
		while(left>from && right<to) {
			
			if(histogram[left-1]>histogram[right+1]) {
				left--;
				height = Math.min(height, histogram[left]);
				
			}else {
				right++;
				height = Math.min(height, histogram[right]);
			}
			
			maxArea = Math.max(maxArea, (right-left+1) * height);
			
		}
		
		while(right<to) {
			right++;
			height = Math.min(height, histogram[right]);
			maxArea = Math.max(maxArea, (right-left+1) * height);
		}
		
		while(left>from) {
			left--;
			height = Math.min(height, histogram[left]);
			maxArea = Math.max(maxArea, (right-left+1) * height);
		}
		
		return Math.max(Math.max(leftMaxRectangleArea, rightMaxRectangleArea), maxArea);
		
	}

}
