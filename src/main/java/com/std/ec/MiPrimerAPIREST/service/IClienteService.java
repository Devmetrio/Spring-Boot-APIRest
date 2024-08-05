package com.std.ec.MiPrimerAPIREST.service;


import com.std.ec.MiPrimerAPIREST.model.dto.ClienteDTO;
import com.std.ec.MiPrimerAPIREST.model.entity.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> listAll();
    Cliente save(ClienteDTO cliente);
    Cliente findById(Integer id);
    void delete(Cliente cliente);
    boolean existsById(Integer id);
}
