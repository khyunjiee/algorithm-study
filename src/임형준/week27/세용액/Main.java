package src.backjoon.세용액;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * date : 22.03.22
 * memo : 하나의 기준점을 잡고 투포인터를 사용한다.
 */

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/세용액/input.txt"));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        long min = Long.MAX_VALUE;

        int p1=0, p2=0, p3=0;

        out : for(int i=0;i<=N-3;i++){
            int pointValue = arr[i];

            int left = i+1;
            int right = N-1;

            while(left<right){
                // (long) 부분이 없으면 41%에서 막힌다.
                long sum = (long)pointValue + arr[left] + arr[right];

                if(Math.abs(sum) < min){
                    min = Math.abs(sum);
                    p1 = i;
                    p2 = left;
                    p3 = right;
                }

                if(sum == 0){
                    p1 = i;
                    p2 = left;
                    p3 = right;
                    break out;
                }
                else if(sum > 0){
                    right--;
                }else{
                    left++;
                }
            }
        }
        System.out.println(arr[p1] + " " + arr[p2] + " " + arr[p3]);
    }
}
