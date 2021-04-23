package com.example.receptor.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "domicilioId")
    private Domicilio domicilio;

    @NotNull
    private String nroSerieMedidor;

    private Double medicionInicial;

    private Double medicionFinal;

    private LocalDateTime fechaMedicionInicial;

    private LocalDateTime fechaMedicionFinal;

    private Double consumoTotal;

    private Double totalPagar;

    private Boolean pagada;

}
