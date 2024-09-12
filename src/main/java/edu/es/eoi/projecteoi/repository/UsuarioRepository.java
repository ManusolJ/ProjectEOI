package edu.es.eoi.projecteoi.repository;

import edu.es.eoi.projecteoi.DTOs.usuarioDTOs.UsuarioDTO;
import edu.es.eoi.projecteoi.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    Usuario findByNombreUsuarioAndPassword(String nombreUsuario, String password);

    @Query("SELECT u FROM Usuario u JOIN Pedido p ON u.idUsuario = p.idPedido JOIN ArticuloPedido ap ON p.idPedido = ap.pedido.idPedido GROUP BY u.idUsuario ORDER BY SUM(ap.articulo.precio) DESC")
    List<Usuario> ListarUsuariosPorTotalGastado();
}
