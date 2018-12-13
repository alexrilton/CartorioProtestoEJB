package ifpe.dsc.cartorioprotesto.teste;

import ifpe.dsc.cartorioprotesto.model.Tabeliao;
import ifpe.dsc.cartorioprotesto.service.TabeliaoServico;
import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import org.junit.After;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Assert;

public class TabeliaoServicoTest {

    private static EJBContainer container;
    private static TabeliaoServico objectServico;

    public TabeliaoServicoTest() {
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
        objectServico = (TabeliaoServico) container.getContext().lookup("java:global/classes/TabeliaoServico!ifpe.dsc.cartorioprotesto.service.TabeliaoServico");
    }

    @After
    public void tearDown() {
        objectServico = null;
    }

    @Test
    public void persistirCredor() throws Exception {

        Tabeliao tabeliao = new Tabeliao();
        tabeliao.setNome("João Roma");
        tabeliao.setRegistroTJ(1454454L);
        tabeliao.setCpf("091.174.754-06");
        tabeliao.setGrupo("USUARIO");
        tabeliao.setLogin("roma");
        tabeliao.setSenha("12345678");
        
        try {
            objectServico.criptografarSenha(tabeliao);
        } catch (Exception e) {
            System.err.println("Erro");
        }    

        objectServico.salvar(tabeliao);
        Tabeliao tabeliaoCriado = objectServico.getTabeliaoPorNome("João Roma");
        Assert.assertNotNull(tabeliaoCriado.getId());
    }

}
