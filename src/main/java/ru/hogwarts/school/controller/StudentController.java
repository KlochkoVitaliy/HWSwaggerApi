package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController (StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo (@PathVariable long id){
        Student student  = studentService.findStudents(id);
        if(student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent (@RequestBody Student student){
        return studentService.addStudents(student);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> editStudent(@RequestBody Student student, @PathVariable Long id){
        Student foundStudent = studentService.editStudents( student);
        if(foundStudent == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping
    public ResponseEntity deleteStudent (@PathVariable Long id){
        studentService.deleteStudents(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int age) {
        if (age > 0) {
            return ResponseEntity.ok(studentService.studentAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
}
