package src.알고리즘스터디.날짜12월3째주.용액;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * date : 21.12.22
 * link : https://www.acmicpc.net/problem/2467
 */

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/용액/input.txt"));

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        arr = new int[N];

        for(int i=0;i<N;i++){
            arr[i] = sc.nextInt();
        }

        int left = 0;
        int right = N-1;
        int min = Integer.MAX_VALUE;

        int leftV=0;
        int rightV=0;

        while(left < right){
            int sum = Math.abs(arr[left] + arr[right]);

            if(min > sum){
                min = sum;
                leftV = left;
                rightV = right;
            }

            int goLeft = Math.abs(arr[left+1]+arr[right]);
            int goRight = Math.abs(arr[left]+arr[right-1]);

            if(goLeft > goRight){
                right--;
            }else{
                left++;
            }
        }
        System.out.print(arr[leftV] + " " + arr[rightV]);
    }
}
