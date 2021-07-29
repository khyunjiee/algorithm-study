package baekjoon.july;

import java.util.Arrays;
import java.util.Scanner;

/*
백준 1920 수 찾
풀이법 : 이분탐색 사
시간복잡도 : 이분탐색의 시간복잡 O(logN)
주의해야할 점 : 오름차순으로 정렬된 배열로 이분탐색 진행
 */

public class b1920 {
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        arr = new int[N];

        for(int i = 0; i < N; ++i) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int M = sc.nextInt();
        for(int i = 0; i < M; ++i) {
            int num = sc.nextInt();
            if (find(0, N, num)) System.out.println("1");
            else System.out.println("0");
        }
    }

    private static boolean find(int from, int to, int num) {
        int start = from;
        int end = to-1;

        while (start <= end) {
            int midIndex = (start+end)/2;
            int mid = arr[midIndex];
            if (mid > num) end = midIndex - 1;
            else if (mid < num) start = midIndex + 1;
            else return true;
        }
        return false;
    }
}
