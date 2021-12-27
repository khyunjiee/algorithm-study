package src.알고리즘스터디.날짜12월3째주.보행자천국;

import java.util.LinkedList;
import java.util.Queue;

public class Test {

    static int[][] map;
    static int[][] count;
    static int[][] countMap;
    static boolean[][] isVisited;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int N;

    public static void main(String[] args) {

        N = 4;
        countMap = new int[N][N];
        isVisited = new boolean[N][N];

        for(int i=0;i<N;i++){
            countMap[i][0] = 1;
        }
        for(int j=0;j<N;j++){
            countMap[0][j] = 1;
        }

        map = new int[][]{
                {0,0,0,0},
                {0,0,0,0},
                {0,1,0,0},
                {0,0,1,0}
        };

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0,0});
        isVisited[0][0]= true;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();

            for(int d=0;d<4;d++){
                int nx = poll[0] + dx[d];
                int ny = poll[1] + dy[d];

                if(isOut(nx,ny) || isVisited[nx][ny] || map[nx][ny] == 1) continue;

                countMap[nx][ny] = countMap[poll[0]][poll[1]] +1;
                isVisited[nx][ny] = true;

                queue.offer(new int[]{nx,ny});
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(countMap[i][j] + " ");
            }
            System.out.println();
        }

    }
    static boolean isOut(int nx,int ny){
        return nx<0 || ny<0 || nx>=N || ny>=N;
    }


}
