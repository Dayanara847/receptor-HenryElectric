package com.example.receptor.controller;

import com.example.receptor.model.Cliente;
import com.example.receptor.service.ClienteService;
import com.example.receptor.utils.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id){
        return clienteService.getClienteById(id);
    }

    @GetMapping
    public List<Cliente> getAll(){
        return clienteService.getAll();
    }

    @GetMapping("/lista/topconsumidores")
    public List<Map<String, Object>> getTopConsumidores() { return clienteService.getTopConsumidores(); }

    @PostMapping
    public PostResponse addCliente(@RequestBody Cliente cliente){
        return clienteService.addCliente(cliente);
    }

    @PutMapping()
    private void editCliente(@RequestBody Cliente cliente) {
         clienteService.editCliente(cliente);
    }

    @DeleteMapping("/{id}")
    private Cliente deletedById(@PathVariable Integer id){
        return clienteService.deleteById(id);
    }
}
