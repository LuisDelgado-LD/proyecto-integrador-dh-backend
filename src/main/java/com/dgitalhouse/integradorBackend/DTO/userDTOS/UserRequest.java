package com.dgitalhouse.integradorBackend.DTO.userDTOS;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotNull
    private String nombre;

    @NotNull
    private String apellido;
    @NotNull
    private String email;
    @NotNull
    private String telefono;
    @NotNull
    private String direccion;
    @NotNull
    private String password;
}
