package edu.es.eoi.projecteoi.DTOs.articuloDTOs;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticuloDTO {

    private int idArticulo;

    private String nombreArticulo;

    private double precio;

    private int stock;
}
