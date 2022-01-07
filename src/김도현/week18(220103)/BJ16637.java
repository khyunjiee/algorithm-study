package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ16637 {
    /* 16637. 괄호 추가하기
    [1 <= N <= 19] => 최대 연산자 9개 => 2^9 => DFS
     */
    static int M;
    static int[] nums;
    static char[] ops;
    static int result = Integer.MIN_VALUE;

    public static void dfs(int idx, int res) {
        if (idx >= M) {
            if (res > result) result = res;
            return;
        }
        // 괄호 없이
        int t = res;
        if (ops[idx] == '+') t += nums[idx];
        else if (ops[idx] == '-') t -= nums[idx];
        else t *= nums[idx];
        dfs(idx + 1, t);
        // 다음 연산자에 괄호 추가
        if (idx < M - 1) {
            t = res;
            int s;
            // 괄호 내부 계산
            if (ops[idx+1] == '+') s = nums[idx] + nums[idx+1];
            else if (ops[idx+1] == '-') s = nums[idx] - nums[idx+1];
            else s = nums[idx] * nums[idx+1];
            // 괄호 외부 계산
            if (ops[idx] == '+') t += s;
            else if (ops[idx] == '-') t -= s;
            else t *= s;
            dfs(idx + 2, t);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine()) / 2;
        char[] str = br.readLine().toCharArray();
        // 숫자, 연산자를 분리하여 배열에 저장
        nums = new int[M];
        ops = new char[M];
        for (int i = 0; i < M; i++) {
            nums[i] = str[2*i+2] - '0';
            ops[i] = str[2*i+1];
        }
        dfs(0, str[0] - '0');
        System.out.println(result);
    }
}
