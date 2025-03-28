package com.banca.customer.controller;

import com.banca.customer.service.ClienteService;
import com.banca.utils.db.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/hc")
    public ResponseEntity<String> hc(){
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(clienteService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);
        if (cliente != null){
            return ResponseEntity.ok(cliente);
        }else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Cliente cliente){
        Cliente cliente_ =  clienteService.save(cliente);
        if (cliente_ != null){
            return ResponseEntity.ok(cliente_);
        }else return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<String> put(){
        return ResponseEntity.ok("Actualiza");
    }

    @DeleteMapping
    public ResponseEntity<String> delete(){
        return ResponseEntity.ok("Elimina");
    }
}
