package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudents(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudents(long id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudents(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudents(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findStudentByAgeBetween(min, max);
    }

    public Collection<Student> findByFaculty(long faculty) {
        return studentRepository.findStudentByFaculty_Id(faculty);
    }

    public Faculty findFacultyByStudent(Long id) {
        return studentRepository.findById(id).get().getFaculty();
    }
}
