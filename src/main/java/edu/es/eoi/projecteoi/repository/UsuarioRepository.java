package edu.es.eoi.projecteoi.repository;

import edu.es.eoi.projecteoi.DTOs.usuarioDTOs.UsuarioDTO;
import edu.es.eoi.projecteoi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    public UsuarioDTO findByNombreUsuarioAndPassword(String nombreUsuario, String password);
}
