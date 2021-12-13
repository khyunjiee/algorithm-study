package com.ssafy.algo.study.week15;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 매칭점수_42893_programmers {
	/*
	 * 접근:
	 * 정규식으로 파싱
	 * 
	 * 시간복잡도:
	 * ?
	 * 
	 * 공간복잡도:
	 * ?
	 * 
	 * 풀이에 걸린 시간:
	 * 3h
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(solution("Muzi",new String[] {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"}));
	}
	
	static double[] basicScores,linkScores, matchingScores;	// 기본점수, 링크점수, 매칭점수 배열
	static String[] url;									// 웹페이지 url 배열
	static String[][] outerLinks;							// 외부링크 배열
	
	static public int solution(String word, String[] pages) {
		
		// 배열 생성
		int N = pages.length;
		basicScores = new double[N];
		outerLinks = new String[N][];
		linkScores = new double[N];
		matchingScores = new double[N];
		url = new String[N];
		
		
		// 기본점수, url, 외부링크 구하기
		for (int i = 0; i < pages.length; i++) {
			basicScores[i] = getBasicScores(pages[i], word);
			url[i] = getUrl(pages[i]);
			outerLinks[i] = getOuterLinks(pages[i]);
		}
		
		// 링크점수, 매칭 점수 구하기
		for (int i = 0; i < pages.length; i++) {
			linkScores[i] = getLinkScores(url[i],i,  outerLinks, basicScores);
			matchingScores[i] = basicScores[i] + linkScores[i];
		}
		
		// 최대값 인덱스 구하기
		int answer = getMaxIndex(matchingScores);
		
        return answer;
        
    }

	// 최대값 인덱스 구하는 메소드
	private static int getMaxIndex(double[] scores) {
		
		int idx = 0;	// 인덱스
		double max = 0;	// 최대값
		
		for (int i = 1; i < scores.length; i++) {
			if(max<scores[i]) {
				max = scores[i];
				idx = i;
			}
		}
		
		return idx;
	}

	
	// 링크 점수 구하는 메소드
	private static double getLinkScores(String url, int idx, String[][] outerLinks, double[] basicScores) {
		
		double score = 0;
		
		for (int i = 0; i < outerLinks.length; i++) {
			
			if(idx==i) continue;
			for (int j = 0; j < outerLinks[i].length; j++) {
				if(url.equals(outerLinks[i][j])) 
					score += basicScores[i]/outerLinks[i].length;
			}
		}
		
		return score;
	}

	
	// 외부 링크 배열 구하기
	private static String[] getOuterLinks(String page) {
		
		Pattern pattern = Pattern.compile("<a href=\"(\\S*)\"");
		Matcher matcher = pattern.matcher(page);
		
		
		List<String> outerLinks = new LinkedList<>();
		while(matcher.find()) {
			outerLinks.add(matcher.group().split("=")[1].replaceAll("\"", ""));
		}
		
		return outerLinks.toArray(new String[0]);
		
	}
	
	
	// 페이지의 url 구하기
	private static String getUrl(String page) {
		
		Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
		Matcher matcher = pattern.matcher(page);
		
		while(matcher.find()) {
			return matcher.group().split("=")[2].replaceAll("\"", "");
		}
		
		return null;
	}

	// 기본 점수 구하기
	private static int getBasicScores(String page, String word) {
		
		//body 태그 추출
		String bodyTag = page.substring(page.indexOf("<body>")+7, page.indexOf("</body>")).replaceAll("\\d", " ");
		
		Pattern pattern = Pattern.compile("\\b"+word+"\\b",Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = pattern.matcher(bodyTag);
		String after = matcher.replaceAll("");
		
		int result = (bodyTag.length() - after.length())/word.length();
		
		return result;
		
	}

}
