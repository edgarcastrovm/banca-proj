package com.banca.customer.controller;

import com.banca.customer.service.ClienteService;
import com.banca.utils.db.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        Cliente _cliente =  clienteService.save(cliente);
        if (_cliente != null){
            return ResponseEntity.ok(_cliente);
        }else return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Integer id,@RequestBody Cliente cliente){
        Cliente _cliente =  clienteService.update(id,cliente);
        if (_cliente != null){
            return ResponseEntity.ok(_cliente);
        }else return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(@PathVariable Integer id, @RequestBody Map<String, Object> cliente){
            Cliente _cliente =  clienteService.partialUpdate(id,cliente);
            if (_cliente != null){
                return ResponseEntity.ok(_cliente);
            }else return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        boolean deleted = clienteService.delete(id);
        if (deleted){
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.badRequest().build();
    }
}
