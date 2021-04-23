package com.example.receptor.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MedidorResponse {
    private Integer id;

    private Double consumoTotal;
}
