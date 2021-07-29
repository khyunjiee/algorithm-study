package week1;

import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon_1920 {
	
	
	static int BSearch(int arr[], int target) {
	    int low = 0;
	    int high = arr.length - 1;
	    int mid;

	    while(low <= high) {
	        mid = (low + high) / 2;

	        if (arr[mid] == target)
	            return 1;
	        else if (arr[mid] > target)
	            high = mid - 1;
	        else
	            low = mid + 1;
	    }
	    return 0;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[] arr1 = new int[N];
		for(int i = 0; i<N; i++) {
			arr1[i] = sc.nextInt();
		}
		
		Arrays.sort(arr1);
		
		int M = sc.nextInt();
		int[] arr2 = new int[M];
		for(int i = 0; i<M; i++) {
			arr2[i] = sc.nextInt();
		}
		
		for(int i : arr2) {
			System.out.println(BSearch(arr1,i));
		}
		
	}
}
