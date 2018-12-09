package ifpe.dsc.cartorioprotesto.primeiraentrega;

import ifpe.dsc.cartorioprotesto.model.Credor;
import java.util.Set;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import org.junit.Test;
import static org.junit.Assert.*;

public class CredorValidationTest extends TesteGenerico {

    @Test(expected = ConstraintViolationException.class)
    public void persistirCredorInvalido() {
        Credor credor = null;
        try {
            credor = new Credor();
            credor.setNome("Teste validação com mais de 100 caracteres para checagem de tamanho do nome com a quantidade maxima de caracteres");//
            credor.setEmail("perssistidogmailcom");//Email inválido
            credor.setTelefone("3231-7448");
            credor.setCpf("");
            em.persist(credor);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getRootBeanClass() + "." + violation.getPropertyPath() + ": " + violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("class ifpe.dsc.cartorioprotesto.model.Credor.nome: deve conter no maximo 100 caracteres"),
                                startsWith("class ifpe.dsc.cartorioprotesto.model.Credor.cpf: CPF Invalido"),
                                startsWith("class ifpe.dsc.cartorioprotesto.model.Credor.email: not a well-formed email address"),
                                startsWith("class ifpe.dsc.cartorioprotesto.model.Credor.cpf: may not be empty")));
            }

            assertEquals(4, constraintViolations.size());
            assertNull(credor.getId());
            throw ex;
        }
    }

    @Test(expected = ConstraintViolationException.class)
    public void atualizarCredorInvalido() {
        TypedQuery<Credor> querycredor;
        querycredor = em.createQuery(
                "SELECT c FROM Credor c WHERE c.cpf =  ?1 ",
                Credor.class);
        querycredor.setParameter(1, "678.680.210-98");
        Credor credor = querycredor.getSingleResult();
        credor.setNome("Vitor Saldanha*");
        credor.setEmail("validationemail.teste");

        try {
            em.flush();
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            for (ConstraintViolation violation : constraintViolations) {
                assertThat(violation.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("not a well-formed email address"),
                                startsWith("caracteres invalidos")));
            }
            assertEquals(2, ex.getConstraintViolations().size());
            throw ex;
        }
    }
}
