package ifpe.dsc.cartorioprotesto.teste;

import ifpe.dsc.cartorioprotesto.model.Guia;
import ifpe.dsc.cartorioprotesto.service.GuiaServico;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.After;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;

public class GuiaServicoTest {

    private static EJBContainer container;
    private static GuiaServico objectServico;

    public GuiaServicoTest() {
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
        objectServico = (GuiaServico) container.getContext().lookup("java:global/classes/GuiaServico!ifpe.dsc.cartorioprotesto.service.GuiaServico");
    }

    @After
    public void tearDown() {
        objectServico = null;
    }    

    @Test
    public void consultarGuiaPorNumero() throws NamingException {
        Guia guia = objectServico.getGuiaPorNumero(20180001L);
        Assert.assertNotNull(guia);
        double valorGuia = 73.20;
        Assert.assertEquals(valorGuia, guia.getValor(), 0.2);
    }
    
    @Test
    public void atualizarGuia() throws NamingException {
        Guia guia = objectServico.getGuiaPorNumero(20180002L);
        Assert.assertNotNull(guia);
        double novoValor = 600.02;
        guia.setValor(novoValor);
        objectServico.atualizar(guia);
        Guia guiaAtualizada = objectServico.getGuiaPorNumero(20180002L);
        assertEquals(novoValor, guiaAtualizada.getValor(), 0.2);
    }
    
}
