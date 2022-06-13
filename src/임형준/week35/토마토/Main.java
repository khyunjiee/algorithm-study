package 문제집.backjoon.토마토;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * date: 22.05.30
 */

public class Main {
    static int N,M,H;
    static int[][][] map;
    static int max;

    static int[] dx = {-1,1,0,0,0,0};
    static int[] dy = {0,0,-1,1,0,0};
    static int[] dh = {0,0,0,0,-1,1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/토마토/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[N][M][H];
        Queue<int[]> queue = new LinkedList<>();
        max = 0;

        for(int h=0;h<H;h++){
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<M;j++){
                    int in = Integer.parseInt(st.nextToken());
                    if(in == 1){
                        queue.offer(new int[]{i,j,h,0});
                    }
                    map[i][j][h] = in;
                }
            }
        }

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int h = poll[2];
            int cnt = poll[3];
            max = Math.max(max,cnt);

            for(int d=0;d<6;d++){
                int nx = x + dx[d];
                int ny = y + dy[d];
                int nh = h + dh[d];

                if(isOut(nx,ny,nh) || map[nx][ny][nh] != 0) continue;

                map[nx][ny][nh] = 1;
                queue.offer(new int[]{nx,ny,nh,cnt+1});
            }
        }
        boolean flag = true;
        out: for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                for(int h=0;h<H;h++){
                    if(map[i][j][h] == 0){
                        flag = false;
                        break out;
                    }
                }
            }
        }
        System.out.println(flag ? max : -1);
    }

    private static void printQueue(Queue<int[]> queue) {
        for(int [] a : queue){
            System.out.println(Arrays.toString(a));
        }
    }

    static boolean isOut(int nx,int ny, int nh){
        return nx<0 || ny<0 || nx>=N || ny>=M || nh<0 || nh>=H;
    }
}
