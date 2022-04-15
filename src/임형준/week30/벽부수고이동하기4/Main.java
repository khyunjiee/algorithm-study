package src.backjoon.벽부수고이동하기4;

import java.io.*;
import java.util.*;

/**
 * date: 22.04.07
 * memo : 시간초과가 납니다...
 * link : https://devowen.com/253
 */

public class Main {
    static int N,M;
    static int[][] map;
    static int[][] countMap;
    static boolean[][] visited;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static List<Integer> list ;

    static int[][] resultMap;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/벽부수고이동하기4/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        countMap = new int[N][M];
        resultMap = new int[N][M];
        list = new ArrayList<>();
        list.add(0);
        list.add(0);

        for(int i=0;i<N;i++){
            String s = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = s.charAt(j)-'0';
            }
        }

        for(int i=0;i<N;i++){
            countMap[i] = map[i].clone();
        }

        int cnt =2;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(countMap[i][j] == 0){
                    int amount = set(i, j, cnt++);
                    list.add(amount);
                }
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] == 1){
                    int n = countDirs(i, j);
                    resultMap[i][j] = (n+1)%10;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                sb.append(resultMap[i][j]);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int countDirs(int x, int y) {
        Set<Integer> set = new HashSet<>();

        for(int d=0;d<4;d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(isOut(nx,ny) || map[nx][ny] == 1) continue;

            set.add(countMap[nx][ny]);
        }
        int res = 0;

        for(int s : set){
            res += list.get(s);
        }
        return res;
    }

    private static void print() {
        for(int a=0;a<N;a++){
            for(int b=0;b<M;b++){
                System.out.print(countMap[a][b] + " ");
            }
            System.out.println();
        }
    }

    private static int set(int x, int y,int cnt) {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[N][M];
        int amount =0 ;

        queue.offer(new int[]{x,y});
        countMap[x][y] = cnt;
        amount++;
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();

            int x1 = poll[0];
            int y1 = poll[1];

            for(int d=0;d<4;d++){
                int nx = x1 + dx[d];
                int ny = y1 + dy[d];

                if(isOut(nx,ny) || visited[nx][ny] || map[nx][ny] != 0 ) continue;

                queue.offer(new int[]{nx,ny});
                visited[nx][ny] = true;
                countMap[nx][ny] = cnt;
                amount++;
            }
        }
        return amount;
    }
    static boolean isOut(int nx,int ny){
        return nx<0 || ny<0 || nx>=N || ny>=M;
    }
}
