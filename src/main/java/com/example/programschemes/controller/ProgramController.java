package com.example.programschemes.controller;

import com.example.programschemes.model.Programs;
import com.example.programschemes.model.Scheme;
import com.example.programschemes.repository.ProgramRepository;
import com.example.programschemes.repository.SchemeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Controller
public class ProgramController {

    private final ProgramRepository programRepository;
    private final SchemeRepository schemeRepository;

    public ProgramController(ProgramRepository programRepository, SchemeRepository schemeRepository) {
        this.programRepository = programRepository;
        this.schemeRepository = schemeRepository;
    }

    @GetMapping("/dashboard")
    public String showPrograms(Model model) {
        model.addAttribute("programs", programRepository.findAll());
        return "dashboard";
    }

    @GetMapping("/program/add")
    public String showAddForm(Model model) {
        model.addAttribute("program", new Programs());
        model.addAttribute("editing", false);
        return "program_form";
    }

    @PostMapping("/program/save")
    public String saveProgram(@ModelAttribute("program") Programs program,
                              @RequestParam("editing") boolean editing,
                              RedirectAttributes redirectAttributes) {

        if (!editing && programRepository.existsById(program.getId())) {
            redirectAttributes.addFlashAttribute("error", "Program with this ID already exists.");
            return "redirect:/program/add";
        }

        program.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        program.setRowState((short) 1); // Assuming 1 = active

        programRepository.save(program);
        return "redirect:/dashboard";
    }

    @GetMapping("/program/{id}")
    public String viewProgramSchemes(@PathVariable("id") Short programId, Model model) {
        Optional<Programs> program = programRepository.findById(programId);
        if (program.isEmpty()) {
            return "redirect:/dashboard";
        }
        List<Scheme> schemes = schemeRepository.findByProgram_Id(programId);
        model.addAttribute("program", program.get());
        model.addAttribute("schemes", schemes);
        return "program_schemes";
    }
    @GetMapping("/program/edit/{id}")
    public String showEditForm(@PathVariable("id") short id, Model model) {
        Programs program = programRepository.findById(id).orElseThrow();
        model.addAttribute("program", program);
        model.addAttribute("editing", true);
        return "program_form";
    }
    @GetMapping("/secure")
    @ResponseBody
    public String testSecure() {
        return "You are authenticated!";
    }

}
