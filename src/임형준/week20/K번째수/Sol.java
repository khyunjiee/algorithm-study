package src.backjoon.K번째수;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * link : https://st-lab.tistory.com/281
 *
 * B[k] = x
 *
 * int mid = (start + end)/2
 *
 * mid 가 의미하는 것 = x를 찾는 변수
 *
 * mid 가 (start + end)/2 라고 가정
 * for i <- 1 to N
 *   count += Math.min( mid /i , N)
 *
 * count 가 K보다 작다면 mid 키워줘야 한다.
 *
 * 가장 처음에 등장하는 K값을 세는 것이기 때문에 lower bound 를 써준다.
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