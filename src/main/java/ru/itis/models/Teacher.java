package ru.itis.models;

import java.util.Set;

public class Teacher {
    private Integer id;
    private String first_name;
    private String last_name;
    private Integer work_experience;
    private Set<Integer> courses_id;

    public Teacher(Integer id, String first_name, String last_name, Integer work_experience, Set<Integer> courses_id) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.work_experience = work_experience;
        this.courses_id = courses_id;
    }

    public Teacher(String first_name, String last_name, Integer work_experience, Set<Integer> courses_id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.work_experience = work_experience;
        this.courses_id = courses_id;
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

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public Integer getWorkExperience() {
        return work_experience;
    }

    public void setWorkExperience(Integer experience) {
        this.work_experience = work_experience;
    }

    public Set<Integer> getCourses_id() {
        return courses_id;
    }

    public void setCourses_id(Set<Integer> courses_id) {
        this.courses_id = courses_id;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id=" + id +
                ", firstName='" + first_name + '\'' +
                ", lastName='" + last_name + '\'' +
                ", experience=" + work_experience +
                ", idCourses=" + courses_id +
                '}';
    }
}