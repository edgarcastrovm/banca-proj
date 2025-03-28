package com.banca.transactions.repository;

import com.banca.utils.db.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICuentaRepository extends JpaRepository<Cuenta, Integer> {
}