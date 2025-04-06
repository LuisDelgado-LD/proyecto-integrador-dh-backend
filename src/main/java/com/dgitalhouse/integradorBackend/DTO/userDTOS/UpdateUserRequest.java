package com.dgitalhouse.integradorBackend.DTO.userDTOS;

import com.dgitalhouse.integradorBackend.entity.enums.Rol;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {

    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;


}

