package edu.es.eoi.projecteoi.DTOs.articuloDTOs;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticuloPedidoDTO {

    private int idArticulo;

    private int cantidad;
}
