package src.backjoon.점프;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * date: 22.03.23
 */

public class Main {
    static int N;
    static int[][] map;
    static long[][] dp;

    static int[] dx = {1,0};
    static int[] dy = {0,1};

    static long result;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/점프/input.txt"));

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        map = new int[N][N];
        dp = new long[N][N];

        dp[0][0] = 1;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = sc.nextInt();
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(dp[i][j] == 0 || (i==N-1 && j==N-1)) continue;

                int range = map[i][j];

                for(int d=0;d<2;d++){
                    int nx = i + dx[d]*range;
                    int ny = j + dy[d]*range;

                    if(isOut(nx,ny)) continue;

                    dp[nx][ny] += dp[i][j];
                }
            }
        }

        System.out.println(dp[N-1][N-1]);
    }

    private static void print() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }


    static boolean isOut(int nx,int ny){
        return nx >= N || ny>=N;
    }
}
