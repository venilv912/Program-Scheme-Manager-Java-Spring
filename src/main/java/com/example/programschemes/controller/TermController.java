package com.example.programschemes.controller;

import com.example.programschemes.dto.*;
import com.example.programschemes.util.RomanNumeralUtil;
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
import java.util.Date;
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

        // Finding academic year entity
        AcademicYears academicYear = academicYearRepository.findById(academicYearId).orElseThrow(() -> new RuntimeException("Academic Year not found"));

        // Extract starting year name
        String academicAyrName = academicYear.getAyrname();

        // Extracting starting year
        int academicYearStarting = Integer.parseInt(academicAyrName.split("-")[0]);

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

                    // Finding academic year entity
                    AcademicYears batchYear = academicYearRepository.findById(batchAyrId).orElseThrow(() -> new RuntimeException("Batch Year not found"));

                    // Extract starting year name
                    String batchAyrName = batchYear.getAyrname();

                    // Extracting starting year
                    int batchYearStarting = Integer.parseInt(batchAyrName.split("-")[0]);

                    // Calculate difference
                    int diff = academicYearStarting - batchYearStarting;
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
            Short batchAyrId = batch.getBchcalid();

            // Finding academic year entity
            AcademicYears batchYear = academicYearRepository.findById(batchAyrId).orElseThrow(() -> new RuntimeException("Batch Year not found"));

            // Extract starting year name
            String batchAyrName = batchYear.getAyrname();

            // Extracting starting year
            int batchYearStarting = Integer.parseInt(batchAyrName.split("-")[0]);

            // Calculate difference
            int diff = academicYearStarting - batchYearStarting;
            int semNo = diff * 2 + (termName.equalsIgnoreCase("Winter") ? 2 : 1);
            String semRoman = RomanNumeralUtil.toRoman(semNo);

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
            semester.setStrname("Semester " + semRoman);
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
        List<Object[]> rows = termRepository.getAllTermsDetailsRaw();

        List<TermViewDTO> terms = rows.stream()
                .map(r -> new TermViewDTO(
                        ((Number) r[0]).longValue(),
                        (String) r[1],
                        (String) r[2],
                        (Date) r[3],
                        (Date) r[4]
                )).toList();

        model.addAttribute("terms", terms);
        return "terms-list";
    }

    // LIST SEMESTERS (DESC ORDER)
    @GetMapping("/semesters")
    public String listSemesters(Model model) {
        List<Object[]> rows = semesterRepository.getAllSemestersDetailsRaw();

        List<SemesterViewDTO> semesters = rows.stream()
                .map(r -> new SemesterViewDTO(
                        ((Number) r[0]).longValue(),
                        (String) r[1],
                        (String) r[2],
                        (String) r[3]
                )).toList();

        model.addAttribute("semesters", semesters);
        return "semesters-list"; // semesters.html
    }

    // LIST TERM COURSES (DESC ORDER BY termId, then courseCode)
    @GetMapping("/termcourses")
    public String listTermCourses(Model model) {
        List<Object[]> rows = termCourseRepository.getAllTermCoursesDetailsRaw();

        List<TermCoursesViewDTO> termcourses = rows.stream()
                .map(r -> new TermCoursesViewDTO(
                        (String) r[0],
                        (String) r[1],
                        (String) r[2],
                        (String) r[3]
                )).toList();

        model.addAttribute("termcourses", termcourses);
        return "termcourses-list"; // term_courses.html
    }

    @GetMapping("/semestercourses")
    public String listSemesterCourses(Model model) {
        List<Object[]> rows = semesterCoursesRepository.getAllSemesterCoursesDetailsRaw();

        List<SemesterCoursesViewDTO> semesterCourses = rows.stream()
                .map(r -> new SemesterCoursesViewDTO(
                        (String) r[0],
                        (String) r[1],
                        (String) r[2],
                        (String) r[3],
                        (String) r[4]
                )).toList();

        model.addAttribute("semesterCourses", semesterCourses);
        return "semestercourses-list";
    }
}
