package ifpe.dsc.cartorioprotesto.primeiraentrega;

import ifpe.dsc.cartorioprotesto.model.Guia;
import ifpe.dsc.cartorioprotesto.model.Recepcao;
import java.util.Date;
import java.util.Set;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.Test;
import static org.junit.Assert.*;

public class GuiaValidationTest extends TesteGenerico {    
    

    @Test(expected = ConstraintViolationException.class)
    public void persistirGuiaInvalida() {
        Guia guia = null;
        try{
            TypedQuery<Recepcao> queryrecepcao = em.createNamedQuery(Recepcao.RECEPCAO_POR_NUMERO, Recepcao.class);
            queryrecepcao.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
            queryrecepcao.setParameter("numero", 2018045);
            Recepcao recepcao = queryrecepcao.getSingleResult();
            guia = new Guia();
            guia.setNumero(20181545);
            guia.setValor(2558.98);
            guia.setData(new Date());
            em.persist(guia);
            
        }catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            
            for (ConstraintViolation<?> violation : constraintViolations) {
                System.out.println(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage());
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(), 
                        CoreMatchers.anyOf(
                                startsWith("class ifpe.dsc.cartorioprotesto.model.Guia.recepcao: may not be null")));
            }
            
            assertEquals(1, constraintViolations.size());            
            assertNull(guia.getId());
            throw ex;
        }        
    }    
    
}
