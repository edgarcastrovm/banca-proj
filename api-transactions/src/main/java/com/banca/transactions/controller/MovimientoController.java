package com.banca.transactions.controller;

import com.banca.transactions.service.MovimientoService;
import com.banca.utils.ApiResponse;
import com.banca.utils.db.entity.Movimiento;
import com.banca.utils.dto.MovimientoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {


    @Autowired
    private MovimientoService movimientoService;

    @GetMapping("/hc")
    public ResponseEntity<String> hc() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping
    public ResponseEntity<?> get() {
        ApiResponse<?> response = movimientoService.findAll();
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        ApiResponse<?> response = movimientoService.findById(id);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody MovimientoDto movimientoDto) {
        ApiResponse<?> response = movimientoService.save(movimientoDto);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Integer id, @RequestBody MovimientoDto movimiento) {
        ApiResponse<?> response = movimientoService.update(id, movimiento);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(@PathVariable Integer id, @RequestBody Map<String, Object> movimiento) {
        ApiResponse<?> response = movimientoService.partialUpdate(id, movimiento);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
