-- 루시와 엘라 찾기
SELECT animal_id,
       name,
       sex_upon_intake
FROM   animal_ins
WHERE  name IN ('Lucy', 'Ella', 'Pickle', 'Rogan', 'Sabrina', 'Mitty')
ORDER BY animal_id

-- 이름에 el이 들어가는 동물 찾기
-- 기본값은 대소문자 구분없이 찾는다. 구분하려면 아래 링크 확인
-- https://kmj1107.tistory.com/entry/MS-SQL-대소문자-구분

SELECT animal_id,
       name
FROM animal_ins
WHERE name LIKE '%EL%'
      AND animal_type = 'Dog'
ORDER BY name


-- 중성화 여부 파악하기
-- http://www.spatium.co.kr/languages/content.php?chno=5&bno=34

-- 풀이 1: IF문 활용
SELECT animal_id,
       name,
       If(sex_upon_intake LIKE '%Neutered%' 
          OR sex_upon_intake LIKE '%Spayed%', 
          'O', 
          'X') AS 중성화
FROM animal_ins

-- 풀이 2: case문 활용
SELECT animal_id, name,
(case when sex_upon_intake like '%Neutered%' then 'O'
 when sex_upon_intake like '%Spayed%' then 'O' else 'X' end) AS 중성화
from animal_ins
order by animal_id

-- 풀이 3: regexp(정규식) + case
SELECT  A.ANIMAL_ID,
        A.NAME,
        CASE    WHEN A.SEX_UPON_INTAKE  REGEXP 'Neutered|Spayed'
                THEN 'O'
                ELSE 'X'
        END AS 중성화
FROM   ANIMAL_INS AS A 
ORDER BY A.ANIMAL_ID

-- 풀이 4: regexp + if 는 왜 안될까?
-- https://dev.mysql.com/doc/refman/8.0/en/regexp.html#operator_regexp
SELECT animal_id,
       name,
       If(sex_upon_intake REGEXP('Neutered|Spayed'),
          'O', 
          'X') AS 중성화
FROM animal_ins

-- 오랜 기간 보호한 동물(2)
-- ORDER BY에 DATEDIFF() 함수 쓰니 에러 발생... 그냥 빼주면 된다.
SELECT o.animal_id,
       o.name
FROM animal_ins as i
INNER JOIN animal_outs as o
    ON i.animal_id = o.animal_id
ORDER BY (o.DATETIME - i.DATETIME) DESC
LIMIT 2


-- DATETIME에서 DATE로 형 변환
SELECT   ANIMAL_ID, 
         NAME,
         DATE_FORMAT(`DATETIME`, '%Y-%m-%d') AS '날짜'
 FROM ANIMAL_INS 
ORDER BY ANIMAL_ID ASC