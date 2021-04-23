package com.example.receptor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 7, max = 8, message = "El dni no es válido")
    private String dni;

    @NotNull(message = "El campo Nombre no puede ser vacío.")
    private String nombre;

    @NotNull(message = "El campo Apellido no puede ser vacío.")
    private String apellido;

    private Boolean deleted = false;

}

