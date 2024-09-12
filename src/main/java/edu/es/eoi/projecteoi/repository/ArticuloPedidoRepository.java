package edu.es.eoi.projecteoi.repository;

import edu.es.eoi.projecteoi.DTOs.articuloDTOs.ArticuloPedidoDTO;
import edu.es.eoi.projecteoi.entity.ArticuloPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloPedidoRepository extends JpaRepository<ArticuloPedido, Integer> {

    ArticuloPedidoDTO findArticuloPedidoByPedidoIs(Integer pedido);
}
