package src.backjoon.벽부수고이동하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * date: 22.02.25
 * memo: 답은 맞게 나오는데, 시간 초과
 * 반례 모음집 : https://www.acmicpc.net/board/view/66299
 */

public class Main {
    static int N,M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int result;

    static class Move implements Comparable<Move>{
        int x;
        int y;
        int cnt;

        public Move(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Move o) {
            return Integer.compare(this.cnt,o.cnt);
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
        List<int[]> walls = new ArrayList<>();

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            char[] chars = st.nextToken().toCharArray();
            for(int j=0;j<M;j++){
                int m = chars[j]-'0';
                if(m == 1) walls.add(new int[]{i,j});
                map[i][j] = m;
            }
        }

        for(int[] wall : walls){
            int[][] newMap = cloneMap();
            newMap[wall[0]][wall[1]] = 0;

            PriorityQueue<Move> pq = new PriorityQueue<>();
            pq.offer(new Move(0,0,0));
            visited = new boolean[N][M];
            visited[0][0] = true;

            while(!pq.isEmpty()){
                Move poll = pq.poll();

                if(poll.x == N-1 && poll.y == M-1){
                    result = Math.min(poll.cnt+1,result);
                    continue;
                }

                for(int d=0;d<4;d++){
                    int nx = poll.x + dx[d];
                    int ny = poll.y + dy[d];

                    if(isOut(nx,ny) || newMap[nx][ny] == 1 || visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    pq.offer(new Move(nx,ny,poll.cnt+1));
                }
            }
        }
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static boolean isOut(int nx,int ny){
        return nx<0 || ny<0 || nx>=N || ny>=M;
    }

    private static int[][] cloneMap() {
        int[][] newMap = new int[N][M];
        for(int i=0;i<N;i++){
            newMap[i] = map[i].clone();
        }
        return newMap;
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
