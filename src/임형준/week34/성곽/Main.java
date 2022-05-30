package src.backjoon.성곽;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * date: 22.05.23
 */

public class Main {
    static int N,M;
    static int[][] map;
    static int[][] mark;
    static int[] dx ={0,-1,0,1};
    static int[] dy = {-1,0,1,0};
    static Map<Integer,Integer> hash;
    static int maxSum;
    static int maxCnt;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/성곽/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        mark = new int[N][M];
        hash = new HashMap<>();
        maxSum = 0;
        maxCnt = 0;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 1;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(mark[i][j] == 0){
                    bfs(i,j,cnt);
                    cnt++;
                }
            }
        }
        Set<Integer> integers = hash.keySet();
        for(int a : integers){
            maxCnt = Math.max(maxCnt,hash.get(a));
        }

        countMaxCount();

        System.out.println(cnt-1);
        System.out.println(maxCnt);
        System.out.println(maxSum);
    }

    private static void countMaxCount() {
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                for(int d=0;d<4;d++){
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if(isOut(nx,ny)) continue;

                    if(mark[nx][ny] != mark[i][j]){
                        maxSum = Math.max(maxSum, hash.get(mark[nx][ny]) + hash.get(mark[i][j]));
                    }
                }
            }
        }
    }

    private static void printMark() {
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(mark[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void bfs(int i, int j,int cnt) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i,j});
        mark[i][j] = cnt;

        int area = 1;

        while (!q.isEmpty()){
            int[] poll = q.poll();

            int x = poll[0];
            int y = poll[1];

            int flag = map[x][y];

            for(int d=0;d<4;d++) {
                int dFlag = 1 << d;

                if ((flag & dFlag) == 0) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (isOut(nx, ny) || mark[nx][ny] != 0) continue;

                    mark[nx][ny] = cnt;
                    area++;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        hash.put(cnt,area);
    }

    static boolean isOut(int nx, int ny){
        return nx<0 || ny<0 || nx>=N || ny>=M;
    }
}
