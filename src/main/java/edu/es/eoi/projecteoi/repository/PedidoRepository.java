package edu.es.eoi.projecteoi.repository;

import edu.es.eoi.projecteoi.entity.Pedido;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    //PAAAIN
    @Query("SELECT p FROM Pedido p " +
            "JOIN ArticuloPedido ap ON p.idPedido = ap.pedido.idPedido " +
            "JOIN Articulo a ON ap.articulo.idArticulo = a.idArticulo " +
            "GROUP BY p.idPedido, p.nombrePedido, p.fechaPedido " +
            "ORDER BY SUM(a.precio * ap.cantidadPedida) DESC")
    List<Pedido> findTop3PedidosMasCaros(Pageable pageable);
}
