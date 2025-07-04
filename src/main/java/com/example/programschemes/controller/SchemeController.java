package com.example.programschemes.controller;

import com.example.programschemes.model.*;
import com.example.programschemes.repository.CourseRequirementRepository;
import com.example.programschemes.repository.ProgramRepository;
import com.example.programschemes.repository.SchemeRepository;
import com.example.programschemes.repository.SemesterCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;
@Controller
public class SchemeController {

    @Autowired
    private SchemeRepository schemeRepository;

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private CourseRequirementRepository courseRequirementRepository;
    @Autowired
    private SemesterCourseRepository semesterCourseRepository;
    // Show Add Scheme form
    @GetMapping("/program/{id}/scheme/add")
    public String showAddSchemeForm(@PathVariable("id") Short programId, Model model) {
        Optional<Programs> program = programRepository.findById(programId);
        if (program.isEmpty()) {
            return "redirect:/dashboard";
        }

        Scheme scheme = new Scheme();
        scheme.setProgram(program.get());
        model.addAttribute("scheme", scheme);
        model.addAttribute("editing", false);
        return "scheme_form";
    }

    // Handle Scheme submission
    @PostMapping("/scheme/save")
    public String saveScheme(@ModelAttribute("scheme") Scheme scheme) {
        schemeRepository.save(scheme);
        return "redirect:/program/" + scheme.getProgram().getId();
    }
    @GetMapping("/program/{programId}/scheme/{schemeId}/details")
    public String viewSchemeDetails(@PathVariable int programId,
                                    @PathVariable int schemeId,
                                    Model model) {
        Scheme scheme = schemeRepository.findById(schemeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid scheme ID"));
        model.addAttribute("scheme", scheme);
        model.addAttribute("programId", programId);
        return "scheme_details";
    }

    @GetMapping("/program/{programId}/scheme/{schemeId}/delete")
    public String deleteScheme(@PathVariable int programId,
                               @PathVariable int schemeId) {
        schemeRepository.deleteById(schemeId);
        return "redirect:/program/" + programId;
    }
    @GetMapping("/program/{programId}/scheme/{schemeId}/edit")
    public String showEditSchemeForm(@PathVariable("programId") short programId,
                                     @PathVariable("schemeId") int schemeId,
                                     Model model) {
        Scheme scheme = schemeRepository.findById(schemeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid scheme ID"));

        model.addAttribute("scheme", scheme);
        model.addAttribute("editing", true);
        return "scheme_form";
    }
    @GetMapping("/program/{id}/scheme/pull")
    public String showPullForm(@PathVariable("id") Short programId, Model model) {
        List<Scheme> allSchemes = schemeRepository.findByProgramId(programId);
        model.addAttribute("schemes", allSchemes);
        model.addAttribute("programId", programId);
        return "pull_scheme_select";
    }
    @PostMapping("/program/{programId}/scheme/pull")
    public String pullFromScheme(@PathVariable int programId,
                                 @RequestParam("sourceSchemeId") int fromSchemeId,
                                 @RequestParam("effectiveYear") int effectiveYear) {

        // Fetch the scheme to pull from
        Scheme fromScheme = schemeRepository.findById(fromSchemeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid source scheme ID"));

        // Generate new scheme ID based on programId and effective year
        int newSchemeId = Integer.parseInt(programId + "" + effectiveYear);

        // Create and populate new Scheme
        Scheme newScheme = new Scheme();
        newScheme.setId(newSchemeId);
        newScheme.setProgram(fromScheme.getProgram());
        newScheme.setEffectiveFromYear(effectiveYear);
        newScheme.setMinCpi(fromScheme.getMinCpi());
        newScheme.setMinCredits(fromScheme.getMinCredits());
        newScheme.setMaxCreditLoad(fromScheme.getMaxCreditLoad());
        newScheme.setMaxCourses(fromScheme.getMaxCourses());
        schemeRepository.save(newScheme);

        // Clone Course Requirements
        List<CourseRequirement> fromRequirements = courseRequirementRepository.findBySchemeId(fromSchemeId);
        for (CourseRequirement cr : fromRequirements) {
            CourseRequirement newCr = new CourseRequirement();
            newCr.setSchemeId(newSchemeId);
            newCr.setProgramId(cr.getProgramId());
            newCr.setCourseTypeCode(cr.getCourseTypeCode());
            newCr.setMinCourse(cr.getMinCourse());
            newCr.setMaxCourse(cr.getMaxCourse());
            newCr.setMinCredits(cr.getMinCredits());
            newCr.setMaxCredits(cr.getMaxCredits());
            courseRequirementRepository.save(newCr);
        }

        // Clone Semester Courses
        List<SemesterCourse> fromSemesterCourses = semesterCourseRepository.findBySchemeId(fromSchemeId);
        for (SemesterCourse sc : fromSemesterCourses) {
            SemesterCourse newSc = new SemesterCourse();
            newSc.setSchemeId(newSchemeId);
            newSc.setSemNo(sc.getSemNo());
            newSc.setCourseSrNo(sc.getCourseSrNo());
            newSc.setCourseTypeCode(sc.getCourseTypeCode());
            newSc.setCourseId(sc.getCourseId());
            newSc.setCourseCode(sc.getCourseCode());
            newSc.setCourseTitle(sc.getCourseTitle());
            newSc.setProgramId(sc.getProgramId());
            newSc.setLectureHours(sc.getLectureHours());
            newSc.setTutorialHours(sc.getTutorialHours());
            newSc.setPracticalHours(sc.getPracticalHours());
            newSc.setTotalCredits(sc.getTutorialHours());
            semesterCourseRepository.save(newSc);
        }

        // Redirect to the course requirements page of the new scheme
        return "redirect:/program/" + programId + "/scheme/" + newSchemeId + "/details";
    }





}
