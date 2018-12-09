package ifpe.dsc.cartorioprotesto.primeiraentrega;

import ifpe.dsc.cartorioprotesto.model.Guia;
import static ifpe.dsc.cartorioprotesto.primeiraentrega.TesteGenerico.logger;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;

public class GuiaJpqlTest extends TesteGenerico {

    @Test
    public void guiaAcimaDe2000() {
        logger.info("Executando guiaAcimaDe2000()");
        TypedQuery<Guia> queryGuia = em.createQuery(
                "SELECT g FROM Guia g WHERE g.valor > :valor",
                Guia.class);
        
        queryGuia.setParameter("valor", 2000);
        List<Guia> guias = queryGuia.getResultList();

        assertEquals(3, guias.size());
    }
    
    @Test
    public void guiaFeitaEm2017() throws ParseException {
        logger.info("Executando guiaFeitaEm2017()");
        TypedQuery<Guia> queryGuia = em.createQuery(
                "SELECT g FROM Guia g WHERE g.data BETWEEN :dataInicial AND :dataFinal",
                Guia.class);
        
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date dataInicial = new Date(fmt.parse("2017-01-01").getTime());
        
        Date dataFinal = new Date(fmt.parse("2017-12-31").getTime());
        
        
        queryGuia.setParameter("dataInicial", dataInicial);
        queryGuia.setParameter("dataFinal", dataFinal);
        List<Guia> guias = queryGuia.getResultList();

        assertEquals(3, guias.size());
    }
    
    @Test
    public void guiaFeitaEm2018() throws ParseException {
        logger.info("Executando guiaFeitaEm2018()");
        TypedQuery<Guia> queryGuia = em.createQuery(
                "SELECT g FROM Guia g WHERE g.data BETWEEN :dataInicial AND :dataFinal",
                Guia.class);
        
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date dataInicial = new Date(fmt.parse("2018-01-01").getTime());
        
        Date dataFinal = new Date(fmt.parse("2018-12-31").getTime());
        
        
        queryGuia.setParameter("dataInicial", dataInicial);
        queryGuia.setParameter("dataFinal", dataFinal);
        List<Guia> guias = queryGuia.getResultList();

        assertEquals(9, guias.size());
    }
}
