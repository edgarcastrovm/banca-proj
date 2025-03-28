package com.banca.transactions.controller;

import com.banca.transactions.service.CuentaService;
import com.banca.utils.db.entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/hc")
    public ResponseEntity<String> hc(){
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(cuentaService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        Cuenta cuenta = cuentaService.findById(id);
        if (cuenta != null){
            return ResponseEntity.ok(cuenta);
        }else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Cuenta cuenta){
        Cuenta _cuenta =  cuentaService.save(cuenta);
        if (_cuenta != null){
            return ResponseEntity.ok(_cuenta);
        }else return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Integer id,@RequestBody Cuenta cuenta){
        Cuenta _cuenta =  cuentaService.update(id,cuenta);
        if (_cuenta != null){
            return ResponseEntity.ok(_cuenta);
        }else return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(@PathVariable Integer id, @RequestBody Map<String, Object> cuenta){
        Cuenta _cuenta =  cuentaService.partialUpdate(id,cuenta);
        if (_cuenta != null){
            return ResponseEntity.ok(_cuenta);
        }else return ResponseEntity.badRequest().build();
    }
}
