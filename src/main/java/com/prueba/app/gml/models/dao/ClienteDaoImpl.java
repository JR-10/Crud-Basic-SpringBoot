package com.prueba.app.gml.models.dao;

import com.prueba.app.gml.models.entity.Cliente;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("clienteDaoJPA")  // marcar la clase con una persistencia de acceso a datos
public class ClienteDaoImpl implements IClienteDao {

    @PersistenceContext
    private EntityManager  em;// Manejar la clase de entidades, el ciclo de vida, la persiste las actualiza, elimina operaciones de BD


    /**
     * Listar Dao
     */
    @Override
    public List<Cliente> findAll() {
        return em.createQuery("from Cliente").getResultList();
    }

    /**
     * Guardar Dao
     * @param cliente
     */
    @Override
    public void save(Cliente cliente) {
        // Condicion para saber si corresponde al guardado
        if (cliente.getId() != null && cliente.getId() > 0){
            em.merge(cliente);
        } else {
            em.persist(cliente);
        }
    }


    /**
     * Editar Dao
     * @param id
     * @return
     */
    @Override
    public Cliente findOne(Long id) {
        return em.find(Cliente.class, id);
    }

    /**
     * Eliminar Dao
     * @param id
     */
    @Override
    public void delete(Long id) {
        Cliente cliente = findOne(id);
        em.remove(cliente);
    }
}
