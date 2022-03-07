package src.backjoon.암호코드;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * date: 22.03.06
 * 풀이 참조 : https://c-king.tistory.com/301
 */

public class MainV2 {
    static int N;
    static String S;
    static int[] dp;
    static int MOD = 1000000;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/암호코드/input.txt"));

        Scanner sc = new Scanner(System.in);
        S = sc.next();
        N = S.length();

        dp = new int[N+1];
        dp[0] = 1;

        for(int i=1;i<=N;i++){
            int one = S.charAt(i-1) - '0';

            if(one != 0){
                dp[i] = dp[i-1];
                dp[i] %= MOD;
            }

            if(i==1) continue;

            int ten = S.charAt(i-2) - '0';

            int num = ten * 10 + one;

            if(num >= 10 && num <=26){
                dp[i] += dp[i-2];
                dp[i] %= MOD;
            }
        }
        System.out.println(dp[N]);
    }
}
