package com.ssafy.algo.study.week15;

public class 매칭점수_42893_programmers {
	/*
	 * 접근:
	 * 정규표현식 공부 필요!
	 * 
	 * 
	 * 시간복잡도:
	 * 
	 * 
	 * 공간복잡도:
	 * 
	 * 
	 * 풀이에 걸린 시간:
	 * 
	 * 
	 */
	public static void main(String[] args) {
		System.out.println(solution("blind",new String[] {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"}));
	}
	
	static int[] basicScores, outerLinks, linkScores, scores;
	static String[] url;
	
	static public int solution(String word, String[] pages) {
		
		int N = pages.length;
		
		basicScores = new int[N];
		outerLinks = new int[N];
		linkScores = new int[N];
		scores = new int[N];
		url = new String[N];
		
		for (int i = 0; i < pages.length; i++) {
			getBasicScores(pages[i], word);
		}
		
		
        int answer = 0;
        return answer;
        
    }

	private static void getBasicScores(String page, String word) {
		
		//body 태그 추출
		String bodyTag = page.substring(page.indexOf("<body>")+7, page.indexOf("</body>"));
		
		
		// 소문자로 변환
		String bodyTagLowerCase = bodyTag.toLowerCase();
		
		
		
		bodyTagLowerCase.replaceAll("\\b"+word+"\\b","");
		
		
	}

}
