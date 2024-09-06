package edu.es.eoi.projecteoi.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ArticuloPedido")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticuloPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRegistroArticuloPedido;

    @ManyToOne
    @JoinColumn(name = "idPedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idArticulo")
    private Articulo articulo;

    @Column
    private int cantidadPedida;
}
