package ifpe.dsc.cartorioprotesto.bean;

import ifpe.dsc.cartorioprotesto.model.Guia;
import ifpe.dsc.cartorioprotesto.service.GuiaServico;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean(name = "guiaBean")
@javax.faces.bean.RequestScoped
public class GuiaBean extends AbstractBean implements Serializable {

    @Inject
    @EJB
    private GuiaServico guiaServico;

    private List<Guia> guias;

    private long buscarPorNumero;

    public List<Guia> getGuias() {
        return guias;
    }

    public void setGuias(List<Guia> guias) {
        this.guias = guias;
    }

    public long getBuscarPorNumero() {
        return buscarPorNumero;
    }

    public void setBuscarPorNumero(long buscarPorNumero) {
        this.buscarPorNumero = buscarPorNumero;
    }

    @PostConstruct
    public void init() {
        listar();
    } 

    public void listar() {
        try {
            guias = guiaServico.getGuias();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarPorNumero() {
        try {
            Guia guia = guiaServico.getGuiaPorNumero(buscarPorNumero);
            guias.clear();
            guias.add(guia);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
