package ifpe.dsc.cartorioprotesto.primeiraentrega;

import ifpe.dsc.cartorioprotesto.model.Credor;
import ifpe.dsc.cartorioprotesto.model.Devedor;
import ifpe.dsc.cartorioprotesto.model.Guia;
import ifpe.dsc.cartorioprotesto.model.Recepcao;
import ifpe.dsc.cartorioprotesto.model.Titulo;
import java.util.Date;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecepcaoCrudTest extends TesteGenerico {
    
     
    @Test
    public void persistirRecepcao() {
        logger.info("Executando persistirRecepcao()");
        
        TypedQuery<Credor> querycredor = em.createNamedQuery("Credor.PorNome", Credor.class);
        TypedQuery<Devedor> querydevedor = em.createNamedQuery("Devedor.PorNome", Devedor.class);
        TypedQuery<Guia> queryguia = em.createNamedQuery("Guia.PorRecepcao", Guia.class);         
        TypedQuery<Titulo> querytitulo = em.createNamedQuery("Titulo.PorRecepcao", Titulo.class);         
        querycredor.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);                
        querydevedor.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);        
        queryguia.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);        
        querytitulo.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);        
        Recepcao recepcao = new Recepcao();
        recepcao.setNumero(2018005);
        recepcao.setData(new Date());
        Titulo titulo = new Titulo();
        titulo.setNatureza("Cheque");
        titulo.setValor(2548.56);
        titulo.setRecepcao(recepcao);
        recepcao.addTitulo(titulo);
        querycredor.setParameter("nome", "Ciro Gomes");
        Credor credor = querycredor.getSingleResult();
        recepcao.addCredor(credor);
        querydevedor.setParameter("nome", "Paulo Costa");
        Devedor devedor = querydevedor.getSingleResult();
        recepcao.addDevedor(devedor);
        em.persist(recepcao);
        em.flush();
        Guia guia = new Guia();
        guia.setNumero(2018010);
        guia.setValor(2548.56);
        guia.setData(new Date());
        guia.setRecepcao(recepcao);    
        em.persist(guia);
        em.flush();
        assertNotNull(recepcao.getId());
        queryguia.setParameter("idRecepcao", recepcao.getId());
        querytitulo.setParameter("idRecepcao", recepcao.getId());
        assertEquals(1, queryguia.getResultList().size());
        assertEquals(1, querytitulo.getResultList().size());
        
    }
    
    @Test
    public void atualizarRecepcao(){
        logger.info("Executando persistirRecepcao()");
        TypedQuery<Recepcao> queryrecepcao = em.createNamedQuery("Recepcao.PorNumero", Recepcao.class);
        queryrecepcao.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryrecepcao.setParameter("numero", 2018011);
        Recepcao recepcao = queryrecepcao.getSingleResult();
        
        assertNotNull(recepcao);
        Date data = new Date();
        recepcao.setData(data);
        em.flush();
        recepcao = queryrecepcao.getSingleResult();
        assertEquals(data, recepcao.getData());
    }
    
    @Test
    public void atualizarRecepcaoMerge(){
        logger.info("Executando atualizarRecepcaoMerge()");
        TypedQuery<Recepcao> queryrecepcao = em.createNamedQuery("Recepcao.PorNumero", Recepcao.class); 
        queryrecepcao.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryrecepcao.setParameter("numero", 2018007);
        Recepcao recepcao = queryrecepcao.getSingleResult();
        assertNotNull(recepcao);
        Date data = new Date();
        recepcao.setData(data);
        em.clear();        
        em.merge(recepcao);
        em.flush();
        recepcao = queryrecepcao.getSingleResult();
        assertEquals(data, recepcao.getData());
    }
    
    @Test
    public void removerRecepcao() {
        logger.info("Executando removerRecepcao()");
        TypedQuery<Recepcao> queryrecepcao = em.createNamedQuery("Recepcao.PorNumero", Recepcao.class);
        TypedQuery<Guia> queryguia = em.createNamedQuery("Guia.PorRecepcao", Guia.class);
        queryrecepcao.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryguia.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        queryrecepcao.setParameter("numero", 2018022);
        Recepcao recepcao = queryrecepcao.getSingleResult();
        Long idRecepcao = recepcao.getId();
        assertNotNull(recepcao);
        em.remove(recepcao);
        em.flush();
        assertEquals(0, queryrecepcao.getResultList().size());
        queryguia.setParameter("idRecepcao", idRecepcao);
        assertEquals(0, queryguia.getResultList().size());
        
    }
}
