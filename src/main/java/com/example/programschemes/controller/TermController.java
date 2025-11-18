package com.example.programschemes.controller;

import com.example.programschemes.model.*;
import com.example.programschemes.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/terms")
public class TermController {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private TermsRepository termRepository;

    @Autowired
    private AcademicYearsRepository academicYearRepository;

    @Autowired
    private SemestersRepository semesterRepository;

    @Autowired
    private BatchesRepository batchRepository;

    @Autowired
    private SemesterCourseRepository schemeCourseRepository;

    @Autowired
    private TermCoursesRepository termCourseRepository;

    @Autowired
    private SchemeRepository schemeRepository;

    @Autowired
    private SemesterCoursesRepository semesterCoursesRepository;

    // Show "Add Term" form
    @GetMapping("/add")
    public String showAddTermForm(Model model) {
        model.addAttribute("term", new Terms());
        model.addAttribute("academicYears", academicYearRepository.findAllByOrderByAyridDesc());
        model.addAttribute("termNames", List.of("Autumn", "Winter", "Summer"));
        return "add-term"; // templates/terms/add-term.html
    }

    // Handle "Add Term" submission
    @PostMapping("/add")
    @Transactional
    public String addTerm(
            @RequestParam("academicYearId") Short academicYearId,
            @RequestParam("termName") String termName,
            @RequestParam("termStartDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate termStartDate,
            @RequestParam("termEndDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate termEndDate,
            Model model) {

        // Generate new Term ID
        Short maxId = termRepository.findMaxTrmid();
        Short newTermId = (short) ((maxId == null ? 0 : maxId) + 1);

        // Create and save Term
        Terms term = new Terms();
        term.setTrmid(newTermId);
        term.setTrmname(termName);
        term.setTrmayrid(academicYearId);
        term.setTrmstarts(termStartDate);
        term.setTrmends(termEndDate);
        term.setTrmrowstate((short) 1);
        term.setTrmcreatedat(LocalDateTime.now());
        termRepository.save(term);

        // Fetch all active batches
        List<Batches> allBatches = batchRepository.findAll();

        List<Batches> activeBatches = allBatches.stream()
                .filter(batch -> {
                    // Skip if schemeId is null
                    if (batch.getSchemeId() == null) return false;

                    Short batchAyrId = batch.getBchcalid();
                    if (batchAyrId == null) return false;

                    Programs program = programRepository.findById(batch.getBchprgid()).orElse(null);
                    if (program == null || program.getDuration() == null) return false;

                    int diff = academicYearId - batchAyrId;
                    return diff >= 0 && diff < (program.getDuration());
                })
                .toList();


        int totalSemesters = 0;
        int totalCourses = 0;

        // For each active batch
        int cnt=0;
        for (Batches batch : activeBatches) {
            Programs program = programRepository.findById(batch.getBchprgid()).orElse(null);
            if (program == null) continue;

            // Calculate semester number
            int diff = academicYearId - batch.getBchcalid();
            int semNo = diff * 2 + (termName.equalsIgnoreCase("Winter") ? 2 : 1);

            // Fetch only CORE courses of that semNo from the scheme
            List<SemesterCourse> schemeCourses = schemeCourseRepository.findBySchemeIdAndSemNo(batch.getSchemeId(), semNo)
                    .stream()
                    .filter(sc -> sc.getCourseTypeCode() != null && sc.getCourseTypeCode().equalsIgnoreCase("CORE"))
                    .toList();

            //if (schemeCourses.isEmpty()) continue;

            // Create Semester record
            Short maxSemId = semesterRepository.findMaxSemesterId();
            Short newSemId = (short) ((maxSemId == null ? 0 : maxSemId) + 1);

            Semesters semester = new Semesters();
            semester.setStrid(newSemId);
            semester.setStrbchid(batch.getBchid());
            semester.setStrtrmid(term.getTrmid());
            semester.setStrname(batch.getBchname() + " - " + termName + " (" + semNo + ")");
            semesterRepository.save(semester);
            totalSemesters++;

            // Copy only the CORE courses of that sem
            for (SemesterCourse sc : schemeCourses) {

                // Insert into TERMCourses (your existing logic)
                Long maxTcrId = termCourseRepository.findMaxTermCourseid();
                Long newTcrId = (maxTcrId == null ? 1 : maxTcrId + 1);

                TermCourses tc = new TermCourses();
                tc.setTcrid(newTcrId);
                tc.setTcrtrmid(term.getTrmid());
                tc.setTcrcrsid(sc.getCrsid());
                tc.setTcrtype("REGULAR");
                tc.setTcrrowstate(1);          // ensure rowstate is valid
                tc.setTcrstatus("AVAILABLE");
                termCourseRepository.save(tc);
                totalCourses++;

                // NEW: Insert into SEMESTERCOURSES

                Long maxScrId = semesterCoursesRepository.findMaxSemesterCourseid();
                Long newScrId = (maxScrId == null ? 1 : maxScrId + 1);

                SemesterCourses semCourse = new SemesterCourses();
                semCourse.setScrid(newScrId);
                semCourse.setScrstrid(newSemId);           // link to newly created semester
                semCourse.setScrcrsid(sc.getCrsid());      // course ID from scheme
                semCourse.setScrelective("N");             // CORE means non-elective
                semCourse.setScrrowstate(1);       // active row
                semCourse.setScrcreatedat(LocalDateTime.now());

                semesterCoursesRepository.save(semCourse);
            }

        }

        model.addAttribute("message",
                "Term '" + termName + "' added successfully! Created " +
                        totalSemesters + " semesters and " + totalCourses + " CORE term courses.");

        return "redirect:/terms";
    }

    // LIST TERMS (DESC ORDER)
    @GetMapping("")
    public String listTerms(Model model) {
        model.addAttribute("terms",
                termRepository.findAll()
                        .stream()
                        .sorted((a, b) -> b.getTrmid().compareTo(a.getTrmid()))
                        .toList()
        );
        return "terms-list";
    }

    // LIST SEMESTERS (DESC ORDER)
    @GetMapping("/semesters")
    public String listSemesters(Model model) {
        model.addAttribute("semesters",
                semesterRepository.findAll()
                        .stream()
                        .sorted((a, b) -> b.getStrid().compareTo(a.getStrid()))
                        .toList()
        );
        return "semesters-list"; // semesters.html
    }

    // LIST TERM COURSES (DESC ORDER BY termId, then courseCode)
    @GetMapping("/termcourses")
    public String listTermCourses(Model model) {
        model.addAttribute("termcourses",
                termCourseRepository.findAll()
                        .stream()
                        .sorted((a, b) -> {
                            int cmp = b.getTcrid().compareTo(a.getTcrid());
                            return cmp;
                        })
                        .toList()
        );
        return "termcourses-list"; // term_courses.html
    }

    @GetMapping("/semestercourses")
    public String listSemesterCourses(Model model) {

        // Get all semester courses sorted DESC by scrid
        List<SemesterCourses> semesterCourses = semesterCoursesRepository
                .findAll(Sort.by(Sort.Direction.DESC, "scrid"));

        model.addAttribute("semesterCourses", semesterCourses);

        return "semestercourses-list";
    }
}
