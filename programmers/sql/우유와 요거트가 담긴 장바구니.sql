-- 풀이 1: GROUP_CONCAT
SELECT A.CART_ID
FROM (SELECT CART_ID, GROUP_CONCAT(`NAME` SEPARATOR ',') AS NAME 
        FROM CART_PRODUCTS
        GROUP BY CART_ID ) AS A
WHERE A.NAME LIKE "%우유%" AND A.NAME LIKE "%요거트%"
ORDER BY A.CART_ID ASC

-- 풀이 2: JOIN
SELECT A.cart_id
FROM
    (SELECT cart_id FROM cart_products WHERE name = '우유') AS A
    JOIN
    (SELECT cart_id FROM cart_products WHERE name = '요거트') AS B
        ON (A.cart_id = B.cart_id)
ORDER BY A.cart_id

-- 풀이 3: DISTINCT
SELECT a.cart_id
FROM (
    SELECT
        cart_id, count(distinct `name`) AS cnt
    from
        CART_PRODUCTS
    where
        name in ('요거트','우유')
    group by cart_id
) AS a
where cnt = 2
group by a.cart_id
order by a.cart_id ASC


-- 풀이 4: INTERSECT
-- INTERSECT MySQL도 되는 것 같은데 여기선 Oracle만 된다.
SELECT CART_ID
  FROM CART_PRODUCTS
 WHERE NAME = '우유' 
INTERSECT
SELECT CART_ID
  FROM CART_PRODUCTS
 WHERE NAME = '요거트'
 ORDER BY CART_ID