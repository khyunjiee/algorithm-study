package src.프로그래머스.카카오기출.인턴2020.경주로건설;

import java.util.LinkedList;
import java.util.Queue;

/**
 * date: 22.02.25
 * 참조 링크 2개
 * 1. 25번 케이스 삑나는 풀이 링크
 *  :https://hidelookit.tistory.com/108
 * 2. 4방향 설정
 *  :https://settembre.tistory.com/481
 */

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int solution1 = solution.solution(new int[][]{{0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 1}, {0, 0, 1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}});
        System.out.println(solution1);
    }
    static class Solution {
        static int N;
        static int[] dx = {-1,1,0,0};
        static int[] dy = {0,0,-1,1};
        static boolean[][][] isVisited;
        static class Location{
            int x;
            int y;
            int score;
            int direction;

            public Location(int x, int y, int score, int direction) {
                this.x = x;
                this.y = y;
                this.score = score;
                this.direction = direction;
            }
        }

        public int solution(int[][] board) {
            N = board.length;

            Queue<Location> queue = new LinkedList<>();
            setQueue(queue, 0);
            setQueue(queue, 1);
            setQueue(queue, 2);
            setQueue(queue, 3);

            int min = Integer.MAX_VALUE;
            int[][] costs = new int[N][N];
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    costs[i][j] = Integer.MAX_VALUE;
                }
            }

            while(!queue.isEmpty()){
                Location poll = queue.poll();

                if(poll.x == N-1 && poll.y == N-1){
                    min = Math.min(min,poll.score);
                    continue;
                }

                for(int d=0;d<4;d++){
                    int nx = poll.x + dx[d];
                    int ny = poll.y + dy[d];

                    if(isOut(nx,ny) || board[nx][ny] == 1) continue;

                    int newScore = poll.score;

                    if(poll.direction == d || poll.direction == -1){
                        newScore += 100;
                    }else{
                        newScore += 600;
                    }

                    if(!isVisited[nx][ny][d] || costs[nx][ny] >= newScore){
                        isVisited[nx][ny][d]= true;
                        costs[nx][ny] = newScore;
                        queue.offer(new Location(nx,ny,newScore,d));
                    }
                }
            }
            return min;
        }

        private void setQueue(Queue<Location> queue, int direction) {
            isVisited = new boolean[N][N][4];
            queue.offer(new Location(0, 0, 0, direction));
            isVisited[0][0][0] = true;
        }

        static void print(int[][] map){
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    System.out.print(map[i][j]+ " ");
                }
                System.out.println();
            }
        }

        static boolean isOut(int nx,int ny){
            return nx<0 || nx>=N || ny<0 || ny>=N;
        }
    }
}
