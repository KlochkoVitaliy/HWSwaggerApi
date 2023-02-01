SELECT s.name, s.age, f.name
FROM student AS s
         INNER JOIN faculty AS f ON s.faculty_id = f.id;

SELECT s.name, s.age
FROM student AS s
         INNER JOIN avatar ON s.id = avatar.student_id