package com.example.receptor.repository;

import com.example.receptor.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM cliente WHERE deleted = :deleted")
    List<Cliente> findAllClientesActivos(Boolean deleted);

}
