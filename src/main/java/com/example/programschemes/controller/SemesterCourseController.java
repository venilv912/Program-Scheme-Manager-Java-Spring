package com.example.programschemes.controller;

import com.example.programschemes.model.SemesterCourse;
import com.example.programschemes.model.SemesterCourseId;
import com.example.programschemes.model.CourseType;
import com.example.programschemes.model.MasterCourse;
import com.example.programschemes.repository.ProgramRepository;
import com.example.programschemes.repository.SemesterCourseRepository;
import com.example.programschemes.repository.CourseTypeRepository;
import com.example.programschemes.repository.MasterCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/program/{programId}/scheme/{schemeId}/semestercourses")
public class SemesterCourseController {

    @Autowired
    private SemesterCourseRepository semesterCourseRepository;

    @Autowired
    private CourseTypeRepository courseTypeRepository;

    @Autowired
    private MasterCourseRepository masterCourseRepository;

    @Autowired
    private ProgramRepository programRepository;

    @GetMapping
    public String listSemesterCourses(@PathVariable short programId,
                                      @PathVariable int schemeId,
                                      Model model) {
        List<SemesterCourse> courses = semesterCourseRepository.findBySchemeId(schemeId);

    // Group by semNo
            Map<Integer, List<SemesterCourse>> groupedCourses = new HashMap<>();
            courses.forEach(course -> {
                groupedCourses.computeIfAbsent(course.getSemNo(), k -> new ArrayList<>()).add(course);
            });

    // Ensure all semesters up to duration exist
            int duration = programRepository.findById(programId).get().getDuration();
            for (int sem = 1; sem <= duration; sem++) {
                groupedCourses.putIfAbsent(sem, new ArrayList<>());
            }

        model.addAttribute("groupedCourses", groupedCourses);  // <-- add this
        model.addAttribute("programId", programId);
        model.addAttribute("schemeId", schemeId);
        return "semester_courses";  // View name
    }

    @GetMapping("/add")
    public String showAddForm(@PathVariable short programId,
                              @PathVariable int schemeId,
                              @RequestParam("semNo") int semNo,
                              Model model) {

        SemesterCourse sc = new SemesterCourse();
        sc.setProgramId(programId);
        sc.setSchemeId(schemeId);
        sc.setSemNo(semNo); // set semester number here

        // Fetch existing courses for this semester
        List<SemesterCourse> existingCourses = semesterCourseRepository
                .findBySchemeIdAndSemNo(schemeId, semNo);

        // Determine next serial number
        int nextSrNo = existingCourses.stream()
                .mapToInt(SemesterCourse::getCourseSrNo)
                .max()
                .orElse(0) + 1;

        sc.setCourseSrNo(nextSrNo);
        List<CourseType> courseTypes = courseTypeRepository.findByProgramId(programId);
        List<MasterCourse> masterCourses = masterCourseRepository.findAll();

        model.addAttribute("semesterCourse", sc);
        model.addAttribute("courseTypes", courseTypes);
        model.addAttribute("masterCourses", masterCourses);
        model.addAttribute("programId", programId);
        model.addAttribute("schemeId", schemeId);
        model.addAttribute("editing", false); // Ensure not in edit mode

        return "semester_course_form";
    }


    @PostMapping("/save")
    public String saveSemesterCourse(@ModelAttribute("semesterCourse") SemesterCourse sc) {
        semesterCourseRepository.save(sc);
        return "redirect:/program/" + sc.getProgramId() + "/scheme/" + sc.getSchemeId() + "/semestercourses";
    }

    @GetMapping("/edit")
    public String showEditForm(@PathVariable short programId,
                               @PathVariable int schemeId,
                               @RequestParam("semNo") int semNo,
                               @RequestParam("courseSrNo") int courseSrNo,
                               Model model) {

        Optional<SemesterCourse> courseOpt = semesterCourseRepository.findById(
                new SemesterCourseId(semNo, schemeId, courseSrNo)
        );

        if (courseOpt.isEmpty()) {
            return "redirect:/program/" + programId + "/scheme/" + schemeId + "/semestercourses";
        }

        SemesterCourse course = courseOpt.get();
        List<CourseType> courseTypes = courseTypeRepository.findByProgramId(programId);
        List<MasterCourse> masterCourses = masterCourseRepository.findAll();

        model.addAttribute("semesterCourse", course);
        model.addAttribute("courseTypes", courseTypes);
        model.addAttribute("masterCourses", masterCourses);
        model.addAttribute("programId", programId);
        model.addAttribute("schemeId", schemeId);
        model.addAttribute("editing", true);

        return "semester_course_form";
    }

    @GetMapping("/delete")
    public String deleteSemesterCourse(@PathVariable short programId,
                                       @PathVariable int schemeId,
                                       @RequestParam("semNo") int semNo,
                                       @RequestParam("courseSrNo") int courseSrNo) {
        // Delete the specific course
        semesterCourseRepository.deleteById(new SemesterCourseId(semNo, schemeId, courseSrNo));

        // Fetch all remaining courses in that semester ordered by courseSrNo
        List<SemesterCourse> courses = semesterCourseRepository
                .findBySchemeIdAndSemNoOrderByCourseSrNo(schemeId, semNo);

        // Reassign courseSrNo to keep it sequential (starting from 1)
        for (int i = 0; i < courses.size(); i++) {
            SemesterCourse course = courses.get(i);
            int newSrNo = i + 1;

            if (course.getCourseSrNo() != newSrNo) {
                // Delete old record (because courseSrNo is part of composite key)
                semesterCourseRepository.deleteById(
                        new SemesterCourseId(course.getSemNo(), course.getSchemeId(), course.getCourseSrNo())
                );

                // Update courseSrNo and re-save
                course.setCourseSrNo(newSrNo);
                semesterCourseRepository.save(course);
            }
        }

        return "redirect:/program/" + programId + "/scheme/" + schemeId + "/semestercourses";
    }
}
