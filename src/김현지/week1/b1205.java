package baekjoon.july;

/*
백준 1205 등수 구하기
풀이법 : 반복문으로 예외처리
시간복잡도 : 반복문이 최대 1개이므로 O(N)
주의해야할 점 : N에 0이 올 수도 있다.
 */

import java.util.Scanner;

public class b1205 {
    static int[] ranking;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int score = sc.nextInt();
        int P = sc.nextInt();
        ranking = new int[N];

        for (int i = 0; i < N; i++) {
            ranking[i] = sc.nextInt();
        }

        System.out.println(whatsMyRank(N, P, score));
    }

    private static int whatsMyRank(int N, int P, int score) {
        if (N == 0) return 1;

        if (N >= P && ranking[N-1] >= score) return -1;

        int index = 0;
        int rank = 0;
        int beforeScore = Integer.MAX_VALUE;

        while (index < N) {
            if (ranking[index] < score) break;
            if (beforeScore > ranking[index]) {
                rank = index + 1;
                beforeScore = ranking[index];
            }
            index++;
        }

        return beforeScore == score ? rank: index+1;
    }
}
