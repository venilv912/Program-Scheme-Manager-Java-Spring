package com.example.programschemes.controller;

import com.example.programschemes.model.CourseType;
import com.example.programschemes.model.CourseTypeId;
import com.example.programschemes.repository.CourseTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/program/{programId}/coursetypes")
public class CourseTypeController {

    @Autowired
    private CourseTypeRepository courseTypeRepository;

    // 1. Show all course types for a program
    @GetMapping
    public String listCourseTypes(@PathVariable("programId") short programId, Model model) {
        List<CourseType> courseTypes = courseTypeRepository.findByProgramId(programId);
        model.addAttribute("programId", programId);
        model.addAttribute("courseTypes", courseTypes);
        return "course_types";
    }

    // 2. Show add form
    @GetMapping("/add")
    public String showAddForm(@PathVariable("programId") short programId, Model model) {
        CourseType courseType = new CourseType();
        courseType.setProgramId(programId);
        model.addAttribute("courseType", courseType);
        model.addAttribute("editing", false);
        return "course_type_form";
    }

    @GetMapping("/edit")
    public String showEditForm(@PathVariable("programId") short programId,
                               @RequestParam("courseTypeCode") String courseTypeCode,
                               Model model) {
        CourseTypeId id = new CourseTypeId(courseTypeCode, programId);
        CourseType existing = courseTypeRepository.findById(id).orElse(null);

        if (existing == null) {
            return "redirect:/program/" + programId + "/coursetypes";
        }

        model.addAttribute("courseType", existing);
        model.addAttribute("editing", true);
        return "course_type_form";
    }

    // 3. Save course type
    @PostMapping("/save")
    public String saveCourseType(@ModelAttribute("courseType") CourseType courseType,
                                 @RequestParam("editing") boolean editing,
                                 RedirectAttributes redirectAttributes) {

        CourseTypeId id = new CourseTypeId(courseType.getCourseTypeCode(), courseType.getProgramId());

        if (!editing && courseTypeRepository.existsById(id)) {
            redirectAttributes.addFlashAttribute("error", "Course Type with this code already exists for this program.");
            return "redirect:/program/" + courseType.getProgramId() + "/coursetypes/add";
        }

        courseTypeRepository.save(courseType);
        return "redirect:/program/" + courseType.getProgramId() + "/coursetypes";
    }

}
