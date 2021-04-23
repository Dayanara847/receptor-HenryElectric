package com.example.receptor.controller;

import com.example.receptor.model.request.FechasMedicionesRequest;
import com.example.receptor.model.Medicion;
import com.example.receptor.model.response.ConsumoResponse;
import com.example.receptor.service.MedicionesService;
import com.example.receptor.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicion")
public class MedicionesController {

    @Autowired
    private MedicionesService medicionesService;

    @PostMapping
    public PostResponse addMedicion(@RequestBody Medicion medicion) {
        return medicionesService.addMedicion(medicion);
    }

    @PostMapping("/allmediciones")
    public void addMedicionesFromMedidores(@RequestBody List<Medicion> medicionList) {
        System.out.println(medicionList);
        medicionesService.addMedicionesFromMedidores(medicionList);
    }

    @GetMapping("/medicionesentrefechas/{domicilioId}")
    public List<Medicion> getMedicionesEntreFechas(@RequestBody  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) FechasMedicionesRequest fechasMediciones, @PathVariable Integer domicilioId) {
        return medicionesService.getMedicionesEntreFechas(domicilioId, fechasMediciones.getFechaInicial(), fechasMediciones.getFechaFinal());
    }

    @GetMapping("/consumoentrefechas/{domicilioId}")
    public ConsumoResponse getConsumoEntreFechas(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) FechasMedicionesRequest fechasMediciones, @PathVariable Integer domicilioId) {
        return medicionesService.getConsumoEntreFechas(domicilioId, fechasMediciones.getFechaInicial(), fechasMediciones.getFechaFinal());
    }

}
