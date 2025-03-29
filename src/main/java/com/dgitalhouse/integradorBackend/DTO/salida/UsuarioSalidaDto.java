package com.dgitalhouse.integradorBackend.DTO.salida;

public record UsuarioSalidaDto(
    Long id,
    String nombre,
    String apellido,
    String email,
    String telefono,
    String direccion
    //String password
) {


}
