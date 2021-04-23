package com.example.receptor.repository;

import com.example.receptor.model.Cliente;
import com.example.receptor.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM factura WHERE pagada = 0 AND cliente_id = :clienteId")
    List<Factura> findAllFacturasImpagas(Integer clienteId);

    @Query(nativeQuery = true, value = "SELECT * FROM factura WHERE cliente_id = :clienteId")
    List<Factura> findByClienteId(Integer clienteId);
}
