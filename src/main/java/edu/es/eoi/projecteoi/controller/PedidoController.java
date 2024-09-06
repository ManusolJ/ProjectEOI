package edu.es.eoi.projecteoi.controller;

import edu.es.eoi.projecteoi.DTOs.pedidoDTOs.PedidoCreateDTO;
import edu.es.eoi.projecteoi.DTOs.pedidoDTOs.PedidoDTO;
import edu.es.eoi.projecteoi.service.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("marketplace/pedidos")
public class PedidoController {

    @Autowired
    private PedidoServiceImpl pedidoService;

    @GetMapping("/{nombreparcial}/nombre")
    public ResponseEntity<List<PedidoDTO>> getPedidosByNombreParcial(@PathVariable String nombreparcial) {

        return new ResponseEntity<List<PedidoDTO>>(pedidoService.getAllPedidosByNombre(nombreparcial), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable String id) {

        return new ResponseEntity<PedidoDTO>(pedidoService.getPedidoById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addPedido(@RequestBody PedidoCreateDTO pedidoDTO) {

        pedidoService.createPedido(pedidoDTO);

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePedido(@RequestBody PedidoDTO pedidoDTO) {

        pedidoService.updatePedido(pedidoDTO);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable String id) {

        pedidoService.deletePedido(id);

        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
