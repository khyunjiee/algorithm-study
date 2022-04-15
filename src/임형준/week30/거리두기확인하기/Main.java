package src.프로그래머스.카카오기출.거리두기확인하기;

import java.util.Arrays;

/**
 * date: 22.04.08
 */

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}});
    }
    static class Solution {
        static char[][] map;
        static int N = 5;
        static boolean[][] visited;
        static boolean flag;

        static int[] dx = {-1,1,0,0};
        static int[] dy = {0,0,-1,1};

        static int[] result = new int[N];

        public int[] solution(String[][] places) {
            int cnt =0;
            for(String[] place : places){
                map = new char[N][N];
                visited = new boolean[N][N];
                flag = true;

                for(int i=0;i<N;i++){
                    String s = place[i];
                    for(int j=0;j<N;j++){
                        map[i][j] = s.charAt(j);
                    }
                }

                for(int i=0;i<N;i++){
                    for(int j=0;j<N;j++){
                        if(map[i][j] == 'P'){
                            dfs(i,j,0);
                        }
                    }
                }
                if(flag){
                    result[cnt] = 1;
                }else{
                    result[cnt] = 0;
                }
                cnt++;
            }
            System.out.println(Arrays.toString(result));
            return null;
        }

        private void dfs(int x, int y,int cnt) {
            if(cnt!=0 && map[x][y] == 'P' && !visited[x][y]){
                flag = false;
                return ;
            }
            if(cnt==2){
                return ;
            }

            visited[x][y] = true;

            for(int d=0;d<4;d++){
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(isOut(nx,ny) || map[nx][ny] == 'X' || visited[nx][ny]) continue;

                dfs(nx,ny,cnt+1);
            }
        }

        boolean isOut(int nx, int ny ){
            return nx < 0 || ny < 0 || nx>=N || ny>= N;
        }

        private void print() {
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    System.out.print(map[i][j]  + " ");
                }
                System.out.println();
            }
        }
    }
}
