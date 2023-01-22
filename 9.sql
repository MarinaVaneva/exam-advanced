USE stc;
#2
INSERT INTO clients (full_name, phone_number)
SELECT (concat(d.first_name," ",d.last_name)), (concat('(088) 9999',d.id*2)) FROM drivers AS d
WHERE d.`id` between 10 AND 20;

#3

UPDATE cars AS c
SET c.condition = 'C'
WHERE c.mileage >= 800000 OR c.mileage IS null AND c.year <= 2010 AND c.make <> 'Mercedes-Benz';

#4
DELETE c
FROM clients AS c 
LEFT JOIN courses AS co
ON c.`id` =  co.`client_id`
WHERE char_length(c.full_name) > 3 AND co.`client_id` IS NULL ;

#5
SELECT c.make, c.model, condition FROM cars AS c
order by c.id;

#6
SELECT d.first_name, d.last_name, c.make, c.model, c.mileage FROM drivers AS d
LEFT JOIN cars_drivers AS cd
ON d.id = cd.driver_id
LEFT JOIN cars AS c
ON c.id = cd.car_id
WHERE mileage IS NOT NULL
ORDER BY mileage DESC, first_name;

#7
SELECT c.id AS car_id, c.make, c.mileage, count(co.id) AS count_of_courses, ROUND(avg(co.bill), 2) AS avg_bill FROM cars AS c
LEFT JOIN courses AS co
ON co.car_id = c.id
group by c.id
having count_of_courses <> 2
ORDER BY count_of_courses DESC, c.id ;

#8

SELECT c.full_name, count(co.client_id) AS count_of_cars, sum(co.bill) AS total_sum FROM clients AS c
LEFT JOIN courses AS co
ON c.id = co.client_id
WHERE c.full_name LIKE '_a%'
group by full_name
having count_of_cars > 1
ORDER BY full_name;

#9 
SELECT a.name, IF(hour(cs.start) BETWEEN 6 AND 20, 'Day', 'Night') AS day_time, cs.bill, cl.full_name, c.make, c.model, cat.name AS category_name
FROM addresses AS a
JOIN courses AS cs ON cs.from_address_id = a.id
JOIN clients AS cl ON cl.id = cs.client_id
JOIN cars AS c ON c.id = cs.car_id
JOIN categories AS cat ON cat.id = c.category_id
ORDER BY cs.id;
#10
CREATE FUNCTION udf_actor_history_movies_count(full_name VARCHAR(50))
    RETURNS INT
    DETERMINISTIC
BEGIN
    DECLARE movies_count INT;
    SET movies_count := (
        SELECT COUNT(g.name) movies
        FROM actors a
                 JOIN movies_actors ma on a.id = ma.actor_id
                 JOIN genres_movies gm on ma.movie_id = gm.movie_id
                 JOIN genres g on g.id = gm.genre_id
        WHERE CONCAT(a.first_name, ' ', a.last_name) = full_name AND g.name = 'History'
        GROUP BY  g.name);
    RETURN movies_count;
END
 
 
CREATE PROCEDURE `udp_award_movie`(`movie_title` VARCHAR(50))
BEGIN
    UPDATE actors a
        JOIN movies_actors ma on a.id = ma.actor_id
        JOIN movies m on m.id = ma.movie_id
    SET  a.awards = a.awards + 1
    WHERE m.title = movie_title;
END

