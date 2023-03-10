package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("{id}/students")
    public ResponseEntity<Collection<Student>> getStudentsByFacultyInfo(@RequestParam Long id) {
        Collection<Student> students = facultyService.findStudentsByFaculty(id);
        if (students == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping("{id}")
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty, @PathVariable Long id) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> findFaculties(@RequestParam(required = false) String color,
                                                             @RequestParam(required = false) String name) {
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColor(name));
        }
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColor(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }
    @GetMapping("/get-longest-faculty-name")
    public Optional<String> getLongestNameFaculty() {
        return facultyService.getLongestNameFaculty();
    }

    @GetMapping("/calculate-sum-lesson-4.5")
    public Integer calculateSum() {
        return facultyService.calculateSum();
    }

    @GetMapping("/calculate-sum-lesson-4.5-faster")
    public Integer fastCalculateSum() {
        return facultyService.fastCalculateSum();
    }
}
