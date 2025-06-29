package com.example.programschemes.controller;

import com.example.programschemes.model.Programs;
import com.example.programschemes.model.Scheme;
import com.example.programschemes.repository.ProgramRepository;
import com.example.programschemes.repository.SchemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
public class SchemeController {

    @Autowired
    private SchemeRepository schemeRepository;

    @Autowired
    private ProgramRepository programRepository;

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
        return "scheme_form";
    }

    // Handle Scheme submission
    @PostMapping("/scheme/save")
    public String saveScheme(@ModelAttribute("scheme") Scheme scheme) {
        schemeRepository.save(scheme);
        return "redirect:/program/" + scheme.getProgram().getId();
    }
}
