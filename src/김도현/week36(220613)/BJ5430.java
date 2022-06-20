package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ5430 {
    /* 5430. AC
    덱
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            boolean isFoward = true;
            char[] func = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            String arr = br.readLine();
            String[] nums = arr.substring(1, arr.length() - 1).split(",");
            Deque<String> deque;
            if (nums.length >= 1 && "".equals(nums[0])) deque = new ArrayDeque<>();
            else deque = new ArrayDeque<>(Arrays.asList(nums));
            StringBuilder result = new StringBuilder();
            for (char f:func) {
                if (f == 'R') {
                    if (isFoward) isFoward = false;
                    else isFoward = true;
                } else if (f == 'D') {
                    if (!deque.isEmpty()) {
                        if (isFoward) deque.pollFirst();
                        else deque.pollLast();
                    } else {
                        result = new StringBuilder("error");
                        break;
                    }
                }
            }

            List<String> list = new ArrayList<>(deque);
            if (!"error".equals(result.toString())) {
                result.append("[");
                if (!isFoward) Collections.reverse(list);
                for (String str:list) result.append(str).append(",");
                if (!list.isEmpty()) result.deleteCharAt(result.length() - 1);
                result.append("]");
            }
            System.out.println(result);
        }
    }
}
