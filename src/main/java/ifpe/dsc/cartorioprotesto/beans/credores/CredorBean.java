package ifpe.dsc.cartorioprotesto.beans.credores;

import ifpe.dsc.cartorioprotesto.bean.AbstractBean;
import ifpe.dsc.cartorioprotesto.model.Credor;
import ifpe.dsc.cartorioprotesto.service.CredorServico;
import ifpe.dsc.cartorioprotesto.util.jsf.JSFUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean(name = "credorBean")
@javax.faces.bean.RequestScoped
public class CredorBean extends AbstractBean implements Serializable {

    @Inject
    @EJB
    private CredorServico credorServico;
    
    private Credor credorSelecionado;

    private List<Credor> credores;

    private String buscarPorNome;
    

    public List<Credor> getCredores() {
        return credores;
    }

    public void setCredores(List<Credor> credores) {
        this.credores = credores;
    }

    public String getBuscarPorNome() {
        return buscarPorNome;
    }

    public void setBuscarPorNome(String buscarPorNome) {
        this.buscarPorNome = buscarPorNome;
    }
    
    

    public Credor getCredorSelecionado() {
        return credorSelecionado;
    }

    public void setCredorSelecionado(Credor credorSelecionado) {
        this.credorSelecionado = credorSelecionado;
    }

    @PostConstruct
    public void init() {
        listar();
    } 

    public void listar() {
        try {
            credores = credorServico.getCredores();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarPorNome() {
        try {
            Credor credor = credorServico.getCredorPorNome(buscarPorNome);
            credores.clear();
            credores.add(credor);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void renderTo(String action) {
		JSFUtils.renderTo("credore_edit.xhtml");
		JSFUtils.setParam("credor", credorSelecionado);
                JSFUtils.setParam("action", action);
	}
}
