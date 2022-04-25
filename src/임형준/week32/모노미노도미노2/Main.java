package src.backjoon.모노미노도미노2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * date: 22.04.25
 * memo : 무한 loop... 실패부분 찾는중
 */

public class Main {
    static int K,N;
    static int[][] map;
    static int lineCnt;
    static class Block {
        int type;
        int x;
        int y;

        public Block(int type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }
    static Block[] blocks;

    // 우 하
    static int[] dx = {1,0};
    static int[] dy = {0,1};
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/모노미노도미노2/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        N = 10;
        lineCnt = 0;
        StringTokenizer st;
        blocks = new Block[K];
        for(int i=0;i<K;i++){
            st= new StringTokenizer(br.readLine(), " ");
            int type = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            blocks[i] = new Block(type,x,y);
        }

        map = new int[N][N];
        // type == 2 : y+1
        // type == 3 : x+1
        for(Block block : blocks){
            dfsRight(block);
            dfsDown(block);
        }
        System.out.println(lineCnt);
    }

    private static void dfsRight(Block block) {
        int t = block.type;
        int x = block.x;
        int y = block.y;
        while(!isOut(block) && !isCrash(block)){
            y++;
        }
        y--;

        if(t==1){
            map[x][y] = 1;
        }else if(t==2){
            map[x][y] = 1;
            map[x][y+1] = 1;
        }else{
            map[x][y] = 1;
            map[x+1][y] = 1;
        }
        boolean flag = false;
        if(y>=6 && y<=9){
            for(int i=0;i<4;i++){
                if(map[i][y] != 1){
                    flag = true;
                    break;
                }
            }

            if(flag){
                for(int i=0;i<4;i++){
                    map[i][y] = 0;
                }
                lineCnt ++;
            }
        }


        while (!checkColumn()){
            for(int j=9;j>5;j--){
                for(int i=0;i<4;i++){
                    map[i][j] = map[i][j-1];
                }
            }
        }
    }

    private static boolean checkColumn() {
        for(int i=0;i<4;i++){
            if(map[i][4]==1 || map[i][5] == 1) {
                return false;
            }
        }
        return true;
    }
    private static boolean checkRow() {
        for(int j=0;j<4;j++){
            if(map[4][j]==1 || map[5][j] == 1) {
                return false;
            }
        }
        return true;
    }

    private static void dfsDown(Block block) {
        int t = block.type;
        int x = block.x;
        int y = block.y;
        while(!isOut(block) && !isCrash(block)){
            x++;
        }
        x--;

        if(t==1){
            map[x][y] = 1;
        }else if(t==2){
            map[x][y] = 1;
            map[x][y+1] = 1;
        }else{
            map[x][y] = 1;
            map[x+1][y] = 1;
        }
        boolean flag = false;

        if(x>=6 && x<=9){
            for(int j=0;j<4;j++){
                if(map[x][j] != 1){
                    flag = true;
                    break;
                }
            }

            if(flag){
                for(int j=0;j<4;j++){
                    map[x][j] = 0;
                }
                lineCnt ++;
            }
        }

        while (!checkRow()){
            for(int i=9;i>5;i--){
                for(int j=0;j<4;j++){
                    map[i][j] = map[i-1][j];
                }
            }
        }
    }

    static boolean isOut(Block b){
        int t = b.type;
        int x = b.x;
        int y = b.y;

        if(t==1){
            return x>= N || y>=N;
        }else if(t==2){
            return x >= N || y>=N || y+1>=N;
        }else{
            return x>=N || x+1>=N || y>=N;
        }
    }
    static boolean isCrash(Block b){
        int t = b.type;
        int x = b.x;
        int y = b.y;

        if(t==1){
            return map[x][y] == 1;
        }else if(t==2){
            return map[x][y] == 1 || map[x][y+1] == 1;
        }else{
            return map[x][y] == 1 || map[x+1][y] == 1;
        }
    }
}
