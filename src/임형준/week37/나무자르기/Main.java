package 문제집.backjoon.나무자르기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * date: 22.06.19
 */

public class Main {
    static int N,M;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/나무자르기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long start = 0;
        long end = 2000000000;
        long ans = 0;

        while(start < end){
            long mid = (start+end)/2;

            long sum = 0;

            for(int n : arr){
                if(n > mid){
                    sum += n - mid;
                }
            }
            if(sum>=M){
                start = mid+1;
                ans = Math.max(ans,mid);
            }else{
                end = mid;
            }
        }
        System.out.println(ans);
    }
}
