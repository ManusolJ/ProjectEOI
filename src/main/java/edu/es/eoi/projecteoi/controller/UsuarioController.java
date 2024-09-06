package edu.es.eoi.projecteoi.controller;

import edu.es.eoi.projecteoi.DTOs.usuarioDTOs.UsuarioCreateAndLoginDTO;
import edu.es.eoi.projecteoi.DTOs.usuarioDTOs.UsuarioDTO;
import edu.es.eoi.projecteoi.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marketplace/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getUsuarios() {

        return new ResponseEntity<List<UsuarioDTO>>(usuarioService.getAllUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable String id) {

        return new ResponseEntity<UsuarioDTO>(usuarioService.getUsuarioById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addUsuario(@RequestBody UsuarioCreateAndLoginDTO usuarioDTO) {
        usuarioService.createUsuario(usuarioDTO);

        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUsuario(@PathVariable String id, @RequestBody UsuarioDTO usuarioDTO) {

        usuarioService.updateUsuario(usuarioDTO);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioCreateAndLoginDTO> login(@RequestBody UsuarioCreateAndLoginDTO usuarioDTO) {

        if(usuarioService.loginUsuario(usuarioDTO) != null){

            return new ResponseEntity<UsuarioCreateAndLoginDTO>(usuarioDTO, HttpStatus.OK);
        }else {

            return new ResponseEntity<UsuarioCreateAndLoginDTO>(HttpStatus.UNAUTHORIZED);
        }
    }
}
