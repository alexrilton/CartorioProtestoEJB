package ifpe.dsc.cartorioprotesto.bean;

import ifpe.dsc.cartorioprotesto.model.Devedor;
import ifpe.dsc.cartorioprotesto.service.DevedorServico;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean(name = "devedorBean")
@javax.faces.bean.RequestScoped
public class DevedorBean extends AbstractBean implements Serializable {

    @Inject
    @EJB
    private DevedorServico devedorServico;

    private List<Devedor> devedores;

    private String buscarPorNome;

    public List<Devedor> getDevedores() {
        return devedores;
    }

    public void setDevedores(List<Devedor> devedores) {
        this.devedores = devedores;
    }

    public String getBuscarPorNome() {
        return buscarPorNome;
    }

    public void setBuscarPorNome(String buscarPorNome) {
        this.buscarPorNome = buscarPorNome;
    }

    @PostConstruct
    public void init() {
        listar();
    } 

    public void listar() {
        try {
            devedores = devedorServico.getDevedores();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarPorNome() {
        try {
            Devedor devedor = devedorServico.getDevedorPorNome(buscarPorNome);
            devedores.clear();
            devedores.add(devedor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
