package com.std.ec.MiPrimerAPIREST.service.impl;

import com.std.ec.MiPrimerAPIREST.model.dao.ClienteDAO;
import com.std.ec.MiPrimerAPIREST.model.dto.ClienteDTO;
import com.std.ec.MiPrimerAPIREST.model.entity.Cliente;
import com.std.ec.MiPrimerAPIREST.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteImpl implements IClienteService {
    @Autowired
    private ClienteDAO clienteDAO;

    @Override
    public List<Cliente> listAll() {
        return (List) clienteDAO.findAll();
    }

    @Transactional
    @Override
    public Cliente save(ClienteDTO clienteDTO) {
        Cliente cliente = Cliente.builder()
                .IdCliente(clienteDTO.getIdCliente())
                .nombre(clienteDTO.getNombre())
                .apellido(clienteDTO.getApellido())
                .fechaRegistro(clienteDTO.getFechaRegistro())
                .correo(clienteDTO.getCorreo())
                .build();
        return clienteDAO.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDAO.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDAO.delete(cliente);
    }

    @Override
    public boolean existsById(Integer id) {
        return clienteDAO.existsById(id);
    }
}
