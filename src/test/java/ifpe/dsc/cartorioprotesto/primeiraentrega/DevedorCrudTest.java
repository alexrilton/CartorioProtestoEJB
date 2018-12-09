package ifpe.dsc.cartorioprotesto.primeiraentrega;

import ifpe.dsc.cartorioprotesto.model.Devedor;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DevedorCrudTest extends TesteGenerico {

    @Test
    public void persistirDevedor() {
        logger.info("Executando persistirDevedor()");
        Devedor devedor = new Devedor();
        devedor.setNome("Geraldo Merenda");
        devedor.setCpf("750.398.390-65");
        em.persist(devedor);
        em.flush();
        assertNotNull(devedor.getId());
    }
    
    @Test
    public void atualizarDevedor(){
        logger.info("Executando persistirDevedor()");
        TypedQuery<Devedor> query = em.createNamedQuery(Devedor.DEVEDOR_POR_NOME, Devedor.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("nome", "Schergio Mhelloh");
        Devedor devedor = query.getSingleResult();
        assertNotNull(devedor);
        devedor.setNome("Schergio Mhelloh Sylvah");
        em.flush();
        assertEquals(0, query.getResultList().size());
        query.setParameter("nome", "Schergio Mhelloh Sylvah");
        devedor = query.getSingleResult();
        assertNotNull(devedor);
    }
    
    @Test
    public void atualizarDevedorMerge(){
        logger.info("Executando atualizarDevedorMerge()");
        TypedQuery<Devedor> query = em.createNamedQuery(Devedor.DEVEDOR_POR_NOME, Devedor.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter("nome", "Michele Castelo Branco");
        Devedor devedor = query.getSingleResult();
        assertNotNull(devedor);
        devedor.setNome("Michele Castelo Azul");
        em.clear();        
        em.merge(devedor);
        em.flush();
        query.setParameter("nome", "Michele Castelo Azul");
        devedor = query.getSingleResult();
        assertNotNull(devedor);
    }
    
    @Test
    public void removerDevedor() {
        logger.info("Executando removerDevedor()");
        TypedQuery<Devedor> query = em.createNamedQuery(Devedor.DEVEDOR_POR_NOME, Devedor.class);
        query.setParameter("nome", "Guilherme Boulos");
        Devedor devedor = query.getSingleResult();
        assertNotNull(devedor);
        em.remove(devedor);
        em.flush();
        assertEquals(0, query.getResultList().size());
    }
}
