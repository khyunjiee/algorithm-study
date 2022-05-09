package src.backjoon.양팔저울;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static int N,K;
    static int[] weights;
    static boolean[][] check;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/양팔저울/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        weights = new int[N];

        for(int i=0;i<N;i++){
            weights[i] = sc.nextInt();
        }
        check = new boolean[N+1][40001];

        dp(0,0);

        K = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<K;i++){
            int target = sc.nextInt();

            if(check[N][target]){
                sb.append("Y").append(" ");
            }else{
                sb.append("N").append(" ");
            }
        }
        System.out.println(sb);
    }

    private static void dp(int cnt, int num) {
        if(check[cnt][num]){
            return ;
        }
        check[cnt][num] =true;

        if(cnt == N){
            return;
        }

        dp(cnt+1, num + weights[cnt]);
        dp(cnt+1, num);
        dp(cnt+1, Math.abs(num-weights[cnt]));
    }
}
