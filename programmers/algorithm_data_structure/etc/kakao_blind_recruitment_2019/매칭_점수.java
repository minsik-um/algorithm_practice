package algorithm_data_structure.etc.kakao_blind_recruitment_2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 매칭_점수 {
    /**
     * @param word : 기본 점수 계산시 사용하는 글자
     * @param pages : 모든 페이지의 html 문자열
     * @return 최종 점수가 가장 높은(동일 점수 간에는 작은 index 번호) 페이지의 index
     * 
     * 알고리즘 자체는 제시한 그대로 파싱하고 검사하면 되지만,
     * 문제는 2가지가 있다.
     * 
     * 1. float type 사용 시 매칭 저무를 섬세하게 계산하지 못한다.
     * 2. html 형식을 따르지 않는 문서도 있다.
     * 
     * 아래와 같이 대응하는 게 어려웠다.
     * 
     * 1. double 타입으로 바꾼다.
     * 2. 특히 페이지 url을 구할 때 주어진 조건을 모두 만족(<meta -... />)
     *    했을 때만 url을 가져온다. 그외엔 쓰레기 url 값을 알아서 할당하여 계산에서 제외힌다.
     * 
     * 아래 풀이는 정규식을 사용하지 않았다. 다음 betterSolution() 함수에 정규식 풀이도 해보았다.
     */
    public int solution(String word, String[] pages) {
        double maxMatchScore = -1D;
        int maxMatchIndex = -1;
        
        Map<Integer, String> urlMap = new HashMap<>();
        Map<String, Integer> baseScoreMap = new HashMap<>();
        Map<String, List<String>> linkUrlMap = new HashMap<>();
        Map<String, Double> linkScoreMap = new HashMap<>();

        // baseScore 및 기본 웹페이지 정보 파싱
        for (int i = 0; i < pages.length; i++) {
            // String editedPageStr = pages[i].replace('\n', ' ');
            Page page = new Page(pages[i], word);
            urlMap.put(i, page.getUrl());
            baseScoreMap.put(page.getUrl(), page.getBaseScore());
            linkScoreMap.put(page.getUrl(), 0D);            
            linkUrlMap.put(page.getUrl(), page.getLinkUrls());            
        }
        
        // linkScore 계산
        for (Entry<Integer, String> entry : urlMap.entrySet()) {
            int baseScore = baseScoreMap.getOrDefault(entry.getValue(), 0);
            int linkCount = linkUrlMap.getOrDefault(entry.getValue(), new ArrayList<>()).size();
            double linkScore = baseScore * 1D / linkCount;
            
            List<String> linkUrls = linkUrlMap.get(entry.getValue());
            for (String linkUrl : linkUrls) {
                linkScoreMap.put(linkUrl, linkScoreMap.getOrDefault(linkUrl, 0D) + linkScore);
            }
        }

        // matchScore가 최댓값인 웹 페이지 index 찾기
        for (int idx = 0; idx < pages.length; idx++) {
            String url = urlMap.get(idx);
            double matchScore = baseScoreMap.get(url) + linkScoreMap.get(url);
           
            if (matchScore > maxMatchScore) {
                maxMatchScore = matchScore;
                maxMatchIndex = idx;
            } else if (matchScore == maxMatchScore && maxMatchIndex > idx) {
                maxMatchIndex = idx;                
            }
        }
        
        return maxMatchIndex;
    }
    
    class Page {
        private String url;
        private String contents;
        private String targetWord;
        private int baseScore;
        private List<String> linkUrls;

        public Page(String page, String targetWord) {
            this.url = parseUrl(page);
            this.contents = parseContents(page);
            this.targetWord = targetWord;
            
            this.baseScore = checkBaseScore();
            this.linkUrls = checkLinkUrls();
        }
        
        public String getUrl() {
            return this.url;
        }
        
        public int getBaseScore() {
            return this.baseScore;
        }
        
        public List<String> getLinkUrls() {
            return this.linkUrls;
        }
        
        private String parseUrl(String page) {
            int startIdx = page.indexOf("<meta property=\"og:url\" content=\"https://");
            int endIdx = page.indexOf("\"/>", startIdx);
            System.out.println(page.substring(startIdx + 41, endIdx));
            return page.substring(startIdx + 41, endIdx);
        }
        
        private String parseContents(String page) {
            int bodyStartIdx = page.indexOf("<body>") + 6;
            int bodyEndIdx = page.indexOf("</body>", bodyStartIdx);
            
            return page.substring(bodyStartIdx, bodyEndIdx);
        }


        private int checkBaseScore() {
            int ret = 0;
            int idx = 0;
            String word = "";
            String uppedTargetWord = this.targetWord.toUpperCase();
            char[] contents = this.contents.toUpperCase().toCharArray();
            
            while (idx < contents.length) {
                if (Character.isAlphabetic(Character.codePointAt(contents, idx))) {
                    word += contents[idx];
                } else {
                    if (word.toUpperCase().equals(uppedTargetWord)) {
                        ret += 1;              
                    }
                    word = "";
                }

                idx += 1;   
            }

            if (word.length() > 0 && word.equals(uppedTargetWord)) {
                ret += 1;              
            }

            return ret;
        }
        
        private List<String> checkLinkUrls() {
            List<String> ret = new ArrayList<>();
            int idx = 0;
            
            while (idx < this.contents.length()) {
                int startLinkIdx = this.contents.indexOf("a href=\"https://", idx);
                if (startLinkIdx == -1) {
                    break;
                } else {
                    startLinkIdx += 16;
                }
                int endLinkIdx = this.contents.indexOf("\">", startLinkIdx);
                String link = this.contents.substring(startLinkIdx, endLinkIdx);
                ret.add(link);
                idx = endLinkIdx + 2;
            }
                 
            return ret;
        }
    }

    public int betterSolution(String word, String[] pages) {
        double maxMatchScore = -1D;
        int maxMatchIndex = -1;
        
        Map<Integer, String> urlMap = new HashMap<>();
        Map<String, Double> matchScoreMap = new HashMap<>();

        for (int i = 0; i < pages.length; i++) {
            PageReg page = new PageReg(pages[i], word); 

            String url = page.getUrl();
            int baseScore = page.getBaseScore();
            List<String> linkUrls = page.getLinkUrls();
            int linkCount = linkUrls.size();
            double linkScore = baseScore * 1D / linkCount;
            
            urlMap.put(i, url);
            matchScoreMap.put(url, matchScoreMap.getOrDefault(url, 0D) + baseScore);
            for (String linkUrl : linkUrls) {
                matchScoreMap.put(linkUrl, matchScoreMap.getOrDefault(linkUrl, 0D) + linkScore);
            }
        }

        for (int idx = 0; idx < pages.length; idx++) {
            String url = urlMap.get(idx);
            double matchScore = matchScoreMap.get(url);

            if (matchScore > maxMatchScore) {
                maxMatchScore = matchScore;
                maxMatchIndex = idx;
            } else if (matchScore == maxMatchScore && maxMatchIndex > idx) {
                maxMatchIndex = idx;                
            }
        }
        
        return maxMatchIndex;
    }
    
    class PageReg {
        final String PAGE_URL_REG = "<meta property=\"og:url\" content=\"https://(.+)\"/>";
        final String A_TAG_REG = "<a href=\"https://(.+)\">";

        private String wordReg; 

        private String url;
        private String contents;
        private int baseScore;
        private List<String> linkUrls;

        public PageReg(String page, String word) {
            this.url = parseUrl(page);
            this.contents = parseContents(page);
            this.wordReg = "[^a-zA-Z]+" + word + "[^a-zA-Z]+";
            
            this.baseScore = checkBaseScore();
            this.linkUrls = checkLinkUrls();
        }
        
        public String getUrl() {
            return this.url;
        }
        
        public int getBaseScore() {
            return this.baseScore;
        }
        
        public List<String> getLinkUrls() {
            return this.linkUrls;
        }
        
        private String parseUrl(String page) {
            String url = "";
            Pattern pattern = Pattern.compile(this.PAGE_URL_REG);
            Matcher matcher= pattern.matcher(page);

            if (matcher.find()) {
                url = matcher.group(1);
            }

            return url;
        }
        
        private String parseContents(String page) {
            int bodyStartIdx = page.indexOf("<body>") + 6;
            int bodyEndIdx = page.indexOf("</body>", bodyStartIdx);
            
            return page.substring(bodyStartIdx, bodyEndIdx);
        }

        private int checkBaseScore() {
            int wordCount = 0;
            Pattern pattern = Pattern.compile(this.wordReg, Pattern.CASE_INSENSITIVE);
            Matcher matcher= pattern.matcher(this.contents);

            while (matcher.find()) {
                wordCount += 1;
            }

            return wordCount;
        }
        
        private List<String> checkLinkUrls() {
            List<String> links = new ArrayList<>();
            Pattern pattern = Pattern.compile(this.A_TAG_REG);
            Matcher matcher= pattern.matcher(this.contents);

            while (matcher.find()) {
                links.add(matcher.group(1));
            }
   
            return links;
        }
    }
}