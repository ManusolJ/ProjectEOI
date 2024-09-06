package edu.es.eoi.projecteoi.DTOs.pedidoDTOs;

import edu.es.eoi.projecteoi.DTOs.articuloDTOs.ArticuloPedidoDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO {

    private int idPedido;

    private Date fechaPedido;

    private String nombrePedido;

    private List<ArticuloPedidoDTO> articulos;
}
