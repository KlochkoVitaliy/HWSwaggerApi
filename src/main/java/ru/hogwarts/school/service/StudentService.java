package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {

    @Value("${avatars.dir.path}")
    private String avatarsDir;

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    public StudentService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
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
