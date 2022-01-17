package pg;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PG17683 {
    /* Level 2. 방금그곡
    문자열 다루기
     */
    public static void main(String[] args) throws Exception {
        Solution test = new Solution();
        System.out.println(test.solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
        System.out.println(test.solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"}));
        System.out.println(test.solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"}));
        System.out.println(test.solution("CC#BCC#BCC#", new String[]{"03:00,03:08,FOO,CC#B"}));
        System.out.println(test.solution("A#", new String[]{"13:00,13:02,HAPPY,B#A#"}));
        System.out.println(test.solution("CCB", new String[]{"03:00,03:10,FOO,CCB#CCB", "04:00,04:08,BAR,ABC"}));
    }

    static class Music {
        int startTime;
        int playTime;
        String title;
        String score;

        public Music(int startTime, int playTime, String title, String score) {
            this.startTime = startTime;
            this.playTime = playTime;
            this.title = title;
            this.score = score;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getPlayTime() {
            return playTime;
        }

        public String getTitle() {
            return title;
        }

        public String getScore() {
            return score;
        }
    }

    static class Solution {
        public String solution(String m, String[] musicinfos) throws Exception {
            Music[] musics = new Music[musicinfos.length];
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            for(int i = 0; i < musicinfos.length; i++) {
                String[] str = musicinfos[i].split(",");
                Date start = sdf.parse(str[0]);
                Date end = sdf.parse(str[1]);
                int playTime = (int) ((end.getTime() - start.getTime()) / (60 * 1000));
                StringBuilder temp = new StringBuilder(str[3]);
                for (int j = 0; j < temp.length(); j++)
                    if (temp.charAt(j) == '#') temp.setCharAt(j - 1, (char) ('a' - 'A' + temp.charAt(j - 1)));
                String tmp = temp.toString().replaceAll("#","");

                temp = new StringBuilder(tmp);
                for (int j = 0; j < Math.ceil((double) playTime / tmp.length()); j++) temp.append(tmp);
                musics[i] = new Music((int) start.getTime(), playTime, str[2], temp.toString().substring(0, playTime));
            }
//            for (Music music:musics) {
//                System.out.println(music.getPlayTime()+" "+music.getTitle()+" "+music.getScore());
//            }
            StringBuilder mm = new StringBuilder(m);
            for (int j = 0; j < mm.length(); j++)
                if (mm.charAt(j) == '#') mm.setCharAt(j - 1, (char) ('a' - 'A' + mm.charAt(j - 1)));
            m = mm.toString().replaceAll("#","");
            int res = -1;
            for(int i = 0; i < musics.length; i++) {
                String temp = musics[i].getScore();
                int index = temp.indexOf(m);
                if (index == -1) continue;
                if (res != -1) {
                    if (musics[res].getPlayTime() < musics[i].getPlayTime()) {
                        res = i;
                    } else if (musics[res].getPlayTime() < musics[i].getPlayTime() &&
                            musics[res].getStartTime() > musics[i].getStartTime()) {
                        res = i;
                    }
                } else res = i;
            }

            if (res == -1) return "(None)";
            return musics[res].getTitle();
        }
    }
}
