package com.banca.transactions.service;

import com.banca.transactions.repository.IMovimientoRepository;
import com.banca.utils.db.entity.Movimiento;
import com.banca.utils.db.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoService {
    @Autowired
    IMovimientoRepository movimientoRepository;

    public List<Movimiento> findAll() {
        var movimientos = movimientoRepository.findAll();
        if (movimientos.isEmpty()) {
            return null;
        }
        return movimientos.stream().toList();
    }

    public Movimiento findById(Integer id) {
        return movimientoRepository.findById(id).orElse(null);
    }

    public Movimiento save(Movimiento movimiento) {
        Persona persona = movimiento.getPersona();
        if (persona == null) {
            return null;
        }
        persona = personaRepository.save(persona);
        movimiento.setPersona(persona);
        return movimientoRepository.save(movimiento);
    }
    public Movimiento update(Integer id,Movimiento movimiento) {
        Movimiento movimientoToUpdate = findById(id);
        if (movimientoToUpdate == null) {
            return null;
        }
        Persona personaToUpdate = movimiento.getPersona();
        if (personaToUpdate == null) {
            return null;
        }
        movimientoToUpdate.setPersona(personaToUpdate);

        return movimientoRepository.save(movimientoToUpdate);
    }
    public Movimiento partialUpdate(Integer id, Map<String, Object> movimiento) {
        Movimiento movimientoToUpdate = findById(id);
        if (movimientoToUpdate == null) {
            return null;
        }
        movimiento.forEach((key, value) -> {
            switch (key){
                default:
                    break;
            }
        });

        return movimientoRepository.save(movimientoToUpdate);
    }

    public boolean delete(Integer id) {
        Movimiento movimientoToUpdate = findById(id);
        if (movimientoToUpdate == null) {
            return false;
        }
        movimientoToUpdate.setEstado(false);
        movimientoRepository.save(movimientoToUpdate);
        return true;
    }
}
