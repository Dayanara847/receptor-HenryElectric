package com.example.receptor.converter;

import com.example.receptor.model.Factura;
import com.example.receptor.model.dto.FacturaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FacturaToFacturaDTOConverter implements Converter<Factura, FacturaDTO> {

    private final ModelMapper modelMapper;

    public FacturaToFacturaDTOConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FacturaDTO convert(Factura source) {

        Map factura = new HashMap<String, Object>();

        factura.put("cliente", source.getCliente().getNombre() + " " + source.getCliente().getApellido());
        factura.put("domicilio", source.getDomicilio().getDireccion());
        factura.put("nroSerieMedidor", source.getNroSerieMedidor());
        factura.put("medicionInicial", source.getMedicionInicial());
        factura.put("medicionFinal", source.getMedicionFinal());
        factura.put("fechaMedicionInicial", source.getFechaMedicionInicial());
        factura.put("fechaMedicionFinal", source.getFechaMedicionFinal());
        factura.put("consumoTotal", source.getConsumoTotal());
        factura.put("tipoTarifa", source.getDomicilio().getTarifa());
        factura.put("totalPagar", source.getTotalPagar());
        factura.put("pagada", source.getPagada());

        return modelMapper.map(factura, FacturaDTO.class);
    }

}
