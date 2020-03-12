-- 한줄 주석
/*
여러줄 주석
*/

-- 모든 레코드 조회하기
SELECT ANIMAL_ID, ANIMAL_TYPE, DATETIME, INTAKE_CONDITION, NAME, SEX_UPON_INTAKE
FROM ANIMAL_INS
ORDER BY ANIMAL_ID

-- 역순 정렬하기
SELECT NAME, DATETIME
FROM ANIMAL_INS
ORDER BY ANIMAL_ID DESC

-- 아픈 동물 찾기
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION = 'Sick'
ORDER BY ANIMAL_ID

-- 어린 동물 찾기
SELECT ANIMAL_ID, NAME
FROM ANIMAL_INS
WHERE INTAKE_CONDITION != 'AGED'
ORDER BY ANIMAL_ID

-- 동물의 아이디와 이름
SELECT ANIMAL_ID, NAME
FROM  ANIMAL_INS
ORDER BY ANIMAL_ID

-- 여러 기준으로 정렬하기
SELECT ANIMAL_ID, NAME, DATETIME
FROM ANIMAL_INS
ORDER BY NAME, DATETIME DESC

-- 상위 n개 레코드
/*
Kaggle의 Intro to SQL 실습 후
연습 삼아 하고 있어서
서브 쿼리나 LIMIT는 처음 알았음
*/
SELECT NAME
FROM ANIMAL_INS
ORDER BY DATETIME
LIMIT 1

/*
다른 형태의 코드들

[ 서브쿼리 활용 ]

SELECT NAME
FROM ANIMAL_INS
WHERE DATETIME = (select min(DATETIME) from ANIMAL_INS)

[ Oracle 풀이 ]

SELECT A.*
FROM
(
SELECT NAME
FROM ANIMAL_INS
ORDER BY DATETIME
) A
WHERE ROWNUM = 1;
*/
