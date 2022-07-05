import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * date: 22.07.05
 * dfs(++y) 조심하기 -> y+=1 해야함
 * 2^26 = 6800만
 *
 * 10이니까
 * 2^25 + 2^25 = 2^26
 *
 * 반례 : https://www.acmicpc.net/board/view/34131
 * https://codingnotes.tistory.com/90
 */

public class Main {
    static int N;
    static int[][] map;
    static int bMax;
    static int wMax;

    // 위에 값들만 체크해주면 됨
    // 좌상 우상
    static int[] dx = {-1,-1};
    static int[] dy = {-1,1};
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        map = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(N==1 && map[0][0]==1){
            System.out.println(1);
            return ;
        }

        // black
        dfs(0,0,0,0);
        // white
        dfs(0,1,0,1);
        System.out.println(bMax+wMax);
    }
    private static void dfs(int x, int y, int cnt,int color) {
        // 이 부분 실수
        // 마지막 값에 대한 체크 y>=N-1로 하면 마지막 값이 체크가 안됨
        if(x == N-1 && y > N-1){
            if(color == 0 ){
                bMax = Math.max(bMax,cnt);
            }else{
                wMax = Math.max(wMax,cnt);
            }
            return ;
        }
        if(y>=N){
            // 이 부분 실수
            // x를 먼저 더해줘야 함
            x+=1;
            if(color == 0){
                if(x%2==0){
                    y=0;
                }else{
                    y=1;
                }
            }else{
                if(x%2==0){
                    y=1;
                }else{
                    y=0;
                }
            }
        }

        if(map[x][y] == 1){
            if(isPossible(x, y)){
                map[x][y] = 2;
                dfs(x,y+2,cnt+1,color);
                map[x][y] = 1;
            }
        }
        dfs(x,y+2,cnt,color);
    }

    private static boolean isPossible(int x, int y) {
        for(int d=0;d<2;d++){
            // 이 부분 실수
            // nx가 loop 밖에 있으면 안되고 안에 있어야 함
            int nx = x;
            int ny = y;
            while(true) {
                nx += dx[d];
                ny += dy[d];

                if(isOut(nx,ny)) break;
                if(map[nx][ny] == 2){
                    return false;
                }
            }
        }
        return true;
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
