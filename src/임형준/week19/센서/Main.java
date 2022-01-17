package src.backjoon.센서;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * date: 22.01.10
 * sol)
 * 차이 값의 오름차순을 기준으로, N-M개의 값을 추출
 *
 * 시간복잡도 : O(nlogn)
 */

public class Main {
    static int N,M;
    static int[] arr;
    static int[] diff;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/센서/input.txt"));

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N];

        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        diff = new int[N-1];

        for(int i=0;i<N-1;i++){
            diff[i] = arr[i+1] - arr[i];
        }
        Arrays.sort(diff);

        int res = 0;

        for(int i=0;i<N-M;i++){
            res += diff[i];
        }
        System.out.println(res);

    }
}
