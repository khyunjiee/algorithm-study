package baekjoon.aug;

import java.util.Scanner;
import java.util.Stack;

/**
 * 백준 1918 후위 표기식
 *
 * 접근방법 :
 * 열린 괄호와 연산자가 나오면 스택에 push한다.
 * 연산자가 나왔을 때 스택에 본인보다 연산자 우선순위가 높거나 같다면 결과에 추가한다.
 * 닫힌 괄호가 나오면 열린 괄호가 스택에서 나올때까지 결과에 스택 연산자들을 추가한다.
 * 반복문이 끝난 후, 스택에 값이 남아있다면 결과 끝에 추가한다.
 *
 * 시간복잡도 :
 * O(N) -> N: 수식 길이
 */

public class b1918 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();

        Stack<Character> symbol = new Stack<>();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            // ( 무조건 스택에 push
            if (c == '(') {
                symbol.push(c);
            }   // ) 스택에서 ( 나올때까지 pop
            else if (c == ')') {
                while (symbol.peek() != '(') sb.append(symbol.pop());
                symbol.pop();
            }   // *, / 스택에서 본인들 연산자 있으면 pop
            else if (c == '*' || c == '/') {
                while (!symbol.isEmpty() && (symbol.peek() == '*' || symbol.peek() == '/')) sb.append(symbol.pop());
                symbol.push(c);
            }   // +, - 스택에서 연산자 있으면 pop
            else if (c == '+' || c == '-') {
                while (!symbol.isEmpty() && symbol.peek() != '(') sb.append(symbol.pop());
                symbol.push(c);
            }   // 알파벳은 무조건 push
            else {
                sb.append(c);
            }
        }

        while (!symbol.isEmpty()) sb.append(symbol.pop());

        System.out.println(sb.toString());
    }
}
