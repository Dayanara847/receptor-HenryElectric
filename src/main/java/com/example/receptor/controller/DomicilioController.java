package com.example.receptor.controller;

import com.example.receptor.model.Domicilio;
import com.example.receptor.service.DomicilioService;
import com.example.receptor.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/domicilio")
public class DomicilioController {

    @Autowired
    private DomicilioService domicilioService;

    @GetMapping
    public List<Domicilio> getAll() { return domicilioService.getAll();}

    @GetMapping("/{id}")
    public Domicilio getDomicilioById(@PathVariable Integer id){
        return domicilioService.getDomicilioById(id);
    }

    @PostMapping
    public void addDomicilio(@RequestBody Domicilio domicilio){
        domicilioService.addDomicilio(domicilio);
    }

    @PutMapping
    public void editDomicilio(@RequestBody Domicilio domicilio){
        domicilioService.editDomicilio(domicilio);
    }

    @DeleteMapping("/{id}")
    public void deleteDomicilioById(@PathVariable Integer id){
        domicilioService.deleteDomicilioById(id);
    }

    @PutMapping("/{domicilioId}/cliente/{clienteId}")
    public void addCliente(@PathVariable Integer clienteId, @PathVariable Integer domicilioId ) {
        domicilioService.addCliente(clienteId, domicilioId);
    }
}
