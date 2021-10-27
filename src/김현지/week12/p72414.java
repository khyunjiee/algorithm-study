package programmers.oct.oct27;

/*
프로그래머스 Lv3 광고삽입

접근 방식:
매개변수로 넘어오는 play_time, adv_time을 초 단위로 변경합니다.
그리고 총 길이인 play_time + 1 만큼의 배열을 생성하고
로그를 순회하면서 해당 초 위치 배열 인덱스 값을 +1 합니다. (끝점은 포함X)
0초에 광고를 삽입했을 때 누적값을 구하고
광고의 시작과 끝을 +1 하면서 누적값을 비교한다.
long 자료형을 써야하는 것에 주의해야 한다.

소요 시간:
2시간
 */

public class p72414 {
}

class Solution_p72414 {
    public String solution(String play_time, String adv_time, String[] logs) {
        // 매개변수로 넘어오는 play_time과 adv_time 을 초로 변환
        int playTime = convertToSec(play_time);
        int advTime = convertToSec(adv_time);
        int[] count = new int[playTime + 1];    // play_time까지 포함이므로 +1

        // 로그 순회
        for (String s: logs) {
            String[] arr = s.split("-");
            int start = convertToSec(arr[0]);   // 시작 시간
            int end = convertToSec(arr[1]);     // 끝나는 시간
            // 끝나는 시간 제외한 나머지 시간에 +1
            for (int i = start; i < end; i++) count[i]++;
        }

        // 0초에 광고를 삽입한다고 가정했을 때 누적값 sum 구함
        int startTime = 0;
        int endTime = advTime;
        long sum = 0;
        for (int i = startTime; i < endTime; i++) {
            sum += count[i];
        }

        // 누적값과 각 초마다 광고를 넣었을 때의 누적값을 비교해서 max 구함
        long max = sum;
        int maxStartTime = 0;
        while (endTime <= playTime) {
            sum -= count[startTime];
            sum += count[endTime];
            // max 갱신
            if (sum > max) {
                max = sum;
                maxStartTime = startTime + 1;
            }
            startTime++;
            endTime++;
        }

        return convertToString(maxStartTime);
    }

    // string to sec
    private int convertToSec(String s) {
        String[] arr = s.split(":");
        return Integer.parseInt(arr[0]) * 60 * 60 + Integer.parseInt(arr[1]) * 60 + Integer.parseInt(arr[2]);
    }

    // sec to string
    private String convertToString(int s) {
        String result = "";

        int hour = s / 3600;
        s %= 3600;
        int min = s / 60;
        int sec = s % 60;

        if (hour < 10) result += "0";
        result += hour + ":";
        if (min < 10) result += "0";
        result += min + ":";
        if (sec < 10) result += "0";
        result += sec;

        return result;
    }
}
