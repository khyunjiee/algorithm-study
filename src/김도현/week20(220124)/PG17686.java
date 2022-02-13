package pg;

import java.util.*;

public class PG17686 {
    /* Level 2. 파일명 정렬
    정렬
     */

    public static void main(String[] args) {
        PG17686.Solution test = new PG17686.Solution();
        System.out.println(Arrays.toString(test.solution(new String[]{"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"})));
        System.out.println(Arrays.toString(test.solution(new String[]{"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"})));
    }

    static class File {
        String head;
        int number;
        int tail;
        public File(String head, int number, int tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
    }

    static class Solution {
        public String[] solution(String[] files) {
            ArrayList<File> fileList = new ArrayList<>();
            for (int i = 0; i < files.length; i++) {
                String head;
                int number;
                String str = files[i];
                int s = 0, e = str.length();
                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(j) >= '0' && str.charAt(j) <= '9') {
                        s = j;
                        break;
                    }
                }
                for (int j = s; j < str.length(); j++) {
                    if (!(str.charAt(j) >= '0' && str.charAt(j) <= '9')) {
                        e = j;
                        break;
                    }
                }
                head = str.substring(0, s).toLowerCase();
                number = Integer.parseInt(str.substring(s, e));
                fileList.add(new File(head, number, i));
            }

//            for (File f:fileList) {
//                System.out.println(f.head+" "+f.number+" "+f.tail);
//            }

            fileList.sort(new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    int res = o1.head.compareTo(o2.head);
                    if (res != 0) return res;
                    res = Integer.compare(o1.number, o2.number);
                    if (res != 0) return res;
                    return Integer.compare(o1.tail, o2.tail);
                }
            });

            String[] result = new String[files.length];
            for (int i = 0; i < fileList.size(); i++)
                result[i] = files[fileList.get(i).tail];
            return result;
        }
    }
}
