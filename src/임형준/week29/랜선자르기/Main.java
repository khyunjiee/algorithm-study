package src.backjoon.랜선자르기;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * date: 22.03.31
 * 참조링크 : https://st-lab.tistory.com/269
 */

public class Main {
    static int N,K;
    static long[] arr;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/랜선자르기/input.txt"));

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        long max = 0;
        arr = new long[N];

        for (int i=0;i<N;i++){
            arr[i] = sc.nextInt();
            max  = Math.max(max,arr[i]);
        }

        long start =0;
        long end = max;
        end++;

        while(start<end){
            long mid = (start+end)/2;
            long cnt = 0;
            for(long n : arr){
                cnt += n/mid;
            }
            if(cnt >= K){
                start = mid+1;
            }else{
                end = mid;
            }
        }
        System.out.println(start-1);
    }
}
