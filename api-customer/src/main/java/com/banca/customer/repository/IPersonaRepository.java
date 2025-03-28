package com.banca.customer.repository;

import com.banca.utils.db.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
}