package com.example.receptor.model;

import com.example.receptor.enums.Tarifa;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String direccion;

    private Tarifa tarifa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    private Integer medidorId;

}
