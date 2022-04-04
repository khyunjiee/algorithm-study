package src.backjoon.벽부수고이동하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * date: 22.02.25
 * 참조 링크 : https://velog.io/@yanghl98/%EB%B0%B1%EC%A4%80-2206-%EB%B2%BD-%EB%B6%80%EC%88%98%EA%B3%A0-%EC%9D%B4%EB%8F%99%ED%95%98%EA%B8%B0-JAVA%EC%9E%90%EB%B0%94
 */

public class MainV2 {
    static int N,M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int result;

    static class Move {
        int x;
        int y;
        int cnt;
        boolean isDestroy;

        public Move(int x, int y, int cnt, boolean isDestroy) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.isDestroy = isDestroy;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/벽부수고이동하기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        result = Integer.MAX_VALUE;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            char[] chars = st.nextToken().toCharArray();
            for(int j=0;j<M;j++){
                map[i][j] = chars[j]-'0';
            }
        }
        visited = new boolean[N][M][2];
        Queue<Move> queue = new LinkedList<>();
        queue.offer(new Move(0,0,0,false));
        visited[0][0][0] = true;

        while(!queue.isEmpty()){
            Move poll = queue.poll();

            if(poll.x == N-1 && poll.y == M-1){
                result = poll.cnt+1;
                break;
            }

            for(int d=0;d<4;d++){
                int nx = poll.x + dx[d];
                int ny = poll.y + dy[d];

                if(isOut(nx,ny)) continue;

                if(map[nx][ny] == 1){
                    if(!poll.isDestroy){
                        queue.offer(new Move(nx,ny, poll.cnt+1,true));
                        visited[nx][ny][1] = true;
                    }
                }else{
                    if(!poll.isDestroy && !visited[nx][ny][0]){
                        queue.offer(new Move(nx,ny, poll.cnt+1,false));
                        visited[nx][ny][0] = true;
                    }else if(poll.isDestroy && !visited[nx][ny][1]){
                        queue.offer(new Move(nx,ny, poll.cnt+1,true));
                        visited[nx][ny][1] = true;
                    }
                }
            }
        }

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static boolean isOut(int nx,int ny){
        return nx<0 || ny<0 || nx>=N || ny>=M;
    }

    private static void print() {
        for(int i = 0; i< N; i++){
            for(int j = 0; j< M; j++){
                System.out.print(map[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
