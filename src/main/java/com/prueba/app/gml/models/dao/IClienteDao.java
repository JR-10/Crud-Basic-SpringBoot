package com.prueba.app.gml.models.dao;

import com.prueba.app.gml.models.entity.Cliente;

import java.util.List;

public interface IClienteDao {

    // retornar una lista de clientes
    public List<Cliente> findAll();

    // guardar
    public void save(Cliente cliente);

    // editar
    public  Cliente findOne(Long id);

    // eliminar
    public void delete(Long id);

}