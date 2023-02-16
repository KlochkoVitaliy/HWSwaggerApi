package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.model.StudentQuerySQL;
import ru.hogwarts.school.repository.AvatarRepository;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Value("${avatars.dir.path}")
    private String avatarsDir;

    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    public StudentService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }
    public Student addStudents(Student student) {
        logger.info("Method called to add student");
        return studentRepository.save(student);
    }

    public Student findStudents(long id) {
        logger.info("Method called to get student");
        return studentRepository.findById(id).get();
    }

    public Student editStudents(Student student) {
        logger.info("Method called to edit student");
        return studentRepository.save(student);
    }

    public void deleteStudents(long id) {
        logger.info("Method called to delete student");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findByAge(int age) {
        logger.info("Method called to get students by age");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Method called to get students between age");
        return studentRepository.findStudentByAgeBetween(min, max);
    }

    public Collection<Student> findByFaculty(long faculty) {
        logger.info("Method called to get students by faculty");
        return studentRepository.findStudentByFaculty_Id(faculty);
    }

    public Faculty findFacultyByStudent(Long id) {
        logger.info("Method called to get faculty by student");
        return studentRepository.findById(id).get().getFaculty();
    }

    public List<StudentQuerySQL> getNameAndAge() {
        logger.info("Method called to get last five students");
        return studentRepository.getNameAndAgeStudents();
    }

    public Integer getCountStudents() {
        logger.info("Method called to get count students");
        return studentRepository.getCountAllStudents();
    }

    public Double getAverageAge() {
        logger.info("Method called to get average age students");
        return studentRepository.findAll()
                .stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(0);
    }
    public List<String> getAllStudents() {
        logger.info("Method called to get all students");
        return studentRepository.findAll()
                .stream()
                .filter(student -> student.getName().startsWith("А"))
                .map(student -> student.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }
    public void printNameStudentsThread() {

        List<String> students = getAllStudents();
        System.out.println(students.get(0));
        System.out.println(students.get(1));

        new Thread(() -> {
            System.out.println(students.get(2));
            System.out.println(students.get(3));
        }).start();

        new Thread(() -> {
            System.out.println(students.get(4));
            System.out.println(students.get(5));
        }).start();
    }

    public void printNameStudentsSynchronizedThread() {

        List<String> students = getAllStudents();
        printNameStudent(students.get(0));
        printNameStudent(students.get(1));

        new Thread(() -> {
            printNameStudent(students.get(2));
            printNameStudent(students.get(3));
        }).start();

        new Thread(() -> {
            printNameStudent(students.get(4));
            printNameStudent(students.get(5));
        }).start();
    }

    public synchronized void printNameStudent(String name) {
        System.out.println(name);
    }

}
