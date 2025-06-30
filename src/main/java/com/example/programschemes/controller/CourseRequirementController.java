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

        //System.out.println("=== Received programId: " + programId + ", schemeId: " + schemeId);

        CourseRequirement cr = new CourseRequirement();
        cr.setSchemeId(schemeId);
        cr.setProgramId((short) programId);

        List<CourseType> courseTypes = courseTypeRepository.findByProgramId((short) programId);

        // Print courseTypes to confirm filtering
       // System.out.println("=== Fetched courseTypes count: " + courseTypes.size());

        model.addAttribute("requirement", cr);
        model.addAttribute("courseTypes", courseTypes);
        model.addAttribute("programId", programId);
        model.addAttribute("schemeId", schemeId);
        return "course_requirement_form";
    }


    // Save the requirement (from form)
    @PostMapping("/save")
    public String saveRequirement(@ModelAttribute("requirement") CourseRequirement cr) {
        courseRequirementRepository.save(cr);
        return "redirect:/program/" + cr.getProgramId() + "/scheme/" + cr.getSchemeId() + "/courserequirements";
    }
    @PostMapping("/delete-selected")
    public String deleteSelected(@PathVariable int programId,
                                 @PathVariable int schemeId,
                                 @RequestParam("selected") List<String> selectedCodes) {

        for (String code : selectedCodes) {
            CourseRequirementId id = new CourseRequirementId(schemeId, code);
            courseRequirementRepository.deleteById(id);
        }

        return "redirect:/program/" + programId + "/scheme/" + schemeId + "/courserequirements";
    }

}
