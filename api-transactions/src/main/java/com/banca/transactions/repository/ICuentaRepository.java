package com.banca.transactions.repository;

import com.banca.utils.db.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ICuentaRepository extends JpaRepository<Cuenta, Integer> {
    List<Cuenta> findAllByCliente_Id(Integer clienteId);
    Collection<Cuenta> findAllByCliente_Usuario(String cliente);
}