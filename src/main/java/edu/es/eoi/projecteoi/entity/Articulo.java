package edu.es.eoi.projecteoi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Articulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idArticulo;

    @Column
    @Size(max = 100)
    private String nombreArticulo;

    @Column
    private double precio;

    @Column
    private int stock;
}
