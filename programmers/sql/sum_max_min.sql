-- 최댓값 구하기
SELECT Max(DATETIME)
FROM   ANIMAL_INS

-- 최솟값 구하기
SELECT Min(DATETIME)
FROM   ANIMAL_INS

-- 동물 수 구하기
SELECT Count(1)
FROM ANIMAL_INS

-- 중복 제거하기
/*
IS(NOT): NULL 비교 전용 연산자, NULL에 =를 쓰면 안먹힌다.
*/
SELECT Count(DISTINCT NAME) AS NAME_COUNT
FROM   ANIMAL_INS
WHERE  NAME IS NOT NULL