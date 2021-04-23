package com.example.receptor.service;

import com.example.receptor.model.Domicilio;
import com.example.receptor.model.Factura;
import com.example.receptor.model.Medicion;
import com.example.receptor.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FacturaService {

    private FacturaRepository facturaRepository;
    private DomicilioService domicilioService;
    private MedicionesService medicionesService;

    @Autowired
    public FacturaService(FacturaRepository facturaRepository, DomicilioService domicilioService, MedicionesService medicionesService) {
        this.facturaRepository = facturaRepository;
        this.domicilioService = domicilioService;
        this.medicionesService = medicionesService;
    }

    public List<Factura> getAll(){
        return facturaRepository.findAll();
    }

    public Factura getById(Integer id){
        return facturaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Factura> getFacturasByClienteId(Integer clienteId){
        return facturaRepository.findByClienteId(clienteId);
    }

    @Scheduled(cron = "0 0 8 1 * *")
    public void createFacturas() {
        List<Domicilio> domicilios = domicilioService.getAll();

        for (Domicilio domicilio :
                domicilios) {
            addFactura(domicilio.getId());
        }
    }

    public Factura addFactura(Integer domicilioId) {
        Domicilio domicilio = domicilioService.getDomicilioById(domicilioId);

        // TODO : La idea es que no cree facturas de domicilios que no tienen clientes asociados, y por lo tanto no generan gastos (medidor asignado = false.
        if(domicilio.getCliente() == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }

        LocalDateTime now = LocalDateTime.now();

        List<Medicion> consumos = medicionesService.getConsumos(domicilio.getMedidorId(), now.getMonthValue() - 1, now.getYear());
        medicionesService.setConsumosFacturados(consumos.get(0).getFechaHora(), consumos.get(1).getFechaHora(), domicilio.getMedidorId());

        Double consumoTotal = consumos.get(1).getConsumo() - consumos.get(0).getConsumo();
        Double tarifa = domicilio.getTarifa().getValorTarifa();
        Double totalAPagar = tarifa * consumoTotal;

        Factura factura = new Factura();

        factura.setDomicilio(domicilio);
        factura.setCliente(domicilio.getCliente());
        factura.setNroSerieMedidor(consumos.get(0).getNroSerieMedidor());
        factura.setMedicionInicial(consumos.get(0).getConsumo());
        factura.setMedicionFinal(consumos.get(1).getConsumo());
        factura.setFechaMedicionInicial(consumos.get(0).getFechaHora());
        factura.setFechaMedicionFinal(consumos.get(1).getFechaHora());
        factura.setConsumoTotal(consumoTotal);
        factura.setTotalPagar(totalAPagar);
        factura.setPagada(false);

        return facturaRepository.save(factura);
    }

    public List<Factura> getFacturasImpagas(Integer clienteId) {
        List<Factura> facturasImpagas = facturaRepository.findAllFacturasImpagas(clienteId);
        if (facturasImpagas.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return facturasImpagas;
    }

}
