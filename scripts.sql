select * from student
where age > 5 AND age < 10;

select name from student;

select * from student
where name like '%о%';

select * from student
where age < id;

select * from student
order by age;

select s.name, s.age, f.color, f.name from faculty as f, student as s
where faculty_id = f.id
order by f.name