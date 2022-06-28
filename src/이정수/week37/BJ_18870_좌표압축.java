package week37;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 문제:
 * 링크:
 * 
 * 풀이:
 * 이중 for문으로 각 원소에 대해 다른 모든 원소와 크기 비교를 하면 O(N^2)시간으로
 * 시간초과가 일어날 수 있습니다. 따라서 정렬을 사용해야합니다. 압축 결과는 X_i>X_j를 만족
 * 하는 서로 다른 좌표의 개수와 같으므로 중복된 원소를 없애주면 정렬에 걸리는 시간을 단축
 * 할수 있습니다. 따라서 컬렉션의 Set 객체로 만들어준 뒤에 정렬을 수행하면 인덱스로 각각의
 * 원소보다 작은 원소의 수를 셀수 있습니다. 각 원소의 위치는 binary search로 구합니다.
 * 
 * 시간복잡도:
 * 정렬에서 O(NlogN), Set객체로 만들때 어느정도 시간이 걸리는지 잘 모르겠습니다.
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class BJ_18870_좌표압축 {

	public static void main(String[] args) throws IOException {
		BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(bin.readLine());
		
		int[] coordinates = Arrays.asList(bin.readLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();
		
		Set<Integer> set = new HashSet<>(Arrays.stream(coordinates).boxed().collect(Collectors.toList()));
		
		ArrayList<Integer> list = new ArrayList<>(set); //Set -> ArrayList

		Collections.sort(list); //정렬
		
		int[] sorted = new int[list.size()];

		for(int i = 0; i < list.size(); i++) { //ArrayList -> Array
			sorted[i] = list.get(i);
		}
		
		for(int coordinate:coordinates) {
			sb.append((Arrays.binarySearch(sorted, coordinate)+" "));
		}
		
		
		System.out.println(sb.toString());
		
	}

}
