package com.example.receptor.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsumoResponse {

    private Double consumoKwh;
    private Double precio;

}
