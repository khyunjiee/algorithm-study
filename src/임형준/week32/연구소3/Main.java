package src.backjoon.연구소3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * date: 22.04.21
 */

public class Main {
    static int N,M;
    static int[][] map;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static List<Point> virus;
    static int K;
    static int[] temp;
    static int answer;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/연구소3/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        virus = new ArrayList<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 2) {
                    virus.add(new Point(i, j));
                    map[i][j] = -2;
                } else if (n == 1) {
                    map[i][j] = -1;
                } else {
                    map[i][j] = 0;
                }
            }
        }
        K = virus.size();
        temp = new int[M];
        answer = Integer.MAX_VALUE;

        comb(0,0);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

    }

    private static void comb(int start,int cnt) {
        if(cnt==M){
            Queue<int[]> q = new LinkedList<>();
            boolean[][] v = new boolean[N][N];

            for(int n : temp){
                Point p = virus.get(n);
                q.offer(new int[]{p.x,p.y,0});
                v[p.x][p.y] = true;
            }

            int[][] temp = new int[N][N];
            for(int i=0;i<N;i++){
                temp[i] = map[i].clone();
            }
            while(!q.isEmpty()){
                int[] arr = q.poll();
                int x = arr[0];
                int y = arr[1];
                int c = arr[2];
                if(c==0 || temp[x][y] == -2){
                    temp[x][y] = -2;
                }else{
                    temp[x][y] = c;
                }

                for(int d=0;d<4;d++){
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if(isOut(nx,ny) || temp[nx][ny] == -1 || v[nx][ny]) continue;

                    if(temp[nx][ny] != 0 && temp[nx][ny] != -2) {
                        temp[nx][ny] = Math.min(temp[nx][ny],c+1);
                        continue;
                    }

                    q.offer(new int[]{nx,ny,c+1});
                    v[nx][ny] = true;
                }
            }

            int max = 0;
            boolean flag = true;

            out: for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(temp[i][j]==0){
                        flag = false;
                        break out;
                    }
                    if(temp[i][j] != 0 && temp[i][j] != -1){
                        max = Math.max(max,temp[i][j]);
                    }
                }
            }

            if(flag){
               answer = Math.min(answer,max);
            }
            return ;
        }

        for(int i=start;i<K;i++){
            temp[cnt] = i;
            comb(i+1,cnt+1);
        }
    }

    static boolean isOut(int nx,int ny){
        return nx < 0 || ny < 0 || nx>= N || ny>=N;
    }

    private static void print(int[][] t) {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(t[i][j] + " ");
            }
            System.out.println();
        }
    }
}
