package ifpe.dsc.cartorioprotesto.service;

import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.SUPPORTS;
import static javax.ejb.TransactionAttributeType.NOT_SUPPORTED;
import static javax.ejb.TransactionManagementType.CONTAINER;
import static javax.persistence.PersistenceContextType.TRANSACTION;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@TransactionManagement(CONTAINER)
@TransactionAttribute(REQUIRED)
public abstract class Servico<T> {

    @PersistenceContext(name = "CartorioProtesto", type = TRANSACTION)
    protected EntityManager entityManager;
    protected Class<T> classe;
    
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
}