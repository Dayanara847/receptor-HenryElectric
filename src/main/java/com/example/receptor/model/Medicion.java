package com.example.receptor.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Medicion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer idMedidor;

    @NotNull
    private String nroSerieMedidor;

    @NotNull
    private LocalDateTime fechaHora;

    @NotNull
    private Double consumo;

    private Boolean facturado = false;

}
