package com.banca.customer.controller;

import com.banca.customer.config.RequestInterceptor;
import com.banca.customer.service.ClienteService;
import com.banca.utils.db.entity.Cliente;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    private static final Logger log = LogManager.getLogger(ClienteController.class);

    @GetMapping("/hc")
    public ResponseEntity<String> hc() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping
    public ResponseEntity<?> get() {
        log.info("Clientes findAll");
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        log.info("Cliente ById {}", id);
        Cliente cliente = clienteService.findById(id);
        if (cliente != null) {
            log.info("Cliente found");
            return ResponseEntity.ok(cliente);
        }
        log.error("Cliente not found");
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Cliente cliente) {
        log.info("New cliente {}", cliente);
        Cliente _cliente = clienteService.save(cliente);
        if (_cliente != null) {
            log.info("New cliente success");
            return ResponseEntity.ok(_cliente);
        }
        log.error("New cliente failed");
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Integer id, @RequestBody Cliente cliente) {
        log.info("Update full cliente {}", cliente);
        Cliente _cliente = clienteService.update(id, cliente);
        if (_cliente != null) {
            log.info("Update full cliente success");
            return ResponseEntity.ok(_cliente);
        }
        log.error("Update full cliente failed");
        return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patch(@PathVariable Integer id, @RequestBody Map<String, Object> cliente) {
        log.info("Parcial update cliente {}", cliente);
        Cliente _cliente = clienteService.partialUpdate(id, cliente);
        if (_cliente != null) {
            log.info("Parcial update cliente success");
            return ResponseEntity.ok(_cliente);
        }
        log.error("Parcial update cliente failed");
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        log.info("Delete cliente {}", id);
        boolean deleted = clienteService.delete(id);
        if (deleted) {
            log.info("Delete cliente success");
            return ResponseEntity.ok("Deleted");
        }
        log.error("Delete cliente failed");
        return ResponseEntity.badRequest().build();
    }
}
