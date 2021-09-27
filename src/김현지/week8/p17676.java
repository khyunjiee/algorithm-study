package programmers.sep.sep27;

/**
 * 프로그래머스 추석 트래픽 [Lv3]
 *
 * 접근 방식:로
 * 정렬된 lines 를 적극 활용했습니다.
 * 주어진 배열에서 start, end 시간을 구해서
 * 해당 배열 이후의 시간들 중에 한 값이 포함되는 것들을 모두 카운팅 해서 answer을 업데이트 했습니다.
 * 문제에서 처리시간이 시작시간과 끝시간을 포함한다고 해서 다음 것과 비교를 >= 연산자로 진행했는데
 * 다음 시간에서 1000을 빼기 때문에 > 를 해야 했더라고요.. 주의해야할 것 같습니다.
 *
 * 시간 복잡도:
 * O(N^2)
 *
 * 소요 시간:
 * 1시간 30분
 */

public class p17676 {
    public static void main(String[] args) {
    }
}

class Solution_p17676 {
    public int solution(String[] lines) {
        int answer = 0;
        int len = lines.length; // 배열 길이
        int[] starts = new int[len];    // 각 배열 원소의 시작 시간
        int[] ends = new int[len];      // 각 배열 원소의 끝 시간

        // 시작 시간과 끝 시간을 구해서 배열에 저장
        for (int i = 0; i < len; i++) {
            String[] subs = lines[i].split(" ");    // 0번 인덱스는 무조건 추석 날짜이므로 사용하지 않는다
            // 정수형으로 변환해서 배열에 적재
            ends[i] = calcEnd(subs[1]);
            starts[i] = calcStart(ends[i], subs[2]);
        }

        // 배열을 순회하면서 해당 시간이 포함되는 타임이 몇개인지 찾는다.
        for (int i = 0; i < len; i++) {
            int cnt = 0;
            for (int j = i; j < len; j++) { // 정렬이기 때문에 이후값들만 비교하면 된다
                // *주의* 기준 end 시간이 비교할 start 시간의 1초 사이에 포함된다면 cnt+1
                // >= 로 하면 1.001초도 포함되므로 주의
                if (ends[i] > starts[j] - 1000) cnt++;
            }
            answer = Math.max(answer, cnt);
        }

        return answer;
    }

    // 끝 시간 계산
    private int calcEnd(String time) {
        int hour = Integer.parseInt(time.substring(0, 2)) * 3600;
        int min = Integer.parseInt(time.substring(3, 5)) * 60;
        int sec = Integer.parseInt(time.substring(6, 8));
        int milli = Integer.parseInt(time.substring(9));

        return (hour + min + sec) * 1000 + milli;   // 밀리초 단위로 변환
    }

    // 시작 시간 계산
    // time : 끝 시간, duration: 걸린 처리 시간
    private int calcStart(int time, String duration) {
        // duration을 double로 바꾸고 *1000 해서 정수로 바꿔준다.
        int dur = (int)(Double.parseDouble(duration.substring(0, duration.length()-1)) * 1000);

        // 끝 시간에서 dur 을 빼주고 1을 더해줘야 함 (시작 시간과 끝 시간이 포함이므)
        return time - dur + 1;
    }
}
