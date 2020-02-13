package com.prueba.app.gml.models.dao;

import com.prueba.app.gml.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}