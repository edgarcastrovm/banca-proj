package com.banca.transactions.repository;

import com.banca.utils.db.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovimientoRepository extends JpaRepository<Movimiento, Integer> {
}