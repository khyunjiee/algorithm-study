package src.프로그래머스.해시.위장;

import java.util.Arrays;

import static java.util.stream.Collectors.*;

public class MainV3 {


    public static void main(String[] args) {

    }

    static class Solution {
        public int solution(String[][] clothes) {
            return Arrays.stream(clothes)
                    .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
                    .values()
                    .stream()
                    .reduce(1L, (x, y) -> x * (y + 1)).intValue() - 1;
        }
    }
}
