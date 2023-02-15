package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable long id) {
        Student student = studentService.findStudents(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping("{id}/faculty")
    public ResponseEntity<Faculty> getFacultyByStudentInfo(@PathVariable Long id) {
        Faculty faculty = studentService.findFacultyByStudent(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudents(student);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudents(student);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudents(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int age,
                                                            @RequestParam(required = false) int min,
                                                            @RequestParam(required = false) int max,
                                                            @RequestParam(required = false) Long faculty) {
        if (age != 0) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        if (min != 0 && max != 0) {
            return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
        }
        if (faculty != 0) {
            return ResponseEntity.ok(studentService.findByFaculty(faculty));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
    @GetMapping("/get-all-students-stream")
    public List<String> getAllStudentsWithFirstCharacter() {
        return studentService.getAllStudents();
    }

    @GetMapping("/get-average-age-students-stream")
    public Double calculateAverageAgeStudents() {
        return studentService.getAverageAge();
    }

}
