package ifpe.dsc.cartorioprotesto.primeiraentrega;

import ifpe.dsc.cartorioprotesto.model.Credor;
import ifpe.dsc.cartorioprotesto.model.CredorQtdRecepcao;
import static ifpe.dsc.cartorioprotesto.primeiraentrega.TesteGenerico.logger;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CredorJpqlTets extends TesteGenerico {

    @Test
    public void credorPorNome() {
        logger.info("Executando credorPorNome()");
        TypedQuery<Credor> querycredor = em.createQuery(
                "SELECT c FROM Credor c WHERE c.nome LIKE :nome",
                Credor.class);
        querycredor.setParameter("nome", "Kassimo%");
        List<Credor> credores = querycredor.getResultList();

        credores.forEach((credor) -> {
            assertTrue(credor.getNome().startsWith("Kassimo"));
       });

        assertEquals(2, credores.size());
    }
    
    @Test
    public void credorPorCPF() {
        logger.info("Executando credorPorCPF()");        
        TypedQuery<Credor> querycredor;
        querycredor = em.createQuery(
                "SELECT c FROM Credor c "
                + "WHERE c.cpf =  ?1 ",
                Credor.class);
        querycredor.setParameter(1 , "562.989.480-31");
        List<Credor> credores = querycredor.getResultList();

        assertEquals(1, credores.size());
    }
    
    @Test
    public void recepcoesPorCredor() {
        logger.info("Executando recepcaoPorCredor()");
        TypedQuery<Credor> querycredor = em.createQuery(
                "SELECT c FROM Credor c WHERE c.cpf = :cpf",
                Credor.class);
        querycredor.setParameter("cpf", "445.456.456-66");
        List<Credor> credores = querycredor.getResultList();
        assertEquals(1, credores.size());
        
        assertEquals(2, credores.get(0).getRecepcoes().size());
    }
    
    @Test
    public void credoresQtdRecepcoes() {
        logger.info("Executando credoresComMaisRecepcoes()");
        Query querycredor = em.createNamedQuery("quantidadeRecepcoes");
        List<CredorQtdRecepcao> credores = querycredor.getResultList();        
        assertEquals(9, credores.size());
        assertEquals("Ciro Gomes", credores.get(0).getNome());
        assertEquals(2, credores.get(0).getNumRecepcoes(), 0);
        assertEquals("Sara Gusmão", credores.get(credores.size()-1).getNome());
        assertEquals(1, credores.get(credores.size()-1).getNumRecepcoes(), 0);
    }
}
