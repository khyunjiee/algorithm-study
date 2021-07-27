package com.ssafy.algorithm;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 수찾기_1920 {
	/*
	 	접근: 단순히 정렬되지 않은 배열 A에 대해 검색을 하면 O(NM)의 시간 복잡도로 시간 초과가 일어납니다. 
	 	따라서 A를 정렬하여 binary search로 검색 시간을 단축시켜야합니다. 
	 	또한 sort 알고리즘은 O(NlogN)의 시간 복잡도를 갖는 정렬 알고리즘(merge sort or heap sort)을 사용하여야 합니다. 
	 	O(N^2)시간복잡도를 갖게되면 시간초과가 일어납니다.
	 	
	  	시간 복잡도: 정렬에 의해  O(NlogN)
	 */

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// get N
		int N = sc.nextInt();
		
		// create array A
		int[] A = new int[N];
		
		// fill in A
		for(int i=0;i<N;i++) {
			A[i]=sc.nextInt();
		}
		
		// get M
		int M = sc.nextInt();
		
		// sort A
		Arrays.sort(A);
		
		// binary search for each number
		for(int i=0;i<M;i++) {
			int num = sc.nextInt();
			if(Arrays.binarySearch(A, num)<0)System.out.println(0);
			else System.out.println(1);
		}
		
	}

}
