package com.example.programschemes.controller;

import com.example.programschemes.model.Programs;
import com.example.programschemes.model.Scheme;
import com.example.programschemes.repository.ProgramRepository;
import com.example.programschemes.repository.SchemeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "program_form";
    }

    @PostMapping("/program/save")
    public String saveProgram(@ModelAttribute("program") Programs program) {
        program.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        program.setRowState((short) 1); // assuming 1 = active
        programRepository.save(program);
        return "redirect:/dashboard";
    }

    @GetMapping("/program/{id}")
    public String viewProgramSchemes(@PathVariable("id") Short programId, Model model) {
        Optional<Programs> program = programRepository.findById(programId);
        if (program.isEmpty()) {
            return "redirect:/dashboard";
        }

        List<Scheme> schemes = schemeRepository.findByProgramId(programId);
        model.addAttribute("program", program.get());
        model.addAttribute("schemes", schemes);
        return "program_schemes";
    }
}
