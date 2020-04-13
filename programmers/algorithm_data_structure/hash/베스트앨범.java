import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 베스트앨범 {

    public int sum(ArrayList<Integer[]> list) {
        int result = 0;
        for (Integer[] node : list) {
            result += node[1];
        }
        return result;
    }

    public int[] mySolution(String[] genres, int[] plays) {
        /*
        뒤에 bestSolution1과 달리 Comparator를 매번 다르게 지정해줘서
        코드 크기가 커졌다.

        bestSolution1처럼 Map으로 데이터들을 통일하면
        하나의 Comparator(value 기준으로 정렬)로 처리할 수 있다.

        그리고 굳이 songs에 index와 play 수를 담을 필요는 없다.
        그냥 따로 장르별 플레이 총합 맵을 따로 만들고 정렬하면 코드가 눈에 보기 좋다.
        */
        // 이후 계산을 위해 장르별로 데이터 포함
        Map<String, ArrayList<Integer[]>> songs = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            ArrayList<Integer[]> valueList = songs.getOrDefault(genres[i], new ArrayList<>());
            Integer[] newValue = { i, plays[i] };
            valueList.add(newValue);
            songs.put(genres[i], valueList);
        }

        // 많이 재생된 순서로 장르 정렬
        ArrayList<String> sortedGenres = new ArrayList<>();
        sortedGenres.addAll(songs.keySet());
        Collections.sort(sortedGenres, new Comparator() {
            public int compare(Object o1, Object o2) {
                int sum1 = sum(songs.get((String) o1));
                int sum2 = sum(songs.get((String) o2));

                if (sum1 != sum2)
                    return sum2 - sum1;
                else
                    return songs.get((String) o2).size() - songs.get((String) o1).size();
            }
        });

        // 순서대로 최대 곡 2개씩 뽑아 고유 index 담기
        ArrayList<Integer> answerAL = new ArrayList<>();
        for (String genre : sortedGenres) {
            ArrayList<Integer[]> songsInGenre = songs.get(genre);
            Collections.sort(songsInGenre, new Comparator() {
                public int compare(Object o1, Object o2) {
                    int v1 = ((Integer[]) o1)[1];
                    int v2 = ((Integer[]) o2)[1];

                    return v2 - v1;
                }
            });

            int count = (songsInGenre.size() >= 2) ? 2 : 1;
            for (int i = 0; i < count; i++) {
                answerAL.add(songsInGenre.get(i)[0]);
            }
        }
        int[] answer = new int[answerAL.size()];
        for (int i = 0; i < answerAL.size(); i++) {
            answer[i] = answerAL.get(i).intValue();
        }
        return answer;
    }

    public int[] bestSolution1(String[] genres, int[] plays) {
        /*
         * 비교 함수를 어떻게 이용하는지 알려주는 코드 object 타입으로 해시맵을 생성하여 코드가 간결해졌다. 기본적인 코드 문법을 익히자
         */
        HashMap<String, Object> genresMap = new HashMap<String, Object>(); // <장르, 곡 정보>
        HashMap<String, Integer> playMap = new HashMap<String, Integer>(); // <장르, 총 장르 재생수>
        ArrayList<Integer> resultAL = new ArrayList<Integer>();

        for (int i = 0; i < genres.length; i++) {
            String key = genres[i];
            HashMap<Integer, Integer> infoMap; // 곡 정보 : <곡 고유번호, 재생횟수>

            if (genresMap.containsKey(key)) {
                infoMap = (HashMap<Integer, Integer>) genresMap.get(key);
            } else {
                infoMap = new HashMap<Integer, Integer>();
            }

            infoMap.put(i, plays[i]);
            genresMap.put(key, infoMap);

            // 재생수
            if (playMap.containsKey(key)) {
                playMap.put(key, playMap.get(key) + plays[i]);
            } else {
                playMap.put(key, plays[i]);
            }
        }

        Iterator it = sortByValue(playMap).iterator();

        while (it.hasNext()) {
            String key = (String) it.next();
            Iterator indexIt = sortByValue((HashMap<Integer, Integer>) genresMap.get(key)).iterator();
            int playsCnt = 0;

            while (indexIt.hasNext()) {
                resultAL.add((int) indexIt.next());
                playsCnt++;
                if (playsCnt > 1)
                    break;
            }
        }

        int[] answer = new int[resultAL.size()];

        for (int i = 0; i < resultAL.size(); i++) {
            answer[i] = resultAL.get(i).intValue();
        }

        return answer;
    }

    private ArrayList sortByValue(final Map map) {
        /*
         * https://www.geeksforgeeks.org/how-compare-method-works-in-java/
         * https://beginnersbook.com/2013/12/java-string-compareto-method-example/
         * 
         * Collections.sort() 에 Comparator를 넣어 정렬 규칙을 정할 수 있다. Comparator의 compare 메소드를
         * 오버라이딩해주자. 기본적으로 오름차순(ASC) 순서이며 리턴값이 음수이면 o1 < o2 라고 판정한다. 0은 o1 = o2라고 하고 양수는
         * o1 > o2라고 판정한다.
         * 
         * 여기선 내림차순 순서를 위해 v2.compareTo(v1)으로 하여 결과 값 부호를 뒤집었다.
         */
        ArrayList<Object> keyList = new ArrayList();
        keyList.addAll(map.keySet());

        Collections.sort(keyList, new Comparator() {
            public int compare(Object o1, Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);

                return ((Comparable) v2).compareTo(v1);
            }
        });

        return keyList;
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

    public int[] bestSolution2(String[] genres, int[] plays) {
        /*
         * mapToObj: 원소 하나하나를 다른 걸로 바꿈 collect : reduce나 groupingBy 같이 집약적인 함수를 처리
         * groupingBy() 지정한 값을 기준으로 값을 모은다 sorted() : 지정한 비교함수로 처리 flatMap : 지정한 함수대로
         * 펼쳐서 한 줄로 나열함 mapToInt : 지정한 수로 변경
         */
        return IntStream.range(0, genres.length).mapToObj(i -> new Music(genres[i], plays[i], i))
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