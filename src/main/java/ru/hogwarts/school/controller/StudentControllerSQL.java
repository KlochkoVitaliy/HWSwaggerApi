package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.StudentQuerySQL;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
public class StudentControllerSQL {

    private final StudentService studentService;

    public StudentControllerSQL(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/get-name-and-age-students")
    public List<StudentQuerySQL> getNameAndAgeStudents(){
        return studentService.getNameAndAge();
    }

    @GetMapping("/get-count-students")
    public Integer getCountAllStudents(){
        return studentService.getCountStudents();
    }

    @GetMapping("/get-average-age-students")
    public Double getAverageAge(){
        return studentService.getAverageAge();
    }
}
