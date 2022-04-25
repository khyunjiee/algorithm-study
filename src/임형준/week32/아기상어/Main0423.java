package src.backjoon.아기상어;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * date: 22.04.23
 */

public class Main0423 {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N;
    static int[][] map;

    static int cx;
    static int cy;
    static int size;
    static int eat;
    static boolean[][] visited;
    static PriorityQueue<int[]> pq;
    static int cnt;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/아기상어/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        map = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                int n = Integer.parseInt(st.nextToken());
                if(n==9){
                    cx = i;
                    cy = j;
                }
                map[i][j] = n;
            }
        }
        Comparator<int[]> comp = (o1, o2) -> {

            int c1 = o1[2];
            int c2 = o2[2];

            if(c1==c2){
                int x1 = o1[0];
                int x2 = o2[0];
                if(x1==x2){
                    return Integer.compare(o1[1],o2[1]);
                }else{
                    return Integer.compare(x1,x2);
                }
            }else{
                return Integer.compare(c1,c2);
            }
        };
        pq = new PriorityQueue<>(comp);
        size = 2;
        cnt = 0;
        while(true){
            visited = new boolean[N][N];
            bfs();
            if(pq.isEmpty()) {
                break;
            }
            int[] poll = pq.poll();
            cnt += poll[2];
            map[cx][cy]=0;
            cx = poll[0];
            cy = poll[1];
            map[cx][cy] = 9;
            eat +=1 ;
            if(eat == size){
                size ++;
                eat = 0;
            }
            pq.clear();
        }
        System.out.println(cnt);
    }

    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{cx,cy,0});
        visited[cx][cy] = true;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();

            int x = poll[0];
            int y = poll[1];
            int cnt = poll[2];

            for(int d=0;d<4;d++){
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(isOut(nx,ny) || visited[nx][ny] || map[nx][ny] > size) continue;

                if(map[nx][ny] <= size){
                    queue.offer(new int[]{nx,ny,cnt+1});
                    visited[nx][ny] = true;
                }
                if(map[nx][ny] < size && map[nx][ny] !=0){
                    pq.offer(new int[]{nx,ny,cnt+1});
                }
            }
        }
    }

    static void print(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isOut(int nx, int ny){
        return nx<0 || ny < 0 || nx>=N || ny >=N;
    }

}
