-- 한줄 주석
/*
여러줄 주석
*/

-- 모든 레코드 조회하기
SELECT   animal_id, 
         animal_type, 
         datetime, 
         intake_condition, 
         NAME, 
         sex_upon_intake 
FROM     animal_ins 
ORDER BY animal_id 

-- 역순 정렬하기
SELECT   NAME, 
         datetime 
FROM     animal_ins 
ORDER BY animal_id DESC

-- 아픈 동물 찾기
SELECT   animal_id, 
         NAME 
FROM     animal_ins 
WHERE    intake_condition = 'Sick' 
ORDER BY animal_id 

-- 어린 동물 찾기
SELECT   animal_id, 
         NAME 
FROM     animal_ins 
WHERE    intake_condition != 'AGED' 
ORDER BY animal_id 

-- 동물의 아이디와 이름
ELECT   animal_id, 
         NAME 
FROM     animal_ins 
ORDER BY animal_id 

-- 여러 기준으로 정렬하기
SELECT   animal_id, 
         NAME, 
         datetime 
FROM     animal_ins 
ORDER BY NAME, 
         datetime DESC

-- 상위 n개 레코드
-- 풀이 1
SELECT   NAME 
FROM     animal_ins 
ORDER BY datetime
limit    1 

-- 풀이2: 서브쿼리 사용
SELECT NAME 
FROM   animal_ins 
WHERE  datetime = (SELECT Min(datetime) 
                   FROM   animal_ins) 