package pg;

import java.util.*;
import java.util.stream.Collectors;

public class PG72411 {
    /* Level 2. 메뉴 리뉴얼
    */
    public static void main(String[] args) {
        PG72411.Solution test = new PG72411.Solution();
        System.out.println(Arrays.toString(test.solution(new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[] {2, 3, 4})));
        System.out.println(Arrays.toString(test.solution(new String[] {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[] {2, 3, 5})));
        System.out.println(Arrays.toString(test.solution(new String[] {"XYZ", "XWY", "WXA"}, new int[] {2, 3, 4})));
    }

    static class Solution {
        static final int ALPHABET = 'Z' - 'A' + 1;
        static HashMap<String, Integer> menus = new HashMap<>();

        static class Menu {
            String component;
            int number;
            public Menu(String component, int number) {
                this.component = component;
                this.number = number;
            }
        }

        public void combination(char[] orig, char[] sel, int n, int r, int s, int cnt) {
            if (cnt == r) {
                String key = new String(sel);
                menus.put(key, menus.getOrDefault(key, 0) + 1);
                return;
            }
            for (int i = s; i < n; i++) {
                sel[cnt] = orig[i];
                combination(orig, sel, n, r, i + 1, cnt + 1);
            }
        }

        public char[] sortAlphabetString(String str){
            char[] charArray = new char[str.length()];
            int temp = 0, j = 0;
            for (int i = 0; i < str.length(); i++)
                temp = temp | (1 << (str.charAt(i) - 'A'));
            for (int i = 0; i < ALPHABET; i++)
                if ((temp & (1 << i)) != 0) charArray[j++] = (char) ('A' + i);
            return  charArray;
        }

        public String[] solution(String[] orders, int[] course) {
            char[] orderArray, comb;
            List<Menu> menuList;
            int maxVal;
            ArrayList<String> answer = new ArrayList<>();
            for (int r:course) {
                comb = new char[r];
                for (String order:orders) {
                    orderArray = sortAlphabetString(order);
                    combination(orderArray, comb, orderArray.length, r, 0, 0);
                }
                if (menus.isEmpty()) continue;
//                for (Map.Entry<String,Integer> entry:menus.entrySet())
//                    System.out.println(entry.getKey() + " " + entry.getValue());
                menuList = menus.entrySet().stream().map(o -> new Menu(o.getKey(), o.getValue())).collect(Collectors.toList());
                menuList.sort((o1, o2) -> Integer.compare(o2.number, o1.number));
                maxVal = menuList.get(0).number;
                if (maxVal < 2) continue;
                for (Menu menu : menuList)
                    if (menu.number == maxVal) answer.add(menu.component);
                    else break;
                menus.clear();
            }
            answer.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int len = Math.min(o1.length(), o2.length());
                    for (int i = 0; i < len; i++) {
                        if (o1.charAt(i) < o2.charAt(i)) return -1;
                        else if (o1.charAt(i) > o2.charAt(i)) return 1;
                    }
                    if (o1.length() < o2.length()) return -1;
                    else if (o1.length() > o2.length()) return 1;
                    return 0;
                }
            });
            return answer.toArray(new String[0]);
        }
    }
}
