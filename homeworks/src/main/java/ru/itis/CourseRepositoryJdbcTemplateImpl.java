package ru.itis;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.itis.models.Course;
import ru.itis.models.Student;


import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class CourseRepositoryJdbcTemplateImpl implements CourseRepository {

    //language=SQL
    private static final String SQL_SELECT_ALL_COURSE = "SELECT id,name,date,teacher_id,student_id from student_course sc inner join course on course.id = sc.course_id order by id";

    //language=SQL
    private static final String SQL_SELECT_ALL_BY_NAME_COURSE = "SELECT id,name,date,teacher_id,student_id from student_course sc inner join course on course.id = sc.course_id order by id where d.name = ?";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "SELECT id,name,date,teacher_id,student_id from student_course sc inner join course on course.id = sc.course_id order by id where d.id = ?";

    //language=SQL
    private static final String SQL_INSERT_COURSE = "insert into course(name, date, teacher_id) values (?, ?, ?)";

    //language=SQL
    private static final String SQL_INSERT_STUDENT_INTO_COURSE_STUDENT = "insert into student_course(course_id, student_id) values (?, ?)";

    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update course set name = ?, date = ?, teacher_id = ? where id = ?";

    //language=SQL
    private static final String SQL_DELETE_BY_COURSE_ID = "delete from course where id = ?";

    //language=SQL
    private static final String SQL_DELETE_STUDENTS_BY_COURSE_ID = "delete from student_course where course_id = ?";

    private final JdbcTemplate jdbcTemplate;

    public CourseRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final Function<ResultSet, Course> courseRawMapper = row -> {
        try {
            int id = row.getInt("id");
            String name = row.getString("name");
            String date = row.getString("date");
            int teacher_id = row.getInt("teacher_id");
            return new Course(id, name, date, teacher_id, new ArrayList<Student>());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    private final ResultSetExtractor<List<Course>> coursesWithStudentsResultSetExtractor = rows -> {
        List<Course> courses = new ArrayList<>();
        while (rows.next()) {
            Integer id = rows.getInt("course_id");
            String name = rows.getString("name");
            String date = rows.getString("date");
            Integer teacher_id = rows.getInt("teacher_id");
            Course course = new Course(id, name, date, teacher_id, new ArrayList<Student>());
            do {
                Integer student_id = rows.getInt("student_id");
                String first_name = rows.getString("first_name");
                String last_name = rows.getString("last_name");
                Integer group_id = rows.getInt("group_id");
                Student student = new Student(student_id, first_name, last_name, group_id, null);
                course.getStudent_Id().add(student);
            } while (rows.next() && id == rows.getInt("course_id"));
            courses.add(course);
        }
        return courses;
    };

    @Override
    public List<Course> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL_COURSE, coursesWithStudentsResultSetExtractor);
    }

    @Override
    public List<Course> findByName(String name) {
        return jdbcTemplate.query(SQL_SELECT_ALL_BY_NAME_COURSE, coursesWithStudentsResultSetExtractor, name);
    }

    @Override
    public Optional<Course> findById(Integer id) {
        List<Course> courses = jdbcTemplate.query(SQL_SELECT_BY_ID, coursesWithStudentsResultSetExtractor, id);
        if (courses.size() == 1) {
            return Optional.of(courses.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void save(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_COURSE, new String[]{"id"});
            statement.setString(1, course.getName());
            statement.setString(2, course.getDate());
            statement.setInt(3, course.getTeacher_Id());
            return statement;
        }, keyHolder);
        course.setId(keyHolder.getKey().intValue());
        for (Student student : course.getStudent_Id()) {
            jdbcTemplate.update(SQL_INSERT_STUDENT_INTO_COURSE_STUDENT, course.getId(), student.getId());
        }
    }

    @Override
    public void update(Course course) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, course.getName(), course.getDate(), course.getTeacher_Id(), course.getId());
        jdbcTemplate.update(SQL_DELETE_STUDENTS_BY_COURSE_ID, course.getId());
        for (Student student : course.getStudent_Id()) {
            jdbcTemplate.update(SQL_INSERT_STUDENT_INTO_COURSE_STUDENT, course.getId(), student.getId());
        }
    }

    @Override
    public void delete(Course course) {
        jdbcTemplate.update(SQL_DELETE_STUDENTS_BY_COURSE_ID, course.getId());
        jdbcTemplate.update(SQL_DELETE_BY_COURSE_ID, course.getId());
    }
}