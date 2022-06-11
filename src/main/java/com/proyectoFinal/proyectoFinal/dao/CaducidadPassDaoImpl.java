package com.proyectoFinal.proyectoFinal.dao;


import com.proyectoFinal.proyectoFinal.model.CaducidadPassword;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional
public class CaducidadPassDaoImpl implements CaducidadPassDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<CaducidadPassword> getCaducidad() {
        String query = "FROM CaducidadPassword ";

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void registrar(CaducidadPassword caducidad) {
        entityManager.merge(caducidad);
    }

    @Override
    public int modificarCaducidad(String username) {

        CaducidadPassword caducidad = entityManager.find(CaducidadPassword.class, username);

        int dias = caducidad.getDias();
        return dias;
    }
}
