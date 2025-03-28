package com.banca.customer.service;

import com.banca.customer.repository.IClienteRepository;
import com.banca.customer.repository.IPersonaRepository;
import com.banca.utils.db.entity.Cliente;
import com.banca.utils.db.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    IClienteRepository clienteRepository;
    @Autowired
    IPersonaRepository personaRepository;

    public List<Cliente> findAll() {
        var clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            return null;
        }
        return clientes.stream().toList();
    }

    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente save(Cliente cliente) {
        Persona persona = cliente.getPersona();
        if (persona == null) {
            return null;
        }
        persona = personaRepository.save(persona);
        cliente.setPersona(persona);
        return clienteRepository.save(cliente);
    }
}
