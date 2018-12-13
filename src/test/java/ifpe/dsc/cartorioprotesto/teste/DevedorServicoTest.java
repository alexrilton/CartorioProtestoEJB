package ifpe.dsc.cartorioprotesto.teste;

import ifpe.dsc.cartorioprotesto.model.Devedor;
import ifpe.dsc.cartorioprotesto.service.DevedorServico;
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

public class DevedorServicoTest {

    private static EJBContainer container;
    private static DevedorServico objectServico;

    public DevedorServicoTest() {
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
        objectServico = (DevedorServico) container.getContext().lookup("java:global/classes/DevedorServico!ifpe.dsc.cartorioprotesto.service.DevedorServico");
    }

    @After
    public void tearDown() {
        objectServico = null;
    }    

    @Test
    public void consultarDevedor() throws NamingException {
        Devedor devedor = objectServico.getDevedorPorNome("Guilherme Boulos");
        Assert.assertNotNull(devedor);
        Assert.assertEquals("639.046.840-78", devedor.getCpf());
    }

    @Test
    public void persistirDevedor() {
        Devedor devedor = new Devedor();
        devedor.setNome("Motorista do Milhao");
        devedor.setCpf("039.530.000-20");

        objectServico.salvar(devedor);
        Devedor devedorPersistido = objectServico.getDevedorPorNome("Motorista do Milhao");
        Assert.assertNotNull(devedorPersistido.getId());
    }

    @Test
    public void persistirDevedorInvalido() {
        Devedor devedor = new Devedor();
        devedor.setNome("C&A");
        devedor.setCpf("");

        try {
            objectServico.salvar(devedor);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("caracteres invalidos"),
                                startsWith("CPF Invalido"),
                                startsWith("não pode estar vazio")));
            }
        }
    }
    
    @Test
    public void atualizarDevedor() throws NamingException {
        Devedor devedor = objectServico.getDevedorPorNome("Paulo Costa");
        Assert.assertNotNull(devedor);
        devedor.setNome("Paulo Costa Atualizado");
        objectServico.atualizar(devedor);
        Devedor devedorAtualizado = objectServico.getDevedorPorNome("Paulo Costa Atualizado");
        assertTrue("427.161.640-02".equals(devedorAtualizado.getCpf()));
    }

    @Test
    public void atualizarDevedorInvalido() {
        Devedor devedor = objectServico.getDevedorPorNome("Schergio Mhelloh");
        Assert.assertNotNull(devedor);
        devedor.setNome("Schergio Mhelloh & Santos");

        try {
            objectServico.atualizar(devedor);
        } catch (EJBException ex) {
            assertTrue(ex.getCause() instanceof ConstraintViolationException);
            ConstraintViolationException causa
                    = (ConstraintViolationException) ex.getCause();
            for (ConstraintViolation erroValidacao : causa.getConstraintViolations()) {
                assertThat(erroValidacao.getMessage(),
                        CoreMatchers.anyOf(
                                startsWith("caracteres invalidos")));
            }
        }
    }

    @Test(expected = EJBException.class)
    public void removerDevedor() throws NamingException {
        Devedor object = objectServico.getDevedorPorNome("Gilberto Junior");
        Assert.assertNotNull(object);
        objectServico.remover(object);
        object = objectServico.getDevedorPorNome("Gilberto Junior");
        Assert.assertNull(object);
    }
}
