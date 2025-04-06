package com.dgitalhouse.integradorBackend.DTO.userDTOS;

import com.dgitalhouse.integradorBackend.entity.enums.Rol;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRol {
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
