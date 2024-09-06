package edu.es.eoi.projecteoi.DTOs.usuarioDTOs;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    private int idUsuario;

    private String nombreUsuario;

    private String password;
}
