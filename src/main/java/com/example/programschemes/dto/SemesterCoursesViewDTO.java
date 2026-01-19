package com.example.programschemes.dto;

public record SemesterCoursesViewDTO(
        String batch,
        String semester,
        String crscode,
        String crsname,
        String credithours
) {}
