package src.backjoon.알약;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * date : 22.01.20
 * 참조 풀이 link : https://kimtaehyun98.tistory.com/67
 */

/*
풀이 :
dp[3][1] = dp[3][0] + h + dp[2][1] + w

h==0 일 때는 경우의 수가 1가지

if w>=h :
dp[w][h] = dp[w][h-1] + dp[w-1][h]
 */

public class Main {
    static long[][] dp;
    static int N;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/알약/input.txt"));
        Scanner sc = new Scanner(System.in);

        while(true){
            N = sc.nextInt();
            if(N==0) break;
            dp = new long[N+1][N+1];

            for(int i=0;i<N+1;i++){
                for(int j=0;j<N+1;j++){
                    if(j==0) dp[i][j] = 1;
                }
            }

            for(int w=1;w<N+1;w++){
                for(int h=0;h<N+1;h++){
                    if(w<h) continue;
                    if(h==0){
                        dp[w][h] = dp[w-1][h];
                    }else{
                        dp[w][h] = dp[w][h-1] + dp[w-1][h];
                    }
                }
            }
            System.out.println(dp[N][N]);
        }

    }
}
