package src.backjoon.페그솔리테어;

import java.io.*;

/**
 * date: 22.05.21
 */

public class Main {
    static int T;
    static int N,M;
    static int minMove;
    static int minPinCount;
    static char[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/페그솔리테어/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        N = 5;
        M = 9;

        for(int t=1;t<=T;t++){
            minMove = Integer.MAX_VALUE;
            minPinCount = Integer.MAX_VALUE;
            map = new char[N][M];

            for(int i=0;i<N;i++){
                String s = br.readLine();
                for(int j=0;j<M;j++){
                    map[i][j] = s.charAt(j);
                }
            }

            dfs(0);
            System.out.print(minPinCount + " " + minMove);
            System.out.println();
            br.readLine();
        }
    }

    private static void print() {
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(map[i][j] +" ");
            }
            System.out.println();
        }
    }

    private static void dfs(int cnt) {
        boolean flag = false;
        int pins = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] == 'o'){
                    pins++;
                }
                if(map[i][j] == 'o' && check(i,j)){
                    flag = true;
                }
            }
        }
        if(!flag){
            if(minPinCount >= pins){
                if(minPinCount == pins){
                    minMove = Math.min(minMove,cnt);
                }else{
                    minPinCount = pins;
                    minMove = cnt;
                }
            }
            return ;
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] == 'o'){
                    for(int d=0;d<4;d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if(isOut(nx,ny) || map[nx][ny] != 'o') continue;

                        int nnx = nx + dx[d];
                        int nny = ny + dy[d];

                        if(isOut(nnx,nny)) continue;

                        if(map[nnx][nny] == '.'){
                            map[i][j] = '.';
                            map[nx][ny] = '.';
                            map[nnx][nny] = 'o';

                            dfs(cnt+1);

                            map[i][j] = 'o';
                            map[nx][ny] = 'o';
                            map[nnx][nny] = '.';
                        }
                    }
                }
            }
        }
    }
    static boolean isOut(int nx, int ny){
        return nx<0 || ny<0 || nx>=N || ny>=M;
    }
    static boolean check(int x,int y){
        for(int d=0;d<4;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(isOut(nx,ny) || map[nx][ny] != 'o') continue;

            int nnx = nx + dx[d];
            int nny = ny + dy[d];

            if(isOut(nnx,nny)) continue;

            if(map[nnx][nny] == '.'){
                return true;
            }
        }
        return false;
    }
}
