package com.example.receptor.enums;

public enum Tarifa {

    RESIDENCIAL(30.41),
    COMERCIAL(149.59);

    private final Double valorTarifa;

    Tarifa(Double valorTarifa) {
        this.valorTarifa = valorTarifa;
    }

    public Double getValorTarifa() {
        return valorTarifa;
    }

}

