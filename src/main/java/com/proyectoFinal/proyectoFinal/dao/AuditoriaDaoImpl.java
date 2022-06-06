package com.proyectoFinal.proyectoFinal.dao;

import com.proyectoFinal.proyectoFinal.model.Auditoria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional
public class AuditoriaDaoImpl implements AuditoriaDao{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Auditoria> getHistorial() {
        String query = "FROM Auditoria";

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void registrar(Auditoria auditoria) {
        entityManager.merge(auditoria);
    }
}
