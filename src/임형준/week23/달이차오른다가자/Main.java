package src.backjoon.달이차오른다가자;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * date: 22.02.20
 * 64개의 평면을 만드는 것이 핵심 풀이
 * 키를 얻으면 해당 키를 얻은 값으로 위치를 이동시켜준다.
 * 나머지는 bfs를 돌려주면 되는 문제
 */

public class Main {
    static int N,M;
    static char[][] map;
    static boolean[][][] visited;

    static int startX,startY;
    static int result;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Move{
        int x;
        int y;
        int cnt;
        int key;

        public Move(int x, int y, int cnt, int key) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.key = key;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/달이차오른다가자/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        result = Integer.MAX_VALUE;

        for(int i=0;i<N;i++){
            String[] split = br.readLine().split("");
            for(int j=0;j<M;j++){
                if(split[j].charAt(0) == '0'){
                    startX = i;
                    startY = j;
                }
                map[i][j] = split[j].charAt(0);
            }
        }

        bfs(startX,startY);
        System.out.println(result == Integer.MAX_VALUE ? "-1" : result);
    }

    private static void bfs(int startX, int startY) {
        visited = new boolean[N][M][1<<6];
        Queue<Move> queue = new LinkedList<>();
        Move move = new Move(startX,startY,0,0);
        visited[startX][startY][0] = true;
        queue.offer(move);

        while(!queue.isEmpty()){
            Move poll = queue.poll();

            if(map[poll.x][poll.y] == '1'){
                result = Math.min(result,poll.cnt);
                continue;
            }

            for(int d=0;d<4;d++){
                int nx = poll.x + dx[d];
                int ny = poll.y + dy[d];

                if(isOut(nx,ny) || visited[nx][ny][poll.key] || map[nx][ny] == '#') continue;

                if(Pattern.matches("[a-f]",String.valueOf(map[nx][ny]))){
                    int key = poll.key | (1<< (map[nx][ny] -'a'));
                    queue.offer(new Move(nx,ny,poll.cnt+1,key));
                    visited[nx][ny][key] = true;
                }else if(Pattern.matches("[A-F]",String.valueOf(map[nx][ny]))){
                    int door = (1<< (map[nx][ny] - 'a'));
                    int key = poll.key;
                    if((key&door) == door){
                        queue.offer(new Move(nx,ny,poll.cnt+1,key));
                        visited[nx][ny][key] = true;
                    }
                }else{
                    queue.offer(new Move(nx,ny,poll.cnt+1,poll.key));
                    visited[nx][ny][poll.key] = true;
                }
            }
        }
    }

    private static boolean isOut(int nx,int ny){
        return nx<0 || nx>=N || ny<0 || ny>=M;
    }
}
