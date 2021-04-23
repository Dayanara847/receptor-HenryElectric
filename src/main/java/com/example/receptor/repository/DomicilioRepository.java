package com.example.receptor.repository;

import com.example.receptor.model.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM domicilio WHERE cliente_id = :clienteId")
    List<Domicilio> findByClienteId(Integer clienteId);

}
