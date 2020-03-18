-- 없어진 기록 찾기
-- 풀이 1
SELECT o.animal_id as ANIMAL_ID,
       o.name as NAME
FROM   animal_outs as o
LEFT JOIN animal_ins as i
    ON o.animal_id = i.animal_id
WHERE i.animal_id IS NULL
ORDER BY o.animal_id

-- 풀이 2
/*
https://streetdeveloper.tistory.com/23

left outer join()은 left join()과 같다.
*/
SELECT o.animal_id as ANIMAL_ID,
       o.name as NAME
FROM   animal_outs as o
LEFT OUTER JOIN animal_ins as i
    ON o.animal_id = i.animal_id
WHERE i.animal_id IS NULL
ORDER BY o.animal_id

-- 있었는데요 없었습니다
SELECT o.animal_id AS ANIMAL_ID,
       o.name AS NAME
FROM   animal_outs AS o
INNER JOIN animal_ins AS i
    ON o.animal_id = i.animal_id
WHERE o.datetime < i.datetime
ORDER BY i.datetime

-- 오랜 기간 보호한 동물(1)
SELECT i.name,
       i.datetime
FROM animal_ins as i
LEFT JOIN animal_outs as o
    ON i.animal_id = o.animal_id
WHERE o.animal_id IS NULL
ORDER BY i.datetime
LIMIT 3

-- 보호소에서 중성화한 동물
-- 풀이 1
SELECT o.animal_id   AS ANIMAL_ID, 
       o.animal_type AS ANIMAL_TYPE, 
       o.name        AS `NAME` 
FROM   animal_ins AS i 
       INNER JOIN animal_outs AS o 
               ON i.animal_id = o.animal_id 
WHERE  (i.sex_upon_intake NOT LIKE '%Spayed%' 
       AND i.sex_upon_intake NOT LIKE '%Neutered%')
       AND ( o.sex_upon_outcome LIKE '%Spayed%' 
              OR o.sex_upon_outcome LIKE '%Neutered%' ) 
ORDER  BY o.animal_id 

-- 풀이 2: 중성화를 다시 되돌리는 경우는 없으므로 값만 달라지는지 확인
SELECT   o.animal_id   AS ANIMAL_ID, 
         o.animal_type AS ANIMAL_TYPE, 
         o.name        AS `NAME` 
FROM     animal_ins AS i 
         INNER JOIN animal_outs AS o 
               ON i.animal_id = o.animal_id 
WHERE    i.sex_upon_intake != o.sex_upon_outcome 
ORDER BY o.animal_id 
