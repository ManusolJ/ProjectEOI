package edu.es.eoi.projecteoi.controller;

import edu.es.eoi.projecteoi.DTOs.articuloDTOs.ArticuloCreateDTO;
import edu.es.eoi.projecteoi.DTOs.articuloDTOs.ArticuloDTO;
import edu.es.eoi.projecteoi.service.ArticuloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marketplace/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloServiceImpl articuloService;

    @GetMapping("/{nombreParcial}/nombre")
    public ResponseEntity<List<ArticuloDTO>> getAllArticulosByNombreParcial(@PathVariable String nombreParcial) {

        return new ResponseEntity<List<ArticuloDTO>>(articuloService.getAllArticulosByNombre(nombreParcial), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticuloDTO> getArticuloById(@PathVariable String id) {

        return new ResponseEntity<ArticuloDTO>(articuloService.getArticuloById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addArticulo(@RequestBody ArticuloCreateDTO articuloDTO) {

        articuloService.createArticulo(articuloDTO);

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArticulo(@RequestBody ArticuloDTO articuloDTO) {

        articuloService.updateArticulo(articuloDTO);

        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
