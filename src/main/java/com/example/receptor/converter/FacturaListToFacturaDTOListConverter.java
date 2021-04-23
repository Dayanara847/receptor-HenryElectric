package com.example.receptor.converter;

import com.example.receptor.model.Factura;
import com.example.receptor.model.dto.FacturaDTO;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Component
@Setter
public class FacturaListToFacturaDTOListConverter implements Converter<List<Factura>, List<FacturaDTO>> {

    private final ModelMapper modelMapper;

    public FacturaListToFacturaDTOListConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FacturaDTO> convert(final List<Factura> sourceList) {
        List<Map> facturas = new ArrayList<>();
        Map factura = new HashMap<String, Object>();
        for (Factura source:
             sourceList) {
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
            facturas.add(factura);
        }
        return modelMapper.map(facturas, new TypeToken<List<FacturaDTO>>() {
        }.getType());
    }
}
