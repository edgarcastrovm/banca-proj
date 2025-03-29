package com.banca.customer.service;

import com.banca.customer.repository.IClienteRepository;
import com.banca.customer.repository.IPersonaRepository;
import com.banca.utils.db.entity.Cliente;
import com.banca.utils.db.entity.Persona;
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

    @Transactional
    public Cliente save(Cliente cliente) {
        Persona persona = cliente.getPersona();
        if (persona == null) {
            return null;
        }
        persona = personaRepository.save(persona);
        cliente.setPersona(persona);
        return clienteRepository.save(cliente);
    }
    public Cliente update(Integer id,Cliente cliente) {
        Cliente clienteToUpdate = findById(id);
        if (clienteToUpdate == null) {
            return null;
        }
        Persona personaToUpdate = cliente.getPersona();
        if (personaToUpdate == null) {
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
        Cliente clienteToUpdate = findById(id);
        if (clienteToUpdate == null) {
            return null;
        }
        cliente.forEach((key, value) -> {
            switch (key){
                case "nombre":
                    clienteToUpdate.getPersona().setNombre((String) value);
                    break;
                case "genero":
                    clienteToUpdate.getPersona().setGenero((String) value);
                    break;
                case "edad":
                    clienteToUpdate.getPersona().setEdad((Integer) value);
                    break;
                case "identificacion":
                    clienteToUpdate.getPersona().setIdentificacion((String) value);
                    break;
                case "telefono":
                    clienteToUpdate.getPersona().setTelefono((String) value);
                    break;
                case "direccion":
                    clienteToUpdate.getPersona().setDireccion((String) value);
                    break;
                case "usuario":
                    clienteToUpdate.setUsuario((String) value);
                    break;
                case "contrasena":
                    clienteToUpdate.setContrasena((String) value);
                    break;
                default:
                    break;
            }
        });

        return clienteRepository.save(clienteToUpdate);
    }

    public boolean delete(Integer id) {
        Cliente clienteToUpdate = findById(id);
        if (clienteToUpdate == null) {
            return false;
        }
        clienteToUpdate.setEstado(false);
        clienteRepository.save(clienteToUpdate);
        return true;
    }
}
