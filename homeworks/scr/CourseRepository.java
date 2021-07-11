import models.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> findAll();
    List<Course> findByName(String name);
    Course findById(Integer id);
    void save(Course course);
    void update(Course course);
    void delete(Integer id);
}
