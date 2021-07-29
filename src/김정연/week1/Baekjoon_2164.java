package week1;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



public class Baekjoon_2164 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		Queue<Integer> nums = new LinkedList<>();
		
		
		for(int i=1; i<N+1; i++) {
			nums.offer(i);
		}
		
		while(nums.size()>1) {
			nums.poll();
			nums.offer(nums.poll());
		}

		
		System.out.println(nums.poll());
	}
}
