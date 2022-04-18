package BOJ_14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st = null;
    static long max = Long.MIN_VALUE, min = Long.MAX_VALUE;
    static int n = 0;
    static int[] num;
    static int[] operand = new int[4];

    public static void main(String[] args) throws IOException {
        input();
        solve(0,num[0]);
        System.out.println(max);
        System.out.println(min);
    }

    private static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        num = new int[n];
        for(int i = 0;i<n;i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0;i<4;i++){
            operand[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solve(int depth, int value) {
        if(depth == n-1){
            min = getMin(min,value);
            max = getMax(max,value);
            return ;
        }
        if(operand[0]>0){
            operand[0]--;
            solve(depth+1,value+num[depth+1]);
            operand[0]++;
        }
        if(operand[1]>0){
            operand[1]--;
            solve(depth+1,value-num[depth+1]);
            operand[1]++;
        }
        if(operand[2]>0){
            operand[2]--;
            solve(depth+1,value*num[depth+1]);
            operand[2]++;
        }
        if(operand[3]>0){
            operand[3]--;
            solve(depth+1,value/num[depth+1]);
            operand[3]++;
        }
    }

    private static long getMax(long max, int value) {
        return max < value ? value : max;
    }

    private static long getMin(long min, int value) {
        return min > value ? value : min;
    }


}
