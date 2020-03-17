/*
'20.03.17 (화)
http://www.dpriver.com/pp/sqlformat.htm
위 사이트를 통해 쿼리를 보기 좋게 정렬했다.

그 결과들을 참고하며 코드 스타일을 공부하자
*/

-- 이름이 없는 동물의 아이디
SELECT animal_id
FROM   animal_ins
WHERE  NAME IS NULL

-- 이름이 있는 동물의 아이디
SELECT   animal_id 
FROM     animal_ins 
WHERE    NAME IS NOT NULL 
ORDER BY animal_id

-- NULL 처리하기
SELECT animal_type, 
       Ifnull(NAME, 'No name') AS NAME, 
       sex_upon_intake 
FROM   animal_ins 

/*
https://extbrain.tistory.com/46

case 문은 switch 문처럼 늘려 쓸 수 있다.
*/
-- 
SELECT animal_type, 
       CASE 
         WHEN NAME IS NULL THEN 'No name' 
         ELSE NAME 
       END, 
       sex_upon_intake 
FROM   animal_ins 