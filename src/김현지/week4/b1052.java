package baekjoon.aug.aug23;

import java.util.Scanner;

/**
 * 백준 1052 물병
 *
 * 접근 방식:
 * 비트 마스크를 적극 활용했습니다.
 * 물병을 담을 수 있도록 하려면, 2의 제곱수들이어야 합니다.
 * 2의 제곱들을 2진수로 나타내면 비트 1은 오직 하나이고 나머지 비트는 모두 0입니다.
 * 그래서 while문을 순회하면서 입력으로 받은 N의 1비트의 개수를 센 후 마지막으로 1비트가 나온 자리의 값을 result와 N에 더해가면서
 * 2의 제곱수를 만들 수 있을 때 while문을 탈출했습니다.
 *
 * 시간 복잡도:
 * O(logN) N을 2진수로 만들어서 찾으니까..?
 */

public class b1052 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int result = 0;
        int bit = N;

        // K가 N보다 크면 무조건 0
        // 결과가 -1일 경우는 없습니다.
        // 1의 비트 개수가 K개보다 작으면 반복을 멈춥니다.
        while(bit > K) {
            bit = 0;
            int n = N;

            // n = 0 까지 반복하면 n이 갖고 있는 1 비트의 수가 bit에 저장합니다.
            while(n > 0) {
                n &= n-1;
                bit++;
            }

            // 1 비트의 수가 K보다 크다면
            if (bit > K) {
                // 마지막으로 1이 나온 비트를 찾아서 해당 값을 result에 더해줍니다.
                result = result + (N & -N);
                N = N + (N & -N);
            }
        }

        System.out.println(result);

    }
}
