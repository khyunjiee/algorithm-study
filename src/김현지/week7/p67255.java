package programmers.sep.sep07;

/**
 * 프로그래머스 Lv1 키패드 누르기
 *
 * 접근 방식:
 * 키패드의 숫자를 좌표로 설정해 왼손으로 누를지, 오른손으로 누를지 체크했습니다.
 * 조금 복잡하게 푼 감이 없지 않아 있는 것 같은데.. ㅎ 잘 모르겠네요
 *
 * 주의할 점:
 * 0은 조작해서 좌표로 쓰기에 오류가 있어서, 따로 처리했습니다.
 *
 * 시간 복잡도:
 * O(N) -> numbers 배열만큼
 *
 * 소요 시간:
 * 20분
 */

public class p67255 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right"));
    }

    public static String solution(int[] numbers, String hand) {
        String answer = "";
        int[] lastL = { 3, 0 };
        int[] lastR = { 3, 2 };
        char h = ' ';
        if (hand.equals("right")) h = 'R';
        else h = 'L';

        for (int num: numbers) {
            int row = (num-1)/3;
            int col = (num+2)%3;
            if (num == 0) {
                row = 3;
                col = 1;
            }

            char touch = ' ';

            if (col == 0) {
                answer += 'L';
                touch = 'L';
            } else if (col == 2) {
                answer += 'R';
                touch = 'R';
            }
            else {
                int diffL = Math.abs(lastL[0]-row) + Math.abs(lastL[1]-col);
                int diffR = Math.abs(lastR[0]-row) + Math.abs(lastR[1]-col);

                if (diffL < diffR) {
                    answer += 'L';
                    touch = 'L';
                } else if (diffL > diffR) {
                    answer += 'R';
                    touch = 'R';
                } else {
                    answer += h;
                    touch = h;
                }
            }

            if (touch == 'L') {
                lastL[0] = row; lastL[1] = col;
            }
            else {
                lastR[0] = row; lastR[1] = col;
            }
        }

        return answer;
    }
}
