package BOJ_15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int MAX_R = 100,MAX_C = 100;
    static int[][] memField = new int[MAX_R+1][MAX_C+1];
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        for(int i = 0;i<n;i++){
            int[] input = new int[4];
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j<4;j++){
                input[j] = Integer.parseInt(st.nextToken());
            }
            makeDragonCurve(input[0],input[1],input[2],input[3]);
        }
//        print(memField);
        System.out.println(answerCheck());
    }
//    static void print(int[][] arr){
//        for(int[] r : arr){
//            for(int c : r){
//                System.out.print(c);
//            }
//            System.out.println("\n");
//        }
//    }

    private static void makeDragonCurve(int startX, int startY, int dir, int generation) {
        List<Integer> direction = new ArrayList<>();
        direction.add(dir);

        for(int i = 1;i<=generation;i++){
            for(int j = direction.size()-1;j>=0;j--){
                direction.add((direction.get(j)+1)%4);
            }
        }
        memField[startY][startX] = 1;
        for(Integer i: direction){
            startX += dx[i];
            startY += dy[i];
            memField[startY][startX] = 1;
        }
    }

    private static int answerCheck() {
        int cnt = 0;
        for(int i = 0;i<MAX_C;i++){
            for(int j = 0;j<MAX_R;j++){
                if(memField[i][j] == 1 && memField[i+1][j] == 1 && memField[i][j+1] == 1 && memField[i+1][j+1] == 1) cnt++;
            }
        }
        return cnt;
    }
}
