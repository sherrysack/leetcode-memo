```mysql
#create a database
CREATE DATABASE patries;
USE patries;
CREATE TABLE patries(name VARCHAR(50), quantity INT);

#show the content of tables
SHOW TABLES;
SHOW COLUMNS FROM tablename;
DESC tablename;

#dropping tables
DROP TABLE <tablename>;

#insert data
INSERT INTO patries(name, quantity) VALUES('Jetson', 7);
INSERT INTO patries(name, quantity) VALUES('Jetson', 7), ('Sadie',3);

#not null and null
CREATE TABLE employees (
	id INT NOT NULL AUTO_INCREMENT,
  	last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    age INT NOT NULL,
    current_status VARCHAR(100) NOT NULL DEFAULT 'employed',
    primary KEY(id)
);

#Select
SELECT name FROM cats;
#update
UPDATE cats SET breed = 'Shorthair' WHERE breed = 'Tabby';
UPDATE cats SET age=14 WHERE name='Misty';
#delete
DELETE FROM cats WHERE name = 'Egg';
#delete all the entries of cats, different from DROP TABLE cats;
DELETE FROM cats;

DELETE FROM cats WHERE cat_id = age;

#running SQL files
source file_name.sql

```

##String Functions##

```mysql
# concat
SELECT author_fname as first, author_lname as last, 
CONCAT(author_fname, author_lname) as full FROM books;

# concat with separator between columns
SELECT
	CONCAT_WS('-', title, author_fname, author_lname)
	FROM books;

#select 
SELECT SUBSTRING('Hello World', 7);
SELECT SUBSTRING('Hello World', -3);
#print rld
SELECT SUBSTRING(title,  1, 10) as short_title FROM books;
SELECT CONCAT(SUBSTRING(title,  1, 10), '...') as shortTitle FROM books;


#Replace
SELECT REPLACE('Hello World', 'Hell', '@#$%');
SELECT REPLACE(title, 'e', 3) FROM books;

#Reverse
SELECT REVERSE('Hello World');

#CHAR_LENGTH
SELECT CHAR_LENGTH('Hello World');

#UPPer
SELECT LOWER('Hello World');

#distinct
SELECT author_lname from books;
SELECT DISTINCT author_lname from books;
SELECT DISTINCT(CONCAT(author_fname, ' ', author_lname)) AS full_name FROM books;

#order
SELECT released_year FROM books ORDER BY released_year DESC;
 
SELECT released_year FROM books ORDER BY released_year ASC;
 
SELECT title, released_year, pages FROM books ORDER BY released_year;
 
SELECT title, pages FROM books ORDER BY released_year;
 
SELECT title, author_fname, author_lname 
FROM books ORDER BY 2;
 
SELECT title, author_fname, author_lname 
FROM books ORDER BY 3;


#limit


#LIKE: better searching
WHERE author_fname LIKE '%da%'

SELECT title FROM books WHERE title LIKE "%stories%";
SELECT title, author_lname FROM books GROUP BY author_lname;

SELECT author_lname, COUNT(*) FROM books GROUP BY author_lname;
+----------------+----------+
| author_lname   | COUNT(*) |
+----------------+----------+
| Carver         |        2 |
| Chabon         |        1 |
| DeLillo        |        1 |
| Eggers         |        3 |
| Foster Wallace |        2 |
| Gaiman         |        3 |
| Lahiri         |        2 |
| Smith          |        1 |
| Steinbeck      |        1 |
+----------------+----------+
9 rows in set (0.00 sec)

#would not work as you guess, would return the 1st book's title
SELECT MAX(pages), title FROM books;
#potential solution
SELECT title FROM books WHERE pages = (SELECT Min(pages) FROM books);
#alternative solution
SELECT title, pages FROM books ORDER BY pages ASC LIMIT 1;


#find the year each author published their 1st book
SELECT author_fname, author_lname, MIN(release_year) 
FROM books
GROUP BY author_lname,
		author_fname;
		
		
#SUM
#sum all pages in the entire database

SELECT COUNT(*) FROM books;
SELECT released_year, COUNT(*) FROM books GROUP BY released_year;
SELECT SUM(stock_quantity) FROM books;
#find the average released_year for each author
SELECT AVG(released_year), CONCAT(author_fname, ' ', author_lname) FROM books GROUP BY author_fname, author_lname;

#find the full name of the author who wrote the longest book
SELECT CONCAT(author_fname, ' ', author_lname) FROM books WHERE pages = (SELECT MAX(pages) FROM books);

SELECT released_year, COUNT(*) AS '# of books', AVG(pages) AS 'avg pages'
FROM books GROUP BY released_year ASC;
```

## More About Data Types##

The length of a CHAR column is fixed to the length that you declare when you create the table. THe length can be any value from 0 to 255. When CHAR values are stored, they are right-padded with spaces to the specified length. WHen CHAR values are retrieved, trailing spaces are removed unless the PAD_CHAR_TO_FULL_LENGHT SQL mode is enabled.

CHAR is faster for fixed length text.

## Logical Operators##

```mysql

##between
SELECT title, released_year FROM books WHERE released_year BETWEEN 2004 AND 2015;
##not between
SELECT title, released_year FROM books WHERE released_year NOT BETWEEN 2004 AND 2015;



#cast
SELECT CAST('2017-05-02' AS DATETIME);

SELECT name, birthdt FROM people
WHERE birthdt BETWEEN '1924-01-01' AND '20001-01-01'
#the upper solution is right, but not regulated

SELECT name, birthdt FROM people
WHERE birthdt BETWEEN CAST('1924-01-01' AS DATETIME) AND CAST('20001-01-01' AS DATETIME);


#IN
SELECT title, author_lname FORM books WHERE author_lname IN('Carver', 'Lahiri', 'Smith');

#case statement
SELECT tilte, stock_quantity,
	CASE
		WHEN stock_quantity <= 50 THEN '*',
		WHEN stock_quantity <= 100 THEN '*',
	END AS STOCK
FROM books;



```

## ONE to MANY##

```Mysql
#
SELECT first_name, last_name, order_date, amount
FROM customers
JOIN orders
ON customers.id = orders.customer_id
GROUP BY orders.customer_id;
#the alternative way
SELECT * FROM customers
INNER JOIN orders
ON customers.id = orders.customer_id;


###LEFT JOIN: take everything contained in customers
SELECT * FROM customers
LEFT JOIN orders
ON customers.id = orders.customer_id
(GROUP BY customers.id;)
###change sum(amount) to 0
SELECT first_name,
	   last_name,
	   IFNULL(SUM(amount), 0) AS total_spent
FROM customers
LEFT JOIN orders
ON customers.id = orders.customer_id
(GROUP BY customers.id)
ORDER BY total_spent;

###RIGHT JOIN: 
SELECT * FROM customers
RIGHT JOIN orders
ON customers.id = orders.customer_id;



```

## 多表查询

根据特定的连接条件从不同的表中获取所需的数据

笛卡尔集的产生条件：

1. 省略

```sql
DROP TABLE IF EXISTS cast_movie_junc;
CREATE TABLE cast_movie_junc AS
SELECT cc.cast_id, cc.movie_id, movies.score
FROM
(SELECT
*
FROM cast as c
GROUP BY cast_id
HAVING COUNT(c.movie_id) >= 3) G
INNER JOIN cast cc on cc.cast_id == G.cast_id
INNER JOIN movies on movies.id == cc.movie_id;

SELECT @Part1 = first.cast_id, @Part2 = second.cast_id
FROM
    (SELECT
        cast_id
    FROM cast as c
    GROUP BY cast_id
    HAVING COUNT(c.movie_id) >= 3
    ) first
CROSS JOIN
    (
    SELECT
        cast_id
    FROM cast as c
    GROUP BY cast_id
    HAVING COUNT(c.movie_id) >= 3
    ) second
WHERE first.cast_id < second.cast_id;


SELECT *
FROM movies
INNER JOIN cast c
ON movies.id == c.movie_id
WHERE c.cast_id in (Part1, Part2)
GROUP BY movies.id
HAVING COUNT(movies.id) > 1;

```

