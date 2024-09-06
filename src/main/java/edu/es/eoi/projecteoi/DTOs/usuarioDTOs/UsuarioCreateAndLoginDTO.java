package edu.es.eoi.projecteoi.DTOs.usuarioDTOs;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioCreateAndLoginDTO {

    private String nombreUsuario;

    private String password;
}
