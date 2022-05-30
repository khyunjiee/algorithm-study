package 문제집.backjoon.두용액;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/두용액/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int left = 0;
        int right = N-1;
        int min = Integer.MAX_VALUE;

        int leftRes = 0;
        int rightRes = 0;

        while(left < right){
            int sum = Math.abs(arr[left] + arr[right]);
            if(sum == 0){
                System.out.println(arr[left] + " " + arr[right]);
                return ;
            }
            if(sum < min){
                min = sum;
                leftRes = left;
                rightRes = right;
            }

            int sumLeft = Math.abs(arr[left+1]+arr[right]);
            int sumRight = Math.abs(arr[right-1]+arr[left]);

            if(sumLeft >= sumRight){
                right--;
            }else{
                left++;
            }
        }
        System.out.println(arr[leftRes] + " " + arr[rightRes]);
    }
}
