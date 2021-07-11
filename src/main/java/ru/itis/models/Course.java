package ru.itis.models;

import java.util.ArrayList;

public class Course {
    private Integer id;
    private String name;
    private String date;
    private Integer teacher_id;
    private ArrayList<Student> student_id;

    public Course(Integer id, String name, String date, Integer teacher_id, ArrayList<Student> student_id) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.teacher_id = teacher_id;
        this.student_id = student_id;
    }

    public Course(String name, String date, Integer teacher_id, ArrayList<Student> student_id) {
        this.name = name;
        this.date = date;
        this.teacher_id = teacher_id;
        this.student_id = student_id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String startEndDate) {
        this.date = startEndDate;
    }

    public Integer getTeacher_Id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public ArrayList<Student> getStudent_Id() {
        return student_id;
    }

    public void setStudent_Id(ArrayList<Student> student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startEndDate='" + date + '\'' +
                ", teacherId=" + teacher_id +
                ", idStudent=" + student_id +
                '}';
    }
}