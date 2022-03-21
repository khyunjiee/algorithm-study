package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2473 {
    /* 2473. 세 용액
   투 포인터
   */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Long.parseLong(st.nextToken());
        Arrays.sort(arr);

        int lef = 0, rig = N-1;
        long first = 0, second = 0, third = 0, opt = Long.MAX_VALUE, sum;

        for (int i = 0; i < N-2; i++) {
            lef = i + 1;    rig = N - 1;
            while (lef < rig) {
                sum = arr[i] + arr[lef] + arr[rig];
                if (Math.abs(sum) < opt) {
                    opt = Math.abs(sum);
                    first = arr[i]; second = arr[lef]; third = arr[rig];
                }

                if (sum > 0) rig--;
                else lef++;
            }
        }

        System.out.println(first+" "+second+" "+third);
    }
}
