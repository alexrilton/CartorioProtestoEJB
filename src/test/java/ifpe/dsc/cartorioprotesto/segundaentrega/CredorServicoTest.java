package ifpe.dsc.cartorioprotesto.segundaentrega;

import ifpe.dsc.cartorioprotesto.model.Credor;
import ifpe.dsc.cartorioprotesto.primeiraentrega.DbUnitUtil;
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
        objectServico = (CredorServico) container.getContext().lookup("java:global/classes/CredorServico!servico.CredorServico");
    }

    @After
    public void tearDown() {
        objectServico = null;
    }

    @Test
    public void consultarCredor() throws NamingException {
        Credor object = objectServico.getCredorByCPF("051.239.054-13");
        Assert.assertNotNull(object);
        Assert.assertTrue("ansca@ig.com.br".equals(object.getEmail()));
    }

    @Test
    public void atualizarCredor() throws NamingException {
        Credor object = objectServico.getCredorByCPF("020.563.268-84");
        Assert.assertNotNull(object);
        object.setEmail("paulodevteam@gmail.com");
        objectServico.atualizar(object);
        Credor object2 = objectServico.getCredorByCPF("020.563.268-84");
        Assert.assertFalse("abdcads@hotmail.com.br".equals(object2.getEmail()));
    }

    @Test
    public void atualizarCredorInvalido() {
        Credor object = objectServico.getCredorByCPF("711.319.163-01");
        Assert.assertNotNull(object);
        object.setCpf("48569102216");

        try {
            objectServico.atualizar(object);
            Assert.assertTrue(false);
        } catch (EJBException e) {
            Assert.assertTrue(e.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa = (ConstraintViolationException) e.getCause();
            for (ConstraintViolation en : causa.getConstraintViolations()) {
                Assert.assertThat(en.getMessage(), CoreMatchers.anyOf(startsWith("invalid Brazilian individual taxpayer registry number (CPF)")));

            }
        }
    }

    @Test(expected = EJBException.class)
    public void removerCredor() throws NamingException {
        Credor object = objectServico.getCredorByCPF("284.511.124-04");
        Assert.assertNotNull(object);
        objectServico.remover(object);
        object = objectServico.getCredorByCPF("284.511.124-04");
        Assert.assertNull(object);
    }

    @Test
    public void inserirCredor() {
        Credor credor = new Credor();
        credor.setNome("Capetonildo doidao");
        credor.setEmail("capetonildodoidao22@gmail.com");
        credor.setTelefone("3344-8989");
        credor.setCpf("528.475.090-19");

        objectServico.salvar(credor);
        Credor object = objectServico.getCredorByCPF("528.475.090-19");
        Assert.assertNotNull(object);
    }

}