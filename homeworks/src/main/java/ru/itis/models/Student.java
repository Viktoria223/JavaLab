package ru.itis.models;

import java.util.Set;

public class Student {
    private Integer id;
    private String first_name;
    private String last_name;
    private Integer group_number;
    private Set<Integer> idCourses;

    public Student(Integer id, String first_name, String last_name, Integer group_number, Set<Integer> idCourses) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.group_number = group_number;
        this.idCourses = idCourses;
    }

    public Student(String first_name, String last_name, Integer group_number, Set<Integer> idCourses) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.group_number = group_number;
        this.idCourses = idCourses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public Integer getGroupNumber() {
        return group_number;
    }

    public void setGroupNumber(Integer numGroup) {
        this.group_number = group_number;
    }

    public Set<Integer> getIdCourses() {
        return idCourses;
    }

    public void setIdCourses(Set<Integer> idCourses) {
        this.idCourses = idCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + first_name + '\'' +
                ", lastName='" + last_name + '\'' +
                ", numGroup='" + group_number + '\'' +
                ", idCourses=" + idCourses +
                '}';
    }
}