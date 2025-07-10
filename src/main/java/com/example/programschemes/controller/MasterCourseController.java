package com.example.programschemes.controller;

import com.example.programschemes.model.MasterCourse;
import com.example.programschemes.repository.MasterCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mastercourses")
public class MasterCourseController {

    @Autowired
    private MasterCourseRepository masterCourseRepository;

    // 1. Show all master courses
    @GetMapping
    public String listMasterCourses(Model model) {
        model.addAttribute("masterCourses", masterCourseRepository.findAll());
        return "master_courses";
    }

    // 2. Show add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("masterCourse", new MasterCourse());
        model.addAttribute("editing", false);
        return "master_course_form";
    }

    // 2.1. Show edit form
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("courseId") int courseId, Model model) {
        MasterCourse course = masterCourseRepository.findById(courseId).orElse(null);
        if (course == null) {
            return "redirect:/mastercourses";
        }
        model.addAttribute("masterCourse", course);
        model.addAttribute("editing", true);
        return "master_course_form";
    }

    // 3. Save master course
    @PostMapping("/save")
    public String saveMasterCourse(@ModelAttribute("masterCourse") MasterCourse masterCourse,
                                   @RequestParam("editing") boolean editing,
                                   RedirectAttributes redirectAttributes) {

        if (!editing && masterCourseRepository.existsById(masterCourse.getCourseId())) {
            // Duplicate course ID only matters during "Add"
            redirectAttributes.addFlashAttribute("error", "Course ID already exists. Please choose a different ID.");
            return "redirect:/mastercourses/add";
        }

        masterCourseRepository.save(masterCourse);
        return "redirect:/mastercourses";
    }
}
