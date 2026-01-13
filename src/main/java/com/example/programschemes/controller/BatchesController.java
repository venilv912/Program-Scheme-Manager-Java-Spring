package com.example.programschemes.controller;

import com.example.programschemes.dto.BatchViewDTO;
import com.example.programschemes.model.Batches;
import com.example.programschemes.model.AcademicYears;
import com.example.programschemes.model.Programs;
import com.example.programschemes.model.Scheme;
import com.example.programschemes.repository.BatchesRepository;
import com.example.programschemes.repository.AcademicYearsRepository;
import com.example.programschemes.repository.ProgramRepository;
import com.example.programschemes.repository.SchemeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/batches")
public class BatchesController {

    @Autowired
    private BatchesRepository batchRepository;

    @Autowired
    private AcademicYearsRepository academicYearRepository;

    @Autowired
    private SchemeRepository schemeRepository;
    @Autowired
    private ProgramRepository programRepository;
    // Show all batches
    @GetMapping
    public String listBatches(Model model) {
        List<Object[]> rows = batchRepository.getAllBatchesDetailsRaw();

        List<BatchViewDTO> batches = rows.stream()
                .map(r -> new BatchViewDTO(
                        ((Number) r[0]).longValue(),
                        (String) r[1],
                        (String) r[2],
                        (String) r[3],
                        ((Number) r[4]).intValue(),
                        r[5] != null ? ((Number) r[5]).intValue() : null
                )).toList();

        model.addAttribute("batches", batches);
        return "batches";
    }

    // Show Add Batch Page
    @GetMapping("/add")
    public String showAddBatch(Model model) {

        model.addAttribute("batch", new Batches());

        List<AcademicYears> years = academicYearRepository.findAllByOrderByAyridDesc();
        List<Scheme> schemes = schemeRepository.findAll();
        List<Programs>programs=programRepository.findAll();
        model.addAttribute("years", years);
        model.addAttribute("schemes", schemes);
        model.addAttribute("programs",programs);

        return "add_batch";
    }

    // Handle Save
    @PostMapping("/add")
    public String saveBatch(@ModelAttribute("batch") Batches batch) {

        // Auto-increment ID
        Integer maxId = batchRepository.findMaxBatchId();
        batch.setBchid(maxId == null ? 1 : (short) (maxId + 1));

        // AUTO-FILL REQUIRED SYSTEM FIELDS
        batch.setBchcreatedat(LocalDateTime.now());
        batch.setBchlastupdatedat(LocalDateTime.now());
        batch.setBchcreatedby(0L);         // Or logged-in user ID
        batch.setBchlastupdatedby(0L);     // Or logged-in user ID
        batch.setBchrowstate((short) 0);               // Active or your default
        batch.setBchinstcode("2021");           // Whatever your default is
        batch.setBchfield1((short) 0);                  // If NOT NULL, set empty string

        batchRepository.save(batch);
        return "redirect:/batches";
    }

}
