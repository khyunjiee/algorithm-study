package BOJ_1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static int n;
    static int[][] point;
    static boolean[] inCombination;
    static double answer;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    //TODO: 입력 받고 조합별로 계산해서 값 처리
    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine());
        for(int t = 0;t<testCase;t++) {
            answer = Double.MAX_VALUE;
            n = Integer.parseInt(br.readLine());
            init();
            int sumX = 0, sumY = 0;

            for(int i = 0;i<n;i++) {
                st = new StringTokenizer(br.readLine());
                point[i][0] = Integer.parseInt(st.nextToken());
                point[i][1] = Integer.parseInt(st.nextToken());
            }
            combination(0,n/2);
            System.out.println(answer);
        }
    }

    private static void combination(int index, int cnt) {
        if(cnt == 0){
            answer = getMin(answer,curValue());
        }else{
            for(int i = index;i<n;i++){
                inCombination[i] = true;
                combination(i+1,cnt-1);
                inCombination[i] = false;
            }
        }
    }

    private static double getMin(double a, double b) {
        return a>b ? b : a;
    }

    private static double curValue() {
        int x = 0, y = 0;
        for(int i = 0;i<n;i++){
            if(inCombination[i]){
                x += point[i][0];
                y += point[i][1];
            }else{
                x -= point[i][0];
                y -= point[i][1];
            }
        }
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }

    private static void init() {
        inCombination = new boolean[n];
        point = new int[n][2];
    }

}
