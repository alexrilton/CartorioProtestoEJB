package ifpe.dsc.cartorioprotesto.bean;

import ifpe.dsc.cartorioprotesto.model.Recepcao;
import ifpe.dsc.cartorioprotesto.service.RecepcaoServico;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean(name = "recepcaoBean")
@javax.faces.bean.RequestScoped
public class RecepcaoBean extends AbstractBean implements Serializable {

    @Inject
    @EJB
    private RecepcaoServico recepcaoServico;

    private List<Recepcao> recepcoes;

    private String buscarPorNumero;

    public List<Recepcao> getRecepcoes() {
        return recepcoes;
    }

    public void setRecepcoes(List<Recepcao> recepcoes) {
        this.recepcoes = recepcoes;
    }

    public String getBuscarPorNumero() {
        return buscarPorNumero;
    }

    public void setBuscarPorNumero(String buscarPorNumero) {
        this.buscarPorNumero = buscarPorNumero;
    }

    @PostConstruct
    public void init() {
        listar();
    } 

    public void listar() {
        try {
            recepcoes = recepcaoServico.getRecepcoes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarPorNumero() {
        try {
            Recepcao recepcao = recepcaoServico.getRecepcaoPorNumero(buscarPorNumero);
            recepcoes.clear();
            recepcoes.add(recepcao);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}