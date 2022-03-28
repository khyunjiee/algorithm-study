package src.backjoon.유기농배추;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * date: 22.03.21
 * memo: 전형적인 dfs 초급 문제
 */

public class Main {
    static int T;
    static int N,M;
    static int[][] map;
    static int amount;

    static int[] dx ={-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/유기농배추/input.txt"));

        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for(int t=1;t<=T;t++){
            N = sc.nextInt();
            M = sc.nextInt();
            amount = sc.nextInt();
            map = new int[N][M];

            for(int i=0;i<amount;i++){
                int a = sc.nextInt();
                int b = sc.nextInt();

                map[a][b] = 1;
            }
            int cnt = 0;
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(map[i][j] == 1){
                        cnt ++;
                        dfs(i,j);
                    }
                }
            }
            System.out.println(cnt);
        }


    }

    private static void print() {
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isOut(int nx,int ny){
        return nx<0 || nx >= N || ny<0 || ny>=M;
    }

    private static void dfs(int x, int y) {
        map[x][y] = 0;

        for(int d=0;d<4;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(isOut(nx,ny)) continue;

            if(map[nx][ny] == 1){
                dfs(nx,ny);
            }
        }
    }
}
