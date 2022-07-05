import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * date: 22.07.04
 */

 /*
  * memo: 문제를 잘 못 보고, 모든 K지점을 방문할 때 고도 차이의 최소값을 구하는 줄 알았다.
  * 블로그에 정리 : https://camel-man-ims.tistory.com/51
  */

// 82454
public class Main {
    static int N;
    static char[][] map;
    static int[][] dist;
    static int[] mmArr;
    static int max,min;

    static int[] dx = {-1,-1,-1,0,1,1,1,0};
    static int[] dy = {-1,0,1,1,1,0,-1,-1};

    static int sx,sy;
    static int houseCnt;
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        map = new char[N][N];
        dist = new int[N][N];

        for(int i=0;i<N;i++){
            String s = br.readLine();
            for(int j=0;j<N;j++){
                char c = s.charAt(j);
                if(c=='P'){
                    sx = i;
                    sy = j;
                }
                if(c=='K'){
                    houseCnt++;
                }
                map[i][j] = c;
            }
        }
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        mmArr = new int[N*N];
        int p =0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                mmArr[p++] = dist[i][j];
            }
        }

        Arrays.sort(mmArr);

        int left = 0;
        int right = 0;
        int min = Integer.MAX_VALUE;
        
        while(left < N*N && right < N*N){
            // 불가
            if(!bfs(mmArr[left],mmArr[right])){
                right++;
            }else{
            // 가능
                min = Math.min(min, mmArr[right] - mmArr[left]);

                if(left <= right){
                    left++;
                }else{
                    right++;
                }
            }
        }
        
        right = N*N-1;
        while(left < N*N){
            if(bfs(mmArr[left],mmArr[right])){
                min = Math.min(min,mmArr[right]-mmArr[left]);
            }
            left++;
        }
        System.out.println(min);
    }

    static boolean isOut(int nx, int ny){
        return nx<0 || ny<0 || nx>=N || ny>=N;
    }

    private static boolean bfs(int min, int max) {
        if(dist[sx][sy] < min || dist[sx][sy] > max){
            return false;
        }
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.offer(new int[]{sx,sy});
        visited[sx][sy] = true;

        int kCnt = 0;

        while(!queue.isEmpty()){
            int[] poll = queue.poll();

            for(int d=0;d<8;d++){
                int nx = poll[0] + dx[d];
                int ny = poll[1] + dy[d];

                if(isOut(nx, ny) || dist[nx][ny] > max || dist[nx][ny] < min || visited[nx][ny]) continue;

                if(map[nx][ny] == 'K'){
                    kCnt++;
                }

                visited[nx][ny] = true;
                queue.offer(new int[]{nx,ny});
            }
        }
        
        if(kCnt == houseCnt){
            return true;
        }else{
            return false;
        }
    }
}
