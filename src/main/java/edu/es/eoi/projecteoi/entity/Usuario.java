package edu.es.eoi.projecteoi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column
    @Size(max = 100)
    private String nombreUsuario;

    @Column
    @Size(max = 100)
    private String password;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedido;
}
