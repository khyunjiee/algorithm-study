package src.backjoon.퇴사;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * date: 22.03.17
 * 참조링크 : https://hidelookit.tistory.com/118
 */
public class Main {
    static int N;
    static int[] time;
    static int[] cost;
    static int[] dp;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/퇴사/input.txt"));
        Scanner sc= new Scanner(System.in);

        N = sc.nextInt();
        time = new int[N];
        cost = new int[N];

        for(int i=0;i<N;i++){
            time[i] = sc.nextInt();
            cost[i] = sc.nextInt();
        }
        dp = new int[N+1];

        for(int i=0;i<N;i++){
            if(i+time[i] < N+1){
                dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + cost[i]);
            }
            dp[i+1] = Math.max(dp[i+1],dp[i]);
        }
        System.out.println(dp[N]);
    }
}
