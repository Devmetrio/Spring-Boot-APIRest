package com.std.ec.MiPrimerAPIREST.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class ClienteDTO implements Serializable {
    private Integer IdCliente;
    private String nombre;
    private String apellido;
    private String correo;
    private Date fechaRegistro;
}
