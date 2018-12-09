package ifpe.dsc.cartorioprotesto.service;

import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionManagementType.CONTAINER;
import static javax.persistence.PersistenceContextType.TRANSACTION;

import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED)
public abstract class Servico<T> {

    @PersistenceContext(name = "CartorioProtesto", type = TRANSACTION)
    protected EntityManager entityManager;
    protected Class<T> classe;
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    protected List<T> getEntidades(String nomeQuery) {
        TypedQuery<T> query = entityManager.createNamedQuery(nomeQuery, classe);
        return query.getResultList();
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    protected List<T> getEntidades(String nomeQuery, Object[] parametros) {
        TypedQuery<T> query = entityManager.createNamedQuery(nomeQuery, classe);
        int i = 1;

        for (Object parametro : parametros) {
            query.setParameter(i, parametro);
            i++;
        }

        return query.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    protected T getEntidade(String nomeQuery, Object[] parametros) {
        TypedQuery<T> query = entityManager.createNamedQuery(nomeQuery, classe);

        int i = 1;
        for (Object parametro : parametros) {
            query.setParameter(i++, parametro);
        }

        return query.getSingleResult();
    }
}