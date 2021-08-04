package baekjoon.aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 18870 좌표압축
 *
 * 접근 방법 :
 * N이 1,000,000 이었기 때문에, O(N^2) 알고리즘을 구현하면 시간 초과가 발생할 것이다.
 * 따라서, for문을 두 번 순회하지 않고도 답을 도출할 수 있도록 했다.
 * Set 또는 Map 을 사용해야겠다는 생각을 했고, Set 또한 순회하면서 값을 찾아야하기 때문에
 * key를 사용하는 Map으로 구현했다.
 * 입력으로 들어온 숫자 배열을 sorting한 후, number와 index를 map에 순서대로 저장했다.
 *
 * 시간복잡도 :
 * Arrays.sort() 에서 퀵 정렬의 시간복잡도인 O(NlogN)
 */

public class b18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 숫자와 인덱스를 담을 배열 (인덱스 = 자신보다 작은 숫자의 수)
        Map<Integer, Integer> map = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N]; // 입력 배열 저장

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // 입력 배열 초기화
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬된 배열 추가로 생성
        int[] sort_numbers = numbers.clone();
        Arrays.sort(sort_numbers);

        // index를 추가해가며 map에 숫자 저장
        int index = 0;
        for (int num: sort_numbers) {
            if (!map.containsKey(num)) {
                map.put(num, index++);
            }
        }

        // key로 찾아오면 value는 index 값, 즉 자신보다 작은 숫자의 개수일 것
        for (int i = 0; i < N; i++) {
            sb.append(map.get(numbers[i]) + " ");
        }

        System.out.println(sb.toString());
    }
}
