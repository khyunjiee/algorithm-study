package src.backjoon.마법사상어와파이어볼;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * date: 22.04.04
 * memo: 틀렸는데, 뭐가 틀렸는지 모르겠네요...
 */

public class Main {
    static int N,M,K;

    static class Ball{
        int x;
        int y;
        int weight;
        int dir;
        int speed;

        public Ball(int x, int y, int weight, int dir, int speed) {
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.dir = dir;
            this.speed = speed;
        }
    }


    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,1,1,1,0,-1,-1,-1};

    static Queue<Ball> queue;
    static List<Ball>[][] map;
    static int[][] checkMap;
    static int answer;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/마법사상어와파이어볼/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        queue = new LinkedList<>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine()," ");
            int x = Integer.parseInt(st.nextToken()) -1;
            int y = Integer.parseInt(st.nextToken()) -1;
            int weight = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            
            queue.offer(new Ball(x,y,weight,dir,speed));
        }
        
        while(K-->0){
            map = new ArrayList[N][N];
            checkMap = new int[N][N];
            move();
            integrate();
        }
        count();
        System.out.println(answer);
    }

    private static void count() {
        for(Ball b : queue){
            answer += b.weight;
        }
    }

    private static void integrate() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(checkMap[i][j] > 1){
                    int weight = 0;
                    int speed = 0;
                    int isOdd = 0;
                    int isEven = 0;
                    for(Ball b : map[i][j]){
                        weight += b.weight;
                        speed += b.speed;
                        if(b.dir%2 == 1){
                            isOdd += 1;
                        }else{
                            isEven +=1;
                        }
                    }
                    weight /= 5;
                    speed = (speed) / checkMap[i][j];

                    if(weight > 0){
                        if(isEven == checkMap[i][j] || isOdd == checkMap[i][j]){
                            for(int d=0;d<8;d+=2){
                                int nx = i + dx[d];
                                int ny = j + dy[d];

                                nx %= N;
                                ny %= N;

                                if(nx<0) nx += N;
                                if(ny<0) ny += N;

                                queue.offer(new Ball(nx,ny,weight,d,speed));
                            }
                        }else{
                            for(int d=1;d<8;d+=2){
                                int nx = i + dx[d];
                                int ny = j + dy[d];

                                nx %= N;
                                ny %= N;

                                if(nx<0) nx += N;
                                if(ny<0) ny += N;

                                queue.offer(new Ball(nx,ny,weight,d,speed));
                            }
                        }
                    }
                }else if(checkMap[i][j] == 1){
                    Ball b = map[i][j].get(0);
                    queue.offer(new Ball(b.x,b.y,b.weight,b.dir,b.speed));
                }
            }
        }
    }

    private static void move() {
        while(!queue.isEmpty()) {
            Ball b = queue.poll();
            int nx = b.x + dx[b.dir]*b.speed;
            int ny = b.y + dy[b.dir]*b.speed;

            nx %= N;
            ny %= N;

            if (nx < 0) nx += N;
            if (ny < 0) ny += N;

            checkMap[nx][ny] +=1;

            Ball newBall = new Ball(nx, ny, b.weight, b.dir, b.speed);

            if(map[nx][ny] == null){
                map[nx][ny] = new ArrayList<>();
            }
            map[nx][ny].add(newBall);
        }
    }
}
