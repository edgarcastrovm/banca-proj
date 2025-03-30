package com.banca.transactions.controller;

import com.banca.transactions.service.ReporteService;
import com.banca.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<?> reporte(
            @RequestHeader HttpHeaders headers,
            @RequestParam Integer cliente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        ApiResponse<?> response = reporteService.getReporte(headers, cliente, fechaInicio, fechaFin);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
