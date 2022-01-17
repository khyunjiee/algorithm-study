package src.backjoon.야구;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * date: 22.01.06
 */

/*
풀이
1. [ o o o 1 o o o o o ]
9! 중 4번째 원소가 1인 값 추출

2. 해당 경우의 수에 따른 값 추출 -> 최대값 비교

시간복잡도
O(n2^n) 인데, n = 9, 낮은 값의 상수

___
실패했는데 왜 실패했는지 모르겠어서 원인 찾는 중
 */

public class Main {

    static int[] temp;

    static int N;
    static int[][] matrix;
    static int count; // 40000
    static int maxCnt;

    static class GroundScore{
        int one;
        int two;
        int three;
        int outCount;

        public GroundScore(int one, int two, int three, int outCount) {
            this.one = one;
            this.two = two;
            this.three = three;
            this.outCount = outCount;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/야구/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        temp = new int[9];
        matrix = new int[N][9];
        maxCnt = 0;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<9;j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        getTemp(0,0);
        System.out.println(maxCnt);
    }

    private static void getTemp(int cnt, int flag) {
        if(cnt == 9){
            if(temp[3] == 0){
                int score = 0;
                int pointer = 0;

                for(int i=0;i<N;i++){
                    GroundScore groundScore = new GroundScore(-1,-1,-1,0);
                    boolean[] isInGround = new boolean[9];

                    while(groundScore.outCount <= 3){
                        while (isInGround[pointer]) {
                            pointer = (pointer+1)%9;
                        }

                        int play = matrix[i][temp[pointer]];
                        System.out.println(play);

                        switch (play){
                            case 0:
                                groundScore.outCount +=1;
                                pointer = (pointer+1)%9;
                                break;
                            case 1:
                                if(groundScore.three != -1){
                                    isInGround[groundScore.three] = false;
                                    groundScore.three = -1;
                                    score +=1;
                                }
                                if(groundScore.two != -1){
                                    groundScore.three = groundScore.two;
                                    groundScore.two = -1;
                                }
                                if(groundScore.one != -1){
                                    groundScore.two = groundScore.one;
                                }
                                groundScore.one = temp[pointer];
                                isInGround[temp[pointer]] = true;

                                pointer = (pointer+1)%9;
                                break;
                            case 2:
                                if(groundScore.three != -1){
                                    isInGround[groundScore.three] = false;
                                    groundScore.three = -1;
                                    score +=1;
                                }
                                if(groundScore.two != -1){
                                    isInGround[groundScore.two] = false;
                                    groundScore.two = -1;
                                    score +=1;
                                }
                                if(groundScore.one != -1){
                                    groundScore.three = groundScore.one;
                                }
                                groundScore.two = temp[pointer];
                                isInGround[temp[pointer]] = true;

                                pointer = (pointer+1)%9;
                                break;
                            case 3:
                                if(groundScore.three != -1){
                                    isInGround[groundScore.three] = false;
                                    groundScore.three = -1;
                                    score +=1;
                                }
                                if(groundScore.two != -1){
                                    isInGround[groundScore.two] = false;
                                    groundScore.two = -1;
                                    score +=1;
                                }
                                if(groundScore.one != -1){
                                    isInGround[groundScore.one] = false;
                                    groundScore.one = -1;
                                    score +=1;
                                }
                                groundScore.three = temp[pointer];
                                isInGround[temp[pointer]] = true;

                                pointer = (pointer+1)%9;
                                break;
                            case 4:
                                score+=1;
                                pointer = (pointer+1)%9;
                                break;
                        }
                    }
                }
                maxCnt = Math.max(maxCnt,score);
            }
            return;
        }

        for(int i=0;i<9;i++){
            if((flag & 1 << i) != 0) continue;
            temp[cnt] = i;
            getTemp(cnt+1, flag | 1 << i);
        }
    }

    private static void print() {
        for(int i=0;i<N;i++){
            for(int j=0;j<9;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
