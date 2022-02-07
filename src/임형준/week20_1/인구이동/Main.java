package src.backjoon.인구이동;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * date : 22.01.14

 * union - find 이용
 * 풀이가 복잡합니다. Sol.java 에서처럼 좌표와 큐를 이용하는게 맞는 풀이인 것 같습니다.
 */

public class Main {
    static int N,L,R;
    static int[][] map;
    static int[] parents;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int[][] parentMap;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/인구이동/input.txt"));

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        L = sc.nextInt();
        R = sc.nextInt();
        map = new int[N][N];
        parentMap = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                map[i][j]= sc.nextInt();
            }
        }
        int result = 0;
        while(!isStop()){
            int cnt = 0;
            setParentMap(cnt);
            setParents();
            result++;
            // union - find 설정
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    for(int d=0;d<4;d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(isOut(nx,ny)) continue;
                        int diff = Math.abs(map[i][j] - map[nx][ny]);
                        if( diff >= L && diff <= R){
                            union(parentMap[i][j],parentMap[nx][ny]);
                            parentMap[nx][ny] = find(parentMap[i][j]);
                        }
                    }
                }
            }

            // union - find 로 동기화 안 돼있는 애들 있을 수 있기에, 동기화 시켜줌
            // ex)
            // 0 4 0
            // 0 5 5
            // 5 5 8
            // 4-5 같은 놈
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    parentMap[i][j] = find(parentMap[i][j]);
                }
            }

            // 더해주고 나눠주고 갱신
            int[] sumArr = new int[N*N];
            int[] countArr = new int[N*N];

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    countArr[parentMap[i][j]]++;
                    sumArr[parentMap[i][j]] += map[i][j];
                }
            }
            for(int i=0;i<N*N;i++){
                if(countArr[i] == 0) sumArr[i] = 0;
                else sumArr[i] /= countArr[i];
            }
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(sumArr[parentMap[i][j]] !=0) map[i][j] = sumArr[parentMap[i][j]];
                }
            }
        }


        System.out.println(result);
    }

    static void setParents(){
        parents = new int[N*N];
        for(int i=0;i<N*N;i++){
            parents[i] = i;
        }
    }

    static int find(int a){
        if(parents[a]==a) return a;
        else return parents[a] = find(parents[a]);
    }
    static void union(int a,int b){
        int aRoot = find(a);
        int bRoot = find(b);
        parents[aRoot] = bRoot;
    }

    private static void setParentMap(int cnt) {
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                parentMap[i][j] = cnt++;
            }
        }
    }

    static boolean isStop(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                for(int d=0;d<4;d++){
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if(isOut(nx,ny)) continue;
                    if(Math.abs(map[i][j] - map[nx][ny]) >= L && Math.abs(map[i][j] - map[nx][ny]) <= R){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static void print(int[][] printMap){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.out.print(printMap[i][j] + " ");
            }
            System.out.println();
        }
    }
    static boolean isOut(int nx, int ny){
        return nx<0 || ny<0 || nx>=N || ny>=N;
    }
}
