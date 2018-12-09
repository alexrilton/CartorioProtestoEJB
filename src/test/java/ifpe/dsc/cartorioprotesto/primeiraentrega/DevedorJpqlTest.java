package ifpe.dsc.cartorioprotesto.primeiraentrega;

import ifpe.dsc.cartorioprotesto.model.Devedor;
import static ifpe.dsc.cartorioprotesto.primeiraentrega.TesteGenerico.logger;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DevedorJpqlTest extends TesteGenerico {

    @Test
    public void devedorPorNome() {
        logger.info("Executando credorPorNome()");
        TypedQuery<Devedor> querydevedor = em.createQuery(
                "SELECT d FROM Devedor d WHERE d.nome LIKE :nome",
                Devedor.class);
        querydevedor.setParameter("nome", "%Maria%");
        List<Devedor> devedores = querydevedor.getResultList();

        devedores.forEach((credor) -> {
            assertTrue(credor.getNome().contains("Maria"));
       });

        assertEquals(3, devedores.size());
    }
    
    @Test
    public void devedorPorCPF() {
        logger.info("Executando devedorPorCPF()");
        TypedQuery<Devedor> querydevedor = em.createQuery(
                "SELECT d FROM Devedor d WHERE d.cpf = :cpf",
                Devedor.class);
        querydevedor.setParameter("cpf", "400.078.780-28");
        List<Devedor> devedores = querydevedor.getResultList();

        assertEquals(1, devedores.size());
    }
    
    @Test
    public void recepcoesPorDevedor() {
        logger.info("Executando recepcoesPorDevedor()");
        TypedQuery<Devedor> querydevedor = em.createQuery(
                "SELECT d FROM Devedor d WHERE d.cpf = :cpf",
                Devedor.class);
        querydevedor.setParameter("cpf", "427.161.640-02");
        List<Devedor> devedores = querydevedor.getResultList();
        assertEquals(1, devedores.size());
        
        assertEquals(2, devedores.get(0).getRecepcoes().size());
    }
    
    @Test
    public void credoresQtdRecepcoesMaiorQue1() {
        logger.info("Executando credoresComMaisRecepcoes()");
        Query query = em.createQuery("SELECT d, COUNT(r) "
                + "FROM Devedor d, Recepcao r "
                + "WHERE "
                + "r MEMBER OF d.recepcoes GROUP BY d HAVING COUNT(r) > ?1 ORDER BY COUNT(r) desc, d.nome");
        query.setParameter(1, 1);
        
        List<Object[]> devedores = query.getResultList();
        assertEquals(3, devedores.size());
        assertEquals("Sandra de Sá", ((Devedor)devedores.get(0)[0]).getNome());
        assertEquals(4, (long) (devedores.get(0)[1]), 0);
        assertEquals("Paulo Costa", ((Devedor)devedores.get(devedores.size()-1)[0]).getNome());
        assertEquals(2, (long) (devedores.get(devedores.size()-1)[1]), 0);
    }
    
}
