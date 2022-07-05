import java.io.*;
import java.util.*;

/**
 * date: 22.06.26
 * memo: 97%에서 오류가 납니다... 원인을 찾을 수가 없네요...
 *
 * 풀이:
 * 1. queue에 k <- 1 to= K 만큼의 가능한 모든 이동경로를 넣어준다.
 * 2. int[] visited 배열로 방문체크를 해준다. visited[i][j]에 방문할 때, 해당 값보다 작아야만 방문이 가능하다.
 */

public class Main {
    static int N,M,K;
    static char[][] map;

    static int sx,sy,ex,ey;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for(int i=0;i<N;i++){
            String s = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = s.charAt(j);
            }
        }

        st = new StringTokenizer(br.readLine());
        
        sx = Integer.parseInt(st.nextToken())-1;
        sy = Integer.parseInt(st.nextToken())-1;
        ex = Integer.parseInt(st.nextToken())-1;
        ey = Integer.parseInt(st.nextToken())-1;

        bfs();
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }
    private static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        int[][] visisted = new int[N][M];

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                visisted[i][j] = Integer.MAX_VALUE;
            }
        }

        queue.offer(new int[]{sx, sy,0});
        visisted[sx][sy] = 0;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();

            int x = poll[0];
            int y = poll[1];
            int time = poll[2];

            if(x == ex && y == ey){
                res = time;
                return ;
            }

            out: for(int d=0;d<4;d++){
                for(int k=1;k<=K;k++){
                    int nx = x + dx[d] * k;
                    int ny = y + dy[d] * k;

                    if(isOut(nx, ny) || map[nx][ny] == '#'){
                        continue out;
                    }
                    if(time+1 >= visisted[nx][ny]) continue;
                    
                    queue.offer(new int[]{nx,ny,time+1});
                    visisted[nx][ny] = time+1;
                }
            }
        }
    }
    private static void print(int[][] a) {
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean isOut(int nx,int ny){
        return nx<0 || ny <0 || nx>=N || ny>=M;
    }
}
