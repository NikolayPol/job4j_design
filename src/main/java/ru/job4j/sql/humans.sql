CREATE TABLE humans (
    id serial primary key,
    name varchar(255),
    age int,
    married text
);

INSERT INTO humans (name, age, married)
VALUES ('Ivan', 30, true);

SELECT * FROM humans;

UPDATE humans
SET age = 35
WHERE id = 2;

DELETE FROM humans;