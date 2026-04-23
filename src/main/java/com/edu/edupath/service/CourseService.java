package com.edu.edupath.service;

import com.edu.edupath.model.Course;
import com.edu.edupath.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public List<Course> filter(String level, double maxPrice) {
        return repo.findAll().stream()
                .filter(c -> level.equals("ALL") || c.getLevel().equalsIgnoreCase(level))
                .filter(c -> c.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public Course getByCode(String code) {
        return repo.findByCode(code);
    }

    public void save(Course c) {
        repo.save(c);
    }

    public boolean delete(String code) {
        Course c = repo.findByCode(code);
        if (c.getStudentCount() > 0) return false;

        repo.delete(code);
        return true;
    }
}