/**
 * 풀이:
 *
 * 1. left to right, right to left LIS를 2번 구한다.
 * 2. LIS 2번 구한 값을 더해주고, 해당 값의 MAX를 구한다.
 * 3. 중복값 1을 빼준다.
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * date: 22.06.26
 */

 import java.util.*;
 import java.io.*;

public class Main {
    static int N;
    static int[] arr;
    static int[] dp1;
    static int[] dp2;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp1 = new int[N]; // left to right
        dp2 = new int[N]; // right to left

        dp1[0] = 1;

        for(int i=1;i<N;i++){
            dp1[i] = 1;

            for(int j=0;j<i;j++){
                if(arr[i] > arr[j]){
                    dp1[i] = Math.max(dp1[i], dp1[j]+1);
                }
            }
        }

        dp2[N-1] = 1;

        for(int i=N-2;i>=0;i--){
            dp2[i] = 1;
            for(int j=N-1;j>i;j--){
                if(arr[i] > arr[j]){
                    dp2[i] = Math.max(dp2[i],dp2[j]+1);
                }
            }
        }
        
        int max = 0;
        for(int i=0;i<N;i++){
            max = Math.max(max,dp1[i]+dp2[i]);
        }
        System.out.println(max-1);
    }
}
