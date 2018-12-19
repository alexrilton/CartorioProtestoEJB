package ifpe.dsc.cartorioprotesto.bean;

import ifpe.dsc.cartorioprotesto.model.Escrevente;
import ifpe.dsc.cartorioprotesto.service.EscreventeServico;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

@ManagedBean(name = "escreventeBean")
@javax.faces.bean.RequestScoped
public class EscreventeBean extends AbstractBean implements Serializable {

    @Inject
    @EJB
    private EscreventeServico escreventeServico;

    private List<Escrevente> escreventes;

    private String buscarPorNome;

    public List<Escrevente> getEscreventes() {
        return escreventes;
    }

    public void setEscreventes(List<Escrevente> escreventes) {
        this.escreventes = escreventes;
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
            escreventes = escreventeServico.getEscreventes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void buscarPorNome() {
        try {
            Escrevente escrevente = escreventeServico.getEscreventePorNome(buscarPorNome);
            escreventes.clear();
            escreventes.add(escrevente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}