package com.banca.customer.service;

import com.banca.customer.repository.IClienteRepository;
import com.banca.customer.repository.IPersonaRepository;
import com.banca.utils.db.entity.Cliente;
import com.banca.utils.db.entity.Persona;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    IClienteRepository clienteRepository;
    @Autowired
    IPersonaRepository personaRepository;

    private static final Logger log = LogManager.getLogger(ClienteService.class);

    public List<Cliente> findAll() {
        log.info("Find all clientes");
        var clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            log.error("No clientes found");
            return null;
        }
        return clientes.stream().toList();
    }

    public Cliente findById(Integer id) {
        log.info("Find cliente by id: {}", id);
        return clienteRepository.findById(id).orElse(null);
    }

    @Transactional
    public Cliente save(Cliente cliente) {
        log.info("Save cliente: {}", cliente);
        Persona persona = cliente.getPersona();
        if (persona == null) {
            log.error("Persona not found");
            return null;
        }
        persona = personaRepository.save(persona);
        cliente.setPersona(persona);
        return clienteRepository.save(cliente);
    }

    public Cliente update(Integer id,Cliente cliente) {
        log.info("Update cliente: {}", cliente);
        Cliente clienteToUpdate = findById(id);
        if (clienteToUpdate == null) {
            log.error("No cliente found");
            return null;
        }
        Persona personaToUpdate = cliente.getPersona();
        if (personaToUpdate == null) {
            log.error("Persona not found");
            return null;
        }
        clienteToUpdate.setUsuario(cliente.getUsuario());
        clienteToUpdate.setContrasena(cliente.getContrasena());
        clienteToUpdate.setEstado(cliente.getEstado());

        personaToUpdate.setNombre(cliente.getPersona().getNombre());
        personaToUpdate.setGenero(cliente.getPersona().getGenero());
        personaToUpdate.setEdad(cliente.getPersona().getEdad());
        personaToUpdate.setIdentificacion(cliente.getPersona().getIdentificacion());
        personaToUpdate.setTelefono(cliente.getPersona().getTelefono());
        personaToUpdate.setDireccion(cliente.getPersona().getDireccion());

        clienteToUpdate.setPersona(personaToUpdate);

        return clienteRepository.save(clienteToUpdate);
    }
    public Cliente partialUpdate(Integer id, Map<String, Object> cliente) {
        log.info("Update partial cliente: {}", cliente);
        Cliente clienteToUpdate = findById(id);
        if (clienteToUpdate == null) {
            log.error("No cliente found");
            return null;
        }
        cliente.forEach((key, value) -> {
            switch (key){
                case "nombre":
                    clienteToUpdate.getPersona().setNombre((String) value);
                    log.info("Update nombre: {}", value);
                    break;
                case "genero":
                    clienteToUpdate.getPersona().setGenero((String) value);
                    log.info("Update genero: {}", value);
                    break;
                case "edad":
                    clienteToUpdate.getPersona().setEdad((Integer) value);
                    log.info("Update edad: {}", value);
                    break;
                case "identificacion":
                    clienteToUpdate.getPersona().setIdentificacion((String) value);
                    log.info("Update identificacion: {}", value);
                    break;
                case "telefono":
                    clienteToUpdate.getPersona().setTelefono((String) value);
                    log.info("Update telefono: {}", value);
                    break;
                case "direccion":
                    clienteToUpdate.getPersona().setDireccion((String) value);
                    log.info("Update direccion: {}", value);
                    break;
                case "usuario":
                    clienteToUpdate.setUsuario((String) value);
                    log.info("Update usuario: {}", value);
                    break;
                case "contrasena":
                    clienteToUpdate.setContrasena((String) value);
                    log.info("Update contrasena: {}", value);
                    break;
                default:
                    log.error("Unknown key: {}", key);
                    break;
            }
        });

        return clienteRepository.save(clienteToUpdate);
    }

    public boolean delete(Integer id) {
        log.info("Delete cliente: {}", id);
        Cliente clienteToUpdate = findById(id);
        if (clienteToUpdate == null) {
            log.error("No cliente found");
            return false;
        }
        clienteToUpdate.setEstado(false);
        clienteRepository.save(clienteToUpdate);
        return true;
    }
}
