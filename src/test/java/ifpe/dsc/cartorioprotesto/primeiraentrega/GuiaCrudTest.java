package ifpe.dsc.cartorioprotesto.primeiraentrega;

import ifpe.dsc.cartorioprotesto.model.Guia;
import ifpe.dsc.cartorioprotesto.model.Recepcao;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GuiaCrudTest extends TesteGenerico {

    @Test
    public void atualizarGuia() {
        logger.info("Executando atualizarGuia()");
        
        TypedQuery<Recepcao> queryrecepcao = em.createNamedQuery(Recepcao.RECEPCAO_POR_NUMERO, Recepcao.class);
        queryrecepcao.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryrecepcao.setParameter("numero", 2018001);
        Recepcao recepcao = queryrecepcao.getSingleResult();
        TypedQuery<Guia> queryguia = em.createNamedQuery(Guia.GUIA_POR_RECEPCAO, Guia.class);
        queryguia.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryguia.setParameter("idRecepcao", recepcao.getId());
        Guia guia = queryguia.getSingleResult();
        assertNotNull(guia);
        double novoValor = 1245.18;
        guia.setValor(novoValor);
        em.flush();
        queryguia.setParameter("idRecepcao", recepcao.getId());
        guia = queryguia.getSingleResult();
        assertEquals(novoValor, guia.getValor(), 0.2);
    }

    @Test
    public void atualizarGuiaMerge() {
        logger.info("Executando atualizarGuiaMerge()");
        TypedQuery<Recepcao> queryrecepcao = em.createNamedQuery(Recepcao.RECEPCAO_POR_NUMERO, Recepcao.class);
        queryrecepcao.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryrecepcao.setParameter("numero", 2018007);
        Recepcao recepcao = queryrecepcao.getSingleResult();
        TypedQuery<Guia> queryguia = em.createNamedQuery(Guia.GUIA_POR_RECEPCAO, Guia.class);
        queryguia.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryguia.setParameter("idRecepcao", recepcao.getId());
        Guia guia = queryguia.getSingleResult();
        assertNotNull(guia);
        double novoValor = 2547.96;
        guia.setValor(novoValor);
        em.clear();
        em.merge(guia);
        em.flush();
        queryguia.setParameter("idRecepcao", recepcao.getId());
        guia = queryguia.getSingleResult();
        assertEquals(novoValor, guia.getValor(), 0.2);
    }
    
}
