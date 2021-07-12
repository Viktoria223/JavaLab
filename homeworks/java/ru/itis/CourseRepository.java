package ru.itis;

import ru.itis.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> findAll();
    List<Course> findByName(String name);
    Optional<Course> findById(Integer id);
    void save(Course course);
    void update(Course course);
    void delete(Course course);
}
