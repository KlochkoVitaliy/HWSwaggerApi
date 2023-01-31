package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentQuerySQL;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository <Student, Long>{
    Collection<Student> findByAge(int age);
    Collection<Student> findStudentByAgeBetween(int min, int max);
    Collection<Student> findStudentByFaculty_Id (long faculty);

    @Query(value = "SELECT name, age from student ORDER BY ID DESC LIMIT 5", nativeQuery = true)
    List<StudentQuerySQL> getNameAndAgeStudents();

    @Query(value ="SELECT COUNT(*) from student",nativeQuery = true)
    Integer getCountAllStudents();

    @Query(value = "SELECT AVG(age) from student",nativeQuery = true)
    Double getAverageAge();
}
