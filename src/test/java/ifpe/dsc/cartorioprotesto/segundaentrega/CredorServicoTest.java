package ifpe.dsc.cartorioprotesto.segundaentrega;

import ifpe.dsc.cartorioprotesto.model.Credor;
import ifpe.dsc.cartorioprotesto.service.CredorServico;
import javax.ejb.EJBException;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import static org.hamcrest.CoreMatchers.startsWith;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.AfterClass;
import org.junit.After;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CredorServicoTest {

    private static EJBContainer container;
    private static CredorServico objectServico;

    public CredorServicoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        container = EJBContainer.createEJBContainer();
        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        container.close();
    }

    @Before
    public void setUp() throws Exception {
        objectServico = (CredorServico) container.getContext().lookup("java:global/classes/CredorServico!ifpe.dsc.cartorioprotesto.service.CredorServico");
    }

    @After
    public void tearDown() {
        objectServico = null;
    }

    @Test
    public void inserirCredor() {
        Credor credor = new Credor();
        credor.setNome("Persistio da Silva");
        credor.setEmail("perssistido@gmail.com");
        credor.setTelefone("3231-7448");
        credor.setCpf("039.530.000-20");

        objectServico.salvar(credor);
        Credor object = objectServico.getCredorPorNome("Persistio da Silva");
        Assert.assertNotNull(object.getId());
    }

    @Test
    public void inserirCredorInvalido() {
        Credor credor = new Credor();
        credor.setNome("Teste validação com mais de 100 caracteres para checagem de tamanho do nome com a quantidade maxima de caracteres");//
        credor.setEmail("perssistidogmailcom");//Email inválido
        credor.setTelefone("3231-7448");
        credor.setCpf("");

        try {
            objectServico.salvar(credor);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("deve conter no maximo 100 caracteres"),
                                startsWith("CPF Invalido"),
                                startsWith("not a well-formed email address"),
                                startsWith("may not be empty")));
            }
        }
    }

    @Test
    public void consultarCredor() throws NamingException {
        Credor object = objectServico.getCredorPorNome("Caio Fernandes");
        Assert.assertNotNull(object);
    }

    @Test
    public void atualizarCredor() throws NamingException {
        Credor object = objectServico.getCredorPorNome("Ciro Gomes");
        Assert.assertNotNull(object);
        object.setEmail("paulodevteam@gmail.com");
        objectServico.atualizar(object);
        Credor object2 = objectServico.getCredorPorNome("Ciro Gomes");
        Assert.assertFalse("abdcads@hotmail.com.br".equals(object2.getEmail()));
    }

    @Test
    public void atualizarCredorInvalido() {
        Credor credor = objectServico.getCredorPorNome("Ciro Gomes");
        Assert.assertNotNull(credor);
        credor.setNome("Vitor Saldanha*");
        credor.setEmail("validationemail.teste");

        try {
            objectServico.atualizar(credor);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("not a well-formed email address"),
                                startsWith("caracteres invalidos")));
            }
        }
    }

    @Test(expected = EJBException.class)
    public void removerCredor() throws NamingException {
        Credor object = objectServico.getCredorPorNome("Carmen Silva");
        Assert.assertNotNull(object);
        objectServico.remover(object);
        object = objectServico.getCredorPorNome("Carmen Silva");
        Assert.assertNull(object);
    }
}
