package ifpe.dsc.cartorioprotesto.beans.credores;

import ifpe.dsc.cartorioprotesto.bean.AbstractBean;
import ifpe.dsc.cartorioprotesto.model.Credor;
import ifpe.dsc.cartorioprotesto.service.CredorServico;
import ifpe.dsc.cartorioprotesto.util.jsf.JSFUtils;
import ifpe.dsc.cartorioprotesto.util.messages.MessageUtils;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean(name = "credorEditBean")
@javax.faces.bean.RequestScoped
public class CredorEditBean extends AbstractBean implements Serializable {

    @Inject
    @EJB
    private CredorServico credorServico;

    private Credor credorSelecionado;

    public Credor getCredor() {
        return credor;
    }

    public void setCredor(Credor credor) {
        this.credor = credor;
    }

    private Credor credor;

    public void preRenderView() {
        if (credor == null) {
            credor = new Credor();
        }
    }

    public void save() {
        if (isCredorEdit()) {
            credorServico.atualizar(credor);
            MessageUtils.messageSucess("Produto de carga atualizado com sucesso.");
        } else {
            credorServico.salvar(credor);
            MessageUtils.messageSucess("Produto de carga adicionado com sucesso.");
        }
        JSFUtils.rederTo("credores.xhtml");
    }

    public Credor getCredorSelecionado() {
        return credorSelecionado;
    }

    public void setCredorSelecionado(Credor credorSelecionado) {
        this.credorSelecionado = credorSelecionado;
    }

    public boolean isCredorEdit() {
       return credor.getId() != null;
    }
}
