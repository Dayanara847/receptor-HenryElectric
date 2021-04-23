package com.example.receptor.service;

import com.example.receptor.model.Cliente;
import com.example.receptor.model.Domicilio;
import com.example.receptor.model.Factura;
import com.example.receptor.repository.ClienteRepository;
import com.example.receptor.utils.EntityURLBuilder;
import com.example.receptor.utils.PostResponse;
import com.example.receptor.utils.UpdateFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.Map.Entry;

@Service
public class ClienteService {

    private static final String CLIENTE_PATH = "cliente";

    private ClienteRepository clienteRepository;
    private FacturaService facturaService;
    private DomicilioService domicilioService;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, FacturaService facturaService, DomicilioService domicilioService) {
        this.clienteRepository = clienteRepository;
        this.facturaService = facturaService;
        this.domicilioService = domicilioService;
    }

    public PostResponse addCliente(Cliente cliente) {
        final Cliente savedCliente = clienteRepository.save(cliente);
        return PostResponse.builder()
                .status(HttpStatus.CREATED)
                .url(EntityURLBuilder.buildURL(CLIENTE_PATH, savedCliente.getId().toString()))
                .build();
    }

    public Cliente getClienteById(Integer id) {
        try {
            return clienteRepository.findById(id).orElseThrow(null);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Map<String, Object>> getTopConsumidores(){
        List<Cliente> clientes = this.getAll();
        Map<Integer, Double> map = new HashMap<>();
        for (Cliente cliente :
                clientes) {
            List<Factura> facturas = facturaService.getFacturasByClienteId(cliente.getId());
            Double consumoTotal = 0.0;
            if(facturas.size() != 0) {
                for (Factura factura :
                        facturas) {
                    consumoTotal += factura.getConsumoTotal();
                }
                map.put(cliente.getId(), consumoTotal);
            }
        }
        List<Entry<Integer, Double>> list = new ArrayList<>(map.entrySet());
        list.sort(Entry.comparingByValue(Comparator.reverseOrder()));

        List<Map<String, Object>> top10 = new ArrayList<>();
        for (int i = 0; i < 10 && i < list.size(); i++) {
            Integer idCliente = list.get(i).getKey();
            Double consumo = list.get(i).getValue();

            Map<String, Object> clienteMap = new HashMap<>();

            Cliente cliente = this.getClienteById(idCliente);
            clienteMap.put("id", cliente.getId());
            clienteMap.put("nombre", cliente.getApellido() + " " + cliente.getNombre());
            clienteMap.put("consumo", consumo);

            top10.add(clienteMap);
        }
        return top10;
    }

    public void editCliente(Cliente cliente) {
        try {
            Cliente clienteAEditar = clienteRepository.findById(cliente.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente no fue encontrado"));
            clienteRepository.save((Cliente) UpdateFields.updateObject(cliente, clienteAEditar));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getCause().toString());
        }
    }

    // TODO : Trae solo los clientes que no están "eliminados"
    public List<Cliente> getAll() {
        List<Cliente> clientes = clienteRepository.findAllClientesActivos(false);
        //List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.size() > 0) {
            return clientes;
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    // TODO : Elimina Cliente solo cambiando el atributo deleted a true. Desasigna el cliente en el domicilio y setea en medidor asignado a false.
    public Cliente deleteById(Integer id) {
        Cliente deletedCliente = clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<Domicilio> domiciliosCliente = domicilioService.getDomicilioByCliente(id);

        for (Domicilio domicilio:
             domiciliosCliente) {

            domicilio.setCliente(null);
            domicilioService.addDomicilio(domicilio);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8081/medidor/asignado/" + domicilio.getMedidorId() + "/0"))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            try{
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                deletedCliente.setDeleted(true);
                clienteRepository.save(deletedCliente);
            }catch(Exception e){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return deletedCliente;
    }
}
