package ifpe.dsc.cartorioprotesto.beans.credores;

import ifpe.dsc.cartorioprotesto.bean.AbstractBean;
import ifpe.dsc.cartorioprotesto.model.Credor;
import ifpe.dsc.cartorioprotesto.service.CredorServico;
import ifpe.dsc.cartorioprotesto.util.jsf.JSFUtils;
import ifpe.dsc.cartorioprotesto.util.messages.MessageUtils;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@ManagedBean(name = "credorEditBean")
@ViewScoped
public class CredorEditBean extends AbstractBean implements Serializable {

    @Inject
    @EJB
    private CredorServico credorServico;

    private Credor credor;
    
    Long id;
    String nome;
    String cpf;
    String telefone;
    String email;
    
    public CredorServico getCredorServico() {
        return credorServico;
    }

    public void setCredorServico(CredorServico credorServico) {
        this.credorServico = credorServico;
    }

    public Credor getCredor() {
        return credor;
    }

    public void setCredor(Credor credor) {
        this.credor = credor;
    }

    public void preRenderView() {
        String action = (String) JSFUtils.getParam("action");
        if (action.equalsIgnoreCase("editar")) {
            this.setCredor((Credor) JSFUtils.getParam("credor"));
            this.id = credor.getId();
            this.nome = credor.getNome();
            this.cpf = credor.getCpf();
            this.telefone  = credor.getTelefone();
            this.email = credor.getEmail();
        } else{
            this.setCredor(new Credor());
        }
    }   

    public void save() {
        if (isCredorEdit()) {
            credorServico.atualizar(this.credor);
            MessageUtils.messageSucess("Produto de carga atualizado com sucesso.");
        } else {
            this.credor = new Credor();
            credor.setNome(this.nome);
            credor.setCpf(this.cpf);
            credor.setTelefone(this.telefone);
            credor.setEmail(this.email);
            credorServico.salvar(credor);
            MessageUtils.messageSucess("Produto de carga adicionado com sucesso.");
        }
        JSFUtils.renderTo("credores.xhtml");
    }

    public boolean isCredorEdit() {
        String action = (String) JSFUtils.getParam("action");
        return action.equalsIgnoreCase("editar");
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
