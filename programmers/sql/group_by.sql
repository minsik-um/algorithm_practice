-- 고양이와 개는 몇 마리 있을까
SELECT   animal_type,
         Count(1) AS count
FROM     animal_ins
GROUP BY animal_type

-- 동명 동물 수 찾기
-- 풀이 1: where clause 에서 null 처리
-- Count(1), Count(), Count(*) 등은 null 값을 포함해 계산해 오류 발생
SELECT   name,
         Count(1) as count
FROM     animal_ins
WHERE    NAME IS NOT NULL
GROUP BY name
HAVING Count(1) > 1
ORDER BY NAME

-- 풀이 2: Count(name)으로 지정하여 자동으로 null 제외 처리
SELECT   name,
         Count(1) as count
FROM     animal_ins
GROUP BY name
HAVING Count(name) > 1
ORDER BY NAME

-- 입양 시각 구하기 (1)
SELECT Hour(datetime) as HOUR,
       Count(datetime) AS COUNT
FROM animal_outs
GROUP BY Hour(datetime)
HAVING HOUR BETWEEN 9 AND 19
/*
HAVING HOUR >= 9 AND HOUR <= 19
이 부분을
HAVING Hour(datetime) BETWEEN 9 AND 19
이렇게 하면 datetime column을 인식할 수 없다고 나온다.
*/

-- 입양 시각 구하기 (2)
-- 풀이 1
/*
https://blog.sonim1.com/166
@ 이거는 변수를 선언할 때 사용한다.

:= 정의한다는 뜻
https://math.stackexchange.com/questions/25214/what-does-mean

여기서 group by @hour를 하면
두번째 column 값이 모두 1이 된다. (animal_outs 와 연결이 안되므로)

``(키보드 좌측 상단) 표시된 걸 지워도 작동은 한다.
아마도 reserved keyword와 다르다는 걸 표시하기 위해 `를 단 것 같다.

@hour < 23으로 되어있지만 23까지 표시된다.
where check 후 true 일 경우 @hour + 1 대입 후 select 사용되는 것 같다.
*/
set @hour = -1;
select
    (@hour := @hour +1) as HOUR,
    (select count(*) from animal_outs where Hour(`datetime`) = @hour) as `COUNT`
from animal_outs 
where @hour < 23

-- 풀이2: 임시 테이블 생성
SELECT    t1.hour AS `hour` ifnull(t2.count, 0) AS `count` 
FROM      ( 
                 SELECT 0 AS thehour 
                 UNION ALL SELECT 1 
                 UNION ALL SELECT 2 
                 UNION ALL SELECT 3 
                 UNION ALL SELECT 4 
                 UNION ALL SELECT 5 
                 UNION ALL SELECT 6 
                 UNION ALL SELECT 7 
                 UNION ALL SELECT 8 
                 UNION ALL SELECT 9 
                 UNION ALL SELECT 10 
                 UNION ALL SELECT 11 
                 UNION ALL SELECT 12 
                 UNION ALL SELECT 13 
                 UNION ALL SELECT 14 
                 UNION ALL SELECT 15 
                 UNION ALL SELECT 16 
                 UNION ALL SELECT 17 
                 UNION ALL SELECT 18 
                 UNION ALL SELECT 19 
                 UNION ALL SELECT 20 
                 UNION ALL SELECT 21 
                 UNION ALL SELECT 22 
                 UNION ALL SELECT 23
          ) AS h 
LEFT JOIN 
          ( 
                   SELECT   hour(datetime) AS hour, 
                            count(*)       AS count 
                   FROM     animal_outs 
                   GROUP BY hour) AS t2 
ON        t2.hour = t1.hour


-- 풀이 3: 숫자 하나씩 IF clause를 사용하고 union all로 합친다.
SELECT '0' AS HOUR
       COUNT(
           IF(DATE_FORMAT(DATETIME, '%H')=0,DATE_FORMAT(DATETIME, '%H'), NULL)) AS 'COUNT' 
           FROM ANIMAL_OUTS
UNION ALL
SELECT '1' AS HOUR, COUNT(IF(DATE_FORMAT(DATETIME, '%H')=1,DATE_FORMAT(DATETIME, '%H'), NULL)) AS 'COUNT' FROM ANIMAL_OUTS
.... (생략)
