package ru.itis.models;

public class Lesson {
    private Integer id;
    private String name;
    private String dateLesson;
    private Integer course_id;

    public Lesson(Integer id, String name, String dateLesson, Integer course_id) {
        this.id = id;
        this.name = name;
        this.dateLesson = dateLesson;
        this.course_id = course_id;
    }

    public Lesson(String name, String dateLesson, Integer course_id) {
        this.name = name;
        this.dateLesson = dateLesson;
        this.course_id = course_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateLesson() {
        return dateLesson;
    }

    public void dateLesson(String dateLesson) {
        this.dateLesson = dateLesson;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timeOfLesson='" + dateLesson + '\'' +
                ", idCourse=" + course_id +
                '}';
    }
}
