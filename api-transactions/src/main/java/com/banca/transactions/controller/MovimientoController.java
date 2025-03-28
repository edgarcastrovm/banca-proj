package com.banca.transactions.controller;

import com.banca.transactions.service.MovimientoService;
import com.banca.utils.db.entity.Movimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {


    @Autowired
    private MovimientoService movimientoService;

    @GetMapping("/hc")
    public ResponseEntity<String> hc(){
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(movimientoService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        Movimiento movimiento = movimientoService.findById(id);
        if (movimiento != null){
            return ResponseEntity.ok(movimiento);
        }else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Movimiento movimiento){
        Movimiento _movimiento =  movimientoService.save(movimiento);
        if (_movimiento != null){
            return ResponseEntity.ok(_movimiento);
        }else return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Integer id,@RequestBody Movimiento movimiento){
        Movimiento _movimiento =  movimientoService.update(id,movimiento);
        if (_movimiento != null){
            return ResponseEntity.ok(_movimiento);
        }else return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(@PathVariable Integer id, @RequestBody Map<String, Object> movimiento){
        Movimiento _movimiento =  movimientoService.partialUpdate(id,movimiento);
        if (_movimiento != null){
            return ResponseEntity.ok(_movimiento);
        }else return ResponseEntity.badRequest().build();
    }
}
