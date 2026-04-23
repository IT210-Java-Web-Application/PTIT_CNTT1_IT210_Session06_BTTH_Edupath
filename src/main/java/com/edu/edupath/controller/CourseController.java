package com.edu.edupath.controller;

import com.edu.edupath.model.Course;
import com.edu.edupath.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping("")
    public String home() {
        return "home/home-page";
    }

    @GetMapping("/list")
    public String list(
            @RequestParam(name = "level", defaultValue = "ALL") String level,
            @RequestParam(name = "price", defaultValue = "1000") double price,
            Model model) {

        List<Course> courses = service.filter(level, price);
        model.addAttribute("courses", courses);
        return "course/list-page";
    }

    @GetMapping("/detail/{code}")
    public String detail(@PathVariable("code") String code, Model model) {
        model.addAttribute("course", service.getByCode(code));
        return "course/detail-page";
    }

    @GetMapping("/edit/{code}")
    public String edit(@PathVariable("code") String code, Model model) {
        model.addAttribute("course", service.getByCode(code));
        return "course/edit-page";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("course") Course form) {
        Course old = service.getByCode(form.getCode());

        old.setPrice(form.getPrice());
        old.setStartDate(form.getStartDate());

        service.save(old);
        return "redirect:/course/list";
    }

    @PostMapping("/delete/{code}")
    public String delete(@PathVariable("code") String code, Model model) {
        if (!service.delete(code)) {
            model.addAttribute("error", "Không thể hủy khóa học đã có học viên đăng ký!");
            return list("ALL", 1000, model);
        }
        return "redirect:/course/list";
    }
}