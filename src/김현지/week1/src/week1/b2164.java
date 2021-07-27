package week1;

import java.util.*;

public class b2164 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		List<Integer> list = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}
		
		while(list.size() > 1) {
			list.remove(0);
			list.add(list.size(), list.get(0));
			list.remove(0);
		}
		
		System.out.println(list.get(0));
	}

}
