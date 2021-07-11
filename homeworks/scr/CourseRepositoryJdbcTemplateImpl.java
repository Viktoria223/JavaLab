import models.Course;

import java.util.*;
import java.util.function.Function;
import java.sql.*;
import javax.sql.DataSource;

public class CourseRepositoryJdbcTemplateImpl implements CourseRepository {

    private final DataSource dataSource;

    public CourseRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //language=SQL
    private static final String SQL_SELECT_ALL_COURSE = "SELECT id,name,date,teacher_id,student_id from student_course sc inner join course on course.id = sc.course_id order by id";

    //language=SQL
    private static final String SQL_SELECT_COURSE_BY_ID = "SELECT id,name,date,teacher_id,student_id from student_course sc inner join course on course.id = sc.course_id where course_id = ?";

    //language=SQL
    private static final String SQL_SELECT_COURSE_BY_NAME = "Select * from Course inner join student_course sc on Course.id = sc.course_id where name = ? order by id";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select id from course where id in(select last_value from course_id_seq)";

    //language=SQL
    private static final String SQL_DELETE_LIST_STUDENTS_FROM_COURSE_BY_ID = "Delete from student_course where course_id = ?";

    //language=SQL
    private static final String SQL_DELETE_COURSE_BY_ID = "Delete from course where id = ?";

    //language=SQL
    private static final String SQL_INSERT_COURSE = "insert into course (name, date, teacher_id) values (?, ?, ?)";

    //language=SQL
    private static final String SQL_INSERT_STUDENTS_FROM_COURSE = "insert into student_course (student_id, course_id) values (?, ?)";

    //language=SQL
    private static final String SQL_UPDATE_COURSE_BY_ID = "Update course set name = ? ,date=?,teacher_id = ? where id = ?";

    Function<ResultSet, Course> function = row -> {
        try {
            Integer id = row.getInt("id");
            String name = row.getString("name");
            String date = row.getString("date");
            Integer teacher_id = row.getInt("teacher_id");
            Set<Integer> student_id = new HashSet<>();
            student_id.add(row.getInt("student_id"));
            while (row.next()) { student_id.add(row.getInt("student_id")); }
            return new Course(id, name, date, teacher_id, student_id);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    };

    @Override
    public List<Course> findAll() {
        List<Course> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet row = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_ALL_COURSE, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            row = statement.executeQuery();
            while (row.next()) {
                Integer id = row.getInt("id");
                String name = row.getString("name");
                String date = row.getString("date");
                Integer teacher_id = row.getInt("teacher_id");
                Set<Integer> student_id = new HashSet<>();
                do {
                    student_id.add(row.getInt("student_id"));
                } while (row.next() && id == row.getInt("id"));
                Course course = new Course(id, name, date, teacher_id, student_id);
                list.add(course);
                row.previous();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (row != null) {
                try {
                    row.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
        return list;
    }

    @Override
    public List<Course> findByName(String name) {
        List<Course> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet row = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_COURSE_BY_NAME, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setString(1, name);
            row = statement.executeQuery();
            while (row.next()) {
                Integer id = row.getInt("id");
                String date = row.getString("date");
                Integer teacher_id = row.getInt("teacher_id");
                Set<Integer> student_id = new HashSet<>();
                do {
                    student_id.add(row.getInt("student_id"));
                } while (row.next() && id == row.getInt("id"));
                Course course = new Course(id, name, date, teacher_id, student_id);
                list.add(course);
                row.previous();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (row != null) {
                try {
                    row.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
        return list;
    }

    @Override
    public Course findById(Integer id) {
        Course course = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet row = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_SELECT_COURSE_BY_ID);
            statement.setInt(1, id);
            row = statement.executeQuery();
            row.next();
            course = function.apply(row);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (row != null) {
                try {
                    row.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
        return course;
    }

    @Override
    public void save(Course course) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet row = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SQL_INSERT_COURSE);
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDate());
            preparedStatement.setInt(3, course.getTeacher_Id());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            row = preparedStatement.executeQuery();
            row.next();
            Integer idCourseBySeq = row.getInt("id");
            preparedStatement = connection.prepareStatement(SQL_INSERT_STUDENTS_FROM_COURSE);
            preparedStatement.setInt(2, idCourseBySeq);
            for (Integer student_id : course.getStudent_Id()) {
                preparedStatement.setInt(1, student_id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (row != null) {
                try {
                    row.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
    }

    @Override
    public void update(Course course) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE_COURSE_BY_ID);
            statement.setString(1, course.getName());
            statement.setString(2, course.getDate());
            statement.setInt(3, course.getTeacher_Id());
            statement.setInt(4, course.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }

    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_LIST_STUDENTS_FROM_COURSE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement = connection.prepareStatement(SQL_DELETE_COURSE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
    }
}