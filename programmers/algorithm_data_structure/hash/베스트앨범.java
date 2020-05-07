package algorithm_data_structure.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 베스트앨범 {

    public static int[] mySolution(String[] genres, int[] plays) {
        /*
         * https://www.geeksforgeeks.org/how-compare-method-works-in-java/
         * https://beginnersbook.com/2013/12/java-string-compareto-method-example/
         *
         * sort() 메소드에 comparator 객체(또는 람다)를 넣어 정렬 규칙을 지정할 수 있다.
         * 기본적으로 오름차순(ASC) 순서이며 리턴값이 음수이면 o1 < o2 라고 판정한다. 
         * 0은 o1 = o2라고 하고 양수는 o1 > o2라고 판정한다.
         * 
         * genresMap: <장르, 곡 정보(곡 고유 번호, 재생 수)>
         * playMap: <장르, 총 장르 재생수>
         * answer: 추천 목록(결과)
         */
        var genresMap = new HashMap<String, HashMap<Integer, Integer>>();
        var playMap = new HashMap<String, Integer>();
        var answer = new ArrayList<Integer>(); 

        // genresMap과 playMap을 채운다.
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            var songsInGenre = genresMap.getOrDefault(genre, new HashMap<Integer, Integer>());
            songsInGenre.put(i, plays[i]);
            genresMap.put(genre, songsInGenre);

            playMap.put(genre, playMap.getOrDefault(genre, 0) + plays[i]);
        }

        // 재생 횟수가 높은 순으로 곡 이름 정렬
        List<String> genreNames = new ArrayList<>(playMap.keySet());
        genreNames.sort((o1, o2) -> playMap.get(o2) - playMap.get(o1));

        // 장르별로 재생횟수가 높은 노래 번호를 추천목록에 추가
        for (String genre : genreNames){
            List<Integer> songsInGenre = new ArrayList<>(genresMap.get(genre).keySet());
            songsInGenre.sort((o1, o2) -> {
                // 더 많이 재생된 노래를 선택
                int diff =  - genresMap.get(genre).get(o1);
                // 재생 횟수가 같으면 고유 번호 낮은 노래 선택
                if (diff == 0){
                    diff = o1 - o2;
                }
                return diff;                    
            });
            answer.addAll(songsInGenre.subList(0, Math.min(songsInGenre.size(), 2)));
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    // ----------------------------------------------------------------------------

    public class Music implements Comparable<Music> {

        private int played;
        private int id;
        private String genre;

        public Music(String genre, int played, int id) {
            this.genre = genre;
            this.played = played;
            this.id = id;
        }

        @Override
        public int compareTo(Music other) {
            if (this.played == other.played)
                return this.id - other.id;
            return other.played - this.played;
        }

        public String getGenre() {
            return genre;
        }
    }

    public int[] bestSolution(String[] genres, int[] plays) {
        /*
         * mapToObj: 원소 하나하나를 다른 걸로 바꿈 collect : reduce나 groupingBy 같이 집약적인 함수를 처리
         * groupingBy() 지정한 값을 기준으로 값을 모은다 sorted() : 지정한 비교함수로 처리 flatMap : 지정한 함수대로
         * 펼쳐서 한 줄로 나열함 mapToInt : 지정한 수로 변경
         */
        return IntStream.range(0, genres.length)
                .mapToObj(i -> new Music(genres[i], plays[i], i))
                .collect(Collectors.groupingBy(Music::getGenre)).entrySet().stream()
                .sorted((a, b) -> sum(b.getValue()) - sum(a.getValue()))
                .flatMap(x -> x.getValue().stream().sorted().limit(2)).mapToInt(x -> x.id).toArray();
    }

    private int sum(List<Music> value) {
        int answer = 0;
        for (Music music : value) {
            answer += music.played;
        }
        return answer;
    }
}