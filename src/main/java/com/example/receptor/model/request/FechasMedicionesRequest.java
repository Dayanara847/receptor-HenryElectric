package com.example.receptor.model.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FechasMedicionesRequest {

    private LocalDateTime fechaInicial;

    private LocalDateTime fechaFinal;

}
