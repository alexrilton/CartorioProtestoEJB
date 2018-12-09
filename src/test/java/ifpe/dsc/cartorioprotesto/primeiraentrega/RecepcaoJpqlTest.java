package ifpe.dsc.cartorioprotesto.primeiraentrega;

import ifpe.dsc.cartorioprotesto.model.Credor;
import ifpe.dsc.cartorioprotesto.model.Devedor;
import ifpe.dsc.cartorioprotesto.model.Recepcao;
import static ifpe.dsc.cartorioprotesto.primeiraentrega.TesteGenerico.logger;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RecepcaoJpqlTest extends TesteGenerico {

    @Test
    public void recepcaoPorNumero() {
        logger.info("Executando recepcaoPorNumero()");
        TypedQuery<Recepcao> queryrecepcao = em.createNamedQuery(Recepcao.RECEPCAO_POR_NUMERO, Recepcao.class);
        queryrecepcao.setParameter("numero", 2018011);
        List<Recepcao> recepcoes = queryrecepcao.getResultList();
        assertEquals(1, recepcoes.size());
    }
    
   @Test
    public void recepcaoAbril2018() throws ParseException {
        logger.info("Executando recepcaoAbril2018()");
        
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date dataInicial = new Date(fmt.parse("2018-04-01").getTime());        
        Date dataFinal = new Date(fmt.parse("2018-04-30").getTime());
        TypedQuery<Recepcao> queryrecepcao = em.createQuery(
                "SELECT r FROM Recepcao r WHERE r.data BETWEEN :dataInicial AND :dataFinal",
                Recepcao.class);
        queryrecepcao.setParameter("dataInicial", dataInicial);
        queryrecepcao.setParameter("dataFinal", dataFinal);
        List<Recepcao> recepcoes = queryrecepcao.getResultList();
        assertEquals(4, recepcoes.size());
    }
    
    @Test
    public void recepcoesPorDevedor() {
        logger.info("Executando recepcoesPorDevedor()");
        
        TypedQuery<Devedor> querydevedor = em.createQuery(
                "SELECT d FROM Devedor d WHERE d.cpf = :cpf",
                Devedor.class);
        querydevedor.setParameter("cpf", "427.161.640-02");
        
        List<Devedor> devedores = querydevedor.getResultList();
        TypedQuery<Recepcao> queryrecepcao = em.createQuery(
                "SELECT r FROM Recepcao r WHERE r.devedores = :devedor",
                Recepcao.class);
        queryrecepcao.setParameter("devedor", devedores.get(0));
        List<Recepcao> Recepcao = queryrecepcao.getResultList();
        assertEquals(2, Recepcao.size());
    }  
    
    @Test
    public void recepcoesPorCredor() {
        logger.info("Executando recepcoesPorCredor()");
        
        TypedQuery<Credor> querycredor = em.createQuery(
                "SELECT c FROM Credor c WHERE c.cpf = :cpf",
                Credor.class);
        querycredor.setParameter("cpf", "445.456.456-66");
        
        List<Credor> credores = querycredor.getResultList();
        TypedQuery<Recepcao> queryrecepcao = em.createQuery(
                "SELECT r FROM Recepcao r WHERE r.credores = :credor",
                Recepcao.class);
        queryrecepcao.setParameter("credor", credores.get(0));
        List<Recepcao> Recepcao = queryrecepcao.getResultList();
        assertEquals(2, Recepcao.size());
    }
}
