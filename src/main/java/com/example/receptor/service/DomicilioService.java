package com.example.receptor.service;

import com.example.receptor.model.Cliente;
import com.example.receptor.model.Domicilio;
import com.example.receptor.repository.DomicilioRepository;
import com.example.receptor.utils.UpdateFields;
import org.springframework.beans.factory.annotation.Autowired;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DomicilioService {

    private DomicilioRepository domicilioRepository;
    private ClienteService clienteService;

    @Autowired
    public DomicilioService(DomicilioRepository domicilioRepository, @Lazy ClienteService clienteService) {
        this.domicilioRepository = domicilioRepository;
        this.clienteService = clienteService;
    }

    public List<Domicilio> getAll() {
        List<Domicilio> domicilios = domicilioRepository.findAll();
        if (domicilios.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return domicilios;
    }

    public Domicilio getDomicilioById(Integer id) {
        return domicilioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El domicilio no se encontro."));
    }

    public List<Domicilio> getDomicilioByCliente(Integer idCliente){
        List<Domicilio> domicilios = domicilioRepository.findByClienteId(idCliente);
        if (domicilios.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        return domicilios;
    }

    public void addDomicilio(Domicilio domicilio) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/medidor/asignado/" + domicilio.getMedidorId() + "/1"))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        try{
            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Domicilio domicilioSaved = domicilioRepository.save(domicilio);
    }

    public void editDomicilio(Domicilio domicilio) {
        try {
            Domicilio domicilioAEditar = domicilioRepository.findById(domicilio.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El domicilio no fue encontrado"));
            domicilioRepository.save((Domicilio) UpdateFields.updateObject(domicilio, domicilioAEditar));
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getCause().toString());
        }
    }

    public void deleteDomicilioById(Integer id) {
        try {
            domicilioRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El domicilio no fue encontrado.");
        }
    }

    public void addCliente(Integer clienteId, Integer domicilioId) {
        Domicilio domicilio = domicilioRepository.findById(domicilioId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El domicilio no fue encontrado."));
        Cliente cliente = clienteService.getClienteById(clienteId);

        domicilio.setCliente(cliente);
        domicilioRepository.save(domicilio);
    }

}
