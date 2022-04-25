package src.backjoon.스타트택시;

import java.io.*;
import java.util.*;

/**
 * date: 22.04.23
 * memo: 시작지와 도착지는 겹치지 않을 것이라 생각했는데, 겹칠 수 있다... **FAIL**
 */

public class Main {
    static int N,M,fuel;
    static int[][] map;
    static int cx,cy;
    static PriorityQueue<int[]> pq;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    static int cost;
    static boolean negativeFlag;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/스타트택시/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine()," ");
        cx = Integer.parseInt(st.nextToken()) -1;
        cy = Integer.parseInt(st.nextToken()) -1;

        int cnt = 2;
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;

            int x1 = Integer.parseInt(st.nextToken())-1;
            int y1 = Integer.parseInt(st.nextToken())-1;

            map[x][y] =cnt++;
            map[x1][y1] = cnt++;
        }

        print();
        System.out.println("--------");

        Comparator<int[]> comp = (o1,o2)->{
            int c1 = o1[2];
            int c2 = o2[2];

            int x1 = o1[0];
            int x2 = o2[0];

            int y1 = o1[1];
            int y2 = o2[1];
            if(c1==c2){
                if(x1==x2){
                    return Integer.compare(y1,y2);
                }else{
                    return Integer.compare(x1,x2);
                }
            }else{
                return Integer.compare(c1,c2);
            }
        };

        negativeFlag = false;
        pq = new PriorityQueue<>(comp);
        while(true){

            pickPerson();
            if(pq.isEmpty()){
                break;
            }
            int[] poll = pq.poll();
            pq.clear();
            int x = poll[0];
            int y = poll[1];
            int distance = poll[2];

            fuel -= distance;
            cost += distance;

            if(fuel <= 0){
                break;
            }
            int before = map[x][y];

            map[x][y] = 0;
            cx = x;
            cy = y;
            goToDestination(before+1);
            if(negativeFlag){
                break;
            }
        }
        if(check()){
            System.out.println(-1);
        }else{
            System.out.println(fuel);
        }
    }

    private static boolean check() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] > 1){
                    return true;
                }
            }
        }
        return false;
    }

    private static void goToDestination(int destination) {
        visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{cx,cy,0});
        visited[cx][cy] = true;

        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int cnt = poll[2];

            if(map[x][y] == destination){
                if(fuel - cnt >= 0){
                    cx = x;
                    cy = y;
                    map[cx][cy] = 0;
                    fuel += cnt;
                    cost = 0;
                }else{
                    negativeFlag = true;
                }
                break;
            }

            for(int d=0;d<4;d++){
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(isOut(nx,ny)) continue;
                int m = map[nx][ny];
                if(visited[nx][ny] || m == 1) continue;
                if( m!= 0 && m%2==0) continue;

                queue.offer(new int[]{nx,ny,cnt+1});
                visited[nx][ny] = true;
            }
        }
    }

    private static void pickPerson() {
        visited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{cx,cy,0});

        while(!queue.isEmpty()){
            int[] poll = queue.poll();

            int x = poll[0];
            int y = poll[1];
            int cnt = poll[2];

            for(int d=0;d<4;d++){
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(isOut(nx,ny)) continue;
                int m = map[nx][ny];
                if(visited[nx][ny] || map[nx][ny] == 1) continue;

                if(m!=0 && m%2 == 0){
                    pq.offer(new int[]{nx,ny,cnt+1});
                }

                queue.offer(new int[]{nx,ny,cnt+1});
                visited[nx][ny] = true;
            }
        }
    }

    static boolean isOut(int nx,int ny){
        return nx<0 || ny<0 || nx>=N || ny>=N;
    }

    private static void print() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
