package baekjoon.aug;

import java.util.Scanner;

/**
 * 백준 1541 잃어버린 괄호
 *
 * 접근방식 :
 * 무조건 +가 있는 부분은 크게 가져가야 한다.
 * 그래서 - 부분을 먼저 split으로 분리해주고,
 * +가 있는 부분부터 연산해준다.
 * 그 후에 전에 계산한 값에서 무조건 -만 해주면 최솟값을 만들 수 있다.
 *
 * 시간복잡도 :
 * O(N) 수식 계산만큼
 *
 */

public class b1541 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input_str = sc.nextLine();
        String[] strings = input_str.split("-");

        int result = Integer.MAX_VALUE;

        for (int i = 0; i < strings.length; i++) {
            int temp = 0;

            String[] add = strings[i].split("\\+");

            for (int j = 0; j < add.length; j++) {
                temp += Integer.parseInt(add[j]);
            }

            if (result == Integer.MAX_VALUE) result = temp;
            else result -= temp;
        }

        System.out.println(result);

    }
}
