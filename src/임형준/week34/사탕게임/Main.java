import java.io.*;



public class Main {
    static int N;
    static char[][] map;
    static int[] dx = {1,0};
    static int[] dy = {0,1};
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        res = 0;

        for(int i=0;i<N;i++){
            String s = br.readLine();
            for(int j=0;j<N;j++){
                map[i][j] = s.charAt(j);
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                char[][] cloneMap = new char[N][N];

                for(int k=0;k<N;k++){
                    cloneMap[k] = map[k].clone();
                }

                for(int d=0;d<2;d++){
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (isOut(nx,ny)) continue;

                    char c = cloneMap[i][j];
                    cloneMap[i][j] = cloneMap[nx][ny];
                    cloneMap[nx][ny] = c;

                    int max = 0;
                    max = Math.max(max,getMax(cloneMap,i,j));
                    max = Math.max(max,getMax(cloneMap,nx,ny));
                    res = Math.max(res,max);

                    c = cloneMap[i][j];
                    cloneMap[i][j] = cloneMap[nx][ny];
                    cloneMap[nx][ny] = c;
                }
            }
        }
        System.out.println(res);
    }

    private static int getMax(char[][] cloneMap, int i, int j) {
        int max = 0 ;
        char before = cloneMap[0][j];
        int cnt = 1;
        for(int n=1;n<N;n++){
            char cur = cloneMap[n][j];

            if(before == cur){
                cnt++;
                max = Math.max(cnt,max);
            }else{
                cnt = 1;
                before = cur;
            }
        }

        before = cloneMap[i][0];
        cnt = 1;
        for(int n=1;n<N;n++){
            char cur = cloneMap[i][n];

            if(before == cur){
                cnt++;
                max = Math.max(cnt,max);
            }else{
                cnt = 1;
                before = cur;
            }
        }
        return max;
    }

    static boolean isOut(int nx, int ny){
        return nx<0 || ny<0 || nx>=N || ny>=N;
    }
}
