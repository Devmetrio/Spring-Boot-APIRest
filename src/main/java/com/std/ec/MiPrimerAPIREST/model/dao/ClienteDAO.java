package com.std.ec.MiPrimerAPIREST.model.dao;

import com.std.ec.MiPrimerAPIREST.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDAO extends CrudRepository<Cliente, Integer> {

}
