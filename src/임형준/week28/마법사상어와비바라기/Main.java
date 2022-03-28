package src.backjoon.마법사상어와비바라기;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N,M;
    static int[][] map;
    static int[] dx = {0,-1,-1,-1,0,1,1,1};
    static int[] dy = {-1,-1,0,1,1,1,0,-1};

    static int[] cx = {-1,-1,1,1};
    static int[] cy = {-1,1,-1,1};
    static boolean[][] checked;

    static class MoveInfo{
        int dir;
        int degree;

        public MoveInfo(int dir, int degree) {
            this.dir = dir;
            this.degree = degree;
        }
    }

    static class Cloud{
        int x;
        int y;

        public Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Queue<Cloud> queue;

    static MoveInfo[] infos;
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/마법사상어와비바라기/input.txt"));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][N];
        queue = new LinkedList<>();

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j] = sc.nextInt();
            }
        }
        infos = new MoveInfo[M];

        for(int i=0;i<M;i++){
            int dir = sc.nextInt()-1;
            int move = sc.nextInt();

            infos[i] = new MoveInfo(dir,move);
        }

        queue.add(new Cloud(N-2,0));
        queue.add(new Cloud(N-1,0));
        queue.add(new Cloud(N-1,1));
        queue.add(new Cloud(N-2,1));

        for(MoveInfo info : infos){
            checked = new boolean[N][N];
            Queue<Cloud> temp = new LinkedList<>();

            while(!queue.isEmpty()){
                Cloud cloud = queue.poll();
                int nx = cloud.x + dx[info.dir]*info.degree;
                nx %= N;

                int ny = cloud.y + dy[info.dir]*info.degree;
                ny %= N;

                if(nx<0) nx = N+nx;
                if(ny<0) ny = N+ny;

                checked[nx][ny] = true;
                map[nx][ny] += 1;

                temp.add(new Cloud(nx,ny));
            }

            while(!temp.isEmpty()){
                Cloud poll = temp.poll();
                int x = poll.x;
                int y = poll.y;
                for(int d=0;d<4;d++){
                    int nx = x + cx[d];
                    int ny = y + cy[d];

                    if(isOut(nx,ny)) continue;

                    if(map[nx][ny] > 0){
                        map[x][y] += 1;
                    }
                }
            }

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(checked[i][j]) continue;
                    if(map[i][j] >=2) {
                        map[i][j] -= 2;
                        queue.offer(new Cloud(i,j));
                    }
                }
            }
        }

        int answer = 0;


        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                answer += map[i][j];
            }
        }

        System.out.println(answer);
    }

    private static void printChecked() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(checked[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void print() {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isOut(int nx,int ny){
        return nx<0 || ny<0 || nx>=N || ny>=N;
    }
}
