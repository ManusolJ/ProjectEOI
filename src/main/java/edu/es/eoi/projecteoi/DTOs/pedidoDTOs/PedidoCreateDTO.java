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
public class PedidoCreateDTO {

    private int idUsuario;

    private String nombrePedido;

    private Date fechaPedido;

    private List<ArticuloPedidoDTO> articulos;
}
