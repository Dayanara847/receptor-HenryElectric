package com.example.receptor.controller;

import com.example.receptor.converter.FacturaToFacturaDTOConverter;
import com.example.receptor.model.Factura;
import com.example.receptor.model.dto.FacturaDTO;
import com.example.receptor.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private FacturaToFacturaDTOConverter facturaToFacturaDTOConvert;

    @GetMapping
    public List<FacturaDTO> getAllFacturas() {
        return conversionService.convert(facturaService.getAll(), List.class);
    }

    @GetMapping("/{id}")
    public FacturaDTO getFacturaById(@PathVariable Integer id) {
        return conversionService.convert(facturaService.getById(id), FacturaDTO.class);
    }

    @PostMapping("/{domicilioId}")
    public Factura addFactura(@PathVariable Integer domicilioId) {
        return facturaService.addFactura(domicilioId);
    }

    @GetMapping("/impagas/{clienteId}")
    public List<Factura> getFacturasImpagas(@PathVariable Integer clienteId) {
        return facturaService.getFacturasImpagas(clienteId);
    }

}
