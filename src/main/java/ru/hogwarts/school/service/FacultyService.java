package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;
import java.util.stream.Stream;

@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Method called to add faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.info("Method called to get faculty");
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Method called to edit faculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.info("Method called to delete faculty");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Method called to find faculty by color");
        return facultyRepository.findByColorIgnoreCase(color);
    }

    public Collection<Faculty> findByName(String name) {
        logger.info("Method called to find faculty by name");
        return facultyRepository.findByNameIgnoreCase(name);
    }

    public Collection<Student> findStudentsByFaculty(Long id) {
        logger.info("Method called to get students by faculty");
        return facultyRepository.findById(id).get().getStudents();
    }

    public Optional<String> getLongestNameFaculty() {
        return facultyRepository.findAll()
                .stream()
                .map(faculty -> faculty.getName())
                .reduce((f1, f2) -> f1.length() > f2.length() ? f1 : f2);
    }

    public Integer calculateSum() {
        return Stream
                .iterate(1, a -> a + 1)
                .limit(100_000_000)
                .reduce(0, (a, b) -> a + b);
    }

    public Integer fastCalculateSum() {
        return Stream
                .iterate(1, a -> a + 1)
                .limit(100_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b);
    }
}
