package com.edu.edupath.repository;

import com.edu.edupath.model.Course;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class CourseRepository {

    private final Map<String, Course> courses = new HashMap<>();

    public CourseRepository() {
        Course c1 = new Course();
        c1.setCode("IELTS-6.5");
        c1.setName("IELTS 6.5");
        c1.setLevel("Intermediate");
        c1.setPrice(300);
        c1.setDescription("Lộ trình học IELTS từ 5.0 → 6.5");
        c1.setInstructor("Mr. John");
        c1.setDuration(3);
        c1.setStudentCount(0);
        c1.setMaxStudent(20);
        c1.setStartDate(LocalDate.now().plusDays(7));

        Course c2 = new Course();
        c2.setCode("IELTS-7.5");
        c2.setName("IELTS 7.5+");
        c2.setLevel("Advanced");
        c2.setPrice(500);
        c2.setDescription("Nâng band 6.5 → 7.5+");
        c2.setInstructor("Ms. Anna");
        c2.setDuration(4);
        c2.setStudentCount(5);
        c2.setMaxStudent(20);
        c2.setStartDate(LocalDate.now().plusDays(10));

        Course c3 = new Course();
        c3.setCode("BASIC-ENG");
        c3.setName("English Basic");
        c3.setLevel("Beginner");
        c3.setPrice(150);
        c3.setDescription("Khóa học nền tảng tiếng Anh");
        c3.setInstructor("Mr. David");
        c3.setDuration(2);
        c3.setStudentCount(0);
        c3.setMaxStudent(15);
        c3.setStartDate(LocalDate.now().plusDays(5));

        courses.put(c1.getCode(), c1);
        courses.put(c2.getCode(), c2);
        courses.put(c3.getCode(), c3);
    }

    public Collection<Course> findAll() {
        return courses.values();
    }

    public Course findByCode(String code) {
        return courses.get(code);
    }

    public void save(Course c) {
        courses.put(c.getCode(), c);
    }

    public void delete(String code) {
        courses.remove(code);
    }
}