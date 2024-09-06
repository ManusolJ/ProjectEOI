package edu.es.eoi.projecteoi.service;

import edu.es.eoi.projecteoi.DTOs.usuarioDTOs.UsuarioCreateAndLoginDTO;
import edu.es.eoi.projecteoi.DTOs.usuarioDTOs.UsuarioDTO;
import edu.es.eoi.projecteoi.entity.Usuario;
import edu.es.eoi.projecteoi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> getAllUsuarios(){

        List<Usuario> usuarios = usuarioRepository.findAll();

        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (Usuario usuario : usuarios){
            UsuarioDTO usuarioDTO = new UsuarioDTO();

            usuarioDTO.setIdUsuario(usuario.getIdUsuario());
            usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
            usuarioDTO.setPassword(usuario.getPassword());

            usuarioDTOS.add(usuarioDTO);
        }

        return usuarioDTOS;
    }

    public UsuarioDTO getUsuarioById(String id){

        int idUsario;

        try{
            idUsario = Integer.parseInt(id);
        }catch (NumberFormatException e){
            return null;
        }

        Usuario usuario = usuarioRepository.findById(idUsario).get();

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
        usuarioDTO.setPassword(usuario.getPassword());

        return usuarioDTO;
    }

    public void createUsuario(UsuarioCreateAndLoginDTO usuarioDTO){

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setPassword(usuarioDTO.getPassword());

        usuarioRepository.save(usuario);
    }

    public void updateUsuario(UsuarioDTO usuarioDTO){

        Usuario usuario = usuarioRepository.findById(usuarioDTO.getIdUsuario()).get();

        usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
        usuario.setPassword(usuarioDTO.getPassword());

        usuarioRepository.save(usuario);
    }

    public UsuarioDTO loginUsuario(UsuarioCreateAndLoginDTO usuarioDTO){

       Usuario usuario = usuarioRepository.findByNombreUsuarioAndPassword(usuarioDTO.getNombreUsuario(), usuarioDTO.getPassword());

       UsuarioDTO usarioDTO = new UsuarioDTO();

       if(usuario != null){
           usarioDTO.setIdUsuario(usuario.getIdUsuario());
           usarioDTO.setNombreUsuario(usuario.getNombreUsuario());
           usarioDTO.setPassword(usuario.getPassword());
           return usarioDTO;
       }else{

           return null;
       }
    }
}
