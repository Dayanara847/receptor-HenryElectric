package com.example.receptor.repository;

import com.example.receptor.model.Medicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicionesRepository extends JpaRepository<Medicion,  Integer> {

    List<Medicion> findByIdMedidorAndFechaHoraGreaterThanEqual(Integer idMedidor, LocalDateTime fecha);

    @Query(nativeQuery = true, value = "SELECT * FROM medicion WHERE id_medidor = :idMedidor AND fecha_hora BETWEEN :fechaInicial AND :fechaFinal")
    List<Medicion> findDatesOfMedicionesByMedidorId(Integer idMedidor, LocalDateTime fechaInicial, LocalDateTime fechaFinal);

}












//    @Query(nativeQuery = true, value = "SELECT * FROM medicion WHERE fecha_hora >= :fecha AND id_medidor = :medidorId LIMIT 1")
//    Medicion findMedicionByDate(Integer medidorId, LocalDateTime fecha);
