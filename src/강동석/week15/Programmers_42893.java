package programmers;

import java.util.HashMap;
import java.util.Map;

// 매칭 점수
/*
 * 정규식을 이용하면 더욱 깔끔하게 문제를 해결할 수 있을 것 같다.
 * String 문자열의 substring 메서드와 indexOf 메서드를 잘 이용하면 원하는 부분의 문자열을 잘 얻을 수 있다.
 */
public class Programmers_42893 {

	public static void main(String[] args) {
		String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};
		System.out.println(solution("Muzi", pages));
	}
	
	static Map<String, Integer> urlMap;
	static String[] pageArr;
	static int[][] pagesInfo;
	static double[] linkPoints;
	static double[] matchingPoints;
	
	public static int solution(String word, String[] pages) {
		pageArr = pages;
		int pagesLen = pages.length;
		pagesInfo = new int[pagesLen][2]; // pages의 각 원소마다 {기본점수,외부 링크 수}를 저장
		linkPoints = new double[pagesLen]; // 링크점수를 저장
		matchingPoints = new double[pagesLen]; // 매칭점수를 저장
		
		// 기본점수 저장
		for(int i=0; i<pagesLen; ++i) {
			pagesInfo[i][0] = cntOfWord(word.toLowerCase(), pages[i].toLowerCase()); // 소문자로 변환
		}
		
		// 외부 링크 수 저장
		for(int i=0; i<pagesLen; ++i) {
			pagesInfo[i][1] = cntOfLink(pages[i].toLowerCase()); // 소문자로 변환
		}
		
		// 각 페이지의 url과 인덱스를 Map으로 저장
		urlMap = new HashMap<String, Integer>();
		for(int i=0; i<pagesLen; ++i) {
			urlMap.put(getUrl(pages[i]), i);
		}
        
		// 링크점수 저장
		for(int i=0; i<pagesLen; ++i) {
			recordLinkPoint(i);
		}
		
		// 매칭점수 저장
		for(int i=0; i<pagesLen; ++i) {
			matchingPoints[i] = pagesInfo[i][0]+linkPoints[i]; // 매칭점수 = 기본점수 + 링크점수
		}
		
		
		int maxIdx = 0; // 최대 매칭점수를 가지는 페이지의 인덱스
		double matchingPoint = matchingPoints[0]; // 0번째 인덱스의 페이지 매칭점수
		for(int i=1; i<pagesLen; ++i) {
			if(matchingPoint < matchingPoints[i]) {
				matchingPoint = matchingPoints[i];
				maxIdx = i;
			}
		}
        return maxIdx;
    }
	
	public static int cntOfWord(String word, String html) { // html문자열에서 완전히 일치하는 word단어의 갯수 찾기
		int cnt = 0; // 발견한 word의 갯수
		int idx = 0; // word를 찾기위해 html내에서 시작위치를 가리키는 인덱스
		while(true) { // word를 계속 발견하는 동안
			idx = html.indexOf(word,idx+1); // 어차피 인덱스0은 검색하는 경우가 없으므로 idx가 처음에 1부터 검색해도 괜찮다.
			if(idx != -1) { // word를 발견하면
				if(!isAlphabet(html.charAt(idx-1)) && !isAlphabet(html.charAt(idx+word.length()))) // word바로 이전과 이후의 문자가 둘 다 알파벳이 아니면
				cnt++; // 갯수 증가
			}
			else break; // word를 더 이상 발견하지 못하면 종료
		}
		return cnt;
	}
	
	public static boolean isAlphabet(char ch) { // 알파벳 소문자 체크
		return ch>='a' && ch<='z';
	}
	
	public static int cntOfLink(String html) { // html문자열에서 a태그의 시작부분인 "<a " 갯수 찾기
		int cnt = 0; // 발견한 a태그의 시작 갯수
		int idx = 0;
		while(true) {
			idx = html.indexOf("<a ",idx+1); // a태그의 시작 부분 검색
			if(idx != -1) { // 발견하면
				cnt++;
			}
			else break; // 더 이상 발견하지 못하면 종료
		}
		return cnt;
	}
	
	public static String getUrl(String html) { // 해당 페이지의 url을 반환
		// meta태그 내에서 "https://" 앞부분을 항상 다음과 같은 형태라고 가정, 문제에서 명확(?)하게 제시를 해주지 않은 것 같다.
		int idxOfMetaTagStart = html.indexOf("<meta property=\"og:url\" content=\"");
		int idxOfUrl = html.indexOf("https://",idxOfMetaTagStart);
		int idxOfMataTagEnd = html.indexOf("\"",idxOfUrl);
		String url = html.substring(idxOfUrl, idxOfMataTagEnd);
		return url;
	}
	
	public static void recordLinkPoint(int pageIdx) { // 링크점수를 기록
		String html = pageArr[pageIdx];
		int idxOfATag = 0;
		while(true) {
			idxOfATag = html.indexOf("<a ",idxOfATag+1); // a태그의 시작 찾기
			if(idxOfATag != -1) { // 외부링크 찾으면
				int idxOfLinkStart = html.indexOf("https://",idxOfATag); // 외부링크의 시작 인덱스
				int idxOfLinkEnd = html.indexOf("\"",idxOfLinkStart); // 외부링크의 끝 인덱스
				String link = html.substring(idxOfLinkStart, idxOfLinkEnd);
				if(urlMap.get(link) != null) { // 해당 외부링크로 연결된 페이지가 존재할 때
					int pageNum = urlMap.get(link); // 해당 링크를 가진 페이지의 인덱스
					linkPoints[pageNum] += (double)pagesInfo[pageIdx][0]/pagesInfo[pageIdx][1]; // (기본 점수/외부 링크 수)를 더하기
				}
			}
			else break;
		}
	}
	
}