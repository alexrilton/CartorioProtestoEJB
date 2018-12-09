package ifpe.dsc.cartorioprotesto.primeiraentrega;

import ifpe.dsc.cartorioprotesto.model.Credor;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CredorCrudTest extends TesteGenerico {

    @Test
    public void persistirCredor() {
        logger.info("Executando persistirCredor()");
        Credor credor = new Credor();
        credor.setNome("Persistio da Silva");
        credor.setEmail("perssistido@gmail.com");
        credor.setTelefone("3231-7448");
        credor.setCpf("039.530.000-20");
        em.persist(credor);
        em.flush();
        assertNotNull(credor.getId());
    }
    
    @Test
    public void atualizarCredor(){
        logger.info("Executando persistirCredor()");
        TypedQuery<Credor> query = em.createNamedQuery(Credor.CREDOR_POR_NOME, Credor.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("nome", "João Amoedo");
        Credor credor = query.getSingleResult();
        assertNotNull(credor);
        credor.setNome("João Amoedo Novo");
        em.flush();
        assertEquals(0, query.getResultList().size());
        query.setParameter("nome", "João Amoedo Novo");
        credor = query.getSingleResult();
        assertNotNull(credor);
    }
    
    @Test
    public void atualizarCredorMerge(){
        logger.info("Executando atualizarCredorMerge()");
        TypedQuery<Credor> query = em.createNamedQuery(Credor.CREDOR_POR_NOME, Credor.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("nome", "Luka Modrich");
        Credor credor = query.getSingleResult();
        assertNotNull(credor);
        credor.setNome("Luka Modrich Bola de Ouro");
        em.clear();        
        em.merge(credor);
        em.flush();
        query.setParameter("nome", "Luka Modrich Bola de Ouro");
        credor = query.getSingleResult();
        assertNotNull(credor);
    }
    
    @Test
    public void removerCredor() {
        logger.info("Executando removerCredor()");
        TypedQuery<Credor> query = em.createNamedQuery(Credor.CREDOR_POR_NOME, Credor.class);
        query.setParameter("nome", "Gildete de Souza");
        Credor credor = query.getSingleResult();
        assertNotNull(credor);
        em.remove(credor);
        em.flush();
        assertEquals(0, query.getResultList().size());
    }
}
