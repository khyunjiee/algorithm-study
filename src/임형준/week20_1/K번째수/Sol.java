package src.backjoon.K번째수;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**

 */

public class Sol {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/K번째수/input.txt"));

        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int K = in.nextInt();

        long lo = 1;
        long hi = K;

        while(lo < hi) {
            long mid = (lo + hi) / 2;
            long count = 0;

            for(int i = 1; i <= N; i++) {
                count += Math.min(mid / i, N);
            }

            if(K <= count) {
                hi = mid;
            }
            else {
                lo = mid + 1;
            }
        }

        System.out.println(lo);
    }
}