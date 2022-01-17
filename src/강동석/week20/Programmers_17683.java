package programmers;

// [3차] 방금그곡
/*
 * String.replaceAll() 메서드를 잘 이용하여 "#"이 붙어있는 문자만 잘 구분하면 쉽게 해결할 수 있는 문제이다.
 * 처음에 너무 단순하게 메서드를 이용하면 오버헤드가 커서 시간 초과가 발생할 줄 알았는데 아니었다.
 * 그냥 생각나는대로 풀면되는 문제였다...
 */
public class Programmers_17683 {

	public static void main(String[] args) {
		String m = "ABCDEFG";
		String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		System.out.println(solution(m, musicinfos));
	}
	
	public static String solution(String m, String[] musicinfos) {
		m = changToTemp(m); // #붙어있는 문자를 소문자로 변환
		int lenOfMusic = 0;
		String result = "";
		for(int i=0,len=musicinfos.length; i<len; ++i) {
			String[] info = musicinfos[i].split(",");
			int start = Integer.parseInt(info[0].split(":")[0])*60 + Integer.parseInt(info[0].split(":")[1]); // 시작 시각
			int end = Integer.parseInt(info[1].split(":")[0])*60 + Integer.parseInt(info[1].split(":")[1]); // 종료 시각
			int playTime = end - start; // 재생시간
			String title = info[2]; // 제목
			String music = changToTemp(info[3]); // #붙어있는 문자를 소문자로 변환
			
			int length = music.length(); // 악보길이
			StringBuilder sb = new StringBuilder(); // 재생시간동안의 전체 악보
			for(int j=0; j<playTime/length; ++j) {
				sb.append(music); // 처음부터 끝까지 완주하는 횟수만큼 더하기
			}
			for(int j=0; j<playTime%length; ++j) {
				sb.append(music.charAt(j)); // 끝까지 완주하지 못하는 남은 부분만큼 더하기
			}
			String totalMusic = sb.toString();
			if(totalMusic.contains(m)) { // 멜로디 m을 포함하고 있으면
				if(lenOfMusic<playTime) { // 길이가 긴 곡으로 최댓값 갱신
					lenOfMusic = playTime;
					result = title; // 제목도 갱신
				}
			}
		}
		if(lenOfMusic==0) return "(None)"; // 정답이 없을 때
        return result;
    }
	
	public static String changToTemp(String str) { // "#"이 붙어있는 문자를 소문자로 변환
		str = str.replaceAll("C#", "c");
		str = str.replaceAll("D#", "d");
		str = str.replaceAll("F#", "f");
		str = str.replaceAll("G#", "g");
		str = str.replaceAll("A#", "a");
		return str;
	}
}