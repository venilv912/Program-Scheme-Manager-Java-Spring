package com.example.programschemes.dto;

import java.util.Date;

public record TermViewDTO(
        Long trmid,
        String trmname,
        String ayrname,
        Date trmstarts,
        Date trmends
) {}
