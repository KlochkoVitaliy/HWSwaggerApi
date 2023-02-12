package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

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
}
