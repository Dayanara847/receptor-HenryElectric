package com.example.receptor.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FacturaDTO {

    private String cliente;
    private String domicilio;
    private String nroSerieMedidor;
    private Double medicionInicial;
    private Double medicionFinal;
    private LocalDateTime fechaMedicionInicial;
    private LocalDateTime fechaMedicionFinal;
    private Double consumoTotal;
    private String tipoTarifa;
    private Double totalPagar;
    private Boolean pagada;

}
