package com.example.receptor.service;

import com.example.receptor.model.Domicilio;
import com.example.receptor.model.Medicion;
import com.example.receptor.model.response.ConsumoResponse;
import com.example.receptor.repository.MedicionesRepository;
import com.example.receptor.utils.EntityURLBuilder;
import com.example.receptor.utils.PostResponse;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MedicionesService {

    private MedicionesRepository medicionesRepository;
    private DomicilioService domicilioService;

    private static final String MEDICION_PATH = "cliente";

    @Autowired
    public MedicionesService(MedicionesRepository medicionesRepository, DomicilioService domicilioService) {
        this.medicionesRepository = medicionesRepository;
        this.domicilioService = domicilioService;
    }

    public PostResponse addMedicion(Medicion medicion) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime dateMedicion = LocalDateTime.of(today.getYear(), today.getMonth(), 1, today.getHour(), today.getMinute(), today.getSecond());

        // HAGO QUE ALGUNAS MEDICIONES TENGAN EL MES QUE SE PIDE Y OTRAS EL MES SIGUIENTE PARA PODER HACER LOS CÁLCULOS.
        if (RandomUtils.nextBoolean()) {
            medicion.setFechaHora(dateMedicion);
        } else {
            medicion.setFechaHora(dateMedicion.minusMonths(1));
        }

        final Medicion medicionSaved = medicionesRepository.save(medicion);

        return PostResponse.builder()
                .status(HttpStatus.CREATED)
                .url(EntityURLBuilder.buildURL(MEDICION_PATH, medicionSaved.getId().toString()))
                .build();
    }

    public void addMedicionesFromMedidores(List<Medicion> medicionList) {
        medicionesRepository.saveAll(medicionList);
    }

    public List<Medicion> getConsumos(Integer idMedidor, Integer mes, Integer anio) {
        LocalDateTime startDateTime = LocalDateTime.of(anio, mes, 1, 00, 00, 00);
        LocalDateTime endDateTime = LocalDateTime.of(anio, mes + 1, 1, 00, 00, 00);

        Medicion consumoInicial = medicionesRepository.findByIdMedidorAndFechaHoraGreaterThanEqual(idMedidor, startDateTime).get(0);
        Medicion consumoFinal = medicionesRepository.findByIdMedidorAndFechaHoraGreaterThanEqual(idMedidor, endDateTime).get(0);

        return new ArrayList<>(Arrays.asList(consumoInicial, consumoFinal));
    }

    public Medicion getMedicion(Integer medicionId) {
        return medicionesRepository.findById(medicionId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El domicilio no se encontro."));
    }

    public List<Medicion> getMedicionesEntreFechas(Integer domicilioId, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        Domicilio domicilio = domicilioService.getDomicilioById(domicilioId);
        List<Medicion> medicionesEntreFechas = medicionesRepository.findDatesOfMedicionesByMedidorId(domicilio.getMedidorId(), fechaInicial, fechaFinal);
        if(medicionesEntreFechas.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return medicionesEntreFechas;
    }

    public void setConsumosFacturados(LocalDateTime fechaInicial, LocalDateTime fechaFinal, Integer medidorId){
        List<Medicion> consumosAFacturar = medicionesRepository.findDatesOfMedicionesByMedidorId(medidorId, fechaInicial, fechaFinal);
        for (Medicion medicion :
                consumosAFacturar) {
            medicion.setFacturado(true);
            medicionesRepository.save(medicion);
        }
    }

    public ConsumoResponse getConsumoEntreFechas(Integer domicilioId, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
        Domicilio domicilio = domicilioService.getDomicilioById(domicilioId);
        List<Medicion> medicionesEntreFechas = medicionesRepository.findDatesOfMedicionesByMedidorId(domicilio.getMedidorId(), fechaInicial, fechaFinal);

        if(medicionesEntreFechas.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        Double consumoKwh = medicionesEntreFechas.get(medicionesEntreFechas.size() - 1).getConsumo() - medicionesEntreFechas.get(0).getConsumo();
        Double precio = consumoKwh * domicilio.getTarifa().getValorTarifa();
        return new ConsumoResponse(consumoKwh, precio);
    }

}
