package edu.es.eoi.projecteoi.controller;

import edu.es.eoi.projecteoi.DTOs.articuloDTOs.ArticuloDTO;
import edu.es.eoi.projecteoi.DTOs.pedidoDTOs.PedidoDTO;
import edu.es.eoi.projecteoi.DTOs.usuarioDTOs.UsuarioDTO;
import edu.es.eoi.projecteoi.service.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    ReportServiceImpl reportService;

    @GetMapping("/articulosMasVendidos")
    public ResponseEntity<List<ArticuloDTO>> getArticulos() {

        return new ResponseEntity<List<ArticuloDTO>>(reportService.findArticulosMasVendidos(), HttpStatus.OK);
    }

    @GetMapping("/mejoresPedidos")
    public ResponseEntity<List<PedidoDTO>> getPedidos() {

        return new ResponseEntity<List<PedidoDTO>>(reportService.findMejoresPedidos(), HttpStatus.OK);
    }

    @GetMapping("/mejoresUsuarios")
    public ResponseEntity<List<UsuarioDTO>> getUsuarios() {

        return new ResponseEntity<List<UsuarioDTO>>(reportService.findUsuariosPorTotalGastado(), HttpStatus.OK);
    }
}
