package com.banca.transactions.service;

import com.banca.transactions.repository.ICuentaRepository;
import com.banca.utils.db.entity.Cuenta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CuentaService {

    @Autowired
    ICuentaRepository cuentaRepository;

    public List<Cuenta> findAll() {
        var cuentas = cuentaRepository.findAll();
        if (cuentas.isEmpty()) {
            return null;
        }
        return cuentas.stream().toList();
    }

    public Cuenta findById(Integer id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    public Cuenta save(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }
    public Cuenta update(Integer id,Cuenta cuenta) {
        Cuenta cuentaToUpdate = findById(id);
        if (cuentaToUpdate == null) {
            return null;
        }
        return cuentaRepository.save(cuentaToUpdate);
    }
    public Cuenta partialUpdate(Integer id, Map<String, Object> cuenta) {
        Cuenta cuentaToUpdate = findById(id);
        if (cuentaToUpdate == null) {
            return null;
        }
        cuenta.forEach((key, value) -> {
            switch (key){
                default:
                    break;
            }
        });

        return cuentaRepository.save(cuentaToUpdate);
    }

    public boolean delete(Integer id) {
        Cuenta cuentaToUpdate = findById(id);
        if (cuentaToUpdate == null) {
            return false;
        }
        cuentaToUpdate.setEstado(false);
        cuentaRepository.save(cuentaToUpdate);
        return true;
    }
}
