package ifpe.dsc.cartorioprotesto.primeiraentrega;

import ifpe.dsc.cartorioprotesto.model.Devedor;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.Test;
import static org.junit.Assert.*;

public class DevedorValidationTest extends TesteGenerico {    
    

    @Test(expected = ConstraintViolationException.class)
    public void persistirDevedorInvalido() {
        Devedor devedor = null;
        try{
            devedor = new Devedor();
            devedor.setNome("C&A");
            devedor.setCpf("");
            em.persist(devedor);
        }catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            
            constraintViolations.forEach((violation) -> {
                System.out.println(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage());
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(), 
                        CoreMatchers.anyOf(
                                startsWith("class JPA.Devedor.nome: caracteres invalidos"),
                                startsWith("class JPA.Devedor.cpf: CPF Invalido"),
                                startsWith("class JPA.Devedor.cpf: não pode estar vazio")));
            });
            
            assertEquals(3, constraintViolations.size());            
            assertNull(devedor.getId());
            throw ex;
        }        
    }
    
    @Test(expected = ConstraintViolationException.class)
    public void atualizarDevedorInvalido() {
        TypedQuery<Devedor> querydevedor;
         querydevedor = em.createQuery(
                "SELECT d FROM Devedor d WHERE d.cpf =  ?1 ",
                Devedor.class);
        querydevedor.setParameter(1 , "650.276.750-89");
        Devedor devedor = querydevedor.getSingleResult();
        devedor.setNome("");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {    
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            constraintViolations.forEach((violation) -> {
                assertThat(violation.getMessage(), 
                        CoreMatchers.anyOf(
                                startsWith("não pode estar vazio")));
            });
            assertEquals(1, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
