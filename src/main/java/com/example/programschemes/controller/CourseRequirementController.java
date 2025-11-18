package com.example.programschemes.controller;

import com.example.programschemes.model.CourseRequirement;
import com.example.programschemes.repository.CourseRequirementRepository;
import com.example.programschemes.repository.CourseTypeRepository;
import com.example.programschemes.model.CourseType;
import com.example.programschemes.model.CourseRequirementId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/program/{programId}/scheme/{schemeId}/courserequirements")
public class CourseRequirementController {

    @Autowired
    private CourseRequirementRepository courseRequirementRepository;

    @Autowired
    private CourseTypeRepository courseTypeRepository;

    // Show all course requirements for a scheme
    @GetMapping
    public String listRequirements(@PathVariable int programId,
                                   @PathVariable int schemeId,
                                   Model model) {
        List<CourseRequirement> requirements = courseRequirementRepository.findBySchemeId(schemeId);
        model.addAttribute("requirements", requirements);
        model.addAttribute("programId", programId);
        model.addAttribute("schemeId", schemeId);
        return "course_requirements"; // <- Your Thymeleaf view
    }

    // Show form to add a new requirement
    @GetMapping("/add")
    public String showAddForm(@PathVariable int programId,
                              @PathVariable int schemeId,
                              Model model) {

        CourseRequirement cr = new CourseRequirement();
        cr.setSchemeId(schemeId);
        cr.setProgramId((short) programId);

        // Step 1: All course types for this program
        List<CourseType> allCourseTypes = courseTypeRepository.findByProgramId((short) programId);

        // Step 2: Already used course type codes in requirements
        List<String> usedCodes = courseRequirementRepository
                .findByProgramIdAndSchemeId((short) programId, schemeId)
                .stream()
                .map(CourseRequirement::getCourseTypeCode)
                .collect(Collectors.toList());

        // Step 3: Filter out used course types
        List<CourseType> availableTypes = allCourseTypes.stream()
                .filter(type -> !usedCodes.contains(type.getCourseTypeCode()))
                .collect(Collectors.toList());

        model.addAttribute("requirement", cr);
        model.addAttribute("courseTypes", availableTypes);
        model.addAttribute("programId", programId);
        model.addAttribute("schemeId", schemeId);
        model.addAttribute("editing", false);

        return "course_requirement_form";
    }


    // Save the requirement (from form)
    @PostMapping("/save")
    public String saveRequirement(@ModelAttribute("requirement") CourseRequirement cr) {
        courseRequirementRepository.save(cr);
        return "redirect:/program/" + cr.getProgramId() + "/scheme/" + cr.getSchemeId() + "/courserequirements";
    }

    // Edit existing requirement
    @GetMapping("/edit")
    public String showEditForm(@PathVariable int programId,
                               @PathVariable int schemeId,
                               @RequestParam("courseTypeCode") String courseTypeCode,
                               Model model) {

        CourseRequirement existing = courseRequirementRepository
                .findBySchemeIdAndCourseTypeCode(schemeId, courseTypeCode);

        // Step 1: All course types for the program
        List<CourseType> allCourseTypes = courseTypeRepository.findByProgramId((short) programId);

        // Step 2: Get used courseTypeCodes for this (programId, schemeId)
        List<String> usedCodes = courseRequirementRepository
                .findByProgramIdAndSchemeId((short) programId, schemeId)
                .stream()
                .map(CourseRequirement::getCourseTypeCode)
                .collect(Collectors.toList());

        // Step 3: Remove used codes except the current one being edited
        List<CourseType> availableTypes = allCourseTypes.stream()
                .filter(type ->
                        !usedCodes.contains(type.getCourseTypeCode()) ||
                                type.getCourseTypeCode().equals(courseTypeCode)
                )
                .collect(Collectors.toList());

        model.addAttribute("requirement", existing);
        model.addAttribute("courseTypes", availableTypes);
        model.addAttribute("programId", programId);
        model.addAttribute("schemeId", schemeId);
        model.addAttribute("editing", true);  // Useful for conditionally disabling dropdown
        return "course_requirement_form";
    }


    // Delete single requirement
    @GetMapping("/delete")
    public String deleteRequirement(@PathVariable int programId,
                                    @PathVariable int schemeId,
                                    @RequestParam("courseTypeCode") String courseTypeCode) {

        CourseRequirementId id = new CourseRequirementId(schemeId, courseTypeCode);
        courseRequirementRepository.deleteById(id);

        return "redirect:/program/" + programId + "/scheme/" + schemeId + "/courserequirements";
    }
}